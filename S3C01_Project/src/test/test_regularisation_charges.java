package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_regularisation_charges {
	
	private Regularisation_chargesDAO regularisation_chargesDAO;
	private Connection connection;
	private Regularisation_charges regularisation_charges;
	int idInsertSetup;
	private ResultSet result;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		regularisation_chargesDAO = DAOFactory.createRegularisation_chargesDAO();
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
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	regularisation_charges = new Regularisation_charges(Date.valueOf("2023-09-08"), new BigDecimal(33).setScale(2, RoundingMode.DOWN), new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN),new BigDecimal(333).setScale(2, RoundingMode.DOWN),new BigDecimal(33).setScale(2, RoundingMode.DOWN), "Bien");
	
	}
	

	@After
	public void tearDown() throws Exception {
		regularisation_chargesDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(regularisation_chargesDAO.findOne(idInsertSetup),regularisation_charges); 
	}
	
	@Test
	public void testInsert() {
		Regularisation_charges charge = new Regularisation_charges(Date.valueOf("2024-09-18"), new BigDecimal(50).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN),new BigDecimal(50).setScale(2, RoundingMode.DOWN),new BigDecimal(900).setScale(2, RoundingMode.DOWN),new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Nul");
		regularisation_chargesDAO.insert(charge);
		assertEquals(charge, regularisation_chargesDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		regularisation_chargesDAO.deleteById(idInsertSetup);
		assertNull(regularisation_chargesDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Regularisation_charges> regus = regularisation_chargesDAO.findAll();
	    int nombreRegusDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Regularisation_charges";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreRegusDansLaBase = result.getInt(1); 
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
	        
	        //Vérifie que le nombre d'actes retournés par findAll() correspond au nombre réel d'actes dans la base
		    assertEquals(nombreRegusDansLaBase, regus.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, regus.contains(regularisation_charges));
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

	    regularisation_charges.setDate_effet(nouvelleDateEffet);
	    regularisation_charges.setCharge_eau(nouvelleChargeEau);
	    regularisation_charges.setCharge_ordure_menagere(nouvelleChargeOrdures);
	    regularisation_charges.setCharge_eclairage(nouvelleChargeEclairage);
	    regularisation_charges.setProvision_pour_charge(nouvelleProvisionPourCharge);
	    regularisation_charges.setIndice(nouvelIndice);
	    regularisation_charges.setEntretien(nouvelEntretien);

	    regularisation_chargesDAO.update(regularisation_charges);

	    assertEquals(nouvelleDateEffet, regularisation_charges.getDate_effet());
	    assertEquals(nouvelleChargeEau, regularisation_charges.getCharge_eau());
	    assertEquals(nouvelleChargeOrdures, regularisation_charges.getCharge_ordure_menagere());
	    assertEquals(nouvelleChargeEclairage, regularisation_charges.getCharge_eclairage());
	    assertEquals(nouvelleProvisionPourCharge, regularisation_charges.getProvision_pour_charge());
	    assertEquals(nouvelIndice, regularisation_charges.getIndice());
	    assertEquals(nouvelEntretien, regularisation_charges.getEntretien());
	}

}
