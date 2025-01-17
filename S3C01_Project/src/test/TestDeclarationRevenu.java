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

public class TestDeclarationRevenu {
	
	private DeclarationRevenuDAO declarationRevenuDAO;
	private Connection connection;
	private DeclarationRevenu declarationRevenu;
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
		declarationRevenuDAO = DAOFactory.createDeclarationRevenuDAO();
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	declarationRevenu = new DeclarationRevenu(Date.valueOf("2008-07-14"),5,new BigDecimal(65).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown(){
		declarationRevenuDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(declarationRevenuDAO.findOne(idInsertSetup),declarationRevenu); 
	}
	
	@Test
	public void testInsert() {
		DeclarationRevenu decla = new DeclarationRevenu(Date.valueOf("2018-07-14"),3,new BigDecimal(605).setScale(2, RoundingMode.DOWN));
		declarationRevenuDAO.insert(decla);
		assertEquals(decla, declarationRevenuDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		declarationRevenuDAO.deleteById(idInsertSetup);
		assertNull(declarationRevenuDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<DeclarationRevenu> declas = declarationRevenuDAO.findAll();
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
	        ExceptionStorageHandler.logException(e, connection);
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
		    assertEquals(true, declas.contains(declarationRevenu));
		}
	    
	} 
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvelleRecetteImmeuble = new BigDecimal(700).setScale(2, RoundingMode.DOWN);
	    Date nouvelleDateAcquisition = Date.valueOf("2019-08-20");
	    int nouveauxLocataires = 8;

	    declarationRevenu.setRecetteImmeuble(nouvelleRecetteImmeuble);
	    declarationRevenu.setDateAcquisition(nouvelleDateAcquisition);
	    declarationRevenu.setLocataires(nouveauxLocataires);

	    declarationRevenuDAO.update(declarationRevenu);

	    assertEquals(nouvelleRecetteImmeuble, declarationRevenu.getRecetteImmeuble());
	    assertEquals(nouvelleDateAcquisition, declarationRevenu.getDateAcquisition());
	    assertEquals(nouveauxLocataires, declarationRevenu.getLocataires());
	}
	
	   @Test
	    public void testFKDeclarationRevenu(){
	        String sql = "{ CALL db1_sae.TestFK_DeclarationRevenu(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKDeclarationRevenu(){
	        String sql = "{ CALL db1_sae.TestCK_DeclarationRevenu(?,?,?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            callableStatement.setDate(1, Date.valueOf("2010-10-10")); 
	            callableStatement.setInt(2, 248);
	            callableStatement.setBigDecimal(3, new BigDecimal(120).setScale(2, RoundingMode.DOWN));
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }


}
