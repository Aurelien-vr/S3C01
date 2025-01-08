package controller;

import java.math.BigDecimal;
import dao.BienDAO;
import dao.DAOFactory;
import dao.entities.Bien;
import view.Page_AjoutBien;
import view.ErrorMessage;

public class Page_AjoutBienController extends AjoutSkeletonController {

    private Page_AjoutBien view = new Page_AjoutBien();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private boolean errorRaise;
    
    private String erreur = "Aucun disponible";

    public Page_AjoutBienController() {
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
				new Page_PrincipaleController();
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
                    new Page_BienController();
                    view.dispose();
            }
        });
    }

	private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(e -> {
                new Page_BienController();
                view.dispose();
        });
    }
	
	private void pressedAjouterCl() {
		view.getAjouterCLButton().addActionListener(e -> {
			new Page_AjoutContratLocationController();
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
        	new Page_BienController();
        	view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
                System.out.println("Locataire clicked");
        });
        
        view.getBtnContratLocation().addActionListener(e -> {
        	new Page_ContratLocationController();
        	view.dispose();
        });

        view.getBtnDocument().addActionListener(e ->{
                System.out.println("Doc cliqué");
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
            int contratLocation = Integer.parseInt((String) view.getComboContratLocation().getSelectedItem());
            
            Bien bien = new Bien(etage, adresse, ville, codePostal, superficie, nombrePiece, meuble, accesoirPrive, accesoirCommun,garage);
            modelBien.insert(bien);
            modelBien.insertFK(bien.getId_bien(), contratLocation);
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }
    
	private void populateCb() {
		String[] list = modelBien.procGetClNotInBien();
		if(list.length != 0) {
			for(String item : list){			
				view.getComboContratLocation().addItem(item);
			}
		}else {
			view.getComboContratLocation().addItem(erreur);
			view.getValiderButton().setEnabled(false);
		}
	}
}