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

public class Page_informationsG{

	private JFrame frame;
	private JTextField textField_loyer;
	private JTextField textField_numero;
	private JTextField textField_adresse;
	private JTextField textField_superficie;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page_informationsG window = new Page_informationsG();
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
	public Page_informationsG() {
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
		
		JLabel lbl_SpecificationAppartement = new JLabel("Spécification Garage");
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
		
		JLabel lbl_numero = new JLabel("Numéro : ");
		lbl_numero.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_numero);
		
		textField_numero = new JTextField();
		textField_numero.setEditable(false);
		textField_numero.setColumns(10);
		corps.add(textField_numero);
		
		JLabel lbl_superficie = new JLabel("Superficie : ");
		lbl_superficie.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_superficie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_superficie);
		
		textField_superficie = new JTextField();
		textField_superficie.setEditable(false);
		textField_superficie.setColumns(10);
		corps.add(textField_superficie);
		
		JLabel lbl_loyer = new JLabel("Loyer :");
		lbl_loyer.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_loyer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(lbl_loyer);
		
		textField_loyer = new JTextField();
		textField_loyer.setEditable(false);
		textField_loyer.setColumns(10);
		corps.add(textField_loyer);
		
		JPanel panel_vide_enregistrer = new JPanel();
		corps.add(panel_vide_enregistrer);
		
		JButton btn_enregistrer = new JButton("Enregistrer ");
		btn_enregistrer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		corps.add(btn_enregistrer);
	}

}
