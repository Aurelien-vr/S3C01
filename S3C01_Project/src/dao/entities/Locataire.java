package dao.entities;

import java.util.Date;

public class Locataire {

    private int id_locataire;
    private String nom;
    private String prenom;
    private Date date_de_naissance;
    private String iban;
    private int id_contrat_location;

    public int getId_locataire() {
        return id_locataire;
    }

    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getId_contrat_location() {
        return id_contrat_location;
    }

    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    @Override
    public String toString() {
        return "Locataire{" +
               "id_locataire=" + id_locataire +
               ", nom='" + (nom != null ? nom : "N/A") + '\'' +
               ", prenom='" + (prenom != null ? prenom : "N/A") + '\'' +
               ", date_de_naissance=" + (date_de_naissance != null ? date_de_naissance : "N/A") +
               ", iban='" + (iban != null ? iban : "N/A") + '\'' +
               ", id_contrat_location=" + id_contrat_location +
               '}';
    }
}
