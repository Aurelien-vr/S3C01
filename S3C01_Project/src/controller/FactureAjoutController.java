package controller;

import view.FactureView;
import dao.entities.Facture;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import dao.BienDAO;
import dao.DAOFactory;
import dao.FactureDAO;
import dao.Facture_eauDAO;
import dao.Facture_electriciteDAO;
import dao.Facture_gazDAO;
import dao.entities.Bien;
import dao.entities.Facture_eau;
import dao.entities.Facture_electricite;
import dao.entities.Facture_gaz;
import utilities.ErrorMessage;
import view.FactureAjoutView;

public class FactureAjoutController extends TemplateAjoutController {

    private FactureAjoutView view = new FactureAjoutView();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private FactureDAO modelFacture = DAOFactory.createFactureDAO();
    private Facture_eauDAO modelEau = DAOFactory.createFacture_eauDAO();
    private Facture_electriciteDAO modelElectricite = DAOFactory.createFacture_electriciteDAO();
    private Facture_gazDAO modelGaz = DAOFactory.createFacture_gazDAO();
    
    private boolean errorRaise;
    private java.sql.Date sqlDate;
    private Map<String, Integer> addressToIdMap = new HashMap<>();

    public FactureAjoutController() {
        super();
        view.setTitleHeader("Facture");
        askForWaterDetails();
        addTypeFactureListener();
        populateAddressComboBox();
        pressedValider();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        view.setVisible(true);
    }
    
    

    private void addTypeFactureListener() {
        view.getCbTypeFacture().addActionListener(e -> {
                clearPreviousDetails();
                String selectedType = (String) view.getCbTypeFacture().getSelectedItem();
                switch (selectedType) {
                    case FactureView.TYPE_EAU:
                        askForWaterDetails();
                        break;
                    case FactureView.TYPE_GAZ:
                        askForGasDetails();
                        break;
                    case FactureView.TYPE_ELECTRICITE:
                        askForElectricityDetails();
                        break;
                    case FactureView.TYPE_ORDURE_MENAGERE:
                    	repaint();
                    	break;
                    case FactureView.TYPE_ENTRETIENT:
                    	repaint();
                    	break;
                    case FactureView.TYPE_AUTRE:
                        createCustomDetail();
                        break;
                    default:
                        break;
                }
        });
    }
    
    private void repaint() {
        view.getForm().revalidate();
        view.getForm().repaint();
    }
    
    private void clearPreviousDetails() {
        view.getForm().removeAll();
        view.fillFormWithFields();
    }

    private void createCustomDetail() {
        view.getGbc().gridy = 1;
        view.getGbc().gridx = 0;
        view.getForm().add(new JLabel("Detail"), view.getGbc());
        view.getGbc().gridx = 1;
        view.getForm().add(view.getFieldDetail(), view.getGbc());
        view.getForm().revalidate();
        view.getForm().repaint();
    }

    private void askForWaterDetails() {
        view.getGbc().gridy = 1;
        view.getGbc().gridx = 0;
        view.getForm().add(new JLabel("Consommation en m3"), view.getGbc());
        view.getGbc().gridx = 1;
        view.getForm().add(view.getFieldWaterConsumption(), view.getGbc());
        view.getForm().revalidate();
        view.getForm().repaint();
    }

    private void askForGasDetails() {
        view.getGbc().gridy = 1;
        view.getGbc().gridx = 0;
        view.getForm().add(new JLabel("Le prix du M3 de gaz"), view.getGbc());
        view.getGbc().gridx = 1;
        view.getForm().add(view.getFieldGasPrice(), view.getGbc());
        view.getGbc().gridx = 2;
        view.getForm().add(new JLabel("Consommation en m3 de gaz"), view.getGbc());
        view.getGbc().gridx = 3;
        view.getForm().add(view.getFieldGasConsumption(), view.getGbc());
        view.getForm().revalidate();
        view.getForm().repaint();
    }

    private void askForElectricityDetails() {
        view.getGbc().gridy = 1;
        view.getGbc().gridx = 0;
        view.getForm().add(new JLabel("Le prix du kWh d'électricité"), view.getGbc());
        view.getGbc().gridx = 1;
        view.getForm().add(view.getFieldElectricityPrice(), view.getGbc());
        view.getGbc().gridx = 2;
        view.getForm().add(new JLabel("Consommation en kWh d'électricité"), view.getGbc());
        view.getGbc().gridx = 3;
        view.getForm().add(view.getFieldElectricityConsumption(), view.getGbc());
        view.getForm().revalidate();
        view.getForm().repaint();
    }

    private void pressedValider() {
        view.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkFactureName();
                checkDateFacture();
                checkMontantFacture();
                checkMoyenPaiement();
                checkAddress();
                checkAdditionalDetails();
                if (!errorRaise) {
                    createFacture();
                    new FactureController();
                    view.dispose();
                }
        });
    }

    private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(e -> {
                new FactureController();
                view.dispose();
        });
    }

    private void checkFactureName() {
        if (errorRaise) { return; }
        String input = view.getFieldFactureName().getText();
        if (input.isEmpty() || input.length() > 50) {
            ErrorMessage.errorDialog("Le nom de la facture doit être non vide et ne pas dépasser 50 caractères");
            errorRaise = true;
        }
    }

    private void checkMontantFacture() {
        if (errorRaise) { return; }
        String input = view.getFieldMontantFacture().getText();
        try {
            double montant = Double.parseDouble(input.replace(',', '.'));
            if (montant < 0) {
                ErrorMessage.errorDialog("Le montant de la facture doit être supérieur à 0");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le montant de la facture doit être un nombre");
            errorRaise = true;
        }
    }

    private void checkDateFacture() {
        if (errorRaise) { return; }
        String input = view.getFieldDateFacture().getText();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            java.util.Date parsedDate = dateFormat.parse(input);
            sqlDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            ErrorMessage.errorDialog("La date doit être au format jj/mm/aaaa");
            errorRaise = true;
        }
    }

    private void checkMoyenPaiement() {
        if (errorRaise) { return; }
        String input = view.getFieldMoyenPaiement().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le moyen de paiement ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAddress() {
        if (errorRaise) { return; }
        String selectedAddress = (String) view.getCbAdress().getSelectedItem();
        if (selectedAddress == null || selectedAddress.isEmpty()) {
            ErrorMessage.errorDialog("L'adresse ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAdditionalDetails() {
        String selectedType = (String) view.getCbTypeFacture().getSelectedItem();
        switch (selectedType) {
            case FactureView.TYPE_EAU:
                checkWaterDetails();
                break;
            case FactureView.TYPE_GAZ:
                checkGasDetails();
                break;
            case FactureView.TYPE_ELECTRICITE:
                checkElectricityDetails();
                break;
            default:
                break;
        }
    }

    private void checkWaterDetails() {
        if (errorRaise) { return; }
        try {
            double consumption = Double.parseDouble(view.getFieldWaterConsumption().getText().replace(',', '.'));
            if (consumption < 0) {
                ErrorMessage.errorDialog("Le prix et la consommation d'eau doivent être des nombres positifs");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le prix et la consommation d'eau doivent être des nombres");
            errorRaise = true;
        }
    }

    private void checkGasDetails() {
        if (errorRaise) { return; }
        try {
            double price = Double.parseDouble(view.getFieldGasPrice().getText().replace(',', '.'));
            double consumption = Double.parseDouble(view.getFieldGasConsumption().getText().replace(',', '.'));
            if (price < 0 || consumption < 0) {
                ErrorMessage.errorDialog("Le prix et la consommation de gaz doivent être des nombres positifs");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le prix et la consommation de gaz doivent être des nombres");
            errorRaise = true;
        }
    }

    private void checkElectricityDetails() {
        if (errorRaise) { return; }
        try {
            double price = Double.parseDouble(view.getFieldElectricityPrice().getText().replace(',', '.'));
            double consumption = Double.parseDouble(view.getFieldElectricityConsumption().getText().replace(',', '.'));
            if (price < 0 || consumption < 0) {
                ErrorMessage.errorDialog("Le prix et la consommation d'électricité doivent être des nombres positifs");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le prix et la consommation d'électricité doivent être des nombres");
            errorRaise = true;
        }
    }

    private void createFacture() {
    	try {
    		String factureName = view.getFieldFactureName().getText();
    		String selectedType = (String) view.getCbTypeFacture().getSelectedItem();
    		String factureType = selectedType.equals("Autre") ? view.getFieldDetail().getText() : selectedType;
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		dateFormat.setLenient(false);
    		java.util.Date parsedDate = dateFormat.parse(view.getFieldDateFacture().getText());
    		sqlDate = new java.sql.Date(parsedDate.getTime());
    		BigDecimal montantFacture = new BigDecimal(view.getFieldMontantFacture().getText().replace(',', '.'));
    		String moyenPaiement = view.getFieldMoyenPaiement().getText();
    		String selectedAddress = (String) view.getCbAdress().getSelectedItem();
    		Integer idBien = addressToIdMap.get(selectedAddress);
    		
    		Facture facture = new Facture(factureType, sqlDate, montantFacture, moyenPaiement);
    		facture.setReference_facture(factureName);
    		modelFacture.insert(facture);
    		if("ERROR CODE 1062".equals(facture.getReference_facture())) {
    			ErrorMessage.errorDialog("La facture n'a pas pu être inserer car l'ID de la facture est déjà utilisé");
    			return;
    		}
    		
    		modelFacture.insertFK(idBien, factureName);
    		

            String selectedTypeInsert = (String) view.getCbTypeFacture().getSelectedItem();
    		switch (selectedTypeInsert) {
	            case "Eau":
	            	BigDecimal consomation = new BigDecimal(view.getFieldWaterConsumption().getText().replace(',', '.'));
	            	Facture_eau eau = new Facture_eau(null, consomation);
	            	modelEau.insert(eau);
	            	modelEau.insertFK(eau.getId_facture_eau(),factureName);
	                break;
	            case "Gaz":
	                BigDecimal prixGaz = new BigDecimal(view.getFieldGasPrice().getText().replace(',', '.'));
	                String consommationGaz = view.getFieldGasConsumption().getText();
	                // Create and insert the Facture_gaz object
	                Facture_gaz gaz = new Facture_gaz(prixGaz, consommationGaz);
	                modelGaz.insert(gaz);
	                modelGaz.insertFK(gaz.getId_facture_gaz(), factureName);
	                break;
	            case "Electricité":
	                BigDecimal prixElectricite = new BigDecimal(view.getFieldElectricityPrice().getText().replace(',', '.'));
	                String consommationElectricite = view.getFieldElectricityConsumption().getText();
	                // Create and insert the Facture_electricite object
	                Facture_electricite electricite = new Facture_electricite(prixElectricite, consommationElectricite);
	                modelElectricite.insert(electricite);
	                modelElectricite.insertFK(electricite.getId_facture_electricite(), factureName);
	                break;
	            case "Autre":
	                break;
	            default:
	                break;
	        }
    		
		} catch (Exception e) {
			ErrorMessage.errorDialog("Erreur lors de l'insertion dans la base de données");
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
        
        view.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	view.dispose();
        });
    }


    private void populateAddressComboBox() {
        List<Bien> biens = modelBien.findAll();
        for (Bien bien : biens) {
            view.getCbAdress().addItem(bien.getAdresse());
            addressToIdMap.put(bien.getAdresse(), bien.getId_bien());
        }
    }
    
    
	private void logoLabel() {
		view.getLogoLabel().addActionListener(e->{
				new HomeController();
				view.dispose();
		});
	}
}