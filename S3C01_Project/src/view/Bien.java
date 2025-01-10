package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;


@SuppressWarnings("serial")
public class Bien extends TemplateTable{
	    
	JButton ajoutBien;
	JDialog editClDialog;
	private JComboBox<String> contratLocationComboBox = new JComboBox<>();
	private JComboBox<String> locataireComboBox = new JComboBox<>();
	private JButton ajouterContratLocationButton;
	private JButton ajouterLocataireButton;
	private JButton modifierButton;
	private JButton deleteButton;
	
	public Bien() {
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
		editClDialog = new JDialog(this, "Edit");
		editClDialog.setBackground(Color.WHITE);
		editClDialog.setSize(500, 300);
		editClDialog.setLocationRelativeTo(this);
		editClDialog.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
		
		// First Column: Delete button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        deleteButton = new JButton("Delete");
        editClDialog.add(deleteButton, gbc);
        
        
        // Second Column: Two combo boxes and a modifier button
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClDialog.add(contratLocationComboBox, gbc);

        gbc.gridy = 1;
        editClDialog.add(locataireComboBox, gbc);

        gbc.gridy = 2;
        modifierButton = new JButton("Modifier");
        editClDialog.add(modifierButton, gbc);

        // Third Column: Two buttons
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ajouterLocataireButton = new JButton("Ajouter Locataire");
        editClDialog.add(ajouterLocataireButton, gbc);

        gbc.gridy = 1;
        ajouterContratLocationButton = new JButton("Ajouter Contrat de Location");
        editClDialog.add(ajouterContratLocationButton, gbc);
		
        if(idCl == -1) {
        	deleteButton.setEnabled(false);
        }
        
		editClDialog.setVisible(true);
	}

	public JDialog getEditClDialog() {
		return editClDialog;
	}

	public JComboBox<String> getContratLocationComboBox() {
		return contratLocationComboBox;
	}

	public JComboBox<String> getLocataireComboBox() {
		return locataireComboBox;
	}
	
	public JButton getAjouterContratLocationButton() {
		return ajouterContratLocationButton;
	}

	public JButton getAjouterLocataireButton() {
		return ajouterLocataireButton;
	}

	public JButton getModifierButton() {
		return modifierButton;
	}
	
	public JButton getDeleteButtonDialog() {
		return this.deleteButton;
	}
}
