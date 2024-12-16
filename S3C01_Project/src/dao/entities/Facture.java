package dao.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Représente une facture.
 * Cette classe contient les informations relatives à une facture,
 * incluant la référence de la facture, le type de facture, la date, le montant, le moyen de paiement, et l'identifiant du bien associé.
 */
public class Facture {

    private String reference_facture;  // Référence unique de la facture
    private String type_facture;  // Type de la facture (par exemple, eau, électricité, gaz, etc.)
    private Date date_facture;  // Date à laquelle la facture a été émise
    private BigDecimal montant_facture;  // Montant total de la facture
    private String moyen_paiement;  // Moyen de paiement utilisé pour régler la facture
    private int id_bien;  // Identifiant du bien associé à cette facture
    
    
    
    public Facture() {}

	public Facture(String type_facture, Date date_facture, BigDecimal montant_facture, String moyen_paiement) {
		super();
		this.type_facture = type_facture;
		this.date_facture = date_facture;
		this.montant_facture = montant_facture;
		this.moyen_paiement = moyen_paiement;
	}

	/**
     * Récupère la référence de la facture.
     *
     * @return La référence de la facture.
     */
    public String getReference_facture() {
        return reference_facture;
    }

    /**
     * Définit la référence de la facture.
     *
     * @param reference_facture La référence de la facture à définir.
     */
    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    /**
     * Récupère le type de la facture.
     *
     * @return Le type de la facture (par exemple, eau, électricité).
     */
    public String getType_facture() {
        return type_facture;
    }

    /**
     * Définit le type de la facture.
     *
     * @param type_facture Le type de la facture à définir.
     */
    public void setType_facture(String type_facture) {
        this.type_facture = type_facture;
    }

    /**
     * Récupère la date de la facture.
     *
     * @return La date de la facture.
     */
    public Date getDate_facture() {
        return date_facture;
    }

    /**
     * Définit la date de la facture.
     *
     * @param date_facture La date de la facture à définir.
     */
    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }

    /**
     * Récupère le montant total de la facture.
     *
     * @return Le montant de la facture.
     */
    public BigDecimal getMontant_facture() {
        return montant_facture;
    }

    /**
     * Définit le montant total de la facture.
     *
     * @param montant_facture Le montant de la facture à définir.
     */
    public void setMontant_facture(BigDecimal montant_facture) {
        this.montant_facture = montant_facture;
    }

    /**
     * Récupère le moyen de paiement utilisé pour régler la facture.
     *
     * @return Le moyen de paiement.
     */
    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    /**
     * Définit le moyen de paiement utilisé pour régler la facture.
     *
     * @param moyen_paiement Le moyen de paiement à définir.
     */
    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    /**
     * Récupère l'identifiant du bien associé à la facture.
     *
     * @return L'identifiant du bien.
     */
    public int getId_bien() {
        return id_bien;
    }

    /**
     * Définit l'identifiant du bien associé à la facture.
     *
     * @param id_bien L'identifiant du bien à définir.
     */
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
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
               "reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               ", type_facture='" + (type_facture != null ? type_facture : "N/A") + '\'' +
               ", date_facture=" + (date_facture != null ? date_facture : "N/A") +
               ", montant_facture=" + (montant_facture != null ? montant_facture : "N/A") +
               ", moyen_paiement='" + (moyen_paiement != null ? moyen_paiement : "N/A") + '\'' +
               ", id_bien=" + id_bien +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_facture, id_bien, montant_facture, moyen_paiement, reference_facture, type_facture);
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
		return Objects.equals(date_facture, other.date_facture) && id_bien == other.id_bien
				&& Objects.equals(montant_facture, other.montant_facture)
				&& Objects.equals(moyen_paiement, other.moyen_paiement)
				&& Objects.equals(reference_facture, other.reference_facture)
				&& Objects.equals(type_facture, other.type_facture);
	}

	
}
