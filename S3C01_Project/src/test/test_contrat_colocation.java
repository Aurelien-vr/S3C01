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

public class test_contrat_colocation {
	
	private Contrat_colocationDAO contrat_colocationDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Contrat_colocation contrat_colocation;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		contrat_colocationDAO = DAOFactory.createContrat_colocationDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_colocation(clause_solidarite, part_des_charges) "
				+ " VALUES(true, 450)";
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
	
	contrat_colocation = new Contrat_colocation(true, new BigDecimal(450).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		contrat_colocationDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contrat_colocationDAO.findOne(idInsertSetup),contrat_colocation); 
	}
	
	@Test
	public void testInsert() {
		contrat_colocationDAO.insert(contrat_colocation);
		assertEquals(contrat_colocation, contrat_colocationDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		contrat_colocationDAO.deleteById(idInsertSetup);
		assertNull(contrat_colocationDAO.findOne(idInsertSetup));
		}

}
