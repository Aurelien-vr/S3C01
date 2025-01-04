package test;


import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import application.App;
import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauMontantLoyer = new BigDecimal(850).setScale(2, RoundingMode.DOWN);
	    Date nouvelleDateDebut = Date.valueOf("2024-5-1");
	    Date nouvelleDateFin = Date.valueOf("2025-6-1");
	    String nouvelleModaliteChauffage = "DPE=B, et autre truc";
	    String nouvelleModaliteEauChaudeSanitaire = "chaudière de 2025";
	    Date nouvelleDateVersement = Date.valueOf("1000-06-01");
	    
	    contrat_location.setMontant_loyer(nouveauMontantLoyer.intValue());
	    contrat_location.setDate_debut(nouvelleDateDebut);
	    contrat_location.setDate_fin(nouvelleDateFin);
	    contrat_location.setModalite_chauffage(nouvelleModaliteChauffage);
	    contrat_location.setModalite_eau_chaude_sanitaire(nouvelleModaliteEauChaudeSanitaire);
	    contrat_location.setDate_versement(nouvelleDateVersement);
	    
	    contrat_locationDAO.update(contrat_location);
	    
	    assertEquals(nouveauMontantLoyer.intValue(), contrat_location.getMontant_loyer());
	    assertEquals(nouvelleDateDebut, contrat_location.getDate_debut());
	    assertEquals(nouvelleDateFin, contrat_location.getDate_fin());
	    assertEquals(nouvelleModaliteChauffage, contrat_location.getModalite_chauffage());
	    assertEquals(nouvelleModaliteEauChaudeSanitaire, contrat_location.getModalite_eau_chaude_sanitaire());
	    assertEquals(nouvelleDateVersement, contrat_location.getDate_versement());
	}


	
}
