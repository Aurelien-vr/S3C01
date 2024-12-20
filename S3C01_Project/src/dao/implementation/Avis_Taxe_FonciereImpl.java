package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Avis_Taxe_FonciereDAO;
import dao.entities.Avis_Taxe_Fonciere;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Avis_Taxe_FonciereDAO} pour gérer les opérations sur les entités "Avis_Taxe_Fonciere".
 */
public class Avis_Taxe_FonciereImpl implements Avis_Taxe_FonciereDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Avis_Taxe_FonciereImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Avis_Taxe_FonciereImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un avis de taxe foncière par son numéro fiscal.
     *
     * @param id Le numéro fiscal de l'avis à rechercher.
     * @return L'entité {@link Avis_Taxe_Fonciere} si trouvée, sinon {@code null}.
     */
    @Override
    public Avis_Taxe_Fonciere findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Avis_Taxe_Fonciere WHERE numero_fiscal = ?";

        try {
            // Préparation de la requête SQL avec le numéro fiscal
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Avis_Taxe_Fonciere
            if (result.next()) {
                return createEntities(result);
            }
            
        } catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		finally {
			DatabaseConnection.closeStatement(statement);
		
        }

        return null; // Si aucun avis n'est trouvé, retour de null
    }

    /**
     * Recherche tous les avis de taxe foncière (fonctionnalité à implémenter).
     *
     * @return Liste des avis ou {@code null} si non implémentée.
     */
    @Override
    public List<Avis_Taxe_Fonciere> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée un nouvel avis de taxe foncière dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avis_Taxe_Fonciere à créer.
     */
    @Override
    public void insert(Avis_Taxe_Fonciere entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Met à jour un avis de taxe foncière existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avis_Taxe_Fonciere à mettre à jour.
     */
    @Override
    public void update(Avis_Taxe_Fonciere entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un avis de taxe foncière de la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avis_Taxe_Fonciere à supprimer.
     */
    @Override
    public void delete(Avis_Taxe_Fonciere entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un avis de taxe foncière par son numéro fiscal (fonctionnalité à implémenter).
     *
     * @param id Le numéro fiscal de l'avis à supprimer.
     */
    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
    }

    /**
     * Crée une entité {@link Avis_Taxe_Fonciere} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'avis de taxe foncière.
     * @return L'entité Avis_Taxe_Fonciere construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Avis_Taxe_Fonciere createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Avis_Taxe_Fonciere à partir des données du ResultSet
        Avis_Taxe_Fonciere avis = new Avis_Taxe_Fonciere();
        avis.setDate_etablissement(result.getDate("date_etablissement"));
        avis.setDebiteur_legaux(result.getString("debiteur_legaux"));
        avis.setTotal_cotisation(result.getDouble("total_cotisation"));
        return avis; // Retourne l'entité Avis_Taxe_Fonciere construite
    }
}
