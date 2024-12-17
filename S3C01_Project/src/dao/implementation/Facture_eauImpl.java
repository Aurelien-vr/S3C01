package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Facture_eauDAO;
import dao.entities.Facture_eau;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Facture_eauDAO} pour gérer les opérations sur les entités "Facture_eau".
 */
public class Facture_eauImpl implements Facture_eauDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Facture_eauImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Facture_eauImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Facture_eau par son identifiant.
     *
     * @param id L'identifiant de la facture d'eau à rechercher.
     * @return L'entité {@link Facture_eau} si trouvée, sinon {@code null}.
     */
    @Override
    public Facture_eau findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture_eau WHERE id_facture_eau = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de la facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Facture_eau
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

        return null; // Si aucune entité Facture_eau n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les entités Facture_eau.
     *
     * @return Liste des entités Facture_eau.
     */
    @Override
    public List<Facture_eau> findAll() {
        // Implémentation à ajouter pour récupérer toutes les entités Facture_eau
        return null;
    }

    /**
     * Crée une nouvelle entité Facture_eau dans la base de données.
     *
     * @param entity L'entité Facture_eau à créer.
     */
    @Override
    public void insert(Facture_eau entity) {
        // Implémentation de l'insertion de l'entité Facture_eau dans la base
    }

    /**
     * Met à jour une entité Facture_eau existante dans la base de données.
     *
     * @param entity L'entité Facture_eau à mettre à jour.
     */
    @Override
    public void update(Facture_eau entity) {
        // Implémentation de la mise à jour de l'entité Facture_eau dans la base
    }

    

    /**
     * Supprime une entité Facture_eau par son identifiant.
     *
     * @param id L'identifiant de la facture d'eau à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Facture_eau WHERE id_facture_eau = ?";
        
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
     * Crée une entité {@link Facture_eau} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Facture_eau.
     * @return L'entité Facture_eau construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Facture_eau createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture_eau à partir des données du ResultSet
        Facture_eau factureEau = new Facture_eau();
        factureEau.setPartie_fixe(result.getBigDecimal("partie_fixe"));
        factureEau.setConsommation(result.getBigDecimal("consommation"));
        return factureEau; // Retourne l'entité Facture_eau construite
    }
}
