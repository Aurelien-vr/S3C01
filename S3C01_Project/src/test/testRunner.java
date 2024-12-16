package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	test_acte_cautionnement.class,
	test_assurance.class,
	test_avis_taxe_fonciere.class,
	test_bien.class,
	test_contrat_colocation.class,
	test_contrat_location.class,
    	test_declaration_revenu.class,
	test_etat_des_lieux.class,
	test_facture_eau.class,
	test_facture_electricite.class,
	test_facture_gaz.class,
	test_facture.class,
	test_locataire.class,
	test_regularisation_charges.class,
	test_solde_de_tout_compte.class,
	test_travaux.class
})
public class testRunner {}
