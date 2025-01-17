package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestFactureGaz {
	
	private FactureGazDAO factureGazDAO;
	private Connection connection;
	private FactureGaz factureGaz;
	int idInsertSetup;
	
	@Before
	public void setUp() {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			App.main(null);
			connection = DatabaseConnection.getInstance();
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		factureGazDAO = DAOFactory.createFactureGazDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_gaz(consommation_m3, prix_m3_gaz)"
				+ " VALUES(35, '5/m3')";
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	factureGaz = new FactureGaz(new BigDecimal(35).setScale(2, RoundingMode.DOWN),"5/m3");
	
	}
	

	@After
	public void tearDown(){
		factureGazDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureGazDAO.findOne(idInsertSetup),factureGaz); 
	}
	
	@Test
	public void testInsert() {
		FactureGaz gaz = new FactureGaz(new BigDecimal(50).setScale(2, RoundingMode.DOWN),"12/m3");
		factureGazDAO.insert(gaz);
		assertEquals(gaz, factureGazDAO.findOne(gaz.getIdFactureGaz()));

	}
	
	@Test
	public void testDelete() {
		factureGazDAO.deleteById(idInsertSetup);
		assertNull(factureGazDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<FactureGaz> factsgaz = factureGazDAO.findAll();
	    int nombreFactsGazDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Facture_gaz";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreFactsGazDansLaBase = result.getInt(1); 
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	      //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
		    assertEquals(nombreFactsGazDansLaBase, factsgaz.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, factsgaz.contains(factureGaz));
	    }
	} 
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvelleConsommation = new BigDecimal(50).setScale(2, RoundingMode.DOWN);
	    String nouveauPrixM3Gaz = "12/m3";

	    factureGaz.setConsommationM3(nouvelleConsommation);
	    factureGaz.setPrixM3Gaz(nouveauPrixM3Gaz);

	    factureGazDAO.update(factureGaz);

	    assertEquals(nouvelleConsommation, factureGaz.getConsommationM3());
	    assertEquals(nouveauPrixM3Gaz, factureGaz.getPrixM3Gaz());
	}
	
	@Test
	public void testUNFactureGaz(){
	    String sql = "{ CALL db1_sae.TestUN_FactureGaz(?) }";
	    String existingReference = "5"; 

	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setString(1, existingReference);
	        callableStatement.execute();
	        
	        fail("Exception attendue, mais non lancée");
	    } catch (SQLException e) {
	        assertEquals("Erreur : Reference_facture déjà utilisée (viol de contrainte UNIQUE).", e.getMessage());
	    }
	}

	   
	   @Test
	    public void testCKFactureGaz(){
	        String sql = "{ CALL db1_sae.TestCK_FactureGaz(?,?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setBigDecimal(1, new BigDecimal(25).setScale(2, RoundingMode.DOWN));
	            callableStatement.setBigDecimal(2, new BigDecimal(40).setScale(2, RoundingMode.DOWN)); 
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }


}
