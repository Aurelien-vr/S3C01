package dao.entities;

import java.math.BigDecimal;

public class Facture_eau {

    private int id_facture_eau;
    private BigDecimal partie_fixe;
    private BigDecimal consommation;
    private String reference_facture;

    public int getId_facture_eau() {
        return id_facture_eau;
    }

    public void setId_facture_eau(int id_facture_eau) {
        this.id_facture_eau = id_facture_eau;
    }

    public BigDecimal getPartie_fixe() {
        return partie_fixe;
    }

    public void setPartie_fixe(BigDecimal partie_fixe) {
        this.partie_fixe = partie_fixe;
    }

    public BigDecimal getConsommation() {
        return consommation;
    }

    public void setConsommation(BigDecimal consommation) {
        this.consommation = consommation;
    }

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    @Override
    public String toString() {
        return "Facture_eau{" +
               "id_facture_eau=" + id_facture_eau +
               ", partie_fixe=" + (partie_fixe != null ? partie_fixe : "N/A") +
               ", consommation=" + (consommation != null ? consommation : "N/A") +
               ", reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               '}';
    }
}
