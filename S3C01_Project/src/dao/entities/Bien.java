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
	
    private int idBien;  // Identifiant unique du bien
    private int etage;  // L'étage du bien immobilier
    private String adresse;  // L'adresse du bien immobilier
    private String ville;  // La ville où se situe le bien
    private String codePostal; //Code postal du bien
    private BigDecimal superficie;  // Superficie du bien immobilier
    private int nombreDePiece;  // Nombre de pièces dans le bien
    private boolean meuble;  // Indique si le bien est meublé
    private String accessoirePrive;  // Accessoires privés du bien
    private String accessoireCommun;  // Accessoires communs du bien
    private boolean estGarage;  // Indique si le bien possède un garage

    /**
     * Constructeur de la classe Bien.
     * Initialise un bien avec son identifiant unique.
     *
     * @param idBien L'identifiant du bien.
     */
    
    public Bien() {}
    
    
    
    public Bien(int etage, String adresse, String ville, String codePostal, BigDecimal superficie, int nombreDePiece, boolean meuble,
			String accessoirePrive, String accessoireCommun, boolean estGarage) {
    	
		super();
		this.etage = etage;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.superficie = superficie;
		this.nombreDePiece = nombreDePiece;
		this.meuble = meuble;
		this.accessoirePrive = accessoirePrive;
		this.accessoireCommun = accessoireCommun;
		this.estGarage = estGarage;
	}



	/**
     * Récupère l'identifiant du bien.
     *
     * @return L'identifiant du bien.
     */
    public int getIdBien() {
        return idBien;
    }

    /**
     * Définit l'identifiant du bien.
     *
     * @param idBien L'identifiant du bien à définir.
     */
    public void setIdBien(int idBien) {
        this.idBien = idBien;
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
    public int getNombreDePiece() {
        return nombreDePiece;
    }

    /**
     * Définit le nombre de pièces du bien.
     *
     * @param nombreDePiece Le nombre de pièces à définir.
     */
    public void setNombreDePiece(int nombreDePiece) {
        this.nombreDePiece = nombreDePiece;
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
    public String getAccessoirePrive() {
        return accessoirePrive;
    }

    /**
     * Définit les accessoires privés du bien.
     *
     * @param accessoirePrive Les accessoires privés à définir.
     */
    public void setAccessoirePrive(String accessoirePrive) {
        this.accessoirePrive = accessoirePrive;
    }

    /**
     * Récupère les accessoires communs du bien.
     *
     * @return Les accessoires communs du bien.
     */
    public String getAccessoireCommun() {
        return accessoireCommun;
    }

    /**
     * Définit les accessoires communs du bien.
     *
     * @param accessoireCommun Les accessoires communs à définir.
     */
    public void setAccessoireCommun(String accessoireCommun) {
        this.accessoireCommun = accessoireCommun;
    }

    /**
     * Récupère l'état du garage du bien.
     *
     * @return true si le bien possède un garage, false sinon.
     */
    public boolean isEstGarage() {
        return estGarage;
    }

    /**
     * Définit si le bien possède un garage.
     *
     * @param estGarage true si le bien possède un garage, false sinon.
     */
    public void setEstGarage(boolean estGarage) {
        this.estGarage = estGarage;
    }
    
    /**
     * Récupère l'identifiant du contrat de location associé au bien.
     *
     * @return L'identifiant du contrat de location.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit l'identifiant du contrat de location associé au bien.
     *
     * @param id_contrat_location L'identifiant du contrat de location à définir.
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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
               "numero_Bien=" + idBien +
               ", etage=" + etage +
               ", adresse=" + (adresse != null ? adresse : "N/A") +
               ", ville=" + (ville != null ? ville : "N/A") +
               ", code_postal=" + (codePostal != null ? codePostal : "N/A") +
               ", superficie=" + (superficie != null ? superficie : "N/A") +
               ", nombre_de_piece=" + nombreDePiece +
               ", meuble=" + meuble +
               ", accessoire_prive=" + (accessoirePrive != null ? accessoirePrive : "N/A") +
               ", accessoire_commun=" + (accessoireCommun != null ? accessoireCommun : "N/A") +
               ", est_garage=" + estGarage +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(accessoireCommun, accessoirePrive, adresse, estGarage, etage, idBien,
				meuble, nombreDePiece, codePostal, superficie, ville);
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
		return Objects.equals(accessoireCommun, other.accessoireCommun)
				&& Objects.equals(accessoirePrive, other.accessoirePrive) && Objects.equals(adresse, other.adresse)
				&& estGarage == other.estGarage && etage == other.etage
				&& meuble == other.meuble
				&& nombreDePiece == other.nombreDePiece && Objects.equals(superficie, other.superficie)
				&& Objects.equals(ville, other.ville) && Objects.equals(codePostal, other.codePostal);
	}
}
