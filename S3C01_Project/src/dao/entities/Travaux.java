package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente les travaux effectués sur un bien, avec des informations relatives à la facture,
 * à la réduction, au montant des travaux, et aux détails de paiement.
 */
public class Travaux {

    private int numeroFacture;  // Numéro de la facture liée aux travaux
    private Date dateTravaux;  // Date à laquelle les travaux ont été réalisés
    private String nature;  // Nature des travaux réalisés
    private String iban;  // IBAN du destinataire du paiement
    private BigDecimal reduction;  // Réduction appliquée sur le montant des travaux
    private BigDecimal montant;  // Montant total des travaux
    private BigDecimal montantNonDeductible;  // Montant des travaux non déductible
    private BigDecimal reductionSpeciale;  // Réduction spéciale applicable
    private String referenceFacture;  // Référence de la facture associée aux travaux

    
    
    public Travaux() {}

	public Travaux(Date dateTravaux, String nature, String iban, BigDecimal reduction, BigDecimal montant,
			BigDecimal montantNonDeductible, BigDecimal reductionSpecial) {
		super();
		this.dateTravaux = dateTravaux;
		this.nature = nature;
		this.iban = iban;
		this.reduction = reduction;
		this.montant = montant;
		this.montantNonDeductible = montantNonDeductible;
		this.reductionSpeciale = reductionSpecial;
	}

	/**
     * Récupère le numéro de la facture.
     *
     * @return Le numéro de la facture.
     */
    public int getNumeroFacture() {
        return numeroFacture;
    }

    /**
     * Définit le numéro de la facture.
     *
     * @param string Le numéro de la facture à définir.
     */
    public void setNumeroFacture(int numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    /**
     * Récupère la date des travaux effectués.
     *
     * @return La date des travaux.
     */
    public Date getDateTravaux() {
        return dateTravaux;
    }

    /**
     * Définit la date des travaux effectués.
     *
     * @param dateTravaux La date des travaux à définir.
     */
    public void setDateTravaux(Date dateTravaux) {
        this.dateTravaux = dateTravaux;
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
    public BigDecimal getMontantNonDeductible() {
        return montantNonDeductible;
    }

    /**
     * Définit le montant des travaux non déductibles.
     *
     * @param montantNonDeductible Le montant des travaux non déductibles à définir.
     */
    public void setMontantNonDeductible(BigDecimal montantNonDeductible) {
        this.montantNonDeductible = montantNonDeductible;
    }

    /**
     * Récupère le montant de la réduction spéciale appliquée.
     *
     * @return Le montant de la réduction spéciale.
     */
    public BigDecimal getReductionSpeciale() {
        return reductionSpeciale;
    }

    /**
     * Définit le montant de la réduction spéciale appliquée.
     *
     * @param reductionSpeciale Le montant de la réduction spéciale à définir.
     */
    public void setReductionSpeciale(BigDecimal reductionSpeciale) {
        this.reductionSpeciale = reductionSpeciale;
    }

    /**
     * Récupère la référence de la facture associée aux travaux.
     *
     * @return La référence de la facture.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture associée aux travaux.
     *
     * @param referenceFacture La référence de la facture à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
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
               "numero_facture=" + numeroFacture +
               ", date_travaux=" + (dateTravaux != null ? dateTravaux : "N/A") +
               ", nature='" + (nature != null ? nature : "N/A") + '\'' +
               ", iban='" + (iban != null ? iban : "N/A") + '\'' +
               ", reduction=" + (reduction != null ? reduction : "N/A") +
               ", montant=" + (montant != null ? montant : "N/A") +
               ", montant_non_deductible=" + (montantNonDeductible != null ? montantNonDeductible : "N/A") +
               ", reduction_special=" + (reductionSpeciale != null ? reductionSpeciale : "N/A") +
               ", reference_facture='" + (referenceFacture != null ? referenceFacture : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateTravaux, iban, montant, montantNonDeductible, nature, numeroFacture, reduction,
				reductionSpeciale, referenceFacture);
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
		return Objects.equals(dateTravaux, other.dateTravaux) && Objects.equals(iban, other.iban)
				&& Objects.equals(montant, other.montant)
				&& Objects.equals(montantNonDeductible, other.montantNonDeductible)
				&& Objects.equals(nature, other.nature) 
				&& Objects.equals(reduction, other.reduction)
				&& Objects.equals(reductionSpeciale, other.reductionSpeciale)
				&& Objects.equals(referenceFacture, other.referenceFacture);
	}
}
