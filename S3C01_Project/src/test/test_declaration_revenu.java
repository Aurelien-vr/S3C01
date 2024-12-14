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

public class test_declaration_revenu {
	
	private Declaration_revenuDAO declaration_revenuDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Declaration_revenu declaration_revenu;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		declaration_revenuDAO = DAOFactory.createDeclaration_revenuDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Declaration_revenu(date_acquisition, locataires, recette_immeuble) "
				+ " VALUES('2008-07-14', 5, 65)";
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
	
	declaration_revenu = new Declaration_revenu(Date.valueOf("2008-07-14"),5,new BigDecimal(65).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		declaration_revenuDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(declaration_revenuDAO.findOne(idInsertSetup),declaration_revenu); 
	}
	
	@Test
	public void testInsert() {
		declaration_revenuDAO.insert(declaration_revenu);
		assertEquals(declaration_revenu, declaration_revenuDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		declaration_revenuDAO.deleteById(idInsertSetup);
		assertNull(declaration_revenuDAO.findOne(idInsertSetup));
		}

}
