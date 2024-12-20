package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import application.Page_principaleController;
import dbConnection.DatabaseConnection;
import view.ErrorMessage;
import view.Page_Coo;

public class Page_CooController {
	
	private Page_Coo view = new Page_Coo();
	
	public Page_CooController() {
		
		view.getConnectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectButtonPressed(view.getUsernameField(), view.getPasswordField());
			}
		});
		
		view.setVisible(true);
	}
	
    private void connectButtonPressed(JTextField username, JPasswordField password){ 	
    	if((username.getText().length() <= 1 || username.getText().length() > 30 ) || (password.getPassword().length <= 1 || password.getPassword().length > 40)) {
    		ErrorMessage.errorDialog("La longueur du nom d'utilisateur ou du mot de passe est incorrecte");
    		return;
    	}
    	DatabaseConnection.setUername(username.getText());
    	DatabaseConnection.setPassword(password.getPassword());
    	DatabaseConnection.getInstance();
    	if(!DatabaseConnection.connected) {
    		ErrorMessage.errorDialog("Nom d'utilisateur ou mot de passe incorrecte");
    	}else {
    		new Page_principaleController();
    		view.dispose();
      	}
    	
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Page_CooController());
    }
	
}
