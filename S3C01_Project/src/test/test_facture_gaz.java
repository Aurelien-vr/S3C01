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
		facture_gazDAO.insert(facture_gaz);
		assertEquals(facture_gaz, facture_gazDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		facture_gazDAO.deleteById(idInsertSetup);
		assertNull(facture_gazDAO.findOne(idInsertSetup));
		}

}
