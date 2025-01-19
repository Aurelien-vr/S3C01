package dao.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Représente une déclaration de revenu provenant d'un bien immobilier.
 * Cette classe contient les informations sur la déclaration de revenu, y compris la date d'acquisition,
 * le nombre de locataires, le montant des recettes générées par l'immeuble, et l'identifiant du bien concerné.
 */
public class DeclarationRevenu {

    private int idDeclarationRevenu;  // Identifiant unique de la déclaration de revenu
    private Date dateAcquisition;  // Date d'acquisition du bien immobilier
    private int locataires;  // Nombre de locataires du bien immobilier
    private BigDecimal recetteImmeuble;  // Montant des recettes générées par l'immeuble
    private int idBien;  // Identifiant du bien immobilier lié à la déclaration

    public DeclarationRevenu() {}
    
    public DeclarationRevenu(Date dateAcquisition, int locataires, BigDecimal recetteImmeuble) {
		super();
		this.dateAcquisition = dateAcquisition;
		this.locataires = locataires;
		this.recetteImmeuble = recetteImmeuble;
	}

	/**
     * Récupère l'identifiant unique de la déclaration de revenu.
     *
     * @return L'identifiant de la déclaration de revenu.
     */
    public int getIdDeclarationRevenu() {
        return idDeclarationRevenu;
    }

    /**
     * Définit l'identifiant unique de la déclaration de revenu.
     *
     * @param idDeclarationRevenu L'identifiant à définir.
     */
    public void setIdDeclarationRevenu(int idDeclarationRevenu) {
        this.idDeclarationRevenu = idDeclarationRevenu;
    }

    /**
     * Récupère la date d'acquisition du bien immobilier.
     *
     * @return La date d'acquisition du bien.
     */
    public Date getDateAcquisition() {
        return dateAcquisition;
    }

    /**
     * Définit la date d'acquisition du bien immobilier.
     *
     * @param dateAcquisition La date d'acquisition à définir.
     */
    public void setDateAcquisition(Date dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
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
    public BigDecimal getRecetteImmeuble() {
        return recetteImmeuble;
    }

    /**
     * Définit les recettes générées par l'immeuble.
     *
     * @param recetteImmeuble Le montant des recettes à définir.
     */
    public void setRecetteImmeuble(BigDecimal recetteImmeuble) {
        this.recetteImmeuble = recetteImmeuble;
    }

    /**
     * Récupère l'identifiant du bien immobilier lié à la déclaration.
     *
     * @return L'identifiant du bien immobilier.
     */
    public int getIdBien() {
        return idBien;
    }

    /**
     * Définit l'identifiant du bien immobilier lié à la déclaration.
     *
     * @param idBien L'identifiant du bien à définir.
     */
    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link DeclarationRevenu}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant la déclaration de revenu.
     */
    @Override
    public String toString() {
        return "Declaration_revenu{" +
               "id_declaration_revenu=" + idDeclarationRevenu +
               ", date_acquisition=" + (dateAcquisition != null ? dateAcquisition : "N/A") +
               ", locataires=" + locataires +
               ", recette_immeuble=" + (recetteImmeuble != null ? recetteImmeuble : "N/A") +
               ", id_bien=" + idBien +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(dateAcquisition, idBien, idDeclarationRevenu, locataires, recetteImmeuble);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeclarationRevenu other = (DeclarationRevenu) obj;
		return Objects.equals(dateAcquisition, other.dateAcquisition) && idBien == other.idBien
			    && locataires == other.locataires
				&& Objects.equals(recetteImmeuble, other.recetteImmeuble);
	}
}
