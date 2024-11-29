package dbConnection;
import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
	private static String username = "avnadmin";
	private static String mdp;
	
	private static Connection instance;

	private DatabaseConnection() {	}

	
	public static Connection getInstance(){
		mdp = getMaskedPasswordWithinEclipse("Password");
		if(instance != null) {
			try {
				instance = DriverManager.getConnection("jdbc:mysql://" + "mysql-1ba067f8-s3c01.e.aivencloud.com:24004/defaultdb?sslmode=require", username, mdp);
				System.out.println("Connected With the database successfully");
				
			} catch (SQLException e) {
				System.out.println("Error while connecting to the database");
			}
		}
		return instance;
	}
	
	
    public static String getMaskedPasswordWithinEclipse(String msg) {
    	final String password;
    	final JPasswordField jpf = new JPasswordField();
    	password = JOptionPane.showConfirmDialog(null, jpf, msg,
    			JOptionPane.OK_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ?
    					new String(jpf.getPassword()) : "";
    	return password;
    }
    
    
    public void closeConnection() {
    	if (instance != null) {    		
    		try {
    			instance.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public static void main(String[] args) {
		DatabaseConnection.getInstance();
	}

}
