package dao.entities;

import java.util.Date;

public class Avis_Taxe_Fonciere {

    private int numero_fiscal;
    private Date date_etablissement;
    private String debiteur_legaux;
    private double total_cotisation;
    private int id_bien;

    public int getNumero_fiscal() {
        return numero_fiscal;
    }

    public void setNumero_fiscal(int numero_fiscal) {
        this.numero_fiscal = numero_fiscal;
    }

    public Date getDate_etablissement() {
        return date_etablissement;
    }

    public void setDate_etablissement(Date date_etablissement) {
        this.date_etablissement = date_etablissement;
    }

    public String getDebiteur_legaux() {
        return debiteur_legaux;
    }

    public void setDebiteur_legaux(String debiteur_legaux) {
        this.debiteur_legaux = debiteur_legaux;
    }

    public double getTotal_cotisation() {
        return total_cotisation;
    }

    public void setTotal_cotisation(double total_cotisation) {
        this.total_cotisation = total_cotisation;
    }

    public int getId_bien() {
        return id_bien;
    }

    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

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
}
