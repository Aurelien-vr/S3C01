package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Etat_des_lieuxDAO;
import dao.entities.Etat_des_lieux;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Etat_des_lieuxDAO} pour gérer les opérations sur les entités "Etat_des_lieux".
 */
public class Etat_des_lieuxImpl implements Etat_des_lieuxDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Etat_des_lieuxImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Etat_des_lieuxImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Etat_des_lieux par son identifiant.
     *
     * @param id L'identifiant de l'état des lieux à rechercher.
     * @return L'entité {@link Etat_des_lieux} si trouvée, sinon {@code null}.
     */
    @Override
    public Etat_des_lieux findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Etat_des_lieux WHERE id_etat_des_lieux = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de l'état des lieux
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Etat_des_lieux
            if (result.next()) {
                return createEntities(result);
            }
        } catch (SQLException e) {
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

        return null; // Si aucune entité Etat_des_lieux n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les entités Etat_des_lieux.
     *
     * @return Liste des entités Etat_des_lieux.
     */
    @Override
    public List<Etat_des_lieux> findAll() {
        // Implémentation à ajouter pour récupérer toutes les entités Etat_des_lieux
        return null;
    }

    /**
     * Crée une nouvelle entité Etat_des_lieux dans la base de données.
     *
     * @param entity L'entité Etat_des_lieux à créer.
     */
    @Override
    public void insert(Etat_des_lieux entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Etat_des_lieux(date_signature, nombre_cles, etats_des_element, est_entrer)  VALUES (?,?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setDate(1, entity.getDate_signature());
    		statement.setInt(2, entity.getNombre_cles());
    		statement.setString(3, entity.getEtat_des_elements());
    		statement.setBoolean(4, entity.isEst_entrer());
    		
    			
    			
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
     * Met à jour une entité Etat_des_lieux existante dans la base de données.
     *
     * @param entity L'entité Etat_des_lieux à mettre à jour.
     */
    @Override
    public void update(Etat_des_lieux entity) {
        // Implémentation de la mise à jour de l'entité Etat_des_lieux dans la base
    }


    /**
     * Supprime une entité Etat_des_lieux par son identifiant.
     *
     * @param id L'identifiant de l'état des lieux à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Etat_des_lieux WHERE id_etat_des_lieux = ?";
        
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
     * Crée une entité {@link Etat_des_lieux} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Etat_des_lieux.
     * @return L'entité Etat_des_lieux construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Etat_des_lieux createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Etat_des_lieux à partir des données du ResultSet
        Etat_des_lieux etatDesLieux = new Etat_des_lieux();
        etatDesLieux.setDate_signature(result.getDate("date_signature"));
        etatDesLieux.setNombre_cles(result.getInt("nombre_cles"));
        etatDesLieux.setEtat_des_elements(result.getString("etats_des_element"));
        etatDesLieux.setEst_entrer(result.getBoolean("est_entrer"));
        return etatDesLieux; // Retourne l'entité Etat_des_lieux construite
    }
}
