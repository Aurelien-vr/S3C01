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

public class test_bien {
	
	private BienDAO bienDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Bien bien;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		bienDAO = DAOFactory.createBienDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Bien(etage, adresse, ville, code_postal, superficie, nombre_de_piece, meuble, accessoire_prive, accessoire_commun, est_garage) " +
	               "VALUES (4, '33 rue du corbeau', 'Toulouse', '31000', 330, 5, true, 'canapé, lit', 'porte, cuisine', false)";

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
	
	bien = new Bien(4, "33 rue du corbeau", "Toulouse", "31000", new BigDecimal(330).setScale(2, RoundingMode.DOWN), 5, true, "canapé, lit", "porte, cuisine", false);

	
	}
	

	@After
	public void tearDown() throws Exception {
		bienDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(bienDAO.findOne(idInsertSetup),bien); 
	}
	
	@Test
	public void testInsert() {
		bienDAO.insert(bien);
		assertEquals(bien, bienDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		bienDAO.deleteById(idInsertSetup);
		assertNull(bienDAO.findOne(idInsertSetup));
		}

}