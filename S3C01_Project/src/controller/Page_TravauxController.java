package controller;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.DAOFactory;
import dao.TravauxDAO;
import view.Page_Travaux;

public class Page_TravauxController extends TableSkeletonController{
	
	private Page_Travaux view = new Page_Travaux();
	private TravauxDAO model = DAOFactory.createTravauxDAO();
	private List<List<String>> listData;
	
	public Page_TravauxController() {
		super();
        fillTable(); 
        view.setTableModel(modelTable, 1);
        view.setVisible(true);
       
	}
	
	@SuppressWarnings("serial")
	@Override
	void fillTable() {
		 modelTable = new DefaultTableModel(new String[]{"Référence facture", "Adresse", "Logement", "Montant", "Montant non déductible", "Réduction", "Date", "Nature"}, 0) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
		
	        listData = model.procPageTravaux();
	        System.out.println(listData);
	        
	        for (int i = 0; i < listData.size(); i++) {
	        	List<String> rowResult = listData.get(i);
	        	
	        	String refFacture = rowResult.get(0);
	        	String adresse = rowResult.get(1) + "\n" + rowResult.get(3) + " | " + rowResult.get(2);
	        	String logement = rowResult.get(4);
	        	String montant = rowResult.get(5);
	        	String montantNonDeductible = rowResult.get(6);
	        	String reduction = rowResult.get(7);
	        	String date = TableSkeletonController.transformDate(rowResult.get(8));
	        	String nature = rowResult.get(9);
	        	
	        	Object[] row = {refFacture, adresse, logement, montant, montantNonDeductible, reduction, date, nature};
	        	modelTable.addRow(row);
	        	
	        }
	}
	
	
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new Page_TravauxController());
	    }

}
