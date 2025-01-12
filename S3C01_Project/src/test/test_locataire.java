package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_locataire {
	
	private LocataireDAO locataireDAO;
	private Connection connection;
	private Locataire locataire;
	int idInsertSetup;
	private ResultSet result;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		locataireDAO = DAOFactory.createLocataireDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Locataire(nom, prenom, date_de_naissance ,iban) "
				+ " VALUES('Chlabi', 'Aymen', '2004-08-13', '1234567890')";
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
	
	locataire = new Locataire("Chlabi", "Aymen", Date.valueOf("2004-08-13"), "1234567890");
	
	}
	

	@After
	public void tearDown() throws Exception {
		locataireDAO = null;
		Connection connection = DatabaseConnection.getInstance();
		DatabaseConnection.closeResult(result);
		connection.rollback();
	}
	
	@Test
	public void testFindOne() {
		assertEquals(locataireDAO.findOne(idInsertSetup),locataire); 
	}
	
	@Test
	public void testInsert() {
		Locataire loc = new Locataire("Vincent-Randonnier", "Aurelien", Date.valueOf("2004-11-03"), "0987654321");
		locataireDAO.insert(loc);
		assertEquals(loc, locataireDAO.findOne(loc.getId_locataire()));

	}
	
	@Test
	public void testDelete() {
		locataireDAO.deleteById(idInsertSetup);
		assertNull(locataireDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Locataire> locs = locataireDAO.findAll();
	    int nombreLocsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Locataire";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreLocsDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreLocsDansLaBase, locs.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, locs.contains(locataire));
		}
	    
	} 
	
	@Test
	public void testUpdate() {
	    String nouveauNom = "Durand";
	    String nouveauPrenom = "Pierre";
	    Date nouvelleDateDeNaissance = Date.valueOf("1990-05-21");
	    String nouveauIban = "1122334455";

	    locataire.setNom(nouveauNom);
	    locataire.setPrenom(nouveauPrenom);
	    locataire.setDate_de_naissance(nouvelleDateDeNaissance);
	    locataire.setIban(nouveauIban);

	    locataireDAO.update(locataire);

	    assertEquals(nouveauNom, locataire.getNom());
	    assertEquals(nouveauPrenom, locataire.getPrenom());
	    assertEquals(nouvelleDateDeNaissance, locataire.getDate_de_naissance());
	    assertEquals(nouveauIban, locataire.getIban());
	}

	   @Test
	    public void testFKLocataire() throws Exception {
	        String sql = "{ CALL db1_sae.TestFK_Locataire(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKLocataire() throws Exception {
	        String sql = "{ CALL db1_sae.TestCK_Locataire(?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setDate(1, Date.valueOf("2012-12-12"));
	            

	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   public void testProcedureGetLocataires() {
		    String sql = "{ CALL db1_sae.get_locataires() }";
		    String sqlVerif = "SELECT l.Id_Locataire, l.Nom, l.Prenom, b.Adresse, l.Date_de_naissance, l.IBAN FROM db1_sae.Locataire l LEFT JOIN db1_sae.Contrat_location cl ON cl.Id_Contrat_location = l.Id_Contrat_location LEFT JOIN db1_sae.Bien b ON b.Id_Contrat_location = cl.Id_Contrat_location LIMIT 1"; // Récupère la première ligne
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Vérifie si le ResultSet contient des résultats
		            assertTrue(resultSet.next());

		            // Récupère les valeurs réelles de la base pour la première ligne
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                Integer idLocataireAttendu = expectedResultSet.getInt("Id_Locataire");
		                String nomAttendu = expectedResultSet.getString("Nom");
		                String prenomAttendu = expectedResultSet.getString("Prenom");
		                String adresseAttendue = expectedResultSet.getString("Adresse");
		                Date dateDeNaissanceAttendue = expectedResultSet.getDate("Date_de_naissance");
		                String ibanAttendu = expectedResultSet.getString("IBAN");

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                Integer idLocataire = resultSet.getInt("Id_Locataire");
		                String nom = resultSet.getString("Nom");
		                String prenom = resultSet.getString("Prenom");
		                String adresse = resultSet.getString("Adresse");
		                Date dateDeNaissance = resultSet.getDate("Date_de_naissance");
		                String iban = resultSet.getString("IBAN");

		                assertEquals(idLocataireAttendu, idLocataire);
		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(dateDeNaissanceAttendue, dateDeNaissance);
		                assertEquals(ibanAttendu, iban);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.LogException(e, connection);
		        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
		    }
		}
	   
	   public void testProcedureGetLocatairesActif() {
		    String sql = "{ CALL db1_sae.get_locatairesActif() }";
		    String sqlVerif = "SELECT l.Id_Locataire, l.Nom, l.Prenom, b.Adresse, l.Date_de_naissance, l.IBAN FROM db1_sae.Locataire l LEFT JOIN db1_sae.Contrat_location cl ON cl.Id_Contrat_location = l.Id_Contrat_location LEFT JOIN db1_sae.Bien b ON b.Id_Contrat_location = cl.Id_Contrat_location WHERE b.Id_Contrat_location IS NOT NULL LIMIT 1"; // Récupère la première ligne
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Vérifie si le ResultSet contient des résultats
		            assertTrue(resultSet.next());

		            // Récupère les valeurs réelles de la base pour la première ligne
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                Integer idLocataireAttendu = expectedResultSet.getInt("Id_Locataire");
		                String nomAttendu = expectedResultSet.getString("Nom");
		                String prenomAttendu = expectedResultSet.getString("Prenom");
		                String adresseAttendue = expectedResultSet.getString("Adresse");
		                Date dateDeNaissanceAttendue = expectedResultSet.getDate("Date_de_naissance");
		                String ibanAttendu = expectedResultSet.getString("IBAN");

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                Integer idLocataire = resultSet.getInt("Id_Locataire");
		                String nom = resultSet.getString("Nom");
		                String prenom = resultSet.getString("Prenom");
		                String adresse = resultSet.getString("Adresse");
		                Date dateDeNaissance = resultSet.getDate("Date_de_naissance");
		                String iban = resultSet.getString("IBAN");

		                assertEquals(idLocataireAttendu, idLocataire);
		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(dateDeNaissanceAttendue, dateDeNaissance);
		                assertEquals(ibanAttendu, iban);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.LogException(e, connection);
		        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
		    }
		}
	   
	   public void testProcedureGetLocataireSansContrat() {
		    String sql = "{ CALL db1_sae.get_locataireSansContrat() }";
		    String sqlVerif = "SELECT Nom, Prenom, Id_Locataire FROM db1_sae.Locataire l WHERE Id_Contrat_location IS NULL LIMIT 1"; // Récupère la première ligne
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Vérifie si le ResultSet contient des résultats
		            assertTrue(resultSet.next());

		            // Récupère les valeurs réelles de la base pour la première ligne
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                String nomAttendu = expectedResultSet.getString("Nom");
		                String prenomAttendu = expectedResultSet.getString("Prenom");
		                Integer idLocataireAttendu = expectedResultSet.getInt("Id_Locataire");

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                String nom = resultSet.getString("Nom");
		                String prenom = resultSet.getString("Prenom");
		                Integer idLocataire = resultSet.getInt("Id_Locataire");

		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(idLocataireAttendu, idLocataire);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.LogException(e, connection);
		        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
		    }
		}
	   
	   
	


}
