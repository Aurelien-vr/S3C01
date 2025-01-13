package view;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PrincipaleView extends TemplateTableView{
	
	private JLabel footerlab = new JLabel();

	public PrincipaleView() {
		super();
	}
	
	public JLabel getFooterlab() {
		return footerlab;
	}
	
	public void setFooterLabText(String text) {
		footerlab.setText(text);
	}
}
