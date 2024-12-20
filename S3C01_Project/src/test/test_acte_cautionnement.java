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

public class test_acte_cautionnement {
	
	private Acte_cautionnementDAO acte_cautionnementDAO;
	private Connection connection;
	private Acte_cautionnement acte_cautionnement;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		acte_cautionnementDAO = DAOFactory.createActe_cautionnementDAO();
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
			//ExceptionStorageHandler.LogException(e, connection);
		}
	
	acte_cautionnement = new Acte_cautionnement(new BigDecimal(800).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		acte_cautionnementDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testInsert() {
		Acte_cautionnement acte = new Acte_cautionnement(new BigDecimal(20).setScale(2, RoundingMode.DOWN));
				acte_cautionnementDAO.insert(acte);
				assertEquals(acte, acte_cautionnementDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testFindOne() {
		assertEquals(acte_cautionnementDAO.findOne(idInsertSetup),acte_cautionnement); 
	}
	
	
	//**
	@Test
	public void testFindAll() {
	    List<Acte_cautionnement> actes = acte_cautionnementDAO.findAll();
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
	        ExceptionStorageHandler.LogException(e, connection);
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
	    assertEquals(true, actes.contains(acte_cautionnement));
	}
	
	@Test
	public void testDelete() {
		acte_cautionnementDAO.deleteById(idInsertSetup);
		assertNull(acte_cautionnementDAO.findOne(idInsertSetup));
		}

	

}
