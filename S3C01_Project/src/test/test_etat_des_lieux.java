package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_etat_des_lieux {
	
	private Etat_des_lieuxDAO etat_des_lieuxDAO;
	private Connection connection;
	private Etat_des_lieux etat_des_lieux;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		etat_des_lieuxDAO = DAOFactory.createEtat_des_lieuxDAO();
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
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	etat_des_lieux = new Etat_des_lieux(Date.valueOf("2024-12-09"),5, "Detruit",true);
	
	}
	

	@After
	public void tearDown() throws Exception {
		etat_des_lieuxDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(etat_des_lieuxDAO.findOne(idInsertSetup),etat_des_lieux); 
	}
	
	@Test
	public void testInsert() {
		Etat_des_lieux edl = new Etat_des_lieux(Date.valueOf("2023-12-06"),4, "Bien",false);
		etat_des_lieuxDAO.insert(edl);
		assertEquals(edl, etat_des_lieuxDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		etat_des_lieuxDAO.deleteById(idInsertSetup);
		assertNull(etat_des_lieuxDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Etat_des_lieux> edts = etat_des_lieuxDAO.findAll();
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
	        ExceptionStorageHandler.LogException(e, connection);
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
		    assertEquals(true, edts.contains(etat_des_lieux));
		
	    }
	    
	} 
	
	@Test
	public void testUpdate() {
	    Date nouvelleDateSignature = Date.valueOf("2024-12-10");
	    int nouveauNombreCles = 6;
	    String nouvelEtatDesElements = "Bien";
	    boolean nouvelleEstEntrer = false;

	    etat_des_lieux.setDate_signature(nouvelleDateSignature);
	    etat_des_lieux.setNombre_cles(nouveauNombreCles);
	    etat_des_lieux.setEtat_des_elements(nouvelEtatDesElements);
	    etat_des_lieux.setEst_entrer(nouvelleEstEntrer);

	    etat_des_lieuxDAO.update(etat_des_lieux);

	    assertEquals(nouvelleDateSignature, etat_des_lieux.getDate_signature());
	    assertEquals(nouveauNombreCles, etat_des_lieux.getNombre_cles());
	    assertEquals(nouvelEtatDesElements, etat_des_lieux.getEtat_des_elements());
	    assertEquals(nouvelleEstEntrer, etat_des_lieux.isEst_entrer());
	}
	
	   @Test
	    public void testFKEdt() throws Exception {
	        String sql = "{ CALL db1_sae.TestFK_EtatDesLieux(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKAvisEdt() throws Exception {
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
