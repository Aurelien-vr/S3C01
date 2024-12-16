package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.AvancerDAO;
import dao.entities.Avancer;

/**
 * Implémentation de l'interface {@link AvancerDAO} pour gérer les opérations sur les entités "Avancer".
 */
public class AvancerImpl implements AvancerDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe AvancerImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public AvancerImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une avance par l'identifiant du locataire.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link Avancer} si trouvée, sinon {@code null}.
     */
    @Override
    public Avancer findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Avancer WHERE id_locataire = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant du locataire
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Avancer
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

        return null; // Si aucune avance n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les avances (fonctionnalité à implémenter).
     *
     * @return Liste des avances ou {@code null} si non implémentée.
     */
    @Override
    public List<Avancer> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée une nouvelle avance dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avancer à créer.
     */
    @Override
    public void insert(Avancer entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Met à jour une avance existante dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avancer à mettre à jour.
     */
    @Override
    public void update(Avancer entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une avance de la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avancer à supprimer.
     */
    @Override
    public void delete(Avancer entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une avance par l'identifiant du locataire (fonctionnalité à implémenter).
     *
     * @param id L'identifiant du locataire de l'avance à supprimer.
     */
    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
    }

    /**
     * Crée une entité {@link Avancer} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'avance.
     * @return L'entité Avancer construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Avancer createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Avancer à partir des données du ResultSet
        Avancer avancer = new Avancer();
        avancer.setId_locataire(result.getInt("id_locataire"));
        avancer.setNumero_facture(result.getInt("numero_facture"));
        return avancer; // Retourne l'entité Avancer construite
    }
}
