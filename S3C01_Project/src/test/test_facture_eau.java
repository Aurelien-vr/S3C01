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

public class test_facture_eau {
	
	private Facture_eauDAO facture_eauDAO;
	private Connection connection;
	private Facture_eau facture_eau;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		facture_eauDAO = DAOFactory.createFacture_eauDAO();
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
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	facture_eau = new Facture_eau(new BigDecimal(35).setScale(2, RoundingMode.DOWN),new BigDecimal(62).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		facture_eauDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(facture_eauDAO.findOne(idInsertSetup),facture_eau); 
	}
	
	@Test
	public void testInsert() {
		Facture_eau eau = new Facture_eau(new BigDecimal(45).setScale(2, RoundingMode.DOWN),new BigDecimal(80).setScale(2, RoundingMode.DOWN));
		facture_eauDAO.insert(eau);
		assertEquals(eau, facture_eauDAO.findOne(eau.getId_facture_eau()));

	}
	
	@Test
	public void testDelete() {
		facture_eauDAO.deleteById(idInsertSetup);
		assertNull(facture_eauDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Facture_eau> facts_eau = facture_eauDAO.findAll();
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
	        ExceptionStorageHandler.LogException(e, connection);
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
		    assertEquals(nombreFactsEauDansLaBase, facts_eau.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, facts_eau.contains(facture_eau));
		
	    }
	}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvellePartieFixe = new BigDecimal(45).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleConsommation = new BigDecimal(80).setScale(2, RoundingMode.DOWN);

	    facture_eau.setPartie_fixe(nouvellePartieFixe);
	    facture_eau.setConsommation(nouvelleConsommation);

	    facture_eauDAO.update(facture_eau);

	    assertEquals(nouvellePartieFixe, facture_eau.getPartie_fixe());
	    assertEquals(nouvelleConsommation, facture_eau.getConsommation());
	}
	
	   @Test
	    public void testUNFactureEau() throws Exception {
	        String sql = "{ CALL db1_sae.TestUN_FactureEau(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKFactureEau() throws Exception {
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
