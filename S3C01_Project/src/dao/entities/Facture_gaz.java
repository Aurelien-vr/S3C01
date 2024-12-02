package dao.entities;

import java.math.BigDecimal;

public class Facture_gaz {

    private int id_facture_gaz;
    private BigDecimal consommation_m3;
    private String prix_m3_gaz;
    private String reference_facture;

    public int getId_facture_gaz() {
        return id_facture_gaz;
    }

    public void setId_facture_gaz(int id_facture_gaz) {
        this.id_facture_gaz = id_facture_gaz;
    }

    public BigDecimal getConsommation_m3() {
        return consommation_m3;
    }

    public void setConsommation_m3(BigDecimal consommation_m3) {
        this.consommation_m3 = consommation_m3;
    }

    public String getPrix_m3_gaz() {
        return prix_m3_gaz;
    }

    public void setPrix_m3_gaz(String prix_m3_gaz) {
        this.prix_m3_gaz = prix_m3_gaz;
    }

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    @Override
    public String toString() {
        return "Facture_gaz{" +
               "id_facture_gaz=" + id_facture_gaz +
               ", consommation_m3=" + (consommation_m3 != null ? consommation_m3 : "N/A") +
               ", prix_m3_gaz='" + (prix_m3_gaz != null ? prix_m3_gaz : "N/A") + '\'' +
               ", reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               '}';
    }
}
