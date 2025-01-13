package controller;

import dao.BienDAO;
import dao.Contrat_locationDAO;
import dao.LocataireDAO;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;
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

    private ContratLocationAjoutView view = new ContratLocationAjoutView();
    private BienDAO bienDAO = DAOFactory.createBienDAO();
    private LocataireDAO locataireDAO = DAOFactory.createLocataireDAO();
    private Contrat_locationDAO contratLocationDAO = DAOFactory.createContrat_locationDAO();
    private boolean errorRaise;
    List<List<String>> dataLocaSansContrat;
	List<List<String>> dataBienSansContrat;

    public ContratLocationAjoutController() {
        super();
        view.setTitleHeader("Ajout Contrat Location");
        pressedValider();
        pressedAnnuler();
        pressedAjouterBien();
        pressedAjouterLocataire();
        populateComboBoxes();
        addEventHandlers();
        view.setVisible(true);
    }

    private void pressedValider() {
        view.getValiderButton().addActionListener(e -> {
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
                view.dispose();
            }
        });
    }
    
    private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(e -> {
            new BienController();
            view.dispose();
        });
    }

    private void pressedAjouterBien() {
        view.getAjouterBienButton().addActionListener(e -> {
            new BienAjoutController();
            view.dispose();
        });
    }

    private void pressedAjouterLocataire() {
        view.getAjouterLocataireButton().addActionListener(e -> {
            new LocataireAjoutController();
            view.dispose();
        });
    }

    private void checkMontant() {
        if (errorRaise) { return; }
        String input = view.getFieldMontant().getText();
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
        String input = view.getFieldDateDebut().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de début ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateFin() {
        if (errorRaise) { return; }
        String input = view.getFieldDateFin().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de fin ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkModaliteChauffage() {
        if (errorRaise) { return; }
        String input = view.getFieldModaliteChauffage().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La modalité chauffage ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkModaliteEauChaude() {
        if (errorRaise) { return; }
        String input = view.getFieldModaliteEauChaude().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La modalité eau chaude sanitaire ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateVersement() {
        if (errorRaise) { return; }
        String input = view.getFieldDateVersement().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de versement ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void createContratLocation() {
        try {
            int montant = Integer.parseInt(view.getFieldMontant().getText());
            Date dateDebut = transformStringToDate(view.getFieldDateDebut().getText());
            Date dateFin = transformStringToDate(view.getFieldDateFin().getText());
            String modaliteChauffage = view.getFieldModaliteChauffage().getText();
            String modaliteEauChaude = view.getFieldModaliteEauChaude().getText();
            Date dateVersement = transformStringToDate(view.getFieldDateVersement().getText());
            //Recupere le l'indice de la combo box afin de recuperer l'id placer dans la liste de données recuperer par la procédure SQL
            int bienId = Integer.parseInt(dataBienSansContrat.get(view.getComboBien().getSelectedIndex()).get(1));
            int locataireId = Integer.parseInt(dataBienSansContrat.get(view.getComboLocataire().getSelectedIndex()).get(1));
            Contrat_location contratLocation = new Contrat_location(montant, dateDebut, dateFin, modaliteChauffage, modaliteEauChaude, dateVersement);
            contratLocationDAO.insert(contratLocation);
            // Lie le bien et le locataire au contrat de location en recuperant la clé auto générer  de contrat location
            contratLocationDAO.procUpdateFkBienLocation(contratLocation.getNumero_location(), bienId, locataireId);;
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }

    private void populateComboBoxes() {
    	dataLocaSansContrat = locataireDAO.procLocataireSansContrat();
    	dataBienSansContrat = bienDAO.procBienSansContrat();

    	if(dataBienSansContrat.isEmpty() || dataLocaSansContrat.isEmpty()) {
    		view.getValiderButton().setEnabled(false);
    	}
    	
		for(List<String> list : dataLocaSansContrat) {
			view.getComboBien().addItem(list.get(0));
		}
		
		for(List<String> list : dataBienSansContrat) {
			view.getComboLocataire().addItem(list.get(0));
		}
	}
    
    public static Date transformStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date parsed = format.parse(dateString);
            return new Date(parsed.getTime());
        } catch (ParseException e) {
        	ExceptionStorageHandler.LogException(e, DatabaseConnection.getInstance());
            return null;
        }
    }
    
    private void addEventHandlers() {
    	
	    view.getLogoLabel().addActionListener(e -> {
	        new HomeController();
	        view.dispose();
	    });
    	
        view.getBtnBienLouable().addActionListener(e -> {
            new BienController();
            view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
            new LocataireController();
        });
        
        view.getBtnContratLocation().addActionListener(e -> {
            new ContratLocationController();
            view.dispose();
        });
        
        view.getItemAssurance().addActionListener(e -> {
            new AssuranceController();
            view.dispose();
        });
        
        view.getItemFacture().addActionListener(e -> {
            new FactureController();
            view.dispose();
        });
        
        view.getItemTravaux().addActionListener(e -> {
            new TravauxController();
            view.dispose();
        });
        
        view.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	view.dispose();
        });
    }
}