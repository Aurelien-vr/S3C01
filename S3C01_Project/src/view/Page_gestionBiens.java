package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Page_gestionBiens extends Page_sousHeader{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            Page_gestionBiens pageGestionBiens = new Page_gestionBiens();
            pageGestionBiens.setSize(800, 600); // Ajuster la taille de la fenêtre
            pageGestionBiens.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pageGestionBiens.setVisible(true);
        });
	}

	/**
	 * Create the application.
	 */
	public Page_gestionBiens() {
		super();
		
		JMenu mnNewMenu = new JMenu("Type de bien");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Appartement");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Garage");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
