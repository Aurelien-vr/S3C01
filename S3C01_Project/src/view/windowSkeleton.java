package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dbConnection.DatabaseConnection;

public class windowSkeleton extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public windowSkeleton() {
		// Configuration de la fenêtre principale
        setTitle("Page principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        // Utilisation de BorderLayout pour structurer l'interface
        getContentPane().setLayout(new BorderLayout());
				
		JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250)); // Bleu clair
        headerPanel.setLayout(new BorderLayout()); // Layout pour gérer le logo
        headerPanel.setPreferredSize(new Dimension(700, 100)); // Hauteur fixe

        JButton logoLabel = new JButton(new ImageIcon(getClass().getResource("/ressources/logoDBM.png")));
        logoLabel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DatabaseConnection.connected) {
					new Page_principale();
				}
			}
		});
        setTransparencyButton(logoLabel);
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 10)); // Marge autour du logo
        headerPanel.add(logoLabel, BorderLayout.WEST); // Ajout à gauche du header
        getContentPane().add(headerPanel, BorderLayout.NORTH); // Positionner la bande bleue en haut
	}

	private static void setTransparencyButton(JButton button) {
		button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPressedIcon(null);
        button.setOpaque(false);
	}

}
