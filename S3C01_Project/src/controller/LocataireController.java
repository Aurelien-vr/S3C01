package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.LocataireDAO;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import dao.DAOFactory;
import view.LocataireView;

public class LocataireController extends TemplateTableController {

    private LocataireView viewLocataire = new LocataireView();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private List<List<String>> listData;

    public LocataireController() {
        super();
        viewLocataire.setTitleHeader("Locataire");
        updateFooter();
        openAjoutLocatairePage();
        actionDeleteButton();
        addEventHandlers();
        logoLabel();
        fillTable();
        actionToggleButton();
        viewLocataire.setTableModel(modelTable);
        viewLocataire.setVisible(true);
    }

    private void logoLabel() {
        viewLocataire.getLogoLabel().addActionListener(e -> {
            new HomeController();
            viewLocataire.dispose();
        });
    }

    @Override
    void fillTable() {
        modelTable = new DefaultTableModel(new String[]{"Name", "Date de naissance", "Logement", "IBAN" ,"SUPPRIMER"}, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                int columnCount = getColumnCount();
                return column == columnCount - 1;
            }
        };

        if (viewLocataire.getToggleButton() != null && viewLocataire.getToggleButton().isSelected()) {
            listData = modelLocataire.procGetLocatairesActifs();
        } else {
            listData = modelLocataire.procGetLocataires();
        }

        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);
            String name = rowResult.get(1);
            String date = rowResult.get(3);
            String logement = rowResult.get(2);
            String iban = rowResult.get(4);
            Object[] row = {name, transformDate(date), logement, iban, "Delete"};
            modelTable.addRow(row);
        }
    }

    private void actionDeleteButton() {
        viewLocataire.getDeleteButton().addActionListener(e -> {
            int selectedRow = viewLocataire.getTable().getSelectedRow();
            int idLocataire = Integer.parseInt(listData.get(selectedRow).get(0));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression du locataire: " + idLocataire);
            if (response == JOptionPane.YES_OPTION) {
                modelLocataire.procDeleteLocataireCascade(idLocataire);
                modelTable.setRowCount(0);
                fillTable();
                viewLocataire.setTableModel(modelTable, 1);
            }
        });
    }

    private void openAjoutLocatairePage() {
        viewLocataire.getAjoutLocataireButton().addActionListener(e -> {
        	viewLocataire.dispose();
            new LocataireAjoutController();
        });
    }

    private void addEventHandlers() {
        viewLocataire.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewLocataire.dispose();
        });

        viewLocataire.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewLocataire.dispose();
        });
        
        viewLocataire.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewLocataire.dispose();
        });
        
        viewLocataire.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewLocataire.dispose();
        });
        
        viewLocataire.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewLocataire.dispose();
        });
        
        viewLocataire.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewLocataire.dispose();
        });
        
        viewLocataire.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewLocataire.dispose();
        });
    }

    private void actionToggleButton() {
        viewLocataire.getToggleButton().addActionListener(e -> {
            fillTable();
            viewLocataire.setTableModel(modelTable, 3);
        });
    }

    @Override
    void updateFooter() {
        if (viewLocataire.getToggleButton() != null) {
            viewLocataire.getFooterPanel().add(viewLocataire.getToggleButton());
            FontComponent.setFontForAllComponents(viewLocataire.getFooterPanel(), ScallingDimension.scaleValue(18));
        } 
    }
}