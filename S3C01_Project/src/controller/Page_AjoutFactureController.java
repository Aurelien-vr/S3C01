package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import dao.entities.Bien;
import dao.entities.Facture;
import dao.entities.Facture_eau;
import view.Page_AjoutFacture;
import view.ErrorMessage;

public class Page_AjoutFactureController extends AjoutSkeletonController {

    private Page_AjoutFacture view = new Page_AjoutFacture();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private FactureDAO modelFacture = DAOFactory.createFactureDAO();
    private Facture_eauDAO modelEau = DAOFactory.createFacture_eauDAO();
    
    private boolean errorRaise;
    private java.sql.Date sqlDate;
    private Map<String, Integer> addressToIdMap = new HashMap<>();

    public Page_AjoutFactureController() {
        super();
        askForWaterDetails();
        addTypeFactureListener();
        populateAddressComboBox();
        pressedValider();
        pressedAnnuler();
        view.setVisible(true);
    }

    private void addTypeFactureListener() {
        view.getCbTypeFacture().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPreviousDetails();
                String selectedType = (String) view.getCbTypeFacture().getSelectedItem();
                switch (selectedType) {
                    case "Eau":
                        askForWaterDetails();
                        break;
                    case "Gaz":
                        askForGasDetails();
                        break;
                    case "Electricité":
                        askForElectricityDetails();
                        break;
                    case "Custom":
                        createCustomDetail();
                        break;
                    default:
                        break;
                }
            }
        });
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
        view.getForm().add(new JLabel("Le prix du M3"), view.getGbc());
        view.getGbc().gridx = 1;
        view.getForm().add(view.getFieldWaterFixedPart(), view.getGbc());
        view.getGbc().gridx = 2;
        view.getForm().add(new JLabel("Consommation en m3"), view.getGbc());
        view.getGbc().gridx = 3;
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
        view.getValiderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorRaise = false;
                checkFactureName();
                checkDateFacture();
                checkMontantFacture();
                checkMoyenPaiement();
                checkAddress();
                checkAdditionalDetails();
                if (!errorRaise) {
                    createFacture();
                    new Page_FactureController();
                    view.dispose();
                }
            }
        });
    }

    private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Page_FactureController();
                view.dispose();
            }
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
            case "Eau":
                checkWaterDetails();
                break;
            case "Gaz":
                checkGasDetails();
                break;
            case "Electricité":
                checkElectricityDetails();
                break;
            default:
                break;
        }
    }

    private void checkWaterDetails() {
        if (errorRaise) { return; }
        try {
            double fixedPart = Double.parseDouble(view.getFieldWaterFixedPart().getText().replace(',', '.'));
            double consumption = Double.parseDouble(view.getFieldWaterConsumption().getText().replace(',', '.'));
            if (fixedPart < 0 || consumption < 0) {
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
    		String factureType = selectedType.equals("Custom") ? view.getFieldDetail().getText() : selectedType;
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
    		modelFacture.insertFK(idBien, factureName);
    		
    		/*
    		 * 
            String selectedTypeInsert = (String) view.getCbTypeFacture().getSelectedItem();
    		switch (selectedTypeInsert) {
	            case "Eau":
	            	BigDecimal partieFix = new BigDecimal(view.getFieldWaterFixedPart().getText().replace(',', '.'));
	            	BigDecimal consomation = new BigDecimal(view.getFieldWaterConsumption().getText().replace(',', '.'));
	            	Facture_eau eau = new Facture_eau(partieFix, consomation);
	            	modelEau.insert(eau);
	                break;
	            case "Gaz":
	                break;
	            case "Electricité":
	                break;
	            case "Custom":
	                break;
	            default:
	                break;
    		}
    		 */
    		
		} catch (Exception e) {
			ErrorMessage.errorDialog("Erreur lors de l'insertion dans la base de données");
		}
        
        
    }


    private void populateAddressComboBox() {
        List<Bien> biens = modelBien.findAll();
        for (Bien bien : biens) {
            view.getCbAdress().addItem(bien.getAdresse());
            addressToIdMap.put(bien.getAdresse(), bien.getId_bien());
        }
    }

    public static void main(String[] args) {
        new Page_AjoutFactureController();
    }
}