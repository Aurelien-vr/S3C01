package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente une assurance liée à un bien.
 * Cette classe contient les informations relatives à une police d'assurance, 
 * notamment le numéro de contrat, la prime, le taux d'augmentation, la protection juridique et l'identifiant du bien assuré.
 */
public class Assurance {

    private int numero_contrat;  // Numéro de contrat d'assurance
    private Date dateAssurance;
    private BigDecimal prime;  // Montant de la prime d'assurance
    private BigDecimal protection_juridique;  // Montant de la protection juridique incluse
    private int id_bien;  // Identifiant du bien assuré

    public Assurance() {};
    
    public Assurance(Date dateAssurance, BigDecimal prime, BigDecimal protection_juridique) {
		super();
		this.dateAssurance = dateAssurance;
		this.prime = prime;
		this.protection_juridique = protection_juridique;
	}

	/**
     * Récupère le numéro du contrat d'assurance.
     *
     * @return Le numéro de contrat d'assurance.
     */
    public int getNumero_contrat() {
        return numero_contrat;
    }

    /**
     * Définit le numéro du contrat d'assurance.
     *
     * @param numero_contrat Le numéro de contrat d'assurance à définir.
     */
    public void setNumero_contrat(int numero_contrat) {
        this.numero_contrat = numero_contrat;
    }

    /**
     * Récupère le montant de la prime d'assurance.
     *
     * @return Le montant de la prime.
     */
    public BigDecimal getPrime() {
        return prime;
    }

    /**
     * Définit le montant de la prime d'assurance.
     *
     * @param prime Le montant de la prime à définir.
     */
    public void setPrime(BigDecimal prime) {
        this.prime = prime;
    }

    /**
     * Récupère le taux d'augmentation de la prime d'assurance.
     *
     * @return Le taux d'augmentation de la prime.
     */
    public Date getDateAssurance() {
        return dateAssurance;
    }

    /**
     * Définit le taux d'augmentation de la prime d'assurance.
     *
     * @param taux_augmentation Le taux d'augmentation à définir.
     */
    public void setDateAssurance(Date dateAssurance) {
        this.dateAssurance = dateAssurance;
    }

    /**
     * Récupère le montant de la protection juridique.
     *
     * @return Le montant de la protection juridique.
     */
    public BigDecimal getProtection_juridique() {
        return protection_juridique;
    }

    /**
     * Définit le montant de la protection juridique.
     *
     * @param protection_juridique Le montant de la protection juridique à définir.
     */
    public void setProtection_juridique(BigDecimal protection_juridique) {
        this.protection_juridique = protection_juridique;
    }

    /**
     * Récupère l'identifiant du bien assuré par le contrat.
     *
     * @return L'identifiant du bien assuré.
     */
    public int getId_bien() {
        return id_bien;
    }

    /**
     * Définit l'identifiant du bien assuré par le contrat.
     *
     * @param id_bien L'identifiant du bien à définir.
     */
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Assurance}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'assurance.
     */
    @Override
    public String toString() {
        return "Assurance{" +
               "numero_contrat=" + numero_contrat +
               ", prime=" + (prime != null ? prime : "N/A") +
               ", date_assurance=" + (dateAssurance != null ? dateAssurance : "N/A") +
               ", protection_juridique=" + (protection_juridique != null ? protection_juridique : "N/A") +
               ", id_bien=" + id_bien +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Assurance that = (Assurance) obj;
        return this.numero_contrat == that.numero_contrat; // Comparaison basée sur le numero_contrat
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero_contrat); // Assurez-vous de définir un bon hashCode basé sur le numero_contrat
    }

    
}
