package dao;

import dao.entities.Travaux;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Travaux}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface TravauxDAO extends DAO<Travaux> {

	void insertFK(int NumeroFacture, String ReferenceFacture);
    // Les méthodes spécifiques à l'entité Travaux peuvent être ajoutées ici si nécessaire
}
