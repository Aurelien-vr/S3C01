package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Page_sousHeaderGB extends Page_sousHeader {

    private static final long serialVersionUID = 1L;

    public Page_sousHeaderGB() {
        super();
        setTitle("sousHeaderGB");
		
		JMenu mnNewMenu = new JMenu("Type de bien");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Appartement");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Garage");
		mnNewMenu.add(mntmNewMenuItem_1);
    }
}
