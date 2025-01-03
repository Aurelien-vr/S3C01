package dbConnection;

import java.sql.*;
import javax.swing.*;

import exception.ExceptionStorageHandler;
public class DatabaseConnection {
  
    private static String username;
    private static String password;
    private static Connection instance;

    /**
     * Constructeur privé pour empêcher l'instanciation directe de cette classe.
     * Utilise le pattern Singleton pour garantir qu'il n'y ait qu'une seule connexion à la fois.
     */
    private DatabaseConnection() { }
    public static boolean connected = false;

    /**
     * Méthode singelton pour récupérer l'instance unique de la connexion à la base de données.
     * Si la connexion n'existe pas encore, elle est créée.
     * 
     * @return L'instance de la connexion à la base de données.
     */
    public static Connection getInstance(){
        // Demande le mot de passe masqué à l'utilisateur dans un champ de saisie sécurisé
        
        // Si l'instance n'existe pas encore, on crée la connexion
        if(instance == null) {
            try {
                instance = DriverManager.getConnection(
                    "jdbc:mysql://" + "mysql-1ba067f8-s3c01.e.aivencloud.com:24004/defaultdb?sslmode=require", 
                    username, password
                );
                System.out.println("Connected with the database successfully");
                DatabaseConnection.connected = true;
            } catch (SQLException e) {
                // Gestion des erreurs de connexion
                System.out.println("Error while connecting to the database");
                System.out.println(e.getClass()+" |SQL state :"+e.getSQLState()
				+"|SQL error code:"+e.getErrorCode()+"| -> " +e.getMessage());
            }
        }
        return instance;
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
        final JPasswordField jpf = new JPasswordField(); // Création du champ de mot de passe
        // Affiche une boîte de dialogue pour demander le mot de passe
        password = JOptionPane.showConfirmDialog(
            null, 
            jpf, 
            msg,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        ) == JOptionPane.OK_OPTION ? new String(jpf.getPassword()) : ""; // Si OK, récupère le mot de passe
        return password; // Retourne le mot de passe
    }
	
	public static void setUername(String usernameIN) {
		username  = usernameIN;
	}
	
	public static void setPassword(char[] tabPassword) {
		password = new String(tabPassword);
	}
	
    
    /**
     * Méthode pour fermer la connexion à la base de données.
     * Elle vérifie d'abord si une connexion existe avant de tenter de la fermer.
     */
    public static void closeConnection() {
    	if (instance != null) {    		
    		try {
    			instance.close(); // Ferme la connexion à la base de données
    			DatabaseConnection.connected = false;
    		} catch (SQLException e) {
    			ExceptionStorageHandler.LogException(e, instance);
    		}
    	}
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
