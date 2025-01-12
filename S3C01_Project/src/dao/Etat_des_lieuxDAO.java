package dao;

import dao.entities.Etat_des_lieux;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Etat_des_lieux}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface Etat_des_lieuxDAO extends DAO<Etat_des_lieux> {

	/**
	 * Associe un Id_Contrat_location à une entité Etat_des_lieux existante.
	 *
	 * @param idEtatDesLieux L'identifiant de l'état des lieux (Id_Etat_des_lieux).
	 * @param idContratLocation La clé étrangère à associer (Id_Contrat_location).
	 */
	void insertFKContratLocation(int idEtatDesLieux, int idContratLocation);
    // Les méthodes spécifiques à l'entité Etat_des_lieux peuvent être ajoutées ici si nécessaire
}