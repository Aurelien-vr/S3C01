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

public class TestActeCautionnement {
	
	private ActeCautionnementDAO acteCautionnementDAO;
	private Connection connection;
	private ActeCautionnement acteCautionnement;
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
		acteCautionnementDAO = DAOFactory.createActeCautionnementDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Acte_cautionnement(montant_caution) "
				+ " VALUES(800)";
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
	
	acteCautionnement = new ActeCautionnement(new BigDecimal(800).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() {
		acteCautionnementDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testInsert() {
		ActeCautionnement acte = new ActeCautionnement(new BigDecimal(20).setScale(2, RoundingMode.DOWN));
				acteCautionnementDAO.insert(acte);
				assertEquals(acte, acteCautionnementDAO.findOne(acte.getIdActeCautionnement()));

	}
	
	@Test
	public void testFindOne() {
		assertEquals(acteCautionnementDAO.findOne(idInsertSetup),acteCautionnement); 
	}
	
	
	//**
	@Test
	public void testFindAll() {
	    List<ActeCautionnement> actes = acteCautionnementDAO.findAll();
	    int nombreActesDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Acte_cautionnement";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreActesDansLaBase = result.getInt(1); // Récupère le total d'actes
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
	    }

	    //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
	    assertEquals(nombreActesDansLaBase, actes.size());
	    
	    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
	    assertEquals(true, actes.contains(acteCautionnement));
	}
	
	@Test
	public void testDelete() {
		acteCautionnementDAO.deleteById(idInsertSetup);
		assertNull(acteCautionnementDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauMontant = new BigDecimal(7).setScale(2, RoundingMode.DOWN);

	    acteCautionnement.setIdActeCautionnement(idInsertSetup);
	    acteCautionnement.setMontantCaution(nouveauMontant);

	    acteCautionnementDAO.update(acteCautionnement);


	    assertEquals(nouveauMontant, acteCautionnement.getMontantCaution());
	}
	
	@Test
	public void testCKActeCautionnement(){
	    String sql = "{ CALL db1_sae.TestCK_ActeCautionnement(?) }";

	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	    	
	        callableStatement.setBigDecimal(1, new BigDecimal("1500.00"));
	        callableStatement.execute();
	    } catch (Exception e) {
	        assertEquals("Success", e.getMessage());
	    }
	}
	
	@Test
	public void testFKActeCautionnement(){
	    String sql = "{ CALL db1_sae.TestFK_ActeCautionnement(?) }";
	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setInt(1, 2);
	        callableStatement.execute();
	    } catch (Exception e) {
	        assertEquals("Success", e.getMessage());
	    }
	}
	
	

}
