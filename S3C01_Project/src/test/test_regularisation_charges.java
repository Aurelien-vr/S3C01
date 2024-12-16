package test;


import org.junit.*;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_regularisation_charges {
	
	private Regularisation_chargesDAO regularisation_chargesDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Regularisation_charges regularisation_charges;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		regularisation_chargesDAO = DAOFactory.createRegularisation_chargesDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Regularisation_charges(date_effet, charge_eau, charge_ordure_menagere, charge_eclairage, provision_pour_charge, indice, entretien) "
				+ " VALUES('2023-09-08', 33, 33,33,33,33, 'Bien')";
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
	
	regularisation_charges = new Regularisation_charges(Date.valueOf("2023-09-08"), new BigDecimal(33).setScale(2, RoundingMode.DOWN), new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN), "Bien");
	
	}
	

	@After
	public void tearDown() throws Exception {
		regularisation_chargesDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(regularisation_chargesDAO.findOne(idInsertSetup),regularisation_charges); 
	}
	
	@Test
	public void testInsert() {
		regularisation_chargesDAO.insert(regularisation_charges);
		assertEquals(regularisation_charges, regularisation_chargesDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		regularisation_chargesDAO.deleteById(idInsertSetup);
		assertNull(regularisation_chargesDAO.findOne(idInsertSetup));
		}

}
