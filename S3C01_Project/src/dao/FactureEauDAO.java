package dao;

import dao.entities.FactureEau;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link FactureEau}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface FactureEauDAO extends DAO<FactureEau> {

	void insertFK(int id, String referenceFacture);
    // Les méthodes spécifiques à l'entité Facture_eau peuvent être ajoutées ici si nécessaire
}
