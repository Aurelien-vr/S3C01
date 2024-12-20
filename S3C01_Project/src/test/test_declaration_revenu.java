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

public class test_declaration_revenu {
	
	private Declaration_revenuDAO declaration_revenuDAO;
	private Connection connection;
	private Declaration_revenu declaration_revenu;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		declaration_revenuDAO = DAOFactory.createDeclaration_revenuDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Declaration_revenu(date_acquisition, locataires, recette_immeuble) "
				+ " VALUES('2008-07-14', 5, 65)";
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
	
	declaration_revenu = new Declaration_revenu(Date.valueOf("2008-07-14"),5,new BigDecimal(65).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown() throws Exception {
		declaration_revenuDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(declaration_revenuDAO.findOne(idInsertSetup),declaration_revenu); 
	}
	
	@Test
	public void testInsert() {
		Declaration_revenu decla = new Declaration_revenu(Date.valueOf("2018-07-14"),3,new BigDecimal(605).setScale(2, RoundingMode.DOWN));
		declaration_revenuDAO.insert(decla);
		assertEquals(decla, declaration_revenuDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		declaration_revenuDAO.deleteById(idInsertSetup);
		assertNull(declaration_revenuDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Declaration_revenu> declas = declaration_revenuDAO.findAll();
	    int nombreDeclasDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Declaration_revenu";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreDeclasDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreDeclasDansLaBase, declas.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, declas.contains(declaration_revenu));
		}
	    
	} 

}
