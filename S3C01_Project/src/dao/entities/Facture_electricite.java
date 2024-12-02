package dao.entities;

import java.math.BigDecimal;

public class Facture_electricite {

    private int id_facture_electricite;
    private BigDecimal compteur_electricite;
    private String prix_kw_electricite;
    private String reference_facture;

    public int getId_facture_electricite() {
        return id_facture_electricite;
    }

    public void setId_facture_electricite(int id_facture_electricite) {
        this.id_facture_electricite = id_facture_electricite;
    }

    public BigDecimal getCompteur_electricite() {
        return compteur_electricite;
    }

    public void setCompteur_electricite(BigDecimal compteur_electricite) {
        this.compteur_electricite = compteur_electricite;
    }

    public String getPrix_kw_electricite() {
        return prix_kw_electricite;
    }

    public void setPrix_kw_electricite(String prix_kw_electricite) {
        this.prix_kw_electricite = prix_kw_electricite;
    }

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    @Override
    public String toString() {
        return "Facture_electricite{" +
               "id_facture_electricite=" + id_facture_electricite +
               ", compteur_electricite=" + (compteur_electricite != null ? compteur_electricite : "N/A") +
               ", prix_kw_electricite='" + (prix_kw_electricite != null ? prix_kw_electricite : "N/A") + '\'' +
               ", reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               '}';
    }
}
