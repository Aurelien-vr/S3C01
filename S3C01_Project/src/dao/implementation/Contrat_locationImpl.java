package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import dao.Contrat_locationDAO;
import dao.entities.Contrat_location;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class Contrat_locationImpl implements Contrat_locationDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Contrat_locationImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Contrat_locationImpl(Connection connection) {
        this.connection = connection;
    }

	@Override
	public Contrat_location findOne(long id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM db1_sae.Contrat_location WHERE Id_Contrat_location = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				Contrat_location contrat_location = createEntities(result);
				return contrat_location;
			}	
		}
		
		catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		finally {
			DatabaseConnection.closeStatement(statement);
		}
		
		return null;
	}


	@Override
	public void insert(Contrat_location entity) {
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_location(Montant_loyer,Date_debut) VALUES (?,?);";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, entity.getMontant_loyer());
			statement.setDate(2, entity.getDate_debut());
			
			if(statement.executeUpdate()>0) {
				System.out.println("User inserted");
			}
		} catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
	}




    /**
     * Recherche tous les contrats de location (fonctionnalité à implémenter).
     *
     * @return Liste des contrats de location.
     */
    @Override
    public List<Contrat_location> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public void deleteById(Contrat_location entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Contrat_location createEntities(ResultSet result) throws SQLException {
		Contrat_location contrat_location = new Contrat_location(result.getInt(1));
		
		int montant_loyer = result.getInt(2);
		contrat_location.setMontant_loyer(montant_loyer);
		
		Date date_debut = result.getDate(3);
		contrat_location.setDate_debut(date_debut);
		
		Date date_fin = result.getDate(4);
		contrat_location.setDate_fin(date_fin);
		
		String modalite_chauffage = result.getString(5);
		contrat_location.setModalite_chauffage(modalite_chauffage);
		
		String modalite_eau_chaude_saniatire = result.getString(6);
		contrat_location.setModalite_eau_chaude_saniatire(modalite_eau_chaude_saniatire);
		
		Date date_versement = result.getDate(7);
		contrat_location.setDate_versement(date_versement);
		
		return contrat_location;
	}

    /**
     * Met à jour un contrat de location existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à mettre à jour.
     */
    @Override
    public void update(Contrat_location entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un contrat de location de la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à supprimer.
     */
    @Override
    public void delete(Contrat_location entity) {
        // TODO Auto-generated method stub
    }


}
