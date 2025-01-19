package dao.entities;

import java.sql.Date;
import java.util.Objects;

public class Charge {
	private int idCharge;
	private Date dateCharge;
	
	public Charge() {}
	
	public Charge(Date dateCharge) {
		this.dateCharge = dateCharge;
	}

	public int getIdCharge() {
		return idCharge;
	}

	public Date getDateCharge() {
		return dateCharge;
	}

	public void setIdCharge(int idCharge) {
		this.idCharge = idCharge;
	}

	public void setDateCharge(Date dateCharge) {
		this.dateCharge = dateCharge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCharge, idCharge);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Charge other = (Charge) obj;
		return Objects.equals(dateCharge, other.dateCharge) && idCharge == other.idCharge;
	}

	@Override
	public String toString() {
		return "Charge [idCharge=" + idCharge + ", dateCharge=" + dateCharge + "]";
	}
	
}
