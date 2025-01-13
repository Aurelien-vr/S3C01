package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import view.ContratLocationView;

public class ContratLocationController extends TemplateTableController {
    
    private ContratLocationView view = new ContratLocationView();
    private Contrat_locationDAO modelContratLocation = DAOFactory.createContrat_locationDAO();
    private List<List<String>> listData;
    
    public ContratLocationController(){
        super();
        view.setTitleHeader("Contrat de location");
        updateFooter();
        openAjoutContratLocationPage();
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
        modelTable = new DefaultTableModel(new String[]{"Montant", "Date debut", "Date fin", "Modalité", "Adresse bien", "Nom locataire", "Colocation", "Etat des lieux", "Solde de tout compte", "Regularisation des charges", "SUPPRIMER"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                int columnCount = getColumnCount();
                return column == columnCount - 1;
            }};
        
            
        if(view.getToggleButton() != null && view.getToggleButton().isSelected()) {
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
        view.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            int idContratLocation = Integer.parseInt(listData.get(selectedRow).get(10));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression du contrat de location: " + idContratLocation);
            if (response == JOptionPane.YES_OPTION) {
                modelContratLocation.procCascadeDelete(idContratLocation);
                modelTable.setRowCount(0);
                fillTable();
                view.setTableModel(modelTable, 1);
            }
        });
    }
    
    private void openAjoutContratLocationPage() {
        view.getAjoutContratLocationButton().addActionListener(e -> {
        	new ContratLocationAjoutController();
        	view.dispose();});
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