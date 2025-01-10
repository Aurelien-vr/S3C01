package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.ScallingDimension;

@SuppressWarnings("serial")
public class TemplateAjout extends TemplateMenu{
	
	private JPanel contentPanel = new JPanel();
	private JPanel topFiller = new JPanel();
	private JPanel bottomFiller = new JPanel();
	private JPanel leftFiller = new JPanel();
	private JPanel rightFiller = new JPanel();
	protected JPanel area = new JPanel();
	private JLabel titleLabel = new JLabel();
	
	private JPanel inerLeftFiller= new JPanel();	
	private JPanel inerRightFiller = new JPanel();	
	
	public TemplateAjout() {
		super();
		JPanel titlePanel = new JPanel();
		
		contentPanel.setLayout(new BorderLayout());
		createFiller();
		area.setBorder(BorderFactory.createLineBorder(Color.black));
		area.setLayout(new BorderLayout());
		
		GridBagConstraints gbc = propertyTitlePanel(titlePanel);
		titlePanel.add(titleLabel,gbc);
		
		titleLabel.setFont(new Font(titlePanel.getFont().getName(),Font.PLAIN,ScallingDimension.scaleValue(30)));
		area.add(titlePanel, BorderLayout.NORTH);
		
		
		contentPanel.add(area);
		getContentPane().add(contentPanel, BorderLayout.CENTER);		
	}

	private GridBagConstraints propertyTitlePanel(JPanel titlePanel) {
		titlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(ScallingDimension.scaleValue(25),0,0,0);
		return gbc;
	}

	private void createFiller() {
		leftFiller.add(Box.createHorizontalStrut(180));
		contentPanel.add(leftFiller, BorderLayout.WEST);
		rightFiller.add(Box.createHorizontalStrut(180));
		contentPanel.add(rightFiller, BorderLayout.EAST);
		topFiller.add(Box.createVerticalStrut(80));
		contentPanel.add(topFiller, BorderLayout.NORTH);
		bottomFiller.add(Box.createVerticalStrut(80));
		contentPanel.add(bottomFiller, BorderLayout.SOUTH);
		
		inerLeftFiller.add(Box.createHorizontalStrut(90));
		area.add(inerLeftFiller, BorderLayout.WEST);
		inerLeftFiller.add(Box.createHorizontalStrut(90));
		area.add(inerRightFiller, BorderLayout.EAST);
	}
	
	protected void changeLabel(String text) {
		titleLabel.setText(text);
	}
}
