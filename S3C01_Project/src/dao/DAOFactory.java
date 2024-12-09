package dao;

import dbConnection.*;
import dao.implementation.*;

/**
 * Usine pour la création des DAO (Data Access Objects).
 * Permet d'instancier les implémentations spécifiques des DAO en fournissant une connexion à la base de données.
 */
public class DAOFactory {

    /**
     * Crée une instance de Contrat_locationDAO.
     *
     * @return Une instance de {@link Contrat_locationDAO} utilisant la connexion à la base de données partagée.
     */
    public static Contrat_locationDAO createContrat_locationDAO() {
        return new Contrat_locationImpl(DatabaseConnection.getInstance());
    }

    /**
     * Crée une instance de BienDAO.
     *
     * @return Une instance de {@link BienDAO} utilisant la connexion à la base de données partagée.
     */
    public static BienDAO createBienDAO() {
        return new BienImpl(DatabaseConnection.getInstance());
    }

}
