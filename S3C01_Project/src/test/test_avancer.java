package test;

import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class test_avancer {

    private AvancerDAO avancerDAO;
    private Connection connection;
    private Avancer avancer;
    int idInsertSetup;

    @Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		avancerDAO = DAOFactory.createAvancerDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Avancer(id_locataire, numero_facture) " +
	               "VALUES (1, 1)";

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
	
	avancer = new Avancer(1, 1);

	
	}
	

	@After
	public void tearDown() throws Exception {
		avancerDAO = null;
		connection.rollback();		
	}

    @Test
    public void testFindOne() {
        assertEquals(avancerDAO.findOne(avancer.getId_locataire()), avancer);
    }

    @Test
    public void testInsert() {
        Avancer av = new Avancer(2, 2);
        avancerDAO.insert(av);
        assertEquals(av, avancerDAO.findOne(av.getId_locataire()));
    }

    @Test
    public void testDelete() {
        avancerDAO.deleteById(idInsertSetup);
        assertNull(avancerDAO.findOne(idInsertSetup));
    }

    @Test
    public void testFindAll() {
        List<Avancer> avancers = avancerDAO.findAll();

        // Vérifie que la liste retournée n'est pas vide
        assertNotNull("La méthode findAll ne doit pas retourner null", avancers);
        assertFalse("La méthode findAll doit retourner une liste non vide", avancers.isEmpty());

        // Affiche les résultats pour vérifier leur contenu
        System.out.println("Liste des objets retournés : " + avancers);
        System.out.println("Objet attendu : " + avancer);

        // Vérifie que l'objet inséré au setUp() est dans la liste
        boolean found = false;
        for (Avancer a : avancers) {
            if (a.getId_locataire() == avancer.getId_locataire() &&
                a.getNumero_facture() == avancer.getNumero_facture()) {
                found = true;
                break;
            }
        }
        assertTrue("L'objet attendu n'a pas été trouvé dans la liste", found);
    }


    @Test
    public void testUpdate() {
        avancer.setId_locataire(3);
        avancer.setNumero_facture(3);
        avancerDAO.update(avancer);

        Avancer updatedAvancer = avancerDAO.findOne(avancer.getId_locataire());
        assertEquals(3, updatedAvancer.getId_locataire());
        assertEquals(3, updatedAvancer.getNumero_facture());
    }
    
    @Test
    public void testFKAvancer() throws Exception {
        String sql = "{ CALL db1_sae.TestFK_Avancer(?, ?) }";

        // Nettoyage de la table Avancer pour éviter les doublons
        String deleteSql = "DELETE FROM db1_sae.Avancer WHERE Id_Locataire = ? AND Numero_facture = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, 1); // Id_Locataire
            deleteStmt.setInt(2, 1); // Numero_facture
            deleteStmt.executeUpdate();
        }

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            // Paramètres valides pour la procédure
            callableStatement.setInt(1, 1); // Id_Locataire
            callableStatement.setInt(2, 1); // Numero_facture

            callableStatement.execute();
            fail("La procédure aurait dû lever une exception 'Success'.");
        } catch (SQLException e) {
            // Vérifie que le message de succès est retourné
            assertEquals("Success", e.getMessage());
        }
    }



}
