package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.LocataireDAO;
import dao.entities.Locataire;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link LocataireDAO} pour gérer les opérations sur les entités "Locataire".
 */
public class LocataireImpl implements LocataireDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe LocataireImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public LocataireImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un locataire par son identifiant.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link Locataire} si trouvée, sinon {@code null}.
     */
    @Override
    public Locataire findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Locataire WHERE id_locataire = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant du locataire
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Locataire
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

        return null; // Si aucun locataire n'est trouvé, retour de null
    }

    /**
     * Recherche tous les locataires (fonctionnalité à implémenter).
     *
     * @return Liste des locataires ou {@code null} si non implémentée.
     */
    @Override
    public List<Locataire> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée un nouveau locataire dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Locataire à créer.
     */
    @Override
    public void insert(Locataire entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Met à jour un locataire existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Locataire à mettre à jour.
     */
    @Override
    public void update(Locataire entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un locataire par son identifiant (fonctionnalité à implémenter).
     *
     * @param id L'identifiant du locataire à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Locataire WHERE Id_Locataire = ?";
        
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
     * Crée une entité {@link Locataire} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du locataire.
     * @return L'entité Locataire construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Locataire createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Locataire à partir des données du ResultSet
        Locataire locataire = new Locataire();
        locataire.setNom(result.getString("nom"));
        locataire.setPrenom(result.getString("prenom"));
        locataire.setDate_de_naissance(result.getDate("date_de_naissance"));
        locataire.setIban(result.getString("iban"));
        return locataire; // Retourne l'entité Locataire construite
    }
}
