package dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Declaration_revenuDAO;
import dao.entities.Declaration_revenu;

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
     * Recherche toutes les déclarations de revenus (fonctionnalité à implémenter).
     * 
     * @return Liste des déclarations de revenus ou {@code null} si non implémentée.
     */
    @Override
    public List<Declaration_revenu> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée une nouvelle déclaration de revenu dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à créer.
     */
    @Override
    public void insert(Declaration_revenu entity) {
        // TODO Auto-generated method stub
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
     * Supprime une déclaration de revenu de la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Declaration_revenu à supprimer.
     */
    @Override
    public void delete(Declaration_revenu entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une déclaration de revenu par son identifiant (fonctionnalité à implémenter).
     * 
     * @param id L'identifiant de la déclaration de revenu à supprimer.
     */
    @Override
    public void deleteById(Declaration_revenu entity) {
        // TODO Auto-generated method stub
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
        revenu.setId_declaration_revenu(result.getInt("id_declaration_revenu"));
        revenu.setDate_acquisition(result.getDate("date_acquisition"));
        revenu.setLocataires(result.getInt("locataires"));
        revenu.setRecette_immeuble(result.getBigDecimal("recette_immeuble"));
        revenu.setId_bien(result.getInt("id_bien"));
        return revenu;
    }
}