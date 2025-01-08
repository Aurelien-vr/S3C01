package view;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Page_Principale extends TableSkeleton{
	
	private JLabel footerlab = new JLabel();

	public Page_Principale() {
		super();
	}
	
	public JLabel getFooterlab() {
		return footerlab;
	}
	
	public void setFooterLabText(String text) {
		footerlab.setText(text);
	}
}
