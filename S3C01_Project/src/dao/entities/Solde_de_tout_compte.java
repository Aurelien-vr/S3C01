package dao.entities;

import java.math.BigDecimal;

public class Solde_de_tout_compte {

    private int id_solde_de_tout_compte;
    private BigDecimal reste_a_devoir;
    private BigDecimal provision_pour_charges;
    private BigDecimal caution;
    private int id_contrat_location;

    public int getId_solde_de_tout_compte() {
        return id_solde_de_tout_compte;
    }

    public void setId_solde_de_tout_compte(int id_solde_de_tout_compte) {
        this.id_solde_de_tout_compte = id_solde_de_tout_compte;
    }

    public BigDecimal getReste_a_devoir() {
        return reste_a_devoir;
    }

    public void setReste_a_devoir(BigDecimal reste_a_devoir) {
        this.reste_a_devoir = reste_a_devoir;
    }

    public BigDecimal getProvision_pour_charges() {
        return provision_pour_charges;
    }

    public void setProvision_pour_charges(BigDecimal provision_pour_charges) {
        this.provision_pour_charges = provision_pour_charges;
    }

    public BigDecimal getCaution() {
        return caution;
    }

    public void setCaution(BigDecimal caution) {
        this.caution = caution;
    }

    public int getId_contrat_location() {
        return id_contrat_location;
    }

    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    @Override
    public String toString() {
        return "Solde_de_tout_compte{" +
               "id_solde_de_tout_compte=" + id_solde_de_tout_compte +
               ", reste_a_devoir=" + (reste_a_devoir != null ? reste_a_devoir : "N/A") +
               ", provision_pour_charges=" + (provision_pour_charges != null ? provision_pour_charges : "N/A") +
               ", caution=" + (caution != null ? caution : "N/A") +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }
}
