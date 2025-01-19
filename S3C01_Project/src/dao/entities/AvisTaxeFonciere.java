package dao.entities;

import java.sql.Date;
import java.util.Objects;

/**
 * Représente un avis de taxe foncière lié à un bien.
 * Cette classe contient les informations relatives à un avis de taxe foncière, 
 * notamment le numéro fiscal, la date d'établissement, le débiteur légal, 
 * le total de la cotisation et l'identifiant du bien concerné.
 */
public class AvisTaxeFonciere {

    private int numeroFiscal;  // Numéro fiscal de l'avis de taxe foncière
    private Date dateEtablissement;  // Date d'établissement de l'avis
    private String debiteurLegaux;  // Débiteur légal associé à l'avis
    private double totalCotisation;  // Montant total de la cotisation de la taxe foncière

    public AvisTaxeFonciere(Date dateEtablissement, String debiteurLegaux, double totalCotisation) {
		super();
		this.dateEtablissement = dateEtablissement;
		this.debiteurLegaux = debiteurLegaux;
		this.totalCotisation = totalCotisation;
	}

	public AvisTaxeFonciere() {}

	/**
     * Récupère le numéro fiscal de l'avis de taxe foncière.
     *
     * @return Le numéro fiscal.
     */
    public int getNumeroFiscal() {
        return numeroFiscal;
    }

    /**
     * Définit le numéro fiscal de l'avis de taxe foncière.
     *
     * @param numeroFiscal Le numéro fiscal à définir.
     */
    public void setNumeroFiscal(int numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    /**
     * Récupère la date d'établissement de l'avis de taxe foncière.
     *
     * @return La date d'établissement.
     */
    public Date getDateEtablissement() {
        return dateEtablissement;
    }

    /**
     * Définit la date d'établissement de l'avis de taxe foncière.
     *
     * @param dateEtablissement La date d'établissement à définir.
     */
    public void setDateEtablissement(Date dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
    }

    /**
     * Récupère le débiteur légal associé à l'avis de taxe foncière.
     *
     * @return Le débiteur légal.
     */
    public String getDebiteurLegaux() {
        return debiteurLegaux;
    }

    /**
     * Définit le débiteur légal associé à l'avis de taxe foncière.
     *
     * @param debiteurLegaux Le débiteur légal à définir.
     */
    public void setDebiteurLegaux(String debiteurLegaux) {
        this.debiteurLegaux = debiteurLegaux;
    }

    /**
     * Récupère le total de la cotisation de la taxe foncière.
     *
     * @return Le total de la cotisation.
     */
    public double getTotalCotisation() {
        return totalCotisation;
    }

    /**
     * Définit le total de la cotisation de la taxe foncière.
     *
     * @param totalCotisation Le total de la cotisation à définir.
     */
    public void setTotalCotisation(double totalCotisation) {
        this.totalCotisation = totalCotisation;
    }


    /**
     * Retourne une représentation textuelle de l'objet {@link AvisTaxeFonciere}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'avis de taxe foncière.
     */
    @Override
    public String toString() {
        return "Avis_Taxe_Fonciere{" +
               "numero_fiscal=" + numeroFiscal +
               ", date_etablissement=" + (dateEtablissement != null ? dateEtablissement : "N/A") +
               ", debiteur_legaux='" + (debiteurLegaux != null ? debiteurLegaux : "N/A") + '\'' +
               ", total_cotisation=" + totalCotisation +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateEtablissement, debiteurLegaux, numeroFiscal, totalCotisation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvisTaxeFonciere other = (AvisTaxeFonciere) obj;
		return Objects.equals(dateEtablissement, other.dateEtablissement)
				&& Objects.equals(debiteurLegaux, other.debiteurLegaux)
				&& Double.doubleToLongBits(totalCotisation) == Double.doubleToLongBits(other.totalCotisation);
	}
}
