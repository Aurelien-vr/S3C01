package dao.entities;

import java.math.BigDecimal;

/**
 * Représente une facture de gaz.
 * Cette classe contient les informations relatives à une facture de gaz,
 * incluant la consommation en mètres cubes, le prix par mètre cube et la référence de la facture.
 */
public class Facture_gaz {

    private int id_facture_gaz;  // Identifiant unique de la facture de gaz
    private BigDecimal consommation_m3;  // Consommation de gaz en mètres cubes
    private String prix_m3_gaz;  // Prix par mètre cube de gaz
    private String reference_facture;  // Référence unique de la facture de gaz

    /**
     * Récupère l'identifiant de la facture de gaz.
     *
     * @return L'identifiant de la facture de gaz.
     */
    public int getId_facture_gaz() {
        return id_facture_gaz;
    }

    /**
     * Définit l'identifiant de la facture de gaz.
     *
     * @param id_facture_gaz L'identifiant de la facture de gaz à définir.
     */
    public void setId_facture_gaz(int id_facture_gaz) {
        this.id_facture_gaz = id_facture_gaz;
    }

    /**
     * Récupère la consommation de gaz en mètres cubes.
     *
     * @return La consommation de gaz en mètres cubes.
     */
    public BigDecimal getConsommation_m3() {
        return consommation_m3;
    }

    /**
     * Définit la consommation de gaz en mètres cubes.
     *
     * @param consommation_m3 La consommation de gaz à définir.
     */
    public void setConsommation_m3(BigDecimal consommation_m3) {
        this.consommation_m3 = consommation_m3;
    }

    /**
     * Récupère le prix par mètre cube de gaz.
     *
     * @return Le prix par mètre cube de gaz.
     */
    public String getPrix_m3_gaz() {
        return prix_m3_gaz;
    }

    /**
     * Définit le prix par mètre cube de gaz.
     *
     * @param prix_m3_gaz Le prix du gaz par mètre cube à définir.
     */
    public void setPrix_m3_gaz(String prix_m3_gaz) {
        this.prix_m3_gaz = prix_m3_gaz;
    }

    /**
     * Récupère la référence de la facture de gaz.
     *
     * @return La référence de la facture de gaz.
     */
    public String getReference_facture() {
        return reference_facture;
    }

    /**
     * Définit la référence de la facture de gaz.
     *
     * @param reference_facture La référence de la facture de gaz à définir.
     */
    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Facture_gaz}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
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
