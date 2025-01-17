package dao;

import dao.entities.FactureElectricite;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link FactureElectricite}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface FactureElectriciteDAO extends DAO<FactureElectricite> {

	void insertFK(int id, String referenceFacture);
    // Les méthodes spécifiques à l'entité Facture_electricite peuvent être ajoutées ici si nécessaire
}

