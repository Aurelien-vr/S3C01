package dao.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente une facture d'eau.
 * Cette classe contient les informations relatives à une facture d'eau, incluant la partie fixe,
 * la consommation et la référence de la facture.
 */
public class FactureEau {

    private int idFactureEau;  // Identifiant unique de la facture d'eau
    private BigDecimal partieFixe;  // Partie fixe de la facture d'eau (ex: frais fixes pour l'abonnement)
    private BigDecimal consommation;  // Consommation d'eau facturée
    private String referenceFacture;  // Référence unique de la facture

    public FactureEau() {}
    
    public FactureEau(BigDecimal partieFixe, BigDecimal consommation) {
		super();
		this.partieFixe = partieFixe;
		this.consommation = consommation;
	}

	/**
     * Récupère l'identifiant de la facture d'eau.
     *
     * @return L'identifiant de la facture d'eau.
     */
    public int getIdFactureEau() {
        return idFactureEau;
    }

    /**
     * Définit l'identifiant de la facture d'eau.
     *
     * @param idFactureEau L'identifiant de la facture d'eau à définir.
     */
    public void setIdFactureEau(int idFactureEau) {
        this.idFactureEau = idFactureEau;
    }

    /**
     * Récupère la partie fixe de la facture d'eau.
     *
     * @return La partie fixe de la facture d'eau.
     */
    public BigDecimal getPartieFixe() {
        return partieFixe;
    }

    /**
     * Définit la partie fixe de la facture d'eau.
     *
     * @param partieFixe La partie fixe à définir.
     */
    public void setPartieFixe(BigDecimal partieFixe) {
        this.partieFixe = partieFixe;
    }

    /**
     * Récupère la consommation d'eau facturée.
     *
     * @return La consommation d'eau.
     */
    public BigDecimal getConsommation() {
        return consommation;
    }

    /**
     * Définit la consommation d'eau facturée.
     *
     * @param consommation La consommation d'eau à définir.
     */
    public void setConsommation(BigDecimal consommation) {
        this.consommation = consommation;
    }

    /**
     * Récupère la référence de la facture d'eau.
     *
     * @return La référence de la facture.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture d'eau.
     *
     * @param referenceFacture La référence de la facture à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link FactureEau}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Facture_eau{" +
               "id_facture_eau=" + idFactureEau +
               ", partie_fixe=" + (partieFixe != null ? partieFixe : "N/A") +
               ", consommation=" + (consommation != null ? consommation : "N/A") +
               ", reference_facture='" + (referenceFacture != null ? referenceFacture : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(consommation, idFactureEau, partieFixe, referenceFacture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactureEau other = (FactureEau) obj;
		return Objects.equals(consommation, other.consommation)
				&& Objects.equals(partieFixe, other.partieFixe)
				&& Objects.equals(referenceFacture, other.referenceFacture);
	}
}
