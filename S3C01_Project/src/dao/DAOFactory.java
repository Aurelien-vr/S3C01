package dao;
import dbConnection.*;
import dao.implementation.*;

public class DAOFactory {
	
	public static Contrat_locationDAO createContrat_locationDAO() {
		return new Contrat_locationImpl(DatabaseConnection.getInstance());
	}
	
}
