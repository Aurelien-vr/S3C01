package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Regularisation_chargesDAO;
import dao.entities.Regularisation_charges;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Regularisation_chargesDAO} pour gérer les opérations sur les entités "Regularisation_charges".
 */
public class Regularisation_chargesImpl implements Regularisation_chargesDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Regularisation_chargesImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Regularisation_chargesImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une régularisation des charges par l'identifiant du locataire.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link Regularisation_charges} si trouvée, sinon {@code null}.
     */
    @Override
    public Regularisation_charges findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Regularisation_charges WHERE id_charge_locataire = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant du locataire
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Regularisation_charges
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

        return null; // Si aucune régularisation des charges n'est trouvée, retour de null
    }

    /**
     * Recherche tous les regularisation de charges 
     * 
     * @return Liste des regularisation de charge 
     */
    @Override
    public List<Regularisation_charges> findAll() {
    	List<Regularisation_charges> regus = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Regularisation_charges";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Regularisation_charges acte = createEntities(result);
                regus.add(acte);
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
        
        return regus;
    }

    /**
     * Crée une nouvelle régularisation des charges dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Regularisation_charges à créer.
     */
    @Override
    public void insert(Regularisation_charges entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Regularisation_charges(date_effet, charge_eau, charge_ordure_menagere, charge_eclairage, provision_pour_charge, indice, entretien) VALUES (?,?,?,?,?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setDate(1,entity.getDate_effet());
    		statement.setBigDecimal(2, entity.getCharge_eau());
    		statement.setBigDecimal(3, entity.getCharge_ordure_menagere());
    		statement.setBigDecimal(4, entity.getCharge_eclairage());
    		statement.setBigDecimal(5, entity.getProvision_pour_charge());
    		statement.setBigDecimal(6, entity.getIndice());
    		statement.setString(7, entity.getEntretien());
    			
    			
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
     * Met à jour une régularisation des charges existante dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Regularisation_charges à mettre à jour.
     */
    @Override
    public void update(Regularisation_charges entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une régularisation des charges par l'identifiant du locataire (fonctionnalité à implémenter).
     *
     * @param id L'identifiant du locataire de la régularisation des charges à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Regularisation_charges WHERE id_charge_locataire = ?";
        
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
     * Crée une entité {@link Regularisation_charges} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de la régularisation des charges.
     * @return L'entité Regularisation_charges construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Regularisation_charges createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Regularisation_charges à partir des données du ResultSet
        Regularisation_charges regularisation = new Regularisation_charges();
        regularisation.setDate_effet(result.getDate("date_effet"));
        regularisation.setCharge_eau(result.getBigDecimal("charge_eau"));
        regularisation.setCharge_ordure_menagere(result.getBigDecimal("charge_ordure_menagere"));
        regularisation.setCharge_eclairage(result.getBigDecimal("charge_eclairage"));
        regularisation.setProvision_pour_charge(result.getBigDecimal("provision_pour_charge"));
        regularisation.setIndice(result.getBigDecimal("indice"));
        regularisation.setEntretien(result.getString("entretien"));
        return regularisation; // Retourne l'entité Regularisation_charges construite
    }
}
