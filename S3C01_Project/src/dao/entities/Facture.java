package dao.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Facture {

    private String reference_facture;
    private String type_facture;
    private Date date_facture;
    private BigDecimal montant_facture;
    private String moyen_paiement;
    private int id_bien;

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    public String getType_facture() {
        return type_facture;
    }

    public void setType_facture(String type_facture) {
        this.type_facture = type_facture;
    }

    public Date getDate_facture() {
        return date_facture;
    }

    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }

    public BigDecimal getMontant_facture() {
        return montant_facture;
    }

    public void setMontant_facture(BigDecimal montant_facture) {
        this.montant_facture = montant_facture;
    }

    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    public int getId_bien() {
        return id_bien;
    }

    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

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
}
