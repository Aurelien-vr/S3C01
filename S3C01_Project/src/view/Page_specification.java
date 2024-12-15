package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Canvas;
import javax.swing.JEditorPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class Page_specification {

	private JFrame frame;
	private JTextField textField_adresse;
	private JTextField textField_numero;
	private JTextField textField_etage;
	private JTextField textField_superficie;
	private JTextField textField_meuble;
	private JTextField textField_garage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page_specification window = new Page_specification();
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
	public Page_specification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JButton btn_retour = new JButton("Retour");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // retour 
			}
		});
		header.add(btn_retour, BorderLayout.EAST);
		
		JLabel Lbl_SpecificationAppartement = new JLabel("Spécification Appartement");
		Lbl_SpecificationAppartement.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_SpecificationAppartement.setFont(new Font("Tahoma", Font.BOLD, 30));
		header.add(Lbl_SpecificationAppartement, BorderLayout.CENTER);
		
		JPanel corps = new JPanel();
		frame.getContentPane().add(corps, BorderLayout.CENTER);
		corps.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel gauche = new JPanel();
		corps.add(gauche);
		gauche.setLayout(new BorderLayout(0, 0));
		
		JEditorPane editorPane = new JEditorPane();
		gauche.add(editorPane, BorderLayout.CENTER);
		
		JPanel boutons = new JPanel();
		boutons.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gauche.add(boutons, BorderLayout.SOUTH);
		
		JButton btn_historiqueLocataire = new JButton("Historique de locataire");
		btn_historiqueLocataire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// aller sur page Page_historiqueLocataire
			}
		});
		boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		btn_historiqueLocataire.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boutons.add(btn_historiqueLocataire);
		
		JButton btn_documents = new JButton("Documents");
		btn_documents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aller sur page Page_documents
			}
		});
		btn_documents.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boutons.add(btn_documents);
		
		JButton btn_listeCharges = new JButton("Liste des charges");
		btn_listeCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aller sur page Page_listeCharges
			}
		});
		btn_listeCharges.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boutons.add(btn_listeCharges);
		
		JPanel droite = new JPanel();
		corps.add(droite);
		droite.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel Lbl_adresse = new JLabel("Adresse : ");
		Lbl_adresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_adresse.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_adresse);
		
		textField_adresse = new JTextField();
		textField_adresse.setEditable(false);
		droite.add(textField_adresse);
		textField_adresse.setColumns(10);
		
		JLabel Lbl_numero = new JLabel("Numéro : ");
		Lbl_numero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_numero.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_numero);
		
		textField_numero = new JTextField();
		textField_numero.setEditable(false);
		droite.add(textField_numero);
		textField_numero.setColumns(10);
		
		JLabel Lbl_etage = new JLabel("Etage : ");
		Lbl_etage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_etage.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_etage);
		
		textField_etage = new JTextField();
		textField_etage.setEditable(false);
		droite.add(textField_etage);
		textField_etage.setColumns(10);
		
		JLabel Lbl_superficie = new JLabel("Superficie : ");
		Lbl_superficie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_superficie.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_superficie);
		
		textField_superficie = new JTextField();
		textField_superficie.setEditable(false);
		droite.add(textField_superficie);
		textField_superficie.setColumns(10);
		
		JLabel Lbl_meuble = new JLabel("Meublé :");
		Lbl_meuble.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_meuble.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_meuble);
		
		textField_meuble = new JTextField();
		textField_meuble.setEditable(false);
		droite.add(textField_meuble);
		textField_meuble.setColumns(10);
		
		JButton btn_ListeMeubles = new JButton("Voir liste des meubles");
		btn_ListeMeubles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// aller page Page_listeMeubles
			}
		});
		btn_ListeMeubles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		droite.add(btn_ListeMeubles);
		
		JPanel panel_vide = new JPanel();
		droite.add(panel_vide);
		
		JLabel Lbl_garage = new JLabel("Garage : ");
		Lbl_garage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Lbl_garage.setHorizontalAlignment(SwingConstants.CENTER);
		droite.add(Lbl_garage);
		
		textField_garage = new JTextField();
		textField_garage.setEditable(false);
		droite.add(textField_garage);
		textField_garage.setColumns(10);
	}

}
