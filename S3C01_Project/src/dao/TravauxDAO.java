package dao;

import java.util.List;

import dao.entities.Travaux;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Travaux}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface TravauxDAO extends DAO<Travaux> {
	public List<List<String>> procPageTravaux();
}
