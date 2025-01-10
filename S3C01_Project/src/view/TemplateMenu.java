package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicMenuBarUI;

import utilities.CustomMenuItemUI;
import utilities.CustomMenuUI;
import utilities.ScallingDimension;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

public class TemplateMenu extends TemplateHeader {

    private static final long serialVersionUID = 1L;

    // Boutons accessibles depuis le contrôleur
    private JButton btnBienLouable;
    private JButton btnLocataire;
    private JButton btnContratLocation;
    private JMenu menuDocument;
    private JMenuBar menuBar;
    
    // Menu items
    private JMenuItem itemAssurance;
    private JMenuItem itemFacture;
    private JMenuItem itemTravaux;
    private JLabel titleLab;

    @SuppressWarnings("serial")
    public TemplateMenu() {
        super();
  
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        btnBienLouable = new JButton("Biens");
        btnLocataire = new JButton("Locataires");
        btnContratLocation = new JButton("Contrats locations");

        stylizeButton(btnBienLouable);
        stylizeButton(btnLocataire);
        stylizeButton(btnContratLocation);

        menuDocument = new JMenu("Documents") {
            @Override
            public void updateUI() {
                setUI(new CustomMenuUI());
                setOpaque(false);
            }
        };
        stylizeMenu(menuDocument);

        // Create menu items and add them to the menu
        itemAssurance = new JMenuItem("Assurance") {
            @Override
            public void updateUI() {
                setUI(new CustomMenuItemUI());
                setOpaque(false);
            }
        };
        itemFacture = new JMenuItem("Facture") {
            @Override
            public void updateUI() {
                setUI(new CustomMenuItemUI());
                setOpaque(false);
            }
        };
        itemTravaux = new JMenuItem("Travaux") {
            @Override
            public void updateUI() {
                setUI(new CustomMenuItemUI());
                setOpaque(false);
            }
        };
        
        stylizeMenuItems(itemAssurance);
        stylizeMenuItems(itemFacture);
        stylizeMenuItems(itemTravaux);

        menuDocument.add(itemAssurance);
        menuDocument.add(itemFacture);
        menuDocument.add(itemTravaux);

        // Create a menu bar and add the menu to it
        menuBar = new JMenuBar() {
            @Override
            public void updateUI() {
                super.updateUI();
                setUI(new BasicMenuBarUI() {
                    @Override
                    public void paint(Graphics g, JComponent c) {
                        // Do nothing to prevent background painting
                    }
                });
                setOpaque(false);
            }
        };
        menuBar.add(menuDocument);

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
        buttonPanel.add(menuBar, gbc);
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
    
    private void stylizeMenuItems(JMenuItem menuItem) {
        menuItem.setPreferredSize(new Dimension(ScallingDimension.scaleValue(170), ScallingDimension.scaleValue(75))); 
        menuItem.setFont(new Font("Arial", Font.PLAIN, 18)); // Taille du texte du bouton
        menuItem.setOpaque(false);
        menuItem.setContentAreaFilled(false); // Remove background fill
        menuItem.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        menuItem.setFocusPainted(false);
    }

    // Méthode pour styliser les menus
    private void stylizeMenu(JMenu menu) {
        menu.setPreferredSize(new Dimension(ScallingDimension.scaleValue(170), ScallingDimension.scaleValue(75))); 
        menu.setFont(new Font("Arial", Font.PLAIN, 18)); // Taille du texte du bouton
        menu.setContentAreaFilled(false); // Remove background fill
        menu.setBackground(new Color(135, 206, 250));
        menu.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        menu.setFocusPainted(false);
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

    public JButton getBtnContratLocation() {
        return btnContratLocation;
    }

    public JMenuItem getItemAssurance() {
        return itemAssurance;
    }

    public JMenuItem getItemFacture() {
        return itemFacture;
    }

    public JMenuItem getItemTravaux() {
        return itemTravaux;
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