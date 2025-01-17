package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.ContratLocationDAO;
import dao.DAOFactory;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import view.ContratLocationView;

public class ContratLocationController extends TemplateTableController {
    
    private ContratLocationView viewContratLocation = new ContratLocationView();
    private ContratLocationDAO modelContratLocation = DAOFactory.createContratLocationDAO();
    private List<List<String>> listData;
    
    public ContratLocationController(){
        super();
        viewContratLocation.setTitleHeader("Contrat de location");
        updateFooter();
        openAjoutContratLocationPage();
        actionDeleteButton();
        addEventHandlers();
        logoLabel();
        fillTable();
        actionToggleButton();
        
        viewContratLocation.setTableModel(modelTable, 3);
        viewContratLocation.setVisible(true);
    }
    
    private void logoLabel() {
	    viewContratLocation.getLogoLabel().addActionListener(e -> {
	        new HomeController();
	        viewContratLocation.dispose();
	    });
	}
    
    @Override
    void fillTable() {
        modelTable = new DefaultTableModel(new String[]{"Montant", "Date debut", "Date fin", "Modalité", "Adresse bien", "Nom locataire", "Colocation", "Etat des lieux", "Solde de tout compte", "Regularisation des charges", "SUPPRIMER"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                int columnCount = getColumnCount();
                return column == columnCount - 1;
            }};
        
            
        if(viewContratLocation.getToggleButton() != null && viewContratLocation.getToggleButton().isSelected()) {
        	listData = modelContratLocation.procPageContratLocationActif();
        }else {
        	listData = modelContratLocation.procPageContratLocation();
        }
        
        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);

            String montant = rowResult.get(0);
            String dateDebut = rowResult.get(1);
            String dateFin = rowResult.get(2);
            String modalite = rowResult.get(3);
            String adresseBien = rowResult.get(4);
            String nomLocataire = rowResult.get(5);

            String oui = "Oui";
            String non = "Non";

            // Use Double.parseDouble for decimal values
            String colocation = Double.parseDouble(rowResult.get(6)) == 1 ? oui : non;
            String etatDesLieux = Double.parseDouble(rowResult.get(7)) == 1 ? oui : non;
            String soldeToutCompte = Double.parseDouble(rowResult.get(8)) == 1 ? oui : non;
            String regularisationCharges = Double.parseDouble(rowResult.get(9)) == 1 ? oui : non;

            Object[] row = {montant, transformDate(dateDebut), transformDate(dateFin), modalite, adresseBien, nomLocataire, colocation, etatDesLieux, soldeToutCompte, regularisationCharges, "Delete"};
            modelTable.addRow(row);
        }
    }
    
    private void actionDeleteButton() {
        viewContratLocation.getDeleteButton().addActionListener(e -> {
            int selectedRow = viewContratLocation.getTable().getSelectedRow();
            int idContratLocation = Integer.parseInt(listData.get(selectedRow).get(10));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression du contrat de location: " + idContratLocation);
            if (response == JOptionPane.YES_OPTION) {
                modelContratLocation.procCascadeDelete(idContratLocation);
                modelTable.setRowCount(0);
                fillTable();
                viewContratLocation.setTableModel(modelTable, 1);
            }
        });
    }
    
    private void openAjoutContratLocationPage() {
        viewContratLocation.getAjoutContratLocationButton().addActionListener(e -> {
        	new ContratLocationAjoutController();
        	viewContratLocation.dispose();});
    }
    
    private void addEventHandlers() {
        viewContratLocation.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewContratLocation.dispose();
        });

        viewContratLocation.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewContratLocation.dispose();
        });
        
        viewContratLocation.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewContratLocation.dispose();
        });
        
        viewContratLocation.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewContratLocation.dispose();
        });
    }
    
    
    private void actionToggleButton() {
    	viewContratLocation.getToggleButton().addActionListener(e -> {
    		fillTable();
            viewContratLocation.setTableModel(modelTable, 3);
    	});
    }

	@Override
	void updateFooter() {
	    if (viewContratLocation.getToggleButton() != null) {
	        viewContratLocation.getFooterPanel().add(viewContratLocation.getToggleButton());
	        FontComponent.setFontForAllComponents(viewContratLocation.getFooterPanel(), ScallingDimension.scaleValue(18));
	    }
	}
	
		
}