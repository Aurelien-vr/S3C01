package test;


import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import application.App;
import dao.ContratLocationDAO;
import dao.DAOFactory;
import dao.entities.ContratLocation;
import db_connection.DatabaseConnection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestContratLocation {
	
	private ContratLocationDAO contratLocationDAO;
	private ContratLocation contratLocation;
	Connection connection = null;
	int idInsertSetup;
	private ResultSet result;
	private String errorMassage = "Erreur lors de l'appel de la procédure : ";
	
	public TestContratLocation() { /*Auncun constructeur car est une class de test*/ }
	
	@Before
	public void setUp(){
		PreparedStatement statement;
		String query = null;
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			App.main(null);
			connection = DatabaseConnection.getInstance();
		}
		contratLocationDAO = DAOFactory.createContratLocationDAO();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	contratLocation = new ContratLocation(800, Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
			"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
	}
	

	@After
	public void tearDown(){
		contratLocationDAO = null;
		connection = DatabaseConnection.getInstance();
		DatabaseConnection.closeResult(result);
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOne() {
		assertEquals(contratLocationDAO.findOne(idInsertSetup),contratLocation); 
	}
	
	@Test
	public void testInsert() {
		contratLocation = new ContratLocation(12,Date.valueOf("2023-4-7"), Date.valueOf("2024-4-7"),
				"DPE=A, et autre truc", "chaudiere de 2024",Date.valueOf("1000-05-01"));
			contratLocationDAO.insert(contratLocation);
			
			assertEquals(contratLocation, contratLocationDAO.findOne(contratLocation.getNumeroLocation()));
	}

	
	@Test
	public void testFindAll() {
	    List<ContratLocation> contrats = contratLocationDAO.findAll();
	    int nombreContratsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Contrat_location";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreContratsDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreContratsDansLaBase, contrats.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, contrats.contains(contratLocation));
		}
	    
	}    
	
	
	@Test
	public void testDelete() {
		contratLocationDAO.deleteById(idInsertSetup);
		assertNull(contratLocationDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauMontantLoyer = new BigDecimal(850).setScale(2, RoundingMode.DOWN);
	    Date nouvelleDateDebut = Date.valueOf("2024-5-1");
	    Date nouvelleDateFin = Date.valueOf("2025-6-1");
	    String nouvelleModaliteChauffage = "DPE=B, et autre truc";
	    String nouvelleModaliteEauChaudeSanitaire = "chaudière de 2025";
	    Date nouvelleDateVersement = Date.valueOf("1000-06-01");
	    
	    contratLocation.setMontantLoyer(nouveauMontantLoyer.intValue());
	    contratLocation.setDateDebut(nouvelleDateDebut);
	    contratLocation.setDateFin(nouvelleDateFin);
	    contratLocation.setModaliteChauffage(nouvelleModaliteChauffage);
	    contratLocation.setModaliteEauChaudeSanitaire(nouvelleModaliteEauChaudeSanitaire);
	    contratLocation.setDateVersement(nouvelleDateVersement);
	    
	    contratLocationDAO.update(contratLocation);
	    
	    assertEquals(nouveauMontantLoyer.intValue(), contratLocation.getMontantLoyer());
	    assertEquals(nouvelleDateDebut, contratLocation.getDateDebut());
	    assertEquals(nouvelleDateFin, contratLocation.getDateFin());
	    assertEquals(nouvelleModaliteChauffage, contratLocation.getModaliteChauffage());
	    assertEquals(nouvelleModaliteEauChaudeSanitaire, contratLocation.getModaliteEauChaudeSanitaire());
	    assertEquals(nouvelleDateVersement, contratLocation.getDateVersement());
	}

	@Test
	public void testCKContratLocation(){
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
	                    + "b.Adresse AS `Adresse Bien`, CONCAT(l.Prenom, ' ', l.Nom) AS `Nom Locataire`, "
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
	                String adressColumn = "Adresse Bien";
	                String dateDebutColumn = "Date Début";
	                String dateFinColumn = "Date Fin";
	                String modaliteColumn = "Modalite";
	                String modaliteEauColumn = "ModaliteEau";
	                String nomLocataireColumn = "Nom Locataire";
	                String colocationColumn = "Colocation";
	                String etatDesLieuxColumn = "État des lieux";
	                String soldeDeToutCompteColumn = "Solde de tout compte";
	                String regularisationDesChargesColumn = "Régularisation des charges";
	                
	                String adresseAttendue = expectedResultSet.getString(adressColumn);
	                Date dateDebutAttendue = expectedResultSet.getDate(dateDebutColumn);
	                Date dateFinAttendue = expectedResultSet.getDate(dateFinColumn);
	                String modaliteAttendue = expectedResultSet.getString(modaliteColumn);
					String modaliteEauAttendue = expectedResultSet.getString(modaliteEauColumn);
					String nomLocataireAttendu = expectedResultSet.getString(nomLocataireColumn);
					boolean colocationAttendue = expectedResultSet.getBoolean(colocationColumn);
					boolean etatDesLieuxAttendu = expectedResultSet.getBoolean(etatDesLieuxColumn);
					boolean soldeDeToutCompteAttendu = expectedResultSet.getBoolean(soldeDeToutCompteColumn);
					boolean regularisationChargesAttendue = expectedResultSet.getBoolean(regularisationDesChargesColumn);

	                // Récupère les valeurs retournées par la procédure et les compare
	                String adresse = resultSet.getString(adressColumn);
	                Date dateDebut = resultSet.getDate(dateDebutColumn);
	                Date dateFin = resultSet.getDate(dateFinColumn);
	                String modalite = resultSet.getString(modaliteColumn);
	                String modaliteEau = resultSet.getString(modaliteEauColumn);
	                String nomLocataire = resultSet.getString(nomLocataireColumn);
	                boolean colocation = resultSet.getBoolean(colocationColumn);
	                boolean etatDesLieux = resultSet.getBoolean(etatDesLieuxColumn);
	                boolean soldeDeToutCompte = resultSet.getBoolean(soldeDeToutCompteColumn);
	                boolean regularisationCharges = resultSet.getBoolean(regularisationDesChargesColumn);

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
	        ExceptionStorageHandler.logException(e, connection);
			fail(errorMassage + e.getMessage());
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
	        fail(errorMassage + e.getMessage());
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
	        ExceptionStorageHandler.logException(e, connection);
	        fail(errorMassage + e.getMessage());
	    }
	}



	
}
