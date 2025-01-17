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
import dao.FactureEauDAO;
import dao.FactureElectriciteDAO;
import dao.FactureGazDAO;
import dao.entities.Bien;
import dao.entities.FactureEau;
import dao.entities.FactureElectricite;
import dao.entities.FactureGaz;
import utilities.ErrorMessage;
import view.FactureAjoutView;

public class FactureAjoutController extends TemplateAjoutController {

    private FactureAjoutView viewAjoutFacture = new FactureAjoutView();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private FactureDAO modelFacture = DAOFactory.createFactureDAO();
    private FactureEauDAO modelEau = DAOFactory.createFactureEauDAO();
    private FactureElectriciteDAO modelElectricite = DAOFactory.createFactureElectriciteDAO();
    private FactureGazDAO modelGaz = DAOFactory.createFactureGazDAO();
    
    private boolean errorRaise;
    private java.sql.Date sqlDate;
    private Map<String, Integer> addressToIdMap = new HashMap<>();

    public FactureAjoutController() {
        super();
        viewAjoutFacture.setTitleHeader("Facture");
        askForWaterDetails();
        addTypeFactureListener();
        populateAddressComboBox();
        pressedValider();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        viewAjoutFacture.setVisible(true);
    }
    
    

    private void addTypeFactureListener() {
        viewAjoutFacture.getCbTypeFacture().addActionListener(e -> {
                clearPreviousDetails();
                String selectedType = (String) viewAjoutFacture.getCbTypeFacture().getSelectedItem();
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
        viewAjoutFacture.getForm().revalidate();
        viewAjoutFacture.getForm().repaint();
    }
    
    private void clearPreviousDetails() {
        viewAjoutFacture.getForm().removeAll();
        viewAjoutFacture.fillFormWithFields();
    }

    private void createCustomDetail() {
        viewAjoutFacture.getGbc().gridy = 1;
        viewAjoutFacture.getGbc().gridx = 0;
        viewAjoutFacture.getForm().add(new JLabel("Detail"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 1;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldDetail(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getForm().revalidate();
        viewAjoutFacture.getForm().repaint();
    }

    private void askForWaterDetails() {
        viewAjoutFacture.getGbc().gridy = 1;
        viewAjoutFacture.getGbc().gridx = 0;
        viewAjoutFacture.getForm().add(new JLabel("Consommation en m3"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 1;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldWaterConsumption(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getForm().revalidate();
        viewAjoutFacture.getForm().repaint();
    }

    private void askForGasDetails() {
        viewAjoutFacture.getGbc().gridy = 1;
        viewAjoutFacture.getGbc().gridx = 0;
        viewAjoutFacture.getForm().add(new JLabel("Le prix du M3 de gaz"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 1;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldGasPrice(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 2;
        viewAjoutFacture.getForm().add(new JLabel("Consommation en m3 de gaz"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 3;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldGasConsumption(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getForm().revalidate();
        viewAjoutFacture.getForm().repaint();
    }

    private void askForElectricityDetails() {
        viewAjoutFacture.getGbc().gridy = 1;
        viewAjoutFacture.getGbc().gridx = 0;
        viewAjoutFacture.getForm().add(new JLabel("Le prix du kWh d'électricité"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 1;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldElectricityPrice(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 2;
        viewAjoutFacture.getForm().add(new JLabel("Consommation en kWh d'électricité"), viewAjoutFacture.getGbc());
        viewAjoutFacture.getGbc().gridx = 3;
        viewAjoutFacture.getForm().add(viewAjoutFacture.getFieldElectricityConsumption(), viewAjoutFacture.getGbc());
        viewAjoutFacture.getForm().revalidate();
        viewAjoutFacture.getForm().repaint();
    }

    private void pressedValider() {
        viewAjoutFacture.getValiderButton().addActionListener(e -> {
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
                    viewAjoutFacture.dispose();
                }
        });
    }

    private void pressedAnnuler() {
        viewAjoutFacture.getAnnulerButton().addActionListener(e -> {
                new FactureController();
                viewAjoutFacture.dispose();
        });
    }

    private void checkFactureName() {
        if (errorRaise) { return; }
        String input = viewAjoutFacture.getFieldFactureName().getText();
        if (input.isEmpty() || input.length() > 50) {
            ErrorMessage.errorDialog("Le nom de la facture doit être non vide et ne pas dépasser 50 caractères");
            errorRaise = true;
        }
    }

    private void checkMontantFacture() {
        if (errorRaise) { return; }
        String input = viewAjoutFacture.getFieldMontantFacture().getText();
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
        String input = viewAjoutFacture.getFieldDateFacture().getText();
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
        String input = viewAjoutFacture.getFieldMoyenPaiement().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le moyen de paiement ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAddress() {
        if (errorRaise) { return; }
        String selectedAddress = (String) viewAjoutFacture.getCbAdress().getSelectedItem();
        if (selectedAddress == null || selectedAddress.isEmpty()) {
            ErrorMessage.errorDialog("L'adresse ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkAdditionalDetails() {
        String selectedType = (String) viewAjoutFacture.getCbTypeFacture().getSelectedItem();
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
            double consumption = Double.parseDouble(viewAjoutFacture.getFieldWaterConsumption().getText().replace(',', '.'));
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
            double price = Double.parseDouble(viewAjoutFacture.getFieldGasPrice().getText().replace(',', '.'));
            double consumption = Double.parseDouble(viewAjoutFacture.getFieldGasConsumption().getText().replace(',', '.'));
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
            double price = Double.parseDouble(viewAjoutFacture.getFieldElectricityPrice().getText().replace(',', '.'));
            double consumption = Double.parseDouble(viewAjoutFacture.getFieldElectricityConsumption().getText().replace(',', '.'));
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
    		String factureName = viewAjoutFacture.getFieldFactureName().getText();
    		String selectedType = (String) viewAjoutFacture.getCbTypeFacture().getSelectedItem();
    		String factureType = selectedType.equals("Autre") ? viewAjoutFacture.getFieldDetail().getText() : selectedType;
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		dateFormat.setLenient(false);
    		java.util.Date parsedDate = dateFormat.parse(viewAjoutFacture.getFieldDateFacture().getText());
    		sqlDate = new java.sql.Date(parsedDate.getTime());
    		BigDecimal montantFacture = new BigDecimal(viewAjoutFacture.getFieldMontantFacture().getText().replace(',', '.'));
    		String moyenPaiement = viewAjoutFacture.getFieldMoyenPaiement().getText();
    		String selectedAddress = (String) viewAjoutFacture.getCbAdress().getSelectedItem();
    		Integer idBien = addressToIdMap.get(selectedAddress);
    		
    		Facture facture = new Facture(factureType, sqlDate, montantFacture, moyenPaiement);
    		facture.setReferenceFacture(factureName);
    		modelFacture.insert(facture);
    		if("ERROR CODE 1062".equals(facture.getReferenceFacture())) {
    			ErrorMessage.errorDialog("La facture n'a pas pu être inserer car l'ID de la facture est déjà utilisé");
    			return;
    		}
    		
    		modelFacture.insertFK(idBien, factureName);
    		

            String selectedTypeInsert = (String) viewAjoutFacture.getCbTypeFacture().getSelectedItem();
    		switch (selectedTypeInsert) {
	            case "Eau":
	            	BigDecimal consomation = new BigDecimal(viewAjoutFacture.getFieldWaterConsumption().getText().replace(',', '.'));
	            	FactureEau eau = new FactureEau(null, consomation);
	            	modelEau.insert(eau);
	            	modelEau.insertFK(eau.getIdFactureEau(),factureName);
	                break;
	            case "Gaz":
	                BigDecimal prixGaz = new BigDecimal(viewAjoutFacture.getFieldGasPrice().getText().replace(',', '.'));
	                String consommationGaz = viewAjoutFacture.getFieldGasConsumption().getText();
	                // Create and insert the Facture_gaz object
	                FactureGaz gaz = new FactureGaz(prixGaz, consommationGaz);
	                modelGaz.insert(gaz);
	                modelGaz.insertFK(gaz.getIdFactureGaz(), factureName);
	                break;
	            case "Electricité":
	                BigDecimal prixElectricite = new BigDecimal(viewAjoutFacture.getFieldElectricityPrice().getText().replace(',', '.'));
	                String consommationElectricite = viewAjoutFacture.getFieldElectricityConsumption().getText();
	                // Create and insert the Facture_electricite object
	                FactureElectricite electricite = new FactureElectricite(prixElectricite, consommationElectricite);
	                modelElectricite.insert(electricite);
	                modelElectricite.insertFK(electricite.getIdFactureElectricite(), factureName);
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
        viewAjoutFacture.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewAjoutFacture.dispose();
        });

        viewAjoutFacture.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewAjoutFacture.dispose();
        });
        
        viewAjoutFacture.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewAjoutFacture.dispose();
        });
        
        viewAjoutFacture.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewAjoutFacture.dispose();
        });
        
        viewAjoutFacture.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewAjoutFacture.dispose();
        });
        
        viewAjoutFacture.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewAjoutFacture.dispose();
        });
        
        viewAjoutFacture.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewAjoutFacture.dispose();
        });
    }


    private void populateAddressComboBox() {
        List<Bien> biens = modelBien.findAll();
        for (Bien bien : biens) {
            viewAjoutFacture.getCbAdress().addItem(bien.getAdresse());
            addressToIdMap.put(bien.getAdresse(), bien.getIdBien());
        }
    }
    
    
	private void logoLabel() {
		viewAjoutFacture.getLogoLabel().addActionListener(e->{
				new HomeController();
				viewAjoutFacture.dispose();
		});
	}
}