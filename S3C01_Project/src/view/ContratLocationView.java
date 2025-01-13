package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ContratLocationView extends TemplateTableView {
    
    private JButton ajoutContratLocation;
    private JToggleButton toggleButton = new JToggleButton("Afficher uniquement les contrats de locations actifs");
	public ContratLocationView() {
        super();
        ajoutContratLocation = addButtonContratLocation();
        layeredPane.add(ajoutContratLocation, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }
    

	private JButton addButtonContratLocation() {
        ajoutContratLocation = new JButton("Ajouter Contrat");
        ajoutContratLocation.setBackground(new Color(135, 206, 250));
        ajoutContratLocation.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
        return ajoutContratLocation;
    }
    
	
    public JButton getAjoutContratLocationButton() {
        return ajoutContratLocation;
    }
    
    public JToggleButton getToggleButton() {
		return toggleButton;
	}
    
}