package dao.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente le solde de tout compte d'un locataire à la fin de son contrat de location.
 * Cette classe contient des informations sur le reste à devoir, la provision pour charges,
 * la caution et l'identifiant du contrat de location associé.
 */
public class SoldeDeToutCompte {

    private int idSoldeDeToutCompte;  // Identifiant unique du solde de tout compte
    private BigDecimal resteADevoir;  // Montant restant à devoir par le locataire
    private BigDecimal provisionPourCharges;  // Montant de la provision pour charges
    private BigDecimal caution;  // Montant de la caution à restituer ou à déduire
    private int idContratLocation;  // Identifiant du contrat de location associé au solde de tout compte

    
    
    public SoldeDeToutCompte() {}

	public SoldeDeToutCompte(BigDecimal resteADevoir, BigDecimal provisionPourCharges, BigDecimal caution) {
		super();
		this.resteADevoir = resteADevoir;
		this.provisionPourCharges = provisionPourCharges;
		this.caution = caution;
	}

	/**
     * Récupère l'identifiant unique du solde de tout compte.
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
     * Récupère le montant restant à devoir par le locataire.
     *
     * @return Le montant restant à devoir.
     */
    public BigDecimal getResteADevoir() {
        return resteADevoir;
    }

    /**
     * Définit le montant restant à devoir par le locataire.
     *
     * @param resteADevoir Le montant restant à devoir à définir.
     */
    public void setResteADevoir(BigDecimal resteADevoir) {
        this.resteADevoir = resteADevoir;
    }

    /**
     * Récupère le montant de la provision pour charges.
     *
     * @return Le montant de la provision pour charges.
     */
    public BigDecimal getProvisionPourCharges() {
        return provisionPourCharges;
    }

    /**
     * Définit le montant de la provision pour charges.
     *
     * @param provisionPourCharges Le montant de la provision pour charges à définir.
     */
    public void setProvisionPourCharges(BigDecimal provisionPourCharges) {
        this.provisionPourCharges = provisionPourCharges;
    }

    /**
     * Récupère le montant de la caution.
     *
     * @return Le montant de la caution.
     */
    public BigDecimal getCaution() {
        return caution;
    }

    /**
     * Définit le montant de la caution.
     *
     * @param caution Le montant de la caution à définir.
     */
    public void setCaution(BigDecimal caution) {
        this.caution = caution;
    }

    /**
     * Récupère l'identifiant du contrat de location associé à ce solde de tout compte.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getIdContratLocation() {
        return idContratLocation;
    }

    /**
     * Définit l'identifiant du contrat de location associé à ce solde de tout compte.
     *
     * @param idContratLocation L'identifiant du contrat de location à définir.
     */
    public void setIdContratLocation(int idContratLocation) {
        this.idContratLocation = idContratLocation;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link SoldeDeToutCompte}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Solde_de_tout_compte{" +
               "id_solde_de_tout_compte=" + idSoldeDeToutCompte +
               ", reste_a_devoir=" + (resteADevoir != null ? resteADevoir : "N/A") +
               ", provision_pour_charges=" + (provisionPourCharges != null ? provisionPourCharges : "N/A") +
               ", caution=" + (caution != null ? caution : "N/A") +
               ", id_contrat_location=" + idContratLocation +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(caution, idContratLocation, idSoldeDeToutCompte, provisionPourCharges,
				resteADevoir);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoldeDeToutCompte other = (SoldeDeToutCompte) obj;
		return Objects.equals(caution, other.caution) && idContratLocation == other.idContratLocation
				&& Objects.equals(provisionPourCharges, other.provisionPourCharges)
				&& Objects.equals(resteADevoir, other.resteADevoir);
	}
}
