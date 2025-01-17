package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContratColocationDAO;
import dao.entities.ContratColocation;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link ContratColocationDAO} pour gérer les opérations sur les entités "Contrat_colocation".
 */
public class ContratColocationImpl implements ContratColocationDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Contrat_colocationImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public ContratColocationImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un contrat de colocation par son identifiant.
     *
     * @param id L'identifiant du contrat de colocation à rechercher.
     * @return L'entité {@link ContratColocation} si trouvée, sinon {@code null}.
     */
    @Override
    public ContratColocation findOne(long id) {
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
			ExceptionStorageHandler.logException(e, connection);
		}finally {
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
    public List<ContratColocation> findAll() {
    	List<ContratColocation> listContratColocation = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Contrat_colocation";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                ContratColocation acte = createEntities(result);
                listContratColocation.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            }catch (Exception e) {
       			ExceptionStorageHandler.logException(e, connection);
       		}finally {
       			DatabaseConnection.closeStatement(statement);
       		}
        }
        
        return listContratColocation;
    }
    /**
     * Crée un nouveau contrat de colocation dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à créer.
     */
    @Override
    public void insert(ContratColocation entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Contrat_colocation(clause_solidarite, part_des_charges)  VALUES (?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setBoolean(1, entity.isClauseSolidarite());
    		statement.setBigDecimal(2, entity.getPartDesCharges());
    		
   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }

    /**
     * Met à jour un contrat de colocation existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à mettre à jour.
     */
    public void update(ContratColocation entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Contrat_colocation SET clause_solidarite = ?, part_des_charges = ? WHERE id_contrat_colocation = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, entity.isClauseSolidarite());
            statement.setBigDecimal(2, entity.getPartDesCharges());
            statement.setLong(3, entity.getIdContratColocation());  // Assurez-vous que l'entité a un ID défini

        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
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
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }
    /**
     * Crée une entité {@link ContratColocation} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du contrat de colocation.
     * @return L'entité Contrat_colocation construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public ContratColocation createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Contrat_colocation à partir des données du ResultSet
        ContratColocation contrat = new ContratColocation();
        contrat.setClauseSolidarite(result.getBoolean("clause_solidarite"));
        contrat.setPartDesCharges(result.getBigDecimal("part_des_charges"));
        return contrat; // Retourne l'entité Contrat_colocation construite
    }
}
