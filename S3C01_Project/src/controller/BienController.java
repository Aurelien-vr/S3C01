package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.Contrat_locationDAO;
import dao.DAOFactory;
import dao.LocataireDAO;
import utilities.ErrorMessage;
import view.BienView;

public class BienController extends TemplateTableController{
	
	private BienView view = new BienView();
	private BienDAO modelBien = DAOFactory.createBienDAO();
	private Contrat_locationDAO modelContratLocation = DAOFactory.createContrat_locationDAO();
	private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
	private List<List<String>> listData;
	
	public BienController(){
        super();
        view.setTitleHeader("Bien");
        fillTable(); 
        view.setTableModel(modelTable, 0);
        actionDeleteButton();
        actionEditButton();
        openAjoutBienPage();
        logoLabel();
        addEventHandlers();
        view.setVisible(true);
    }
	
	
	
	private void logoLabel() {
	    view.getLogoLabel().addActionListener(e -> {
	        new HomeController();
	        view.dispose();
	    });
	}
	
	
	@Override
	void fillTable() {
		modelTable = new DefaultTableModel(new String[]{"Adresse", "Superfice", "Nombre de pièces", "Meublé", "Accesoire privé", "Accesoire commun", "Garage", "Contrat de location", "EDIT", "SUPPRIMER"}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
            	int columnCount = getColumnCount();
                return column == columnCount - 1 || column == columnCount - 2;
            }};
        
        listData = modelBien.procPageBien();
        
        for (int i = 0; i < listData.size(); i++) {
        	List<String> rowResult = listData.get(i);
        	
        	String adresse = rowResult.get(2) + "\n" + rowResult.get(3) + " | " + rowResult.get(4);
        	String superficie = rowResult.get(5);
        	String nbPiece = rowResult.get(6);
        	String meuble = Integer.parseInt(rowResult.get(7)) == 1 ? "Oui" : "Non";
        	String accesoirePrive = rowResult.get(8);
        	String accesoireCommun = rowResult.get(9);
        	String garage = Integer.parseInt(rowResult.get(11)) == 1 ? "Oui" : "Non";
        	String contLoc= "Unknown".equals(rowResult.get(10)) ? "Non" : "Oui";
        	
        	Object[] row = {adresse, superficie, nbPiece, meuble, accesoirePrive, accesoireCommun, garage, contLoc,"Edit contrat location" , "Delete"};
        	modelTable.addRow(row);
        }
	
	}
	
	private void actionDeleteButton() {
		view.getDeleteButton().addActionListener(e-> {
				int selectedRow = view.getTable().getSelectedRow();
				int idbBien = Integer.parseInt(listData.get(selectedRow).get(0));
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + idbBien);
				System.out.println(response);
				if (response == JOptionPane.YES_OPTION) {
				    modelBien.procDeletBienCascade(idbBien);
				    modelTable.setRowCount(0);
				    fillTable();
				    view.setTableModel(modelTable, 1);
				}
		});
	}
	
	private void actionEditButton() {
		view.getEditIdContratBien().addActionListener(e-> {
				int idCl;
				String value = listData.get(view.getTable().getSelectedRow()).get(10);
				if ("Unknown".equals(value)) {idCl = -1;} else {idCl = Integer.parseInt(value);}
				
				List<List<String>> dataLocaSansContrat = modelLocataire.procLocataireSansContrat();
				List<List<String>> dataClActif = modelContratLocation.procContratLocationDisponible();
				view.getLocataireComboBox().removeAllItems();
				view.getContratLocationComboBox().removeAllItems();
				populateCbBien(dataLocaSansContrat,dataClActif);
				
				
				view.fillEditClDialog(idCl);
				if(view.getLocataireComboBox().getSelectedItem() == null || view.getContratLocationComboBox().getSelectedItem() == null) {
					view.getModifierButton().setEnabled(false);
				}
	            actionAnnulerButton(idCl);
	            actionModifierBoutton(idCl);
	            actionNouveauCl();
	            actionNouveauLocataire();
		});
	}

	
    private void actionNouveauCl() {
    	view.getAjouterContratLocationButton().addActionListener(e ->{
    		new ContratLocationAjoutController();
    		view.getEditClDialog().dispose();
    	});
	}



	private void actionNouveauLocataire() {
		view.getAjouterLocataireButton().addActionListener(e ->{
	    	new LocataireAjoutController();
			view.getEditClDialog().dispose();

		});		
	}



	private void actionModifierBoutton(int idCl) {
    	view.getModifierButton().addActionListener(e -> {
            int newCl = Integer.parseInt((String) view.getContratLocationComboBox().getSelectedItem());
    		int newLocataire= Integer.parseInt((String) view.getContratLocationComboBox().getSelectedItem());
    		int result = ErrorMessage.confirmationDialog("Confirmez vous l'insertion du lien entre le bien, le contrat de location et le locataire ?");
            if(result == JOptionPane.YES_OPTION) {
            	modelContratLocation.procUpdateFkBienLocation(idCl, newCl, newLocataire);
            }
            
		});
		
	}

	private void actionAnnulerButton(int idCl) {
        view.getDeleteButtonDialog().addActionListener(e -> {
            int result = ErrorMessage.confirmationDialog("Confirmez vous la supression du lien entre le bien, le contrat de location et le locataire ?");
            if(result == JOptionPane.YES_OPTION) {
            	modelContratLocation.procRemoveFkBienLocation(idCl);
            }
        });}
   

	private void openAjoutBienPage() {
		view.getAjoutBienButton().addActionListener(e -> {
				new BienAjoutController();
				view.dispose();
		});
	}
	
	 private void addEventHandlers() {
        view.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	view.dispose();
        });

        view.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        });
        
        view.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	view.dispose();
        });
        
        view.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	view.dispose();
        });
        
        view.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	view.dispose();
        });
        
        view.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	view.dispose();
        });
        
        view.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	view.dispose();
        });
    }



	@Override
	void updateFooter() {
		return;
	}

	private void populateCbBien(List<List<String>> dataLocaSansContrat,List<List<String>> dataClActif) {
		
		for(List<String> list : dataLocaSansContrat) {
			view.getLocataireComboBox().addItem(list.get(0));
		}
		
		for(List<String> list : dataClActif) {
			view.getContratLocationComboBox().addItem(list.get(0));
		}
	}
	
}
