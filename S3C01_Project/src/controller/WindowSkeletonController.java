package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dbConnection.DatabaseConnection;
import view.Page_principale;
import view.WindowSkeleton;

public class WindowSkeletonController {
	private WindowSkeleton view;
	
	public WindowSkeletonController(WindowSkeleton 	view) {
		this.view = view;
		
		
		this.view.getLogoLabel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DatabaseConnection.connected) {
					new Page_principale();
				}
			}
		});
		
	}
	
	
}
