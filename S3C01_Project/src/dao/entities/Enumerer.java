package dao.entities;

import java.util.Objects;

/**
 * Représente une entité Enumerer avec une référence de facture et un identifiant de solde de tout compte.
 */
public class Enumerer {

    private String reference_facture; // Référence unique de la facture
    private int id_solde_de_tout_compte; // Identifiant du solde de tout compte associé

    /**
     * Constructeur par défaut de la classe Enumerer.
     */
    public Enumerer() {
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param reference_facture         La référence de la facture.
     * @param id_solde_de_tout_compte   L'identifiant du solde de tout compte.
     */
    public Enumerer(String reference_facture, int id_solde_de_tout_compte) {
        this.reference_facture = reference_facture;
        this.id_solde_de_tout_compte = id_solde_de_tout_compte;
    }

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
     *
     * @return Une chaîne de caractères représentant l'objet Enumerer.
     */
    @Override
    public String toString() {
        return "Enumerer{" +
               "reference_facture='" + reference_facture + '\'' +
               ", id_solde_de_tout_compte=" + id_solde_de_tout_compte +
               '}';
    }

    /**
     * Calcule le hashcode de l'objet {@link Enumerer}.
     *
     * @return Le hashcode de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reference_facture, id_solde_de_tout_compte);
    }

    /**
     * Vérifie l'égalité entre cet objet {@link Enumerer} et un autre objet.
     *
     * @param obj L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enumerer enumerer = (Enumerer) obj;
        return id_solde_de_tout_compte == enumerer.id_solde_de_tout_compte &&
               Objects.equals(reference_facture, enumerer.reference_facture);
    }
}
