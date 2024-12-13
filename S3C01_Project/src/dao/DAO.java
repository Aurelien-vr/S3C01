package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface générique pour les opérations CRUD (Create, Read, Update, Delete).
 * Cette interface définit les méthodes de base pour gérer des entités dans la base de données.
 *
 * @param <T> Le type d'entité que cette interface manipule (par exemple, {@link Contrat_location} ou {@link Bien}).
 */
public interface DAO<T> {

    /**
     * Recherche une entité par son identifiant unique.
     *
     * @param id L'identifiant de l'entité à rechercher.
     * @return L'entité correspondante à l'id, ou {@code null} si non trouvée.
     */
    public T findOne(long id);

    /**
     * Récupère toutes les entités de ce type dans la base de données.
     *
     * @return Une liste contenant toutes les entités.
     */
    public List<T> findAll();

    /**
     * Crée une nouvelle entité dans la base de données.
     *
     * @param entity L'entité à créer.
     */
    void insert(T entity);

    /**
     * Met à jour une entité existante dans la base de données.
     *
     * @param entity L'entité à mettre à jour.
     */
    void update(T entity);

    /**
     * Supprime une entité existante de la base de données.
     *
     * @param entity L'entité à supprimer.
     */
    void delete(T entity);

    /**
     * Supprime une entité en utilisant son identifiant unique.
     *
     * @param entity L'entité à supprimer, identifié par son ID.
     */
    void deleteById(long id);

    /**
     * Crée une entité à partir des données extraites d'un {@link ResultSet}.
     *
     * @param result Le {@link ResultSet} contenant les données de la base.
     * @return L'entité créée à partir des données.
     * @throws SQLException Si une erreur se produit lors de l'extraction des données.
     */
    T createEntities(ResultSet result) throws SQLException;

    
}
