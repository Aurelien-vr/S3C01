package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import dao.BienDAO;
import dao.DAOFactory;
import dao.LocataireDAO;
import dao.entities.Locataire;
import utilities.ErrorMessage;
import view.LocataireAjoutView;

public class LocataireAjoutController extends TemplateAjoutController {

    private LocataireAjoutView viewAjoutLocataire = new LocataireAjoutView();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private boolean errorRaise;
    private List<List<String>> dataCbBien;
    
    private String defaultVal = "Créé seul";

    public LocataireAjoutController() {
        super();
        viewAjoutLocataire.setTitleHeader("Locataire");
        pressedValider();
        pressedAjouterCl();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        populateCb();
        viewAjoutLocataire.setVisible(true);
    }
    
    private void logoLabel() {
        viewAjoutLocataire.getLogoLabel().addActionListener(e -> {
                new HomeController();
                viewAjoutLocataire.dispose();
        });
    }
    
    private void pressedValider() {
        viewAjoutLocataire.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkNom();
                checkPrenom();
                checkDateNaissance();
                checkIban();
                if (!errorRaise) {
                    createLocataire();
                    new LocataireController();
                    viewAjoutLocataire.dispose();
            }
        });
    }

    private void pressedAnnuler() {
        viewAjoutLocataire.getAnnulerButton().addActionListener(e -> {
                new LocataireController();
                viewAjoutLocataire.dispose();
        });
    }

    private void pressedAjouterCl() {
        viewAjoutLocataire.getAjouterCLButton().addActionListener(e -> {
            new ContratLocationAjoutController();
            viewAjoutLocataire.dispose();
        });
    }

    private void checkNom() {
        if (errorRaise) { return; }
        String input = viewAjoutLocataire.getFieldNom().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le nom ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkPrenom() {
        if (errorRaise) { return; }
        String input = viewAjoutLocataire.getFieldPrenom().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le prénom ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateNaissance() {
        if (errorRaise) { return; }
        String input = viewAjoutLocataire.getFieldDateNaissance().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("La date de naissance ne peut pas être vide");
            errorRaise = true;
            return;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Strict parsing to ensure the format is correct
        try {
            dateFormat.parse(input);
        } catch (ParseException e) {
            ErrorMessage.errorDialog("La date de naissance doit être au format jj/mm/aaaa");
            errorRaise = true;
        }
    }

    private void checkIban() {
        if (errorRaise) { return; }
        String input = viewAjoutLocataire.getFieldIban().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'IBAN ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void addEventHandlers() {
        viewAjoutLocataire.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewAjoutLocataire.dispose();
        });

        viewAjoutLocataire.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewAjoutLocataire.dispose();
        });
        
        viewAjoutLocataire.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewAjoutLocataire.dispose();
        });
        
        viewAjoutLocataire.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewAjoutLocataire.dispose();
        });
        
        viewAjoutLocataire.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewAjoutLocataire.dispose();
        });
        
        viewAjoutLocataire.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewAjoutLocataire.dispose();
        });
        
        viewAjoutLocataire.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewAjoutLocataire.dispose();
        });
    }
    
    private void createLocataire() {
        try {
            String nom = viewAjoutLocataire.getFieldNom().getText();
            String prenom = viewAjoutLocataire.getFieldPrenom().getText();
            Date dateNaissance = ContratLocationAjoutController.transformStringToDate(viewAjoutLocataire.getFieldDateNaissance().getText());
            String iban = viewAjoutLocataire.getFieldIban().getText();
            
            Locataire locataire = new Locataire(nom, prenom, dateNaissance, iban);
            
            if ((viewAjoutLocataire.getComboContratLocation().getSelectedItem() != defaultVal && viewAjoutLocataire.getComboBoxBien().getSelectedItem() == defaultVal) ||
                (viewAjoutLocataire.getComboContratLocation().getSelectedItem() == defaultVal && viewAjoutLocataire.getComboBoxBien().getSelectedItem() != defaultVal)) {
                   ErrorMessage.errorDialog("Les 2 champs doivent être \"Créé seul\" ou aucun");
            } else {          
                modelLocataire.insert(locataire);
            }
            
            //Handle fk
            if(viewAjoutLocataire.getComboContratLocation().getSelectedItem() != defaultVal) {
                int bien = retrieveIdBien(viewAjoutLocataire.getComboBoxBien());
                int contratLocation = Integer.parseInt((String) viewAjoutLocataire.getComboContratLocation().getSelectedItem());                
                modelLocataire.insertFK(locataire.getIdLocataire(), contratLocation);
                modelBien.insertFK(bien, contratLocation);
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }
    
    private void populateCb() {
        String[] listCB = modelBien.procGetClNotInBien();
        dataCbBien = modelBien.procBienSansContrat();
        
        viewAjoutLocataire.getComboContratLocation().addItem(defaultVal);
        viewAjoutLocataire.getComboBoxBien().addItem(defaultVal);
        for(String item : listCB){            
            viewAjoutLocataire.getComboBoxBien().addItem(item);
        }
        for(List<String> list: dataCbBien) {
            viewAjoutLocataire.getComboContratLocation().addItem(list.get(0));
        }
    }
    
    private int retrieveIdBien(JComboBox<String> cb) {
        int index = 0;
        for (List<String> sublist : dataCbBien) {
            for (String str : sublist) {
                if (str.equals(cb.getSelectedItem())) {
                    return Integer.parseInt(dataCbBien.get(index).get(1));
                }
            }
            index++;
        }
        return -1;
    }
}