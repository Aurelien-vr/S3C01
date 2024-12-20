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
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_bien {
	
	private BienDAO bienDAO;
	private Connection connection;
	private Bien bien;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
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
		Bien bi = new Bien(2, "13 rue du treize", "Toulouse", "31000", new BigDecimal(400).setScale(2, RoundingMode.DOWN), 7, false, "armoire, lit", "douche, cuisine", true);
		bienDAO.insert(bi);
		assertEquals(bi, bienDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		bienDAO.deleteById(idInsertSetup);
		assertNull(bienDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Bien> biens = bienDAO.findAll();
	    int nombreBiensDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Bien";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreBiensDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreBiensDansLaBase, biens.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, biens.contains(bien));
	    }
	} 

}