package dao.entities;

import java.math.BigDecimal;

/**
 * Représente un contrat de colocation associé à un contrat de location.
 * Cette classe contient les informations spécifiques à un contrat de colocation,
 * telles que l'existence d'une clause de solidarité, la part des charges à payer,
 * et l'identifiant du contrat de location associé.
 */
public class Contrat_colocation {

    private int id_contrat_colocation;  // Identifiant unique du contrat de colocation
    private boolean clause_solidarite;  // Indique si le contrat de colocation contient une clause de solidarité
    private BigDecimal part_des_charges;  // Part des charges à payer par le locataire
    private int id_contrat_location;  // Identifiant du contrat de location associé

    /**
     * Récupère l'identifiant du contrat de colocation.
     *
     * @return L'identifiant du contrat de colocation.
     */
    public int getId_contrat_colocation() {
        return id_contrat_colocation;
    }

    /**
     * Définit l'identifiant du contrat de colocation.
     *
     * @param id_contrat_colocation L'identifiant du contrat de colocation à définir.
     */
    public void setId_contrat_colocation(int id_contrat_colocation) {
        this.id_contrat_colocation = id_contrat_colocation;
    }

    /**
     * Récupère si le contrat de colocation contient une clause de solidarité.
     *
     * @return true si la clause de solidarité est présente, false sinon.
     */
    public boolean isClause_solidarite() {
        return clause_solidarite;
    }

    /**
     * Définit si le contrat de colocation contient une clause de solidarité.
     *
     * @param clause_solidarite true si la clause de solidarité doit être présente, false sinon.
     */
    public void setClause_solidarite(boolean clause_solidarite) {
        this.clause_solidarite = clause_solidarite;
    }

    /**
     * Récupère la part des charges à payer par le locataire.
     *
     * @return La part des charges sous forme de BigDecimal.
     */
    public BigDecimal getPart_des_charges() {
        return part_des_charges;
    }

    /**
     * Définit la part des charges à payer par le locataire.
     *
     * @param part_des_charges La part des charges sous forme de BigDecimal à définir.
     */
    public void setPart_des_charges(BigDecimal part_des_charges) {
        this.part_des_charges = part_des_charges;
    }

    /**
     * Récupère l'identifiant du contrat de location associé au contrat de colocation.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location associé au contrat de colocation.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Contrat_colocation}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant le contrat de colocation.
     */
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
