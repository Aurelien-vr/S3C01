package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComboBox;
import dao.BienDAO;
import dao.DAOFactory;
import dao.LocataireDAO;
import dao.entities.Bien;
import utilities.ErrorMessage;
import view.BienAjoutView;

public class BienAjoutController extends TemplateAjoutController {

    private BienAjoutView viewAjoutBien = new BienAjoutView();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private boolean errorRaise;
    private List<List<String>> dataCbLocataire;
    
    private String defaultVal = "Créé seul";

    public BienAjoutController() {
        super();
        viewAjoutBien.setTitleHeader("Bien");
        pressedValider();
        pressedAjouterCl();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        populateCb();
        viewAjoutBien.setVisible(true);
    }
    
	private void logoLabel() {
		viewAjoutBien.getLogoLabel().addActionListener(e -> {
				new HomeController();
				viewAjoutBien.dispose();
		});
	}
    
    private void pressedValider() {
        viewAjoutBien.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkAdresse();
                checkEtage();
                checkVille();
                checkCodePostal();
                checkSuperficie();
                checkNombrePiece();
                checkAccesoirPrive();
                checkAccesoirCommun();
                if (!errorRaise) {
                    createBien();
                    new BienController();
                    viewAjoutBien.dispose();
            }
        });
    }

	private void pressedAnnuler() {
        viewAjoutBien.getAnnulerButton().addActionListener(e -> {
                new BienController();
                viewAjoutBien.dispose();
        });
    }
	
	private void pressedAjouterCl() {
		viewAjoutBien.getAjouterCLButton().addActionListener(e -> {
			new ContratLocationAjoutController();
			viewAjoutBien.dispose();
		});
	}
	
    private void checkAdresse() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldAdresse().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'adresse ne peut pas être vide");
            errorRaise = true;
        }
    }
    
    private void addEventHandlers() {
        viewAjoutBien.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewAjoutBien.dispose();
        });

        viewAjoutBien.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewAjoutBien.dispose();
        });
        
        viewAjoutBien.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewAjoutBien.dispose();
        });
        
        viewAjoutBien.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewAjoutBien.dispose();
        });
        
        viewAjoutBien.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewAjoutBien.dispose();
        });
        
        viewAjoutBien.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewAjoutBien.dispose();
        });
        
        viewAjoutBien.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewAjoutBien.dispose();
        });
    }

    private void checkEtage() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldEtage().getText();
        try {
            int etage = Integer.parseInt(input);
            if (etage < 0) {
                ErrorMessage.errorDialog("L'étage doit être supérieur ou égal à 0");
                errorRaise = true;
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("L'étage doit être un nombre entier");
            errorRaise = true;
        }
    }

    private void checkVille() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldVille().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La ville ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkCodePostal() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldCodePostal().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le code postal ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkSuperficie() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldSuperficie().getText();
        try {
            double superficie = Double.parseDouble(input);
            if (superficie <= 0) {
                ErrorMessage.errorDialog("La superficie doit être supérieure à 0");
                errorRaise = true;
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("La superficie doit être un nombre");
            errorRaise = true;
        }
    }

    private void checkNombrePiece() {
        if (errorRaise) { return; }
        int nombrePiece = (int) viewAjoutBien.getSpinnerNombrePiece().getValue();
        if (nombrePiece <= 0) {
            ErrorMessage.errorDialog("Le nombre de pièces doit être supérieur à 0");
            errorRaise = true;
        }
    }

    private void checkAccesoirPrive() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldAccesoirPrive().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'accesoir privé ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAccesoirCommun() {
        if (errorRaise) { return; }
        String input = viewAjoutBien.getFieldAccesoirCommun().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'accesoir commun ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void createBien() {
        try {
            String adresse = viewAjoutBien.getFieldAdresse().getText();
            int etage = Integer.parseInt(viewAjoutBien.getFieldEtage().getText());
            String ville = viewAjoutBien.getFieldVille().getText();
            String codePostal = viewAjoutBien.getFieldCodePostal().getText();
            BigDecimal superficie = new BigDecimal(viewAjoutBien.getFieldSuperficie().getText().replace(',', '.'));
            int nombrePiece = (int) viewAjoutBien.getSpinnerNombrePiece().getValue();
            boolean meuble = viewAjoutBien.getCheckMeuble().isSelected();
            String accesoirPrive = viewAjoutBien.getFieldAccesoirPrive().getText();
            String accesoirCommun = viewAjoutBien.getFieldAccesoirCommun().getText();
            boolean garage = viewAjoutBien.getCheckGarage().isSelected();
            
            Bien bien = new Bien(etage, adresse, ville, codePostal, superficie, nombrePiece, meuble, accesoirPrive, accesoirCommun,garage);
            
            if ((viewAjoutBien.getComboContratLocation().getSelectedItem() != defaultVal && viewAjoutBien.getComboBoxLocataire().getSelectedItem() == defaultVal) ||
            	    (viewAjoutBien.getComboContratLocation().getSelectedItem() == defaultVal && viewAjoutBien.getComboBoxLocataire().getSelectedItem() != defaultVal)) {
            	   ErrorMessage.errorDialog("Les 2 champs doivent être \"Créé seul\" ou aucun");
            }else {            	
            	modelBien.insert(bien);
            }
            
            //Handle fk
            if(viewAjoutBien.getComboContratLocation().getSelectedItem() != defaultVal) {
            	int locataire = retriveIdLocataire(viewAjoutBien.getComboBoxLocataire());
            	int contratLocation = Integer.parseInt((String) viewAjoutBien.getComboContratLocation().getSelectedItem());            	
            	modelBien.insertFK(bien.getIdBien(), contratLocation);
            	modelLocataire.insertFK(locataire, contratLocation);
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }
    
	private void populateCb() {
		String[] listCB = modelBien.procGetClNotInBien();
		dataCbLocataire = modelLocataire.procGetLocatairesActifs();
		
		viewAjoutBien.getComboContratLocation().addItem(defaultVal);
		viewAjoutBien.getComboBoxLocataire().addItem(defaultVal);
		for(String item : listCB){			
			viewAjoutBien.getComboContratLocation().addItem(item);
		}
		for(List<String> list: dataCbLocataire) {
			viewAjoutBien.getComboBoxLocataire().addItem(list.get(0));
		}
	}
	
	
	private int retriveIdLocataire(JComboBox<String> cb) {
		int index = 0;
	    for (List<String> sublist : dataCbLocataire) {
	        for (String str : sublist) {
	            if (str.equals(cb.getSelectedItem())) {
	                return Integer.parseInt(dataCbLocataire.get(index).get(1));
	            }
	        }
	        index++;
	    }
	    return -1;
	}
}