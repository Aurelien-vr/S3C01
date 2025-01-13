package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.FontComponent;
import utilities.PlaceHolderText;
import utilities.ScallingDimension;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public class LocataireAjoutView extends TemplateAjoutView {

    private JLabel labNom = new JLabel("Nom:");
    private JTextField fieldNom = new JTextField(10);
    private JLabel labPrenom = new JLabel("Prénom:");
    private JTextField fieldPrenom = new JTextField(10);
    private JLabel labDateNaissance = new JLabel("Date de Naissance:");
    private JTextField fieldDateNaissance = new JTextField(10);
    private JLabel labIban = new JLabel("IBAN:");
    private JTextField fieldIban = new JTextField(10);
    private JLabel labContratLocation = new JLabel("Contrat location disponible:");
    private JComboBox<String> comboContratLocation = new JComboBox<>();
    private JLabel labBien = new JLabel("Bien disponible:");
    private JComboBox<String> comboBien = new JComboBox<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel form = new JPanel();
    private JButton annulerButton;
    private JButton ajouterCLButton;
    private JButton validerButton;

    int fontSize = ScallingDimension.scaleValue(14);

    public LocataireAjoutView() {
        super();
        changeLabel("Ajout de Locataire");
        FontComponent.setFontForAllComponents(form, fontSize);
        area.add(addButtonPanel(), BorderLayout.SOUTH);
        fillFormWithFields();
    }

    public void fillFormWithFields() {
        form.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(labNom, gbc);

        gbc.gridx = 1;
        form.add(fieldNom, gbc);

        gbc.gridx = 2;
        form.add(labPrenom, gbc);

        gbc.gridx = 3;
        form.add(fieldPrenom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(labDateNaissance, gbc);

        gbc.gridx = 1;
        PlaceHolderText.addPlaceholderText(fieldDateNaissance, "dd/mm/yyyy");
        form.add(fieldDateNaissance, gbc);

        gbc.gridx = 2;
        form.add(labIban, gbc);

        gbc.gridx = 3;
        form.add(fieldIban, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labContratLocation, gbc);
        
        gbc.gridx = 1;
        form.add(comboContratLocation, gbc);
        
        gbc.gridx = 2;
        form.add(labBien, gbc);
        
        gbc.gridx = 3;
        form.add(comboBien, gbc);

        area.add(form);
        
        FontComponent.setFontForAllComponents(form, (int) (fontSize * 1.3));
    }

    private JPanel addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        annulerButton = new JButton("Annuler");
        ajouterCLButton = new JButton("Ajouter un nouveau contrat de location");
        validerButton = new JButton("Valider");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 70, 30));

        panel.add(Box.createHorizontalGlue());
        panel.add(annulerButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(ajouterCLButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(validerButton);
        panel.add(Box.createHorizontalGlue());

        FontComponent.setFontForAllComponents(panel, (int) (fontSize * 1.3));

        return panel;
    }

    public JTextField getFieldNom() {
        return fieldNom;
    }

    public JTextField getFieldPrenom() {
        return fieldPrenom;
    }

    public JTextField getFieldDateNaissance() {
        return fieldDateNaissance;
    }

    public JTextField getFieldIban() {
        return fieldIban;
    }

    public JComboBox<String> getComboContratLocation() {
        return comboContratLocation;
    }
    
    public JComboBox<String> getComboBoxBien(){
        return comboBien;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public JPanel getForm() {
        return form;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public JButton getAjouterCLButton() {
        return ajouterCLButton;
    }
}