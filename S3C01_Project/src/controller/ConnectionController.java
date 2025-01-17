package controller;


import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db_connection.DatabaseConnection;
import utilities.ErrorMessage;
import view.ConnectionView;

public class ConnectionController extends TemplateHeaderController {
	
	private ConnectionView viewConnection = new ConnectionView();
	
	public ConnectionController() {
        super();

        viewConnection.getConnectButton().addActionListener(e -> 
            connectButtonPressed(viewConnection.getUsernameField(), viewConnection.getPasswordField())
        );
        viewConnection.setVisible(true);
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
    		new HomeController();
    		viewConnection.dispose();
      	}
    }
}
