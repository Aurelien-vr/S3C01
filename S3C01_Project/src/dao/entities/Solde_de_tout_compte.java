package dao.entities;

import java.math.BigDecimal;

/**
 * Représente le solde de tout compte d'un locataire à la fin de son contrat de location.
 * Cette classe contient des informations sur le reste à devoir, la provision pour charges,
 * la caution et l'identifiant du contrat de location associé.
 */
public class Solde_de_tout_compte {

    private int id_solde_de_tout_compte;  // Identifiant unique du solde de tout compte
    private BigDecimal reste_a_devoir;  // Montant restant à devoir par le locataire
    private BigDecimal provision_pour_charges;  // Montant de la provision pour charges
    private BigDecimal caution;  // Montant de la caution à restituer ou à déduire
    private int id_contrat_location;  // Identifiant du contrat de location associé au solde de tout compte

    /**
     * Récupère l'identifiant unique du solde de tout compte.
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
     * Récupère le montant restant à devoir par le locataire.
     *
     * @return Le montant restant à devoir.
     */
    public BigDecimal getReste_a_devoir() {
        return reste_a_devoir;
    }

    /**
     * Définit le montant restant à devoir par le locataire.
     *
     * @param reste_a_devoir Le montant restant à devoir à définir.
     */
    public void setReste_a_devoir(BigDecimal reste_a_devoir) {
        this.reste_a_devoir = reste_a_devoir;
    }

    /**
     * Récupère le montant de la provision pour charges.
     *
     * @return Le montant de la provision pour charges.
     */
    public BigDecimal getProvision_pour_charges() {
        return provision_pour_charges;
    }

    /**
     * Définit le montant de la provision pour charges.
     *
     * @param provision_pour_charges Le montant de la provision pour charges à définir.
     */
    public void setProvision_pour_charges(BigDecimal provision_pour_charges) {
        this.provision_pour_charges = provision_pour_charges;
    }

    /**
     * Récupère le montant de la caution.
     *
     * @return Le montant de la caution.
     */
    public BigDecimal getCaution() {
        return caution;
    }

    /**
     * Définit le montant de la caution.
     *
     * @param caution Le montant de la caution à définir.
     */
    public void setCaution(BigDecimal caution) {
        this.caution = caution;
    }

    /**
     * Récupère l'identifiant du contrat de location associé à ce solde de tout compte.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location associé à ce solde de tout compte.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Solde_de_tout_compte}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Solde_de_tout_compte{" +
               "id_solde_de_tout_compte=" + id_solde_de_tout_compte +
               ", reste_a_devoir=" + (reste_a_devoir != null ? reste_a_devoir : "N/A") +
               ", provision_pour_charges=" + (provision_pour_charges != null ? provision_pour_charges : "N/A") +
               ", caution=" + (caution != null ? caution : "N/A") +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }
}
