package dao.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Représente une déclaration de revenu provenant d'un bien immobilier.
 * Cette classe contient les informations sur la déclaration de revenu, y compris la date d'acquisition,
 * le nombre de locataires, le montant des recettes générées par l'immeuble, et l'identifiant du bien concerné.
 */
public class Declaration_revenu {

    private int id_declaration_revenu;  // Identifiant unique de la déclaration de revenu
    private Date date_acquisition;  // Date d'acquisition du bien immobilier
    private int locataires;  // Nombre de locataires du bien immobilier
    private BigDecimal recette_immeuble;  // Montant des recettes générées par l'immeuble
    private int id_bien;  // Identifiant du bien immobilier lié à la déclaration

    public Declaration_revenu() {};
    
    public Declaration_revenu(Date date_acquisition, int locataires, BigDecimal recette_immeuble) {
		super();
		this.date_acquisition = date_acquisition;
		this.locataires = locataires;
		this.recette_immeuble = recette_immeuble;
	}

	/**
     * Récupère l'identifiant unique de la déclaration de revenu.
     *
     * @return L'identifiant de la déclaration de revenu.
     */
    public int getId_declaration_revenu() {
        return id_declaration_revenu;
    }

    /**
     * Définit l'identifiant unique de la déclaration de revenu.
     *
     * @param id_declaration_revenu L'identifiant à définir.
     */
    public void setId_declaration_revenu(int id_declaration_revenu) {
        this.id_declaration_revenu = id_declaration_revenu;
    }

    /**
     * Récupère la date d'acquisition du bien immobilier.
     *
     * @return La date d'acquisition du bien.
     */
    public Date getDate_acquisition() {
        return date_acquisition;
    }

    /**
     * Définit la date d'acquisition du bien immobilier.
     *
     * @param date_acquisition La date d'acquisition à définir.
     */
    public void setDate_acquisition(Date date_acquisition) {
        this.date_acquisition = date_acquisition;
    }

    /**
     * Récupère le nombre de locataires du bien immobilier.
     *
     * @return Le nombre de locataires.
     */
    public int getLocataires() {
        return locataires;
    }

    /**
     * Définit le nombre de locataires du bien immobilier.
     *
     * @param locataires Le nombre de locataires à définir.
     */
    public void setLocataires(int locataires) {
        this.locataires = locataires;
    }

    /**
     * Récupère le montant des recettes générées par l'immeuble.
     *
     * @return Les recettes de l'immeuble.
     */
    public BigDecimal getRecette_immeuble() {
        return recette_immeuble;
    }

    /**
     * Définit les recettes générées par l'immeuble.
     *
     * @param recette_immeuble Le montant des recettes à définir.
     */
    public void setRecette_immeuble(BigDecimal recette_immeuble) {
        this.recette_immeuble = recette_immeuble;
    }

    /**
     * Récupère l'identifiant du bien immobilier lié à la déclaration.
     *
     * @return L'identifiant du bien immobilier.
     */
    public int getId_bien() {
        return id_bien;
    }

    /**
     * Définit l'identifiant du bien immobilier lié à la déclaration.
     *
     * @param id_bien L'identifiant du bien à définir.
     */
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Declaration_revenu}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant la déclaration de revenu.
     */
    @Override
    public String toString() {
        return "Declaration_revenu{" +
               "id_declaration_revenu=" + id_declaration_revenu +
               ", date_acquisition=" + (date_acquisition != null ? date_acquisition : "N/A") +
               ", locataires=" + locataires +
               ", recette_immeuble=" + (recette_immeuble != null ? recette_immeuble : "N/A") +
               ", id_bien=" + id_bien +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(date_acquisition, id_bien, id_declaration_revenu, locataires, recette_immeuble);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Declaration_revenu other = (Declaration_revenu) obj;
		return Objects.equals(date_acquisition, other.date_acquisition) && id_bien == other.id_bien
				&& id_declaration_revenu == other.id_declaration_revenu && locataires == other.locataires
				&& Objects.equals(recette_immeuble, other.recette_immeuble);
	}
}
