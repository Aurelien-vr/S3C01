package controller_;

import view.Page_header;

public class Page_headerController {

    private Page_header viewHeader;
//    private DAO_header daoheader

    public Page_headerController() {
        initializeController();
    }

    private void initializeController() {
        viewHeader = new Page_header();
        viewHeader.setVisible(true);

        // Ajout des event handlers
        addEventHandlers();
    }

    private void addEventHandlers() {
        viewHeader.getBtnBienLouable().addActionListener(e -> {
            System.out.println("Bien louable cliqué");
        });

        viewHeader.getBtnLocataire().addActionListener(e -> {
            System.out.println("Locataire cliqué");
        });

        viewHeader.getBtnDocument().addActionListener(e -> {
            System.out.println("Document cliqué");
        });
    }

    public static void main(String[] args) {
        new Page_headerController();
    }
}
