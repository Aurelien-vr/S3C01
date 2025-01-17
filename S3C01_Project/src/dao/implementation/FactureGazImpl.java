package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FactureGazDAO;
import dao.entities.FactureGaz;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link FactureElectriciteDAO} pour gérer les opérations sur les entités "Facture_electricite".
 */
public class FactureGazImpl implements FactureGazDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Facture_gazImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public FactureGazImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une entité Facture_gaz par son identifiant.
     *
     * @param id L'identifiant de la facture de gaz à rechercher.
     * @return L'entité {@link FactureGaz} si trouvée, sinon {@code null}.
     */
    @Override
    public FactureGaz findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture_gaz WHERE id_facture_gaz = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant de la facture
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Facture_gaz
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
                DatabaseConnection.closeResult(result);
            }
        }

        return null; // Si aucune entité Facture_gaz n'est trouvée, retour de null
    }

    /**
     * Recherche tous les facture de gaz
     * 
     * @return Liste des facture de gaz
     */
    @Override
    public List<FactureGaz> findAll() {
    	List<FactureGaz> facturesGaz = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture_gaz";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                FactureGaz acte = createEntities(result);
                facturesGaz.add(acte);
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
                DatabaseConnection.closeResult(result);
            }
        }
        
        return facturesGaz;
    }

    /**
     * Crée une nouvelle entité Facture_gaz dans la base de données.
     *
     * @param entity L'entité Facture_gaz à créer.
     */
    @Override
    public void insert(FactureGaz entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Facture_gaz(consommation_m3, prix_m3_gaz) VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1, entity.getConsommationM3());
            statement.setString(2, entity.getPrixM3Gaz());

            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setIdFactureGaz(id);
                }
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Met à jour une entité Facture_gaz existante dans la base de données.
     *
     * @param entity L'entité Facture_gaz à mettre à jour.
     */
    @Override
    public void update(FactureGaz entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture_gaz SET consommation_m3 = ?, prix_m3_gaz = ? WHERE id_facture_gaz = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, entity.getConsommationM3());
            statement.setString(2, entity.getPrixM3Gaz());
            statement.setLong(3, entity.getIdFactureGaz());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Supprime une entité Facture_gaz par son identifiant.
     *
     * @param id L'identifiant de la facture de gaz à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Facture_gaz WHERE id_facture_gaz = ?";
        
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
     * Crée une entité {@link FactureGaz} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'entité Facture_gaz.
     * @return L'entité Facture_gaz construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public FactureGaz createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture_gaz à partir des données du ResultSet
        FactureGaz factureGaz = new FactureGaz();
        factureGaz.setIdFactureGaz(result.getInt("id_facture_gaz"));
        factureGaz.setConsommationM3(result.getBigDecimal("consommation_m3"));
        factureGaz.setPrixM3Gaz(result.getString("prix_m3_gaz"));
        return factureGaz; // Retourne l'entité Facture_electricite construite
    }
    
    @Override
    public void insertFK(int id, String referenceFacture) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture_gaz SET reference_facture = ? WHERE Id_Facture_Gaz = ?";

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