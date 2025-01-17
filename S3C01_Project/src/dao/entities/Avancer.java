package dao.entities;

import java.util.Objects;

/**
 * Représente une entité Avancer avec un identifiant de locataire et un numéro de facture.
 */
public class Avancer {

    private int idLocataire; // Identifiant unique du locataire
    private int numeroFacture; // Numéro de la facture associée

    /**
     * Constructeur par défaut de la classe Avancer.
     */
    public Avancer() {
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param idLocataire   L'identifiant du locataire.
     * @param numeroFacture Le numéro de la facture associée.
     */
    public Avancer(int idLocataire, int numeroFacture) {
        this.idLocataire = idLocataire;
        this.numeroFacture = numeroFacture;
    }

    /**
     * Récupère l'identifiant du locataire.
     *
     * @return L'identifiant du locataire.
     */
    public int getIdLocataire() {
        return idLocataire;
    }

    /**
     * Définit l'identifiant du locataire.
     *
     * @param idLocataire L'identifiant du locataire à définir.
     */
    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
    }

    /**
     * Récupère le numéro de la facture associée.
     *
     * @return Le numéro de la facture.
     */
    public int getNumeroFacture() {
        return numeroFacture;
    }

    /**
     * Définit le numéro de la facture associée.
     *
     * @param numeroFacture Le numéro de la facture à définir.
     */
    public void setNumeroFacture(int numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Avancer}.
     *
     * @return Une chaîne de caractères représentant l'objet Avancer.
     */
    @Override
    public String toString() {
        return "Avancer{" +
               "id_locataire=" + idLocataire +
               ", numero_facture=" + numeroFacture +
               '}';
    }

    /**
     * Calcule le hashcode de l'objet {@link Avancer}.
     *
     * @return Le hashcode de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idLocataire, numeroFacture);
    }

    /**
     * Vérifie l'égalité entre cet objet {@link Avancer} et un autre objet.
     *
     * @param obj L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Avancer avancer = (Avancer) obj;
        return idLocataire == avancer.idLocataire &&
               numeroFacture == avancer.numeroFacture;
    }
}