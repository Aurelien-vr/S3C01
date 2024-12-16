package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.AssuranceDAO;
import dao.entities.Assurance;

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
     * Recherche toutes les assurances (fonctionnalité à implémenter).
     *
     * @return Liste des assurances ou {@code null} si non implémentée.
     */
    @Override
    public List<Assurance> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée une nouvelle assurance dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à créer.
     */
    @Override
    public void insert(Assurance entity) {
        // TODO Auto-generated method stub
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
     * Supprime une assurance de la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à supprimer.
     */
    @Override
    public void delete(Assurance entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une assurance par son numéro de contrat (fonctionnalité à implémenter).
     *
     * @param id Le numéro de contrat de l'assurance à supprimer.
     */
    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
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
