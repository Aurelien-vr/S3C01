package dao;

import dao.entities.ActeCautionnement;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link ActeCautionnement}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface ActeCautionnementDAO extends DAO<ActeCautionnement> {

	/**
	 * Supprime un acte de cautionnement par son identifiant (fonctionnalité à implémenter).
	 * 
	 * @param id L'identifiant de l'acte de cautionnement à supprimer.
	 */
}
