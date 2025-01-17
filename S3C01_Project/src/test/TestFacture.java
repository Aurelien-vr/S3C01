package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestFacture {
	
	private String moyenDePaymentColumn = "Moyen_paiement";
	private String montantFactureColumn = "Montant_facture";
	private String dateFactureColumn = "Date_facture";
	private String typeFactureColumn = "Type_facture";
	private String adressColumn = "Adresse";
	private String referenceFactureColumn = "Reference_facture";
	private String typeFacture = "Payante";
	private String date20241212 = "2024-12-12";
	private FactureDAO factureDAO;
	private Connection connection;
	private Facture facture;
	int idInsertSetup;
	
	@Before
	public void setUp() {
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
		factureDAO = DAOFactory.createFactureDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture(reference_facture, type_facture , date_facture , montant_facture, moyen_paiement, montantNonDeductible, Reduction) "
				+ " VALUES('F2','Payante', '2024-12-12', 50, 'Cheque', 20, 3)";
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
	
	facture = new Facture(typeFacture, Date.valueOf(date20241212), new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Cheque");
	facture.setReferenceFacture("F2");
	}
	

	@After
	public void tearDown() {
		factureDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureDAO.findOneRef(facture.getReferenceFacture()),facture); 
	}
	
	@Test
	public void testInsert(){
	    Facture newFacture = new Facture(typeFacture, Date.valueOf(date20241212), new BigDecimal(100).setScale(2, RoundingMode.DOWN), "Carte");
	    newFacture.setReferenceFacture("F1");
	    factureDAO.insert(newFacture);

	    // Vérifier que la facture a bien été insérée
	    Facture insertedFacture = factureDAO.findOneRef(newFacture.getReferenceFacture());
	    assertNotNull(insertedFacture);
	    assertEquals(newFacture.getReferenceFacture(), insertedFacture.getReferenceFacture());
	    assertEquals(newFacture.getMontantFacture(), insertedFacture.getMontantFacture());
	}

	
	@Test
	public void testDelete(){
	    // Insérer une facture pour être supprimée
	    Facture factureToDelete = new Facture(typeFacture, Date.valueOf(date20241212), new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Cheque");
	    factureToDelete.setReferenceFacture("F012");
	    factureDAO.insert(factureToDelete);
	    
	    // Convertir le String 'Reference_facture' en long
	    String referenceFacture = factureToDelete.getReferenceFacture();

	    factureDAO.deleteByRef(referenceFacture);  // Passer le long à deleteById

	   
	    // Vérifier que la facture a bien été supprimée
	    Facture deletedFacture = factureDAO.findOneRef(referenceFacture);
	    assertNull(deletedFacture);
	}



	
	@Test
	public void testFindAll() {
	    List<Facture> facts = factureDAO.findAll();
	    int nombreFactsDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Facture";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreFactsDansLaBase = result.getInt(1); 
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
	    }
	  //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
	    assertEquals(nombreFactsDansLaBase, facts.size());
	    
	    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
	    assertEquals(true, facts.contains(facture));
	}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauMontantFacture = new BigDecimal(100).setScale(2, RoundingMode.DOWN);
	    String nouveauMoyenPaiement = "Virement";

	    facture.setMontantFacture(nouveauMontantFacture);
	    facture.setMoyenPaiement(nouveauMoyenPaiement);

	    factureDAO.update(facture);

	    assertEquals(nouveauMontantFacture, facture.getMontantFacture());
	    assertEquals(nouveauMoyenPaiement, facture.getMoyenPaiement());
	}

	@Test
	public void testCKFacture(){
	    String sql = "{ CALL db1_sae.TestCK_Facture(?, ?, ?) }";

	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setBigDecimal(1, new BigDecimal("50.00"));
	        callableStatement.setBigDecimal(2, new BigDecimal("20.00"));
	        callableStatement.setDate(3, Date.valueOf("2024-02-02"));
	        
	        callableStatement.execute();
	    } catch (Exception e) {
	        assertEquals("Success", e.getMessage());
	    }
	}
	
	   @Test
	    public void testFKFacture(){
	        String sql = "{ CALL db1_sae.TestFK_Facture(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	   public void testProcedureGetFactures() {
		    String sql = "{ CALL db1_sae.get_factures() }";
		    String sqlVerif = "SELECT Reference_facture, b.Adresse, Type_facture, Date_facture, Montant_facture, Moyen_paiement FROM db1_sae.Facture f JOIN db1_sae.Bien b ON f.Id_Bien = b.Id_Bien LIMIT 1"; // Récupère la première ligne
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Vérifie si le ResultSet contient des résultats
		            assertTrue(resultSet.next());

		            // Récupère les valeurs réelles de la base pour la première ligne
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                String referenceFactureAttendue = expectedResultSet.getString(referenceFactureColumn);
		                String adresseAttendue = expectedResultSet.getString(adressColumn);
		                String typeFactureAttendue = expectedResultSet.getString(typeFactureColumn);
		                Date dateFactureAttendue = expectedResultSet.getDate(dateFactureColumn);
		                BigDecimal montantFactureAttendu = expectedResultSet.getBigDecimal(montantFactureColumn);
		                String moyenPaiementAttendu = expectedResultSet.getString(moyenDePaymentColumn);

		                // Récupère les valeurs de la procédure et les compare avec les valeurs attendues
		                String referenceFacture = resultSet.getString(referenceFactureColumn);
		                String adresse = resultSet.getString(adressColumn);
		                String typeFactureLoc = resultSet.getString(typeFactureColumn);
		                Date dateFacture = resultSet.getDate(dateFactureColumn);
		                BigDecimal montantFacture = resultSet.getBigDecimal(montantFactureColumn);
		                String moyenPaiement = resultSet.getString(moyenDePaymentColumn);

		                assertEquals(referenceFactureAttendue, referenceFacture);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(typeFactureAttendue, typeFactureLoc);
		                assertEquals(dateFactureAttendue, dateFacture);
		                assertEquals(montantFactureAttendu, montantFacture);
		                assertEquals(moyenPaiementAttendu, moyenPaiement);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
		    }
		}
	   
	   @Test
	   public void testProcedureGetNumFacture() {
		    String sql = "{ CALL db1_sae.get_numFacture() }";
		    String sqlVerif = "SELECT f.Reference_facture, f.Type_facture FROM db1_sae.Facture f LEFT JOIN db1_sae.Travaux t ON f.Reference_facture = t.Reference_facture WHERE t.Reference_facture IS NULL LIMIT 1"; // Retrieve the first row for verification
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Verify the ResultSet contains results
		            assertTrue(resultSet.next());

		            // Retrieve expected values from the database for comparison
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                String referenceFactureAttendue = expectedResultSet.getString(referenceFactureColumn);
		                String typeFactureAttendu = expectedResultSet.getString(typeFactureColumn);

		                // Retrieve the actual values from the procedure
		                String referenceFacture = resultSet.getString(referenceFactureColumn);
		                String typeFactureLoc = resultSet.getString(typeFactureColumn);

		                // Compare expected and actual values
		                assertEquals(referenceFactureAttendue, referenceFacture);
		                assertEquals(typeFactureAttendu, typeFactureLoc);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail("Erreur lors de l'appel de la procÃ©dure : " + e.getMessage());
		    }
		}
	   
	   @Test
	   public void testProcedureGetTravauxPageTravaux() {
		    String sql = "{ CALL db1_sae.get_travaux_page_travaux() }";
		    String sqlVerif = "SELECT t.Reference_facture, b.Adresse, b.Code_postal, b.Ville, b.Etage, t.Montant, t.Montant_non_deductible, t.Reduction_special, t.Date_travaux, t.Nature, t.Numero_facture FROM db1_sae.Travaux t JOIN db1_sae.Facture f ON t.Reference_facture = f.Reference_facture LEFT JOIN db1_sae.Bien b ON f.Id_Bien = b.Id_Bien LIMIT 1"; // Retrieve the first row for verification
		    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
		        try (ResultSet resultSet = callableStatement.executeQuery()) {
		            // Verify the ResultSet contains results
		            assertTrue(resultSet.next());

		            // Retrieve expected values from the database for comparison
		            try (Statement stmt = connection.createStatement();
		                    ResultSet expectedResultSet = stmt.executeQuery(sqlVerif)) {

		                assertTrue(expectedResultSet.next());

		                String referenceFactureAttendue = expectedResultSet.getString(referenceFactureColumn);
		                String adresseAttendue = expectedResultSet.getString(adressColumn);
		                String codePostalAttendu = expectedResultSet.getString("Code_postal");
		                String villeAttendue = expectedResultSet.getString("Ville");
		                String etageAttendu = expectedResultSet.getString("Etage");
		                BigDecimal montantAttendu = expectedResultSet.getBigDecimal("Montant");
		                BigDecimal montantNonDeductibleAttendu = expectedResultSet.getBigDecimal("Montant_non_deductible");
		                BigDecimal reductionSpecialAttendue = expectedResultSet.getBigDecimal("Reduction_special");
		                Date dateTravauxAttendue = expectedResultSet.getDate("Date_travaux");
		                String natureAttendue = expectedResultSet.getString("Nature");
		                String numeroFactureAttendu = expectedResultSet.getString("Numero_facture");

		                // Retrieve the actual values from the procedure
		                String referenceFacture = resultSet.getString(referenceFactureColumn);
		                String adresse = resultSet.getString(adressColumn);
		                String codePostal = resultSet.getString("Code_postal");
		                String ville = resultSet.getString("Ville");
		                String etage = resultSet.getString("Etage");
		                BigDecimal montant = resultSet.getBigDecimal("Montant");
		                BigDecimal montantNonDeductible = resultSet.getBigDecimal("Montant_non_deductible");
		                BigDecimal reductionSpecial = resultSet.getBigDecimal("Reduction_special");
		                Date dateTravaux = resultSet.getDate("Date_travaux");
		                String nature = resultSet.getString("Nature");
		                String numeroFacture = resultSet.getString("Numero_facture");

		                // Compare expected and actual values
		                assertEquals(referenceFactureAttendue, referenceFacture);
		                assertEquals(adresseAttendue, adresse);
		                assertEquals(codePostalAttendu, codePostal);
		                assertEquals(villeAttendue, ville);
		                assertEquals(etageAttendu, etage);
		                assertEquals(montantAttendu, montant);
		                assertEquals(montantNonDeductibleAttendu, montantNonDeductible);
		                assertEquals(reductionSpecialAttendue, reductionSpecial);
		                assertEquals(dateTravauxAttendue, dateTravaux);
		                assertEquals(natureAttendue, nature);
		                assertEquals(numeroFactureAttendu, numeroFacture);
		            }
		        }
		    } catch (Exception e) {
		        ExceptionStorageHandler.logException(e, connection);
		        fail("Erreur lors de l'appel de la procédure : " + e.getMessage());
		    }
		}

	

}
