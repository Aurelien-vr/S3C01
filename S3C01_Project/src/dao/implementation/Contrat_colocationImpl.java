package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Contrat_colocationDAO;
import dao.entities.Contrat_colocation;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Contrat_colocationDAO} pour gérer les opérations sur les entités "Contrat_colocation".
 */
public class Contrat_colocationImpl implements Contrat_colocationDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Contrat_colocationImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Contrat_colocationImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un contrat de colocation par son identifiant.
     *
     * @param id L'identifiant du contrat de colocation à rechercher.
     * @return L'entité {@link Contrat_colocation} si trouvée, sinon {@code null}.
     */
    @Override
    public Contrat_colocation findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Contrat_colocation WHERE id_contrat_colocation = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Contrat_colocation
            if (result.next()) {
                return createEntities(result);
            }
        } catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		finally {
			DatabaseConnection.closeStatement(statement);
		}
		return null;
    }

    /**
     * Recherche tous les contrats de colocation (fonctionnalité à implémenter).
     *
     * @return Liste des contrats ou {@code null} si non implémentée.
     */
    @Override
    public List<Contrat_colocation> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée un nouveau contrat de colocation dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à créer.
     */
    @Override
    public void insert(Contrat_colocation entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Met à jour un contrat de colocation existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à mettre à jour.
     */
    @Override
    public void update(Contrat_colocation entity) {
        // TODO Auto-generated method stub
    }


    /**
     * Supprime un contrat de colocation par son entité.
     *
     * @param id L'entité Contrat_colocation à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Contrat_colocation WHERE id_contrat_colocation = ?";
        
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
     * Crée une entité {@link Contrat_colocation} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du contrat de colocation.
     * @return L'entité Contrat_colocation construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Contrat_colocation createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Contrat_colocation à partir des données du ResultSet
        Contrat_colocation contrat = new Contrat_colocation();
        contrat.setClause_solidarite(result.getBoolean("clause_solidarite"));
        contrat.setPart_des_charges(result.getBigDecimal("part_des_charges"));
        return contrat; // Retourne l'entité Contrat_colocation construite
    }
}
