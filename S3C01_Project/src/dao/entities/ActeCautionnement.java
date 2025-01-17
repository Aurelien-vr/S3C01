package dao.entities;
import java.math.*;
import java.util.Objects;

/**
 * Représente un acte de cautionnement dans le système.
 * Cette classe contient les informations relatives à un acte de cautionnement, 
 * notamment l'identifiant de l'acte, le montant de la caution et l'identifiant du locataire.
 */
public class ActeCautionnement {

    private int idActeCautionnement;  // Identifiant unique de l'acte de cautionnement
    private BigDecimal montantCaution;  // Montant de la caution
    private int idLocataire;  // Identifiant du locataire concerné par l'acte de cautionnement

    
    public ActeCautionnement() {}
    
    public ActeCautionnement(BigDecimal montantCaution) {
    	super();
		this.montantCaution = montantCaution;
    }
    
    
    
    /**
     * Récupère l'identifiant de l'acte de cautionnement.
     *
     * @return L'identifiant de l'acte de cautionnement.
     */
    public int getIdActeCautionnement() {
        return idActeCautionnement;
    }

    /**
     * Définit l'identifiant de l'acte de cautionnement.
     *
     * @param idActeCautionnement L'identifiant de l'acte de cautionnement à définir.
     */
    public void setIdActeCautionnement(int idActeCautionnement) {
        this.idActeCautionnement = idActeCautionnement;
    }

    /**
     * Récupère le montant de la caution.
     *
     * @return Le montant de la caution.
     */
    public BigDecimal getMontantCaution() {
        return montantCaution;
    }

    /**
     * Définit le montant de la caution.
     *
     * @param montantCaution Le montant de la caution à définir.
     */
    public void setMontantCaution(BigDecimal montantCaution) {
        this.montantCaution = montantCaution;
    }

    /**
     * Récupère l'identifiant du locataire associé à l'acte de cautionnement.
     *
     * @return L'identifiant du locataire.
     */
    public int getIdLocataire() {
        return idLocataire;
    }

    /**
     * Définit l'identifiant du locataire associé à l'acte de cautionnement.
     *
     * @param idLocataire L'identifiant du locataire à définir.
     */
    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
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
               "id_acte_cautionnement=" + idActeCautionnement +
               ", montant_caution=" + (montantCaution != null ? montantCaution : "N/A") +
               ", id_locataire=" + idLocataire +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(idActeCautionnement, idLocataire, montantCaution);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActeCautionnement other = (ActeCautionnement) obj;
		return idLocataire == other.idLocataire
				&& Objects.equals(montantCaution, other.montantCaution);
	}
    
    
    
}
