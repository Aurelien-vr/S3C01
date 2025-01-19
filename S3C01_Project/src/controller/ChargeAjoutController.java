package controller;

import view.ChargeAjoutView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.BienDAO;
import dao.ChargeDAO;
import dao.DAOFactory;
import dao.FactureDAO;
import dao.entities.Charge;
import dao.entities.Facture;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class ChargeAjoutController extends TemplateAjoutController {

    private final boolean edit;
    private ChargeAjoutView viewAjoutCharge;
    private ChargeDAO modelCharge = DAOFactory.createChargeDAO();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private FactureDAO modelfacture = DAOFactory.createFactureDAO();
    protected DefaultTableModel modelTable;

    private List<List<String>> cbAdresses;

    List<Facture> factures = new ArrayList<>();

    public ChargeAjoutController(boolean edit) {
        this.edit = edit;
        addEventHandlers();
        viewAjoutCharge = new ChargeAjoutView(edit);
        initialize();
    }

    private void initialize() {
        modelTable = new DefaultTableModel(new String[]{"Reference", "Type", "Date","Montant"}, 0);
        viewAjoutCharge.getFactureTable().setModel(modelTable);

        poupulateCb();
        
        addFacture();
        viewAjoutCharge.getAnnulerButton().addActionListener(e -> cancel());
        viewAjoutCharge.getValiderButton().addActionListener(e -> validate());
    }

    private void addFacture() {
        viewAjoutCharge.getAddFactureButton().addActionListener(e -> {
            String ref = (String) viewAjoutCharge.getComboFacture().getSelectedItem();
            Facture facture = modelfacture.findOneRef(ref);
            modelTable.addRow(new Object[]{facture.getReferenceFacture(), facture.getTypeFacture(), facture.getDateFacture(), facture.getMontantFacture()});
            factures.add(facture);
        });
    }
    private void poupulateCb() {
        cbAdresses = modelBien.procGetBienWithCl();
        viewAjoutCharge.getComboAdresse().removeAllItems();
        for (List<String> row : cbAdresses) {
            viewAjoutCharge.getComboAdresse().addItem(row.get(0));
        }
        
        updateCbYear();

        viewAjoutCharge.getComboAdresse().addActionListener(e -> {
            viewAjoutCharge.getComboDate().removeAllItems();
            updateCbYear();
            updateCbFacture();
        });
        
        updateCbFacture();

        viewAjoutCharge.getComboDate().addActionListener(e -> {
            viewAjoutCharge.getComboFacture().removeAllItems();
            updateCbFacture();
        });
    }

    private void updateCbFacture() {
        List<List<String>> cbFactures;
        if (viewAjoutCharge.getComboDate().getSelectedItem() != null) {
            cbFactures = modelCharge.procFactureFromBien(Integer.parseInt(cbAdresses.get(viewAjoutCharge.getComboAdresse().getSelectedIndex()).get(1)));
            for (List<String> row : cbFactures) {
                viewAjoutCharge.getComboFacture().addItem(row.get(0));
            } 
        }
        if(edit) {
        	addFactureToTable();
        }
    }

    private void updateCbYear() {
        List<List<String>> cbYearListData;
        if (viewAjoutCharge.getComboAdresse().getSelectedIndex() != -1) {
            int selectedIndex = viewAjoutCharge.getComboAdresse().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < cbAdresses.size()) {
                cbYearListData = modelCharge.procYearFromFacture(Integer.parseInt(cbAdresses.get(selectedIndex).get(1)));
                viewAjoutCharge.getComboDate().removeAllItems();
                for (List<String> row : cbYearListData) {
                    String firstDate = row.get(0);
                    viewAjoutCharge.getComboDate().addItem(firstDate.split("-")[0]);
                }
            }
            if(edit) {            	
            	addFactureToTable();
            }
        }
    }

    private void addFactureToTable() {
     List<List<String>> dataTable;
    	modelTable.setRowCount(0);
    	factures = new ArrayList<>();
        if (viewAjoutCharge.getComboDate().getSelectedItem() != null) {
            dataTable = modelCharge.procFactureFromCharge(
                    Integer.parseInt((String) viewAjoutCharge.getComboDate().getSelectedItem()),
                    Integer.parseInt(cbAdresses.get(viewAjoutCharge.getComboAdresse().getSelectedIndex()).get(1)));
            
            for (List<String> rowResult : dataTable) {
                String reference = rowResult.get(0);
                String type = rowResult.get(1);
                String date = rowResult.get(2);
                String total = rowResult.get(3);

                Object[] row = {reference, type, date, total};
                modelTable.addRow(row);
            }
        }
    }

    private void cancel() {
        new ChargeController();
        viewAjoutCharge.dispose();
    }

    private void validate() {
    	int idCl = modelCharge.progGetIdClFromIdBien(Integer.parseInt(cbAdresses.get(viewAjoutCharge.getComboAdresse().getSelectedIndex()).get(1)));
    	
        if(edit) {
        	int idCharge = modelCharge.procIdCharge(idCl,Integer.parseInt((String)viewAjoutCharge.getComboDate().getSelectedItem()));
        	for(Facture facture: factures) {
        		modelfacture.insertFKCharges(idCharge, facture.getReferenceFacture());
        	}
        }else{
        	java.sql.Date sqlDate = fromFieldToSqlDate();
        	Charge charge = new Charge(sqlDate);
        	modelCharge.insert(charge);
        	modelCharge.insertFK(idCl, charge.getIdCharge());
        	
        	for(Facture facture: factures) {
        		modelfacture.insertFKCharges(charge.getIdCharge(), facture.getReferenceFacture());
        	}
        }
        new ChargeController();
        viewAjoutCharge.dispose();
    }

	private java.sql.Date fromFieldToSqlDate() {
		String date = viewAjoutCharge.getFieldDateCharge().getText();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date utilDate = null;
		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			ExceptionStorageHandler.logException(e, DatabaseConnection.getInstance());
		}
		 return new java.sql.Date(utilDate.getTime());
	}
	
	private void addEventHandlers() {
        viewAjoutCharge.getBtnBienLouable().addActionListener(e -> {
            new BienController();
            viewAjoutCharge.dispose();
        });

        viewAjoutCharge.getBtnLocataire().addActionListener(e -> {
            new LocataireController();
            viewAjoutCharge.dispose();
        });
        
        viewAjoutCharge.getBtnContratLocation().addActionListener(e -> {
            new ContratLocationController();
            viewAjoutCharge.dispose();
        });
        
        viewAjoutCharge.getItemAssurance().addActionListener(e -> {
            new AssuranceController();
            viewAjoutCharge.dispose();
        });
        
        viewAjoutCharge.getItemFacture().addActionListener(e -> {
            new FactureController();
            viewAjoutCharge.dispose();
        });
        
        viewAjoutCharge.getItemTravaux().addActionListener(e -> {
            new TravauxController();
            viewAjoutCharge.dispose();
        });
        
        viewAjoutCharge.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewAjoutCharge.dispose();
        });
    }
}