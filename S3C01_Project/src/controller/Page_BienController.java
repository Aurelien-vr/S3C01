package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.DAOFactory;
import view.ErrorMessage;
import view.Page_Bien;

public class Page_BienController extends TableSkeletonController{
	
	private Page_Bien view = new Page_Bien();
	private BienDAO modelBien = DAOFactory.createBienDAO();
	private List<List<String>> listData;
	
	public Page_BienController(){
		super();
		view.setTitleHeader("Bien");
		fillTable(); 
		view.setTableModel(modelTable, 0);
		actionDeleteButton();
		actionEditButton();
		openAjoutBienPage();
		logoLabel();
		addEventHandlers();
		view.setVisible(true);
	}
	
	
	
	private void logoLabel() {
	    view.getLogoLabel().addActionListener(e -> {
	        new Page_PrincipaleController();
	        view.dispose();
	    });
	}
	
	
	@Override
	void fillTable() {
		modelTable = new DefaultTableModel(new String[]{"Adresse", "Superfice", "Nombre de pièces", "Meublé", "Accesoire privé", "Accesoire commun", "Garage", "Contrat de location", "EDIT", "SUPPRIMER"}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
            	int columnCount = getColumnCount();
                return column == columnCount - 1 || column == columnCount - 2;
            }};
        
        listData = modelBien.procPageBien();
        
        for (int i = 0; i < listData.size(); i++) {
        	List<String> rowResult = listData.get(i);
        	
        	String adresse = rowResult.get(2) + "\n" + rowResult.get(3) + " | " + rowResult.get(4);
        	String superficie = rowResult.get(5);
        	String nbPiece = rowResult.get(6);
        	String meuble = Integer.parseInt(rowResult.get(7)) == 1 ? "Oui" : "Non";
        	String accesoirePrive = rowResult.get(8);
        	String accesoireCommun = rowResult.get(9);
        	String garage = Integer.parseInt(rowResult.get(11)) == 1 ? "Oui" : "Non";
        	String contLoc= "Unknown".equals(rowResult.get(10)) ? "Non" : "Oui";
        	
        	Object[] row = {adresse, superficie, nbPiece, meuble, accesoirePrive, accesoireCommun, garage, contLoc,"Edit contrat location" , "Delete"};
        	modelTable.addRow(row);
        }
	
	}
	
	private void actionDeleteButton() {
		view.getDeleteButton().addActionListener(e-> {
				int selectedRow = view.getTable().getSelectedRow();
				int idTravaux = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + idTravaux);
				if (response == JOptionPane.YES_OPTION) {
				    modelBien.deleteById(idTravaux);
				    modelTable.setRowCount(0);
				    fillTable();
				    view.setTableModel(modelTable, 1);
				}
		});
	}
	
	private void actionEditButton() {
		view.getEditIdContratBien().addActionListener(e-> {
				//System.out.println("Edit pressed for row: " +  view.getTable().getSelectedRow());
				
				int idCl;
				String value = listData.get(view.getTable().getSelectedRow()).get(10);
				if ("Unknown".equals(value)) {
				    idCl = -1;
				} else {
				    idCl = Integer.parseInt(value);
				}
				view.fillEditClDialog(idCl);
				System.out.println(idCl);
		});
	}
	
	private void openAjoutBienPage() {
		view.getAjoutBienButton().addActionListener(e -> {
				new Page_AjoutBienController();
				view.dispose();
		});
	}
	
	 private void addEventHandlers() {

	        view.getBtnLocataire().addActionListener(e -> {
	                System.out.println("Locataire clicked");
	        });
	        
	        view.getBtnContratLocation().addActionListener(e -> {
	        	new Page_ContratLocationController();
	        	view.dispose();
	        });

	        view.getBtnDocument().addActionListener(e ->{
	                System.out.println("Doc cliqué");
	        });
	    }



	@Override
	void updateFooter() {
		return;
	}

	
	private void dialogEditCl(int idCl, boolean haveCl) {
		
	}
	
}
