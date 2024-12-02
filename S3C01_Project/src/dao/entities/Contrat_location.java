package dao.entities;
import java.sql.*;
import java.util.Objects;



/**
 * Représente un contrat de location pour un bien immobilier.
 * Cette classe contient les informations relatives au contrat de location, telles que
 * les dates de début et de fin, le montant du loyer, et les modalités de chauffage et d'eau chaude sanitaire.
 */
public class Contrat_location {
	
	public Contrat_location() {}
	
    public Contrat_location(int montant_loyer, Date date_debut, Date date_fin, String modalite_chauffage,
			String modalite_eau_chaude_sanitaire, Date date_versement) {
		super();
		this.montant_loyer = montant_loyer;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.modalite_chauffage = modalite_chauffage;
		this.modalite_eau_chaude_sanitaire = modalite_eau_chaude_sanitaire;
		this.date_versement = date_versement;
	}



	private int id_Contrat_location;
	private int montant_loyer;  // Montant du loyer mensuel
    private Date date_debut;  // Date de début du contrat
    private Date date_fin;  // Date de fin du contrat
    private String modalite_chauffage;  // Modalité de chauffage (ex : gaz, électrique, etc.)
    private String modalite_eau_chaude_sanitaire;  // Modalité d'eau chaude sanitaire (ex : collective, individuelle, etc.)
    private Date date_versement;  // Date de versement du loyer

    public int getId_Contrat_location() {
    	return id_Contrat_location;
    }
    
    public void setId_Contrat_location(int id_Contrat_location) {
    	this.id_Contrat_location = id_Contrat_location;
    }
    
    /**
     * Récupère le montant du loyer mensuel.
     *
     * @return Le montant du loyer.
     */
    public int getMontant_loyer() {
        return montant_loyer;
    }

    /**
     * Définit le montant du loyer mensuel.
     *
     * @param montant_loyer Le montant du loyer à définir.
     */
    public void setMontant_loyer(int montant_loyer) {
        this.montant_loyer = montant_loyer;
    }

    /**
     * Récupère la date de début du contrat.
     *
     * @return La date de début du contrat.
     */
    public Date getDate_debut() {
        return date_debut;
    }

    /**
     * Définit la date de début du contrat.
     *
     * @param date_debut La date de début du contrat à définir.
     */
    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    /**
     * Récupère la date de fin du contrat.
     *
     * @return La date de fin du contrat.
     */
    public Date getDate_fin() {
        return date_fin;
    }

    /**
     * Définit la date de fin du contrat.
     *
     * @param date_fin La date de fin du contrat à définir.
     */
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    /**
     * Récupère les modalités de chauffage du bien.
     *
     * @return Les modalités de chauffage (ex : gaz, électrique).
     */
    public String getModalite_chauffage() {
        return modalite_chauffage;
    }

    /**
     * Définit les modalités de chauffage du bien.
     *
     * @param modalite_chauffage Les modalités de chauffage à définir.
     */
    public void setModalite_chauffage(String modalite_chauffage) {
        this.modalite_chauffage = modalite_chauffage;
    }

    /**
     * Récupère les modalités d'eau chaude sanitaire du bien.
     *
     * @return Les modalités d'eau chaude sanitaire (ex : collective, individuelle).
     */
    public String getModalite_eau_chaude_saniatire() {
        return modalite_eau_chaude_sanitaire;
    }

    /**
     * Définit les modalités d'eau chaude sanitaire du bien.
     *
     * @param modalite_eau_chaude_saniatire Les modalités d'eau chaude sanitaire à définir.
     */
    public void setModalite_eau_chaude_saniatire(String modalite_eau_chaude_saniatire) {
        this.modalite_eau_chaude_sanitaire = modalite_eau_chaude_saniatire;
    }

    /**
     * Récupère la date de versement du loyer.
     *
     * @return La date de versement du loyer.
     */
    public Date getDate_versement() {
        return date_versement;
    }

    /**
     * Définit la date de versement du loyer.
     *
     * @param date_versement La date de versement à définir.
     */
    public void setDate_versement(Date date_versement) {
        this.date_versement = date_versement;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Same object
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Different class or null object
        }
        Contrat_location that = (Contrat_location) obj;
        return montant_loyer == that.montant_loyer &&
                Objects.equals(date_debut, that.date_debut) &&
                Objects.equals(date_fin, that.date_fin) &&
                Objects.equals(modalite_chauffage, that.modalite_chauffage) &&
                Objects.equals(modalite_eau_chaude_sanitaire, that.modalite_eau_chaude_sanitaire) &&
                Objects.equals(date_versement, that.date_versement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant_loyer, date_debut, date_fin, modalite_chauffage, modalite_eau_chaude_sanitaire, date_versement);
    }
    

    /**
     * Retourne une représentation textuelle de l'objet {@link Contrat_location}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant le contrat de location.
     */
    @Override
    public String toString() {
        return "ContratLocation{" +
        		"id_Contrat_location=" + id_Contrat_location +
               " ,montant_loyer=" + montant_loyer +
               ", date_debut=" + (date_debut != null ? date_debut : "N/A") +
               ", date_fin=" + (date_fin != null ? date_fin : "N/A") +
               ", modalite_chauffage='" + (modalite_chauffage != null ? modalite_chauffage : "N/A") + '\'' +
               ", modalite_eau_chaude_saniatire='" + (modalite_eau_chaude_sanitaire != null ? modalite_eau_chaude_sanitaire : "N/A") + '\'' +
               ", date_versement=" + (date_versement != null ? date_versement : "N/A") +
               '}';
    }

}
