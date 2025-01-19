package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ChargeView extends TemplateTableView {
    
    private JButton ajoutChargeButton;
    private JToggleButton toggleButton = new JToggleButton("Afficher uniquement les charges actives");

    public ChargeView() {
        super();
        ajoutChargeButton = addButtonCharge();
        layeredPane.add(ajoutChargeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }
    
    private JButton addButtonCharge() {
        ajoutChargeButton = new JButton("Ajouter/modifier charge");
        ajoutChargeButton.setBackground(new Color(135, 206, 250));
        ajoutChargeButton.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
        return ajoutChargeButton;
    }
    
    public JButton getAjoutChargeButton() {
        return ajoutChargeButton;
    }
    
    public JToggleButton getToggleButton() {
        return toggleButton;
    }
}