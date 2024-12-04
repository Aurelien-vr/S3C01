package application;

import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.entities.Contrat_location;

/**
 * Classe principale de l'application.
 * Permet de tester l'accès aux données via le DAO (Data Access Object) pour les contrats de location.
 */
public class App {

    /**
     * Point d'entrée principal de l'application.
     */
    public static void main(String[] args) {
        // Création d'une instance de Contrat_locationDAO via la factory
        Contrat_locationDAO contrat_locationDAO = DAOFactory.createContrat_locationDAO();
        
        // Recherche d'un contrat de location par son identifiant (ici, ID = 1)
        Contrat_location contrat_location = contrat_locationDAO.findOne(1);
        
        // Vérification et affichage du contrat de location trouvé
        if (contrat_location != null) {
            System.out.println(contrat_location);
        } else {
            System.out.println("CONTRAT LOCATION EST NULL");
        }
    }
}
