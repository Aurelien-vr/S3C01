package dao;

import java.util.List;

import dao.entities.Bien;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Bien}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface BienDAO extends DAO<Bien> {
    // Les méthodes spécifiques à l'entité Bien peuvent être ajoutées ici si nécessaire
	public List<List<String>> BienStatus();
}
