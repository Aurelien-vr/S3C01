package test;


import org.junit.*;
import dao.*;
import dao.entities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import java.sql.*;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class test_avancer {
	
	private AvancerDAO avancerDAO;
	private Connection connection = DatabaseConnection.getInstance();
	private Avancer avancer;
	int idInsertSetup;
	
	@Before
	public void setUp() throws Exception {
		connection.setAutoCommit(false);
		avancerDAO = DAOFactory.createAvancerDAO();
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Avancer() "
				+ " VALUES()";
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
	
	avancer = new Avancer();
	
	}
	

	@After
	public void tearDown() throws Exception {
		avancerDAO = null;
		connection.rollback();		
	}
	
	@Test
	public void testFindOne() {
		assertEquals(avancerDAO.findOne(idInsertSetup),avancer); 
	}
	
	@Test
	public void testInsert() {
		avancerDAO.insert(avancer);
		assertEquals(avancer, avancerDAO.findOne(idInsertSetup));

	}
	
	@Test
	public void testDelete() {
		avancerDAO.deleteById(idInsertSetup);
		assertNull(avancerDAO.findOne(idInsertSetup));
		}

}
