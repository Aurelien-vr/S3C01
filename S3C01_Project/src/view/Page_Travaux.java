package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Page_Travaux extends TableSkeleton{
	
	JButton ajoutTravaux;
	
	public Page_Travaux() {
		super();
		
	    ajoutTravaux = addButtonTravaux();
        
        layeredPane.add(ajoutTravaux, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
        
        
	}

	private JButton addButtonTravaux() {
		ajoutTravaux = new JButton("Ajouter");
	    ajoutTravaux.setBackground(new Color(135, 206, 250));
	    ajoutTravaux.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
		return ajoutTravaux;
	}
	
	

	public JButton getButtonAjoutTravaux() {
		return ajoutTravaux;
	}
}
