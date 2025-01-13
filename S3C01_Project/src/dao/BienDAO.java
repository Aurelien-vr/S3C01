package dao;

import java.util.List;
import dao.entities.Bien;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Bien}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface BienDAO extends DAO<Bien> {
	public List<List<String>> BienStatus();
	String[] get_AllAdresses();
	public List<List<String>> procPageBien();
	public String[] procGetClNotInBien();
	void insertFK(int id, int idContratLocation);
	public List<List<String>> procBienSansContrat();
	public String procAdressOfFacture(String refFacture);
	void procDeletBienCascade(int idBien);
}
