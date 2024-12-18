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

public class test_solde_de_tout_compte {
	
	private Solde_de_tout_compteDAO solde_de_tout_compteDAO;
	private Connection connection;
	private Solde_de_tout_compte solde_de_tout_compte;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		solde_de_tout_compteDAO = DAOFactory.createSolde_de_tout_compteDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Solde_de_tout_compte(reste_a_devoir, provision_pour_charges, caution) " +
	               "VALUES (20,30,50)";

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
	
	solde_de_tout_compte = new Solde_de_tout_compte(new BigDecimal(20).setScale(2, RoundingMode.DOWN), new BigDecimal(30).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN));

	
	}
	

	@After
	public void tearDown() throws Exception {
		solde_de_tout_compteDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(solde_de_tout_compteDAO.findOne(idInsertSetup),solde_de_tout_compte); 
	}
	
	@Test
	public void testInsert() {
		Solde_de_tout_compte solde = new Solde_de_tout_compte(new BigDecimal(200).setScale(2, RoundingMode.DOWN), new BigDecimal(300).setScale(2, RoundingMode.DOWN), new BigDecimal(500).setScale(2, RoundingMode.DOWN));
		solde_de_tout_compteDAO.insert(solde);
		assertEquals(solde, solde_de_tout_compteDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		solde_de_tout_compteDAO.deleteById(idInsertSetup);
		assertNull(solde_de_tout_compteDAO.findOne(idInsertSetup));
		}

}