package controller;

import dao.BienDAO;
import dao.ContratLocationDAO;
import dao.LocataireDAO;
import dao.entities.ContratLocation;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;
import utilities.ErrorMessage;
import dao.DAOFactory;
import view.ContratLocationAjoutView;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ContratLocationAjoutController extends TemplateAjoutController {

    private ContratLocationAjoutView viewContratLocation = new ContratLocationAjoutView();
    private BienDAO bienDAO = DAOFactory.createBienDAO();
    private LocataireDAO locataireDAO = DAOFactory.createLocataireDAO();
    private ContratLocationDAO contratLocationDAO = DAOFactory.createContratLocationDAO();
    private boolean errorRaise;
    List<List<String>> dataLocaSansContrat;
	List<List<String>> dataBienSansContrat;

    public ContratLocationAjoutController() {
        super();
        viewContratLocation.setTitleHeader("Ajout Contrat Location");
        pressedValider();
        pressedAnnuler();
        pressedAjouterBien();
        pressedAjouterLocataire();
        populateComboBoxes();
        addEventHandlers();
        viewContratLocation.setVisible(true);
    }

    private void pressedValider() {
        viewContratLocation.getValiderButton().addActionListener(e -> {
            errorRaise = false;
            checkMontant();
            checkDateDebut();
            checkDateFin();
            checkModaliteChauffage();
            checkModaliteEauChaude();
            checkDateVersement();
            if (!errorRaise) {
                createContratLocation();
                new BienController();
                viewContratLocation.dispose();
            }
        });
    }
    
    private void pressedAnnuler() {
        viewContratLocation.getAnnulerButton().addActionListener(e -> {
            new BienController();
            viewContratLocation.dispose();
        });
    }

    private void pressedAjouterBien() {
        viewContratLocation.getAjouterBienButton().addActionListener(e -> {
            new BienAjoutController();
            viewContratLocation.dispose();
        });
    }

    private void pressedAjouterLocataire() {
        viewContratLocation.getAjouterLocataireButton().addActionListener(e -> {
            new LocataireAjoutController();
            viewContratLocation.dispose();
        });
    }

    private void checkMontant() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldMontant().getText();
        try {
            BigDecimal montant = new BigDecimal(input);
            if (montant.compareTo(BigDecimal.ZERO) <= 0) {
                ErrorMessage.errorDialog("Le montant doit être supérieur à 0");
                errorRaise = true;
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Le montant doit être un nombre valide");
            errorRaise = true;
        }
    }

    private void checkDateDebut() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldDateDebut().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de début ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateFin() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldDateFin().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de fin ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkModaliteChauffage() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldModaliteChauffage().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La modalité chauffage ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkModaliteEauChaude() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldModaliteEauChaude().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La modalité eau chaude sanitaire ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateVersement() {
        if (errorRaise) { return; }
        String input = viewContratLocation.getFieldDateVersement().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de versement ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void createContratLocation() {
        try {
            int montant = Integer.parseInt(viewContratLocation.getFieldMontant().getText());
            Date dateDebut = transformStringToDate(viewContratLocation.getFieldDateDebut().getText());
            Date dateFin = transformStringToDate(viewContratLocation.getFieldDateFin().getText());
            String modaliteChauffage = viewContratLocation.getFieldModaliteChauffage().getText();
            String modaliteEauChaude = viewContratLocation.getFieldModaliteEauChaude().getText();
            Date dateVersement = transformStringToDate(viewContratLocation.getFieldDateVersement().getText());
            //Recupere le l'indice de la combo box afin de recuperer l'id placer dans la liste de données recuperer par la procédure SQL
            int bienId = Integer.parseInt(dataBienSansContrat.get(viewContratLocation.getComboBien().getSelectedIndex()).get(1));
            int locataireId = Integer.parseInt(dataBienSansContrat.get(viewContratLocation.getComboLocataire().getSelectedIndex()).get(1));
            
            //create the contrat location with the data for inserting it
            ContratLocation contratLocation = new ContratLocation(montant, dateDebut, dateFin, modaliteChauffage, modaliteEauChaude, dateVersement);
            contratLocationDAO.insert(contratLocation);
            // Lie le bien et le locataire au contrat de location en recuperant la clé auto générer  de contrat location
            contratLocationDAO.procUpdateFkBienLocation(contratLocation.getNumeroLocation(), bienId, locataireId);
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }

    private void populateComboBoxes() {
    	dataLocaSansContrat = locataireDAO.procLocataireSansContrat();
    	dataBienSansContrat = bienDAO.procBienSansContrat();

    	if(dataBienSansContrat.isEmpty() || dataLocaSansContrat.isEmpty()) {
    		viewContratLocation.getValiderButton().setEnabled(false);
    	}
    	
		for(List<String> list : dataLocaSansContrat) {
			viewContratLocation.getComboBien().addItem(list.get(0));
		}
		
		for(List<String> list : dataBienSansContrat) {
			viewContratLocation.getComboLocataire().addItem(list.get(0));
		}
	}
    
    public static Date transformStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date parsed = format.parse(dateString);
            return new Date(parsed.getTime());
        } catch (ParseException e) {
        	ExceptionStorageHandler.logException(e, DatabaseConnection.getInstance());
            return null;
        }
    }
    
    private void addEventHandlers() {
    	
	    viewContratLocation.getLogoLabel().addActionListener(e -> {
	        new HomeController();
	        viewContratLocation.dispose();
	    });
    	
        viewContratLocation.getBtnBienLouable().addActionListener(e -> {
            new BienController();
            viewContratLocation.dispose();
        });

        viewContratLocation.getBtnLocataire().addActionListener(e -> {
            new LocataireController();
            viewContratLocation.dispose();
        });
        
        viewContratLocation.getBtnContratLocation().addActionListener(e -> {
            new ContratLocationController();
            viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemAssurance().addActionListener(e -> {
            new AssuranceController();
            viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemFacture().addActionListener(e -> {
            new FactureController();
            viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemTravaux().addActionListener(e -> {
            new TravauxController();
            viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewContratLocation.dispose();
        });
    }
}