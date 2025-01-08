package view;

import java.awt.Color;import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

import controller.ScallingDimension;

@SuppressWarnings("serial")
public class Page_ContratLocation extends TableSkeleton {
    
    private JButton ajoutContratLocation;
    private JToggleButton toggleButton = new JToggleButton("Afficher uniquement les contrats de locations actifs");
	public Page_ContratLocation() {
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