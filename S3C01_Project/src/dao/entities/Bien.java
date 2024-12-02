package dao.entities;
import java.util.*;
import java.math.*;

public class Bien {
	
	private int id_bien;
	private int etage;
	private String adresse;
	private String ville;
	private BigDecimal superficie;
	private int nombre_de_piece;
	private boolean meuble;
	private String accesoire_prive;
	private String accesoire_commun;
	private boolean est_garage;
	private int id_contrat_location;
	
	public Bien(int id_bien) {
		super();
		this.id_bien = id_bien;
	}
	
	public int getId_bien() {
		return id_bien;
	}
	public void setId_bien(int id_bien) {
		this.id_bien = id_bien;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public BigDecimal getSuperficie() {
		return superficie;
	}
	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}
	public int getNombre_de_piece() {
		return nombre_de_piece;
	}
	public void setNombre_de_piece(int nombre_de_piece) {
		this.nombre_de_piece = nombre_de_piece;
	}
	public boolean isMeuble() {
		return meuble;
	}
	public void setMeuble(boolean meuble) {
		this.meuble = meuble;
	}
	public String getAccesoire_prive() {
		return accesoire_prive;
	}
	public void setAccesoire_prive(String accesoire_prive) {
		this.accesoire_prive = accesoire_prive;
	}
	public String getAccesoire_commun() {
		return accesoire_commun;
	}
	public void setAccesoire_commun(String accesoire_commun) {
		this.accesoire_commun = accesoire_commun;
	}
	public boolean isEst_garage() {
		return est_garage;
	}
	public void setEst_garage(boolean est_garage) {
		this.est_garage = est_garage;
	}
	public int getId_contrat_location() {
		return id_contrat_location;
	}
	public void setId_contrat_location(int id_contrat_location) {
		this.id_contrat_location = id_contrat_location;
	}
	
	@Override
	public String toString() {
		return "Bien{" +
	               "numero_Bien=" + id_bien +
	               ", etage=" + etage +
	               ", adresse=" + (adresse != null ? adresse : "N/A") +
	               ", ville=" + (ville != null ? ville : "N/A") +
	               ", superficie='" + (superficie != null ? superficie : "N/A") + 
	               ", nombre_de_piece='" + nombre_de_piece + 
	               ", meublé=" + meuble +
	               ", accesoire_prive"+ (accesoire_prive != null ? accesoire_prive : "N/A") +
	               ", accesoire_commun"+ (accesoire_commun != null ? accesoire_commun : "N/A") +
	               ", est_garage"+ est_garage +
	               ", numero_contrat_location"+ id_contrat_location +
	               '}';
	}

}
