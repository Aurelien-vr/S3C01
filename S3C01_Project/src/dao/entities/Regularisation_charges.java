package dao.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Regularisation_charges {

    private int id_charge_locataire;
    private Date date_effet;
    private BigDecimal charge_eau;
    private BigDecimal charge_ordure_menagere;
    private BigDecimal charge_eclairage;
    private BigDecimal provision_pour_charge;
    private BigDecimal indice;
    private String entretien;
    private int id_contrat_location;

    public int getId_charge_locataire() {
        return id_charge_locataire;
    }

    public void setId_charge_locataire(int id_charge_locataire) {
        this.id_charge_locataire = id_charge_locataire;
    }

    public Date getDate_effet() {
        return date_effet;
    }

    public void setDate_effet(Date date_effet) {
        this.date_effet = date_effet;
    }

    public BigDecimal getCharge_eau() {
        return charge_eau;
    }

    public void setCharge_eau(BigDecimal charge_eau) {
        this.charge_eau = charge_eau;
    }

    public BigDecimal getCharge_ordure_menagere() {
        return charge_ordure_menagere;
    }

    public void setCharge_ordure_menagere(BigDecimal charge_ordure_menagere) {
        this.charge_ordure_menagere = charge_ordure_menagere;
    }

    public BigDecimal getCharge_eclairage() {
        return charge_eclairage;
    }

    public void setCharge_eclairage(BigDecimal charge_eclairage) {
        this.charge_eclairage = charge_eclairage;
    }

    public BigDecimal getProvision_pour_charge() {
        return provision_pour_charge;
    }

    public void setProvision_pour_charge(BigDecimal provision_pour_charge) {
        this.provision_pour_charge = provision_pour_charge;
    }

    public BigDecimal getIndice() {
        return indice;
    }

    public void setIndice(BigDecimal indice) {
        this.indice = indice;
    }

    public String getEntretien() {
        return entretien;
    }

    public void setEntretien(String entretien) {
        this.entretien = entretien;
    }

    public int getId_contrat_location() {
        return id_contrat_location;
    }

    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

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
}
