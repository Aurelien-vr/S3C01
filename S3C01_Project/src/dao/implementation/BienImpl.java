package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.BienDAO;
import dao.entities.Bien;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link BienDAO} pour gérer les opérations sur les entités "Bien".
 */
public class BienImpl implements BienDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe BienImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public BienImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche un bien par son identifiant.
     * 
     * @param id L'identifiant du bien à rechercher.
     * @return L'entité {@link Bien} si trouvée, sinon {@code null}.
     */
    @Override
    public Bien findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Bien WHERE Id_Bien = ?";
        
        try {
            // Préparation de la requête SQL avec l'ID du bien
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            // Si un résultat est trouvé, création de l'entité Bien
            if (result.next()) {
                Bien bien = createEntities(result);
                return bien;
            } 
        } catch (Exception e) {
        	ExceptionStorageHandler.LogException(e, connection);
        } finally {
            // Le bloc finally est prévu pour fermer les ressources (mais actuellement vide)
        }
        
        return null; // Si aucun bien n'est trouvé, retour de null
    }

    /**
     * Recherche tous les biens (fonctionnalité à implémenter).
     * 
     * @return Liste des biens ou {@code null} si non implémentée.
     */
    @Override
    public List<Bien> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée un nouveau bien dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à créer.
     */
    @Override
    public void insert(Bien entity) {
        // TODO Auto-generated method stub
    }
    
    /**
     * Met à jour un bien existant dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à mettre à jour.
     */
    @Override
    public void update(Bien entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un bien de la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à supprimer.
     */
    @Override
    public void delete(Bien entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un bien par son identifiant (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à supprimer par ID.
     */
    @Override
    public void deleteById(Bien entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Crée une entité {@link Bien} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données du bien.
     * @return L'entité Bien construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Bien createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Bien à partir des données du ResultSet
        Bien bien = new Bien(result.getInt("Id_Bien"));
        
        bien.setEtage(result.getInt(2));
        bien.setAdresse(result.getString(3));
        bien.setVille(result.getString(4));
        bien.setSuperficie(result.getBigDecimal(5));
        bien.setNombre_de_piece(result.getInt(6));
        bien.setMeuble(result.getBoolean(7));
        bien.setAccesoire_prive(result.getString(8));
        bien.setAccesoire_commun(result.getString(9));
        bien.setId_contrat_location(result.getInt(10));
        bien.setEst_garage(result.getBoolean(11));
        
        return bien;  // Retourne l'entité Bien construite
    }
}
