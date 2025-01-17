package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import db_connection.DatabaseConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import java.util.List;

import exception.ExceptionStorageHandler;

public class TestAvisTaxeFonciere {
	
	private AvisTaxeFonciereDAO avisTaxeFonciereDAO;
	private Connection connection;
	private AvisTaxeFonciere avisTaxeFonciere;
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
		avisTaxeFonciereDAO = DAOFactory.createAvisTaxeFonciereDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Avis_Taxe_Fonciere(date_etablissement, debiteur_legaux,total_cotisation) " +
	               "VALUES ('2009-09-09','PaulSab',10.2)";

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
	
	avisTaxeFonciere = new AvisTaxeFonciere(Date.valueOf("2009-09-09"),"PaulSab",10.2);

	
	}
	

	@After
	public void tearDown() {
		avisTaxeFonciereDAO = null;
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(avisTaxeFonciereDAO.findOne(idInsertSetup),avisTaxeFonciere); 
	}
	
	@Test
	public void testInsert() {
		AvisTaxeFonciere taxe = new AvisTaxeFonciere(Date.valueOf("2019-10-09"),"Coucou and co",15);
		avisTaxeFonciereDAO.insert(taxe);
		assertEquals(taxe, avisTaxeFonciereDAO.findOne(idInsertSetup++));

	}
	
	@Test
	public void testDelete() {
		avisTaxeFonciereDAO.deleteById(idInsertSetup);
		assertNull(avisTaxeFonciereDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
		    List<AvisTaxeFonciere> avis = avisTaxeFonciereDAO.findAll();
		    int nombreAvisDansLaBase = 0;

		    // Récupérer le nombre total d'actes dans la base avec une requête SQL
		    PreparedStatement statement = null;
		    ResultSet result = null;
		    String query = "SELECT COUNT(*) FROM db1_sae.Avis_Taxe_Fonciere";
		    try {
		        statement = connection.prepareStatement(query);
		        result = statement.executeQuery();
		        if (result.next()) {
		            nombreAvisDansLaBase = result.getInt(1); 
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
			    assertEquals(nombreAvisDansLaBase, avis.size());
			    
			    //Vérifie que l'acte inséré au setUp() est bien dans la liste des actes
			    assertEquals(true, avis.contains(avisTaxeFonciere));
		    }
		} 
	
	@Test
	public void testUpdate() {
	    Date nouvelDate = Date.valueOf("2019-10-10");
	    String nouveauDeb = "IUT";
	    
	    avisTaxeFonciere.setNumeroFiscal(idInsertSetup);
	    avisTaxeFonciere.setDateEtablissement(nouvelDate);
	    avisTaxeFonciere.setDebiteurLegaux(nouveauDeb);
	    
	    avisTaxeFonciereDAO.update(avisTaxeFonciere);
	    
	    assertEquals(nouvelDate, avisTaxeFonciere.getDateEtablissement());
	    assertEquals(nouveauDeb, avisTaxeFonciere.getDebiteurLegaux());
	}
	
	@Test
    public void testCKAvisTaxeFonciere() {
        String sql = "{ CALL db1_sae.TestCK_TaxeFonciere(?,?)}";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
        	callableStatement.setInt(1, 248);
            callableStatement.setDate(2, Date.valueOf("2010-10-10")); 
            

            callableStatement.execute();

        } catch (Exception e) {
            assertEquals("Success", e.getMessage());
        }
    }
	
	@Test
    public void testFKTaxeFonciere(){
        String sql = "{ CALL db1_sae.TestFK_TaxeFonciere(?) }";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            
            callableStatement.setInt(1, 1);

            callableStatement.execute();

        } catch (Exception e) {
            
            assertEquals("Success", e.getMessage());
        }
    }

}