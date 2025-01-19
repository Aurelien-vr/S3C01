package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.AvisTaxeFonciereDAO;
import dao.entities.AvisTaxeFonciere;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link AvisTaxeFonciereDAO} pour gérer les opérations sur les entités "Avis_Taxe_Fonciere".
 */
public class AvisTaxeFonciereImpl implements AvisTaxeFonciereDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Avis_Taxe_FonciereImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public AvisTaxeFonciereImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un avis de taxe foncière par son numéro fiscal.
     *
     * @param id Le numéro fiscal de l'avis à rechercher.
     * @return L'entité {@link AvisTaxeFonciere} si trouvée, sinon {@code null}.
     */
    @Override
    public AvisTaxeFonciere findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Numero_fiscal, Date_etablissement, Debiteur_legaux, Total_cotisation FROM db1_sae.Avis_Taxe_Fonciere WHERE numero_fiscal = ?";

        try {
            // Préparation de la requête SQL avec le numéro fiscal
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Avis_Taxe_Fonciere
            if (result.next()) {
                return createEntities(result);
            }
            
        } catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}

        return null; // Si aucun avis n'est trouvé, retour de null
    }

    /**
     * Recherche tous les avis de taxe foncière (fonctionnalité à implémenter).
     *
     * @return Liste des avis ou {@code null} si non implémentée.
     */
    @Override
    public List<AvisTaxeFonciere> findAll() {
    	List<AvisTaxeFonciere> avis = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Numero_fiscal, Date_etablissement, Debiteur_legaux, Total_cotisation FROM db1_sae.Avis_Taxe_Fonciere";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                AvisTaxeFonciere acte = createEntities(result);
                avis.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
       			ExceptionStorageHandler.logException(e, connection);
       		}finally {
       			DatabaseConnection.closeStatement(statement);
       		}
        }
        
        return avis;
    }

    /**
     * Crée un nouvel avis de taxe foncière dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avis_Taxe_Fonciere à créer.
     */
    @Override
    public void insert(AvisTaxeFonciere entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Avis_Taxe_Fonciere(date_etablissement, debiteur_legaux,total_cotisation) VALUES (?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    		statement.setDate(1, entity.getDateEtablissement());
    		statement.setString(2, entity.getDebiteurLegaux());
    		statement.setDouble(3,  entity.getTotalCotisation());
    		
    		if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setNumeroFiscal(id);
                }
            }
   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }
    
    /**
     * Met à jour un avis de taxe foncière existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Avis_Taxe_Fonciere à mettre à jour.
     */
    @Override
    public void update(AvisTaxeFonciere entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Avis_Taxe_Fonciere SET Date_etablissement = ? AND Debiteur_legaux = ? AND Total_cotisation = ? WHERE Numero_fiscal = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setDate(1, entity.getDateEtablissement());
			statement.setString(2, entity.getDebiteurLegaux());
			statement.setDouble(3,entity.getTotalCotisation());
			statement.setInt(4,entity.getNumeroFiscal());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Supprime un avis de taxe foncière par son numéro fiscal (fonctionnalité à implémenter).
     *
     * @param id Le numéro fiscal de l'avis à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Avis_Taxe_Fonciere WHERE numero_fiscal = ?";
        
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
     * Crée une entité {@link AvisTaxeFonciere} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'avis de taxe foncière.
     * @return L'entité Avis_Taxe_Fonciere construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public AvisTaxeFonciere createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Avis_Taxe_Fonciere à partir des données du ResultSet
        AvisTaxeFonciere avis = new AvisTaxeFonciere();
        avis.setNumeroFiscal(result.getInt(1));
        avis.setDateEtablissement(result.getDate("date_etablissement"));
        avis.setDebiteurLegaux(result.getString("debiteur_legaux"));
        avis.setTotalCotisation(result.getDouble("total_cotisation"));
        return avis; // Retourne l'entité Avis_Taxe_Fonciere construite
    }
}
