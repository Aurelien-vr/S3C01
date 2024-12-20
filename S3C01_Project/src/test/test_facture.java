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

public class test_facture {
	
	private FactureDAO factureDAO;
	private Connection connection;
	private Facture facture;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		factureDAO = DAOFactory.createFactureDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Facture(reference_facture, type_facture , date_facture , montant_facture, moyen_paiement) "
				+ " VALUES('12345','Payante', '2024-12-12', 50, 'Cheque')";
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
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	facture = new Facture("12345","Payante", Date.valueOf("2024-12-12"), new BigDecimal(50).setScale(2, RoundingMode.DOWN), "Cheque");
	
	}
	

	@After
	public void tearDown() throws Exception {
		factureDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(factureDAO.findOne(12345),facture); 
	}
	
	@Test
	public void testInsert() {
		Facture fact = new Facture("12346","Gratuite", Date.valueOf("2014-12-12"), new BigDecimal(500).setScale(2, RoundingMode.DOWN), "Espece");
		factureDAO.insert(fact);
		assertEquals(fact, factureDAO.findOne(12346));

	}
	
	@Test
	public void testDelete() {
		factureDAO.deleteById(idInsertSetup);
		assertNull(factureDAO.findOne(idInsertSetup));
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
	        ExceptionStorageHandler.LogException(e, connection);
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
	

}
