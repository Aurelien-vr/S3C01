package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import dao.BienDAO;
import dao.DAOFactory;
import dao.FactureDAO;
import dao.TravauxDAO;
import dao.entities.Travaux;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;
import utilities.ErrorMessage;
import view.TravauxAjout;

public class TravauxAjoutController extends TemplateAjoutController {

    private TravauxAjout viewTravaux = new TravauxAjout();
    private FactureDAO model = DAOFactory.createFactureDAO();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private TravauxDAO modelTravaux = DAOFactory.createTravauxDAO();
    private boolean errorRaise;
    private Date sqlDate; // Class variable to store the SQL date

    public TravauxAjoutController() {
        super();
        viewTravaux.setTitleHeader("Travaux");
        viewTravaux.setCbFacture(model.getNumFacture());
        viewTravaux.getLabResAdress().setText(modelBien.procAdressOfFacture((String) viewTravaux.getCbFacture().getSelectedItem()));
        addAutoResultListener(new JTextField[]{viewTravaux.getFieldMontant(), viewTravaux.getFieldMontantNonDeductible(), viewTravaux.getFielReduction()});
        pressedValider();
        pressedAnnuler();
        pressedAjouterTravauxButton();
        addEventHandlers();
        addComboBoxListener();
        logoLabel();

        viewTravaux.setVisible(true);
    }

	private void addAutoResultListener(JTextField[] jTextFields) {
        for (JTextField field : jTextFields) {
            field.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    calculate();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    calculate();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    calculate();
                }

                private void calculate() {
                    double result = 0.0;
                    try {
                        String montText = viewTravaux.getFieldMontant().getText();
                        String montNDText = viewTravaux.getFieldMontantNonDeductible().getText();
                        String reducText = viewTravaux.getFielReduction().getText();

                        if (montText.isEmpty() || montNDText.isEmpty() || reducText.isEmpty()) {
                            viewTravaux.getResultField().setText("");
                            return;
                        }

                        double mont = Double.parseDouble(montText);
                        double montND = Double.parseDouble(montNDText);
                        double reduc = Double.parseDouble(reducText);
                        result = (mont - montND) * (1 - reduc / 100);
                        DecimalFormat df = new DecimalFormat("#.##");
                        viewTravaux.getResultField().setText(df.format(result));
                        
                    } catch (NumberFormatException e) {
                        viewTravaux.getResultField().setText("");
                    } catch (Exception e) {
                        ExceptionStorageHandler.logException(e, DatabaseConnection.getInstance());
                    }
                }
            });
        }
    }
	
    private void addComboBoxListener() {
        viewTravaux.getCbFacture().addActionListener(e -> {
            String selectedFacture = (String) viewTravaux.getCbFacture().getSelectedItem();
            if (selectedFacture != null) {
                String address = modelBien.procAdressOfFacture(selectedFacture);
                viewTravaux.getLabResAdress().setText(address);
            }else {
            	 viewTravaux.getLabResAdress().setText("Aucune adresse liée");
            }
        });
    }

    private void pressedValider() {
        viewTravaux.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkMontant();
                checkMontantNonDeductible();
                checkReduction();
                checkDate();
                if (!errorRaise) {
                    createTravaux();
                    new TravauxController();
                    viewTravaux.dispose();
                }
        });
    }

    private void checkMontantNonDeductible() {
        if (errorRaise) { return; }
        String input = viewTravaux.getFieldMontantNonDeductible().getText();
        try {
            double montantNonDeductible = Double.parseDouble(input.replace(',', '.'));
            if (montantNonDeductible < 0) {
                ErrorMessage.errorDialog("Le champs montant non déductible doit être supérieur à 0");
                errorRaise = true;
            }
            double montant = Double.parseDouble(viewTravaux.getFieldMontant().getText().replace(',', '.'));
            if (montantNonDeductible > montant) {
                ErrorMessage.errorDialog("Le champs montant non déductible non déductible doit être inférieur au montant");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le champs montant non déductible doit être un nombre");
            errorRaise = true;
        }
    }

    private void checkMontant() {
        if (errorRaise) { return; }
        String input = viewTravaux.getFieldMontant().getText();
        try {
            double montant = Double.parseDouble(input.replace(',', '.'));
            if (montant < 0) {
                ErrorMessage.errorDialog("Le champs montant doit être supérieur à 0");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("Le champs montant doit être un nombre");
            errorRaise = true;
        }
    }

    private void checkReduction() {
        if (errorRaise) { return; }
        String input = viewTravaux.getFielReduction().getText();
        try {
            double reduction = Double.parseDouble(input.replace(',', '.'));
            if (reduction < 0) {
                ErrorMessage.errorDialog("La réduction doit être supérieur à 0");
                errorRaise = true;
            }
            if (reduction > 100) {
                ErrorMessage.errorDialog("La réduction de peut pas éxéder 100%");
                errorRaise = true;
            }
        } catch (Exception e) {
            ErrorMessage.errorDialog("La réduction doit être un nombre et être contenu entre 0 et 100");
            errorRaise = true;
        }
    }

    private void checkDate() {
        if (errorRaise) { return; }
        String input = viewTravaux.getDateField().getText();
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

    private void createTravaux() {
        try {
            Date dateTravaux = sqlDate;
            String nature = viewTravaux.getNatureField().getText();
            BigDecimal reduction = new BigDecimal(viewTravaux.getFielReduction().getText().replace(',', '.'));
            BigDecimal montant = new BigDecimal(viewTravaux.getFieldMontant().getText().replace(',', '.'));
            BigDecimal montantNonDeductible = new BigDecimal(viewTravaux.getFieldMontantNonDeductible().getText().replace(',', '.'));
            
            Travaux travaux = new Travaux(dateTravaux, nature, null, reduction, montant, montantNonDeductible, null);
            
            modelTravaux.insert(travaux);
            modelTravaux.insertFK(travaux.getNumeroFacture(), viewTravaux.getCbFacture().getSelectedItem().toString());
        } catch (Exception e) {
            ErrorMessage.errorDialog("Erreur lors de la création de l'objet Travaux: " + e.getMessage());
        }
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    private void pressedAnnuler() {
        viewTravaux.getAnnulerButton().addActionListener(e ->{
                new TravauxController();
                viewTravaux.dispose();
        });
    }
    
    
    private void pressedAjouterTravauxButton() {
    	viewTravaux.getAjouterFactureButton().addActionListener(e -> {
				new FactureAjoutController();
				viewTravaux.dispose();		
		});
	}
    
	private void logoLabel() {
		viewTravaux.getLogoLabel().addActionListener(e -> {
				new HomeController();
				viewTravaux.dispose();
		});
	}
	
	 private void addEventHandlers() {
	        viewTravaux.getBtnBienLouable().addActionListener(e -> {
	        	new BienController();
	        	viewTravaux.dispose();
	        });

	        viewTravaux.getBtnLocataire().addActionListener(e -> {
	        	new LocataireController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getBtnContratLocation().addActionListener(e -> {
	        	new ContratLocationController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemAssurance().addActionListener(e -> {
	        	new AssuranceController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemFacture().addActionListener(e -> {
	        	new FactureController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemTravaux().addActionListener(e -> {
	        	new TravauxController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemCharge().addActionListener(e -> {
	        	new ChargeController();
	        	viewTravaux.dispose();
	        });
	    }
}