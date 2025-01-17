package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AvancerDAO;
import dao.DAOFactory;
import dao.TravauxDAO;
import utilities.ErrorMessage;
import view.TravauxView;

@SuppressWarnings("serial")
public class TravauxController extends TemplateTableController{
	
	private TravauxView viewTravaux = new TravauxView();
	private AvancerDAO modelAvancer = DAOFactory.createAvancerDAO();
	private TravauxDAO model = DAOFactory.createTravauxDAO();
	private List<List<String>> listData;
	
	public TravauxController() {
		super();
		viewTravaux.setTitleHeader("Travaux");
        fillTable(); 
        viewTravaux.setTableModel(modelTable, 1);
        actionDeleteButton();
        openAjoutTravauxPage();
        addEventHandlers();
        logoLabel();
        
        viewTravaux.setVisible(true);
	}

	private void logoLabel() {
		viewTravaux.getLogoLabel().addActionListener(e-> {
				new HomeController();
				viewTravaux.dispose();
		});
	}
	
	 private void addEventHandlers() {
	        viewTravaux.getBtnBienLouable().addActionListener(e -> {
	        	new BienController();
	        	viewTravaux.dispose();
	        });

	        viewTravaux.getBtnLocataire().addActionListener(e -> {
	        	new LocataireController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getBtnContratLocation().addActionListener(e -> {
	        	new ContratLocationController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemAssurance().addActionListener(e -> {
	        	new AssuranceController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemFacture().addActionListener(e -> {
	        	new FactureController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemTravaux().addActionListener(e -> {
	        	new TravauxController();
	        	viewTravaux.dispose();
	        });
	        
	        viewTravaux.getItemCharge().addActionListener(e -> {
	        	new ChargeController();
	        	viewTravaux.dispose();
	        });
	    }

	private void actionDeleteButton() {
		viewTravaux.getDeleteButton().addActionListener(e -> {
				int selectedRow = viewTravaux.getTable().getSelectedRow();
				int idTravaux = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + idTravaux);
				if (response == JOptionPane.YES_OPTION) {
					modelAvancer.deleteById(idTravaux);
				    model.deleteById(idTravaux);
				    modelTable.setRowCount(0);
				    fillTable();
				    viewTravaux.setTableModel(modelTable, 1);
				   	}
		});
		
	}

	private void openAjoutTravauxPage() {
		viewTravaux.getButtonAjoutTravaux().addActionListener(e-> {
				new TravauxAjoutController();
				viewTravaux.dispose();
		});
	}
	
	@Override
	void fillTable() {
		 modelTable = new DefaultTableModel(new String[]{"Référence facture", "Adresse", "Logement", "Montant", "Montant non déductible", "Réduction", "Montant à déclarer", "Date", "Nature", "SUPPRIMER"}, 0) {
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
	        	
	        	float montantInt = Float.parseFloat(montant);
	        	float montantNonDeductibleInt = Float.parseFloat(montantNonDeductible);
	        	float reductionInt = Float.parseFloat(reduction);
	        	float montantADeclarerInt = (montantInt - montantNonDeductibleInt) * (1 - reductionInt / 100);
	        	String montantADeclarer = String.valueOf(montantADeclarerInt);
	        	
	        	String date = TemplateTableController.transformDate(rowResult.get(8));
	        	String nature = rowResult.get(9);
	        	
	        	Object[] row = {refFacture, adresse, logement, montant, montantNonDeductible, reduction, montantADeclarer ,date, nature, "Delete"};
	        	modelTable.addRow(row);
	        	
	        }
	}

	@Override
	void updateFooter() {
		//footer empty because no specific information to display
	}
	
}
