package test;
import dbConnection.*;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;

public class test_contrat_location {
	
	private Contrat_locationDAO contrat_locationDAO;
	private Connection connection = DatabaseConnection.getInstance();
	
	@Before
	public void setUp() throws Exception {
		contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_location"
				+ "(Montant_loyer,Date_debut,Date_fin,Modalite_chauffage,Modalite_eau_chaude_sanitaire,Date_versement)"
				+ " VALUES(800, 2023-4-7, 2024-4-7,\"DPE=A, et autre truc\",\"chaudière de 2024\", 0000-05-01)";
		statement = connection.prepareStatement(query);
		statement.executeUpdate();
	}

	@After
	public void tearDown() throws Exception {
		contrat_locationDAO = null;
	}
	
	/*
	@Test
	public void testFindOne() {
		
	}
	 */

}
