package dao.entities;

import java.sql.Date;
import java.util.Objects;

public class Charge {
	private int id_charge;
	private Date date_charge;
	
	public Charge() {}
	
	public Charge(int id_charge, Date date_charge) {
		this.id_charge = id_charge;
		this.date_charge = date_charge;
	}

	public int getId_charge() {
		return id_charge;
	}

	public Date getDate_charge() {
		return date_charge;
	}

	public void setId_charge(int id_charge) {
		this.id_charge = id_charge;
	}

	public void setDate_charge(Date date_charge) {
		this.date_charge = date_charge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date_charge, id_charge);
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
		return Objects.equals(date_charge, other.date_charge) && id_charge == other.id_charge;
	}
	
	
}
