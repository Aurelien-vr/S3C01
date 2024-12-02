package dao.entities;

public class Enumerer {

    private String reference_facture;
    private int id_solde_de_tout_compte;

    public String getReference_facture() {
        return reference_facture;
    }

    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    public int getId_solde_de_tout_compte() {
        return id_solde_de_tout_compte;
    }

    public void setId_solde_de_tout_compte(int id_solde_de_tout_compte) {
        this.id_solde_de_tout_compte = id_solde_de_tout_compte;
    }

    @Override
    public String toString() {
        return "Enumerer{" +
               "reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               ", id_solde_de_tout_compte=" + id_solde_de_tout_compte +
               '}';
    }
}
