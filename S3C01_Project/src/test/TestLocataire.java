package test;
import org.junit.*;
import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestLocataire {
	
	private String messageError = "Erreur lors de l'appel de la procédure : ";
	private String ibanColumn = "IBAN";
	private String dateNaissanceColumn = "Date_de_naissance";
	private String adresseColumn = "Adresse";
	private String prenomColumn = "Prenom";
	private String nomColumn = "Nom";
	private String idLocataireColumn = "Id_Locataire";
	private LocataireDAO locataireDAO;
	private Connection connection;
	private Locataire locataire;
	int idInsertSetup;
	private ResultSet result;
	
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
		locataire = new Locataire("Chlabi", "Aymen", Date.valueOf("2004-08-13"), "1234567890");
	}catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}
	
	
	}
	

	@After
	public void tearDown(){
	    try {
	        connection.rollback();
	        if (result != null) result.close();
	        connection.setAutoCommit(true);
	        locataireDAO = null;
	    }catch (Exception e) {
			e.printStackTrace();
	    }
	}
	
	@Test
	public void testFindOne() {
		assertEquals(locataireDAO.findOne(idInsertSetup),locataire); 
	}
	
	@Test
	public void testInsert() {
		Locataire loc = new Locataire("Vincent-Randonnier", "Aurelien", Date.valueOf("2004-11-03"), "0987654321");
		locataireDAO.insert(loc);
		assertEquals(loc, locataireDAO.findOne(loc.getIdLocataire()));

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
	    result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Locataire";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreLocsDansLaBase = result.getInt(1); 
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
	    locataire.setDateDeNaissance(nouvelleDateDeNaissance);
	    locataire.setIban(nouveauIban);

	    locataireDAO.update(locataire);

	    assertEquals(nouveauNom, locataire.getNom());
	    assertEquals(nouveauPrenom, locataire.getPrenom());
	    assertEquals(nouvelleDateDeNaissance, locataire.getDateDeNaissance());
	    assertEquals(nouveauIban, locataire.getIban());
	}

	   @Test
	    public void testFKLocataire() {
	        String sql = "{ CALL db1_sae.TestFK_Locataire(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKLocataire(){
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

		                Integer idLocataireAttendu = expectedResultSet.getInt(idLocataireColumn);
		                String nomAttendu = expectedResultSet.getString(nomColumn);
		                String prenomAttendu = expectedResultSet.getString(prenomColumn);
		                String adresseAttendue = expectedResultSet.getString(adresseColumn);
		                Date dateDeNaissanceAttendue = expectedResultSet.getDate(dateNaissanceColumn);
		                String ibanAttendu = expectedResultSet.getString(ibanColumn);

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                Integer idLocataire = resultSet.getInt(idLocataireColumn);
		                String nom = resultSet.getString(nomColumn);
		                String prenom = resultSet.getString(prenomColumn);
		                String adresse = resultSet.getString(adresseColumn);
		                Date dateDeNaissance = resultSet.getDate(dateNaissanceColumn);
		                String iban = resultSet.getString(ibanColumn);

		                assertEquals(idLocataireAttendu, idLocataire);
		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(dateDeNaissanceAttendue, dateDeNaissance);
		                assertEquals(ibanAttendu, iban);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail(messageError + e.getMessage());
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

		                Integer idLocataireAttendu = expectedResultSet.getInt(idLocataireColumn);
		                String nomAttendu = expectedResultSet.getString(nomColumn);
		                String prenomAttendu = expectedResultSet.getString(prenomColumn);
		                String adresseAttendue = expectedResultSet.getString(adresseColumn);
		                Date dateDeNaissanceAttendue = expectedResultSet.getDate(dateNaissanceColumn);
		                String ibanAttendu = expectedResultSet.getString(ibanColumn);

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                Integer idLocataire = resultSet.getInt(idLocataireColumn);
		                String nom = resultSet.getString(nomColumn);
		                String prenom = resultSet.getString(prenomColumn);
		                String adresse = resultSet.getString(adresseColumn);
		                Date dateDeNaissance = resultSet.getDate(dateNaissanceColumn);
		                String iban = resultSet.getString(ibanColumn);

		                assertEquals(idLocataireAttendu, idLocataire);
		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(dateDeNaissanceAttendue, dateDeNaissance);
		                assertEquals(ibanAttendu, iban);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail(messageError + e.getMessage());
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

		                String nomAttendu = expectedResultSet.getString(nomColumn);
		                String prenomAttendu = expectedResultSet.getString(prenomColumn);
		                Integer idLocataireAttendu = expectedResultSet.getInt(idLocataireColumn);

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                String nom = resultSet.getString(nomColumn);
		                String prenom = resultSet.getString(prenomColumn);
		                Integer idLocataire = resultSet.getInt(idLocataireColumn);

		                assertEquals(nomAttendu, nom);
		                assertEquals(prenomAttendu, prenom);
		                assertEquals(idLocataireAttendu, idLocataire);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail(messageError + e.getMessage());
		    }
		}
	   
	   
	


}
