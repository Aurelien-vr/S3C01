package test;

import org.junit.*;
import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

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

import exception.ExceptionStorageHandler;

public class TestAssurance {
    
    private AssuranceDAO assuranceDAO;
    private Connection connection;
    private Assurance assurance;
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
            ExceptionStorageHandler.logException(e, connection);
        }

        assurance = new Assurance(
            Date.valueOf("2012-12-12"),
            new BigDecimal(5).setScale(2, RoundingMode.DOWN),
            new BigDecimal(2).setScale(2, RoundingMode.DOWN)
        );
    }

    @After
    public void tearDown(){
        assuranceDAO = null;
        try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testFindOne() {
        // Créer une assurance avec un numéro de contrat valide
        assurance = new Assurance(
            Date.valueOf("2012-12-12"), 
            BigDecimal.valueOf(5.00).setScale(2, RoundingMode.DOWN), 
            BigDecimal.valueOf(2.00).setScale(2, RoundingMode.DOWN) // Protection juridique
        );
        assuranceDAO.insert(assurance); // Insère l'objet dans la base de données

        // Vérifie si l'assurance récupérée est identique à celle insérée
        Assurance retrievedAssurance = assuranceDAO.findOne(assurance.getNumeroContrat());
        assertNotNull(retrievedAssurance); // Assure-toi que l'objet récupéré n'est pas null
        assertEquals(assurance, retrievedAssurance); // Compare les deux objets
    }

    @Test
    public void testInsert() {
        Assurance ass = new Assurance(
            Date.valueOf("2011-11-11"), 
            BigDecimal.valueOf(4.3).setScale(2, RoundingMode.DOWN), 
            new BigDecimal(1).setScale(2, RoundingMode.DOWN) // Protection juridique explicitement définie
        );
        assuranceDAO.insert(ass);
        
        
        // Vérification que l'ID a bien été généré
        assertNotEquals(0, ass.getNumeroContrat()); // L'ID ne doit pas être égal à 0

        // Essayer de récupérer l'entité en utilisant l'ID généré
        Assurance retrievedAss = assuranceDAO.findOne(ass.getNumeroContrat());

        // Vérification que l'entité récupérée n'est pas nulle
        assertNotNull(retrievedAss);

        // Comparaison des valeurs attendues et récupérées
        assertEquals(ass.getNumeroContrat(), retrievedAss.getNumeroContrat());
        assertEquals(ass.getPrime(), retrievedAss.getPrime());
        assertEquals(ass.getDateAssurance(), retrievedAss.getDateAssurance());
        assertEquals(ass.getProtectionJuridique(), retrievedAss.getProtectionJuridique());
        assertEquals(ass.getIdBien(), retrievedAss.getIdBien());
    }


    @Test
    public void testDelete() {
        assuranceDAO.deleteById(idInsertSetup);
        assertNull(assuranceDAO.findOne(idInsertSetup));
    }
    
    
    @Test
    public void testFindAll() {
        // Insérer plusieurs assurances pour le test
        Assurance ass1 = new Assurance(
            Date.valueOf("2021-01-01"), 
            new BigDecimal(100).setScale(2, RoundingMode.DOWN), 
            new BigDecimal(5).setScale(2, RoundingMode.DOWN)
        );
        Assurance ass2 = new Assurance(
            Date.valueOf("2022-02-02"), 
            new BigDecimal(200).setScale(2, RoundingMode.DOWN), 
            new BigDecimal(10).setScale(2, RoundingMode.DOWN)
        );
        assuranceDAO.insert(ass1);
        assuranceDAO.insert(ass2);

        // Appeler la méthode findAll
        List<Assurance> assurances = assuranceDAO.findAll();

        // Vérifications
        assertNotNull(assurances); // La liste ne doit pas être null
        assertTrue(assurances.size() >= 2); // Il doit y avoir au moins 2 assurances

        // Vérifier si les assurances insérées sont bien présentes
        assertTrue(assurances.stream().anyMatch(a -> 
            a.getDateAssurance().equals(ass1.getDateAssurance()) &&
            a.getPrime().equals(ass1.getPrime()) &&
            a.getProtectionJuridique().equals(ass1.getProtectionJuridique())
        ));
        assertTrue(assurances.stream().anyMatch(a -> 
            a.getDateAssurance().equals(ass2.getDateAssurance()) &&
            a.getPrime().equals(ass2.getPrime()) &&
            a.getProtectionJuridique().equals(ass2.getProtectionJuridique())
        ));
    }




    @Test
    public void testUpdate() {
        BigDecimal nouvelPrime = new BigDecimal(7).setScale(2, RoundingMode.DOWN);
        BigDecimal nouvelPro = new BigDecimal(7).setScale(2, RoundingMode.DOWN);
        
        assurance.setNumeroContrat(idInsertSetup);
        assurance.setPrime(nouvelPrime);
        assurance.setProtectionJuridique(nouvelPro);
        
        assuranceDAO.update(assurance);
        
        assertEquals(nouvelPrime, assurance.getPrime());
        assertEquals(nouvelPro, assurance.getProtectionJuridique());
    }

    @Test
    public void testCKAssurance(){
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
    public void testFKAssurance(){
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
            ExceptionStorageHandler.logException(e, connection);
            fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
        }
    }



}
