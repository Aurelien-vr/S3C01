package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import utilities.FontComponent;
import utilities.PlaceHolderText;
import utilities.ScallingDimension;

import java.awt.*;

@SuppressWarnings("serial")
public class ChargeAjoutView extends TemplateAjoutView {
    private final boolean edit;
    private JTextField fieldDateCharge;
    private JComboBox<String> comboDate;
    private JComboBox<String> comboAdresse;
    private JComboBox<String> comboFacture;
    private JButton addFactureButton;
    private JTable factureTable;
    private JButton annulerButton;
    private JButton validerButton;

    public ChargeAjoutView(boolean edit) {
        this.edit = edit;
        setTitleHeader(edit ? "Edit Charge" : "Add Charge");
        initializeComponents();
        layoutComponents();
        FontComponent.setFontForAllComponents(area, ScallingDimension.scaleValue(14));
        setVisible(true);
    }

    private void initializeComponents() {
        fieldDateCharge = new JTextField(10);
        PlaceHolderText.addPlaceholderText(fieldDateCharge, "dd/mm/yyyy");
        
        comboAdresse = new JComboBox<>();
        comboFacture = new JComboBox<>();
        comboDate = new JComboBox<>(); // Initialize comboDate
        
        addFactureButton = new JButton("Add Facture");
        
        factureTable = new JTable(new DefaultTableModel(new Object[]{"Factures"}, 0));
        
        annulerButton = new JButton("Annuler");
        validerButton = new JButton("Valider");
    }

    private void layoutComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Left side components
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Adresse:"), gbc);

        gbc.gridx = 1;
        formPanel.add(comboAdresse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Date Charge:"), gbc);

        gbc.gridx = 1;
        if(edit) {
            formPanel.add(comboDate, gbc); // Add comboDate if edit mode
        } else {
            formPanel.add(fieldDateCharge, gbc); // Add fieldDateCharge if not edit mode
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Facture:"), gbc);

        gbc.gridx = 1;
        formPanel.add(comboFacture, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(addFactureButton, gbc);

        // Right side components (Table)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(factureTable), BorderLayout.CENTER);

        // Split the area into left and right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
        area.add(splitPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 70, 30));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(annulerButton);
        buttonPanel.add(Box.createHorizontalStrut(ScallingDimension.scaleValue(100)));
        buttonPanel.add(validerButton);
        buttonPanel.add(Box.createHorizontalGlue());

        area.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for the controller
    public JTextField getFieldDateCharge() {
        return fieldDateCharge;
    }

    public JComboBox<String> getComboAdresse() {
        return comboAdresse;
    }

    public JComboBox<String> getComboFacture() {
        return comboFacture;
    }

    public JButton getAddFactureButton() {
        return addFactureButton;
    }

    public JTable getFactureTable() {
        return factureTable;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public JComboBox<String> getComboDate() {
        return comboDate;
    }
}