package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Page_Travaux extends TableSkeleton{
	
	JButton ajoutTravaux = new JButton();
	
	public Page_Travaux() {
		super();
		
	    ajoutTravaux = addButtonTravaux();
        
        layeredPane.add(ajoutTravaux, JLayeredPane.PALETTE_LAYER); // Add to a higher layer
        layeredPane.revalidate();
        layeredPane.repaint();
	}

	private JButton addButtonTravaux() {
		JButton ajoutTravaux = new JButton("Ajouter");
	    ajoutTravaux.setBackground(new Color(135, 206, 250));
	    ajoutTravaux.setBounds(canvas.getX()+ canvas.getWidth()-220,
        		canvas.getX()+ canvas.getHeight()-270,
        		170, 50);
		return ajoutTravaux;
	}	

	public JButton getAjoutTravaux() {
		return ajoutTravaux;
	}
}
