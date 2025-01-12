package dao.entities;

import java.util.Objects;

/**
 * Représente une entité Avancer avec un identifiant de locataire et un numéro de facture.
 */
public class Avancer {

    private int id_locataire; // Identifiant unique du locataire
    private int numero_facture; // Numéro de la facture associée

    /**
     * Constructeur par défaut de la classe Avancer.
     */
    public Avancer() {
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param id_locataire   L'identifiant du locataire.
     * @param numero_facture Le numéro de la facture associée.
     */
    public Avancer(int id_locataire, int numero_facture) {
        this.id_locataire = id_locataire;
        this.numero_facture = numero_facture;
    }

    /**
     * Récupère l'identifiant du locataire.
     *
     * @return L'identifiant du locataire.
     */
    public int getId_locataire() {
        return id_locataire;
    }

    /**
     * Définit l'identifiant du locataire.
     *
     * @param id_locataire L'identifiant du locataire à définir.
     */
    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    /**
     * Récupère le numéro de la facture associée.
     *
     * @return Le numéro de la facture.
     */
    public int getNumero_facture() {
        return numero_facture;
    }

    /**
     * Définit le numéro de la facture associée.
     *
     * @param numero_facture Le numéro de la facture à définir.
     */
    public void setNumero_facture(int numero_facture) {
        this.numero_facture = numero_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Avancer}.
     *
     * @return Une chaîne de caractères représentant l'objet Avancer.
     */
    @Override
    public String toString() {
        return "Avancer{" +
               "id_locataire=" + id_locataire +
               ", numero_facture=" + numero_facture +
               '}';
    }

    /**
     * Calcule le hashcode de l'objet {@link Avancer}.
     *
     * @return Le hashcode de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_locataire, numero_facture);
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
        return id_locataire == avancer.id_locataire &&
               numero_facture == avancer.numero_facture;
    }
}
