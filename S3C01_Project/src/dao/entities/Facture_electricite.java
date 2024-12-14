package dao.entities;

import java.math.BigDecimal;

/**
 * Représente une facture d'électricité.
 * Cette classe contient les informations relatives à une facture d'électricité,
 * incluant le compteur d'électricité, le prix par kilowatt et la référence de la facture.
 */
public class Facture_electricite {
	
	public Facture_electricite() {super();}
	
    public Facture_electricite(BigDecimal compteur_electricite, String prix_kw_electricite, String reference_facture) {
		super();
		this.compteur_electricite = compteur_electricite;
		this.prix_kw_electricite = prix_kw_electricite;
		this.reference_facture = reference_facture;
	}

	private int id_facture_electricite;  // Identifiant unique de la facture d'électricité
    private BigDecimal compteur_electricite;  // Valeur du compteur d'électricité au moment de la facturation
    private String prix_kw_electricite;  // Prix du kilowatt pour la facture d'électricité
    private String reference_facture;  // Référence unique de la facture d'électricité

    /**
     * Récupère l'identifiant de la facture d'électricité.
     *
     * @return L'identifiant de la facture d'électricité.
     */
    public int getId_facture_electricite() {
        return id_facture_electricite;
    }

    /**
     * Définit l'identifiant de la facture d'électricité.
     *
     * @param id_facture_electricite L'identifiant de la facture d'électricité à définir.
     */
    public void setId_facture_electricite(int id_facture_electricite) {
        this.id_facture_electricite = id_facture_electricite;
    }

    /**
     * Récupère la valeur du compteur d'électricité.
     *
     * @return La valeur du compteur d'électricité.
     */
    public BigDecimal getCompteur_electricite() {
        return compteur_electricite;
    }

    /**
     * Définit la valeur du compteur d'électricité.
     *
     * @param compteur_electricite La valeur du compteur d'électricité à définir.
     */
    public void setCompteur_electricite(BigDecimal compteur_electricite) {
        this.compteur_electricite = compteur_electricite;
    }

    /**
     * Récupère le prix par kilowatt de la facture d'électricité.
     *
     * @return Le prix par kilowatt.
     */
    public String getPrix_kw_electricite() {
        return prix_kw_electricite;
    }

    /**
     * Définit le prix par kilowatt de la facture d'électricité.
     *
     * @param prix_kw_electricite Le prix du kilowatt à définir.
     */
    public void setPrix_kw_electricite(String prix_kw_electricite) {
        this.prix_kw_electricite = prix_kw_electricite;
    }

    /**
     * Récupère la référence de la facture d'électricité.
     *
     * @return La référence de la facture d'électricité.
     */
    public String getReference_facture() {
        return reference_facture;
    }

    /**
     * Définit la référence de la facture d'électricité.
     *
     * @param reference_facture La référence de la facture à définir.
     */
    public void setReference_facture(String reference_facture) {
        this.reference_facture = reference_facture;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Facture_electricite}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant l'entité.
     */
    @Override
    public String toString() {
        return "Facture_electricite{" +
               "id_facture_electricite=" + id_facture_electricite +
               ", compteur_electricite=" + (compteur_electricite != null ? compteur_electricite : "N/A") +
               ", prix_kw_electricite='" + (prix_kw_electricite != null ? prix_kw_electricite : "N/A") + '\'' +
               ", reference_facture='" + (reference_facture != null ? reference_facture : "N/A") + '\'' +
               '}';
    }

	public Facture_electricite(int id_facture_electricite, BigDecimal compteur_electricite, String prix_kw_electricite,
			String reference_facture) {
		super();
		this.id_facture_electricite = id_facture_electricite;
		this.compteur_electricite = compteur_electricite;
		this.prix_kw_electricite = prix_kw_electricite;
		this.reference_facture = reference_facture;
	}
}
