package dao;

import java.util.List;

import dao.entities.Charge;

public interface ChargeDAO extends DAO<Charge>{
    public void insertFK(int idContratLocation,int idCharges); 
    public List<List<String>> procGetCharges();
    public List<List<String>> procGetChargesActifs();
}
