package utilities;

import javax.swing.JOptionPane;

public class ErrorMessage {
	public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "ERREUR", JOptionPane.INFORMATION_MESSAGE);
	}
	public static int confirmationDialog(String message) {
	    int response = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
	    return response;
	}
}
