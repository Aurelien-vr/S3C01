package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;
import java.util.List;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_avis_taxe_fonciere {
	
	private Avis_Taxe_FonciereDAO avis_taxe_fonciereDAO;
	private Connection connection;
	private Avis_Taxe_Fonciere avis_taxe_fonciere;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		avis_taxe_fonciereDAO = DAOFactory.createAvis_Taxe_FonciereDAO();
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
			ExceptionStorageHandler.LogException(e, connection);
		}
	
	avis_taxe_fonciere = new Avis_Taxe_Fonciere(Date.valueOf("2009-09-09"),"PaulSab",10.2);

	
	}
	

	@After
	public void tearDown() throws Exception {
		avis_taxe_fonciereDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(avis_taxe_fonciereDAO.findOne(idInsertSetup),avis_taxe_fonciere); 
	}
	
	@Test
	public void testInsert() {
		Avis_Taxe_Fonciere taxe = new Avis_Taxe_Fonciere(Date.valueOf("2019-10-09"),"Coucou and co",15);
		avis_taxe_fonciereDAO.insert(taxe);
		assertEquals(taxe, avis_taxe_fonciereDAO.findOne(idInsertSetup+1));

	}
	
	@Test
	public void testDelete() {
		avis_taxe_fonciereDAO.deleteById(idInsertSetup);
		assertNull(avis_taxe_fonciereDAO.findOne(idInsertSetup));
		}
	
	@Test
	public void testFindAll() {
		    List<Avis_Taxe_Fonciere> avis = avis_taxe_fonciereDAO.findAll();
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
		        ExceptionStorageHandler.LogException(e, connection);
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
			    assertEquals(true, avis.contains(avis_taxe_fonciere));
		    }
		} 

}