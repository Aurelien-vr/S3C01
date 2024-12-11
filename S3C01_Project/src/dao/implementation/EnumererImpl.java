package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.EnumererDAO;
import dao.entities.Enumerer;

/**
 * Implémentation de l'interface {@link EnumererDAO} pour gérer les opérations sur les entités "Enumerer".
 */
public class EnumererImpl implements EnumererDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe EnumererImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public EnumererImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Enumerer par sa référence de facture.
     *
     * @param reference La référence de facture à rechercher.
     * @return L'entité {@link Enumerer} si trouvée, sinon {@code null}.
     */
    @Override
    public Enumerer findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Enumerer WHERE reference_facture = ?";

        try {
            // Préparation de la requête SQL avec la référence de facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Enumerer
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

        return null; // Si aucune entité Enumerer n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les entités Enumerer.
     *
     * @return Liste des entités Enumerer.
     */
    @Override
    public List<Enumerer> findAll() {
        // Implémentation à ajouter pour récupérer toutes les entités Enumerer
        return null;
    }

    /**
     * Crée une nouvelle entité Enumerer dans la base de données.
     *
     * @param entity L'entité Enumerer à créer.
     */
    @Override
    public void insert(Enumerer entity) {
        // Implémentation de l'insertion de l'entité Enumerer dans la base
    }

    /**
     * Met à jour une entité Enumerer existante dans la base de données.
     *
     * @param entity L'entité Enumerer à mettre à jour.
     */
    @Override
    public void update(Enumerer entity) {
        // Implémentation de la mise à jour de l'entité Enumerer dans la base
    }

    /**
     * Supprime une entité Enumerer de la base de données.
     *
     * @param entity L'entité Enumerer à supprimer.
     */
    @Override
    public void delete(Enumerer entity) {
        // Implémentation de la suppression de l'entité Enumerer de la base
    }

    /**
     * Supprime une entité Enumerer par sa référence de facture.
     *
     * @param reference La référence de facture de l'entité Enumerer à supprimer.
     */
    @Override
    public void deleteById(Enumerer entity) {
        // Implémentation de la suppression de l'entité Enumerer par sa référence de facture
    }

    /**
     * Crée une entité {@link Enumerer} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Enumerer.
     * @return L'entité Enumerer construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Enumerer createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Enumerer à partir des données du ResultSet
        Enumerer enumerer = new Enumerer();
        enumerer.setReference_facture(result.getString("reference_facture"));
        enumerer.setId_solde_de_tout_compte(result.getInt("id_solde_de_tout_compte"));
        return enumerer; // Retourne l'entité Enumerer construite
    }
}
