package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.LocataireDAO;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import dao.DAOFactory;
import view.Locataire;

public class LocataireController extends TemplateTableController {

    private Locataire view = new Locataire();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private List<List<String>> listData;

    public LocataireController() {
        super();
        view.setTitleHeader("Locataire");
        updateFooter();
        openAjoutLocatairePage();
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
        modelTable = new DefaultTableModel(new String[]{"Name", "Date", "Logement", "SUPPRIMER"}, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                int columnCount = getColumnCount();
                return column == columnCount - 1;
            }
        };

        if (view.getToggleButton() != null && view.getToggleButton().isSelected()) {
            listData = modelLocataire.procGetLocatairesActifs();
        } else {
            listData = modelLocataire.procGetLocataires();
        }

        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);
            String name = rowResult.get(0);
            String date = rowResult.get(1);
            String logement = rowResult.get(2);
            Object[] row = {name, date, logement, "Delete"};
            modelTable.addRow(row);
        }
    }

    private void actionDeleteButton() {
        view.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            int idLocataire = Integer.parseInt(listData.get(selectedRow).get(listData.get(selectedRow).size() - 1));
            int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression du locataire: " + idLocataire);
            if (response == JOptionPane.YES_OPTION) {
                modelLocataire.deleteById(idLocataire);
                modelTable.setRowCount(0);
                fillTable();
                view.setTableModel(modelTable, 1);
            }
        });
    }

    private void openAjoutLocatairePage() {
        view.getAjoutLocataireButton().addActionListener(e -> {
            view.dispose();
            new LocataireAjoutController();
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