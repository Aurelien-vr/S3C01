package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class PageHeaderSkeleton extends WindowSkeleton {

    private static final long serialVersionUID = 1L;

    // Boutons accessibles depuis le contrôleur
    private JButton btnBienLouable;
    private JButton btnLocataire;
    private JButton btnDocument;

    public PageHeaderSkeleton() {
        super();
  
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        btnBienLouable = new JButton("Gestion des biens");
        btnLocataire = new JButton("Locataires");
        btnDocument = new JButton("Documents");

        // Appliquer le style "stylizeButton" sur les boutons
        stylizeButton(btnBienLouable);
        stylizeButton(btnLocataire);
        stylizeButton(btnDocument);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        
        
        gbc.gridx = 0;
        buttonPanel.add(btnBienLouable, gbc);
        gbc.gridx++;
        buttonPanel.add(createSeparator(), gbc);
        gbc.gridx++;
        buttonPanel.add(btnLocataire, gbc);
        gbc.gridx++;
        buttonPanel.add(createSeparator(), gbc);
        gbc.gridx++;
        buttonPanel.add(btnDocument, gbc);
        gbc.gridx++;
        
        gbc.gridx++;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(Box.createHorizontalGlue(), gbc);
        
        headerPanel.add(buttonPanel, BorderLayout.EAST);
    }

    // Méthode pour styliser les boutons
    private void stylizeButton(JButton button) {
    	button.setPreferredSize(new Dimension(200, 100)); 
        button.setFont(new Font("Arial", Font.PLAIN, 20)); // Taille du texte du bouton
        button.setOpaque(false);
        button.setContentAreaFilled(false); // Remove background fill
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        button.setFocusPainted(false);
    }
    
    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(1, 100));
        separator.setBackground(Color.BLACK);
        separator.setOpaque(true);
        return separator;
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