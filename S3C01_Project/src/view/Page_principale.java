package view;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import dao.BienDAO;
import dao.DAOFactory;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
import java.awt.Component;


public class Page_principale extends windowSkeleton{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Page_Coo());
	}

	public Page_principale() {
		super();
		
		BienDAO bienDAO = DAOFactory.createBienDAO();
		List<List<String>> listOfListResult = bienDAO.BienStatus();
		
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));	
		JScrollPane scrollPane = new JScrollPane(container);
		
		
		for(List<String> strings : listOfListResult) {
			container.add(Box.createRigidArea(new Dimension(0, 50)));
			cellContentCreation(container,strings);
		}
		
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}

	private void cellContentCreation(JPanel container, List<String> cellContent) {
		JPanel holderBienStatus = new JPanel();
		holderBienStatus.setLayout(new BoxLayout(holderBienStatus, BoxLayout.X_AXIS));
		holderBienStatus.setBorder(new RoundedBorder(20, 20, Color.BLACK));
		
		JPanel locationPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel versementPanel = new JPanel();
		JPanel mois = new JPanel();
		JPanel loyerRetard = new JPanel();
		
		String cityZipcode = cellContent.get(1) + " | " + cellContent.get(2) ;
		String firstLastName = cellContent.get(3) + " " + cellContent.get(4);
		
		createJpanelWithLabel(locationPanel,
				new JLabel(cellContent.get(0)),new JLabel(cityZipcode), new JLabel(firstLastName));
		
		createJpanelWithLabel(datePanel,
				new JLabel(cellContent.get(5)),new JLabel(cellContent.get(6)));
		
		createJpanelWithLabel(versementPanel,
				new JLabel(cellContent.get(7)));
		
		createJpanelWithLabel(mois,
				new JLabel("Mois de loyer en retard"),new JLabel(cellContent.get(8)));
		
		createJpanelWithLabel(loyerRetard,
				new JLabel("Loyer dû"),new JLabel(cellContent.get(9)));
		
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(locationPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(datePanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(versementPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(mois);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(loyerRetard);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));

		
		container.add(holderBienStatus);
	}

	private void createJpanelWithLabel(JPanel panel,JLabel...jLabels) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(JLabel label: jLabels) {
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setFont(new Font(getName(), Font.BOLD, 20));
			panel.add(label);
		}
	}
	
	
}
