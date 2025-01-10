package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComboBox;
import dao.BienDAO;
import dao.DAOFactory;
import dao.LocataireDAO;
import dao.entities.Bien;
import utilities.ErrorMessage;
import view.BienAjout;

public class BienAjoutController extends TemplateAjoutController {

    private BienAjout view = new BienAjout();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private boolean errorRaise;
    private List<List<String>> dataCbLocataire;
    
    private String defaultVal = "Créé seul";

    public BienAjoutController() {
        super();
        view.setTitleHeader("Bien");
        pressedValider();
        pressedAjouterCl();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        populateCb();
        view.setVisible(true);
    }
    
	private void logoLabel() {
		view.getLogoLabel().addActionListener(e -> {
				new HomeController();
				view.dispose();
		});
	}
    
    private void pressedValider() {
        view.getValiderButton().addActionListener(e -> {
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
	
	private void pressedAjouterCl() {
		view.getAjouterCLButton().addActionListener(e -> {
			new ContratLocationAjoutController();
			view.dispose();
		});
	}
	
    private void checkAdresse() {
        if (errorRaise) { return; }
        String input = view.getFieldAdresse().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'adresse ne peut pas être vide");
            errorRaise = true;
        }
    }
    
    private void addEventHandlers() {
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
    }

    private void checkEtage() {
        if (errorRaise) { return; }
        String input = view.getFieldEtage().getText();
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
        String input = view.getFieldVille().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La ville ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkCodePostal() {
        if (errorRaise) { return; }
        String input = view.getFieldCodePostal().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le code postal ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkSuperficie() {
        if (errorRaise) { return; }
        String input = view.getFieldSuperficie().getText();
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
        int nombrePiece = (int) view.getSpinnerNombrePiece().getValue();
        if (nombrePiece <= 0) {
            ErrorMessage.errorDialog("Le nombre de pièces doit être supérieur à 0");
            errorRaise = true;
        }
    }

    private void checkAccesoirPrive() {
        if (errorRaise) { return; }
        String input = view.getFieldAccesoirPrive().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'accesoir privé ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAccesoirCommun() {
        if (errorRaise) { return; }
        String input = view.getFieldAccesoirCommun().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'accesoir commun ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void createBien() {
        try {
            String adresse = view.getFieldAdresse().getText();
            int etage = Integer.parseInt(view.getFieldEtage().getText());
            String ville = view.getFieldVille().getText();
            String codePostal = view.getFieldCodePostal().getText();
            BigDecimal superficie = new BigDecimal(view.getFieldSuperficie().getText().replace(',', '.'));
            int nombrePiece = (int) view.getSpinnerNombrePiece().getValue();
            boolean meuble = view.getCheckMeuble().isSelected();
            String accesoirPrive = view.getFieldAccesoirPrive().getText();
            String accesoirCommun = view.getFieldAccesoirCommun().getText();
            boolean garage = view.getCheckGarage().isSelected();
            
            Bien bien = new Bien(etage, adresse, ville, codePostal, superficie, nombrePiece, meuble, accesoirPrive, accesoirCommun,garage);
            
            if ((view.getComboContratLocation().getSelectedItem() != defaultVal && view.getComboBoxLocataire().getSelectedItem() == defaultVal) ||
            	    (view.getComboContratLocation().getSelectedItem() == defaultVal && view.getComboBoxLocataire().getSelectedItem() != defaultVal)) {
            	   ErrorMessage.errorDialog("Les 2 champs doivent être \"Créé seul\" ou aucun");
            }else {            	
            	modelBien.insert(bien);
            }
            
            //Handle fk
            if(view.getComboContratLocation().getSelectedItem() != defaultVal) {
            	int locataire = retriveIdLocataire(view.getComboBoxLocataire());
            	int contratLocation = Integer.parseInt((String) view.getComboContratLocation().getSelectedItem());            	
            	modelBien.insertFK(bien.getId_bien(), contratLocation);
            	modelLocataire.insertFK(locataire, contratLocation);
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }
    
	private void populateCb() {
		String[] listCB = modelBien.procGetClNotInBien();
		dataCbLocataire = modelLocataire.procGetLocatairesActifs();
		
		view.getComboContratLocation().addItem(defaultVal);
		view.getComboBoxLocataire().addItem(defaultVal);
		for(String item : listCB){			
			view.getComboContratLocation().addItem(item);
		}
		for(List<String> list: dataCbLocataire) {
			view.getComboBoxLocataire().addItem(list.get(0));
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