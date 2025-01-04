package application;

import javax.swing.SwingUtilities;

import controller.Page_CooController;
import dbConnection.DatabaseConnection;

public class App {
    /**
     * Point d'entrée principal de l'application.
     */
    public App() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Page_CooController();
            }
        }); 

        while (!DatabaseConnection.connected) {
            try {
                Thread.sleep(100); // Avoid busy waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
    public static void main(String[] args) {
    	new App();
    }
}