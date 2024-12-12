package dao.entities;

import java.util.Date;
import java.util.Objects;

/**
 * Représente l'état des lieux d'une location.
 * Cette classe contient les informations relatives à la signature de l'état des lieux, 
 * les éléments présents dans le bien, et les informations sur le contrat de location associé.
 */
public class Etat_des_lieux {

    private int id_etat_des_lieux;  // Identifiant unique de l'état des lieux
    private Date date_signature;  // Date de signature de l'état des lieux
    private int nombre_cles;  // Nombre de clés remises lors de l'état des lieux
    private String etat_des_elements;  // État des éléments du bien (ex: pièces, équipements)
    private int id_contrat_location;  // Identifiant du contrat de location associé
    private boolean est_entrer;  // Indique si l'état des lieux a été fait à l'entrée (true) ou à la sortie (false)

    /**
     * Récupère l'identifiant de l'état des lieux.
     *
     * @return L'identifiant de l'état des lieux.
     */
    public int getId_etat_des_lieux() {
        return id_etat_des_lieux;
    }

    /**
     * Définit l'identifiant de l'état des lieux.
     *
     * @param id_etat_des_lieux L'identifiant de l'état des lieux à définir.
     */
    public void setId_etat_des_lieux(int id_etat_des_lieux) {
        this.id_etat_des_lieux = id_etat_des_lieux;
    }

    /**
     * Récupère la date de signature de l'état des lieux.
     *
     * @return La date de signature de l'état des lieux.
     */
    public Date getDate_signature() {
        return date_signature;
    }

    /**
     * Définit la date de signature de l'état des lieux.
     *
     * @param date_signature La date de signature de l'état des lieux à définir.
     */
    public void setDate_signature(Date date_signature) {
        this.date_signature = date_signature;
    }

    /**
     * Récupère le nombre de clés remises lors de l'état des lieux.
     *
     * @return Le nombre de clés.
     */
    public int getNombre_cles() {
        return nombre_cles;
    }

    /**
     * Définit le nombre de clés remises lors de l'état des lieux.
     *
     * @param nombre_cles Le nombre de clés à définir.
     */
    public void setNombre_cles(int nombre_cles) {
        this.nombre_cles = nombre_cles;
    }

    /**
     * Récupère l'état des éléments dans le bien.
     *
     * @return L'état des éléments (ex: pièces, équipements).
     */
    public String getEtat_des_elements() {
        return etat_des_elements;
    }

    /**
     * Définit l'état des éléments dans le bien.
     *
     * @param etat_des_elements L'état des éléments à définir.
     */
    public void setEtat_des_elements(String etat_des_elements) {
        this.etat_des_elements = etat_des_elements;
    }

    /**
     * Récupère l'identifiant du contrat de location associé.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location associé.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    /**
     * Vérifie si l'état des lieux a été effectué à l'entrée du locataire.
     *
     * @return true si l'état des lieux a été fait à l'entrée, false sinon.
     */
    public boolean isEst_entrer() {
        return est_entrer;
    }

    /**
     * Définit si l'état des lieux a été effectué à l'entrée du locataire.
     *
     * @param est_entrer true si l'état des lieux a été fait à l'entrée, false sinon.
     */
    public void setEst_entrer(boolean est_entrer) {
        this.est_entrer = est_entrer;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Etat_des_lieux}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Etat_des_lieux{" +
               "id_etat_des_lieux=" + id_etat_des_lieux +
               ", date_signature=" + (date_signature != null ? date_signature : "N/A") +
               ", nombre_cles=" + nombre_cles +
               ", etat_des_elements='" + (etat_des_elements != null ? etat_des_elements : "N/A") + '\'' +
               ", id_contrat_location=" + id_contrat_location +
               ", est_entrer=" + est_entrer +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_signature, est_entrer, etat_des_elements, id_contrat_location, id_etat_des_lieux,
				nombre_cles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etat_des_lieux other = (Etat_des_lieux) obj;
		return Objects.equals(date_signature, other.date_signature) && est_entrer == other.est_entrer
				&& Objects.equals(etat_des_elements, other.etat_des_elements)
				&& id_contrat_location == other.id_contrat_location && id_etat_des_lieux == other.id_etat_des_lieux
				&& nombre_cles == other.nombre_cles;
	}
}
