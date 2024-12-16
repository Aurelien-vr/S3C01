package dao.entities;

import java.util.Date;
import java.util.Objects;

/**
 * Représente un locataire.
 * Cette classe contient les informations relatives à un locataire, incluant son nom, prénom, date de naissance, IBAN et l'identifiant du contrat de location associé.
 */
public class Locataire {

    private int id_locataire;  // Identifiant unique du locataire
    private String nom;  // Nom du locataire
    private String prenom;  // Prénom du locataire
    private Date date_de_naissance;  // Date de naissance du locataire
    private String iban;  // IBAN du locataire pour le paiement des loyers
    private int id_contrat_location;  // Identifiant du contrat de location associé

    public Locataire() {}
    
    public Locataire(String nom, String prenom, Date date_de_naissance, String iban) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date_de_naissance = date_de_naissance;
		this.iban = iban;
	}

	/**
     * Récupère l'identifiant unique du locataire.
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
    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    /**
     * Définit la date de naissance du locataire.
     *
     * @param date_de_naissance La date de naissance du locataire à définir.
     */
    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
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
     * Récupère l'identifiant du contrat de location du locataire.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location du locataire.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
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
               "id_locataire=" + id_locataire +
               ", nom='" + (nom != null ? nom : "N/A") + '\'' +
               ", prenom='" + (prenom != null ? prenom : "N/A") + '\'' +
               ", date_de_naissance=" + (date_de_naissance != null ? date_de_naissance : "N/A") +
               ", iban='" + (iban != null ? iban : "N/A") + '\'' +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_de_naissance, iban, id_contrat_location, id_locataire, nom, prenom);
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
		return Objects.equals(date_de_naissance, other.date_de_naissance) && Objects.equals(iban, other.iban)
				&& id_contrat_location == other.id_contrat_location && id_locataire == other.id_locataire
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
}
