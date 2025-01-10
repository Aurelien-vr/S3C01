package dao;

import java.util.List;

import dao.entities.Locataire;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Locataire}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface LocataireDAO extends DAO<Locataire> {

	public void insertFK(int idLocataire, int idContratLocation);
	public List<List<String>> procGetLocataires();
	public List<List<String>> procGetLocatairesActifs();
	public List<List<String>> procLocataireSansContrat();
}