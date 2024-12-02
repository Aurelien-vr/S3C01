package dao.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Travaux {

    private int numero_facture;
    private Date date_travaux;
    private String nature;
    private String iban;
    private BigDecimal reduction;
    private BigDecimal montant;
    private BigDecimal montant_non_deductible;
    private BigDecimal reduction_special;
    private String reference_facture;

    public int getNumero_facture() {
        return numero_facture;
    }

    public void setNumero_facture(int numero_facture) {
        this.numero_facture = numero_facture;
    }

    public Date getDate_travaux() {
        return date_travaux;
    }

    public void setDate_travaux(Date date_travaux) {
        this.date_travaux = date_travaux;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getReduction() {
        return reduction;
    }

    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontant_non_deductible() {
        return montant_non_deductible;
    }

    public void setMontant_non_deductible(BigDecimal montant_non_deductible) {
        this.montant_non_deductible = montant_non_deductible;
    }

    public BigDecimal getReduction_special() {
        return reduction_special;
    }

    public void setReduction_special(BigDecimal reduction_special) {
        this.reduction_special = reduction_special;
    }

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

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
}
