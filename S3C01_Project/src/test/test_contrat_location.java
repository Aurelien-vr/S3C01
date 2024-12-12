package test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_contrat_location {
	
	private Contrat_locationDAO contrat_locationDAO;
	private Connection connection = DatabaseConnection.getInstance();
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_location(Montant_loyer,Date_debut,Date_fin,Modalite_chauffage,Modalite_eau_chaude_sanitaire,Date_versement) "
				+ " VALUES(800, '2023-4-7', '2024-4-7','DPE=A, et autre truc','chaudiere de 2024', '1000-05-01')";
	try {
		statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		if(statement.executeUpdate()>0) {
			System.out.println("User inserted TEST");
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				idInsertSetup = result.getInt(1);
			}
			DatabaseConnection.closeResult(result);
		}
	}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
	}

	@After
	public void tearDown() throws Exception {
		contrat_locationDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		Contrat_location contrat_location = new Contrat_location(800,
				Date.valueOf("2023-4-7"),
				Date.valueOf("2024-4-7"),
				"DPE=A, et autre truc",
				"chaudiere de 2024",
				Date.valueOf("1000-05-01"));

		assertEquals(contrat_locationDAO.findOne(999),contrat_location); 
	}

}
