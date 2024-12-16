package dao.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Représente une régularisation des charges d'un locataire.
 * Cette classe contient des informations sur les charges liées à l'eau, les ordures ménagères, l'éclairage,
 * la provision pour charges, l'indice d'ajustement, l'entretien, ainsi que l'identifiant du contrat de location associé.
 */
public class Regularisation_charges {

    private int id_charge_locataire;  // Identifiant unique de la régularisation des charges du locataire
    private Date date_effet;  // Date d'effet de la régularisation des charges
    private BigDecimal charge_eau;  // Montant de la charge d'eau
    private BigDecimal charge_ordure_menagere;  // Montant de la charge des ordures ménagères
    private BigDecimal charge_eclairage;  // Montant de la charge d'éclairage
    private BigDecimal provision_pour_charge;  // Montant de la provision pour charges
    private BigDecimal indice;  // Indice d'ajustement des charges
    private String entretien;  // Informations sur l'entretien
    private int id_contrat_location;  // Identifiant du contrat de location associé

    
    
    public Regularisation_charges() {}

	public Regularisation_charges(Date date_effet, BigDecimal charge_eau, BigDecimal charge_ordure_menagere,
			BigDecimal charge_eclairage, BigDecimal provision_pour_charge, BigDecimal indice, String entretien) {
		super();
		this.date_effet = date_effet;
		this.charge_eau = charge_eau;
		this.charge_ordure_menagere = charge_ordure_menagere;
		this.charge_eclairage = charge_eclairage;
		this.provision_pour_charge = provision_pour_charge;
		this.indice = indice;
		this.entretien = entretien;
	}

	/**
     * Récupère l'identifiant unique de la régularisation des charges du locataire.
     *
     * @return L'identifiant de la régularisation des charges.
     */
    public int getId_charge_locataire() {
        return id_charge_locataire;
    }

    /**
     * Définit l'identifiant de la régularisation des charges du locataire.
     *
     * @param id_charge_locataire L'identifiant de la régularisation des charges à définir.
     */
    public void setId_charge_locataire(int id_charge_locataire) {
        this.id_charge_locataire = id_charge_locataire;
    }

    /**
     * Récupère la date d'effet de la régularisation des charges.
     *
     * @return La date d'effet de la régularisation des charges.
     */
    public Date getDate_effet() {
        return date_effet;
    }

    /**
     * Définit la date d'effet de la régularisation des charges.
     *
     * @param date_effet La date d'effet à définir.
     */
    public void setDate_effet(Date date_effet) {
        this.date_effet = date_effet;
    }

    /**
     * Récupère le montant de la charge d'eau.
     *
     * @return Le montant de la charge d'eau.
     */
    public BigDecimal getCharge_eau() {
        return charge_eau;
    }

    /**
     * Définit le montant de la charge d'eau.
     *
     * @param charge_eau Le montant de la charge d'eau à définir.
     */
    public void setCharge_eau(BigDecimal charge_eau) {
        this.charge_eau = charge_eau;
    }

    /**
     * Récupère le montant de la charge des ordures ménagères.
     *
     * @return Le montant de la charge des ordures ménagères.
     */
    public BigDecimal getCharge_ordure_menagere() {
        return charge_ordure_menagere;
    }

    /**
     * Définit le montant de la charge des ordures ménagères.
     *
     * @param charge_ordure_menagere Le montant de la charge des ordures ménagères à définir.
     */
    public void setCharge_ordure_menagere(BigDecimal charge_ordure_menagere) {
        this.charge_ordure_menagere = charge_ordure_menagere;
    }

    /**
     * Récupère le montant de la charge d'éclairage.
     *
     * @return Le montant de la charge d'éclairage.
     */
    public BigDecimal getCharge_eclairage() {
        return charge_eclairage;
    }

    /**
     * Définit le montant de la charge d'éclairage.
     *
     * @param charge_eclairage Le montant de la charge d'éclairage à définir.
     */
    public void setCharge_eclairage(BigDecimal charge_eclairage) {
        this.charge_eclairage = charge_eclairage;
    }

    /**
     * Récupère le montant de la provision pour charges.
     *
     * @return Le montant de la provision pour charges.
     */
    public BigDecimal getProvision_pour_charge() {
        return provision_pour_charge;
    }

    /**
     * Définit le montant de la provision pour charges.
     *
     * @param provision_pour_charge Le montant de la provision pour charges à définir.
     */
    public void setProvision_pour_charge(BigDecimal provision_pour_charge) {
        this.provision_pour_charge = provision_pour_charge;
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
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location associé à cette régularisation des charges.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Regularisation_charges}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Regularisation_charges{" +
               "id_charge_locataire=" + id_charge_locataire +
               ", date_effet=" + (date_effet != null ? date_effet : "N/A") +
               ", charge_eau=" + (charge_eau != null ? charge_eau : "N/A") +
               ", charge_ordure_menagere=" + (charge_ordure_menagere != null ? charge_ordure_menagere : "N/A") +
               ", charge_eclairage=" + (charge_eclairage != null ? charge_eclairage : "N/A") +
               ", provision_pour_charge=" + (provision_pour_charge != null ? provision_pour_charge : "N/A") +
               ", indice=" + (indice != null ? indice : "N/A") +
               ", entretien='" + (entretien != null ? entretien : "N/A") + '\'' +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(charge_eau, charge_eclairage, charge_ordure_menagere, date_effet, entretien,
				id_charge_locataire, id_contrat_location, indice, provision_pour_charge);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regularisation_charges other = (Regularisation_charges) obj;
		return Objects.equals(charge_eau, other.charge_eau) && Objects.equals(charge_eclairage, other.charge_eclairage)
				&& Objects.equals(charge_ordure_menagere, other.charge_ordure_menagere)
				&& Objects.equals(date_effet, other.date_effet) && Objects.equals(entretien, other.entretien)
				&& id_charge_locataire == other.id_charge_locataire && id_contrat_location == other.id_contrat_location
				&& Objects.equals(indice, other.indice)
				&& Objects.equals(provision_pour_charge, other.provision_pour_charge);
	}
}
