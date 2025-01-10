package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AvancerDAO;
import dao.DAOFactory;
import dao.TravauxDAO;
import utilities.ErrorMessage;
import view.Travaux;

@SuppressWarnings("serial")
public class TravauxController extends TemplateTableController{
	
	private Travaux view = new Travaux();
	private AvancerDAO modelAvancer = DAOFactory.createAvancerDAO();
	private TravauxDAO model = DAOFactory.createTravauxDAO();
	private List<List<String>> listData;
	
	public TravauxController() {
		super();
		view.setTitleHeader("Travaux");
        fillTable(); 
        view.setTableModel(modelTable, 1);
        actionDeleteButton();
        openAjoutTravauxPage();
        addEventHandlers();
        logoLabel();
        
        view.setVisible(true);
	}

	private void logoLabel() {
		view.getLogoLabel().addActionListener(e-> {
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

	private void actionDeleteButton() {
		view.getDeleteButton().addActionListener(e -> {
				int selectedRow = view.getTable().getSelectedRow();
				int idTravaux = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + idTravaux);
				if (response == JOptionPane.YES_OPTION) {
					modelAvancer.deleteById(idTravaux);
				    model.deleteById(idTravaux);
				    modelTable.setRowCount(0);
				    fillTable();
				    view.setTableModel(modelTable, 1);
				   	}
		});
		
	}

	private void openAjoutTravauxPage() {
		view.getButtonAjoutTravaux().addActionListener(e-> {
				new TravauxAjoutController();
				view.dispose();
		});
	}
	
	@Override
	void fillTable() {
		 modelTable = new DefaultTableModel(new String[]{"Référence facture", "Adresse", "Logement", "Montant", "Montant non déductible", "Réduction", "Date", "Nature", "SUPPRIMER"}, 0) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	            	 return column == 8;
	            }
	        };
		
	        listData = model.procPageTravaux();
	        
	        for (int i = 0; i < listData.size(); i++) {
	        	List<String> rowResult = listData.get(i);
	        	
	        	String refFacture = rowResult.get(0);
	        	String adresse = rowResult.get(1) + "\n" + rowResult.get(3) + " | " + rowResult.get(2);
	        	String logement = rowResult.get(4);
	        	String montant = rowResult.get(5);
	        	String montantNonDeductible = rowResult.get(6);
	        	String reduction = rowResult.get(7);
	        	String date = TemplateTableController.transformDate(rowResult.get(8));
	        	String nature = rowResult.get(9);
	        	
	        	Object[] row = {refFacture, adresse, logement, montant, montantNonDeductible, reduction, date, nature, "Delete"};
	        	modelTable.addRow(row);
	        	
	        }
	}

	@Override
	void updateFooter() {
		// TODO Auto-generated method stub
		
	}
	
}
