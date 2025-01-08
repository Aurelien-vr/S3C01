package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.LocataireDAO;
import dao.DAOFactory;
import view.ErrorMessage;
import view.Page_AjoutTravaux;
import view.Page_locataire;

public class Page_LocataireController extends TableSkeletonController {

    private Page_locataire view = new Page_locataire();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private List<List<String>> listData;

    public Page_LocataireController() {
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
            new Page_PrincipaleController();
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
            new Page_AjoutLocataireController();
        });
    }

    private void addEventHandlers() {
        view.getBtnBienLouable().addActionListener(e -> {
            new Page_BienController();
            view.dispose();
        });

        view.getBtnDocument().addActionListener(e -> {
            System.out.println("Doc cliqué");
        });

        view.getBtnContratLocation().addActionListener(e -> {
            new Page_ContratLocationController();
            view.dispose();
        });

        view.getLogoLabel().addActionListener(e -> {
            new Page_PrincipaleController();
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
            Page_AjoutTravaux.setFontForAllComponents(view.getFooterPanel(), ScallingDimension.scaleValue(18));
        } else {
            System.out.println("Toggle button is not initialized.");
        }
    }
}