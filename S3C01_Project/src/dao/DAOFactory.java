package dao;

import dao.implementation.*;
import db_connection.*;

/**
 * Usine pour la création des DAO (Data Access Objects).
 * Permet d'instancier les implémentations spécifiques des DAO en fournissant une connexion à la base de données.
 */
public class DAOFactory {
	
	private DAOFactory() {}

    /**
     * Crée une instance de Contrat_locationDAO.
     *
     * @return Une instance de {@link ContratLocationDAO} utilisant la connexion à la base de données partagée.
     */
    public static ContratLocationDAO createContratLocationDAO() {
        return new ContratLocationImpl(DatabaseConnection.getInstance());
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
     * @return Une instance de {@link ActeCautionnementDAO} utilisant la connexion à la base de données partagée.
     */
    public static ActeCautionnementDAO createActeCautionnementDAO() {
        return new ActeCautionnementImpl(DatabaseConnection.getInstance());
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
     * @return Une instance de {@link AvisTaxeFonciereDAO} utilisant la connexion à la base de données partagée.
     */
    public static AvisTaxeFonciereDAO createAvisTaxeFonciereDAO() {
        return new AvisTaxeFonciereImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Contrat_colocationDAO.
     *
     * @return Une instance de {@link ContratColocationDAO} utilisant la connexion à la base de données partagée.
     */
    public static ContratColocationDAO createContratColocationDAO() {
        return new ContratColocationImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Declaration_revenuDAO.
     *
     * @return Une instance de {@link DeclarationRevenuDAO} utilisant la connexion à la base de données partagée.
     */
    public static DeclarationRevenuDAO createDeclarationRevenuDAO() {
        return new DeclarationRevenuImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de EnumererDAO.
     *
     * @return Une instance de {@link EnumererDAO} utilisant la connexion à la base de données partagée.
     */
    public static EnumererDAO createEnumererDAO() {
        return new EnumererImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Etat_des_lieuxDAO.
     *
     * @return Une instance de {@link EtatDesLieuxDAO} utilisant la connexion à la base de données partagée.
     */
    public static EtatDesLieuxDAO createEtatDesLieuxDAO() {
        return new EtatDesLieuxImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_eauDAO.
     *
     * @return Une instance de {@link FactureEauDAO} utilisant la connexion à la base de données partagée.
     */
    public static FactureEauDAO createFactureEauDAO() {
        return new FactureEauImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_electriciteDAO.
     *
     * @return Une instance de {@link FactureElectriciteDAO} utilisant la connexion à la base de données partagée.
     */
    public static FactureElectriciteDAO createFactureElectriciteDAO() {
        return new FactureElectriciteImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Facture_gazDAO.
     *
     * @return Une instance de {@link FactureGazDAO} utilisant la connexion à la base de données partagée.
     */
    public static FactureGazDAO createFactureGazDAO() {
        return new FactureGazImpl(DatabaseConnection.getInstance());
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
     * @return Une instance de {@link RegularisationChargesDAO} utilisant la connexion à la base de données partagée.
     */
    public static RegularisationChargesDAO createRegularisationChargesDAO() {
        return new RegularisationChargesImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de Solde_de_tout_compteDAO.
     *
     * @return Une instance de {@link SoldeDeToutCompteDAO} utilisant la connexion à la base de données partagée.
     */
    public static SoldeDeToutCompteDAO createSoldeDeToutCompteDAO() {
        return new SoldeDeToutCompteImpl(DatabaseConnection.getInstance());
    }
    
    /**
     * Crée une instance de TravauxDAO.
     *
     * @return Une instance de {@link TravauxDAO} utilisant la connexion à la base de données partagée.
     */
    public static TravauxDAO createTravauxDAO() {
        return new TravauxImpl(DatabaseConnection.getInstance());
    }

	public static ChargeDAO createChargeDAO() {
		return new ChargeImpl(DatabaseConnection.getInstance());
	}

}