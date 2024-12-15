package view;

import javax.swing.JOptionPane;

public class ErrorMessage {
	public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "ERREUR", JOptionPane.INFORMATION_MESSAGE);

	}
}
