package application;
import java.util.List;

import controller.Page_CooController;
import dao.BienDAO;
import dao.DAOFactory;
import dbConnection.DatabaseConnection;
import view.Page_Coo;


public class App {
    /**
     * Point d'entrée principal de l'application.
     */
	public App() {
		
		new Page_CooController();
		
		 while (!DatabaseConnection.connected) {
           try {
               Thread.sleep(100); // Avoid busy waiting
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
		 
		BienDAO bienDAO = DAOFactory.createBienDAO();
		List<List<String>> res = bienDAO.BienStatus();
		System.out.println(res);
				

	}
	public static void main(String[] args) {
		new App();
		
	}
}
