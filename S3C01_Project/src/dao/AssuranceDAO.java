package dao;

import java.util.List;

import dao.entities.Assurance;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Assurance}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface AssuranceDAO extends DAO<Assurance> {

	void insertFK(int id, int idContratLocation);
    // Les méthodes spécifiques à l'entité Assurance peuvent être ajoutées ici si nécessaire

	List<List<String>> procGet_assurances();
}
