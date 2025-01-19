package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente une facture.
 * Cette classe contient les informations relatives à une facture,
 * incluant la référence de la facture, le type de facture, la date, le montant, le moyen de paiement, et l'identifiant du bien associé.
 */
public class Facture {

    private String referenceFacture;  // Référence unique de la facture
    private String typeFacture;  // Type de la facture (par exemple, eau, électricité, gaz, etc.)
    private Date dateFacture;  // Date à laquelle la facture a été émise
    private BigDecimal montantFacture;  // Montant total de la facture
    private String moyenPaiement;  // Moyen de paiement utilisé pour régler la facture
    
    
    
    public Facture() {}

	public Facture(String typeFacture, Date dateFacture, BigDecimal montantFacture, String moyenPaiement) {
		super();
		this.typeFacture = typeFacture;
		this.dateFacture = dateFacture;
		this.montantFacture = montantFacture;
		this.moyenPaiement = moyenPaiement;
	}

	/**
     * Récupère la référence de la facture.
     *
     * @return La référence de la facture.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture.
     *
     * @param referenceFacture La référence de la facture à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    /**
     * Récupère le type de la facture.
     *
     * @return Le type de la facture (par exemple, eau, électricité).
     */
    public String getTypeFacture() {
        return typeFacture;
    }

    /**
     * Définit le type de la facture.
     *
     * @param typeFacture Le type de la facture à définir.
     */
    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

    /**
     * Récupère la date de la facture.
     *
     * @return La date de la facture.
     */
    public Date getDateFacture() {
        return dateFacture;
    }

    /**
     * Définit la date de la facture.
     *
     * @param dateFacture La date de la facture à définir.
     */
    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    /**
     * Récupère le montant total de la facture.
     *
     * @return Le montant de la facture.
     */
    public BigDecimal getMontantFacture() {
        return montantFacture;
    }

    /**
     * Définit le montant total de la facture.
     *
     * @param montantFacture Le montant de la facture à définir.
     */
    public void setMontantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
    }

    /**
     * Récupère le moyen de paiement utilisé pour régler la facture.
     *
     * @return Le moyen de paiement.
     */
    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Définit le moyen de paiement utilisé pour régler la facture.
     *
     * @param moyenPaiement Le moyen de paiement à définir.
     */
    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Facture}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Facture{" +
               "reference_facture='" + (referenceFacture != null ? referenceFacture : "N/A") + '\'' +
               ", type_facture='" + (typeFacture != null ? typeFacture : "N/A") + '\'' +
               ", date_facture=" + (dateFacture != null ? dateFacture : "N/A") +
               ", montant_facture=" + (montantFacture != null ? montantFacture : "N/A") +
               ", moyen_paiement='" + (moyenPaiement != null ? moyenPaiement : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateFacture, montantFacture, moyenPaiement, typeFacture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		return Objects.equals(dateFacture, other.dateFacture)
				&& Objects.equals(montantFacture, other.montantFacture)
				&& Objects.equals(moyenPaiement, other.moyenPaiement)
				&& Objects.equals(typeFacture, other.typeFacture);
	}

	
}
