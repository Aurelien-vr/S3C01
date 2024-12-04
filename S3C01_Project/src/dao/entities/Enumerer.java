package dao.entities;

/**
 * Représente une entité qui fait le lien entre une facture et un solde de tout compte.
 * Cette classe contient la référence de la facture et l'identifiant du solde de tout compte associé.
 */
public class Enumerer {

    private String reference_facture;  // Référence unique de la facture
    private int id_solde_de_tout_compte;  // Identifiant du solde de tout compte lié à la facture

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
     * Récupère l'identifiant du solde de tout compte.
     *
     * @return L'identifiant du solde de tout compte.
     */
    public int getId_solde_de_tout_compte() {
        return id_solde_de_tout_compte;
    }

    /**
     * Définit l'identifiant du solde de tout compte.
     *
     * @param id_solde_de_tout_compte L'identifiant du solde de tout compte à définir.
     */
    public void setId_solde_de_tout_compte(int id_solde_de_tout_compte) {
        this.id_solde_de_tout_compte = id_solde_de_tout_compte;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Enumerer}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Enumerer{" +
               "reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               ", id_solde_de_tout_compte=" + id_solde_de_tout_compte +
               '}';
    }
}
