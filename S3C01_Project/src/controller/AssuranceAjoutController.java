package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.AssuranceDAO;
import dao.BienDAO;
import dao.DAOFactory;
import dao.entities.Assurance;
import dao.entities.Bien;
import utilities.ErrorMessage;
import view.AssuranceAjoutView;

public class AssuranceAjoutController extends TemplateAjoutController {

	private AssuranceAjoutView viewAssurance = new AssuranceAjoutView();
	private BienDAO modelBien = DAOFactory.createBienDAO();
	private AssuranceDAO modelAssurance = DAOFactory.createAssuranceDAO();
	private boolean errorRaise;
	private Map<String, Integer> idBienMap = new HashMap<>();
	private Date sqlDate;

	public AssuranceAjoutController() {
		super();
		viewAssurance.setTitleHeader("Assurance");
		populateIdBienComboBox();
		pressedValider();
		pressedAnnuler();
		logoLabel();
		addEventHandlers();
		viewAssurance.setVisible(true);
	}

	private void populateIdBienComboBox() {
		List<Bien> biens = modelBien.findAll();
		for (Bien bien : biens) {
			viewAssurance.getCbIdBien().addItem(bien.getAdresse());
			idBienMap.put(bien.getAdresse(), bien.getIdBien());
		}
	}

	private void pressedValider() {
		viewAssurance.getValiderButton().addActionListener(e -> {
			errorRaise = false;
			checkPrime();
			checkProtectionJuridique();
			checkIdBien();
			checkDate();
			if (!errorRaise) {
				createAssurance();
				new AssuranceController();
				viewAssurance.dispose();
			}
		});
	}

	private void pressedAnnuler() {
		viewAssurance.getAnnulerButton().addActionListener(e -> {
			new AssuranceController();
			viewAssurance.dispose();
		});
	}

	private void checkPrime() {
		if (errorRaise) {
			return;
		}
		String input = viewAssurance.getFieldPrime().getText();
		try {
			double prime = Double.parseDouble(input.replace(',', '.'));
			if (prime < 0) {
				ErrorMessage.errorDialog("La prime doit être supérieure à 0");
				errorRaise = true;
			}
		} catch (Exception e) {
			ErrorMessage.errorDialog("La prime doit être un nombre");
			errorRaise = true;
		}
	}

	private void checkProtectionJuridique() {
		if (errorRaise) {
			return;
		}
		String input = viewAssurance.getFieldProtectionJuridique().getText();
		if (input.isEmpty()) {
			ErrorMessage.errorDialog("La protection juridique ne peut pas être vide");
			errorRaise = true;
		}
	}

	private void checkIdBien() {
		if (errorRaise) {
			return;
		}
		String selectedIdBien = (String) viewAssurance.getCbIdBien().getSelectedItem();
		if (selectedIdBien == null || selectedIdBien.isEmpty()) {
			ErrorMessage.errorDialog("L'ID Bien ne peut pas être vide");
			errorRaise = true;
		}
	}

	private void checkDate() {
		if (errorRaise) {
			return;
		}
		String input = viewAssurance.getFieldDate().getText();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			java.util.Date parsedDate = dateFormat.parse(input);
			sqlDate = new java.sql.Date(parsedDate.getTime());
		} catch (ParseException e) {
			ErrorMessage.errorDialog("La date doit être au format jj/mm/aaaa");
			errorRaise = true;
		}
	}

	private void createAssurance() {
		try {
			BigDecimal prime = new BigDecimal(viewAssurance.getFieldPrime().getText().replace(',', '.'));
			BigDecimal protectionJuridique = new BigDecimal(viewAssurance.getFieldProtectionJuridique().getText());
			int selectedIdBien = idBienMap.get(viewAssurance.getCbIdBien().getSelectedItem());
			Date date = sqlDate;

			Assurance assurance = new Assurance(date, prime, protectionJuridique);
			modelAssurance.insert(assurance);
			modelAssurance.insertFK(selectedIdBien, assurance.getIdBien());
		} catch (NumberFormatException e) {
			ErrorMessage.errorDialog("Erreur lors de l'insertion des données dans la base de données");
		}
	}

	private void logoLabel() {
		viewAssurance.getLogoLabel().addActionListener(e -> {
			new HomeController();
			viewAssurance.dispose();
		});
	}

	private void addEventHandlers() {
		viewAssurance.getBtnBienLouable().addActionListener(e -> {
			new BienController();
			viewAssurance.dispose();
		});

		viewAssurance.getBtnLocataire().addActionListener(e -> {
			new LocataireController();
			viewAssurance.dispose();
		});

		viewAssurance.getBtnContratLocation().addActionListener(e -> {
			new ContratLocationController();
			viewAssurance.dispose();
		});

		viewAssurance.getItemAssurance().addActionListener(e -> {
			new AssuranceController();
			viewAssurance.dispose();
		});

		viewAssurance.getItemFacture().addActionListener(e -> {
			new FactureController();
			viewAssurance.dispose();
		});

		viewAssurance.getItemTravaux().addActionListener(e -> {
			new TravauxController();
			viewAssurance.dispose();
		});
		
        viewAssurance.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewAssurance.dispose();
        });
	}
}