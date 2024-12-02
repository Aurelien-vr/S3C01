package dao.entities;

import java.util.Date;

public class Etat_des_lieux {

    private int id_etat_des_lieux;
    private Date date_signature;
    private int nombre_cles;
    private String etat_des_elements;
    private int id_contrat_location;
    private boolean est_entrer;

    public int getId_etat_des_lieux() {
        return id_etat_des_lieux;
    }

    public void setId_etat_des_lieux(int id_etat_des_lieux) {
        this.id_etat_des_lieux = id_etat_des_lieux;
    }

    public Date getDate_signature() {
        return date_signature;
    }

    public void setDate_signature(Date date_signature) {
        this.date_signature = date_signature;
    }

    public int getNombre_cles() {
        return nombre_cles;
    }

    public void setNombre_cles(int nombre_cles) {
        this.nombre_cles = nombre_cles;
    }

    public String getEtat_des_elements() {
        return etat_des_elements;
    }

    public void setEtat_des_elements(String etat_des_elements) {
        this.etat_des_elements = etat_des_elements;
    }

    public int getId_contrat_location() {
        return id_contrat_location;
    }

    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    public boolean isEst_entrer() {
        return est_entrer;
    }

    public void setEst_entrer(boolean est_entrer) {
        this.est_entrer = est_entrer;
    }

    @Override
    public String toString() {
        return "Etat_des_lieux{" +
               "id_etat_des_lieux=" + id_etat_des_lieux +
               ", date_signature=" + (date_signature != null ? date_signature : "N/A") +
               ", nombre_cles=" + nombre_cles +
               ", etat_des_elements='" + (etat_des_elements != null ? etat_des_elements : "N/A") + '\'' +
               ", id_contrat_location=" + id_contrat_location +
               ", est_entrer=" + est_entrer +
               '}';
    }
}
