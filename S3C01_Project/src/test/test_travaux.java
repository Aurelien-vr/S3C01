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

public class test_travaux {
	
	private TravauxDAO travauxDAO;
	private Connection connection;
	private Travaux travaux;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		travauxDAO = DAOFactory.createTravauxDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Travaux(date_travaux, nature, iban, reduction, montant, montant_non_deductible, reduction_special) " +
	               "VALUES ('2024-08-08','bien','09876543210',33,50,20,3)";

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
	
	travaux = new Travaux(Date.valueOf("2024-08-08"), "bien", "09876543210",new BigDecimal(33).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN), new BigDecimal(20).setScale(2, RoundingMode.DOWN), new BigDecimal(3).setScale(2, RoundingMode.DOWN));

	
	}
	

	@After
	public void tearDown() throws Exception {
		travauxDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(travauxDAO.findOne(idInsertSetup),travaux); 
	}
	
	@Test
	public void testInsert() {
		travauxDAO.insert(travaux);
		assertEquals(travaux, travauxDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		travauxDAO.deleteById(idInsertSetup);
		assertNull(travauxDAO.findOne(idInsertSetup));
		}

}