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
    
    /**
     * Crée une instance de Acte_cautionnementDAO.
     *
     * @return Une instance de {@link Acte_cautionnementDAO} utilisant la connexion à la base de données partagée.
     */
    public static Acte_cautionnementDAO createActe_cautionnementDAO() {
        return new Acte_cautionnementImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de AssuranceDAO.
     *
     * @return Une instance de {@link AssuranceDAO} utilisant la connexion à la base de données partagée.
     */
    public static AssuranceDAO createAssuranceDAO() {
        return new AssuranceImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de AvancerDAO.
     *
     * @return Une instance de {@link AvancerDAO} utilisant la connexion à la base de données partagée.
     */
    public static AvancerDAO createAvancerDAO() {
        return new AvancerImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Avis_taxe_fonciereDAO.
     *
     * @return Une instance de {@link Avis_Taxe_FonciereDAO} utilisant la connexion à la base de données partagée.
     */
    public static Avis_Taxe_FonciereDAO createAvis_Taxe_FonciereDAO() {
        return new Avis_Taxe_FonciereImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Contrat_colocationDAO.
     *
     * @return Une instance de {@link Contrat_colocationDAO} utilisant la connexion à la base de données partagée.
     */
    public static Contrat_colocationDAO createContrat_colocationDAO() {
        return new Contrat_colocationImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Declaration_revenuDAO.
     *
     * @return Une instance de {@link Declaration_revenuDAO} utilisant la connexion à la base de données partagée.
     */
    public static Declaration_revenuDAO createDeclaration_revenuDAO() {
        return new Declaration_revenuImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Etat_des_lieuxDAO.
     *
     * @return Une instance de {@link Etat_des_lieuxDAO} utilisant la connexion à la base de données partagée.
     */
    public static Etat_des_lieuxDAO createEtat_des_lieuxDAO() {
        return new Etat_des_lieuxImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_eauDAO.
     *
     * @return Une instance de {@link Facture_eauDAO} utilisant la connexion à la base de données partagée.
     */
    public static Facture_eauDAO createFacture_eauDAO() {
        return new Facture_eauImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_electriciteDAO.
     *
     * @return Une instance de {@link Facture_electriciteDAO} utilisant la connexion à la base de données partagée.
     */
    public static Facture_electriciteDAO createFacture_electriciteDAO() {
        return new Facture_electriciteImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_gazDAO.
     *
     * @return Une instance de {@link Facture_gazDAO} utilisant la connexion à la base de données partagée.
     */
    public static Facture_gazDAO createFacture_gazDAO() {
        return new Facture_gazImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de FactureDAO.
     *
     * @return Une instance de {@link FactureDAO} utilisant la connexion à la base de données partagée.
     */
    public static FactureDAO createFactureDAO() {
        return new FactureImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de LocataireDAO.
     *
     * @return Une instance de {@link LocataireDAO} utilisant la connexion à la base de données partagée.
     */
    public static LocataireDAO createLocataireDAO() {
        return new LocataireImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Regularisation_chargesDAO.
     *
     * @return Une instance de {@link Regularisation_chargesDAO} utilisant la connexion à la base de données partagée.
     */
    public static Regularisation_chargesDAO createRegularisation_chargesDAO() {
        return new Regularisation_chargesImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Solde_de_tout_compteDAO.
     *
     * @return Une instance de {@link Solde_de_tout_compteDAO} utilisant la connexion à la base de données partagée.
     */
    public static Solde_de_tout_compteDAO createSolde_de_tout_compteDAO() {
        return new Solde_de_tout_compteImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de TravauxDAO.
     *
     * @return Une instance de {@link TravauxDAO} utilisant la connexion à la base de données partagée.
     */
    public static TravauxDAO createTravauxDAO() {
        return new TravauxImpl(DatabaseConnection.getInstance());
    }

}
