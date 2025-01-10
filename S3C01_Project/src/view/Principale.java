package view;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Principale extends TemplateTable{
	
	private JLabel footerlab = new JLabel();

	public Principale() {
		super();
	}
	
	public JLabel getFooterlab() {
		return footerlab;
	}
	
	public void setFooterLabText(String text) {
		footerlab.setText(text);
	}
}
