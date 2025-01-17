package dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DeclarationRevenuDAO;
import dao.entities.DeclarationRevenu;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link DeclarationRevenuDAO} pour gérer les opérations sur les entités "Declaration_revenu".
 */
public class DeclarationRevenuImpl implements DeclarationRevenuDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe Declaration_revenuImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public DeclarationRevenuImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche une déclaration de revenu par son identifiant.
     * 
     * @param id L'identifiant de la déclaration de revenu à rechercher.
     * @return L'entité {@link DeclarationRevenu} si trouvée, sinon {@code null}.
     */
    @Override
    public DeclarationRevenu findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Declaration_revenu WHERE id_declaration_revenu = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            if (result.next()) {
                return createEntities(result);
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
        
        return null;
    }

    /**
     * Recherche toutes les déclarations de revenus (fonctionnalité à implémenter).
     * 
     * @return Liste des déclarations de revenus ou {@code null} si non implémentée.
     */
    @Override
    public List<DeclarationRevenu> findAll() {
    	List<DeclarationRevenu> declas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Declaration_revenu";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                DeclarationRevenu acte = createEntities(result);
                declas.add(acte);
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
        
        return declas;
    }

    /**
     * Crée une nouvelle déclaration de revenu dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à créer.
     */
    @Override
    public void insert(DeclarationRevenu entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Declaration_revenu(date_acquisition, locataires, recette_immeuble)  VALUES (?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setDate(1, entity.getDateAcquisition());
    		statement.setInt(2, entity.getLocataires());
    		statement.setBigDecimal(3, entity.getRecetteImmeuble());

   		} catch (Exception e) {
   			ExceptionStorageHandler.logException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   		}
    }
    
    /**
     * Met à jour une déclaration de revenu existante dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à mettre à jour.
     */
    @Override
    public void update(DeclarationRevenu entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Declaration_revenu SET date_acquisition = ?, locataires = ?, recette_immeuble = ? WHERE id_declaration_revenu = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, entity.getDateAcquisition());
            statement.setInt(2, entity.getLocataires());
            statement.setBigDecimal(3, entity.getRecetteImmeuble());
            statement.setLong(4, entity.getIdDeclarationRevenu());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Supprime une déclaration de revenu par son identifiant (fonctionnalité à implémenter).
     * 
     * @param id L'identifiant de la déclaration de revenu à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Declaration_revenu WHERE id_declaration_revenu = ?";
        
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
     * Crée une entité {@link DeclarationRevenu} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de la déclaration de revenu.
     * @return L'entité Declaration_revenu construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public DeclarationRevenu createEntities(ResultSet result) throws SQLException {
        DeclarationRevenu revenu = new DeclarationRevenu();
        revenu.setDateAcquisition(result.getDate("date_acquisition"));
        revenu.setLocataires(result.getInt("locataires"));
        revenu.setRecetteImmeuble(result.getBigDecimal("recette_immeuble"));
        return revenu;
    }
}