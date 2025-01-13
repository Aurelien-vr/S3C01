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

    private LocataireAjoutView view = new LocataireAjoutView();
    private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
    private BienDAO modelBien = DAOFactory.createBienDAO();
    private boolean errorRaise;
    private List<List<String>> dataCbBien;
    
    private String defaultVal = "Créé seul";

    public LocataireAjoutController() {
        super();
        view.setTitleHeader("Locataire");
        pressedValider();
        pressedAjouterCl();
        pressedAnnuler();
        logoLabel();
        addEventHandlers();
        populateCb();
        view.setVisible(true);
    }
    
    private void logoLabel() {
        view.getLogoLabel().addActionListener(e -> {
                new HomeController();
                view.dispose();
        });
    }
    
    private void pressedValider() {
        view.getValiderButton().addActionListener(e -> {
                errorRaise = false;
                checkNom();
                checkPrenom();
                checkDateNaissance();
                checkIban();
                if (!errorRaise) {
                    createLocataire();
                    new LocataireController();
                    view.dispose();
            }
        });
    }

    private void pressedAnnuler() {
        view.getAnnulerButton().addActionListener(e -> {
                new LocataireController();
                view.dispose();
        });
    }

    private void pressedAjouterCl() {
        view.getAjouterCLButton().addActionListener(e -> {
            new ContratLocationAjoutController();
            view.dispose();
        });
    }

    private void checkNom() {
        if (errorRaise) { return; }
        String input = view.getFieldNom().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le nom ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkPrenom() {
        if (errorRaise) { return; }
        String input = view.getFieldPrenom().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("Le prénom ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void checkDateNaissance() {
        if (errorRaise) { return; }
        String input = view.getFieldDateNaissance().getText();
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
        String input = view.getFieldIban().getText();
        if (input.isEmpty()) {
            ErrorMessage.errorDialog("L'IBAN ne peut pas être vide");
            errorRaise = true;
        }
    }

    private void addEventHandlers() {
        view.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	view.dispose();
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
    
    private void createLocataire() {
        try {
            String nom = view.getFieldNom().getText();
            String prenom = view.getFieldPrenom().getText();
            Date dateNaissance = ContratLocationAjoutController.transformStringToDate(view.getFieldDateNaissance().getText());
            String iban = view.getFieldIban().getText();
            
            Locataire locataire = new Locataire(nom, prenom, dateNaissance, iban);
            
            if ((view.getComboContratLocation().getSelectedItem() != defaultVal && view.getComboBoxBien().getSelectedItem() == defaultVal) ||
                (view.getComboContratLocation().getSelectedItem() == defaultVal && view.getComboBoxBien().getSelectedItem() != defaultVal)) {
                   ErrorMessage.errorDialog("Les 2 champs doivent être \"Créé seul\" ou aucun");
            } else {                
                modelLocataire.insert(locataire);
            }
            
            //Handle fk
            if(view.getComboContratLocation().getSelectedItem() != defaultVal) {
                int bien = retrieveIdBien(view.getComboBoxBien());
                int contratLocation = Integer.parseInt((String) view.getComboContratLocation().getSelectedItem());                
                modelLocataire.insertFK(locataire.getId_locataire(), contratLocation);
                modelBien.insertFK(bien, contratLocation);
            }
        } catch (NumberFormatException e) {
            ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
        }
    }
    
    private void populateCb() {
        String[] listCB = modelBien.procGetClNotInBien();
        dataCbBien = modelBien.procBienSansContrat();
        
        view.getComboContratLocation().addItem(defaultVal);
        view.getComboBoxBien().addItem(defaultVal);
        for(String item : listCB){            
            view.getComboContratLocation().addItem(item);
        }
        for(List<String> list: dataCbBien) {
            view.getComboBoxBien().addItem(list.get(0));
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