package application;

import javax.swing.SwingUtilities;

import controller.ConnectionController;
import db_connection.DatabaseConnection;

public class App {
    /**
     * Point d'entrée principal de l'application.
     */
    public App() {
        SwingUtilities.invokeLater(ConnectionController::new); 

        while (!DatabaseConnection.connected) {
            try {
                Thread.sleep(100); // Avoid busy waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
  
    public static void main(String[] args) {
    	 Runtime.getRuntime().addShutdownHook(
    			 new Thread(DatabaseConnection::closeConnection));
         new App();
    }
}