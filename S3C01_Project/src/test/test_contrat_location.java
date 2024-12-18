package test;


import org.junit.*;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import application.App;
import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import java.sql.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_contrat_location {
	
	private Contrat_locationDAO contrat_locationDAO;
	private Contrat_location contrat_location;
	Connection connection = null;
	int idInsertSetup;
	
	public test_contrat_location() {}
	
	@Before
	public void setUp() throws Exception {
		PreparedStatement statement;
		String query = null;
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		connection.setAutoCommit(false);
		query = "INSERT INTO db1_sae.Contrat_location(Montant_loyer,Date_debut,Date_fin,Modalite_chauffage,Modalite_eau_chaude_sanitaire,Date_versement) "
				+ " VALUES(800, '2023-4-7', '2024-4-7','DPE=A, et autre truc','chaudiere de 2024', '1000-05-01')";
	try {
		statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		if(statement.executeUpdate()>0) {
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				idInsertSetup = result.getInt(1);
			}
			DatabaseConnection.closeResult(result);
		}
	}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	contrat_location = new Contrat_location(800, Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
			"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
	}
	

	@After
	public void tearDown() throws Exception {
		contrat_locationDAO = null;
		Connection connection = DatabaseConnection.getInstance();
		connection.rollback();
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contrat_locationDAO.findOne(idInsertSetup),contrat_location); 
	}
	
	@Test
	public void testInsert() {
		Contrat_location cont_loc = new Contrat_location(12,Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
				"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
		contrat_locationDAO.insert(cont_loc);
		assertEquals(cont_loc, contrat_locationDAO.findOne(idInsertSetup+1));
	}
	
	@Test
	public void testDelete() {
		contrat_locationDAO.deleteById(idInsertSetup);
		assertNull(contrat_locationDAO.findOne(idInsertSetup));
		}

}
