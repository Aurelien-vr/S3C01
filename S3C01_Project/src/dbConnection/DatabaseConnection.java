package dbConnection;
import java.sql.*;
import javax.swing.*;


/**
 * Classe utilitaire pour gérer la connexion à la base de données.
 */

import exception.ExceptionStorageHandler;


public class DatabaseConnection {
    
    // Nom d'utilisateur pour la connexion à la base de données
    private static String username = "avnadmin";
    
    // Mot de passe masqué pour la connexion à la base de données
    private static String mdp;
    
    // Instance de la connexion unique (utilisée pour le pattern Singleton)
    private static Connection instance;


    /**
     * Constructeur privé pour empêcher l'instanciation directe de cette classe.
     * Utilise le pattern Singleton pour garantir qu'il n'y ait qu'une seule connexion à la fois.
     */
    private DatabaseConnection() { }



    /**
     * Méthode pour récupérer l'instance unique de la connexion à la base de données.
     * Si la connexion n'existe pas encore, elle est créée.
     * 
     * @return L'instance de la connexion à la base de données.
     */
    public static Connection getInstance(){

        if(instance == null) {
            try {
            	mdp = getMaskedPasswordWithinEclipse("Password");
                instance = DriverManager.getConnection(
                    "jdbc:mysql://" + "mysql-1ba067f8-s3c01.e.aivencloud.com:24004/defaultdb?sslmode=require", 
                    username, mdp
                );
                System.out.println("Connected with the database successfully");
            } catch (SQLException e) {
                System.out.println("Error while connecting to the database");
                System.out.println(e.getClass()+" |SQL state :"+e.getSQLState()
				+"|SQL error code:"+e.getErrorCode()+"| -> " +e.getMessage());
                //e.printStackTrace();
            }
        }
        return instance; // Retourne l'instance de la connexion
    }

    /**
     * Méthode pour demander à l'utilisateur de saisir un mot de passe masqué.
     * Utilise un champ de texte sécurisé pour masquer le mot de passe dans l'interface graphique.
     * 
     * @param msg Le message à afficher dans la boîte de dialogue pour guider l'utilisateur.
     * @return Le mot de passe saisi par l'utilisateur.
     */
	public static String getMaskedPasswordWithinEclipse(String msg) {
    	final String password;
    	final JPasswordField jpf = new JPasswordField();
    	password = JOptionPane.showConfirmDialog(null, jpf, msg,
    			JOptionPane.OK_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ?
    					new String(jpf.getPassword()) : "";
    	return password;
    }
    
    
    public static void closeStatement(Statement statement) {
		if(statement!=null) {
			try {
				statement.close();
			}catch (Exception e) {
				ExceptionStorageHandler.LogException(e, instance);
			}
		}
	}
    
    /**
     * Méthode pour fermer la connexion à la base de données.
     * Elle vérifie d'abord si une connexion existe avant de tenter de la fermer.
     */
    public static void closeConnection() {
    	if (instance != null) {   // Vérifie si la connexion existe 		
    		try {
    			instance.close(); // Ferme la connexion à la base de données
    		} catch (SQLException e) {
    			// Gestion des erreurs lors de la fermeture de la connexion
    			ExceptionStorageHandler.LogException(e, instance);
    		}
    	}
    }
    
    
    public static void closeResult(ResultSet result) {
    	if (result != null) {
    		try {
				result.close();
			} catch (Exception e) {
				ExceptionStorageHandler.LogException(e, instance);
			}
    	}
    }

}
