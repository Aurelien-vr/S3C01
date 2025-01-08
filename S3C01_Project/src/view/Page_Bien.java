package view;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;

import controller.ScallingDimension;

@SuppressWarnings("serial")
public class Page_Bien extends TableSkeleton{
	    
	JButton ajoutBien;
	
	public Page_Bien() {
		super();
		
		ajoutBien = addButtonBien();
		layeredPane.add(ajoutBien, JLayeredPane.PALETTE_LAYER);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	private JButton addButtonBien() {
		ajoutBien = new JButton("Ajouter");
		ajoutBien.setBackground(new Color(135, 206, 250));
		ajoutBien.setBounds(canvas.getX() + canvas.getWidth() - 220,
                canvas.getY() + canvas.getHeight() - 90, // Adjust the y coordinate to lower the button
                170, 50);
		return ajoutBien;
	}
	
	public JButton getAjoutBienButton() {
		return ajoutBien;
	}
	
	public void fillEditClDialog(int idCl) {
		JDialog editClDialog = new JDialog(this, "Edit");
		editClDialog.setSize(ScallingDimension.scaleValue(300), ScallingDimension.scaleValue(200));
		editClDialog.setLocationRelativeTo(this);
		editClDialog.setLayout(new GridBagLayout());
		
		editClDialog.setVisible(true);
	}
}
