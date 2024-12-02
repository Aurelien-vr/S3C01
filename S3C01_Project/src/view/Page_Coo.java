package view;

import javax.swing.*;
import java.awt.*;

public class Page_Coo extends JFrame {

    public Page_Coo() {
        // Configuration de la fenêtre principale
        setTitle("Page de Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        // Utilisation de BorderLayout pour structurer l'interface
        getContentPane().setLayout(new BorderLayout());

        // Bande bleue avec logo
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250)); // Bleu clair
        headerPanel.setLayout(new BorderLayout()); // Layout pour gérer le logo
        headerPanel.setPreferredSize(new Dimension(800, 100)); // Hauteur fixe

        JLabel logoLabel = new JLabel(new ImageIcon("C:\\Users\\Lionel\\Pictures\\SAE AN2\\ok-celui-la.png")); // Remplace par le chemin de ton image
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marge autour du logo
        headerPanel.add(logoLabel, BorderLayout.WEST); // Ajout à gauche du header

        getContentPane().add(headerPanel, BorderLayout.NORTH); // Positionner la bande bleue en haut

        // Panneau central pour le formulaire de connexion
        JPanel centerPanel = new JPanel();
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.rowHeights = new int[] {3};
        gbl_centerPanel.columnWidths = new int[] {3};
        centerPanel.setLayout(gbl_centerPanel); // Utilisation de GridBagLayout pour centrer le formulaire
        centerPanel.setBackground(Color.WHITE);

        // Panneau interne pour le formulaire de connexion
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout()); // Gestion des composants dans le formulaire
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Bordure grise
        loginPanel.setBackground(new Color(245, 245, 245)); // Gris clair

        // Contraintes communes
        Insets insets = new Insets(10, 10, 10, 10); // Marges entre les composants

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
        JTextField usernameField = new JTextField();
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
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField, gbcPasswordField);

        // Bouton Connecter
        GridBagConstraints gbcConnectButton = new GridBagConstraints();
        gbcConnectButton.insets = insets;
        gbcConnectButton.gridx = 0;
        gbcConnectButton.gridy = 3;
        gbcConnectButton.weightx = 0.5;
        gbcConnectButton.anchor = GridBagConstraints.CENTER;
        JButton connectButton = new JButton("Connecter");
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
        centerPanel.add(loginPanel);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Page_Coo());
    }
}
