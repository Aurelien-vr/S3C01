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

public class test_enumerer {

    private EnumererDAO enumererDAO;
    private Connection connection;
    private Enumerer enumerer;
    int idInsertSetup;

    @Before
    public void setUp() throws Exception {
        connection = DatabaseConnection.getInstance();
        if (connection == null) {
            new App();
            connection = DatabaseConnection.getInstance();
        }
        connection.setAutoCommit(false);
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
            ExceptionStorageHandler.LogException(e, connection);
        }

        enumerer = new Enumerer("F001", 1);
    }

    @After
    public void tearDown() throws Exception {
        enumererDAO = null;
        connection.rollback();
    }

    @Test
    public void testFindOne() {
        assertEquals(enumererDAO.findOne(enumerer.getId_solde_de_tout_compte()), enumerer);
    }

    @Test
    public void testInsert() {
        Enumerer en = new Enumerer("F002", 2);
        enumererDAO.insert(en);
        assertEquals(en, enumererDAO.findOne(en.getId_solde_de_tout_compte()));
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

        // Affiche les résultats pour vérifier leur contenu
        System.out.println("Liste des objets retournés : " + enumerers);
        System.out.println("Objet attendu : " + enumerer);

        // Vérifie que l'objet inséré au setUp() est dans la liste
        boolean found = false;
        for (Enumerer e : enumerers) {
            if (e.getReference_facture().equals(enumerer.getReference_facture()) &&
                e.getId_solde_de_tout_compte() == enumerer.getId_solde_de_tout_compte()) {
                found = true;
                break;
            }
        }
        assertTrue("L'objet attendu n'a pas été trouvé dans la liste", found);
    }

    @Test
    public void testUpdate() {
        enumerer.setReference_facture("REF789");
        enumerer.setId_solde_de_tout_compte(3);
        enumererDAO.update(enumerer);

        Enumerer updatedEnumerer = enumererDAO.findOne(enumerer.getId_solde_de_tout_compte());
        assertEquals("F003", updatedEnumerer.getReference_facture());
        assertEquals(3, updatedEnumerer.getId_solde_de_tout_compte());
    }

    @Test
    public void testFKEnumerer() throws Exception {
        String sql = "{ CALL db1_sae.TestFK_Enumerer(?, ?) }";

        // Nettoyage de la table Enumerer pour éviter les doublons
        String deleteSql = "DELETE FROM db1_sae.Enumerer WHERE reference_facture = ? AND id_solde_de_tout_compte = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setString(1, "F001");
            deleteStmt.setInt(2, 1);
            deleteStmt.executeUpdate();
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
