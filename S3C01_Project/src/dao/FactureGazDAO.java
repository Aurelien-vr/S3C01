package dao;

import dao.entities.FactureGaz;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link FactureGaz}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface FactureGazDAO extends DAO<FactureGaz> {

	void insertFK(int idFactureGaz, String factureName);
    // Les méthodes spécifiques à l'entité Facture_gaz peuvent être ajoutées ici si nécessaire
}

