package dao;

import java.util.List;

import dao.entities.Facture;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Facture}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface FactureDAO extends DAO<Facture> {
    public String[] getNumFacture();
    public void insertFK(int id, String facture);
    public void insertFKCharges(int idCharge, String refFacture);
    public List<List<String>> procGetFactures();
	void deleteByRef(String ref);
	Facture findOneRef(String reference);
}

