package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.DAOFactory;
import dao.AssuranceDAO;
import view.ErrorMessage;
import view.Page_Assurance;

public class Page_AssuranceController extends TableSkeletonController {
    
    private Page_Assurance view = new Page_Assurance();
    private AssuranceDAO model = DAOFactory.createAssuranceDAO();
    private List<List<String>> listData;
    
    public Page_AssuranceController() {
        super();
        fillTable();
        view.setTableModel(modelTable);
        deleteActionButton();
        openAjoutAssurancePage();
        view.setVisible(true);
    }

    @SuppressWarnings("serial")
    @Override
    void fillTable() {
        modelTable = new DefaultTableModel(new String[]{"Adresse", "Date", "Prime", "Protection juridique", "Total" ,"Pourcentage augmentation", "SUPPRIMER"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        listData = model.procGet_assurances();
        
        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);
            
            String adresse = rowResult.get(0);
            String date = rowResult.get(1);
            double prime = Double.parseDouble(rowResult.get(2));
            double protectionJuridique = Double.parseDouble(rowResult.get(3));
            
            Object[] row = {adresse, Page_TravauxController.transformDate(date), prime, protectionJuridique, prime + protectionJuridique,"N/A", "Delete"};
            modelTable.addRow(row);
        }
                
        for (int i = 0; i < modelTable.getRowCount(); i++) {
        	int diffPrev = -1;
            for (int j = 0; j < modelTable.getRowCount(); j++) {
            	int diff = compareDates((String) modelTable.getValueAt(i, 1), (String) modelTable.getValueAt(j, 1));
                if (diff > 0 && listData.get(i).get(listData.get(i).size() - 1).equals(listData.get(j).get(listData.get(j).size() - 1)) && i != j) {
                	double prime = (double) (modelTable.getValueAt(i, 4));
                	double prevPrime = (double) (modelTable.getValueAt(j, 4));
                	String percent= String.format("%.2f%%", ((prime - prevPrime) / prevPrime) * 100);
                	if(diffPrev == -1 || diff < diffPrev) {
                		diff = diffPrev;
                		modelTable.setValueAt(percent, i, 5);
                	}
                }
            }
        }
    }

    private void openAjoutAssurancePage() {
        view.getAjoutAssurance().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Page_AjoutAssuranceController();
                view.dispose();
            }
        });
    }

    private void deleteActionButton() {
        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                int idAssurance = Integer.parseInt(listData.get(selectedRow).get(4)); // Parse as Double and cast to int
                int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression de l'assurance: " + idAssurance);
                if (response == JOptionPane.YES_OPTION) {
                    model.deleteById(idAssurance);
                    modelTable.setRowCount(0);
                    fillTable();
                    view.setTableModel(modelTable, 1);
                } else if (response == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        });
    }
    
    
    public static int compareDates(String dateStr1, String dateStr2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date utilDate1 = dateFormat.parse(dateStr1);
            java.util.Date utilDate2 = dateFormat.parse(dateStr2);

            Date sqlDate1 = new Date(utilDate1.getTime());
            Date sqlDate2 = new Date(utilDate2.getTime());

            return sqlDate1.compareTo(sqlDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}