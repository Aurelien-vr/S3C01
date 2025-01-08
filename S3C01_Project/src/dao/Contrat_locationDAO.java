package dao;

import java.util.List;

import dao.entities.Contrat_location;
/**
 * Interface spécifique pour les opérations liées à l'entité {@link Contrat_location}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface Contrat_locationDAO extends DAO<Contrat_location> {

	List<List<String>> procPageContratLocation();
	List<List<String>> procPageContratLocationActif();
	void procRemoveFkBienLocation(int fkToRm);
	void procUpdateFkBienLocation(int fkToUpdate, int newFk);
}
