package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AssuranceDAO;
import dao.entities.Assurance;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link AssuranceDAO} pour gérer les opérations sur les entités "Assurance".
 */
public class AssuranceImpl implements AssuranceDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe AssuranceImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public AssuranceImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une assurance par son numéro de contrat.
     *
     * @param id Le numéro de contrat de l'assurance à rechercher.
     * @return L'entité {@link Assurance} si trouvée, sinon {@code null}.
     */
    @Override
    public Assurance findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Assurance WHERE numero_contrat = ?";

        try {
            // Préparation de la requête SQL avec le numéro de contrat
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Assurance
            if (result.next()) {
                return createEntities(result);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Affichage de l'exception pour le débogage
        } finally {
            // Fermeture des ressources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Si aucune assurance n'est trouvée, retour de null
    }

    /**
     * Recherche tous les assurances
     * 
     * @return Liste des assurance
     */
    @Override
    public List<Assurance> findAll() {
    	List<Assurance> ass = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Assurance";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Assurance acte = createEntities(result);
                ass.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return ass;
    }

    /**
     * Crée une nouvelle assurance dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à créer.
     */
    @Override
    public void insert(Assurance entity) {
    	PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Assurance(prime, taux_augmentation, protection_juridique) VALUES (?,?,?)";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setBigDecimal(1, entity.getPrime());
			statement.setBigDecimal(2, entity.getTaux_augmentation());
			statement.setBigDecimal(3,entity.getProtection_juridique());
			
			
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
     * Met à jour une assurance existante dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à mettre à jour.
     */
    @Override
    public void update(Assurance entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une assurance par son numéro de contrat (fonctionnalité à implémenter).
     *
     * @param id Le numéro de contrat de l'assurance à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Assurance WHERE numero_contrat = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Assurance} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'assurance.
     * @return L'entité Assurance construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Assurance createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Assurance à partir des données du ResultSet
        Assurance assurance = new Assurance();
        assurance.setPrime(result.getBigDecimal("Prime"));
        assurance.setTaux_augmentation(result.getBigDecimal("Taux_augmentation"));
        assurance.setProtection_juridique(result.getBigDecimal("Protection_juridique"));
        return assurance; // Retourne l'entité Assurance construite
    }
}
