package dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TravauxDAO;
import dao.entities.Travaux;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link TravauxDAO} pour gérer les opérations sur les entités "Travaux".
 */
public class TravauxImpl implements TravauxDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe TravauxImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public TravauxImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un travail par le numéro de facture.
     *
     * @param numeroFacture Le numéro de la facture.
     * @return L'entité {@link Travaux} si trouvée, sinon {@code null}.
     */
    @Override
    public Travaux findOne(long numeroFacture) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Travaux WHERE numero_facture = ?";

        try {
            // Préparation de la requête SQL avec le numéro de facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, numeroFacture);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Travaux
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

        return null; // Si aucun travail n'est trouvé, retour de null
    }

    /**
     * Recherche tous les travaux
     * 
     * @return Liste des travaux
     */
    @Override
    public List<Travaux> findAll() {
    	List<Travaux> tras = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Travaux";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Travaux acte = createEntities(result);
                tras.add(acte);
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
        
        return tras;
    }

    /**
     * Crée une nouvelle entité Travaux dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Travaux à créer.
     */
    @Override
    public void insert(Travaux entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Travaux(date_travaux, nature, iban, reduction, montant, montant_non_deductible, reduction_special) VALUES (?,?,?,?,?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setDate(1,entity.getDate_travaux());
    		statement.setString(2, entity.getNature());
    		statement.setString(3, entity.getIban());
    		statement.setBigDecimal(4,entity.getReduction());
    		statement.setBigDecimal(5, entity.getMontant());
    		statement.setBigDecimal(6, entity.getMontant_non_deductible());
    		statement.setBigDecimal(7, entity.getReduction_special());
    			
    			
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
     * Met à jour un travail existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Travaux à mettre à jour.
     */
    @Override
    public void update(Travaux entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un travail par le numéro de facture (fonctionnalité à implémenter).
     *
     * @param numeroFacture Le numéro de la facture du travail à supprimer.
     */
    @Override
    public void deleteById(long id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Travaux WHERE Numero_facture = ?";
        
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
     * Crée une entité {@link Travaux} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du travail.
     * @return L'entité Travaux construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Travaux createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Travaux à partir des données du ResultSet
        Travaux travaux = new Travaux();
        travaux.setDate_travaux(result.getDate("date_travaux"));
        travaux.setNature(result.getString("nature"));
        travaux.setIban(result.getString("iban"));
        travaux.setReduction(result.getBigDecimal("reduction"));
        travaux.setMontant(result.getBigDecimal("montant"));
        travaux.setMontant_non_deductible(result.getBigDecimal("montant_non_deductible"));
        travaux.setReduction_special(result.getBigDecimal("reduction_special"));
        return travaux; // Retourne l'entité Travaux construite
    }
}
