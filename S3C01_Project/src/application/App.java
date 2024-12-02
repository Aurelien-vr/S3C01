package application;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;

public class App {
	
	public static void main(String[] args) {
		Contrat_locationDAO contrat_locationDAO = DAOFactory.createContrat_locationDAO();
		Contrat_location contrat_location = contrat_locationDAO.findOne(1);
		if(contrat_location!=null) {			
			System.out.println(contrat_location);
		}else {
			System.out.println("CONTRAT LOCATION EST NULL");
		}
	}
}
