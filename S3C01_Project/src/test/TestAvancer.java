package test;

import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class TestAvancer {

    private AvancerDAO avancerDAO;
    private Connection connection;
    private Avancer avancer;
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	avancer = new Avancer(1, 1);

	
	}
	

	@After
	public void tearDown() {
		avancerDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

    @Test
    public void testFindOne() {
        assertEquals(avancerDAO.findOne(avancer.getIdLocataire()), avancer);
    }

    @Test
    public void testInsert() {
        Avancer av = new Avancer(2, 2);
        avancerDAO.insert(av);
        assertEquals(av, avancerDAO.findOne(av.getIdLocataire()));
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

        // Vérifie que l'objet inséré au setUp() est dans la liste
        boolean found = false;
        for (Avancer a : avancers) {
            if (a.getIdLocataire() == avancer.getIdLocataire() &&
                a.getNumeroFacture() == avancer.getNumeroFacture()) {
                found = true;
                break;
            }
        }
        assertTrue("L'objet attendu n'a pas été trouvé dans la liste", found);
    }


    @Test
    public void testUpdate() {
        avancer.setIdLocataire(3);
        avancer.setNumeroFacture(3);
        avancerDAO.update(avancer);

        Avancer updatedAvancer = avancerDAO.findOne(avancer.getIdLocataire());
        assertEquals(3, updatedAvancer.getIdLocataire());
        assertEquals(3, updatedAvancer.getNumeroFacture());
    }
    
    @Test
    public void testFKAvancer() {
        String sql = "{ CALL db1_sae.TestFK_Avancer(?, ?) }";

        // Nettoyage de la table Avancer pour éviter les doublons
        String deleteSql = "DELETE FROM db1_sae.Avancer WHERE Id_Locataire = ? AND Numero_facture = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, 1); // Id_Locataire
            deleteStmt.setInt(2, 1); // Numero_facture
            deleteStmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
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
