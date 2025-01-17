package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente une régularisation des charges d'un locataire.
 * Cette classe contient des informations sur les charges liées à l'eau, les ordures ménagères, l'éclairage,
 * la provision pour charges, l'indice d'ajustement, l'entretien, ainsi que l'identifiant du contrat de location associé.
 */
public class RegularisationCharges {

    private int idChargeLocataire;  // Identifiant unique de la régularisation des charges du locataire
    private Date dateEffet;  // Date d'effet de la régularisation des charges
    private BigDecimal chargeEau;  // Montant de la charge d'eau
    private BigDecimal chargeOrdureMenagere;  // Montant de la charge des ordures ménagères
    private BigDecimal chargeEclairage;  // Montant de la charge d'éclairage
    private BigDecimal provisionPourCharge;  // Montant de la provision pour charges
    private BigDecimal indice;  // Indice d'ajustement des charges
    private String entretien;  // Informations sur l'entretien
    private int idContratLocation;  // Identifiant du contrat de location associé

    
    
    public RegularisationCharges() {}

	public RegularisationCharges(Date dateEffet, BigDecimal chargeEau, BigDecimal chargeOrdureMenagere,
			BigDecimal chargeEclairage, BigDecimal provisionPourCharge, BigDecimal indice, String entretien) {
		super();
		this.dateEffet = dateEffet;
		this.chargeEau = chargeEau;
		this.chargeOrdureMenagere = chargeOrdureMenagere;
		this.chargeEclairage = chargeEclairage;
		this.provisionPourCharge = provisionPourCharge;
		this.indice = indice;
		this.entretien = entretien;
	}

	/**
     * Récupère l'identifiant unique de la régularisation des charges du locataire.
     *
     * @return L'identifiant de la régularisation des charges.
     */
    public int getIdChargeLocataire() {
        return idChargeLocataire;
    }

    /**
     * Définit l'identifiant de la régularisation des charges du locataire.
     *
     * @param idChargeLocataire L'identifiant de la régularisation des charges à définir.
     */
    public void setIdChargeLocataire(int idChargeLocataire) {
        this.idChargeLocataire = idChargeLocataire;
    }

    /**
     * Récupère la date d'effet de la régularisation des charges.
     *
     * @return La date d'effet de la régularisation des charges.
     */
    public Date getDateEffet() {
        return dateEffet;
    }

    /**
     * Définit la date d'effet de la régularisation des charges.
     *
     * @param dateEffet La date d'effet à définir.
     */
    public void setDateEffet(Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Récupère le montant de la charge d'eau.
     *
     * @return Le montant de la charge d'eau.
     */
    public BigDecimal getChargeEau() {
        return chargeEau;
    }

    /**
     * Définit le montant de la charge d'eau.
     *
     * @param chargeEau Le montant de la charge d'eau à définir.
     */
    public void setChargeEau(BigDecimal chargeEau) {
        this.chargeEau = chargeEau;
    }

    /**
     * Récupère le montant de la charge des ordures ménagères.
     *
     * @return Le montant de la charge des ordures ménagères.
     */
    public BigDecimal getChargeOrdureMenagere() {
        return chargeOrdureMenagere;
    }

    /**
     * Définit le montant de la charge des ordures ménagères.
     *
     * @param chargeOrdureMenagere Le montant de la charge des ordures ménagères à définir.
     */
    public void setChargeOrdureMenagere(BigDecimal chargeOrdureMenagere) {
        this.chargeOrdureMenagere = chargeOrdureMenagere;
    }

    /**
     * Récupère le montant de la charge d'éclairage.
     *
     * @return Le montant de la charge d'éclairage.
     */
    public BigDecimal getChargeEclairage() {
        return chargeEclairage;
    }

    /**
     * Définit le montant de la charge d'éclairage.
     *
     * @param chargeEclairage Le montant de la charge d'éclairage à définir.
     */
    public void setChargeEclairage(BigDecimal chargeEclairage) {
        this.chargeEclairage = chargeEclairage;
    }

    /**
     * Récupère le montant de la provision pour charges.
     *
     * @return Le montant de la provision pour charges.
     */
    public BigDecimal getProvisionPourCharge() {
        return provisionPourCharge;
    }

    /**
     * Définit le montant de la provision pour charges.
     *
     * @param provisionPourCharge Le montant de la provision pour charges à définir.
     */
    public void setProvisionPourCharge(BigDecimal provisionPourCharge) {
        this.provisionPourCharge = provisionPourCharge;
    }

    /**
     * Récupère l'indice d'ajustement des charges.
     *
     * @return L'indice d'ajustement des charges.
     */
    public BigDecimal getIndice() {
        return indice;
    }

    /**
     * Définit l'indice d'ajustement des charges.
     *
     * @param indice L'indice d'ajustement à définir.
     */
    public void setIndice(BigDecimal indice) {
        this.indice = indice;
    }

    /**
     * Récupère les informations sur l'entretien.
     *
     * @return Les informations sur l'entretien.
     */
    public String getEntretien() {
        return entretien;
    }

    /**
     * Définit les informations sur l'entretien.
     *
     * @param entretien Les informations sur l'entretien à définir.
     */
    public void setEntretien(String entretien) {
        this.entretien = entretien;
    }

    /**
     * Récupère l'identifiant du contrat de location associé à cette régularisation des charges.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getIdContratLocation() {
        return idContratLocation;
    }

    /**
     * Définit l'identifiant du contrat de location associé à cette régularisation des charges.
     *
     * @param idContratLocation L'identifiant du contrat de location à définir.
     */
    public void setIdContratLocation(int idContratLocation) {
        this.idContratLocation = idContratLocation;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link RegularisationCharges}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Regularisation_charges{" +
               "id_charge_locataire=" + idChargeLocataire +
               ", date_effet=" + (dateEffet != null ? dateEffet : "N/A") +
               ", charge_eau=" + (chargeEau != null ? chargeEau : "N/A") +
               ", charge_ordure_menagere=" + (chargeOrdureMenagere != null ? chargeOrdureMenagere : "N/A") +
               ", charge_eclairage=" + (chargeEclairage != null ? chargeEclairage : "N/A") +
               ", provision_pour_charge=" + (provisionPourCharge != null ? provisionPourCharge : "N/A") +
               ", indice=" + (indice != null ? indice : "N/A") +
               ", entretien='" + (entretien != null ? entretien : "N/A") + '\'' +
               ", id_contrat_location=" + idContratLocation +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(chargeEau, chargeEclairage, chargeOrdureMenagere, dateEffet, entretien,
				idChargeLocataire, idContratLocation, indice, provisionPourCharge);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegularisationCharges other = (RegularisationCharges) obj;
		return Objects.equals(chargeEau, other.chargeEau) && Objects.equals(chargeEclairage, other.chargeEclairage)
				&& Objects.equals(chargeOrdureMenagere, other.chargeOrdureMenagere)
				&& Objects.equals(dateEffet, other.dateEffet) && Objects.equals(entretien, other.entretien)
				&& idContratLocation == other.idContratLocation
				&& Objects.equals(indice, other.indice)
				&& Objects.equals(provisionPourCharge, other.provisionPourCharge);
	}
}
