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

public class TestFactureElectricite {
	
	private FactureElectriciteDAO factureElectriciteDAO;
	private Connection connection;
	private FactureElectricite factureElectricite;
	int idInsertSetup;
	
	@Before
	public void setUp(){
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
		factureElectriciteDAO = DAOFactory.createFactureElectriciteDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture_electricite(compteur_electricite, prix_kw_electricite) "
				+ " VALUES(35, '54/kw')";
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
	
	factureElectricite = new FactureElectricite(new BigDecimal(35).setScale(2, RoundingMode.DOWN),"54/kw");
	
	}
	

	@After
	public void tearDown(){
		factureElectriciteDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureElectriciteDAO.findOne(idInsertSetup),factureElectricite); 
	}
	
	@Test
	public void testInsert() {
		FactureElectricite elec = new FactureElectricite(new BigDecimal(50).setScale(2, RoundingMode.DOWN),"12/kw");
		factureElectriciteDAO.insert(elec);
		assertEquals(elec, factureElectriciteDAO.findOne(elec.getIdFactureElectricite()));

	}
	
	@Test
	public void testDelete() {
		factureElectriciteDAO.deleteById(idInsertSetup);
		assertNull(factureElectriciteDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<FactureElectricite> facturesElectricite = factureElectriciteDAO.findAll();
	    int nombreFactsElecDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Facture_electricite";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreFactsElecDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreFactsElecDansLaBase, facturesElectricite.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, facturesElectricite.contains(factureElectricite));
		
	    }
	} 
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauCompteurElectricite = new BigDecimal(60).setScale(2, RoundingMode.DOWN);
	    String nouveauPrixKwElectricite = "15/kw";

	    factureElectricite.setCompteurElectricite(nouveauCompteurElectricite);
	    factureElectricite.setPrixKwElectricite(nouveauPrixKwElectricite);

	    factureElectriciteDAO.update(factureElectricite);
	    
	    
	    assertEquals(nouveauCompteurElectricite, factureElectricite.getCompteurElectricite());
	    assertEquals(nouveauPrixKwElectricite, factureElectricite.getPrixKwElectricite());
	}
	
	   @Test
	    public void testUNFactureElec() {
	        String sql = "{ CALL db1_sae.TestUN_FactureElectricite(?) }";
	        String existingReference = "5"; 
	        
	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setString(1, existingReference);
	            callableStatement.execute();
	            fail("Exception attendue, mais non lancée");

	        } catch (Exception e) {
	            
	        	assertEquals("Erreur : Reference_facture déjà utilisée (viol de contrainte UNIQUE).", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKFactureElec(){
	        String sql = "{ CALL db1_sae.TestCK_FactureElectricite(?,?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setBigDecimal(1, new BigDecimal(25).setScale(2, RoundingMode.DOWN));
	            callableStatement.setBigDecimal(2, new BigDecimal(40).setScale(2, RoundingMode.DOWN)); 
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }


}
