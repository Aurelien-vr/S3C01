package dao.entities;
import java.math.*;

/**
 * Représente un acte de cautionnement dans le système.
 * Cette classe contient les informations relatives à un acte de cautionnement, 
 * notamment l'identifiant de l'acte, le montant de la caution et l'identifiant du locataire.
 */
public class Acte_cautionnement {

    private int id_acte_cautionnement;  // Identifiant unique de l'acte de cautionnement
    private BigDecimal montant_caution;  // Montant de la caution
    private int id_locataire;  // Identifiant du locataire concerné par l'acte de cautionnement

    /**
     * Récupère l'identifiant de l'acte de cautionnement.
     *
     * @return L'identifiant de l'acte de cautionnement.
     */
    public int getId_acte_cautionnement() {
        return id_acte_cautionnement;
    }

    /**
     * Définit l'identifiant de l'acte de cautionnement.
     *
     * @param id_acte_cautionnement L'identifiant de l'acte de cautionnement à définir.
     */
    public void setId_acte_cautionnement(int id_acte_cautionnement) {
        this.id_acte_cautionnement = id_acte_cautionnement;
    }

    /**
     * Récupère le montant de la caution.
     *
     * @return Le montant de la caution.
     */
    public BigDecimal getMontant_caution() {
        return montant_caution;
    }

    /**
     * Définit le montant de la caution.
     *
     * @param montant_caution Le montant de la caution à définir.
     */
    public void setMontant_caution(BigDecimal montant_caution) {
        this.montant_caution = montant_caution;
    }

    /**
     * Récupère l'identifiant du locataire associé à l'acte de cautionnement.
     *
     * @return L'identifiant du locataire.
     */
    public int getId_locataire() {
        return id_locataire;
    }

    /**
     * Définit l'identifiant du locataire associé à l'acte de cautionnement.
     *
     * @param id_locataire L'identifiant du locataire à définir.
     */
    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    /**
     * Retourne une représentation textuelle de l'acte de cautionnement.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'acte de cautionnement.
     */
    @Override
    public String toString() {
        return "Acte_Cautionnement{" +
               "id_acte_cautionnement=" + id_acte_cautionnement +
               ", montant_caution=" + (montant_caution != null ? montant_caution : "N/A") +
               ", id_locataire=" + id_locataire +
               '}';
    }
}
