package dao;
import dbConnection.*;
import dao.implementation.*;

public class DAOFactory {
	
	public static Contrat_locationDAO createContrat_locationDAO() {
		return new Contrat_locationImpl(DatabaseConnection.getInstance());
	}
	
	public static BienDAO createBienDAO() {
		return new BienImpl(DatabaseConnection.getInstance());
	}
	
}
