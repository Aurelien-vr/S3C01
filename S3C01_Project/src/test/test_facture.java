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

public class test_facture {
	
	private FactureDAO factureDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Facture facture;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		factureDAO = DAOFactory.createFactureDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture(type_facture , date_facture , montant_facture, moyen_paiement) "
				+ " VALUES('Payante', '2024-12-12', 50, 'Cheque')";
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
	
	facture = new Facture("Payante", Date.valueOf("2024-12-12"), new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Cheque");
	
	}
	

	@After
	public void tearDown() throws Exception {
		factureDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureDAO.findOne(idInsertSetup),facture); 
	}
	
	@Test
	public void testInsert() {
		factureDAO.insert(facture);
		assertEquals(facture, factureDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		factureDAO.deleteById(idInsertSetup);
		assertNull(factureDAO.findOne(idInsertSetup));
		}

}
