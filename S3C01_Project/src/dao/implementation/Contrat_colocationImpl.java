package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
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
     * Recherche tous les contrats de colocations
     * 
     * @return Liste des contrats de colocations
     */
    @Override
    public List<Contrat_colocation> findAll() {
    	List<Contrat_colocation> contrats_co = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Contrat_colocation";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Contrat_colocation acte = createEntities(result);
                contrats_co.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return contrats_co;
    }

    /**
     * Crée un nouveau contrat de colocation dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à créer.
     */
    @Override
    public void insert(Contrat_colocation entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Contrat_colocation(clause_solidarite, part_des_charges)  VALUES (?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setBoolean(1, entity.isClause_solidarite());
    		statement.setBigDecimal(2, entity.getPart_des_charges());
    		
    			
    			
    		if(statement.executeUpdate()>0) {
    			System.out.println("User inserted");
    		}
   		} catch (Exception e) {
   			ExceptionStorageHandler.LogException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }

    /**
     * Met à jour un contrat de colocation existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_colocation à mettre à jour.
     */
    @Override
    public void update(Contrat_colocation entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Contrat_colocation SET clause_solidarite = ?, part_des_charges = ? WHERE id_contrat_colocation = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, entity.isClause_solidarite());
            statement.setBigDecimal(2, entity.getPart_des_charges());
            statement.setLong(3, entity.getId_contrat_colocation());  // Assurez-vous que l'entité a un ID défini

            if (statement.executeUpdate() > 0) {
                System.out.println("User updated");
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
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
    
    @Override
    public void insertFK(int idContratColocation, int idContratLocation) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Contrat_colocation SET Id_Contrat_location = ? WHERE Id_Contrat_colocation = ?";

        try {
            // Préparation de la requête SQL avec les paramètres
            statement = connection.prepareStatement(query);
            statement.setInt(1, idContratLocation); // Clé étrangère
            statement.setInt(2, idContratColocation); // Clé primaire

            // Exécution de la requête
            if (statement.executeUpdate() > 0) {
                System.out.println("Foreign key inserted successfully");
            } else {
                System.out.println("No rows updated. Check if the Id_Contrat_colocation exists.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Integrity constraint violation: " + e.getMessage());
            ExceptionStorageHandler.LogException(e, connection);
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            // Fermeture de la déclaration SQL
            DatabaseConnection.closeStatement(statement);
        }
    }
}
