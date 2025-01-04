package dao;

import java.util.List;

import dao.entities.Assurance;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Assurance}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface AssuranceDAO extends DAO<Assurance> {

	List<List<String>> procGet_assurances();
	void insertFK(int selectedIdBien, int numeroContrat);
}
