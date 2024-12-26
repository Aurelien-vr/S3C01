package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class Page_sousHeader extends Page_header {

    private static final long serialVersionUID = 1L;
    protected JTable table;
    private JButton btnAjoutBien; // Bouton déclaré comme variable membre
    protected JMenuBar menuBar;  // Barre de menu

    public Page_sousHeader() {
        super();
        setTitle("sous-header");

        mainPanel.setLayout(new BorderLayout(0, 0));

        // Panel pour le menu
        JPanel menu = new JPanel();
        mainPanel.add(menu, BorderLayout.NORTH);
        menu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        // Barre de menu
        menuBar = new JMenuBar();
        menu.add(menuBar);

        // Bouton "Ajouter un bien"
        btnAjoutBien = new JButton("Ajouter un bien"); // Bouton correctement initialisé ici
        menuBar.add(btnAjoutBien);

        // Tableau au centre
        table = new JTable();
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Panneau de défilement à droite
        JScrollPane scrollPane = new JScrollPane();
        mainPanel.add(scrollPane, BorderLayout.EAST);
    }

    // Getter pour le bouton "Ajouter un bien"
    public JButton getBtnAjoutBien() {
        return btnAjoutBien;
    }
}
