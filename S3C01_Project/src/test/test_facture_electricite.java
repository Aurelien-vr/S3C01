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

public class test_facture_electricite {
	
	private Facture_electriciteDAO facture_electriciteDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Facture_electricite facture_electricite;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		facture_electriciteDAO = DAOFactory.createFacture_electriciteDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_electricite(compteur_electricite, prix_kw_electricite) "
				+ " VALUES(35, '54/kw')";
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
	
	facture_electricite = new Facture_electricite(new BigDecimal(35).setScale(2, RoundingMode.DOWN),"54/kw");
	
	}
	

	@After
	public void tearDown() throws Exception {
		facture_electriciteDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(facture_electriciteDAO.findOne(idInsertSetup),facture_electricite); 
	}
	
	@Test
	public void testInsert() {
		facture_electriciteDAO.insert(facture_electricite);
		assertEquals(facture_electricite, facture_electriciteDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		facture_electriciteDAO.deleteById(idInsertSetup);
		assertNull(facture_electriciteDAO.findOne(idInsertSetup));
		}

}
