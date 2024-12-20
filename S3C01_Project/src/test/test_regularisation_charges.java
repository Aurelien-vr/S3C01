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
	
	//@Test
	//public void testCreateEntities() {
		//try{
			//Date date_effet = result.getDate("date_effet");
			//BigDecimal charge_eau = result.getBigDecimal("charge_eau");
			//BigDecimal charge_ordure_menagere = result.getBigDecimal("charge_ordure_menagere");
			//BigDecimal charge_eclairage = result.getBigDecimal("charge_eclairage");
			//BigDecimal provision_pour_charge = result.getBigDecimal("provision_pour_charge");
			//BigDecimal indice = result.getBigDecimal("indice");
			//String entretien = result.getString("entretien");
			//Regularisation_charges regu = regularisation_chargesDAO.createEntities(result);
			//assertEquals(regu.getDate_effet(), date_effet);
			//assertEquals(regu.getCharge_eau(), charge_eau);
			//assertEquals(regu.getCharge_ordure_menagere(), charge_ordure_menagere);
			//assertEquals(regu.getCharge_eclairage(), charge_eclairage);
			//assertEquals(regu.getProvision_pour_charge(), provision_pour_charge);
			//assertEquals(regu.getIndice(), indice);
			//assertEquals(regu.getEntretien(), entretien);
		//}catch (Exception e) {
			//ExceptionStorageHandler.LogException(e, connection);
		//} 
	//}

}
