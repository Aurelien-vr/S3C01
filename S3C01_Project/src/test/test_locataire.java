package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_locataire {
	
	private LocataireDAO locataireDAO;
	private Connection connection;
	private Locataire locataire;
	int idInsertSetup;
	private ResultSet result;
	
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
			result = statement.getGeneratedKeys();
			if(result.next()) {
				idInsertSetup = result.getInt(1);
			}
		}
	}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	locataire = new Locataire("Chlabi", "Aymen", Date.valueOf("2004-08-13"), "1234567890");
	
	}
	

	@After
	public void tearDown() throws Exception {
		locataireDAO = null;
		Connection connection = DatabaseConnection.getInstance();
		DatabaseConnection.closeResult(result);
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
	
	@Test
	public void testFindAll() {
	    List<Locataire> locs = locataireDAO.findAll();
	    int nombreLocsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Locataire";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreLocsDansLaBase = result.getInt(1); 
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	      //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
		    assertEquals(nombreLocsDansLaBase, locs.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, locs.contains(locataire));
		}
	    
	} 
	
	//@Test
	//public void testCreateEntities() throws SQLException {
	  //  if (result != null && result.next()) { 
	    //    String nom = result.getString("Nom");
	      //  String prenom = result.getString("Prenom");
	        //Date date_de_naissance = result.getDate("Date_de_naissance");
	        //String iban = result.getString("IBAN");
	        
	        //Locataire loc = locataireDAO.createEntities(result);
	        //assertEquals(loc.getNom(), nom);
	        //assertEquals(loc.getPrenom(), prenom);
	        //assertEquals(loc.getDate_de_naissance(), date_de_naissance);
	        //assertEquals(loc.getIban(), iban);
	    //} else {
	      //  Assert.fail("ResultSet is empty or null");
	    //}
	//}


}
