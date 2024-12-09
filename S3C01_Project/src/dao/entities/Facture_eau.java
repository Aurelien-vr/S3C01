package dao.entities;

import java.math.BigDecimal;

/**
 * Représente une facture d'eau.
 * Cette classe contient les informations relatives à une facture d'eau, incluant la partie fixe,
 * la consommation et la référence de la facture.
 */
public class Facture_eau {

    private int id_facture_eau;  // Identifiant unique de la facture d'eau
    private BigDecimal partie_fixe;  // Partie fixe de la facture d'eau (ex: frais fixes pour l'abonnement)
    private BigDecimal consommation;  // Consommation d'eau facturée
    private String reference_facture;  // Référence unique de la facture

    /**
     * Récupère l'identifiant de la facture d'eau.
     *
     * @return L'identifiant de la facture d'eau.
     */
    public int getId_facture_eau() {
        return id_facture_eau;
    }

    /**
     * Définit l'identifiant de la facture d'eau.
     *
     * @param id_facture_eau L'identifiant de la facture d'eau à définir.
     */
    public void setId_facture_eau(int id_facture_eau) {
        this.id_facture_eau = id_facture_eau;
    }

    /**
     * Récupère la partie fixe de la facture d'eau.
     *
     * @return La partie fixe de la facture d'eau.
     */
    public BigDecimal getPartie_fixe() {
        return partie_fixe;
    }

    /**
     * Définit la partie fixe de la facture d'eau.
     *
     * @param partie_fixe La partie fixe à définir.
     */
    public void setPartie_fixe(BigDecimal partie_fixe) {
        this.partie_fixe = partie_fixe;
    }

    /**
     * Récupère la consommation d'eau facturée.
     *
     * @return La consommation d'eau.
     */
    public BigDecimal getConsommation() {
        return consommation;
    }

    /**
     * Définit la consommation d'eau facturée.
     *
     * @param consommation La consommation d'eau à définir.
     */
    public void setConsommation(BigDecimal consommation) {
        this.consommation = consommation;
    }

    /**
     * Récupère la référence de la facture d'eau.
     *
     * @return La référence de la facture.
     */
    public String getReference_facture() {
        return reference_facture;
    }

    /**
     * Définit la référence de la facture d'eau.
     *
     * @param reference_facture La référence de la facture à définir.
     */
    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Facture_eau}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
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
