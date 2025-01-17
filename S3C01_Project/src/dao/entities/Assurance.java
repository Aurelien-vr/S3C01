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

    private int numeroContrat;  // Numéro de contrat d'assurance
    private Date dateAssurance;
    private BigDecimal prime;  // Montant de la prime d'assurance
    private BigDecimal protectionJuridique;  // Montant de la protection juridique incluse
    private int idBien;  // Identifiant du bien assuré

    public Assurance() {}
    
    public Assurance(Date dateAssurance, BigDecimal prime, BigDecimal protectionJuridique) {
		super();
		this.dateAssurance = dateAssurance;
		this.prime = prime;
		this.protectionJuridique = protectionJuridique;
	}

	/**
     * Récupère le numéro du contrat d'assurance.
     *
     * @return Le numéro de contrat d'assurance.
     */
    public int getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Définit le numéro du contrat d'assurance.
     *
     * @param numeroContrat Le numéro de contrat d'assurance à définir.
     */
    public void setNumeroContrat(int numeroContrat) {
        this.numeroContrat = numeroContrat;
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
     * Récupère le montant de la protection juridique.
     *
     * @return Le montant de la protection juridique.
     */
    public BigDecimal getProtectionJuridique() {
        return protectionJuridique;
    }

    /**
     * Définit le montant de la protection juridique.
     *
     * @param protectionJuridique Le montant de la protection juridique à définir.
     */
    public void setProtectionJuridique(BigDecimal protectionJuridique) {
        this.protectionJuridique = protectionJuridique;
    }

    /**
     * Récupère l'identifiant du bien assuré par le contrat.
     *
     * @return L'identifiant du bien assuré.
     */
    public int getIdBien() {
        return idBien;
    }

    /**
     * Définit l'identifiant du bien assuré par le contrat.
     *
     * @param idBien L'identifiant du bien à définir.
     */
    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }
    
    
	public Date getDateAssurance() {
		return dateAssurance;
	}

	public void setDateAssurance(Date dateAssurance) {
		this.dateAssurance = dateAssurance;
	}
	
	
	
	@Override
	public String toString() {
		return "Assurance [numero_contrat=" + numeroContrat + ", date_assurance=" + dateAssurance + ", prime=" + prime
				+ ", protection_juridique=" + protectionJuridique + ", id_bien=" + idBien + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateAssurance, idBien, numeroContrat, prime, protectionJuridique);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assurance other = (Assurance) obj;
		return Objects.equals(dateAssurance, other.dateAssurance) && idBien == other.idBien
				&& Objects.equals(prime, other.prime)
				&& Objects.equals(protectionJuridique, other.protectionJuridique);
	}




    
}
