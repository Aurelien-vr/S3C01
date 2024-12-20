package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Solde_de_tout_compteDAO;
import dao.entities.Solde_de_tout_compte;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Solde_de_tout_compteDAO} pour gérer les opérations sur les entités "Solde_de_tout_compte".
 */
public class Solde_de_tout_compteImpl implements Solde_de_tout_compteDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Solde_de_tout_compteImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public Solde_de_tout_compteImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un solde de tout compte par l'identifiant du locataire.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link Solde_de_tout_compte} si trouvée, sinon {@code null}.
     */
    @Override
    public Solde_de_tout_compte findOne(long id) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Si aucun solde de tout compte n'est trouvé, retour de null
    }

    /**
     * Recherche tous les soldes de tout compte 
     * 
     * @return Liste des soldes de tout compte
     */
    @Override
    public List<Solde_de_tout_compte> findAll() {
    	List<Solde_de_tout_compte> soldes = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Solde_de_tout_compte";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Solde_de_tout_compte acte = createEntities(result);
                soldes.add(acte);
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
        
        return soldes;
    }

    /**
     * Crée une nouvelle entité Solde_de_tout_compte dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Solde_de_tout_compte à créer.
     */
    @Override
    public void insert(Solde_de_tout_compte entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Solde_de_tout_compte(reste_a_devoir, provision_pour_charges, caution) VALUES (?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setBigDecimal(1,entity.getReste_a_devoir());
    		statement.setBigDecimal(2, entity.getProvision_pour_charges());
    		statement.setBigDecimal(3, entity.getCaution());
    			
    			
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
     * Met à jour un solde de tout compte existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Solde_de_tout_compte à mettre à jour.
     */
    @Override
    public void update(Solde_de_tout_compte entity) {
        // TODO Auto-generated method stub
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
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Solde_de_tout_compte} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du solde de tout compte.
     * @return L'entité Solde_de_tout_compte construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Solde_de_tout_compte createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Solde_de_tout_compte à partir des données du ResultSet
        Solde_de_tout_compte solde = new Solde_de_tout_compte();
        solde.setReste_a_devoir(result.getBigDecimal("reste_a_devoir"));
        solde.setProvision_pour_charges(result.getBigDecimal("provision_pour_charges"));
        solde.setCaution(result.getBigDecimal("caution"));
        return solde; // Retourne l'entité Solde_de_tout_compte construite
    }
}
