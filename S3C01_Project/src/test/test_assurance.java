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

public class test_assurance {
	
	private AssuranceDAO assuranceDAO;
	private Connection connection;
	private Assurance assurance;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
	    connection.setAutoCommit(false);
	    assuranceDAO = DAOFactory.createAssuranceDAO();
	    PreparedStatement statement = null;
	    String query = "INSERT INTO db1_sae.Assurance(prime, taux_augmentation, protection_juridique) "
	                 + "VALUES(80, 5, 2)";
	    try {
	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        if (statement.executeUpdate() > 0) {
	            ResultSet result = statement.getGeneratedKeys();
	            if (result.next()) {
	                idInsertSetup = result.getInt(1); // Récupération de l'id
	            }
	            DatabaseConnection.closeResult(result);
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	    }

	    // Met à jour l'objet assurance avec le bon id
	    assurance = new Assurance(
	                              new BigDecimal(80).setScale(2, RoundingMode.DOWN), 
	                              new BigDecimal(5).setScale(2, RoundingMode.DOWN), 
	                              new BigDecimal(2).setScale(2, RoundingMode.DOWN));
	}

	@After
	public void tearDown() throws Exception {
	    assuranceDAO = null;
	    connection.rollback();
	}

	@Test
	public void testFindOne() {
	    assertEquals(assurance, assuranceDAO.findOne(idInsertSetup)); 
	}

	@Test
	public void testInsert() {
		Assurance ass = new Assurance(new BigDecimal(20).setScale(2, RoundingMode.DOWN), 
					                new BigDecimal(4.3).setScale(2, RoundingMode.DOWN), 
					                new BigDecimal(1).setScale(2, RoundingMode.DOWN));
	    assuranceDAO.insert(ass);
	    assertEquals(ass, assuranceDAO.findOne(idInsertSetup+1));
	}

	@Test
	public void testDelete() {
	    assuranceDAO.deleteById(idInsertSetup);
	    assertNull(assuranceDAO.findOne(idInsertSetup));
	}
	
	@Test
	public void testFindAll() {
	    List<Assurance> ass = assuranceDAO.findAll();
	    int nombreAssDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Assurance";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreAssDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreAssDansLaBase, ass.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, ass.contains(assurance));
	    }
	} 
}
