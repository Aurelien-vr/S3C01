package dao;

import dao.entities.Regularisation_charges;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Regularisation_charges}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface Regularisation_chargesDAO extends DAO<Regularisation_charges> {

	/**
	 * Associe un Id_Contrat_location à une régularisation des charges existante.
	 *
	 * @param idChargeLocataire L'identifiant de la régularisation des charges (Id_Charge_locataire).
	 * @param idContratLocation L'identifiant du contrat de location à associer (Id_Contrat_location).
	 */
	void insertFK(int idChargeLocataire, int idContratLocation);
    // Les méthodes spécifiques à l'entité Facture peuvent être ajoutées ici si nécessaire
}
