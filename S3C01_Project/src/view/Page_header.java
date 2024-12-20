package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

public class Page_header extends windowSkeleton {

    private static final long serialVersionUID = 1L;

    // Boutons accessibles depuis le contrôleur
    private JButton btnBienLouable;
    private JButton btnLocataire;
    private JButton btnImmeuble;
    private JButton btnDocument;

    public Page_header() {
        super();
        initializeUI();
        setTitle("Header");
    }

    private void initializeUI() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Transparence pour s'intégrer dans la bannière

        // Création des boutons
        btnBienLouable = new JButton("Bien louable");
        btnLocataire = new JButton("Locataire");
        btnImmeuble = new JButton("Immeuble");
        btnDocument = new JButton("Document");

        // Utilisation de GridBagConstraints pour le positionnement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 70, 0, 70); // Espacement horizontal entre les boutons
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        buttonPanel.add(btnBienLouable, gbc);
        gbc.gridx++;
        buttonPanel.add(btnLocataire, gbc);
        gbc.gridx++;
        buttonPanel.add(btnImmeuble, gbc);
        gbc.gridx++;
        buttonPanel.add(btnDocument, gbc);

        headerPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    // Getters des boutton pour le controlleur
    public JButton getBtnBienLouable() {
        return btnBienLouable;
    }

    public JButton getBtnLocataire() {
        return btnLocataire;
    }

    public JButton getBtnImmeuble() {
        return btnImmeuble;
    }

    public JButton getBtnDocument() {
        return btnDocument;
    }
}
