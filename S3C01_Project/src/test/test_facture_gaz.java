package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_facture_gaz {
	
	private Facture_gazDAO facture_gazDAO;
	private Connection connection;
	private Facture_gaz facture_gaz;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		facture_gazDAO = DAOFactory.createFacture_gazDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_gaz(consommation_m3, prix_m3_gaz)"
				+ " VALUES(35, '5/m3')";
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
	
	facture_gaz = new Facture_gaz(new BigDecimal(35).setScale(2, RoundingMode.DOWN),"5/m3");
	
	}
	

	@After
	public void tearDown() throws Exception {
		facture_gazDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(facture_gazDAO.findOne(idInsertSetup),facture_gaz); 
	}
	
	@Test
	public void testInsert() {
		Facture_gaz gaz = new Facture_gaz(new BigDecimal(50).setScale(2, RoundingMode.DOWN),"12/m3");
		facture_gazDAO.insert(gaz);
		assertEquals(gaz, facture_gazDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		facture_gazDAO.deleteById(idInsertSetup);
		assertNull(facture_gazDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Facture_gaz> factsgaz = facture_gazDAO.findAll();
	    int nombreFactsGazDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Facture_gaz";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreFactsGazDansLaBase = result.getInt(1); 
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	} 

}
