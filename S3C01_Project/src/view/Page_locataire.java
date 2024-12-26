package view;

import javax.swing.*;
import java.awt.BorderLayout;

public class Page_locataire extends Page_sousHeader {

    private static final long serialVersionUID = 1L;

    public Page_locataire() {
    	super();

    	JScrollPane scrollPane = new JScrollPane();
    	mainPanel.add(scrollPane, BorderLayout.EAST);
    	}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Page_locataire pageLocataire = new Page_locataire();
            pageLocataire.setSize(800, 600); // Ajuster la taille de la fenêtre
            pageLocataire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pageLocataire.setVisible(true);
        });
    }
}
