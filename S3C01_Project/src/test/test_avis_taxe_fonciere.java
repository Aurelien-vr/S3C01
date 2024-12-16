package test;


import org.junit.*;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.*;

import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_avis_taxe_fonciere {
	
	private Avis_Taxe_FonciereDAO avis_taxe_fonciereDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Avis_Taxe_Fonciere avis_taxe_fonciere;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
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
		avis_taxe_fonciereDAO.insert(avis_taxe_fonciere);
		assertEquals(avis_taxe_fonciere, avis_taxe_fonciereDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		avis_taxe_fonciereDAO.deleteById(idInsertSetup);
		assertNull(avis_taxe_fonciereDAO.findOne(idInsertSetup));
		}

}