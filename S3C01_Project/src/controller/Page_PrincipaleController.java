package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.DAOFactory;

public class Page_PrincipaleController extends TableSkeletonController{
	
	private BienDAO model = DAOFactory.createBienDAO();
    private List<List<String>> listData = new ArrayList<>();
	
	public Page_PrincipaleController() {
		super();
        fillTable(); 
        view.setTableModel(modelTable);
        view.setVisible(true);
	}
	
	 @SuppressWarnings("serial")
	 @Override
	 void fillTable() {
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
}
