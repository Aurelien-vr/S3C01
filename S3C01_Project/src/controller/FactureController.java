package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOFactory;
import dao.FactureDAO;
import utilities.ErrorMessage;
import view.FactureView;

public class FactureController extends TemplateTableController{
	
	private FactureView viewFacture = new FactureView();
	private FactureDAO model = DAOFactory.createFactureDAO();
	
	
			
	public FactureController() {
		super();
		viewFacture.setTitleHeader("Facture");
		fillTable();
		viewFacture.setTableModel(modelTable);
		deleteActionButton();
		openAjoutFactuePage();
		logoLabel();
		addEventHandlers();
		viewFacture.setVisible(true);
	}
	
	private void logoLabel() {
		viewFacture.getLogoLabel().addActionListener(e -> {
				new HomeController();
				viewFacture.dispose();
		});
	}
	
	 private void addEventHandlers() {
	        viewFacture.getBtnBienLouable().addActionListener(e -> {
	        	new BienController();
	        	viewFacture.dispose();
	        });

	        viewFacture.getBtnLocataire().addActionListener(e -> {
	        	new LocataireController();
	        	viewFacture.dispose();
	        });
	        
	        viewFacture.getBtnContratLocation().addActionListener(e -> {
	        	new ContratLocationController();
	        	viewFacture.dispose();
	        });
	        
	        viewFacture.getItemAssurance().addActionListener(e -> {
	        	new AssuranceController();
	        	viewFacture.dispose();
	        });
	        
	        viewFacture.getItemFacture().addActionListener(e -> {
	        	new FactureController();
	        	viewFacture.dispose();
	        });
	        
	        viewFacture.getItemTravaux().addActionListener(e -> {
	        	new TravauxController();
	        	viewFacture.dispose();
	        });
	        
	        viewFacture.getItemCharge().addActionListener(e -> {
	        	new ChargeController();
	        	viewFacture.dispose();
	        });
	    }
	
	@SuppressWarnings("serial")
	@Override
	void fillTable() {
  List<List<String>> listData; 
		modelTable = new DefaultTableModel(new String[]{"Référence facture", "Adresse", "Type", "Date", "Montant", "Moyen de paiment", "SUPPRIMER"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
        	 return column == 6;
        }
    };
    
	    listData = model.procGetFactures();
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
		viewFacture.getAjoutFacture().addActionListener(e -> {
				new FactureAjoutController();
				viewFacture.dispose();
		});
	}
	
	
	private void deleteActionButton() {
		viewFacture.getDeleteButton().addActionListener(e -> {
				int selectedRow = viewFacture.getTable().getSelectedRow();
				String refFacture = (String) viewFacture.getTable().getValueAt(selectedRow, 0);
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + refFacture);
				if (response == JOptionPane.YES_OPTION) {
				    model.deleteByRef(refFacture);
				    modelTable.setRowCount(0);
				    fillTable();
				    viewFacture.setTableModel(modelTable, 1);
				} else if (response == JOptionPane.NO_OPTION) {
				    return;
				}
		});
	}

	@Override
	void updateFooter() {
		// No specific footer on this page		
	}
}
