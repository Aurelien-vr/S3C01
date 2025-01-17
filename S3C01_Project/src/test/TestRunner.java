package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestActeCautionnement.class,
	TestAssurance.class,
	TestAvisTaxeFonciere.class,
	TestBien.class,
	TestContratColocation.class,
	TestContratLocation.class,
    TestDeclarationRevenu.class,
	TestEtatDesLieux.class,
	TestFactureEau.class,
	TestFactureElectricite.class,
	TestFactureGaz.class,
	TestFacture.class,
	TestLocataire.class,
	TestRegularisationCharges.class,
	TestSoldeDeToutCompte.class,
	TestTravaux.class,
	TestAvancer.class,
	TestEnumerer.class
})
public class TestRunner {}
