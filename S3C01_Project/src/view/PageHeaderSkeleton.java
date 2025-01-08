package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import controller.ScallingDimension;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;

public class PageHeaderSkeleton extends WindowSkeleton {

    private static final long serialVersionUID = 1L;

    // Boutons accessibles depuis le contrôleur
    private JButton btnBienLouable;
    private JButton btnLocataire;
    private JButton btnDocument;
    private JButton btnContratLocation;
    private JLabel titleLab;

    public PageHeaderSkeleton() {
        super();
  
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        btnBienLouable = new JButton("Biens");
        btnLocataire = new JButton("Locataires");
        btnContratLocation = new JButton("Contrats locations");
        btnDocument = new JButton("Documents");

        stylizeButton(btnBienLouable);
        stylizeButton(btnLocataire);
        stylizeButton(btnContratLocation);
        stylizeButton(btnDocument);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5); // Add some padding between components

        gbc.gridx = 0;
        buttonPanel.add(btnBienLouable, gbc);
        gbc.gridx++;
        buttonPanel.add(createSeparator(), gbc);
        gbc.gridx++;
        buttonPanel.add(btnLocataire, gbc);
        gbc.gridx++;
        buttonPanel.add(createSeparator(), gbc);
        gbc.gridx++;
        buttonPanel.add(btnContratLocation, gbc);
        gbc.gridx++;
        buttonPanel.add(createSeparator(), gbc);
        gbc.gridx++;
        buttonPanel.add(btnDocument, gbc);
        gbc.gridx++;
        
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(Box.createHorizontalGlue(), gbc);

        headerPanel.add(buttonPanel, BorderLayout.EAST);
    }

    // Méthode pour styliser les boutons
    private void stylizeButton(JButton button) {
        button.setPreferredSize(new Dimension(ScallingDimension.scaleValue(170), ScallingDimension.scaleValue(75))); 
        button.setFont(new Font("Arial", Font.PLAIN, 18)); // Taille du texte du bouton
        button.setOpaque(false);
        button.setContentAreaFilled(false); // Remove background fill
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        button.setFocusPainted(false);
    }
    
    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(1, ScallingDimension.scaleValue(75)));
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

    public JButton getBtnContratLocation() {
        return btnContratLocation;
    }
    
    public void setTitleHeader(String titleString) {
        if (titleLab == null) {
            titleLab = new JLabel();
            headerPanel.add(titleLab, BorderLayout.CENTER);
        }
        titleLab.setText(titleString);
        titleLab.setFont(new Font("Arial", Font.BOLD, ScallingDimension.scaleValue(28)));
        titleLab.setBorder(BorderFactory.createEmptyBorder(0, ScallingDimension.scaleValue(20), 0, 0));
    }
}