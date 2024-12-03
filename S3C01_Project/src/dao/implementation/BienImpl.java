package dao.implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.BienDAO;
import dao.entities.Bien;


public class BienImpl implements BienDAO{
	private Connection connection;
	
	public BienImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public Bien findOne(long id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM db1_sae.Bien WHERE Id_Bien = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				Bien bien = createEntities(result);
				return bien;
			}	
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			
		}
		
		return null;
	}

	@Override
	public List<Bien> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Bien entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Bien entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bien entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Bien entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bien createEntities(ResultSet result) throws SQLException {
		
		Bien bien = new Bien(result.getInt("Id_Bien"));
		
		bien.setEtage(result.getInt(2));
		
		bien.setAdresse(result.getString(3));
		
		bien.setVille(result.getString(4));
		
		bien.setSuperficie(result.getBigDecimal(5));
		
		bien.setNombre_de_piece(result.getInt(6));
		
		bien.setMeuble(result.getBoolean(7));
		
		bien.setAccesoire_prive(result.getString(8));
		
		bien.setAccesoire_commun(result.getString(9));
		
		bien.setId_contrat_location(result.getInt(10));
		
		bien.setEst_garage(result.getBoolean(11));
		
		return bien;
	}

}
