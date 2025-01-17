package dao.entities;

import java.util.Objects;

/**
 * Représente une entité Enumerer avec une référence de facture et un identifiant de solde de tout compte.
 */
public class Enumerer {

    private String referenceFacture; // Référence unique de la facture
    private int idSoldeDeToutCompte; // Identifiant du solde de tout compte associé

    /**
     * Constructeur par défaut de la classe Enumerer.
     */
    public Enumerer() {
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param referenceFacture         La référence de la facture.
     * @param idSoldeDeToutCompte   L'identifiant du solde de tout compte.
     */
    public Enumerer(String referenceFacture, int idSoldeDeToutCompte) {
        this.referenceFacture = referenceFacture;
        this.idSoldeDeToutCompte = idSoldeDeToutCompte;
    }

    /**
     * Récupère la référence de la facture.
     *
     * @return La référence de la facture.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture.
     *
     * @param referenceFacture La référence de la facture à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    /**
     * Récupère l'identifiant du solde de tout compte.
     *
     * @return L'identifiant du solde de tout compte.
     */
    public int getIdSoldeDeToutCompte() {
        return idSoldeDeToutCompte;
    }

    /**
     * Définit l'identifiant du solde de tout compte.
     *
     * @param idSoldeDeToutCompte L'identifiant du solde de tout compte à définir.
     */
    public void setIdSoldeDeToutCompte(int idSoldeDeToutCompte) {
        this.idSoldeDeToutCompte = idSoldeDeToutCompte;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Enumerer}.
     *
     * @return Une chaîne de caractères représentant l'objet Enumerer.
     */
    @Override
    public String toString() {
        return "Enumerer{" +
               "reference_facture='" + referenceFacture + '\'' +
               ", id_solde_de_tout_compte=" + idSoldeDeToutCompte +
               '}';
    }

    /**
     * Calcule le hashcode de l'objet {@link Enumerer}.
     *
     * @return Le hashcode de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(referenceFacture, idSoldeDeToutCompte);
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
        return idSoldeDeToutCompte == enumerer.idSoldeDeToutCompte &&
               Objects.equals(referenceFacture, enumerer.referenceFacture);
    }
}