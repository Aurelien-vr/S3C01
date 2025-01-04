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

public class test_solde_de_tout_compte {
	
	private Solde_de_tout_compteDAO solde_de_tout_compteDAO;
	private Connection connection;
	private Solde_de_tout_compte solde_de_tout_compte;
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
		solde_de_tout_compteDAO = DAOFactory.createSolde_de_tout_compteDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Solde_de_tout_compte(reste_a_devoir, provision_pour_charges, caution) " +
	               "VALUES (20,30,50)";

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
	
	solde_de_tout_compte = new Solde_de_tout_compte(new BigDecimal(20).setScale(2, RoundingMode.DOWN), new BigDecimal(30).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN));

	
	}
	

	@After
	public void tearDown() throws Exception {
		solde_de_tout_compteDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(solde_de_tout_compteDAO.findOne(idInsertSetup),solde_de_tout_compte); 
	}
	
	@Test
	public void testInsert() {
		Solde_de_tout_compte solde = new Solde_de_tout_compte(new BigDecimal(200).setScale(2, RoundingMode.DOWN), new BigDecimal(300).setScale(2, RoundingMode.DOWN), new BigDecimal(500).setScale(2, RoundingMode.DOWN));
		solde_de_tout_compteDAO.insert(solde);
		assertEquals(solde, solde_de_tout_compteDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		solde_de_tout_compteDAO.deleteById(idInsertSetup);
		assertNull(solde_de_tout_compteDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<Solde_de_tout_compte> soldes = solde_de_tout_compteDAO.findAll();
	    int nombreSoldesDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Solde_de_tout_compte";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreSoldesDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreSoldesDansLaBase, soldes.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, soldes.contains(solde_de_tout_compte));
		
	    }
	}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauResteADoive = new BigDecimal(25).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleProvisionPourCharges = new BigDecimal(35).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleCaution = new BigDecimal(55).setScale(2, RoundingMode.DOWN);

	    solde_de_tout_compte.setReste_a_devoir(nouveauResteADoive);
	    solde_de_tout_compte.setProvision_pour_charges(nouvelleProvisionPourCharges);
	    solde_de_tout_compte.setCaution(nouvelleCaution);

	    solde_de_tout_compteDAO.update(solde_de_tout_compte);

	    assertEquals(nouveauResteADoive, solde_de_tout_compte.getReste_a_devoir());
	    assertEquals(nouvelleProvisionPourCharges, solde_de_tout_compte.getProvision_pour_charges());
	    assertEquals(nouvelleCaution, solde_de_tout_compte.getCaution());
	}


}