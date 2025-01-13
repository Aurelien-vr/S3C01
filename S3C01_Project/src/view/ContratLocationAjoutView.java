package view;

import javax.swing.*;

import utilities.FontComponent;
import utilities.PlaceHolderText;
import utilities.ScallingDimension;

import java.awt.*;

@SuppressWarnings("serial")
public class ContratLocationAjoutView extends TemplateAjoutView {

    private JLabel labMontant = new JLabel("Montant:");
    private JTextField fieldMontant = new JTextField(10);
    private JLabel labDateDebut = new JLabel("Date début:");
    private JTextField fieldDateDebut = new JTextField(10);
    private JLabel labDateFin = new JLabel("Date fin:");
    private JTextField fieldDateFin = new JTextField(10);
    private JLabel labModaliteChauffage = new JLabel("Modalité Chauffage:");
    private JTextField fieldModaliteChauffage = new JTextField(10);
    private JLabel labModaliteEauChaude = new JLabel("Modalité Eau Chaude Sanitaire:");
    private JTextField fieldModaliteEauChaude = new JTextField(10);
    private JLabel labDateVersement = new JLabel("Date de Versement:");
    private JTextField fieldDateVersement = new JTextField(10);
    private JLabel labBien = new JLabel("Bien:");
    private JComboBox<String> comboBien = new JComboBox<>();
    private JLabel labLocataire = new JLabel("Locataire:");
    private JComboBox<String> comboLocataire = new JComboBox<>();
    private JButton annulerButton;
    private JButton ajouterBienButton;
    private JButton ajouterLocataireButton;
    private JButton validerButton;

    public ContratLocationAjoutView() {
        super();
        setTitleHeader("Ajout Contrat Location");
        fillFormWithFields();
        addButtonPanel();
        FontComponent.setFontForAllComponents(area, ScallingDimension.scaleValue(14));
        setVisible(true);
    }

    private void fillFormWithFields() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(labMontant, gbc);

        gbc.gridx = 1;
        form.add(fieldMontant, gbc);

        gbc.gridx = 2;
        form.add(labDateDebut, gbc);

        gbc.gridx = 3;
        PlaceHolderText.addPlaceholderText(fieldDateDebut, "dd/mm/yyyy");
        form.add(fieldDateDebut, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(labDateFin, gbc);

        gbc.gridx = 1;
        PlaceHolderText.addPlaceholderText(fieldDateFin, "dd/mm/yyyy");
        form.add(fieldDateFin, gbc);

        gbc.gridx = 2;
        form.add(labModaliteChauffage, gbc);

        gbc.gridx = 3;
        form.add(fieldModaliteChauffage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labModaliteEauChaude, gbc);

        gbc.gridx = 1;
        form.add(fieldModaliteEauChaude, gbc);

        gbc.gridx = 2;
        form.add(labDateVersement, gbc);

        gbc.gridx = 3;
        form.add(fieldDateVersement, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(labBien, gbc);

        gbc.gridx = 1;
        form.add(comboBien, gbc);

        gbc.gridx = 2;
        form.add(labLocataire, gbc);

        gbc.gridx = 3;
        form.add(comboLocataire, gbc);

        area.add(form, BorderLayout.CENTER);
    }

    private void addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        annulerButton = new JButton("Annuler");
        ajouterBienButton = new JButton("Ajouter Bien");
        ajouterLocataireButton = new JButton("Ajouter Locataire");
        validerButton = new JButton("Valider");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 70, 30));

        panel.add(Box.createHorizontalGlue());
        panel.add(annulerButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(ajouterBienButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(ajouterLocataireButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(validerButton);
        panel.add(Box.createHorizontalGlue());

        area.add(panel, BorderLayout.SOUTH);
    }

    // Getters for the controller
    public JTextField getFieldMontant() {
        return fieldMontant;
    }

    public JTextField getFieldDateDebut() {
        return fieldDateDebut;
    }

    public JTextField getFieldDateFin() {
        return fieldDateFin;
    }

    public JTextField getFieldModaliteChauffage() {
        return fieldModaliteChauffage;
    }

    public JTextField getFieldModaliteEauChaude() {
        return fieldModaliteEauChaude;
    }

    public JTextField getFieldDateVersement() {
        return fieldDateVersement;
    }

    public JComboBox<String> getComboBien() {
        return comboBien;
    }

    public JComboBox<String> getComboLocataire() {
        return comboLocataire;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JButton getAjouterBienButton() {
        return ajouterBienButton;
    }

    public JButton getAjouterLocataireButton() {
        return ajouterLocataireButton;
    }

    public JButton getValiderButton() {
        return validerButton;
    }
}