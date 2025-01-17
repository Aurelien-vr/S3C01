package dao.entities;

import java.sql.Date;
import java.util.Objects;

/**
 * Représente l'état des lieux d'une location.
 * Cette classe contient les informations relatives à la signature de l'état des lieux, 
 * les éléments présents dans le bien, et les informations sur le contrat de location associé.
 */
public class EtatDesLieux {

    private int idEtatDesLieux;  // Identifiant unique de l'état des lieux
    private Date dateSignature;  // Date de signature de l'état des lieux
    private int nombreCles;  // Nombre de clés remises lors de l'état des lieux
    private String etatsDesElement;  // État des éléments du bien (ex: pièces, équipements)
    private int idContratLocation;  // Identifiant du contrat de location associé
    private boolean estEntrer;  // Indique si l'état des lieux a été fait à l'entrée (true) ou à la sortie (false)

    public EtatDesLieux() {}
    
    public EtatDesLieux(Date dateSignature, int nombreCles, String etatDesElements, boolean estEntrer) {
		super();
		this.dateSignature = dateSignature;
		this.nombreCles = nombreCles;
		this.etatsDesElement = etatDesElements;
		this.estEntrer = estEntrer;
	}

	/**
     * Récupère l'identifiant de l'état des lieux.
     *
     * @return L'identifiant de l'état des lieux.
     */
    public int getIdEtatDesLieux() {
        return idEtatDesLieux;
    }

    /**
     * Définit l'identifiant de l'état des lieux.
     *
     * @param idEtatDesLieux L'identifiant de l'état des lieux à définir.
     */
    public void setIdEtatDesLieux(int idEtatDesLieux) {
        this.idEtatDesLieux = idEtatDesLieux;
    }

    /**
     * Récupère la date de signature de l'état des lieux.
     *
     * @return La date de signature de l'état des lieux.
     */
    public Date getDateSignature() {
        return dateSignature;
    }

    /**
     * Définit la date de signature de l'état des lieux.
     *
     * @param dateSignature La date de signature de l'état des lieux à définir.
     */
    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Récupère le nombre de clés remises lors de l'état des lieux.
     *
     * @return Le nombre de clés.
     */
    public int getNombreCles() {
        return nombreCles;
    }

    /**
     * Définit le nombre de clés remises lors de l'état des lieux.
     *
     * @param nombreCles Le nombre de clés à définir.
     */
    public void setNombreCles(int nombreCles) {
        this.nombreCles = nombreCles;
    }

    /**
     * Récupère l'état des éléments dans le bien.
     *
     * @return L'état des éléments (ex: pièces, équipements).
     */
    public String getEtatDesElements() {
        return etatsDesElement;
    }

    /**
     * Définit l'état des éléments dans le bien.
     *
     * @param etatDesElements L'état des éléments à définir.
     */
    public void setEtatDesElements(String etatDesElements) {
        this.etatsDesElement = etatDesElements;
    }

    /**
     * Récupère l'identifiant du contrat de location associé.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getIdContratLocation() {
        return idContratLocation;
    }

    /**
     * Définit l'identifiant du contrat de location associé.
     *
     * @param idContratLocation L'identifiant du contrat de location à définir.
     */
    public void setIdContratLocation(int idContratLocation) {
        this.idContratLocation = idContratLocation;
    }

    /**
     * Vérifie si l'état des lieux a été effectué à l'entrée du locataire.
     *
     * @return true si l'état des lieux a été fait à l'entrée, false sinon.
     */
    public boolean isEstEntrer() {
        return estEntrer;
    }

    /**
     * Définit si l'état des lieux a été effectué à l'entrée du locataire.
     *
     * @param estEntrer true si l'état des lieux a été fait à l'entrée, false sinon.
     */
    public void setEstEntrer(boolean estEntrer) {
        this.estEntrer = estEntrer;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link EtatDesLieux}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Etat_des_lieux{" +
               "id_etat_des_lieux=" + idEtatDesLieux +
               ", date_signature=" + (dateSignature != null ? dateSignature : "N/A") +
               ", nombre_cles=" + nombreCles +
               ", etats_des_elements='" + (etatsDesElement != null ? etatsDesElement : "N/A") + '\'' +
               ", id_contrat_location=" + idContratLocation +
               ", est_entrer=" + estEntrer +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateSignature, estEntrer, etatsDesElement, idContratLocation, idEtatDesLieux,
				nombreCles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtatDesLieux other = (EtatDesLieux) obj;
		return Objects.equals(dateSignature, other.dateSignature) && estEntrer == other.estEntrer
				&& Objects.equals(etatsDesElement, other.etatsDesElement)
				&& idContratLocation == other.idContratLocation && idEtatDesLieux == other.idEtatDesLieux
				&& nombreCles == other.nombreCles;
	}
}
