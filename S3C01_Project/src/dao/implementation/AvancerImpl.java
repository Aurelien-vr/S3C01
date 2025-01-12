package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.AvancerDAO;
import dao.entities.Avancer;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link AvancerDAO} pour gérer les opérations sur les entités "Avancer".
 */
public class AvancerImpl implements AvancerDAO {

    private Connection connection;

    /**
     * Constructeur de la classe AvancerImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public AvancerImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Avancer par son identifiant et le numéro de facture.
     *
     * @param id_locataire L'identifiant du locataire.
     * @param numero_facture Le numéro de facture.
     * @return L'entité {@link Avancer} si trouvée, sinon {@code null}.
     */
    @Override
    public Avancer findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Avancer WHERE id_locataire = ? ";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return createEntities(result);
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }

    /**
     * Recherche toutes les entités Avancer.
     *
     * @return Liste des entités {@link Avancer}.
     */
    @Override
    public List<Avancer> findAll() {
        List<Avancer> avancerList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Avancer";

        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                avancerList.add(createEntities(result));
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return avancerList;
    }

    /**
     * Insère une nouvelle entité Avancer dans la base de données.
     *
     * @param entity L'entité {@link Avancer} à insérer.
     */
    @Override
    public void insert(Avancer entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Avancer (id_locataire, numero_facture) VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, entity.getId_locataire());
            statement.setInt(2, entity.getNumero_facture());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Met à jour une entité Avancer existante dans la base de données.
     *
     * @param entity L'entité {@link Avancer} à mettre à jour.
     */
    @Override
    public void update(Avancer entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Avancer SET numero_facture = ? WHERE id_locataire = ? AND numero_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, entity.getNumero_facture());
            statement.setInt(2, entity.getId_locataire());
            statement.setInt(3, entity.getNumero_facture());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Supprime une entité Avancer de la base de données.
     *
     * @param id_locataire L'identifiant du locataire.
     * @param numero_facture Le numéro de facture.
     */
    @Override
    public void deleteById(long id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Avancer WHERE id_locataire = ? ";

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
     * Crée une entité {@link Avancer} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité.
     * @return L'entité {@link Avancer} construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Avancer createEntities(ResultSet result) throws SQLException {
        int id_locataire = result.getInt("id_locataire");
        int numero_facture = result.getInt("numero_facture");
        return new Avancer(id_locataire, numero_facture);
    }
}
