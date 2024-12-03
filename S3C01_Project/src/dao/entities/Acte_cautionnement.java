package dao.entities;
import java.math.*;

public class Acte_cautionnement {

	private int id_acte_cautionnement;
	private BigDecimal montant_caution;
	private int id_locataire;
	
	public int getId_acte_cautionnement() {
		return id_acte_cautionnement;
	}
	public void setId_acte_cautionnement(int id_acte_cautionnement) {
		this.id_acte_cautionnement = id_acte_cautionnement;
	}
	public BigDecimal getMontant_caution() {
		return montant_caution;
	}
	public void setMontant_caution(BigDecimal montant_caution) {
		this.montant_caution = montant_caution;
	}
	public int getId_locataire() {
		return id_locataire;
	}
	public void setId_locataire(int id_locataire) {
		this.id_locataire = id_locataire;
	}
	
	@Override
	public String toString() {
	    return "Acte_Cautionnement{" +
	           "id_acte_cautionnement=" + id_acte_cautionnement +
	           ", montant_caution=" + (montant_caution != null ? montant_caution : "N/A") +
	           ", id_locataire=" + id_locataire +
	           '}';
	}

}
