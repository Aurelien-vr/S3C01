package controller_;

import view.Page_header;

public class Page_headerController {

    private Page_header Page_header;
//    private DAO_header daoheader

    public Page_headerController() {
        initializeController();
    }

    private void initializeController() {
    	Page_header = new Page_header();
    	Page_header.setVisible(true);

        // Ajout des event handlers
        addEventHandlers();
    }

    private void addEventHandlers() {
    	Page_header.getBtnBienLouable().addActionListener(e -> {
            System.out.println("Bien louable cliqué");
        });

    	Page_header.getBtnLocataire().addActionListener(e -> {
            System.out.println("Locataire cliqué");
        });

    	Page_header.getBtnImmeuble().addActionListener(e -> {
            System.out.println("Immeuble cliqué");
        });

    	Page_header.getBtnDocument().addActionListener(e -> {
            System.out.println("Document cliqué");
        });
    }

    public static void main(String[] args) {
        new Page_headerController();
    }
}
