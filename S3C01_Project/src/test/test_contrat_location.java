package test;


import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
			assertEquals(cont_loc, contrat_locationDAO.findOne(cont_loc.getNumero_location()));
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

	@Test
	public void testCKContratLocation() throws Exception {
	    String sql = "{ CALL db1_sae.TestCK_ContratLocation(?, ?, ?) }";

	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setBigDecimal(1, new BigDecimal("850.00"));
	        callableStatement.setDate(2, Date.valueOf("2024-05-01"));
	        callableStatement.setDate(3, Date.valueOf("2025-06-01"));

	        callableStatement.execute();
	    } catch (Exception e) {
	        assertEquals("Success", e.getMessage());
	    }
	}
	
	@Test
	public void testGetContratLocations() {
	    String sql = "{ CALL db1_sae.get_contratLocations() }"; // Appel de la procédure
	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        try (ResultSet resultSet = callableStatement.executeQuery()) {
	            // Vérifier que le ResultSet contient des résultats
	            assertTrue(resultSet.next());

	            // Récupère les valeurs attendues de la base de données pour la première ligne
	            String query = "SELECT DISTINCT cl.Montant_loyer AS Montant, cl.Date_debut AS `Date Début`, cl.Date_fin AS `Date Fin`, "
	                    + "cl.Modalite_chauffage AS Modalite, cl.Modalite_eau_chaude_sanitaire AS ModaliteEau, "
	                    + "b.Adresse AS `Adresse`, CONCAT(l.Prenom, ' ', l.Nom) AS `Nom Locataire`, "
	                    + "CASE WHEN cc.Clause_solidarite IS NOT NULL THEN TRUE ELSE FALSE END AS `Colocation`, "
	                    + "CASE WHEN edl.Est_entrer IS NOT NULL THEN TRUE ELSE FALSE END AS `État des lieux`, "
	                    + "CASE WHEN sdc.Reste_a_devoir IS NOT NULL THEN TRUE ELSE FALSE END AS `Solde de tout compte`, "
	                    + "CASE WHEN rc.Charge_eau IS NOT NULL THEN TRUE ELSE FALSE END AS `Régularisation des charges` "
	                    + "FROM db1_sae.Contrat_location cl LEFT JOIN db1_sae.Bien b ON cl.Id_Contrat_location = b.Id_Contrat_location "
	                    + "LEFT JOIN db1_sae.Locataire l ON cl.Id_Contrat_location = l.Id_Contrat_location "
	                    + "LEFT JOIN db1_sae.Contrat_colocation cc ON cl.Id_Contrat_location = cc.Id_Contrat_location "
	                    + "LEFT JOIN db1_sae.Etat_des_lieux edl ON cl.Id_Contrat_location = edl.Id_Contrat_location "
	                    + "LEFT JOIN db1_sae.Solde_de_tout_compte sdc ON cl.Id_Contrat_location = sdc.Id_Contrat_location "
	                    + "LEFT JOIN db1_sae.Regularisation_charges rc ON cl.Id_Contrat_location = rc.Id_Contrat_location "
	                    + "ORDER BY cl.Date_fin DESC LIMIT 1"; // Pour obtenir la première ligne à des fins de comparaison

	            try (Statement stmt = connection.createStatement(); ResultSet expectedResultSet = stmt.executeQuery(query)) {
	                assertTrue(expectedResultSet.next());
	                
	                // Récupération des valeurs attendues de la base de données
	                String adresseAttendue = expectedResultSet.getString("Adresse");
	                Date dateDebutAttendue = expectedResultSet.getDate("Date Début");
	                Date dateFinAttendue = expectedResultSet.getDate("Date Fin");
	                String modaliteAttendue = expectedResultSet.getString("Modalite");
	                String modaliteEauAttendue = expectedResultSet.getString("ModaliteEau");
	                String nomLocataireAttendu = expectedResultSet.getString("Nom Locataire");
	                boolean colocationAttendue = expectedResultSet.getBoolean("Colocation");
	                boolean etatDesLieuxAttendu = expectedResultSet.getBoolean("État des lieux");
	                boolean soldeDeToutCompteAttendu = expectedResultSet.getBoolean("Solde de tout compte");
	                boolean regularisationChargesAttendue = expectedResultSet.getBoolean("Régularisation des charges");

	                // Récupère les valeurs retournées par la procédure et les compare
	                String adresse = resultSet.getString("Adresse Bien");
	                Date dateDebut = resultSet.getDate("Date Début");
	                Date dateFin = resultSet.getDate("Date Fin");
	                String modalite = resultSet.getString("Modalite");
	                String modaliteEau = resultSet.getString("ModaliteEau");
	                String nomLocataire = resultSet.getString("Nom Locataire");
	                boolean colocation = resultSet.getBoolean("Colocation");
	                boolean etatDesLieux = resultSet.getBoolean("État des lieux");
	                boolean soldeDeToutCompte = resultSet.getBoolean("Solde de tout compte");
	                boolean regularisationCharges = resultSet.getBoolean("Régularisation des charges");

	                // Comparaison des valeurs récupérées avec celles attendues
	                assertEquals(adresseAttendue, adresse);
	                assertEquals(dateDebutAttendue, dateDebut);
	                assertEquals(dateFinAttendue, dateFin);
	                assertEquals(modaliteAttendue, modalite);
	                assertEquals(modaliteEauAttendue, modaliteEau);
	                assertEquals(nomLocataireAttendu, nomLocataire);
	                assertEquals(colocationAttendue, colocation);
	                assertEquals(etatDesLieuxAttendu, etatDesLieux);
	                assertEquals(soldeDeToutCompteAttendu, soldeDeToutCompte);
	                assertEquals(regularisationChargesAttendue, regularisationCharges);
	            }
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
	    }
	}

	@Test
	public void testGetContratLocationsActif() {
	    // Appel à la procédure 'get_contratLocationsActif'
	    ResultSet resultSet = null;
	    String query = "{ CALL db1_sae.get_contratLocationsActif() }";
	    try (CallableStatement stmt = connection.prepareCall(query)) {
	        resultSet = stmt.executeQuery();

	        // Vérifier que les résultats sont bien retournés
	        assertTrue(resultSet.next()); // On vérifie qu'au moins une ligne existe

	        // Vérifier le contenu des colonnes
	        do {
	            assertNotNull(resultSet.getBigDecimal("Montant"));
	            assertNotNull(resultSet.getDate("Date Début"));
	            assertNotNull(resultSet.getDate("Date Fin"));
	            assertNotNull(resultSet.getString("Modalite"));
	            assertNotNull(resultSet.getString("ModaliteEau"));
	            assertNotNull(resultSet.getString("Adresse Bien"));
	            assertNotNull(resultSet.getString("Nom Locataire"));
	            assertNotNull(resultSet.getBoolean("Colocation"));
	            assertNotNull(resultSet.getBoolean("État des lieux"));
	            assertNotNull(resultSet.getBoolean("Solde de tout compte"));
	            assertNotNull(resultSet.getBoolean("Régularisation des charges"));
	        } while (resultSet.next());

	    } catch (SQLException e) {
	        e.printStackTrace();
	        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
	    } finally {
	        // Fermer le résultat et la connexion
	        DatabaseConnection.closeResult(resultSet);
	    }
	}

	
	public void testProcedureGetContratLocNotFkInBien() {
	    String sql = "{ CALL db1_sae.get_ContratLocNotFkInBien() }";
	    String sqlVerif = "SELECT cl.Id_Contrat_location FROM db1_sae.Contrat_location cl WHERE NOT EXISTS (SELECT b.Id_Bien FROM db1_sae.Bien b WHERE b.Id_Contrat_location = cl.Id_Contrat_location) LIMIT 1"; // Récupère la première ligne
	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        try (ResultSet resultSet = callableStatement.executeQuery()) {
	            // Vérifie si le ResultSet contient des résultats
	            assertTrue(resultSet.next());

	            // Récupère les valeurs réelles de la base pour la première ligne
	            try (Statement stmt = connection.createStatement();
	                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

	                assertTrue(expectedResultSet.next());

	                Integer idContratLocationAttendu = expectedResultSet.getInt("Id_Contrat_location");

	                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
	                Integer idContratLocation = resultSet.getInt("Id_Contrat_location");

	                assertEquals(idContratLocationAttendu, idContratLocation);
	            }
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
	    }
	}



	
}
