package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Page_informationsI{

	private JFrame frame;
	private JTextField textField_codeP;
	private JTextField textField_ville;
	private JTextField textField_adresse;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page_informationsI window = new Page_informationsI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Page_informationsI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JButton btn_retour = new JButton("Retour");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.dispose(); 
			}
		});
		header.add(btn_retour, BorderLayout.EAST);
		
		JLabel lbl_SpecificationAppartement = new JLabel("Spécification Immeuble");
		lbl_SpecificationAppartement.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SpecificationAppartement.setFont(new Font("Tahoma", Font.BOLD, 30));
		header.add(lbl_SpecificationAppartement, BorderLayout.CENTER);
		
		JPanel corps = new JPanel();
		frame.getContentPane().add(corps, BorderLayout.CENTER);
		corps.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbl_adresse = new JLabel("Adresse : ");
		lbl_adresse.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_adresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_adresse);
		
		textField_adresse = new JTextField();
		textField_adresse.setEditable(false);
		textField_adresse.setColumns(10);
		corps.add(textField_adresse);
		
		JLabel lbl_ville = new JLabel("Ville : ");
		lbl_ville.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ville.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_ville);
		
		textField_ville = new JTextField();
		textField_ville.setEditable(false);
		textField_ville.setColumns(10);
		corps.add(textField_ville);
		
		JLabel lbl_codeP = new JLabel("Code postal : ");
		lbl_codeP.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_codeP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_codeP);
		
		textField_codeP = new JTextField();
		textField_codeP.setEditable(false);
		textField_codeP.setColumns(10);
		corps.add(textField_codeP);
		
		JPanel panel_vide_enregistrer = new JPanel();
		corps.add(panel_vide_enregistrer);
		
		JButton btn_enregistrer = new JButton("Enregistrer ");
		btn_enregistrer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(btn_enregistrer);
	}

}
