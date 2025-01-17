package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RegularisationChargesDAO;
import dao.entities.RegularisationCharges;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link RegularisationChargesDAO} pour gérer les opérations sur les entités "Regularisation_charges".
 */
public class RegularisationChargesImpl implements RegularisationChargesDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Regularisation_chargesImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public RegularisationChargesImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une régularisation des charges par l'identifiant du locataire.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link RegularisationCharges} si trouvée, sinon {@code null}.
     */
    @Override
    public RegularisationCharges findOne(long id) {
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
            }catch (Exception e) {
    			ExceptionStorageHandler.logException(e, connection);
    		}finally {
    			DatabaseConnection.closeResult(result);
    			DatabaseConnection.closeStatement(statement);
    		}
        }

        return null; // Si aucune régularisation des charges n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les régularisations des charges (fonctionnalité à implémenter).
     *
     * @return Liste des régularisations des charges ou {@code null} si non implémentée.
     */
    @Override
    public List<RegularisationCharges> findAll() {
    	List<RegularisationCharges> regus = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Regularisation_charges";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                RegularisationCharges acte = createEntities(result);
                regus.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
        }catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
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
    public void insert(RegularisationCharges entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Regularisation_charges(date_effet, charge_eau, charge_ordure_menagere, charge_eclairage, provision_pour_charge, indice, entretien) VALUES (?,?,?,?,?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setDate(1,entity.getDateEffet());
    		statement.setBigDecimal(2, entity.getChargeEau());
    		statement.setBigDecimal(3, entity.getChargeOrdureMenagere());
    		statement.setBigDecimal(4, entity.getChargeEclairage());
    		statement.setBigDecimal(5, entity.getProvisionPourCharge());
    		statement.setBigDecimal(6, entity.getIndice());
    		statement.setString(7, entity.getEntretien());
    			

   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
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
    public void update(RegularisationCharges entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Regularisation_charges SET date_effet = ?, charge_eau = ?, charge_ordure_menagere = ?, charge_eclairage = ?, provision_pour_charge = ?, indice = ?, entretien = ? WHERE id_charge_locataire = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, entity.getDateEffet());
            statement.setBigDecimal(2, entity.getChargeEau());
            statement.setBigDecimal(3, entity.getChargeOrdureMenagere());
            statement.setBigDecimal(4, entity.getChargeEclairage());
            statement.setBigDecimal(5, entity.getProvisionPourCharge());
            statement.setBigDecimal(6, entity.getIndice());
            statement.setString(7, entity.getEntretien());
            statement.setLong(8, entity.getIdChargeLocataire());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
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
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link RegularisationCharges} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de la régularisation des charges.
     * @return L'entité Regularisation_charges construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public RegularisationCharges createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Regularisation_charges à partir des données du ResultSet
        RegularisationCharges regularisation = new RegularisationCharges();
        regularisation.setIdChargeLocataire(result.getInt("id_charge_locataire"));
        regularisation.setDateEffet(result.getDate("date_effet"));
        regularisation.setChargeEau(result.getBigDecimal("charge_eau"));
        regularisation.setChargeOrdureMenagere(result.getBigDecimal("charge_ordure_menagere"));
        regularisation.setChargeEclairage(result.getBigDecimal("charge_eclairage"));
        regularisation.setProvisionPourCharge(result.getBigDecimal("provision_pour_charge"));
        regularisation.setIndice(result.getBigDecimal("indice"));
        regularisation.setEntretien(result.getString("entretien"));
        return regularisation; // Retourne l'entité Regularisation_charges construite
    }
}
