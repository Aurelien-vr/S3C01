package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class Page_bienlouable extends JFrame {

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

    public Page_bienlouable() {
        // Configuration de la fenêtre principale
        setTitle("Liste des Biens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());

        // Bande bleue avec le logo et les outils de sélection
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new GridLayout(1, 6, 10, 0)); // 6 colonnes avec espace horizontal

        // Ajout du logo
        JLabel logoLabel = new JLabel("LBM"); // Remplace par un logo réel si nécessaire
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(logoLabel);

        // Label pour "Type de tri"
        JLabel label = new JLabel("Type de tri :");
        headerPanel.add(label);

        // ComboBox pour le type de tri
        JComboBox<String> sortTypeComboBox = new JComboBox<>(new String[]{"Bien louable", "Locataire", "Immeuble"});
        headerPanel.add(sortTypeComboBox);

        // ComboBox pour la catégorie (appartement ou garage)
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Appartement", "Garage"});
        headerPanel.add(categoryComboBox);

        // Icône de recherche (placeholder pour l'icône réelle)
        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(searchIcon);

        // Icône de retour (placeholder pour l'icône réelle)
        JLabel backIcon = new JLabel("↩");
        backIcon.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(backIcon);

        // Ajout de la bande bleue en haut
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Panneau principal avec le JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Une seule colonne, espace vertical entre les éléments
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Exemple de données pour les biens
        String[][] propertyData = {
            {"31000 Toulouse", "44, impasse Besnard"},
            {"31000 Toulouse", "50, impasse de Grondin"},
            {"31000 Toulouse", "880, place de Clement"}
        };

        // Ajout de panneaux pour chaque bien
        for (String[] property : propertyData) {
            JPanel propertyPanel = new JPanel();
            propertyPanel.setLayout(new BorderLayout(10, 10));
            propertyPanel.setBorder(new RoundedBorder(20, 20, Color.BLACK)); // Bordure arrondie appliquée au panneau
            propertyPanel.setBackground(Color.WHITE);

            // Label pour l'image
            JLabel imageLabel = new JLabel("Image");
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Placeholder pour l'image

            // Labels pour les informations textuelles
            JLabel regionLabel = new JLabel(property[0]);
            JLabel addressLabel = new JLabel(property[1]);

            // Panneau pour le texte
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new GridLayout(2, 1)); // Deux lignes
            textPanel.add(regionLabel);
            textPanel.add(addressLabel);

            // Ajout des composants au panneau de propriété
            propertyPanel.add(imageLabel, BorderLayout.WEST);
            propertyPanel.add(textPanel, BorderLayout.CENTER);

            // Ajout au panneau principal
            mainPanel.add(propertyPanel);
        }

        // Ajout du JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER); // Ajout au centre de la fenêtre

        // Rendre la fenêtre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Page_bienlouable::new);
    }
}
