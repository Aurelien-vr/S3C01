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

public class test_facture_eau {
	
	private Facture_eauDAO facture_eauDAO;
	private Connection connection;
	private Facture_eau facture_eau;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		facture_eauDAO = DAOFactory.createFacture_eauDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_eau(partie_fixe, consommation) "
				+ " VALUES(35, 62)";
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
	
	facture_eau = new Facture_eau(new BigDecimal(35).setScale(2, RoundingMode.DOWN),new BigDecimal(62).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		facture_eauDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(facture_eauDAO.findOne(idInsertSetup),facture_eau); 
	}
	
	@Test
	public void testInsert() {
		facture_eauDAO.insert(facture_eau);
		assertEquals(facture_eau, facture_eauDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		facture_eauDAO.deleteById(idInsertSetup);
		assertNull(facture_eauDAO.findOne(idInsertSetup));
		}

}
