package dao.entities;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente un bien immobilier associé à un contrat de location.
 * Cette classe contient les informations relatives à un bien, 
 * telles que son identifiant, son étage, son adresse, sa superficie, 
 * le nombre de pièces, l'état de son meuble, les accessoires privés et communs,
 * l'existence d'un garage et l'identifiant du contrat de location associé.
 */
public class Bien {
	
    private int id_bien;  // Identifiant unique du bien
    private int etage;  // L'étage du bien immobilier
    private String adresse;  // L'adresse du bien immobilier
    private String ville;  // La ville où se situe le bien
    private String code_postal; //Code postal du bien
    private BigDecimal superficie;  // Superficie du bien immobilier
    private int nombre_de_piece;  // Nombre de pièces dans le bien
    private boolean meuble;  // Indique si le bien est meublé
    private String accessoire_prive;  // Accessoires privés du bien
    private String accessoire_commun;  // Accessoires communs du bien
    private boolean est_garage;  // Indique si le bien possède un garage
    private int id_contrat_location;  // Identifiant du contrat de location associé

    /**
     * Constructeur de la classe Bien.
     * Initialise un bien avec son identifiant unique.
     *
     * @param id_bien L'identifiant du bien.
     */
    
    public Bien() {}
    
    
    
    public Bien(int etage, String adresse, String ville, String code_postal, BigDecimal superficie, int nombre_de_piece, boolean meuble,
			String accessoire_prive, String accessoire_commun, boolean est_garage) {
		super();
		this.etage = etage;
		this.adresse = adresse;
		this.ville = ville;
		this.code_postal = code_postal;
		this.superficie = superficie;
		this.nombre_de_piece = nombre_de_piece;
		this.meuble = meuble;
		this.accessoire_prive = accessoire_prive;
		this.accessoire_commun = accessoire_commun;
		this.est_garage = est_garage;
	}



	/**
     * Récupère l'identifiant du bien.
     *
     * @return L'identifiant du bien.
     */
    public int getId_bien() {
        return id_bien;
    }

    /**
     * Définit l'identifiant du bien.
     *
     * @param id_bien L'identifiant du bien à définir.
     */
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    /**
     * Récupère l'étage du bien.
     *
     * @return L'étage du bien.
     */
    public int getEtage() {
        return etage;
    }

    /**
     * Définit l'étage du bien.
     *
     * @param etage L'étage du bien à définir.
     */
    public void setEtage(int etage) {
        this.etage = etage;
    }

    /**
     * Récupère l'adresse du bien.
     *
     * @return L'adresse du bien.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse du bien.
     *
     * @param adresse L'adresse du bien à définir.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère la ville du bien.
     *
     * @return La ville où se trouve le bien.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Définit la ville du bien.
     *
     * @param ville La ville où se trouve le bien.
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Récupère la superficie du bien.
     *
     * @return La superficie du bien.
     */
    public BigDecimal getSuperficie() {
        return superficie;
    }

    /**
     * Définit la superficie du bien.
     *
     * @param superficie La superficie du bien à définir.
     */
    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    /**
     * Récupère le nombre de pièces du bien.
     *
     * @return Le nombre de pièces.
     */
    public int getNombre_de_piece() {
        return nombre_de_piece;
    }

    /**
     * Définit le nombre de pièces du bien.
     *
     * @param nombre_de_piece Le nombre de pièces à définir.
     */
    public void setNombre_de_piece(int nombre_de_piece) {
        this.nombre_de_piece = nombre_de_piece;
    }

    /**
     * Récupère l'état de meuble du bien.
     *
     * @return true si le bien est meublé, false sinon.
     */
    public boolean isMeuble() {
        return meuble;
    }

    /**
     * Définit si le bien est meublé.
     *
     * @param meuble true si le bien est meublé, false sinon.
     */
    public void setMeuble(boolean meuble) {
        this.meuble = meuble;
    }

    /**
     * Récupère les accessoires privés du bien.
     *
     * @return Les accessoires privés du bien.
     */
    public String getAccessoire_prive() {
        return accessoire_prive;
    }

    /**
     * Définit les accessoires privés du bien.
     *
     * @param accessoire_prive Les accessoires privés à définir.
     */
    public void setAccessoire_prive(String accessoire_prive) {
        this.accessoire_prive = accessoire_prive;
    }

    /**
     * Récupère les accessoires communs du bien.
     *
     * @return Les accessoires communs du bien.
     */
    public String getAccessoire_commun() {
        return accessoire_commun;
    }

    /**
     * Définit les accessoires communs du bien.
     *
     * @param accessoire_commun Les accessoires communs à définir.
     */
    public void setAccessoire_commun(String accessoire_commun) {
        this.accessoire_commun = accessoire_commun;
    }

    /**
     * Récupère l'état du garage du bien.
     *
     * @return true si le bien possède un garage, false sinon.
     */
    public boolean isEst_garage() {
        return est_garage;
    }

    /**
     * Définit si le bien possède un garage.
     *
     * @param est_garage true si le bien possède un garage, false sinon.
     */
    public void setEst_garage(boolean est_garage) {
        this.est_garage = est_garage;
    }

    /**
     * Récupère l'identifiant du contrat de location associé au bien.
     *
     * @return L'identifiant du contrat de location.
     */
    public int getId_contrat_location() {
        return id_contrat_location;
    }

    /**
     * Définit l'identifiant du contrat de location associé au bien.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setId_contrat_location(int id_contrat_location) {
        this.id_contrat_location = id_contrat_location;
    }
    
    /**
     * Récupère l'identifiant du contrat de location associé au bien.
     *
     * @return L'identifiant du contrat de location.
     */
    public String getCode_postal() {
        return code_postal;
    }

    /**
     * Définit l'identifiant du contrat de location associé au bien.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    /**
     * Retourne une représentation textuelle de l'objet {@link Bien}.
     * Utilisé pour un affichage ou un débogage rapide.
     *
     * @return Une chaîne de caractères représentant le bien.
     */
    @Override
    public String toString() {
        return "Bien{" +
               "numero_Bien=" + id_bien +
               ", etage=" + etage +
               ", adresse=" + (adresse != null ? adresse : "N/A") +
               ", ville=" + (ville != null ? ville : "N/A") +
               ", code_postal=" + (code_postal != null ? code_postal : "N/A") +
               ", superficie=" + (superficie != null ? superficie : "N/A") +
               ", nombre_de_piece=" + nombre_de_piece +
               ", meuble=" + meuble +
               ", accessoire_prive=" + (accessoire_prive != null ? accessoire_prive : "N/A") +
               ", accessoire_commun=" + (accessoire_commun != null ? accessoire_commun : "N/A") +
               ", est_garage=" + est_garage +
               ", numero_contrat_location=" + id_contrat_location +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(accessoire_commun, accessoire_prive, adresse, est_garage, etage, id_bien, id_contrat_location,
				meuble, nombre_de_piece, code_postal, superficie, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bien other = (Bien) obj;
		return Objects.equals(accessoire_commun, other.accessoire_commun)
				&& Objects.equals(accessoire_prive, other.accessoire_prive) && Objects.equals(adresse, other.adresse)
				&& est_garage == other.est_garage && etage == other.etage && id_bien == other.id_bien
				&& id_contrat_location == other.id_contrat_location && meuble == other.meuble
				&& nombre_de_piece == other.nombre_de_piece && Objects.equals(superficie, other.superficie)
				&& Objects.equals(ville, other.ville) && Objects.equals(code_postal, other.code_postal);
	}
}
