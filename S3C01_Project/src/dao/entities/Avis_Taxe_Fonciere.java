package dao.entities;

import java.util.Date;
import java.util.Objects;

/**
 * Représente un avis de taxe foncière lié à un bien.
 * Cette classe contient les informations relatives à un avis de taxe foncière, 
 * notamment le numéro fiscal, la date d'établissement, le débiteur légal, 
 * le total de la cotisation et l'identifiant du bien concerné.
 */
public class Avis_Taxe_Fonciere {

    private int numero_fiscal;  // Numéro fiscal de l'avis de taxe foncière
    private Date date_etablissement;  // Date d'établissement de l'avis
    private String debiteur_legaux;  // Débiteur légal associé à l'avis
    private double total_cotisation;  // Montant total de la cotisation de la taxe foncière
    private int id_bien;  // Identifiant du bien concerné par l'avis de taxe foncière

    /**
     * Récupère le numéro fiscal de l'avis de taxe foncière.
     *
     * @return Le numéro fiscal.
     */
    public int getNumero_fiscal() {
        return numero_fiscal;
    }

    /**
     * Définit le numéro fiscal de l'avis de taxe foncière.
     *
     * @param numero_fiscal Le numéro fiscal à définir.
     */
    public void setNumero_fiscal(int numero_fiscal) {
        this.numero_fiscal = numero_fiscal;
    }

    /**
     * Récupère la date d'établissement de l'avis de taxe foncière.
     *
     * @return La date d'établissement.
     */
    public Date getDate_etablissement() {
        return date_etablissement;
    }

    /**
     * Définit la date d'établissement de l'avis de taxe foncière.
     *
     * @param date_etablissement La date d'établissement à définir.
     */
    public void setDate_etablissement(Date date_etablissement) {
        this.date_etablissement = date_etablissement;
    }

    /**
     * Récupère le débiteur légal associé à l'avis de taxe foncière.
     *
     * @return Le débiteur légal.
     */
    public String getDebiteur_legaux() {
        return debiteur_legaux;
    }

    /**
     * Définit le débiteur légal associé à l'avis de taxe foncière.
     *
     * @param debiteur_legaux Le débiteur légal à définir.
     */
    public void setDebiteur_legaux(String debiteur_legaux) {
        this.debiteur_legaux = debiteur_legaux;
    }

    /**
     * Récupère le total de la cotisation de la taxe foncière.
     *
     * @return Le total de la cotisation.
     */
    public double getTotal_cotisation() {
        return total_cotisation;
    }

    /**
     * Définit le total de la cotisation de la taxe foncière.
     *
     * @param total_cotisation Le total de la cotisation à définir.
     */
    public void setTotal_cotisation(double total_cotisation) {
        this.total_cotisation = total_cotisation;
    }

    /**
     * Récupère l'identifiant du bien concerné par l'avis de taxe foncière.
     *
     * @return L'identifiant du bien.
     */
    public int getId_bien() {
        return id_bien;
    }

    /**
     * Définit l'identifiant du bien concerné par l'avis de taxe foncière.
     *
     * @param id_bien L'identifiant du bien à définir.
     */
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Avis_Taxe_Fonciere}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'avis de taxe foncière.
     */
    @Override
    public String toString() {
        return "Avis_Taxe_Fonciere{" +
               "numero_fiscal=" + numero_fiscal +
               ", date_etablissement=" + (date_etablissement != null ? date_etablissement : "N/A") +
               ", debiteur_legaux='" + (debiteur_legaux != null ? debiteur_legaux : "N/A") + '\'' +
               ", total_cotisation=" + total_cotisation +
               ", id_bien=" + id_bien +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_etablissement, debiteur_legaux, id_bien, numero_fiscal, total_cotisation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avis_Taxe_Fonciere other = (Avis_Taxe_Fonciere) obj;
		return Objects.equals(date_etablissement, other.date_etablissement)
				&& Objects.equals(debiteur_legaux, other.debiteur_legaux) && id_bien == other.id_bien
				&& numero_fiscal == other.numero_fiscal
				&& Double.doubleToLongBits(total_cotisation) == Double.doubleToLongBits(other.total_cotisation);
	}
}
