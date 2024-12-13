package dao.entities;

import java.util.Objects;

/**
 * Représente une avance effectuée par un locataire.
 * Cette classe contient les informations relatives à une avance, 
 * notamment l'identifiant du locataire et le numéro de la facture associée.
 */
public class Avancer {

    private int id_locataire;  // Identifiant du locataire qui a effectué l'avance
    private int numero_facture;  // Numéro de la facture associée à l'avance

    /**
     * Récupère l'identifiant du locataire ayant effectué l'avance.
     *
     * @return L'identifiant du locataire.
     */
    public int getId_locataire() {
        return id_locataire;
    }

    /**
     * Définit l'identifiant du locataire ayant effectué l'avance.
     *
     * @param id_locataire L'identifiant du locataire à définir.
     */
    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    /**
     * Récupère le numéro de la facture associée à l'avance.
     *
     * @return Le numéro de la facture.
     */
    public int getNumero_facture() {
        return numero_facture;
    }

    /**
     * Définit le numéro de la facture associée à l'avance.
     *
     * @param numero_facture Le numéro de la facture à définir.
     */
    public void setNumero_facture(int numero_facture) {
        this.numero_facture = numero_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Avancer}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'avance.
     */
    @Override
    public String toString() {
        return "Avancer{" +
               "id_locataire=" + id_locataire +
               ", numero_facture=" + numero_facture +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(id_locataire, numero_facture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avancer other = (Avancer) obj;
		return id_locataire == other.id_locataire && numero_facture == other.numero_facture;
	}
    
}
