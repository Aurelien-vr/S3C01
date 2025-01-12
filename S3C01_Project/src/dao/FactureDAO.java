package dao;

import dao.entities.Facture;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Facture}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface FactureDAO extends DAO<Facture> {

	/**
	 * Associe un id_Bien à une facture existante.
	 *
	 * @param referenceFacture La référence de la facture à laquelle associer le bien.
	 * @param idBien La clé étrangère à associer (id_Bien).
	 */
	void insertFK(String referenceFacture, int idBien);
    // Les méthodes spécifiques à l'entité Facture peuvent être ajoutées ici si nécessaire
}

