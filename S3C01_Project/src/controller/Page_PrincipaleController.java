package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.DAOFactory;
import view.Page_AjoutTravaux;
import view.Page_Principale;

public class Page_PrincipaleController extends TableSkeletonController {
    
    private Page_Principale view = new Page_Principale();
    private BienDAO model = DAOFactory.createBienDAO();
    
    
    public Page_PrincipaleController() {
        super();
        view.setTitleHeader("Page principale");
        fillTable();
        addEventHandlers();
        view.setTableModel(modelTable, 0);
        updateFooter();
        view.setVisible(true);
    }
    
    @SuppressWarnings("serial")
    @Override
    void fillTable() {
        List<List<String>> listData;
        modelTable = new DefaultTableModel(new String[]{"Location", "Date", "Date de versement", "Mois de retard", "Loyer en retard"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        listData = model.BienStatus();

        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);

            String location = rowResult.get(0) + "\n" + rowResult.get(1) + " | " + rowResult.get(2) + "\n" + rowResult.get(3) + " " + rowResult.get(4);
            String date = "Du " + transformDate(rowResult.get(5)) + " au " + transformDate(rowResult.get(6));
            String truc = transformDate(rowResult.get(7));
            String moisRetard = rowResult.get(8);
            String loyerRetard = rowResult.get(9);

            Object[] row = {location, date, truc, moisRetard, loyerRetard};
            modelTable.addRow(row);
        }
    }
     
    private void addEventHandlers() {
        view.getBtnBienLouable().addActionListener(e -> {
        	new Page_BienController();
        	view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
                new Page_LocataireController();
                view.dispose();
        });
        
        view.getBtnContratLocation().addActionListener(e -> {
        	new Page_ContratLocationController();
        	view.dispose();
        });

        view.getBtnDocument().addActionListener(e ->{
                new Page_AssuranceController();
                view.dispose();
        });
    }
    
    @Override 
    void updateFooter() {
        int rowCount = modelTable.getRowCount();
        double totalLoyerRetard = 0.0;
        for (int i = 0; i < rowCount; i++) {
            String loyerRetard = (String) modelTable.getValueAt(i, 4);
            totalLoyerRetard += Double.parseDouble(loyerRetard);
        }
        
        String footerText = "Total Loyer en Retard: " + String.format("%.2f", totalLoyerRetard);
        view.setFooterLabText(footerText);
        view.updateFooter(view.getFooterlab());
        Page_AjoutTravaux.setFontForAllComponents(view.getFooterPanel(), (int) (ScallingDimension.scaleValue(14) * 1.3));
    }
}