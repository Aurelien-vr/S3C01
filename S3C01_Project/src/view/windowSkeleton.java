package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class windowSkeleton extends JFrame {

    private static final long serialVersionUID = 1L;
    protected JPanel mainPanel;
    protected JPanel headerPanel;

    public windowSkeleton() {
        // Configuration de la fenêtre principale
        setTitle("skeleton");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        getContentPane().setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(700, 100));

        // Vérification et chargement de l'icône
        java.net.URL logo = windowSkeleton.class.getResource("/ressources/logoDBM.png");
        JButton logoLabel = new JButton(new ImageIcon(logo));
        setTransparencyButton(logoLabel);
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        getContentPane().add(headerPanel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private static void setTransparencyButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}
