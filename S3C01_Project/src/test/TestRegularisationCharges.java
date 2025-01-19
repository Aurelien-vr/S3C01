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

public class TestRegularisationCharges {
	
	private RegularisationChargesDAO regularisationChargesDAO;
	private Connection connection;
	private RegularisationCharges regularisationCharges;
	private int idInsertSetup;
	private ResultSet result;
	
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
		regularisationChargesDAO = DAOFactory.createRegularisationChargesDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Regularisation_charges(date_effet, charge_eau, charge_ordure_menagere, charge_eclairage, provision_pour_charge, indice, entretien) "
				+ " VALUES('2023-09-08', 33, 33,33,333,33, 'Bien')";
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
	
	regularisationCharges = new RegularisationCharges(Date.valueOf("2023-09-08"), new BigDecimal(33).setScale(2, RoundingMode.DOWN), new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(333).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN), "Bien");
	
	}
	

	@After
	public void tearDown() {
		regularisationChargesDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(regularisationChargesDAO.findOne(idInsertSetup),regularisationCharges); 
	}
	
	@Test
	public void testInsert() {
		RegularisationCharges charge = new RegularisationCharges(Date.valueOf("2024-09-18"), new BigDecimal(50).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN),new BigDecimal(50).setScale(2, RoundingMode.DOWN),new BigDecimal(900).setScale(2, RoundingMode.DOWN),new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Nul");
		regularisationChargesDAO.insert(charge);
		assertEquals(charge, regularisationChargesDAO.findOne(charge.getIdChargeLocataire()));

	}
	
	@Test
	public void testDelete() {
		regularisationChargesDAO.deleteById(idInsertSetup);
		assertNull(regularisationChargesDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<RegularisationCharges> regus = regularisationChargesDAO.findAll();
	    int nombreRegusDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Regularisation_charges";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreRegusDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreRegusDansLaBase, regus.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, regus.contains(regularisationCharges));
		}
	    
	} 
	

	@Test
	public void testUpdate() {
	    Date nouvelleDateEffet = Date.valueOf("2024-09-18");
	    BigDecimal nouvelleChargeEau = new BigDecimal(55).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleChargeOrdures = new BigDecimal(55).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleChargeEclairage = new BigDecimal(55).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleProvisionPourCharge = new BigDecimal(1000).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelIndice = new BigDecimal(55).setScale(2, RoundingMode.DOWN);
	    String nouvelEntretien = "Mauvais";

	    regularisationCharges.setDateEffet(nouvelleDateEffet);
	    regularisationCharges.setChargeEau(nouvelleChargeEau);
	    regularisationCharges.setChargeOrdureMenagere(nouvelleChargeOrdures);
	    regularisationCharges.setChargeEclairage(nouvelleChargeEclairage);
	    regularisationCharges.setProvisionPourCharge(nouvelleProvisionPourCharge);
	    regularisationCharges.setIndice(nouvelIndice);
	    regularisationCharges.setEntretien(nouvelEntretien);

	    regularisationChargesDAO.update(regularisationCharges);

	    assertEquals(nouvelleDateEffet, regularisationCharges.getDateEffet());
	    assertEquals(nouvelleChargeEau, regularisationCharges.getChargeEau());
	    assertEquals(nouvelleChargeOrdures, regularisationCharges.getChargeOrdureMenagere());
	    assertEquals(nouvelleChargeEclairage, regularisationCharges.getChargeEclairage());
	    assertEquals(nouvelleProvisionPourCharge, regularisationCharges.getProvisionPourCharge());
	    assertEquals(nouvelIndice, regularisationCharges.getIndice());
	    assertEquals(nouvelEntretien, regularisationCharges.getEntretien());
	}
	

	   @Test
	    public void testFKRegularisation() {
	        String sql = "{ CALL db1_sae.TestFK_RegularisationCharges(?) }";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	            
	            callableStatement.setInt(1, 1);

	            callableStatement.execute();

	        } catch (Exception e) {
	            
	            assertEquals("Success", e.getMessage());
	        }
	    }
	   
	   @Test
	    public void testCKRegularisation(){
	        String sql = "{ CALL db1_sae.TestCK_RegularisationCharges(?)}";

	        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        	callableStatement.setDate(1, Date.valueOf("2015-07-09"));
	            callableStatement.execute();

	        } catch (Exception e) {
	            assertEquals("Success", e.getMessage());
	        }
	    }

}
