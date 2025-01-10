package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOFactory;
import dao.FactureDAO;
import utilities.ErrorMessage;
import view.Facture;

public class FactureController extends TemplateTableController{
	
	private Facture view = new Facture();
	private FactureDAO model = DAOFactory.createFactureDAO();
	private List<List<String>> listData;
	
			
	public FactureController() {
		super();
		view.setTitleHeader("Facture");
		fillTable();
		view.setTableModel(modelTable);
		deleteActionButton();
		openAjoutFactuePage();
		logoLabel();
		addEventHandlers();
		view.setVisible(true);
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
	
	@SuppressWarnings("serial")
	@Override
	void fillTable() { 
		modelTable = new DefaultTableModel(new String[]{"Référence facture", "Adresse", "Type", "Date", "Montant", "Moyen de paiment", "SUPPRIMER"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
        	 return column == 6;
        }
    };
    
	    listData = model.procGet_factures();
	    for (int i = 0; i < listData.size(); i++) {
	        List<String> rowResult = listData.get(i);
	        
	        String refFacture = rowResult.get(0); // Reference facture
	        String adresse = rowResult.get(1);   // Address
	        String typeFacture = rowResult.get(2); // Bill type (Electricity, Water, etc.)
	        String date = TemplateTableController.transformDate(rowResult.get(3)); // Date
	        String montant = rowResult.get(4);   // Amount
	        String modePaiement = rowResult.get(5); // Payment mode
	        
	        // Construct the row for the table
	        Object[] row = {refFacture, adresse, typeFacture, date, montant, modePaiement, "Delete"};
	        modelTable.addRow(row);
	    }
	}
	
	private void openAjoutFactuePage() {
		view.getAjoutFacture().addActionListener(e -> {
				new FactureAjoutController();
				view.dispose();
		});
	}
	
	
	private void deleteActionButton() {
		view.getDeleteButton().addActionListener(e -> {
				int selectedRow = view.getTable().getSelectedRow();
				String refFacture = (String) view.getTable().getValueAt(selectedRow, 0);
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + refFacture);
				if (response == JOptionPane.YES_OPTION) {
				    model.deleteByRef(refFacture);
				    modelTable.setRowCount(0);
				    fillTable();
				    view.setTableModel(modelTable, 1);
				} else if (response == JOptionPane.NO_OPTION) {
				    return;
				}
		});
	}

	@Override
	void updateFooter() {
		// TODO Auto-generated method stub
		
	}
}
