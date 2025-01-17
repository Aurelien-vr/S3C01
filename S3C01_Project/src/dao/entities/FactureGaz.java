package dao.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente une facture de gaz.
 * Cette classe contient les informations relatives à une facture de gaz,
 * incluant la consommation en mètres cubes, le prix par mètre cube et la référence de la facture.
 */
public class FactureGaz {

    private int idFactureGaz;  // Identifiant unique de la facture de gaz
    private BigDecimal consommationM3;  // Consommation de gaz en mètres cubes
    private String prixM3Gaz;  // Prix par mètre cube de gaz
    private String referenceFacture;  // Référence unique de la facture de gaz
    
    
    
    public FactureGaz() {}

	public FactureGaz(BigDecimal consommationM3, String prixM3Gaz) {
    	super();
    	this.consommationM3 = consommationM3;
    	this.prixM3Gaz = prixM3Gaz;
    }
    
    /**
     * Récupère l'identifiant de la facture de gaz.
     *
     * @return L'identifiant de la facture de gaz.
     */
    public int getIdFactureGaz() {
        return idFactureGaz;
    }


	/**
     * Définit l'identifiant de la facture de gaz.
     *
     * @param idFactureGaz L'identifiant de la facture de gaz à définir.
     */
    public void setIdFactureGaz(int idFactureGaz) {
        this.idFactureGaz = idFactureGaz;
    }

    /**
     * Récupère la consommation de gaz en mètres cubes.
     *
     * @return La consommation de gaz en mètres cubes.
     */
    public BigDecimal getConsommationM3() {
        return consommationM3;
    }

    /**
     * Définit la consommation de gaz en mètres cubes.
     *
     * @param consommationM3 La consommation de gaz à définir.
     */
    public void setConsommationM3(BigDecimal consommationM3) {
        this.consommationM3 = consommationM3;
    }

    /**
     * Récupère le prix par mètre cube de gaz.
     *
     * @return Le prix par mètre cube de gaz.
     */
    public String getPrixM3Gaz() {
        return prixM3Gaz;
    }

    /**
     * Définit le prix par mètre cube de gaz.
     *
     * @param prixm3gaz Le prix du gaz par mètre cube à définir.
     */
    public void setPrixM3Gaz(String prixm3gaz) {
        this.prixM3Gaz = prixm3gaz;
    }

    /**
     * Récupère la référence de la facture de gaz.
     *
     * @return La référence de la facture de gaz.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture de gaz.
     *
     * @param referenceFacture La référence de la facture de gaz à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link FactureGaz}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Facture_gaz{" +
               "id_facture_gaz=" + idFactureGaz +
               ", consommation_m3=" + (consommationM3 != null ? consommationM3 : "N/A") +
               ", prix_m3_gaz='" + (prixM3Gaz != null ? prixM3Gaz : "N/A") + '\'' +
               ", reference_facture='" + (referenceFacture != null ? referenceFacture : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(consommationM3, idFactureGaz, prixM3Gaz, referenceFacture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactureGaz other = (FactureGaz) obj;
		return Objects.equals(consommationM3, other.consommationM3) 
				&& Objects.equals(prixM3Gaz, other.prixM3Gaz)
				&& Objects.equals(referenceFacture, other.referenceFacture);
	}

	
}
