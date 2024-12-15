package view;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
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
import java.awt.Component;


public class Page_principale extends windowSkeleton{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Page_principale());
	}

	public Page_principale() {
		super();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));	
		JScrollPane scrollPane = new JScrollPane(container);
		
		
		for(int i = 0; i<15 ; i ++) {
			container.add(Box.createRigidArea(new Dimension(0, 50)));
			cellContentCreation(container);
		}
		
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}

	private void cellContentCreation(JPanel container) {
		JPanel holderBienStatus = new JPanel();
		holderBienStatus.setLayout(new BoxLayout(holderBienStatus, BoxLayout.X_AXIS));
		holderBienStatus.setBorder(new RoundedBorder(20, 20, Color.BLACK));
		JPanel locationPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel statusPanel = new JPanel();
		
		createJpanelWithLabel(locationPanel,new JLabel("New Label"),new JLabel("New Label"));
		createJpanelWithLabel(datePanel,new JLabel("New Label"),new JLabel("New Label"));
		createJpanelWithLabel(statusPanel,new JLabel("New Label"));
		
		
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(locationPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(datePanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(statusPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));

		
		container.add(holderBienStatus);
	}

	private void createJpanelWithLabel(JPanel panel,JLabel...jLabels) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(JLabel label: jLabels) {
			label.setFont(new Font(getName(), Font.BOLD, 30));
			panel.add(label);
		}
	}
	
	
}
