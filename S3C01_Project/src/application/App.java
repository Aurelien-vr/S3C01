package application;
import java.sql.Date;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;


public class App {
	
	public static void main(String[] args) {
		/*
		Contrat_locationDAO contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		Contrat_location contrat_location = contrat_locationDAO.findOne(1);
		Contrat_location contrat_location = contrat_locationDAO.findOne(1);	
		System.out.println(contrat_location);
		Contrat_location contrat_loc = new Contrat_location();
		contrat_loc.setMontant_loyer(22);
		contrat_loc.setDate_debut(Date.valueOf("2022-12-01"));
		contrat_loc.setModalite_chauffage("je pense que c'est ok si je fais va");
		contrat_locationDAO.insert(contrat_loc);
		System.out.println(contrat_loc);
		 */
		
		DatabaseConnection.closeConnection();
	}
}
