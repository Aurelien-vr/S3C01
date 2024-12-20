package test;


import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import application.App;
import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;
import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_contrat_location {
	
	private Contrat_locationDAO contrat_locationDAO;
	private Contrat_location contrat_location;
	Connection connection = null;
	int idInsertSetup;
	private ResultSet result;
	
	public test_contrat_location() {}
	
	@Before
	public void setUp() throws Exception {
		PreparedStatement statement;
		String query = null;
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		connection.setAutoCommit(false);
		query = "INSERT INTO db1_sae.Contrat_location(Montant_loyer,Date_debut,Date_fin,Modalite_chauffage,Modalite_eau_chaude_sanitaire,Date_versement) "
				+ " VALUES(800, '2023-4-7', '2024-4-7','DPE=A, et autre truc','chaudiere de 2024', '1000-05-01')";
	try {
		statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		if(statement.executeUpdate()>0) {
			result = statement.getGeneratedKeys();
			if(result.next()) {
				idInsertSetup = result.getInt(1);
			}
		}
	}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	contrat_location = new Contrat_location(800, Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
			"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
	}
	

	@After
	public void tearDown() throws Exception {
		contrat_locationDAO = null;
		Connection connection = DatabaseConnection.getInstance();
		DatabaseConnection.closeResult(result);
		connection.rollback();
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contrat_locationDAO.findOne(idInsertSetup),contrat_location); 
	}
	
	@Test
	public void testInsert() {
		Contrat_location cont_loc = new Contrat_location(12,Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
				"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
		contrat_locationDAO.insert(cont_loc);
		assertEquals(cont_loc, contrat_locationDAO.findOne(idInsertSetup+1));
	}
	
	@Test
	public void testFindAll() {
	    List<Contrat_location> contrats = contrat_locationDAO.findAll();
	    int nombreContratsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Contrat_location";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreContratsDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreContratsDansLaBase, contrats.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, contrats.contains(contrat_location));
		}
	    
	}    
	
	
	@Test
	public void testDelete() {
		contrat_locationDAO.deleteById(idInsertSetup);
		assertNull(contrat_locationDAO.findOne(idInsertSetup));
		}

	//@Test
	//public void testCreateEntities() throws SQLException {
		//int Montant = result.getInt(2);
		//Date Date_debut = result.getDate("date_debut");
		//Date Date_fin = result.getDate("date_fin");
		//String Modalite_chauffage = result.getString("modalite_chauffage");
		//String Modalite_eau_chaude_sanitaire = result.getString("modalite_eau_chaude_sanitaire");
		//Date Date_versement = result.getDate("date_versement");
		//Contrat_location cl = contrat_locationDAO.createEntities(result);
		//System.out.println(cl);
		//assertEquals(cl.getMontant_loyer(), Montant);
		//assertEquals(cl.getDate_debut(), Date_debut);
		//assertEquals(cl.getDate_fin(), Date_fin);
		//assertEquals(cl.getModalite_chauffage(), Modalite_chauffage);
		//assertEquals(cl.getModalite_eau_chaude_sanitaire(), Modalite_eau_chaude_sanitaire);
		//assertEquals(cl.getDate_versement(), Date_versement);

	//}
}
