package controller_;

import view.Page_sousHeader;

public class Page_sousHeaderController {

    private Page_sousHeader viewSousHeader;

    public Page_sousHeaderController() {
        initializeController();
    }

    private void initializeController() {
        // Instancier la vue
        viewSousHeader = new Page_sousHeader();
        viewSousHeader.setVisible(true); // Afficher la fenêtre

        // Ajoute les gestionnaires d'événements
        addEventHandlers();
    }

    private void addEventHandlers() {
        viewSousHeader.getBtnAjoutBien().addActionListener(e -> {
            System.out.println("Bouton 'Ajouter un bien' cliqué !");
        });
    }

    // Point d'entrée
    public static void main(String[] args) {
        new Page_sousHeaderController(); // Instancier le contrôleur
    }
}
