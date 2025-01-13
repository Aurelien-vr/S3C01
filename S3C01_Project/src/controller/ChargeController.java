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
    
    private ChargeView view = new ChargeView();
    private ChargeDAO modelCharge = DAOFactory.createChargeDAO();
    private List<List<String>> listData;
    
    public ChargeController(){
        super();
        view.setTitleHeader("Charges de location");
        updateFooter();
        openAjoutChargePage();
        actionDeleteButton();
        addEventHandlers();
        logoLabel();
        fillTable();
        actionToggleButton();
        
        view.setTableModel(modelTable, 3);
        view.setVisible(true);
    }
    
    private void logoLabel() {
        view.getLogoLabel().addActionListener(e -> {
            new HomeController();
            view.dispose();
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
        
        if(view.getToggleButton() != null && view.getToggleButton().isSelected()) {
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
    
    private void actionDeleteButton() {
        view.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            int idCharge = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression de la charge: " + idCharge);
            if (response == JOptionPane.YES_OPTION) {
                modelCharge.deleteById(idCharge);
                modelTable.setRowCount(0);
                fillTable();
                view.setTableModel(modelTable, 1);
            }
        });
    }
    
    private void openAjoutChargePage() {
        view.getAjoutChargeButton().addActionListener(e -> {
            //new ChargeAjoutController();
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
        
        view.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	view.dispose();
        });
    }
    
    private void actionToggleButton() {
        view.getToggleButton().addActionListener(e -> {
            fillTable();
            view.setTableModel(modelTable, 3);
        });
        
    }
    
    @Override
    void updateFooter() {
        if (view.getToggleButton() != null) {
            view.getFooterPanel().add(view.getToggleButton());
            FontComponent.setFontForAllComponents(view.getFooterPanel(), ScallingDimension.scaleValue(18));
        }
    }
}