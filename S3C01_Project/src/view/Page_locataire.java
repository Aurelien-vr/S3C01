package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class Page_locataire extends TableSkeleton {

    private JButton ajoutLocataireButton;
    private JToggleButton toggleButton = new JToggleButton("Afficher uniquement les locataires actifs");

    public Page_locataire() {
        super();

        ajoutLocataireButton = addButtonLocataire();
        layeredPane.add(ajoutLocataireButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private JButton addButtonLocataire() {
        ajoutLocataireButton = new JButton("Ajouter Locataire");
        ajoutLocataireButton.setBackground(new Color(135, 206, 250));
        ajoutLocataireButton.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
        return ajoutLocataireButton;
    }

    public JButton getAjoutLocataireButton() {
        return ajoutLocataireButton;
    }

    public JToggleButton getToggleButton() {
        return toggleButton;
    }

}