package dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Declaration_revenuDAO;
import dao.entities.Declaration_revenu;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link Declaration_revenuDAO} pour gérer les opérations sur les entités "Declaration_revenu".
 */
public class Declaration_revenuImpl implements Declaration_revenuDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe Declaration_revenuImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public Declaration_revenuImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche une déclaration de revenu par son identifiant.
     * 
     * @param id L'identifiant de la déclaration de revenu à rechercher.
     * @return L'entité {@link Declaration_revenu} si trouvée, sinon {@code null}.
     */
    @Override
    public Declaration_revenu findOne(long id) {
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
     * Recherche tous les declarations de revenus
     * 
     * @return Liste des declaration de revenus
     */
    @Override
    public List<Declaration_revenu> findAll() {
    	List<Declaration_revenu> declas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Declaration_revenu";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Declaration_revenu acte = createEntities(result);
                declas.add(acte);
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
        
        return declas;
    }

    /**
     * Crée une nouvelle déclaration de revenu dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à créer.
     */
    @Override
    public void insert(Declaration_revenu entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Declaration_revenu(date_acquisition, locataires, recette_immeuble)  VALUES (?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
    		statement.setDate(1, entity.getDate_acquisition());
    		statement.setInt(2, entity.getLocataires());
    		statement.setBigDecimal(3, entity.getRecette_immeuble());
    		
    			
    			
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
     * Met à jour une déclaration de revenu existante dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à mettre à jour.
     */
    @Override
    public void update(Declaration_revenu entity) {
        // TODO Auto-generated method stub
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
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Declaration_revenu} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de la déclaration de revenu.
     * @return L'entité Declaration_revenu construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Declaration_revenu createEntities(ResultSet result) throws SQLException {
        Declaration_revenu revenu = new Declaration_revenu();
        revenu.setDate_acquisition(result.getDate("date_acquisition"));
        revenu.setLocataires(result.getInt("locataires"));
        revenu.setRecette_immeuble(result.getBigDecimal("recette_immeuble"));
        return revenu;
    }
}