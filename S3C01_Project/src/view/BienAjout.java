package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import utilities.FontComponent;
import utilities.ScallingDimension;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public class BienAjout extends TemplateAjout {

    private JLabel labAdresse = new JLabel("Adresse:");
    private JTextField fieldAdresse = new JTextField(10);
    private JLabel labEtage = new JLabel("Étage:");
    private JTextField fieldEtage = new JTextField(10);
    private JLabel labVille = new JLabel("Ville:");
    private JTextField fieldVille = new JTextField(10);
    private JLabel labCodePostal = new JLabel("Code Postal:");
    private JTextField fieldCodePostal = new JTextField(10);
    private JLabel labSuperficie = new JLabel("Superficie:");
    private JTextField fieldSuperficie = new JTextField(10);
    private JLabel labNombrePiece = new JLabel("Nombre de pièces:");
    private JSpinner spinnerNombrePiece = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private JCheckBox checkMeuble = new JCheckBox("Meublé");
    private JLabel labAccesoirPrive = new JLabel("Accesoir privé:");
    private JTextField fieldAccesoirPrive = new JTextField(10);
    private JLabel labAccesoirCommun = new JLabel("Accesoir commun:");
    private JTextField fieldAccesoirCommun = new JTextField(10);
    private JCheckBox checkGarage = new JCheckBox("Garage");
    private JLabel labContratLocation = new JLabel("Contrat location disponible:");
    private JComboBox<String> comboContratLocation = new JComboBox<>();
    private JLabel labLocataire = new JLabel("Locataire disponible");
    private JComboBox<String> comboLocataire = new JComboBox<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel form = new JPanel();
    private JButton annulerButton;
    private JButton ajouterCLButton;
    private JButton validerButton;

    int fontSize = ScallingDimension.scaleValue(14);

    public BienAjout() {
        super();
        changeLabel("Ajout de Bien");
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
        form.add(labAdresse, gbc);

        gbc.gridx = 1;
        form.add(fieldAdresse, gbc);

        gbc.gridx = 2;
        form.add(labEtage, gbc);

        gbc.gridx = 3;
        form.add(fieldEtage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(labVille, gbc);

        gbc.gridx = 1;
        form.add(fieldVille, gbc);

        gbc.gridx = 2;
        form.add(labCodePostal, gbc);

        gbc.gridx = 3;
        form.add(fieldCodePostal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labSuperficie, gbc);

        gbc.gridx = 1;
        form.add(fieldSuperficie, gbc);

        gbc.gridx = 2;
        form.add(labNombrePiece, gbc);

        gbc.gridx = 3;
        form.add(spinnerNombrePiece, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(checkMeuble, gbc);

        gbc.gridx = 1;
        form.add(checkGarage, gbc);

        gbc.gridx = 2;
        form.add(labAccesoirPrive, gbc);

        gbc.gridx = 3;
        form.add(fieldAccesoirPrive, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        form.add(labAccesoirCommun, gbc);

        gbc.gridx = 1;
        form.add(fieldAccesoirCommun, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        form.add(labContratLocation, gbc);
        
        gbc.gridx = 1;
        form.add(comboContratLocation, gbc);
        
        gbc.gridx = 2;
        form.add(labLocataire, gbc);
        
        gbc.gridx = 3;
        form.add(comboLocataire, gbc);
       

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

    public JTextField getFieldAdresse() {
        return fieldAdresse;
    }

    public JTextField getFieldEtage() {
        return fieldEtage;
    }

    public JTextField getFieldVille() {
        return fieldVille;
    }

    public JTextField getFieldCodePostal() {
        return fieldCodePostal;
    }

    public JTextField getFieldSuperficie() {
        return fieldSuperficie;
    }

    public JSpinner getSpinnerNombrePiece() {
        return spinnerNombrePiece;
    }

    public JCheckBox getCheckMeuble() {
        return checkMeuble;
    }

    public JTextField getFieldAccesoirPrive() {
        return fieldAccesoirPrive;
    }

    public JTextField getFieldAccesoirCommun() {
        return fieldAccesoirCommun;
    }

    public JCheckBox getCheckGarage() {
        return checkGarage;
    }

    public JComboBox<String> getComboContratLocation() {
        return comboContratLocation;
    }
    
    public JComboBox<String> getComboBoxLocataire(){
    	return comboLocataire;
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