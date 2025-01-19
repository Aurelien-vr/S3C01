package dao;

import java.util.List;

import dao.entities.Charge;

public interface ChargeDAO extends DAO<Charge>{
    public void insertFK(int idContratLocation,int idCharges); 
    public List<List<String>> procGetCharges();
    public List<List<String>> procGetChargesActifs();
    public List<List<String>> procFactureFromCharge(int inputYear, int idBien);
    public List<List<String>> procYearFromFacture(int idBien);
	public List<List<String>> procFactureFromBien(int idBien);
	public int procIdCharge(int idCl, int year);
	public int progGetIdClFromIdBien(int idBien);
}
