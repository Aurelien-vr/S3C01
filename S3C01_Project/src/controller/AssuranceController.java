package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOFactory;
import utilities.ErrorMessage;
import utilities.FontComponent;
import utilities.ScallingDimension;
import dao.AssuranceDAO;
import view.AssuranceView;

public class AssuranceController extends TemplateTableController {
    
    private AssuranceView view = new AssuranceView();
    private AssuranceDAO model = DAOFactory.createAssuranceDAO();
    private List<List<String>> listData;
    
    private int totalPrime = 0;
    private int totalProtectionJuridique = 0;
    private int totalTotal = 0;
    
    public AssuranceController() {
        super();
        view.setTitleHeader("Assurance");
        fillTable();
        view.setTableModel(modelTable);
        deleteActionButton();
        openAjoutAssurancePage();
        logoLabel();
        addEventHandlers();
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
        List<String> latestDates = findLatestDate(listData);
        System.out.println(latestDates);
        
        for (int i = 0; i < listData.size(); i++) {
            List<String> rowResult = listData.get(i);

            String adresse = rowResult.get(0);
            String date = rowResult.get(1);
            double prime = Double.parseDouble(rowResult.get(2));
            double protectionJuridique = Double.parseDouble(rowResult.get(3));
            
            for(String latestDate : latestDates) {
                if (date.equals(latestDate)) {
                    totalPrime += prime;
                    totalProtectionJuridique += protectionJuridique;
                    totalTotal += prime + protectionJuridique;
                }
            }
            
            Object[] row = {adresse, TemplateTableController.transformDate(date), prime, protectionJuridique, prime + protectionJuridique,"N/A", "Delete"};
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
                        diffPrev = diff;
                        modelTable.setValueAt(percent, i, 5);
                    }
                }
            }
        }
       updateFooter();
    }

    private void openAjoutAssurancePage() {
        view.getAjoutAssurance().addActionListener(e-> {
                new AssuranceAjoutController();
                view.dispose();
        });
    }

    private void deleteActionButton() {
        view.getDeleteButton().addActionListener(e -> {
                int selectedRow = view.getTable().getSelectedRow();
                int idAssurance = Integer.parseInt(listData.get(selectedRow).get(4)); // Parse as Double and cast to int
                int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la suppression de l'assurance: " + idAssurance);
                if (response == JOptionPane.YES_OPTION) {
                    model.deleteById(idAssurance);
                    modelTable.setRowCount(0);
                    fillTable();
                    view.setTableModel(modelTable, 1);
                }
        });
    }
    
    public static int compareDates(String dateStr1, String dateStr2) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Try parsing with the first format
            java.util.Date utilDate1 = dateFormat1.parse(dateStr1);
            java.util.Date utilDate2 = dateFormat1.parse(dateStr2);

            Date sqlDate1 = new Date(utilDate1.getTime());
            Date sqlDate2 = new Date(utilDate2.getTime());

            return sqlDate1.compareTo(sqlDate2);
        } catch (ParseException e1) {
            try {
                // Try parsing with the second format
                java.util.Date utilDate1 = dateFormat2.parse(dateStr1);
                java.util.Date utilDate2 = dateFormat2.parse(dateStr2);

                Date sqlDate1 = new Date(utilDate1.getTime());
                Date sqlDate2 = new Date(utilDate2.getTime());

                return sqlDate1.compareTo(sqlDate2);
            } catch (ParseException e2) {
                e2.printStackTrace();
                return 0;
            }
        }
    }
    
    private void logoLabel() {
        view.getLogoLabel().addActionListener (e ->{
                new HomeController();
                view.dispose();
        });
    }
    
    private List<String> findLatestDate(List<List<String>> listData) {
        // A map to keep track of the latest date for each address
        Map<String, String> latestDatesMap = new HashMap<>();

        for (List<String> row : listData) {
            String address = row.get(0);
            String dateStr = row.get(1);

            // If the address is not in the map or the date is later than the current latest date, update the map
            if (!latestDatesMap.containsKey(address) || compareDates(latestDatesMap.get(address), dateStr) < 0) {
                latestDatesMap.put(address, dateStr);
            }
        }

        // Collect all the latest dates into a list
        List<String> latestDates = new ArrayList<>(latestDatesMap.values());

        return latestDates;
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
    
    @Override
    void updateFooter() {
        JLabel totalPrimeLabel = new JLabel("Total Prime: " + totalPrime);
        JLabel totalProtectionJuridiqueLabel = new JLabel("Total Protection Juridique: " + totalProtectionJuridique);
        JLabel totalTotalLabel = new JLabel("Total: " + totalTotal);

        view.updateFooter(totalPrimeLabel, totalProtectionJuridiqueLabel, totalTotalLabel);
        FontComponent.setFontForAllComponents(view.getFooterPanel(), (int) (ScallingDimension.scaleValue(14) * 1.3));
    }
}