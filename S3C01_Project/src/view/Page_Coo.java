package view;

import javax.swing.*;

import application.Page_principaleController;
import dbConnection.DatabaseConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Page_Coo extends WindowSkeleton {
	
	public JButton connectButton;
	public JTextField usernameField;
	public JPasswordField passwordField;
	
    public Page_Coo() {
    	super();

        // Panneau central pour le formulaire de connexion
        JPanel centerPanel = new JPanel();
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.rowHeights = new int[] {0, 3};
        gbl_centerPanel.columnWidths = new int[] {0, 3};
        centerPanel.setLayout(gbl_centerPanel); // Utilisation de GridBagLayout pour centrer le formulaire
        centerPanel.setBackground(Color.WHITE);

        // Contraintes communes
        Insets insets = new Insets(10, 10, 10, 10); // Marges entre les composants
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        
	    // Panneau interne pour le formulaire de connexion
	    JPanel loginPanel = new JPanel();
	    loginPanel.setLayout(new GridBagLayout()); // Gestion des composants dans le formulaire
	    loginPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Bordure grise
	    loginPanel.setBackground(new Color(245, 245, 245)); // Gris clair
	    
        // Titre "Connexion"
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = insets;
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2; // S'étend sur deux colonnes
        gbcTitle.fill = GridBagConstraints.HORIZONTAL; // Étend horizontalement
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(titleLabel, gbcTitle);
	            
        // Label Identifiant
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        gbcUsernameLabel.insets = insets;
        gbcUsernameLabel.gridx = 0;
        gbcUsernameLabel.gridy = 1;
        gbcUsernameLabel.anchor = GridBagConstraints.WEST; // Aligné à gauche
        JLabel usernameLabel = new JLabel("Identifiant :");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(usernameLabel, gbcUsernameLabel);
        
        // Champ Identifiant
        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.insets = insets;
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 1;
        gbcUsernameField.fill = GridBagConstraints.HORIZONTAL; // Étend horizontalement
        gbcUsernameField.weightx = 1.0; // Étend en largeur
        usernameField = new JTextField();
        loginPanel.add(usernameField, gbcUsernameField);
        
        // Label Mot de passe
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.insets = insets;
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 2;
        gbcPasswordLabel.anchor = GridBagConstraints.WEST; // Aligné à gauche
        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(passwordLabel, gbcPasswordLabel);
        
        // Champ Mot de passe
        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.insets = insets;
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordField.weightx = 1.0;
        passwordField = new JPasswordField();
        loginPanel.add(passwordField, gbcPasswordField);
                                                        
        // Bouton Connecter
        GridBagConstraints gbcConnectButton = new GridBagConstraints();
        gbcConnectButton.insets = insets;
        gbcConnectButton.gridx = 0;
        gbcConnectButton.gridy = 3;
        gbcConnectButton.weightx = 0.5;
        gbcConnectButton.anchor = GridBagConstraints.CENTER;
        connectButton = new JButton("Connecter");
        loginPanel.add(connectButton, gbcConnectButton);
                                                                      
        // Bouton Annuler
        GridBagConstraints gbcCancelButton = new GridBagConstraints();
        gbcCancelButton.insets = insets;
        gbcCancelButton.gridx = 1;
        gbcCancelButton.gridy = 3;
        gbcCancelButton.weightx = 0.5;
        gbcCancelButton.anchor = GridBagConstraints.CENTER;
        JButton cancelButton = new JButton("Annuler");
        loginPanel.add(cancelButton, gbcCancelButton);
                                                                        
        // Ajout du panneau de connexion au centre
        GridBagConstraints gbc_loginPanel = new GridBagConstraints();
        gbc_loginPanel.insets = new Insets(0, 0, 5, 5);
        gbc_loginPanel.gridx = 0;
        gbc_loginPanel.gridy = 0;
        centerPanel.add(loginPanel, gbc_loginPanel);

    }

	public JButton getConnectButton() {
		return connectButton;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	

    
}
