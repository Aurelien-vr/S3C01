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
public class AssuranceAjout extends TemplateAjout {

    private JLabel labPrime = new JLabel("Prime:");
    private JTextField fieldPrime = new JTextField(10);
    private JLabel labProtectionJuridique = new JLabel("Protection juridique:");
    private JTextField fieldProtectionJuridique = new JTextField(10);
    private JLabel labIdBien = new JLabel("ID Bien:");
    private JComboBox<String> cbIdBien = new JComboBox<>();
    private JLabel labDate = new JLabel("Date:");
    private JTextField fieldDate = new JTextField(10);
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel form = new JPanel();
    private JButton annulerButton;
    private JButton validerButton;

    int fontSize = ScallingDimension.scaleValue(14);

    public AssuranceAjout() {
        super();
        changeLabel("Ajout d'assurance");
        FontComponent.setFontForAllComponents(form, fontSize);
        area.add(addButtonPanel(), BorderLayout.SOUTH);
        fillFormWithFields();
    }

    public void fillFormWithFields() {
        form.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(labPrime, gbc);

        gbc.gridx = 1;
        form.add(fieldPrime, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(labProtectionJuridique, gbc);

        gbc.gridx = 1;
        form.add(fieldProtectionJuridique, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(labDate, gbc);

        gbc.gridx = 1;
        form.add(fieldDate, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(labIdBien, gbc);

        gbc.gridx = 1;
        form.add(cbIdBien, gbc);

        area.add(form);
        
        FontComponent.setFontForAllComponents(form, (int) (fontSize * 1.3));
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

    public JTextField getFieldPrime() {
        return fieldPrime;
    }

    public JTextField getFieldProtectionJuridique() {
        return fieldProtectionJuridique;
    }

    public JComboBox<String> getCbIdBien() {
        return cbIdBien;
    }

    public JTextField getFieldDate() {
        return fieldDate;
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
}