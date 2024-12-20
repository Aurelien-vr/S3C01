package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public class Page_bienLouable extends Page_header{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page_bienLouable window = new Page_bienLouable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Page_bienLouable() {
		super();
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		mainPanel.add(menu, BorderLayout.NORTH);
		menu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		menu.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Type de bien");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Appartement");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Garage");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JButton btnNewButton = new JButton("Ajouter un bien");
		menuBar.add(btnNewButton);
		
		table = new JTable();
		mainPanel.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.EAST);
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
