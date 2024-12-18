package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Acte_cautionnementDAO;
import dao.entities.Acte_cautionnement;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link ActeCautionnementDAO} pour gérer les opérations sur les entités "Acte_cautionnement".
 */
public class Acte_cautionnementImpl implements Acte_cautionnementDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe ActeCautionnementImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public Acte_cautionnementImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche un acte de cautionnement par son identifiant.
     * 
     * @param id L'identifiant de l'acte de cautionnement à rechercher.
     * @return L'entité {@link Acte_cautionnement} si trouvée, sinon {@code null}.
     */
    @Override
    public Acte_cautionnement findOne(long id) {
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
            } catch (SQLException e) {
                e.printStackTrace();
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
    public List<Acte_cautionnement> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée un nouvel acte de cautionnement dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Acte_cautionnement à créer.
     */
    @Override
    public void insert(Acte_cautionnement entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Acte_cautionnement(montant_caution) VALUES (?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setBigDecimal(1, entity.getMontant_caution());
    			
    			
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
     * Met à jour un acte de cautionnement existant dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Acte_cautionnement à mettre à jour.
     */
    @Override
    public void update(Acte_cautionnement entity) {
        // TODO Auto-generated method stub
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
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Acte_cautionnement} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de l'acte de cautionnement.
     * @return L'entité Acte_cautionnement construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Acte_cautionnement createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Acte_cautionnement à partir des données du ResultSet
        Acte_cautionnement acte = new Acte_cautionnement();
        acte.setMontant_caution((result.getBigDecimal("Montant_caution")));
        return acte;  // Retourne l'entité Acte_cautionnement construite
    }

	

}
