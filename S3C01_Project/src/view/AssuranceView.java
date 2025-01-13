package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class AssuranceView extends TemplateTableView {
    
    private JButton ajoutAssurance = new JButton();

    public AssuranceView() {
        ajoutAssurance = addButtonAssurance();
        
        layeredPane.add(ajoutAssurance, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private JButton addButtonAssurance() {
        JButton ajoutAssurance = new JButton("Ajouter");
        ajoutAssurance.setBackground(new Color(135, 206, 250));
        ajoutAssurance.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
        return ajoutAssurance;
    }

    public JButton getAjoutAssurance() {
        return ajoutAssurance;
    }
}