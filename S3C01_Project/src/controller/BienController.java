package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.ContratLocationDAO;
import dao.DAOFactory;
import dao.LocataireDAO;
import utilities.ErrorMessage;
import view.BienView;

public class BienController extends TemplateTableController{
	
	private BienView viewBein = new BienView();
	private BienDAO modelBien = DAOFactory.createBienDAO();
	private ContratLocationDAO modelContratLocation = DAOFactory.createContratLocationDAO();
	private LocataireDAO modelLocataire = DAOFactory.createLocataireDAO();
	private List<List<String>> listData;
	
	public BienController(){
        super();
        viewBein.setTitleHeader("Bien");
        fillTable(); 
        viewBein.setTableModel(modelTable, 0);
        actionDeleteButton();
        actionEditButton();
        openAjoutBienPage();
        logoLabel();
        addEventHandlers();
        viewBein.setVisible(true);
    }
	
	
	
	private void logoLabel() {
	    viewBein.getLogoLabel().addActionListener(e -> {
	        new HomeController();
	        viewBein.dispose();
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
		viewBein.getDeleteButton().addActionListener(e-> {
				int selectedRow = viewBein.getTable().getSelectedRow();
				int idbBien = Integer.parseInt(listData.get(selectedRow).get(0));
				int response = ErrorMessage.confirmationDialog("Souhaitez vous confirmer la supression du travaux: " + idbBien);
				if (response == JOptionPane.YES_OPTION) {
				    modelBien.procDeletBienCascade(idbBien);
				    modelTable.setRowCount(0);
				    fillTable();
				    viewBein.setTableModel(modelTable, 1);
				}
		});
	}
	
	private void actionEditButton() {
		viewBein.getEditIdContratBien().addActionListener(e-> {
				int idCl;
				String value = listData.get(viewBein.getTable().getSelectedRow()).get(10);
				if ("Unknown".equals(value)) {idCl = -1;} else {idCl = Integer.parseInt(value);}
				
				List<List<String>> dataLocaSansContrat = modelLocataire.procLocataireSansContrat();
				List<List<String>> dataClActif = modelContratLocation.procContratLocationDisponible();
				viewBein.getLocataireComboBox().removeAllItems();
				viewBein.getContratLocationComboBox().removeAllItems();
				populateCbBien(dataLocaSansContrat,dataClActif);
				
				
				viewBein.fillEditClDialog(idCl);
				if(viewBein.getLocataireComboBox().getSelectedItem() == null || viewBein.getContratLocationComboBox().getSelectedItem() == null) {
					viewBein.getModifierButton().setEnabled(false);
				}
	            actionAnnulerButton(idCl);
	            actionModifierBoutton(idCl);
	            actionNouveauCl();
	            actionNouveauLocataire();
		});
	}

	
    private void actionNouveauCl() {
    	viewBein.getAjouterContratLocationButton().addActionListener(e ->{
    		new ContratLocationAjoutController();
    		viewBein.getEditClDialog().dispose();
    	});
	}



	private void actionNouveauLocataire() {
		viewBein.getAjouterLocataireButton().addActionListener(e ->{
	    	new LocataireAjoutController();
			viewBein.getEditClDialog().dispose();

		});		
	}



	private void actionModifierBoutton(int idCl) {
    	viewBein.getModifierButton().addActionListener(e -> {
            int newCl = Integer.parseInt((String) viewBein.getContratLocationComboBox().getSelectedItem());
    		int newLocataire= Integer.parseInt((String) viewBein.getContratLocationComboBox().getSelectedItem());
    		int result = ErrorMessage.confirmationDialog("Confirmez vous l'insertion du lien entre le bien, le contrat de location et le locataire ?");
            if(result == JOptionPane.YES_OPTION) {
            	modelContratLocation.procUpdateFkBienLocation(idCl, newCl, newLocataire);
            }
            
		});
		
	}

	private void actionAnnulerButton(int idCl) {
        viewBein.getDeleteButtonDialog().addActionListener(e -> {
            int result = ErrorMessage.confirmationDialog("Confirmez vous la supression du lien entre le bien, le contrat de location et le locataire ?");
            if(result == JOptionPane.YES_OPTION) {
            	modelContratLocation.procRemoveFkBienLocation(idCl);
            }
        });}
   

	private void openAjoutBienPage() {
		viewBein.getAjoutBienButton().addActionListener(e -> {
				new BienAjoutController();
				viewBein.dispose();
		});
	}
	
	 private void addEventHandlers() {
        viewBein.getBtnBienLouable().addActionListener(e -> {
        	new BienController();
        	viewBein.dispose();
        });

        viewBein.getBtnLocataire().addActionListener(e -> {
        	new LocataireController();
        	viewBein.dispose();
        });
        
        viewBein.getBtnContratLocation().addActionListener(e -> {
        	new ContratLocationController();
        	viewBein.dispose();
        });
        
        viewBein.getItemAssurance().addActionListener(e -> {
        	new AssuranceController();
        	viewBein.dispose();
        });
        
        viewBein.getItemFacture().addActionListener(e -> {
        	new FactureController();
        	viewBein.dispose();
        });
        
        viewBein.getItemTravaux().addActionListener(e -> {
        	new TravauxController();
        	viewBein.dispose();
        });
        
        viewBein.getItemCharge().addActionListener(e -> {
        	new ChargeController();
        	viewBein.dispose();
        });
    }



	@Override
	void updateFooter() {
		// NO footer for this page
	}

	private void populateCbBien(List<List<String>> dataLocaSansContrat,List<List<String>> dataClActif) {
		
		for(List<String> list : dataLocaSansContrat) {
			viewBein.getLocataireComboBox().addItem(list.get(0));
		}
		
		for(List<String> list : dataClActif) {
			viewBein.getContratLocationComboBox().addItem(list.get(0));
		}
	}
	
}
