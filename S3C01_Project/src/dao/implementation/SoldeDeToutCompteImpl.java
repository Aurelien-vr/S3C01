package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SoldeDeToutCompteDAO;
import dao.entities.SoldeDeToutCompte;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link SoldeDeToutCompteDAO} pour gérer les opérations sur les entités "Solde_de_tout_compte".
 */
public class SoldeDeToutCompteImpl implements SoldeDeToutCompteDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Solde_de_tout_compteImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public SoldeDeToutCompteImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un solde de tout compte par l'identifiant du locataire.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link SoldeDeToutCompte} si trouvée, sinon {@code null}.
     */
    @Override
    public SoldeDeToutCompte findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Solde_de_tout_compte WHERE id_solde_de_tout_compte = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant du locataire
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Solde_de_tout_compte
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
            }catch (Exception e) {
    			ExceptionStorageHandler.logException(e, connection);
    		}finally {
    			DatabaseConnection.closeResult(result);
    			DatabaseConnection.closeStatement(statement);
    		}
        }

        return null; // Si aucun solde de tout compte n'est trouvé, retour de null
    }

    /**
     * Recherche toutes les soldes de tout compte (fonctionnalité à implémenter).
     *
     * @return Liste des soldes de tout compte ou {@code null} si non implémentée.
     */
    @Override
    public List<SoldeDeToutCompte> findAll() {
    	List<SoldeDeToutCompte> soldes = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Solde_de_tout_compte";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                SoldeDeToutCompte acte = createEntities(result);
                soldes.add(acte);
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
    			DatabaseConnection.closeResult(result);
    			DatabaseConnection.closeStatement(statement);
    		}
        }
        
        return soldes;
    }

    /**
     * Crée une nouvelle entité Solde_de_tout_compte dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Solde_de_tout_compte à créer.
     */
    @Override
    public void insert(SoldeDeToutCompte entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Solde_de_tout_compte(reste_a_devoir, provision_pour_charges, caution) VALUES (?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setBigDecimal(1,entity.getResteADevoir());
    		statement.setBigDecimal(2, entity.getProvisionPourCharges());
    		statement.setBigDecimal(3, entity.getCaution());
    			

   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }

    /**
     * Met à jour un solde de tout compte existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Solde_de_tout_compte à mettre à jour.
     */
    @Override
    public void update(SoldeDeToutCompte entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Solde_de_tout_compte SET reste_a_devoir = ?, provision_pour_charges = ?, caution = ? WHERE id_solde_de_tout_compte = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, entity.getResteADevoir());
            statement.setBigDecimal(2, entity.getProvisionPourCharges());
            statement.setBigDecimal(3, entity.getCaution());
            statement.setLong(4, entity.getIdSoldeDeToutCompte());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Supprime un solde de tout compte par l'identifiant du locataire (fonctionnalité à implémenter).
     *
     * @param id L'identifiant du locataire de la régularisation des charges à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Solde_de_tout_compte WHERE Id_Solde_de_tout_compte = ?";
        
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
     * Crée une entité {@link SoldeDeToutCompte} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du solde de tout compte.
     * @return L'entité Solde_de_tout_compte construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public SoldeDeToutCompte createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Solde_de_tout_compte à partir des données du ResultSet
        SoldeDeToutCompte solde = new SoldeDeToutCompte();
        solde.setResteADevoir(result.getBigDecimal("reste_a_devoir"));
        solde.setProvisionPourCharges(result.getBigDecimal("provision_pour_charges"));
        solde.setCaution(result.getBigDecimal("caution"));
        return solde; // Retourne l'entité Solde_de_tout_compte construite
    }
}
