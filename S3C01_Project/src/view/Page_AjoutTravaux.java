package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.mysql.cj.telemetry.TelemetrySpanName;

import controller.ScallingDimension;

@SuppressWarnings("serial")
public class Page_AjoutTravaux extends AjoutSkeleton {

    private JPanel form = new JPanel();
    private JLabel labFacture = new JLabel("Facture :");
    private JLabel labAdress = new JLabel("Adresse:");
    private JComboBox<String> cbFacture;
    private JComboBox<String> cbAdress;
    private JLabel labMontant = new JLabel("Montant:");
    private JLabel labMmontantNonDeductible = new JLabel("Montant non déductible:");
    private JTextField fieldMontant = new JTextField(10);
    private JTextField fieldMontantNonDeductible = new JTextField(10);
    private JLabel labReduction = new JLabel("Réduction:");
    private JTextField fielReduction = new JTextField(10);
    private JLabel resultLabel = new JLabel("Montant à déclarer:");
    private JLabel labDate = new JLabel("Date:");
    private JFormattedTextField dateField;
    private JTextField resultField = new JTextField(10);
    private JLabel labNature = new JLabel("Nature:");
    private JTextField natureField = new JTextField(10);

    private JButton validerButton;
    private JButton annulerButton;
    private JButton ajouterFactureButton;
    
	int scalePadding;
    int fontSize;

    public Page_AjoutTravaux() {
        super();

        cbFacture = new JComboBox<>();
        cbAdress = new JComboBox<>();

        scalePadding = ScallingDimension.scaleValue(15);
        fontSize = ScallingDimension.scaleValue(14);

        changeLabel("Ajout de travaux");

        cbAdress = new JComboBox<>(new String[]{"Option A", "Option B", "Option C"});

        // Initialize the date field with a date formatter
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        dateField = new JFormattedTextField(dateFormatter);
        dateField.setColumns(10);

        fillFormWithFields();

        setFontForAllComponents(form, fontSize);
        area.add(addButtonPanel(), BorderLayout.SOUTH);
        area.add(form, BorderLayout.CENTER);
    }

    private void fillFormWithFields() {
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(scalePadding * 2, scalePadding, scalePadding, scalePadding);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        form.add(labFacture, gbc);

        gbc.gridx = 1;
        form.add(cbFacture, gbc);

        gbc.gridx = 2;
        form.add(labAdress, gbc);

        gbc.gridx = 3;
        form.add(cbAdress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(labMontant, gbc);

        gbc.gridx = 1;
        form.add(fieldMontant, gbc);

        gbc.gridx = 2;
        form.add(labMmontantNonDeductible, gbc);

        gbc.gridx = 3;
        form.add(fieldMontantNonDeductible, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labReduction, gbc);

        gbc.gridx = 1;
        form.add(fielReduction, gbc);

        gbc.gridx = 2;
        form.add(labDate, gbc);

        gbc.gridx = 3;
        form.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(labNature, gbc);

        gbc.gridx = 1;
        form.add(natureField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        form.add(resultLabel, gbc);

        gbc.gridx = 1;
        form.add(resultField, gbc);

        resultField.setEditable(false);
    }

    private JPanel addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Create buttons
        annulerButton = new JButton("Annuler");
        ajouterFactureButton = new JButton("Ajouter une nouvel facture");
        validerButton = new JButton("Valider");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 70, 30));

        panel.add(Box.createHorizontalGlue());
        panel.add(annulerButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(ajouterFactureButton);
        panel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        panel.add(validerButton);
        panel.add(Box.createHorizontalGlue());

        setFontForAllComponents(panel, (int) (fontSize * 1.3));

        return panel;
    }

    public static void setFontForAllComponents(Container container, int fontSize) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JTextField ||
                    component instanceof JComboBox || component instanceof JButton) {
                component.setFont(component.getFont().deriveFont((float) fontSize));
            }
            if (component instanceof Container) {
                setFontForAllComponents((Container) component, fontSize); // Recursive for nested containers
            }
        }
    }

    public JPanel getForm() {
        return form;
    }

    public JLabel getLabFacture() {
        return labFacture;
    }

    public JLabel getlabAdresse() {
        return labAdress;
    }

    public JComboBox<String> getCbFacture() {
        return cbFacture;
    }

    public void setCbFacture(String[] items) {
        cbFacture.removeAllItems();
        for (String item : items) {
            cbFacture.addItem(item);
        }
    }

    public JComboBox<String> getCbAdress() {
        return cbAdress;
    }

    public void setCbAdress(String[] items) {
        cbAdress.removeAllItems();
        for (String item : items) {
            cbAdress.addItem(item);
        }
    }

    public JLabel getLabMontant() {
        return labMontant;
    }

    public JLabel getLabMmontantNonDeductible() {
        return labMmontantNonDeductible;
    }

    public JTextField getFieldMontant() {
        return fieldMontant;
    }

    public JTextField getFieldMontantNonDeductible() {
        return fieldMontantNonDeductible;
    }

    public JLabel getLabReduction() {
        return labReduction;
    }

    public JTextField getFielReduction() {
        return fielReduction;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JTextField getResultField() {
        return resultField;
    }

    public void setResultField(String string) {
        this.resultField.setText(string);
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JButton getAjouterFactureButton() {
		return ajouterFactureButton;
	}

    
    public JFormattedTextField getDateField() {
        return dateField;
    }

    public JTextField getNatureField() {
        return natureField;
    }
}