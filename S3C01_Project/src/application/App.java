package application;
import java.sql.Date;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;


/**
 * Classe principale de l'application.
 * Permet de tester l'accès aux données via le DAO (Data Access Object) pour les contrats de location.
 */

public class App {
    /**
     * Point d'entrée principal de l'application.
     */
	
	public static void main(String[] args) {
		Contrat_locationDAO contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		
		/*
		Contrat_location contrat_location = contrat_locationDAO.findOne(1);	
		System.out.println(contrat_location);
		 */
		
		Contrat_location contrat_loc = new Contrat_location();
		contrat_loc.setMontant_loyer(200);
		contrat_loc.setDate_debut(Date.valueOf("2022-12-01"));
		contrat_locationDAO.insert(contrat_loc);		
	}

}
