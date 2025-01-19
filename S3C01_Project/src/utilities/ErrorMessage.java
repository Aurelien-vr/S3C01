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

    public static String optionDialog() {
        // Define the options
        String[] options = {"Create", "Edit"};

        // Show the option dialog
        int choice = JOptionPane.showOptionDialog(
                null, // Parent component (null means the dialog is centered on the screen)
                "Choose an option:", // Message
                "Option Dialog", // Title
                JOptionPane.DEFAULT_OPTION, // Option type
                JOptionPane.QUESTION_MESSAGE, // Message type
                null, // Icon (none)
                options, // Options
                options[0] // Initial value
        );

        // Return the selected option as a string
        if (choice == JOptionPane.YES_OPTION) {
            return "Create";
        } else if (choice == JOptionPane.NO_OPTION) {
            return "Edit";
        } else {
            return null; // No option chosen
        }
    }
}