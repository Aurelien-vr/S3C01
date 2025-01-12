package test;

import org.junit.*;
import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_assurance {
    
    private AssuranceDAO assuranceDAO;
    private Connection connection;
    private Assurance assurance;
    int idInsertSetup;
    
    @Before
    public void setUp() throws Exception {
        connection = DatabaseConnection.getInstance();
        if (connection == null) {
            new App();
            connection = DatabaseConnection.getInstance();
        }
        connection.setAutoCommit(false);
        assuranceDAO = DAOFactory.createAssuranceDAO();
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Assurance(Date_assurance, prime, protection_juridique) "
                     + "VALUES('2012-12-12', 80, 2)"; // Date corrigée au format yyyy-MM-dd
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    idInsertSetup = result.getInt(1); // Récupération de l'id
                }
                DatabaseConnection.closeResult(result);
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        }

        assurance = new Assurance(
            Date.valueOf("2012-12-12"),
            new BigDecimal(5).setScale(2, RoundingMode.DOWN),
            new BigDecimal(2).setScale(2, RoundingMode.DOWN)
        );
    }

    @After
    public void tearDown() throws Exception {
        assuranceDAO = null;
        connection.rollback();
    }

    @Test
    public void testFindOne() {
        // Créer une assurance avec un numéro de contrat valide
        Assurance assurance = new Assurance(
            Date.valueOf("2012-12-12"), 
            new BigDecimal(5.00).setScale(2, RoundingMode.DOWN), 
            new BigDecimal(2.00).setScale(2, RoundingMode.DOWN) // Protection juridique
        );
        assuranceDAO.insert(assurance); // Insère l'objet dans la base de données

        // Vérifie que le numéro de contrat a bien été affecté
        System.out.println(assurance.getNumero_contrat());

        // Vérifie si l'assurance récupérée est identique à celle insérée
        Assurance retrievedAssurance = assuranceDAO.findOne(assurance.getNumero_contrat());
        assertNotNull(retrievedAssurance); // Assure-toi que l'objet récupéré n'est pas nul
        assertEquals(assurance, retrievedAssurance); // Compare les deux objets
    }

    @Test
    public void testInsert() {
        Assurance ass = new Assurance(
            Date.valueOf("2011-11-11"), 
            new BigDecimal(4.3).setScale(2, RoundingMode.DOWN), 
            new BigDecimal(1).setScale(2, RoundingMode.DOWN) // Protection juridique explicitement définie
        );
        assuranceDAO.insert(ass);

        // Vérification que l'ID a bien été généré
        assertNotEquals(0, ass.getNumero_contrat()); // L'ID ne doit pas être égal à 0

        // Essayer de récupérer l'entité en utilisant l'ID généré
        Assurance retrievedAss = assuranceDAO.findOne(ass.getNumero_contrat());

        // Vérification que l'entité récupérée n'est pas nulle
        assertNotNull(retrievedAss);

        // Comparaison des valeurs attendues et récupérées
        assertEquals(ass.getNumero_contrat(), retrievedAss.getNumero_contrat());
        assertEquals(ass.getPrime(), retrievedAss.getPrime());
        assertEquals(ass.getDateAssurance(), retrievedAss.getDateAssurance());
        assertEquals(ass.getProtection_juridique(), retrievedAss.getProtection_juridique());
        assertEquals(ass.getId_bien(), retrievedAss.getId_bien());
    }


    @Test
    public void testDelete() {
        assuranceDAO.deleteById(idInsertSetup);
        assertNull(assuranceDAO.findOne(idInsertSetup));
    }
    
    @Test
    public void testFindAll() {
        List<Assurance> ass = assuranceDAO.findAll();
        int nombreAssDansLaBase = 0;

        // Récupérer le nombre total d'actes dans la base avec une requête SQL
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT COUNT(*) FROM db1_sae.Assurance";
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            if (result.next()) {
                nombreAssDansLaBase = result.getInt(1);
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
        }

        System.out.println("Assurances dans la liste: " + ass.size());
        ass.forEach(a -> System.out.println("Assurance dans la liste: " + a));

        // Vérifie si la liste contient l'assurance insérée
        assertEquals(nombreAssDansLaBase, ass.size());
        assertTrue("L'objet assurance n'a pas été trouvé dans la liste", ass.contains(assurance)); // Message d'erreur détaillé
    }



    @Test
    public void testUpdate() {
        BigDecimal nouvelPrime = new BigDecimal(7).setScale(2, RoundingMode.DOWN);
        BigDecimal nouvelPro = new BigDecimal(7).setScale(2, RoundingMode.DOWN);
        
        assurance.setNumero_contrat(idInsertSetup);
        assurance.setPrime(nouvelPrime);
        assurance.setProtection_juridique(nouvelPro);
        
        assuranceDAO.update(assurance);
        
        assertEquals(nouvelPrime, assurance.getPrime());
        assertEquals(nouvelPro, assurance.getProtection_juridique());
    }

    @Test
    public void testCKAssurance() throws Exception {
        String sql = "{ CALL db1_sae.TestCK_Assurance(?,?,?) }";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setBigDecimal(1, new BigDecimal("900.00"));
            callableStatement.setBigDecimal(2, new BigDecimal("500.00"));
            callableStatement.setDate(3, Date.valueOf("2010-10-10")); 

            callableStatement.execute();

        } catch (Exception e) {
            assertEquals("Success", e.getMessage());
        }
    }
    
    @Test
    public void testFKAssurance() throws Exception {
        String sql = "{ CALL db1_sae.TestFK_Assurance(?) }";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            
            callableStatement.setInt(1, 2);

            callableStatement.execute();

        } catch (Exception e) {
            
            assertEquals("Success", e.getMessage());
        }
    }
    
    @Test
    public void testProcedureGetAssurances() {
        String sql = "{ CALL db1_sae.get_assurances() }";
        String sqlVerif = "SELECT Adresse, Date_assurance, Prime, Protection_juridique FROM db1_sae.Assurance a JOIN db1_sae.Bien b ON b.Id_Bien = a.Id_Bien LIMIT 1"; // Récupère la première ligne
        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Vérifie si le ResultSet contient des résultats
                assertTrue(resultSet.next()); 

                // Récupère les valeurs réelles de la base pour la première ligne
                try (Statement stmt = connection.createStatement();
                     ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

                    assertTrue(expectedResultSet.next());

                    String adresseAttendue = expectedResultSet.getString("Adresse");
                    Date dateAssuranceAttendue = expectedResultSet.getDate("Date_assurance");
                    BigDecimal primeAttendue = expectedResultSet.getBigDecimal("Prime");
                    BigDecimal protectionJuridiqueAttendue = expectedResultSet.getBigDecimal("Protection_juridique");

                    // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
                    String adresse = resultSet.getString("Adresse");
                    Date dateAssurance = resultSet.getDate("Date_assurance");
                    BigDecimal prime = resultSet.getBigDecimal("Prime");
                    BigDecimal protectionJuridique = resultSet.getBigDecimal("Protection_juridique");

                    assertEquals(adresseAttendue, adresse);
                    assertEquals(dateAssuranceAttendue, dateAssurance);
                    assertEquals(primeAttendue, prime);
                    assertEquals(protectionJuridiqueAttendue, protectionJuridique);
                }
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
            fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
        }
    }



}
