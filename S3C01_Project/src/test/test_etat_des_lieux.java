package test;


import org.junit.*;

import application.App;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import java.sql.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_etat_des_lieux {
	
	private Etat_des_lieuxDAO etat_des_lieuxDAO;
	private Connection connection;
	private Etat_des_lieux etat_des_lieux;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection = DatabaseConnection.getInstance();
		if (connection == null) {
			new App();
			connection = DatabaseConnection.getInstance();
		}
		connection.setAutoCommit(false);
		etat_des_lieuxDAO = DAOFactory.createEtat_des_lieuxDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Etat_des_lieux(date_signature, nombre_cles, etats_des_element, est_entrer) "
				+ " VALUES('2024-12-09', 5, 'Detruit', true)";
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
	
	etat_des_lieux = new Etat_des_lieux(Date.valueOf("2024-12-09"),5, "Detruit",true);
	
	}
	

	@After
	public void tearDown() throws Exception {
		etat_des_lieuxDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(etat_des_lieuxDAO.findOne(idInsertSetup),etat_des_lieux); 
	}
	
	@Test
	public void testInsert() {
		etat_des_lieuxDAO.insert(etat_des_lieux);
		assertEquals(etat_des_lieux, etat_des_lieuxDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		etat_des_lieuxDAO.deleteById(idInsertSetup);
		assertNull(etat_des_lieuxDAO.findOne(idInsertSetup));
		}

}
