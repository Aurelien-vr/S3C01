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
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;
import utilities.ErrorMessage;
import view.TravauxAjout;

public class TravauxAjoutController extends TemplateAjoutController {

    private TravauxAjout view = new TravauxAjout();
    private FactureDAO model = DAOFactory.createFactureDAO();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private TravauxDAO modelTravaux = DAOFactory.createTravauxDAO();
    private boolean errorRaise;
    private Date sqlDate; // Class variable to store the SQL date

    public TravauxAjoutController() {
        super();
        view.setTitleHeader("Travaux");
        view.setCbFacture(model.get_numFacture());
        view.setCbAdress(modelBien.get_AllAdresses());
        addAutoResultListener(new JTextField[]{view.getFieldMontant(), view.getFieldMontantNonDeductible(), view.getFielReduction()});
        pressedValider();
        pressedAnnuler();
        pressedAjouterTravauxButton();
        addEventHandlers();
        logoLabel();

        view.setVisible(true);
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
                        String montText = view.getFieldMontant().getText();
                        String montNDText = view.getFieldMontantNonDeductible().getText();
                        String reducText = view.getFielReduction().getText();

                        if (montText.isEmpty() || montNDText.isEmpty() || reducText.isEmpty()) {
                            view.getResultField().setText("");
                            return;
                        }

                        double mont = Double.parseDouble(montText);
                        double montND = Double.parseDouble(montNDText);
                        double reduc = Double.parseDouble(reducText);
                        result = (mont - montND) * (1 - reduc / 100);
                        DecimalFormat df = new DecimalFormat("#.##");
                        view.getResultField().setText(df.format(result));
                    } catch (NumberFormatException e) {
                        view.getResultField().setText("");
                    } catch (Exception e) {
                        ExceptionStorageHandler.LogException(e, DatabaseConnection.getInstance());
                    }
                }
            });
        }
    }

    private void pressedValider() {
        view.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkMontant();
                checkMontantNonDeductible();
                checkReduction();
                checkDate();
                if (!errorRaise) {
                    createTravaux();
                    new TravauxController();
                    view.dispose();
                }
        });
    }

    private void checkMontantNonDeductible() {
        if (errorRaise) { return; }
        String input = view.getFieldMontantNonDeductible().getText();
        try {
            double montantNonDeductible = Double.parseDouble(input.replace(',', '.'));
            if (montantNonDeductible < 0) {
                ErrorMessage.errorDialog("Le champs montant non déductible doit être supérieur à 0");
                errorRaise = true;
            }
            double montant = Double.parseDouble(view.getFieldMontant().getText().replace(',', '.'));
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
        String input = view.getFieldMontant().getText();
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
        String input = view.getFielReduction().getText();
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
        String input = view.getDateField().getText();
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
            String nature = view.getNatureField().getText();
            BigDecimal reduction = new BigDecimal(view.getFielReduction().getText().replace(',', '.'));
            BigDecimal montant = new BigDecimal(view.getFieldMontant().getText().replace(',', '.'));
            BigDecimal montantNonDeductible = new BigDecimal(view.getFieldMontantNonDeductible().getText().replace(',', '.'));

            Travaux travaux = new Travaux(dateTravaux, nature, null, reduction, montant, montantNonDeductible, null);
            
            modelTravaux.insert(travaux);
            modelTravaux.insertFK(travaux.getNumero_facture(), view.getCbFacture().getSelectedItem().toString());
            
            ErrorMessage.confirmationDialog(nature);
            
        } catch (Exception e) {
            ErrorMessage.errorDialog("Erreur lors de la création de l'objet Travaux: " + e.getMessage());
        }
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(e ->{
                new TravauxController();
                view.dispose();
        });
    }
    
    
    private void pressedAjouterTravauxButton() {
    	view.getAjouterFactureButton().addActionListener(e -> {
				new FactureAjoutController();
				view.dispose();		
		});
	}
    
	private void logoLabel() {
		view.getLogoLabel().addActionListener(e -> {
				new HomeController();
				view.dispose();
		});
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
}