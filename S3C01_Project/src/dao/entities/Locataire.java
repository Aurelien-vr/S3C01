package dao.entities;

import java.sql.Date;
import java.util.Objects;

/**
 * Représente un locataire.
 * Cette classe contient les informations relatives à un locataire, incluant son nom, prénom, date de naissance, IBAN et l'identifiant du contrat de location associé.
 */
public class Locataire {

    private int idLocataire;  // Identifiant unique du locataire
    private String nom;  // Nom du locataire
    private String prenom;  // Prénom du locataire
    private Date dateNeNaissance;  // Date de naissance du locataire
    private String iban;  // IBAN du locataire pour le paiement des loyers

    public Locataire() {}
    
    public Locataire(String nom, String prenom, Date dateDeNaissance, String iban) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNeNaissance = dateDeNaissance;
		this.iban = iban;
	}

	/**
     * Récupère l'identifiant unique du locataire.
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
     * Récupère le nom du locataire.
     *
     * @return Le nom du locataire.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du locataire.
     *
     * @param nom Le nom du locataire à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom du locataire.
     *
     * @return Le prénom du locataire.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du locataire.
     *
     * @param prenom Le prénom du locataire à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la date de naissance du locataire.
     *
     * @return La date de naissance du locataire.
     */
    public Date getDateDeNaissance() {
        return dateNeNaissance;
    }

    /**
     * Définit la date de naissance du locataire.
     *
     * @param dateDeNaissance La date de naissance du locataire à définir.
     */
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateNeNaissance = dateDeNaissance;
    }

    /**
     * Récupère l'IBAN du locataire.
     *
     * @return L'IBAN du locataire.
     */
    public String getIban() {
        return iban;
    }

    /**
     * Définit l'IBAN du locataire.
     *
     * @param iban L'IBAN du locataire à définir.
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Locataire}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Locataire{" +
               "id_locataire=" + idLocataire +
               ", nom='" + (nom != null ? nom : "N/A") + '\'' +
               ", prenom='" + (prenom != null ? prenom : "N/A") + '\'' +
               ", date_de_naissance=" + (dateNeNaissance != null ? dateNeNaissance : "N/A") +
               ", iban='" + (iban != null ? iban : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateNeNaissance, iban, idLocataire, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locataire other = (Locataire) obj;
		return Objects.equals(dateNeNaissance, other.dateNeNaissance) && Objects.equals(iban, other.iban)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
}
