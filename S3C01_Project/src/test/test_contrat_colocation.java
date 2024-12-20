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

public class test_contrat_colocation {
	
	private Contrat_colocationDAO contrat_colocationDAO;
	private Connection connection;
	private Contrat_colocation contrat_colocation;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		contrat_colocationDAO = DAOFactory.createContrat_colocationDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_colocation(clause_solidarite, part_des_charges) "
				+ " VALUES(true, 450)";
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
	
	contrat_colocation = new Contrat_colocation(true, new BigDecimal(450).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		contrat_colocationDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contrat_colocationDAO.findOne(idInsertSetup),contrat_colocation); 
	}
	
	@Test
	public void testInsert() {
		Contrat_colocation coloc = new Contrat_colocation(true,new BigDecimal(500).setScale(2, RoundingMode.DOWN));
		contrat_colocationDAO.insert(coloc);
		assertEquals(coloc, contrat_colocationDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		contrat_colocationDAO.deleteById(idInsertSetup);
		assertNull(contrat_colocationDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Contrat_colocation> contrats_co = contrat_colocationDAO.findAll();
	    int nombreContratsCoDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Contrat_colocation";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreContratsCoDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreContratsCoDansLaBase, contrats_co.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, contrats_co.contains(contrat_colocation));
		
	    }
	} 

}
