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
import utilities.ScallingDimension;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public class FactureAjout extends TemplateAjout {

    private JLabel labTypeFacture = new JLabel("Type de facture:");
    private JComboBox<String> cbTypeFacture = new JComboBox<>(new String[]{"Eau", "Gaz", "Electricité", "Custom"});
    private JLabel labDateFacture = new JLabel("Date de facture:");
    private JTextField fieldDateFacture = new JTextField(10);
    private JLabel labMontantFacture = new JLabel("Montant de facture:");
    private JTextField fieldMontantFacture = new JTextField(10);
    private JLabel labMoyenPaiement = new JLabel("Moyen de paiement:");
    private JTextField fieldMoyenPaiement = new JTextField(10);
    private JLabel labAdress = new JLabel("Adresse:");
    private JComboBox<String> cbAdress = new JComboBox<>();
    private JLabel labFactureName = new JLabel("Nom de la facture:");
    private JTextField fieldFactureName = new JTextField(10);
    private JTextField fieldWaterConsumption = new JTextField(10);
    private JTextField fieldGasPrice = new JTextField(10);
    private JTextField fieldGasConsumption = new JTextField(10);
    private JTextField fieldElectricityPrice = new JTextField(10);
    private JTextField fieldElectricityConsumption = new JTextField(10);
    private JTextField fieldDetail = new JTextField(10);
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel form = new JPanel();
    private JLabel fieldAdditionalInfo = new JLabel();
    private JButton annulerButton;
    private JButton validerButton;

    int fontSize = ScallingDimension.scaleValue(14);

    public FactureAjout() {
        super();
        changeLabel("Ajout de facture");
        FontComponent.setFontForAllComponents(form, fontSize);
        area.add(addButtonPanel(), BorderLayout.SOUTH);
        fillFormWithFields();
    }

    public void fillFormWithFields() {
        form.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(labTypeFacture, gbc);

        gbc.gridx = 1;
        form.add(cbTypeFacture, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labFactureName, gbc);

        gbc.gridx = 1;
        form.add(fieldFactureName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(labDateFacture, gbc);

        gbc.gridx = 1;
        form.add(fieldDateFacture, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        form.add(labMontantFacture, gbc);

        gbc.gridx = 1;
        form.add(fieldMontantFacture, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        form.add(labMoyenPaiement, gbc);

        gbc.gridx = 1;
        form.add(fieldMoyenPaiement, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        form.add(labAdress, gbc);

        gbc.gridx = 1;
        form.add(cbAdress, gbc);

        area.add(form);
    }

    private JPanel addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        annulerButton = new JButton("Annuler");
        validerButton = new JButton("Valider");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 70, 30));

        panel.add(Box.createHorizontalGlue());
        panel.add(annulerButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(validerButton);
        panel.add(Box.createHorizontalGlue());

        FontComponent.setFontForAllComponents(panel, (int) (fontSize * 1.3));

        return panel;
    }

    public JComboBox<String> getCbTypeFacture() {
        return cbTypeFacture;
    }

    public JTextField getFieldDateFacture() {
        return fieldDateFacture;
    }

    public JTextField getFieldMontantFacture() {
        return fieldMontantFacture;
    }

    public JTextField getFieldMoyenPaiement() {
        return fieldMoyenPaiement;
    }

    public JComboBox<String> getCbAdress() {
        return cbAdress;
    }

    public JTextField getFieldFactureName() {
        return fieldFactureName;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public JTextField getFieldWaterConsumption() {
        return fieldWaterConsumption;
    }

    public JTextField getFieldGasPrice() {
        return fieldGasPrice;
    }

    public JTextField getFieldGasConsumption() {
        return fieldGasConsumption;
    }

    public JTextField getFieldElectricityPrice() {
        return fieldElectricityPrice;
    }

    public JTextField getFieldElectricityConsumption() {
        return fieldElectricityConsumption;
    }

    public JTextField getFieldDetail() {
        return fieldDetail;
    }

    public JPanel getForm() {
        return form;
    }

    public void setAdditionalInfo(String info) {
        fieldAdditionalInfo.setText(info);
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JButton getValiderButton() {
        return validerButton;
    }
}