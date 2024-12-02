package dbConnection;
import java.sql.*;
import javax.swing.*;

import exception.ExceptionStorageHandler;

public class DatabaseConnection {
	private static String username = "avnadmin";
	private static String mdp;
	
	private static Connection instance;

	private DatabaseConnection() {	}

	
	public static Connection getInstance(){
		mdp = getMaskedPasswordWithinEclipse("Password");
		if(instance == null) {
			try {
				instance = DriverManager.getConnection("jdbc:mysql://" + "mysql-1ba067f8-s3c01.e.aivencloud.com:24004/defaultdb?sslmode=require", username, mdp);
				System.out.println("Connected with the database successfully");
				
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
    
    
    public static void closeStatement(Statement statement) {
		if(statement!=null) {
			try {
				statement.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				ExceptionStorageHandler.LogException(e, instance);
			}
		}
	}
    
    public static void closeConnection() {
    	if (instance != null) {    		
    		try {
    			instance.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }

}
