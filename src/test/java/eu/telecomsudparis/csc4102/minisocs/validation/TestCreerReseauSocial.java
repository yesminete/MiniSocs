// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCreerReseauSocial {
	private MiniSocs miniSocs;
	private Utilisateur u;
	private String nomReseau;
	private String pseudonyme;
	private String pseudoMembre;

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		nomReseau = "reseau1";
		pseudonyme = "utilisateur1";
		pseudoMembre = "membre1";
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudonyme, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
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
				() -> miniSocs.creerReseauSocial(null, pseudoMembre,nomReseau));
	}

	@Test
	void creerReseauTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial("",  pseudoMembre, nomReseau));
	}

    @Test
    void creerReseauTest2Jeu1() throws Exception{
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, null, nomReseau));
    }

	@Test
    void creerReseauTest2Jeu2() throws Exception{
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, "", pseudoMembre));
    }

    @Test
    void creerReseauTest3Jeu1() throws Exception {
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, null));
    }

    @Test
    void creerReseauTest3Jeu2() throws Exception {
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, ""));
    }

	@Test
    void creerReseauTest4Jeu1() throws Exception {
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial("pseudonyme", pseudoMembre, nomReseau));
    }

	@Test
    void creerReseauTest5Jeu1() throws Exception {
		u.desactiverCompte();
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, nomReseau));
    }

	@Test
    void creerReseauTest5Jeu2() throws Exception {
		u.bloquerCompte();
        assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, nomReseau));
    }


	@Test
	void creerReseauTest6Jeu1() throws Exception {
		Assertions.assertTrue(miniSocs.listerReseaux().isEmpty());
		miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, nomReseau);
		Assertions.assertFalse(miniSocs.listerReseaux().isEmpty());
		Assertions.assertEquals(1, miniSocs.listerUtilisateurs().size());
		Assertions.assertTrue(miniSocs.listerReseaux().get(0).contains(nomReseau));
		Assertions.assertTrue(miniSocs.listerReseaux().get(0).contains("true"));
		Assertions.assertFalse(miniSocs.listerReseaux().get(0).contains("false"));
		Assertions.assertFalse(miniSocs.listerReseaux().get(0).contains(pseudoMembre));
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial(pseudonyme, pseudoMembre, nomReseau));
	}
}
