package application;
import java.sql.Date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;
import test.testRunner;
import test.test_contrat_location;
import view.Page_Coo;


/**
 * Classe principale de l'application.
 * Permet de tester l'accès aux données via le DAO (Data Access Object) pour les contrats de location.
 */

public class App {
    /**
     * Point d'entrée principal de l'application.
     */
	public App() {
		new Page_Coo();
		
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
