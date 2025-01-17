package dao.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente une facture d'électricité.
 * Cette classe contient les informations relatives à une facture d'électricité,
 * incluant le compteur d'électricité, le prix par kilowatt et la référence de la facture.
 */
public class FactureElectricite {

	private int idFactureElectricite;  // Identifiant unique de la facture d'électricité
    private BigDecimal compteurElectricite;  // Valeur du compteur d'électricité au moment de la facturation
    private String prixKwElectricite;  // Prix du kilowatt pour la facture d'électricité
    private String referenceFacture;  // Référence unique de la facture d'électricité

    public FactureElectricite() {}
    
    public FactureElectricite(BigDecimal compteurElectricite, String prixKwElectricite) {
		super();
		this.compteurElectricite = compteurElectricite;
		this.prixKwElectricite = prixKwElectricite;
	}

	/**
     * Récupère l'identifiant de la facture d'électricité.
     *
     * @return L'identifiant de la facture d'électricité.
     */
    public int getIdFactureElectricite() {
        return idFactureElectricite;
    }

    /**
     * Définit l'identifiant de la facture d'électricité.
     *
     * @param idFactureElectricite L'identifiant de la facture d'électricité à définir.
     */
    public void setIdFactureElectricite(int idFactureElectricite) {
        this.idFactureElectricite = idFactureElectricite;
    }

    /**
     * Récupère la valeur du compteur d'électricité.
     *
     * @return La valeur du compteur d'électricité.
     */
    public BigDecimal getCompteurElectricite() {
        return compteurElectricite;
    }

    /**
     * Définit la valeur du compteur d'électricité.
     *
     * @param compteurElectricite La valeur du compteur d'électricité à définir.
     */
    public void setCompteurElectricite(BigDecimal compteurElectricite) {
        this.compteurElectricite = compteurElectricite;
    }

    /**
     * Récupère le prix par kilowatt de la facture d'électricité.
     *
     * @return Le prix par kilowatt.
     */
    public String getPrixKwElectricite() {
        return prixKwElectricite;
    }

    /**
     * Définit le prix par kilowatt de la facture d'électricité.
     *
     * @param prixKwElectricite Le prix du kilowatt à définir.
     */
    public void setPrixKwElectricite(String prixKwElectricite) {
        this.prixKwElectricite = prixKwElectricite;
    }

    /**
     * Récupère la référence de la facture d'électricité.
     *
     * @return La référence de la facture d'électricité.
     */
    public String getReferenceFacture() {
        return referenceFacture;
    }

    /**
     * Définit la référence de la facture d'électricité.
     *
     * @param referenceFacture La référence de la facture à définir.
     */
    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link FactureElectricite}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Facture_electricite{" +
               "id_facture_electricite=" + idFactureElectricite +
               ", compteur_electricite=" + (compteurElectricite != null ? compteurElectricite : "N/A") +
               ", prix_kw_electricite='" + (prixKwElectricite != null ? prixKwElectricite : "N/A") + '\'' +
               ", reference_facture='" + (referenceFacture != null ? referenceFacture : "N/A") + '\'' +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(compteurElectricite, idFactureElectricite, prixKwElectricite, referenceFacture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactureElectricite other = (FactureElectricite) obj;
		return Objects.equals(compteurElectricite, other.compteurElectricite)
				&& Objects.equals(prixKwElectricite, other.prixKwElectricite)
				&& Objects.equals(referenceFacture, other.referenceFacture);
	}
    
    
}
