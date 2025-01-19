package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.EnumererDAO;
import dao.entities.Enumerer;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link EnumererDAO} pour gérer les opérations sur les entités "Enumerer".
 */
public class EnumererImpl implements EnumererDAO {

    private Connection connection;

    /**
     * Constructeur de la classe EnumererImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public EnumererImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Enumerer par son identifiant.
     *
     * @param id L'identifiant de l'entité.
     * @return L'entité {@link Enumerer} si trouvée, sinon {@code null}.
     */
    @Override
    public Enumerer findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Reference_facture, Id_Solde_de_tout_compte FROM db1_sae.Enumerer WHERE id_solde_de_tout_compte = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return createEntities(result);
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }

    /**
     * Recherche toutes les entités Enumerer.
     *
     * @return Liste des entités {@link Enumerer}.
     */
    @Override
    public List<Enumerer> findAll() {
        List<Enumerer> enumererList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Reference_facture, Id_Solde_de_tout_compte FROM db1_sae.Enumerer";

        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                enumererList.add(createEntities(result));
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return enumererList;
    }

    /**
     * Insère une nouvelle entité Enumerer dans la base de données.
     *
     * @param entity L'entité {@link Enumerer} à insérer.
     */
    @Override
    public void insert(Enumerer entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Enumerer (reference_facture, id_solde_de_tout_compte) VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getReferenceFacture());
            statement.setInt(2, entity.getIdSoldeDeToutCompte());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Met à jour une entité Enumerer existante dans la base de données.
     *
     * @param entity L'entité {@link Enumerer} à mettre à jour.
     */
    @Override
    public void update(Enumerer entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Enumerer SET reference_facture = ? WHERE id_solde_de_tout_compte = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getReferenceFacture());
            statement.setInt(2, entity.getIdSoldeDeToutCompte());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Supprime une entité Enumerer de la base de données.
     *
     * @param id L'identifiant de l'entité à supprimer.
     */
    @Override
    public void deleteById(long id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Enumerer WHERE id_solde_de_tout_compte = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Enumerer} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité.
     * @return L'entité {@link Enumerer} construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Enumerer createEntities(ResultSet result) throws SQLException {
        String referenceFacture = result.getString("reference_facture");
        int idSoldeDeToutCompte = result.getInt("id_solde_de_tout_compte");
        return new Enumerer(referenceFacture, idSoldeDeToutCompte);
    }
}
