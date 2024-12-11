package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.List;

import dao.Facture_gazDAO;
import dao.entities.Facture_gaz;

/**
 * Implémentation de l'interface {@link Facture_electriciteDAO} pour gérer les opérations sur les entités "Facture_electricite".
 */
public class Facture_gazImpl implements Facture_gazDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Facture_gazImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Facture_gazImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Facture_gaz par son identifiant.
     *
     * @param id L'identifiant de la facture de gaz à rechercher.
     * @return L'entité {@link Facture_gaz} si trouvée, sinon {@code null}.
     */
    @Override
    public Facture_gaz findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture_gaz WHERE id_facture_gaz = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de la facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Facture_gaz
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

        return null; // Si aucune entité Facture_gaz n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les entités Facture_gaz.
     *
     * @return Liste des entités Facture_gaz.
     */
    @Override
    public List<Facture_gaz> findAll() {
        // Implémentation à ajouter pour récupérer toutes les entités Facture_gaz
        return null;
    }

    /**
     * Crée une nouvelle entité Facture_gaz dans la base de données.
     *
     * @param entity L'entité Facture_gaz à créer.
     */
    @Override
    public void insert(Facture_gaz entity) {
        // Implémentation de l'insertion de l'entité Facture_gaz dans la base
    }

    /**
     * Met à jour une entité Facture_gaz existante dans la base de données.
     *
     * @param entity L'entité Facture_gaz à mettre à jour.
     */
    @Override
    public void update(Facture_gaz entity) {
        // Implémentation de la mise à jour de l'entité Facture_gaz dans la base
    }

    /**
     * Supprime une entité Facture_gaz de la base de données.
     *
     * @param entity L'entité Facture_gaz à supprimer.
     */
    @Override
    public void delete(Facture_gaz entity) {
        // Implémentation de la suppression de l'entité Facture_gaz de la base
    }

    /**
     * Supprime une entité Facture_gaz par son identifiant.
     *
     * @param id L'identifiant de la facture de gaz à supprimer.
     */
    @Override
    public void deleteById(Facture_gaz entity) {
        // Implémentation de la suppression de l'entité Facture_gaz par son identifiant
    }

    /**
     * Crée une entité {@link Facture_gaz} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Facture_gaz.
     * @return L'entité Facture_gaz construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Facture_gaz createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture_gaz à partir des données du ResultSet
        Facture_gaz factureGaz = new Facture_gaz();
        factureGaz.setId_facture_gaz(result.getInt("id_facture_gaz"));
        factureGaz.setConsommation_m3(result.getBigDecimal("consommation_m3"));
        factureGaz.setPrix_m3_gaz(result.getString("prix_m3_gaz"));
        factureGaz.setReference_facture(result.getString("reference_facture"));
        return factureGaz; // Retourne l'entité Facture_electricite construite
    }
}
