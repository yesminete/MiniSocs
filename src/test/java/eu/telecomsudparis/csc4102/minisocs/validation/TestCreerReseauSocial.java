// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCreerReseauSocial {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudonyme;
	private String pseudoMembre;

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		nomReseau = "reseau1";
		pseudonyme = "utilisateur1";
		pseudoMembre = "membre1";
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		nomReseau = null;
		pseudonyme = null;
		pseudoMembre = null;
	}

	@Test
	void creerReseauTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(null, pseudonyme, pseudoMembre));
	}

	@Test
	void creerReseauTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau("", pseudonyme, pseudoMembre));
	}

	
	@Test
	void creerReseauTest2Jeu1() throws Exception {
	    Assertions.assertTrue(miniSocs.listerReseaux().isEmpty());
	    miniSocs.creerReseau(nomReseau, pseudonyme, pseudoMembre);
	    Assertions.assertFalse(miniSocs.listerReseaux().isEmpty());
	    Assertions.assertEquals(1, miniSocs.listerReseaux().size());
	    ReseauSocial reseauCree = miniSocs.getReseau(nomReseau);
	    Assertions.assertNotNull(reseauCree);
	    Assertions.assertEquals(nomReseau, reseauCree.getNomRéseau());
	    Assertions.assertTrue(reseauCree.getEstOuvert());
	    Assertions.assertThrows(OperationImpossible.class,
	            () -> miniSocs.creerReseau(nomReseau, pseudonyme, pseudoMembre));
	}


	@Test
	void creerReseauTest3Jeu1() throws Exception {
		Assertions.assertFalse(miniSocs.listerUtilisateurs().isEmpty());
		Assertions.assertEquals(1, miniSocs.listerUtilisateurs().size());
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(pseudonyme));
		Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.BLOQUE.toString()));
		Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.DESACTIVE.toString()));
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomReseau, pseudonyme, pseudoMembre));
	}
	
	@Test
	void creerReseauTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomReseau, null, pseudoMembre));
	}
	
	@Test
	void creerReseauTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomReseau, "", pseudoMembre));
	}
	
	@Test
	void creerReseauTest5Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomReseau, pseudonyme, null));
	}
	
	@Test
	void creerReseauTest5Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseau(nomReseau, pseudonyme, ""));
	}
	
}
