package dao.entities;

import java.sql.Date;
import java.util.Objects;

/**
 * Représente un contrat de location pour un bien immobilier.
 * Cette classe contient les informations relatives au contrat de location, telles que
 * les dates de début et de fin, le montant du loyer, et les modalités de chauffage et d'eau chaude sanitaire.
 */
public class ContratLocation {

    private int idNumeroLocation;  // Identifiant unique du contrat de location
    private int montantLoyer;  // Montant du loyer mensuel
    private Date dateDebut;  // Date de début du contrat
    private Date dateFin;  // Date de fin du contrat
    private String modaliteChauffage;  // Modalité de chauffage (ex : gaz, électrique, etc.)
    private String modaliteEauChaudeSanitaire;  // Modalité d'eau chaude sanitaire (ex : collective, individuelle, etc.)
    private Date dateVersement;  // Date de versement du loyer


    public ContratLocation() {}
	
    public ContratLocation(int montantLoyer, Date dateDebut, Date dateFin, String modaliteChauffage,
			String modaliteEauChaudeSanitaire, Date dateVersement) {
		super();
		this.montantLoyer = montantLoyer;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.modaliteChauffage = modaliteChauffage;
		this.modaliteEauChaudeSanitaire = modaliteEauChaudeSanitaire;
		this.dateVersement = dateVersement;
	}
    

    /**
     * Récupère le numéro unique du contrat de location.
     *
     * @return Le numéro de location.
     */
    public int getNumeroLocation() {
        return idNumeroLocation;
    }

    /**
     * Définit le numéro unique du contrat de location.
     *
     * @param numeroLocation Le numéro de location à définir.
     */
    public void setNumeroLocation(int numeroLocation) {
        this.idNumeroLocation = numeroLocation;
    }

    /**
     * Récupère le montant du loyer mensuel.
     *
     * @return Le montant du loyer.
     */
    public int getMontantLoyer() {
        return montantLoyer;
    }

    /**
     * Définit le montant du loyer mensuel.
     *
     * @param montantLoyer Le montant du loyer à définir.
     */
    public void setMontantLoyer(int montantLoyer) {
        this.montantLoyer = montantLoyer;
    }

    /**
     * Récupère la date de début du contrat.
     *
     * @return La date de début du contrat.
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début du contrat.
     *
     * @param dateDebut La date de début du contrat à définir.
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Récupère la date de fin du contrat.
     *
     * @return La date de fin du contrat.
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Définit la date de fin du contrat.
     *
     * @param dateFin La date de fin du contrat à définir.
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Récupère les modalités de chauffage du bien.
     *
     * @return Les modalités de chauffage (ex : gaz, électrique).
     */
    public String getModaliteChauffage() {
        return modaliteChauffage;
    }

    /**
     * Définit les modalités de chauffage du bien.
     *
     * @param modaliteChauffage Les modalités de chauffage à définir.
     */
    public void setModaliteChauffage(String modaliteChauffage) {
        this.modaliteChauffage = modaliteChauffage;
    }

    /**
     * Récupère les modalités d'eau chaude sanitaire du bien.
     *
     * @return Les modalités d'eau chaude sanitaire (ex : collective, individuelle).
     */
    public String getModaliteEauChaudeSanitaire() {
        return modaliteEauChaudeSanitaire;
    }

    /**
     * Définit les modalités d'eau chaude sanitaire du bien.
     *
     * @param modaliteEauChaudeSanitaire Les modalités d'eau chaude sanitaire à définir.
     */
    public void setModaliteEauChaudeSanitaire(String modaliteEauChaudeSanitaire) {
        this.modaliteEauChaudeSanitaire = modaliteEauChaudeSanitaire;
    }

    /**
     * Récupère la date de versement du loyer.
     *
     * @return La date de versement du loyer.
     */
    public Date getDateVersement() {
        return dateVersement;
    }

    /**
     * Définit la date de versement du loyer.
     *
     * @param dateVersement La date de versement à définir.
     */
    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link ContratLocation}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant le contrat de location.
     */
    @Override
    public String toString() {
        return "ContratLocation{" +
               "numero_location=" + idNumeroLocation +
               ", montant_loyer=" + montantLoyer +
               ", date_debut=" + (dateDebut != null ? dateDebut : "N/A") +
               ", date_fin=" + (dateFin != null ? dateFin : "N/A") +
               ", modalite_chauffage='" + (modaliteChauffage != null ? modaliteChauffage : "N/A") + '\'' +
               ", modalite_eau_chaude_saniatire='" + (modaliteEauChaudeSanitaire != null ? modaliteEauChaudeSanitaire : "N/A") + '\'' +
               ", date_versement=" + (dateVersement != null ? dateVersement : "N/A") +
               '}';
    }


	@Override
	public int hashCode() {
		return Objects.hash(dateDebut, dateFin, dateVersement, idNumeroLocation, modaliteChauffage,
				modaliteEauChaudeSanitaire, montantLoyer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratLocation other = (ContratLocation) obj;
		return Objects.equals(dateDebut, other.dateDebut) && Objects.equals(dateFin, other.dateFin)
				&& Objects.equals(dateVersement, other.dateVersement)
				&& Objects.equals(modaliteChauffage, other.modaliteChauffage)
				&& Objects.equals(modaliteEauChaudeSanitaire, other.modaliteEauChaudeSanitaire)
				&& montantLoyer == other.montantLoyer;
	}
    
    

}
