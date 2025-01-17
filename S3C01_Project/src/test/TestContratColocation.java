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

public class TestContratColocation {
	
	private ContratColocationDAO contratColocationDAO;
	private Connection connection;
	private ContratColocation contratColocation;
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
		contratColocationDAO = DAOFactory.createContratColocationDAO();
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	contratColocation = new ContratColocation(true, new BigDecimal(450).setScale(2, RoundingMode.DOWN));
	
	}
	

	@After
	public void tearDown(){
		contratColocationDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contratColocationDAO.findOne(idInsertSetup),contratColocation); 
	}
	
	@Test
	public void testInsert() {
		ContratColocation coloc = new ContratColocation(true,new BigDecimal(500).setScale(2, RoundingMode.DOWN));
		contratColocationDAO.insert(coloc);
		assertEquals(coloc, contratColocationDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		contratColocationDAO.deleteById(idInsertSetup);
		assertNull(contratColocationDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<ContratColocation> contratsColocation = contratColocationDAO.findAll();
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
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
		    assertEquals(nombreContratsCoDansLaBase, contratsColocation.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, contratsColocation.contains(contratColocation));
		
	    }
	} 
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvellePartDesCharges = new BigDecimal(500).setScale(2, RoundingMode.DOWN);
	    boolean nouvelleClauseSolidarite = false;

	    contratColocation.setClauseSolidarite(nouvelleClauseSolidarite);
	    contratColocation.setPartDesCharges(nouvellePartDesCharges);

	    contratColocationDAO.update(contratColocation);

	    assertEquals(nouvelleClauseSolidarite, contratColocation.isClauseSolidarite());
	    assertEquals(nouvellePartDesCharges, contratColocation.getPartDesCharges());
	}
	
	   @Test
	    public void testFKContratColocation(){
	        String sql = "{ CALL db1_sae.TestFK_ContratColocation(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKContratColocation(){
	        String sql = "{ CALL db1_sae.TestCK_ContratColocation(?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setBigDecimal(1, new BigDecimal(50).setScale(2, RoundingMode.DOWN));
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }




}
