package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FactureEauDAO;
import dao.entities.FactureEau;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link FactureEauDAO} pour gérer les opérations sur les entités "Facture_eau".
 */
public class FactureEauImpl implements FactureEauDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Facture_eauImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public FactureEauImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Facture_eau par son identifiant.
     *
     * @param id L'identifiant de la facture d'eau à rechercher.
     * @return L'entité {@link FactureEau} si trouvée, sinon {@code null}.
     */
    @Override
    public FactureEau findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Id_Facture_eau, Partie_fixe, Consommation, Reference_facture FROM db1_sae.Facture_eau WHERE id_facture_eau = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de la facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Facture_eau
            if (result.next()) {
                return createEntities(result);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Affichage de l'exception pour le débogage
        } finally {
            // Fermeture des ressources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            }catch (Exception e) {
                ExceptionStorageHandler.logException(e, connection);
            } finally {
                DatabaseConnection.closeStatement(statement);
            }
        }

        return null; // Si aucune entité Facture_eau n'est trouvée, retour de null
    }

    /**
     * Recherche tous les facture eau
     * 
     * @return Liste des facture eau
     */
    @Override
    public List<FactureEau> findAll() {
    	List<FactureEau> facturesEau = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Id_Facture_eau, Partie_fixe, Consommation, Reference_facture FROM db1_sae.Facture_eau";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                FactureEau acte = createEntities(result);
                facturesEau.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            }catch (Exception e) {
                ExceptionStorageHandler.logException(e, connection);
            } finally {
                DatabaseConnection.closeStatement(statement);
            }
        }
        
        return facturesEau;
    }

    /**
     * Crée une nouvelle entité Facture_eau dans la base de données.
     *
     * @param entity L'entité Facture_eau à créer.
     */
    @Override
    public void insert(FactureEau entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Facture_eau(partie_fixe, consommation) VALUES (?,?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1, entity.getPartieFixe());
            statement.setBigDecimal(2, entity.getConsommation());

            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1); 
                    entity.setIdFactureEau(id); 
                }
            }
        }catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Met à jour une entité Facture_eau existante dans la base de données.
     *
     * @param entity L'entité Facture_eau à mettre à jour.
     */
    @Override
    public void update(FactureEau entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture_eau SET partie_fixe = ?, consommation = ? WHERE id_facture_eau = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, entity.getPartieFixe());
            statement.setBigDecimal(2, entity.getConsommation());
            statement.setLong(3, entity.getIdFactureEau());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    

    /**
     * Supprime une entité Facture_eau par son identifiant.
     *
     * @param id L'identifiant de la facture d'eau à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Facture_eau WHERE id_facture_eau = ?";
        
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
     * Crée une entité {@link FactureEau} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Facture_eau.
     * @return L'entité Facture_eau construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public FactureEau createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture_eau à partir des données du ResultSet
        FactureEau factureEau = new FactureEau();
        factureEau.setIdFactureEau(result.getInt(1));
        factureEau.setPartieFixe(result.getBigDecimal("partie_fixe"));
        factureEau.setConsommation(result.getBigDecimal("consommation"));
        return factureEau; // Retourne l'entité Facture_eau construite
    }
    
    @Override
    public void insertFK(int id, String referenceFacture) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture_eau SET reference_facture = ? WHERE Id_Facture_Eau = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, referenceFacture); 
            statement.setInt(2, id); 

        }catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


}