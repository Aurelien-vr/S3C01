package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestFactureEau {
	
	private FactureEauDAO factureEauDAO;
	private Connection connection;
	private FactureEau factureEau;
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
		factureEauDAO = DAOFactory.createFactureEauDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_eau(partie_fixe, consommation) "
				+ " VALUES(35, 62)";
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
	
	factureEau = new FactureEau(new BigDecimal(35).setScale(2, RoundingMode.DOWN),new BigDecimal(62).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() {
		factureEauDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureEauDAO.findOne(idInsertSetup),factureEau); 
	}
	
	@Test
	public void testInsert() {
		FactureEau eau = new FactureEau(new BigDecimal(45).setScale(2, RoundingMode.DOWN),new BigDecimal(80).setScale(2, RoundingMode.DOWN));
		factureEauDAO.insert(eau);
		assertEquals(eau, factureEauDAO.findOne(eau.getIdFactureEau()));

	}
	
	@Test
	public void testDelete() {
		factureEauDAO.deleteById(idInsertSetup);
		assertNull(factureEauDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<FactureEau> facturesEau = factureEauDAO.findAll();
	    int nombreFactsEauDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Facture_eau";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreFactsEauDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreFactsEauDansLaBase, facturesEau.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, facturesEau.contains(factureEau));
		
	    }
	}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvellePartieFixe = new BigDecimal(45).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleConsommation = new BigDecimal(80).setScale(2, RoundingMode.DOWN);

	    factureEau.setPartieFixe(nouvellePartieFixe);
	    factureEau.setConsommation(nouvelleConsommation);

	    factureEauDAO.update(factureEau);

	    assertEquals(nouvellePartieFixe, factureEau.getPartieFixe());
	    assertEquals(nouvelleConsommation, factureEau.getConsommation());
	}
	
	   @Test
	    public void testUNFactureEau() {
	        String sql = "{ CALL db1_sae.TestUN_FactureEau(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKFactureEau(){
	        String sql = "{ CALL db1_sae.TestCK_FactureEau(?,?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setBigDecimal(1, new BigDecimal(25).setScale(2, RoundingMode.DOWN));
	            callableStatement.setBigDecimal(2, new BigDecimal(40).setScale(2, RoundingMode.DOWN)); 
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }


}
