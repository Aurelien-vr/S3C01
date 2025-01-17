package dao.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente un contrat de colocation associé à un contrat de location.
 * Cette classe contient les informations spécifiques à un contrat de colocation,
 * telles que l'existence d'une clause de solidarité, la part des charges à payer,
 * et l'identifiant du contrat de location associé.
 */
public class ContratColocation {

    private int idContratColocation;  // Identifiant unique du contrat de colocation
    private boolean clauseSolidarite;  // Indique si le contrat de colocation contient une clause de solidarité
    private BigDecimal partDesCharges;  // Part des charges à payer par le locataire
    private int idContratLocation;  // Identifiant du contrat de location associé

    
    public ContratColocation() {}
    
    public ContratColocation(boolean clauseSolidarite, BigDecimal partDesCharges) {
		super();
		this.clauseSolidarite = clauseSolidarite;
		this.partDesCharges = partDesCharges;
	}

	/**
     * Récupère l'identifiant du contrat de colocation.
     *
     * @return L'identifiant du contrat de colocation.
     */
    public int getIdContratColocation() {
        return idContratColocation;
    }

    /**
     * Définit l'identifiant du contrat de colocation.
     *
     * @param idContratColocation L'identifiant du contrat de colocation à définir.
     */
    public void setIdContratColocation(int idContratColocation) {
        this.idContratColocation = idContratColocation;
    }

    /**
     * Récupère si le contrat de colocation contient une clause de solidarité.
     *
     * @return true si la clause de solidarité est présente, false sinon.
     */
    public boolean isClauseSolidarite() {
        return clauseSolidarite;
    }

    /**
     * Définit si le contrat de colocation contient une clause de solidarité.
     *
     * @param clauseSolidarite true si la clause de solidarité doit être présente, false sinon.
     */
    public void setClauseSolidarite(boolean clauseSolidarite) {
        this.clauseSolidarite = clauseSolidarite;
    }

    /**
     * Récupère la part des charges à payer par le locataire.
     *
     * @return La part des charges sous forme de BigDecimal.
     */
    public BigDecimal getPartDesCharges() {
        return partDesCharges;
    }

    /**
     * Définit la part des charges à payer par le locataire.
     *
     * @param partDesCharges La part des charges sous forme de BigDecimal à définir.
     */
    public void setPartDesCharges(BigDecimal partDesCharges) {
        this.partDesCharges = partDesCharges;
    }

    /**
     * Récupère l'identifiant du contrat de location associé au contrat de colocation.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getIdContratLocation() {
        return idContratLocation;
    }

    /**
     * Définit l'identifiant du contrat de location associé au contrat de colocation.
     *
     * @param idContratLocation L'identifiant du contrat de location à définir.
     */
    public void setIdContratLocation(int idContratLocation) {
        this.idContratLocation = idContratLocation;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link ContratColocation}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant le contrat de colocation.
     */
    @Override
    public String toString() {
        return "Contrat_colocation{" +
               "id_contrat_colocation=" + idContratColocation +
               ", clause_solidarite=" + clauseSolidarite +
               ", part_des_charges=" + (partDesCharges != null ? partDesCharges : "N/A") +
               ", id_contrat_location=" + idContratLocation +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(clauseSolidarite, idContratColocation, idContratLocation, partDesCharges);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratColocation other = (ContratColocation) obj;
		return clauseSolidarite == other.clauseSolidarite && idContratColocation == other.idContratColocation
				&& idContratLocation == other.idContratLocation
				&& Objects.equals(partDesCharges, other.partDesCharges);
	}
}
