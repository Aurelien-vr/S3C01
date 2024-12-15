package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
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

    /**
     * Recherche un contrat de location par son identifiant.
     *
     * @param id L'identifiant du contrat de location.
     * @return L'entité Contrat_location si trouvée, sinon {@code null}.
     */
    @Override
    public Contrat_location findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Contrat_location WHERE Id_Contrat_location = ?";


        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
			
            if (result.next()) {
                return createEntities(result);
            }

        } catch (SQLException e) {
        	ExceptionStorageHandler.LogException(e, connection);
        } finally {
            if (result != null) DatabaseConnection.closeResult(result);
			if (statement != null) DatabaseConnection.closeStatement(statement);
        }
        return null;
    }
    
    /**
     * Crée un nouveau contrat de location dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à créer.
     */
	@Override
	public void insert(Contrat_location entity) {
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Contrat_location(Montant_loyer,Date_debut,Date_fin,Modalite_chauffage,Modalite_eau_chaude_sanitaire,Date_versement) VALUES (?,?,?,?,?,?);";
		
		try {
			statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, entity.getMontant_loyer());
			statement.setDate(2, entity.getDate_debut());
			statement.setDate(3, entity.getDate_fin());
			statement.setString(4, entity.getModalite_chauffage());
			statement.setString(5, entity.getModalite_eau_chaude_saniatire());
			statement.setDate(6, entity.getDate_versement());
			
			if(statement.executeUpdate()>0) {
				System.out.println("User inserted");
				ResultSet result = statement.getGeneratedKeys();
				if(result.next()) {
					int id = result.getInt(1);
					entity.setId_Contrat_location(id);
				}
				DatabaseConnection.closeResult(result);
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
    	PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement("SELECT * FROM db1_sae.Contrat_location");
			result = statement.executeQuery();
			
			List<Contrat_location> contrats_locations = new ArrayList<Contrat_location>();
			
			while(result.next()) {
				contrats_locations.add(createEntities(result));
			}
			return contrats_locations;
			
		} catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeResult(result);
		}
		return null;
		
    }

    /**
     * Supprime un contrat de location par son identifiant (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à supprimer par ID.
     */
	@Override
	public void deleteById(long id) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("DELETE FROM db1_sae.Contrat_location WHERE Id_Contrat_location = ?");
			statement.setLong(1, id);
			statement.executeUpdate();
		}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
		
	}

    /**
     * Met à jour un contrat de location existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à mettre à jour.
     */
    @Override
    public void update(Contrat_location entity) {
    	PreparedStatement statement = null;
    	try {
			statement = connection.prepareStatement("UPDATE db1_sae.Contrat_location"
					+ "SET Montant_loyer=?,Date_debut=?,Date_fin=?,Modalite_chauffage=?,Modalite_eau_chaude_sanitaire=?,Date_versement=?"
					+ "WHERE Id_Contrat_location = ?");
			
			statement.setInt(2, entity.getMontant_loyer());
			statement.setDate(3, entity.getDate_debut());
			statement.setDate(4, entity.getDate_fin());
			statement.setString(5, entity.getModalite_chauffage());
			statement.setString(6, entity.getModalite_eau_chaude_saniatire());
			statement.setDate(7, entity.getDate_versement());
			
			statement.execute();
			
		} catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
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
    
    
    /**
     * Crée une entité Contrat_location à partir des résultats d'une requête SQL.
     *
     * @param result Le ResultSet contenant les données du contrat de location.
     * @return L'entité Contrat_location construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Contrat_location createEntities(ResultSet result) throws SQLException {
    	Contrat_location contrat_location = new Contrat_location();
    	
    	contrat_location.setId_Contrat_location(result.getInt(1));
    	
    	contrat_location.setMontant_loyer(result.getInt(2));
    	
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
}

