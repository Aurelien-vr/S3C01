package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Page_locataire extends JFrame {

    // Ajout du serialVersionUID pour éviter l'avertissement
    private static final long serialVersionUID = 1L;

    // Classe interne pour une bordure arrondie personnalisée
    static class RoundedBorder extends AbstractBorder {
        private final int arcWidth;
        private final int arcHeight;
        private final Color borderColor;

        public RoundedBorder(int arcWidth, int arcHeight, Color borderColor) {
            this.arcWidth = arcWidth;
            this.arcHeight = arcHeight;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(arcHeight / 2, arcWidth / 2, arcHeight / 2, arcWidth / 2);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = arcWidth / 2;
            insets.right = arcWidth / 2;
            insets.top = arcHeight / 2;
            insets.bottom = arcHeight / 2;
            return insets;
        }
    }

    public Page_locataire() {
        // Configuration de la fenêtre principale
        setTitle("Liste des Locataires");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());

        // Bande bleue avec le logo et les outils de sélection
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new GridLayout(1, 5, 10, 0));

        // Ajout du logo
        JLabel logoLabel = new JLabel("LBA", JLabel.CENTER);
        logoLabel.setFont(new Font("Serif", Font.BOLD, 28));
        headerPanel.add(logoLabel);

        // Label pour le tri
        JLabel sortLabel = new JLabel("Type de tri :", JLabel.RIGHT);
        headerPanel.add(sortLabel);

        // ComboBox pour le type de tri
        JComboBox<String> sortTypeComboBox = new JComboBox<>(new String[]{"Locataire"});
        headerPanel.add(sortTypeComboBox);

        // Icône de recherche
        JLabel searchIcon = new JLabel(new ImageIcon("path/to/search.png")); // Remplacer par l'icône
        searchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(searchIcon);

        // Icône de retour
        JLabel backIcon = new JLabel(new ImageIcon("path/to/back.png")); // Remplacer par l'icône
        backIcon.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(backIcon);

        // Ajout de la bande bleue au haut de la fenêtre
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Panneau principal pour les locataires
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Une colonne avec espacement vertical
        mainPanel.setBackground(Color.WHITE);

        // Exemple de données pour les locataires
        String[] locataireData = {
                "Philippe Simon",
                "Sylvie du Ferrand",
                "Chantal Guyon"
        };

        // Ajout des panneaux pour chaque locataire
        for (String locataire : locataireData) {
            JPanel locatairePanel = new JPanel();
            locatairePanel.setLayout(new BorderLayout());
            locatairePanel.setBackground(Color.WHITE);
            locatairePanel.setBorder(new RoundedBorder(20, 20, Color.GRAY)); // Bordure arrondie

            // Label pour l'image
            JLabel imageLabel = new JLabel(new ImageIcon("path/to/profile.png")); // Remplacer par l'icône de profil
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Label pour le nom et prénom avec padding
            JLabel nameLabel = new JLabel(locataire);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            nameLabel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Padding à gauche

            // Ajout des composants au panneau de locataire
            locatairePanel.add(imageLabel, BorderLayout.WEST);
            locatairePanel.add(nameLabel, BorderLayout.CENTER);

            // Ajout du panneau au panneau principal
            mainPanel.add(locatairePanel);
        }

        // Ajout du JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajout du panneau principal au centre de la fenêtre
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Page_locataire());
    }
}
