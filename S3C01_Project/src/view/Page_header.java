package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

public class Page_header extends windowSkeleton {

    private static final long serialVersionUID = 1L;

    // Boutons accessibles depuis le contrôleur
    private JButton btnBienLouable;
    private JButton btnLocataire;
    private JButton btnDocument;

    public Page_header() {
        super();
        setTitle("Header");
  
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Transparence pour s'intégrer dans la bannière

        btnBienLouable = new JButton("Gestion des biens");
        btnLocataire = new JButton("Locataires");
        btnDocument = new JButton("Documents");

        // Appliquer le style "stylizeButton" sur les boutons
        stylizeButton(btnBienLouable);
        stylizeButton(btnLocataire);
        stylizeButton(btnDocument);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 70, 0, 70); // Espacement horizontal entre les boutons
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        buttonPanel.add(btnBienLouable, gbc);
        gbc.gridx++;
        buttonPanel.add(btnLocataire, gbc);
        gbc.gridx++;
        buttonPanel.add(btnDocument, gbc);

        headerPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    // Méthode pour styliser les boutons
    private void stylizeButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 50)); // Taille boutton
        button.setFont(new Font("Arial", Font.PLAIN, 20)); // Taille du texte du bouton
    }

    // Getters des boutons pour le contrôleur
    public JButton getBtnBienLouable() {
        return btnBienLouable;
    }

    public JButton getBtnLocataire() {
        return btnLocataire;
    }


    public JButton getBtnDocument() {
        return btnDocument;
    }
}
