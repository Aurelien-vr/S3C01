package dao.entities;

import java.math.BigDecimal;

public class Assurance {

    private int numero_contrat;
    private BigDecimal prime;
    private BigDecimal taux_augmentation;
    private BigDecimal protection_juridique;
    private int id_bien;

    public int getNumero_contrat() {
        return numero_contrat;
    }

    public void setNumero_contrat(int numero_contrat) {
        this.numero_contrat = numero_contrat;
    }

    public BigDecimal getPrime() {
        return prime;
    }

    public void setPrime(BigDecimal prime) {
        this.prime = prime;
    }

    public BigDecimal getTaux_augmentation() {
        return taux_augmentation;
    }

    public void setTaux_augmentation(BigDecimal taux_augmentation) {
        this.taux_augmentation = taux_augmentation;
    }

    public BigDecimal getProtection_juridique() {
        return protection_juridique;
    }

    public void setProtection_juridique(BigDecimal protection_juridique) {
        this.protection_juridique = protection_juridique;
    }

    public int getId_bien() {
        return id_bien;
    }

    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    @Override
    public String toString() {
        return "Assurance{" +
               "numero_contrat=" + numero_contrat +
               ", prime=" + (prime != null ? prime : "N/A") +
               ", taux_augmentation=" + (taux_augmentation != null ? taux_augmentation : "N/A") +
               ", protection_juridique=" + (protection_juridique != null ? protection_juridique : "N/A") +
               ", id_bien=" + id_bien +
               '}';
    }
}
