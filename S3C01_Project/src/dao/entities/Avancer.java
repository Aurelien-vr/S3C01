package dao.entities;

public class Avancer {

    private int id_locataire;
    private int numero_facture;

    public int getId_locataire() {
        return id_locataire;
    }

    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    public int getNumero_facture() {
        return numero_facture;
    }

    public void setNumero_facture(int numero_facture) {
        this.numero_facture = numero_facture;
    }

    @Override
    public String toString() {
        return "Avancer{" +
               "id_locataire=" + id_locataire +
               ", numero_facture=" + numero_facture +
               '}';
    }
}
