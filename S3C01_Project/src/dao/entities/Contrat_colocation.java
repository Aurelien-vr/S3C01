package dao.entities;

import java.math.BigDecimal;

public class Contrat_colocation {

    private int id_contrat_colocation;
    private boolean clause_solidarite;
    private BigDecimal part_des_charges;
    private int id_contrat_location;

    public int getId_contrat_colocation() {
        return id_contrat_colocation;
    }

    public void setId_contrat_colocation(int id_contrat_colocation) {
        this.id_contrat_colocation = id_contrat_colocation;
    }

    public boolean isClause_solidarite() {
        return clause_solidarite;
    }

    public void setClause_solidarite(boolean clause_solidarite) {
        this.clause_solidarite = clause_solidarite;
    }

    public BigDecimal getPart_des_charges() {
        return part_des_charges;
    }

    public void setPart_des_charges(BigDecimal part_des_charges) {
        this.part_des_charges = part_des_charges;
    }

    public int getId_contrat_location() {
        return id_contrat_location;
    }

    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    @Override
    public String toString() {
        return "Contrat_colocation{" +
               "id_contrat_colocation=" + id_contrat_colocation +
               ", clause_solidarite=" + clause_solidarite +
               ", part_des_charges=" + (part_des_charges != null ? part_des_charges : "N/A") +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }
}
