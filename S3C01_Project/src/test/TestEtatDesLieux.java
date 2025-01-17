package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestEtatDesLieux {
	
	private EtatDesLieuxDAO etatDesLieuxDAO;
	private Connection connection;
	private EtatDesLieux etatDesLieux;
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
		etatDesLieuxDAO = DAOFactory.createEtatDesLieuxDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Etat_des_lieux(date_signature, nombre_cles, etats_des_element, est_entrer) "
				+ " VALUES('2024-12-09', 5, 'Detruit', true)";
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
	
	etatDesLieux = new EtatDesLieux(Date.valueOf("2024-12-09"),5, "Detruit",true);
	
	}
	

	@After
	public void tearDown() {
		etatDesLieuxDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(etatDesLieuxDAO.findOne(idInsertSetup),etatDesLieux); 
	}
	
	@Test
	public void testInsert() {
		EtatDesLieux edl = new EtatDesLieux(Date.valueOf("2023-12-06"),4, "Bien",false);
		etatDesLieuxDAO.insert(edl);
		assertEquals(edl, etatDesLieuxDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		etatDesLieuxDAO.deleteById(idInsertSetup);
		assertNull(etatDesLieuxDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<EtatDesLieux> edts = etatDesLieuxDAO.findAll();
	    int nombreEdtsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Etat_des_lieux";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreEdtsDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreEdtsDansLaBase, edts.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, edts.contains(etatDesLieux));
		
	    }
	    
	} 
	
	@Test
	public void testUpdate() {
	    Date nouvelleDateSignature = Date.valueOf("2024-12-10");
	    int nouveauNombreCles = 6;
	    String nouvelEtatDesElements = "Bien";
	    boolean nouvelleEstEntrer = false;

	    etatDesLieux.setDateSignature(nouvelleDateSignature);
	    etatDesLieux.setNombreCles(nouveauNombreCles);
	    etatDesLieux.setEtatDesElements(nouvelEtatDesElements);
	    etatDesLieux.setEstEntrer(nouvelleEstEntrer);

	    etatDesLieuxDAO.update(etatDesLieux);

	    assertEquals(nouvelleDateSignature, etatDesLieux.getDateSignature());
	    assertEquals(nouveauNombreCles, etatDesLieux.getNombreCles());
	    assertEquals(nouvelEtatDesElements, etatDesLieux.getEtatDesElements());
	    assertEquals(nouvelleEstEntrer, etatDesLieux.isEstEntrer());
	}
	
	   @Test
	    public void testFKEdt(){
	        String sql = "{ CALL db1_sae.TestFK_EtatDesLieux(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);
	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKAvisEdt(){
	        String sql = "{ CALL db1_sae.TestCK_EtatDesLieux(?,?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            callableStatement.setDate(1, Date.valueOf("2010-10-10")); 
	            callableStatement.setInt(2, 248);
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }


}
