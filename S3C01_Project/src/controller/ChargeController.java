package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.ChargeDAO;
import dao.DAOFactory;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import view.ChargeView;

public class ChargeController extends TemplateTableController {
    
    private ChargeView viewCharge = new ChargeView();
    private ChargeDAO modelCharge = DAOFactory.createChargeDAO();
    private List<List<String>> listData;
    
    public ChargeController(){
        super();
        viewCharge.setTitleHeader("Charges de location");
        updateFooter();
        actionDeleteButton();
        addEventHandlers();
        buttonEditAddCharge();
        logoLabel();
        fillTable();
        actionToggleButton();
        
        viewCharge.setTableModel(modelTable);
        viewCharge.setVisible(true);
    }
    
    private void logoLabel() {
        viewCharge.getLogoLabel().addActionListener(e -> {
            new HomeController();
            viewCharge.dispose();
        });
    }
    
    @Override
    void fillTable() {
        modelTable = new DefaultTableModel(new String[]{"Adresse", "Date", "Total Electricité", "Total Eau", "Total Ordures Ménagères", "Total Entretien", "SUPPRIMER"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                int columnCount = getColumnCount();
                return column == columnCount - 1;
            }};
        
        if(viewCharge.getToggleButton() != null && viewCharge.getToggleButton().isSelected()) {
            listData = modelCharge.procGetChargesActifs();
        } else {
            listData = modelCharge.procGetCharges();
        }
        
        for (List<String> rowResult : listData) {
            String adresse = rowResult.get(0);
            String date = rowResult.get(1);
            String totalElectricite = rowResult.get(2);
            String totalEau = rowResult.get(3);
            String totalOrduresMenageres = rowResult.get(4);
            String totalEntretien = rowResult.get(5);

            Object[] row = {adresse, transformDate(date), totalElectricite, totalEau, totalOrduresMenageres, totalEntretien, "Delete"};
            modelTable.addRow(row);
        }
    }
    
    private void buttonEditAddCharge() {
    	viewCharge.getAjoutChargeButton().addActionListener(e->{
    		String rep = ErrorMessage.optionDialog();
    		if("Create".equals(rep)) {
    			new ChargeAjoutController(false);
    		}else if("Edit".equals(rep)){
    			new ChargeAjoutController(true);
    		}
    		viewCharge.dispose();
    	});
    }
    
    private void actionDeleteButton() {
        viewCharge.getDeleteButton().addActionListener(e -> {
            int selectedRow = viewCharge.getTable().getSelectedRow();
            int idCharge = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression de la charge: " + idCharge);
            if (response == JOptionPane.YES_OPTION) {
                modelCharge.deleteById(idCharge);
                modelTable.setRowCount(0);
                fillTable();
                viewCharge.setTableModel(modelTable, 1);
            }
        });
    }
    
    private void addEventHandlers() {
        viewCharge.getBtnBienLouable().addActionListener(e -> {
            new BienController();
            viewCharge.dispose();
        });

        viewCharge.getBtnLocataire().addActionListener(e -> {
            new LocataireController();
            viewCharge.dispose();
        });
        
        viewCharge.getBtnContratLocation().addActionListener(e -> {
            new ContratLocationController();
            viewCharge.dispose();
        });
        
        viewCharge.getItemAssurance().addActionListener(e -> {
            new AssuranceController();
            viewCharge.dispose();
        });
        
        viewCharge.getItemFacture().addActionListener(e -> {
            new FactureController();
            viewCharge.dispose();
        });
        
        viewCharge.getItemTravaux().addActionListener(e -> {
            new TravauxController();
            viewCharge.dispose();
        });
        
        viewCharge.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewCharge.dispose();
        });
    }
    
    private void actionToggleButton() {
        viewCharge.getToggleButton().addActionListener(e -> {
            fillTable();
            viewCharge.setTableModel(modelTable, 3);
        });
        
    }
    
    @Override
    void updateFooter() {
        if (viewCharge.getToggleButton() != null) {
            viewCharge.getFooterPanel().add(viewCharge.getToggleButton());
            FontComponent.setFontForAllComponents(viewCharge.getFooterPanel(), ScallingDimension.scaleValue(18));
        }
    }
}