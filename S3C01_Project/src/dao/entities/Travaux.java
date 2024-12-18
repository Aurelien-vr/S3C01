package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente les travaux effectués sur un bien, avec des informations relatives à la facture,
 * à la réduction, au montant des travaux, et aux détails de paiement.
 */
public class Travaux {

    private int numero_facture;  // Numéro de la facture liée aux travaux
    private Date date_travaux;  // Date à laquelle les travaux ont été réalisés
    private String nature;  // Nature des travaux réalisés
    private String iban;  // IBAN du destinataire du paiement
    private BigDecimal reduction;  // Réduction appliquée sur le montant des travaux
    private BigDecimal montant;  // Montant total des travaux
    private BigDecimal montant_non_deductible;  // Montant des travaux non déductible
    private BigDecimal reduction_special;  // Réduction spéciale applicable
    private String reference_facture;  // Référence de la facture associée aux travaux

    
    
    public Travaux() {}

	public Travaux(Date date_travaux, String nature, String iban, BigDecimal reduction, BigDecimal montant,
			BigDecimal montant_non_deductible, BigDecimal reduction_special) {
		super();
		this.date_travaux = date_travaux;
		this.nature = nature;
		this.iban = iban;
		this.reduction = reduction;
		this.montant = montant;
		this.montant_non_deductible = montant_non_deductible;
		this.reduction_special = reduction_special;
	}

	/**
     * Récupère le numéro de la facture.
     *
     * @return Le numéro de la facture.
     */
    public int getNumero_facture() {
        return numero_facture;
    }

    /**
     * Définit le numéro de la facture.
     *
     * @param numero_facture Le numéro de la facture à définir.
     */
    public void setNumero_facture(int numero_facture) {
        this.numero_facture = numero_facture;
    }

    /**
     * Récupère la date des travaux effectués.
     *
     * @return La date des travaux.
     */
    public Date getDate_travaux() {
        return date_travaux;
    }

    /**
     * Définit la date des travaux effectués.
     *
     * @param date_travaux La date des travaux à définir.
     */
    public void setDate_travaux(Date date_travaux) {
        this.date_travaux = date_travaux;
    }

    /**
     * Récupère la nature des travaux.
     *
     * @return La nature des travaux.
     */
    public String getNature() {
        return nature;
    }

    /**
     * Définit la nature des travaux.
     *
     * @param nature La nature des travaux à définir.
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Récupère l'IBAN du destinataire du paiement.
     *
     * @return L'IBAN du destinataire.
     */
    public String getIban() {
        return iban;
    }

    /**
     * Définit l'IBAN du destinataire du paiement.
     *
     * @param iban L'IBAN à définir.
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Récupère la réduction appliquée sur le montant des travaux.
     *
     * @return Le montant de la réduction.
     */
    public BigDecimal getReduction() {
        return reduction;
    }

    /**
     * Définit la réduction appliquée sur le montant des travaux.
     *
     * @param reduction Le montant de la réduction à définir.
     */
    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    /**
     * Récupère le montant total des travaux.
     *
     * @return Le montant total des travaux.
     */
    public BigDecimal getMontant() {
        return montant;
    }

    /**
     * Définit le montant total des travaux.
     *
     * @param montant Le montant des travaux à définir.
     */
    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    /**
     * Récupère le montant des travaux non déductibles.
     *
     * @return Le montant des travaux non déductibles.
     */
    public BigDecimal getMontant_non_deductible() {
        return montant_non_deductible;
    }

    /**
     * Définit le montant des travaux non déductibles.
     *
     * @param montant_non_deductible Le montant des travaux non déductibles à définir.
     */
    public void setMontant_non_deductible(BigDecimal montant_non_deductible) {
        this.montant_non_deductible = montant_non_deductible;
    }

    /**
     * Récupère le montant de la réduction spéciale appliquée.
     *
     * @return Le montant de la réduction spéciale.
     */
    public BigDecimal getReduction_special() {
        return reduction_special;
    }

    /**
     * Définit le montant de la réduction spéciale appliquée.
     *
     * @param reduction_special Le montant de la réduction spéciale à définir.
     */
    public void setReduction_special(BigDecimal reduction_special) {
        this.reduction_special = reduction_special;
    }

    /**
     * Récupère la référence de la facture associée aux travaux.
     *
     * @return La référence de la facture.
     */
    public String getReference_facture() {
        return reference_facture;
    }

    /**
     * Définit la référence de la facture associée aux travaux.
     *
     * @param reference_facture La référence de la facture à définir.
     */
    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Travaux}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Travaux{" +
               "numero_facture=" + numero_facture +
               ", date_travaux=" + (date_travaux != null ? date_travaux : "N/A") +
               ", nature='" + (nature != null ? nature : "N/A") + '\'' +
               ", iban='" + (iban != null ? iban : "N/A") + '\'' +
               ", reduction=" + (reduction != null ? reduction : "N/A") +
               ", montant=" + (montant != null ? montant : "N/A") +
               ", montant_non_deductible=" + (montant_non_deductible != null ? montant_non_deductible : "N/A") +
               ", reduction_special=" + (reduction_special != null ? reduction_special : "N/A") +
               ", reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_travaux, iban, montant, montant_non_deductible, nature, numero_facture, reduction,
				reduction_special, reference_facture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Travaux other = (Travaux) obj;
		return Objects.equals(date_travaux, other.date_travaux) && Objects.equals(iban, other.iban)
				&& Objects.equals(montant, other.montant)
				&& Objects.equals(montant_non_deductible, other.montant_non_deductible)
				&& Objects.equals(nature, other.nature) && numero_facture == other.numero_facture
				&& Objects.equals(reduction, other.reduction)
				&& Objects.equals(reduction_special, other.reduction_special)
				&& Objects.equals(reference_facture, other.reference_facture);
	}
}
