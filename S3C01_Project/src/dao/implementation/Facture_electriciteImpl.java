package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Facture_electriciteDAO;
import dao.entities.Facture_electricite;

/**
 * Implémentation de l'interface {@link Facture_electriciteDAO} pour gérer les opérations sur les entités "Facture_electricite".
 */
public class Facture_electriciteImpl implements Facture_electriciteDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Facture_electriciteImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Facture_electriciteImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Facture_electricite par son identifiant.
     *
     * @param id L'identifiant de la facture d'électricité à rechercher.
     * @return L'entité {@link Facture_electricite} si trouvée, sinon {@code null}.
     */
    @Override
    public Facture_electricite findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture_electricite WHERE id_facture_electricite = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de la facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Facture_electricite
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

        return null; // Si aucune entité Facture_electricite n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les entités Facture_electricite.
     *
     * @return Liste des entités Facture_electricite.
     */
    @Override
    public List<Facture_electricite> findAll() {
        // Implémentation à ajouter pour récupérer toutes les entités Facture_electricite
        return null;
    }

    /**
     * Crée une nouvelle entité Facture_electricite dans la base de données.
     *
     * @param entity L'entité Facture_electricite à créer.
     */
    @Override
    public void insert(Facture_electricite entity) {
        // Implémentation de l'insertion de l'entité Facture_electricite dans la base
    }

    /**
     * Met à jour une entité Facture_electricite existante dans la base de données.
     *
     * @param entity L'entité Facture_electricite à mettre à jour.
     */
    @Override
    public void update(Facture_electricite entity) {
        // Implémentation de la mise à jour de l'entité Facture_electricite dans la base
    }

    /**
     * Supprime une entité Facture_electricite de la base de données.
     *
     * @param entity L'entité Facture_electricite à supprimer.
     */
    @Override
    public void delete(Facture_electricite entity) {
        // Implémentation de la suppression de l'entité Facture_electricite de la base
    }

    /**
     * Supprime une entité Facture_electricite par son identifiant.
     *
     * @param id L'identifiant de la facture d'électricité à supprimer.
     */
    @Override
    public void deleteById(long id) {
        // Implémentation de la suppression de l'entité Facture_electricite par son identifiant
    }

    /**
     * Crée une entité {@link Facture_electricite} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Facture_electricite.
     * @return L'entité Facture_electricite construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Facture_electricite createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture_electricite à partir des données du ResultSet
        Facture_electricite factureElectricite = new Facture_electricite();
        factureElectricite.setCompteur_electricite(result.getBigDecimal("compteur_electricite"));
        factureElectricite.setPrix_kw_electricite(result.getString("prix_kw_electricite"));
        return factureElectricite; // Retourne l'entité Facture_electricite construite
    }
}
