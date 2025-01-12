package dao;

import dao.entities.Contrat_colocation;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Contrat_colocation}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface Contrat_colocationDAO extends DAO<Contrat_colocation> {

	void insertFK(int idContratColocation, int idContratLocation);
    // Les méthodes spécifiques à l'entité Contrat_colocation peuvent être ajoutées ici si nécessaire
}


