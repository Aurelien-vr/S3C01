package dao.entities;
import java.util.*;

public class Contrat_location {
	
	private int id_numero_location;
	private int montant_loyer;
	private Date date_debut;
	private Date date_fin;
	private String modalite_chauffage;
	private String modalite_eau_chaude_saniatire;
	private Date date_versement;
	
	public Contrat_location(int numero_location) {
		super();
		this.id_numero_location = numero_location;
	}
	
	public int getNumero_location() {
		return id_numero_location;
	}
	public void setNumero_location(int numero_location) {
		this.id_numero_location = numero_location;
	}
	public int getMontant_loyer() {
		return montant_loyer;
	}
	public void setMontant_loyer(int montant_loyer) {
		this.montant_loyer = montant_loyer;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public String getModalite_chauffage() {
		return modalite_chauffage;
	}
	public void setModalite_chauffage(String modalite_chauffage) {
		this.modalite_chauffage = modalite_chauffage;
	}
	public String getModalite_eau_chaude_saniatire() {
		return modalite_eau_chaude_saniatire;
	}
	public void setModalite_eau_chaude_saniatire(String modalite_eau_chaude_saniatire) {
		this.modalite_eau_chaude_saniatire = modalite_eau_chaude_saniatire;
	}
	public Date getDate_versement() {
		return date_versement;
	}
	public void setDate_versement(Date date_versement) {
		this.date_versement = date_versement;
	}

	@Override
	public String toString() {
		return "ContratLocation{" +
	               "numero_location=" + id_numero_location +
	               ", montant_loyer=" + montant_loyer +
	               ", date_debut=" + (date_debut != null ? date_debut : "N/A") +
	               ", date_fin=" + (date_fin != null ? date_fin : "N/A") +
	               ", modalite_chauffage='" + (modalite_chauffage != null ? modalite_chauffage : "N/A") + '\'' +
	               ", modalite_eau_chaude_saniatire='" + (modalite_eau_chaude_saniatire != null ? modalite_eau_chaude_saniatire : "N/A") + '\'' +
	               ", date_versement=" + (date_versement != null ? date_versement : "N/A") +
	               '}';
	}
	
}
