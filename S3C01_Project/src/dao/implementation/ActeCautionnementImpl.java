package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ActeCautionnementDAO;
import dao.entities.ActeCautionnement;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link ActeCautionnementDAO} pour gérer les opérations sur les entités "Acte_cautionnement".
 */
public class ActeCautionnementImpl implements ActeCautionnementDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe ActeCautionnementImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public ActeCautionnementImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche un acte de cautionnement par son identifiant.
     * 
     * @param id L'identifiant de l'acte de cautionnement à rechercher.
     * @return L'entité {@link ActeCautionnement} si trouvée, sinon {@code null}.
     */
    @Override
    public ActeCautionnement findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Acte_cautionnement WHERE Id_Acte_cautionnement = ?";
        
        try {
            // Préparation de la requête SQL avec l'ID de l'acte
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            // Si un résultat est trouvé, création de l'entité Acte_cautionnement
            if (result.next()) {
                return createEntities(result);
            } 
        } catch (Exception e) {
            e.printStackTrace();  // Affichage de l'exception pour le débogage
        } finally {
            // Fermeture des ressources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
       			ExceptionStorageHandler.logException(e, connection);
       		}finally {
       			DatabaseConnection.closeStatement(statement);
       		}
        }
        
        return null; // Si aucun acte n'est trouvé, retour de null
    }

    /**
     * Recherche tous les actes de cautionnement (fonctionnalité à implémenter).
     * 
     * @return Liste des actes de cautionnement ou {@code null} si non implémentée.
     */
    @Override
    public List<ActeCautionnement> findAll() {
    	List<ActeCautionnement> actes = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Acte_cautionnement";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                ActeCautionnement acte = createEntities(result);
                actes.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
       			ExceptionStorageHandler.logException(e, connection);
       		}finally {
       			DatabaseConnection.closeStatement(statement);
       		}
        }
        
        return actes;
    }

    /**
     * Crée un nouvel acte de cautionnement dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Acte_cautionnement à créer.
     */
    @Override
    public void insert(ActeCautionnement entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Acte_cautionnement(montant_caution) VALUES (?)";
   		
   		try {
   			statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    		statement.setBigDecimal(1, entity.getMontantCaution());
            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setIdActeCautionnement(id);
                }
            }
   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }
    
    /**
     * Met à jour un acte de cautionnement existant dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Acte_cautionnement à mettre à jour.
     */
    @Override
    public void update(ActeCautionnement entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Acte_cautionnement SET Montant_caution = ? WHERE Id_Acte_cautionnement = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, entity.getMontantCaution());
            statement.setLong(2, entity.getIdActeCautionnement()); // Ajoute l'ID ici

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }



    /**
     * Supprime un acte de cautionnement par son identifiant (fonctionnalité à implémenter).
     * 
     * @param id L'identifiant de l'acte de cautionnement à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Acte_cautionnement WHERE Id_Acte_cautionnement = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link ActeCautionnement} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de l'acte de cautionnement.
     * @return L'entité Acte_cautionnement construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public ActeCautionnement createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Acte_cautionnement à partir des données du ResultSet
        ActeCautionnement acte = new ActeCautionnement();
        acte.setIdActeCautionnement(result.getInt(1));
        acte.setMontantCaution((result.getBigDecimal("Montant_caution")));
        return acte;  // Retourne l'entité Acte_cautionnement construite
    }

	

}
