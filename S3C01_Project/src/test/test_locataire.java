package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_locataire {
	
	private LocataireDAO locataireDAO;
	private Connection connection;
	private Locataire locataire;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		locataireDAO = DAOFactory.createLocataireDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Locataire(nom, prenom, date_de_naissance ,iban) "
				+ " VALUES('Chlabi', 'Aymen', '2004-08-13', '1234567890')";
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
	
	locataire = new Locataire("Chlabi", "Aymen", Date.valueOf("2004-08-13"), "1234567890");
	
	}
	

	@After
	public void tearDown() throws Exception {
		locataireDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(locataireDAO.findOne(idInsertSetup),locataire); 
	}
	
	@Test
	public void testInsert() {
		Locataire loc = new Locataire("Vincent-Randonnier", "Aurelien", Date.valueOf("2004-11-03"), "0987654321");
		locataireDAO.insert(loc);
		assertEquals(loc, locataireDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		locataireDAO.deleteById(idInsertSetup);
		assertNull(locataireDAO.findOne(idInsertSetup));
		}

}
