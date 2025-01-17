package utilities;

import javax.swing.JOptionPane;

public class ErrorMessage {
	
	private ErrorMessage() {}
	
	public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "ERREUR", JOptionPane.INFORMATION_MESSAGE);
	}
	public static int confirmationDialog(String message) {
	    return JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
	}
}
