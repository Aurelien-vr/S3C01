package dao;

import java.util.List;

import dao.entities.ContratLocation;
/**
 * Interface spécifique pour les opérations liées à l'entité {@link ContratLocation}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface ContratLocationDAO extends DAO<ContratLocation> {

	List<List<String>> procPageContratLocation();
	List<List<String>> procPageContratLocationActif();
	List<List<String>> procContratLocationDisponible();
	void procRemoveFkBienLocation(int fkToRm);
	void procUpdateFkBienLocation(int fkToUpdate, int newFk, int locataireKey);
	void procCascadeDelete(int idCL);
}
