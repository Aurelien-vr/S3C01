package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestTravaux {
	
	private TravauxDAO travauxDAO;
	private Connection connection;
	private Travaux travaux;
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
		travauxDAO = DAOFactory.createTravauxDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Travaux(date_travaux, nature, iban, reduction, montant, montant_non_deductible, reduction_special) " +
	               "VALUES ('2024-08-08','bien','09876543210',33,50,20,3)";

	try {
		statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		if(statement.executeUpdate()>0) {
			result = statement.getGeneratedKeys();
			if(result.next()) {
				idInsertSetup = result.getInt(1);
			}
			DatabaseConnection.closeResult(result);
		}
	}catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}
	
	travaux = new Travaux(Date.valueOf("2024-08-08"), "bien", "09876543210",new BigDecimal(33).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN), new BigDecimal(20).setScale(2, RoundingMode.DOWN), new BigDecimal(3).setScale(2, RoundingMode.DOWN));

	
	}
	

	@After
	public void tearDown()  {
		travauxDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(travauxDAO.findOne(idInsertSetup),travaux); 
	}
	
	@Test
	public void testInsert() {
		Travaux tra = new Travaux(Date.valueOf("2024-09-09"), "Tres bien", "09456543210",new BigDecimal(73).setScale(2, RoundingMode.DOWN), new BigDecimal(55).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN), new BigDecimal(7).setScale(2, RoundingMode.DOWN));
		travauxDAO.insert(tra);
		assertEquals(tra, travauxDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		travauxDAO.deleteById(idInsertSetup);
		assertNull(travauxDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Travaux> tras = travauxDAO.findAll();
	    int nombreTrasDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Travaux";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreTrasDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreTrasDansLaBase, tras.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, tras.contains(travaux));
		
	    }
	} 
	
	@Test
	public void testUpdate() {
	    BigDecimal nouvelleReduction = new BigDecimal(40).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouveauMontant = new BigDecimal(60).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouveauMontantNonDeductible = new BigDecimal(30).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleReductionSpeciale = new BigDecimal(5).setScale(2, RoundingMode.DOWN);

	    travaux.setReduction(nouvelleReduction);
	    travaux.setMontant(nouveauMontant);
	    travaux.setMontantNonDeductible(nouveauMontantNonDeductible);
	    travaux.setReductionSpeciale(nouvelleReductionSpeciale);

	    travauxDAO.update(travaux);

	    assertEquals(nouvelleReduction, travaux.getReduction());
	    assertEquals(nouveauMontant, travaux.getMontant());
	    assertEquals(nouveauMontantNonDeductible, travaux.getMontantNonDeductible());
	    assertEquals(nouvelleReductionSpeciale, travaux.getReductionSpeciale());
	}
	
	
	@Test
	public void testUNTravaux() {
	    String sql = "{ CALL db1_sae.TestUN_Travaux(?) }";
	    String nonExistingReference = "999";

	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setString(1, nonExistingReference);
	        callableStatement.execute();
	        // On attend une exception avec le message "Success"
	    } catch (SQLException e) {
	    	assertEquals("Erreur : La Reference_facture n'existe pas dans la table Facture.", e.getMessage());
	    }
	}


	

}