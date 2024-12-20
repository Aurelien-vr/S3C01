package application;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.BienDAO;
import dao.DAOFactory;
import view.Page_principale;
import view.RoundedBorder;

public class Page_principaleController {
	private Page_principale view = new Page_principale();
	private BienDAO model = DAOFactory.createBienDAO();
	
	public Page_principaleController() {
		view.setVisible(true);
		
		List<List<String>> strings = model.BienStatus();
		for(List<String> cell: strings) {
			view.getContainer().add(Box.createRigidArea(new Dimension(0, 35)));
			cellContentCreation(view.getContainer(),cell);
		}
	}
	
	private void cellContentCreation(JPanel container, List<String> cellContent) {
		JPanel holderBienStatus = new JPanel();
		holderBienStatus.setLayout(new BoxLayout(holderBienStatus, BoxLayout.X_AXIS));
		holderBienStatus.setBorder(new RoundedBorder(20, 20, Color.BLACK));
		
		JPanel locationPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel versementPanel = new JPanel();
		JPanel mois = new JPanel();
		JPanel loyerRetard = new JPanel();
		
		String cityZipcode = cellContent.get(1) + " | " + cellContent.get(2) ;
		String firstLastName = cellContent.get(3) + " " + cellContent.get(4);
		
		view.createJpanelWithLabel(locationPanel,
				new JLabel(cellContent.get(0)),new JLabel(cityZipcode), new JLabel(firstLastName));
		
		view.createJpanelWithLabel(datePanel,
				new JLabel(cellContent.get(5)),new JLabel(cellContent.get(6)));
		
		view.createJpanelWithLabel(versementPanel,
				new JLabel(cellContent.get(7)));
		
		view.createJpanelWithLabel(mois,
				new JLabel("Mois de loyer en retard"),new JLabel(cellContent.get(8)));
		
		view.createJpanelWithLabel(loyerRetard,
				new JLabel("Loyer dû"),new JLabel(cellContent.get(9)));
		
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(locationPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(datePanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(versementPanel);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(mois);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));
		holderBienStatus.add(loyerRetard);
		holderBienStatus.add(Box.createRigidArea(new Dimension(50, 0)));

		container.add(holderBienStatus);
	}
}
