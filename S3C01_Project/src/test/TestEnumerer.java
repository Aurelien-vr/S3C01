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

public class TestEnumerer {

    private EnumererDAO enumererDAO;
    private Connection connection;
    private Enumerer enumerer;
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
        enumererDAO = DAOFactory.createEnumererDAO();
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Enumerer(reference_facture, id_solde_de_tout_compte) " +
                       "VALUES ('F001', 1)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    idInsertSetup = result.getInt(1);
                }
                DatabaseConnection.closeResult(result);
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        }

        enumerer = new Enumerer("F001", 1);
    }

    @After
    public void tearDown(){
        enumererDAO = null;
        try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testFindOne() {
        assertEquals(enumererDAO.findOne(enumerer.getIdSoldeDeToutCompte()), enumerer);
    }

    @Test
    public void testInsert() {
        Enumerer en = new Enumerer("F002", 2);
        enumererDAO.insert(en);
        assertEquals(en, enumererDAO.findOne(en.getIdSoldeDeToutCompte()));
    }

    @Test
    public void testDelete() {
        enumererDAO.deleteById(idInsertSetup);
        assertNull(enumererDAO.findOne(idInsertSetup));
    }

    @Test
    public void testFindAll() {
        List<Enumerer> enumerers = enumererDAO.findAll();

        // Vérifie que la liste retournée n'est pas vide
        assertNotNull("La méthode findAll ne doit pas retourner null", enumerers);
        assertFalse("La méthode findAll doit retourner une liste non vide", enumerers.isEmpty());

        // Vérifie que l'objet inséré au setUp() est dans la liste
        boolean found = false;
        for (Enumerer e : enumerers) {
            if (e.getReferenceFacture().equals(enumerer.getReferenceFacture()) &&
                e.getIdSoldeDeToutCompte() == enumerer.getIdSoldeDeToutCompte()) {
                found = true;
                break;
            }
        }
        assertTrue("L'objet attendu n'a pas été trouvé dans la liste", found);
    }

    @Test
    public void testUpdate() {
        enumerer.setReferenceFacture("REF789");
        enumerer.setIdSoldeDeToutCompte(3);
        enumererDAO.update(enumerer);

        Enumerer updatedEnumerer = enumererDAO.findOne(enumerer.getIdSoldeDeToutCompte());
        assertEquals("F003", updatedEnumerer.getReferenceFacture());
        assertEquals(3, updatedEnumerer.getIdSoldeDeToutCompte());
    }

    @Test
    public void testFKEnumerer(){
        String sql = "{ CALL db1_sae.TestFK_Enumerer(?, ?) }";

        // Nettoyage de la table Enumerer pour éviter les doublons
        String deleteSql = "DELETE FROM db1_sae.Enumerer WHERE reference_facture = ? AND id_solde_de_tout_compte = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setString(1, "F001");
            deleteStmt.setInt(2, 1);
            deleteStmt.executeUpdate();
        }catch (Exception e) {
        	ExceptionStorageHandler.logException(e, connection);
		}

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            // Paramètres valides pour la procédure
            callableStatement.setString(1, "F001");
            callableStatement.setInt(2, 1);

            callableStatement.execute();
            fail("La procédure aurait dû lever une exception 'Success'.");
        } catch (SQLException e) {
            // Vérifie que le message de succès est retourné
            assertEquals("Success", e.getMessage());
        }
    }
}
