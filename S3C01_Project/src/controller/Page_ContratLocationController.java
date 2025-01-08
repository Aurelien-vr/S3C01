package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import view.ErrorMessage;
import view.Page_AjoutTravaux;
import view.Page_ContratLocation;

public class Page_ContratLocationController extends TableSkeletonController {
    
    private Page_ContratLocation view = new Page_ContratLocation();
    private Contrat_locationDAO modelContratLocation = DAOFactory.createContrat_locationDAO();
    private List<List<String>> listData;
    
    public Page_ContratLocationController(){
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
	        new Page_PrincipaleController();
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
            int idContratLocation = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression du contrat de location: " + idContratLocation);
            if (response == JOptionPane.YES_OPTION) {
                modelContratLocation.deleteById(idContratLocation);
                modelTable.setRowCount(0);
                fillTable();
                view.setTableModel(modelTable, 1);
            }
        });
    }
    
    private void openAjoutContratLocationPage() {
        view.getAjoutContratLocationButton().addActionListener(e -> {
                view.dispose();});
        //new Page_AjoutContratLocationController();
    }
    
    private void addEventHandlers() {
        view.getBtnBienLouable().addActionListener(e -> {
        	new Page_BienController();
        	view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
                System.out.println("Locataire clicked");
        });
        

        view.getBtnDocument().addActionListener(e ->{
                System.out.println("Doc cliqué");
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
	        Page_AjoutTravaux.setFontForAllComponents(view.getFooterPanel(), ScallingDimension.scaleValue(18));
	    } else {
	        System.out.println("Toggle button is not initialized.");
	    }
	}
		
}