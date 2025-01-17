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

public class TestSoldeDeToutCompte {
	
	private SoldeDeToutCompteDAO soldeDeToutCompteDAO;
	private Connection connection;
	private SoldeDeToutCompte soldeDeToutCompte;
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
		soldeDeToutCompteDAO = DAOFactory.createSoldeDeToutCompteDAO();
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
			ExceptionStorageHandler.logException(e, connection);
		}
	
	soldeDeToutCompte = new SoldeDeToutCompte(new BigDecimal(20).setScale(2, RoundingMode.DOWN), new BigDecimal(30).setScale(2, RoundingMode.DOWN), new BigDecimal(50).setScale(2, RoundingMode.DOWN));

	
	}
	

	@After
	public void tearDown(){
		soldeDeToutCompteDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(soldeDeToutCompteDAO.findOne(idInsertSetup),soldeDeToutCompte); 
	}
	
	@Test
	public void testInsert() {
		SoldeDeToutCompte solde = new SoldeDeToutCompte(new BigDecimal(200).setScale(2, RoundingMode.DOWN), new BigDecimal(300).setScale(2, RoundingMode.DOWN), new BigDecimal(500).setScale(2, RoundingMode.DOWN));
		soldeDeToutCompteDAO.insert(solde);
		assertEquals(solde, soldeDeToutCompteDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		soldeDeToutCompteDAO.deleteById(idInsertSetup);
		assertNull(soldeDeToutCompteDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
	    List<SoldeDeToutCompte> soldes = soldeDeToutCompteDAO.findAll();
	    int nombreSoldesDansLaBase = 0;

	    // Récupérer le nombre total d'actes dans la base avec une requête SQL
	    PreparedStatement statement = null;
	    result = null;
	    String query = "SELECT COUNT(*) FROM db1_sae.Solde_de_tout_compte";
	    try {
	        statement = connection.prepareStatement(query);
	        result = statement.executeQuery();
	        if (result.next()) {
	            nombreSoldesDansLaBase = result.getInt(1); 
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
		    assertEquals(nombreSoldesDansLaBase, soldes.size());
		    
		    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
		    assertEquals(true, soldes.contains(soldeDeToutCompte));
		
	    }
	}
	
	@Test
	public void testUpdate() {
	    BigDecimal nouveauResteADoive = new BigDecimal(25).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleProvisionPourCharges = new BigDecimal(35).setScale(2, RoundingMode.DOWN);
	    BigDecimal nouvelleCaution = new BigDecimal(55).setScale(2, RoundingMode.DOWN);

	    soldeDeToutCompte.setResteADevoir(nouveauResteADoive);
	    soldeDeToutCompte.setProvisionPourCharges(nouvelleProvisionPourCharges);
	    soldeDeToutCompte.setCaution(nouvelleCaution);

	    soldeDeToutCompteDAO.update(soldeDeToutCompte);

	    assertEquals(nouveauResteADoive, soldeDeToutCompte.getResteADevoir());
	    assertEquals(nouvelleProvisionPourCharges, soldeDeToutCompte.getProvisionPourCharges());
	    assertEquals(nouvelleCaution, soldeDeToutCompte.getCaution());
	}
	
	@Test
    public void testFKSoldeDeToutCompte(){
        String sql = "{ CALL db1_sae.TestFK_SoldeDeToutCompte(?) }";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            
            callableStatement.setInt(1, 1);

            callableStatement.execute();

        } catch (Exception e) {
            
            assertEquals("Success", e.getMessage());
        }
    }


}