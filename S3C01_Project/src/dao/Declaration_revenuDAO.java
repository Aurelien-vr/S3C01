package dao;

import dao.entities.Declaration_revenu;

/**
 * Interface spécifique pour les opérations liées à l'entité {@link Declaration_revenu}.
 * Hérite des méthodes génériques définies dans l'interface {@link DAO}.
 */
public interface Declaration_revenuDAO extends DAO<Declaration_revenu> {

	/**
	 * Insère ou met à jour la clé étrangère id_Bien pour une déclaration de revenu existante.
	 * 
	 * @param idDeclarationRevenu L'identifiant de la déclaration de revenu (Id_Declaration_revenu).
	 * @param idBien La clé étrangère à associer (id_Bien).
	 */
	void insertFK(int idDeclarationRevenu, int idBien);
    // Les méthodes spécifiques à l'entité Declaration_revenu peuvent être ajoutées ici si nécessaire
}