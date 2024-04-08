package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestAjouterMembre {
    private MiniSocs miniSocs;
    private String pseudoModerateur;
    private String pseudoUtilisateur;
    private String pseudoMembre;
    private String nomReseau;
    @BeforeEach
	void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        pseudoModerateur = "mod1";
        pseudoUtilisateur = "uToAdd";
        pseudoMembre = "newMember";
        nomReseau = "r1";
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoModerateur, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoUtilisateur, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoModerateur, pseudoModerateur, nomReseau), "creation reseau social a  échoué");

	}

	@AfterEach
	void tearDown() {
        miniSocs = null;
        pseudoModerateur = null;
        pseudoUtilisateur = null;
        pseudoMembre = null;
        nomReseau = null;
	}

    // 	public void ajouterMembre(final String pseudoModerateur, final String nomReseau, final String pseudoUtilisateur, final String pseudoMembre) 

	@Test
	void ajouterMembreTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("",pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(null,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,null, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,"", nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, null, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "", pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, null, pseudoMembre));
	}

    @Test
	void ajouterMembreTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "", pseudoMembre));
	}

    @Test
	void ajouterMembreTest5Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, ""));
	}

    @Test
	void ajouterMembreTest5Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, null));
	}

    @Test
	void ajouterMembreTest6Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "nonExisting", pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest6Jeu2() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).fermerReseau();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest7Jeu1() throws Exception {
		miniSocs.getUtilisateurs().get(pseudoModerateur).desactiverCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest7Jeu2() throws Exception {
		miniSocs.getUtilisateurs().get(pseudoModerateur).bloquerCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

	@Test
	void ajouterMembreTest7Jeu3() throws Exception {
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("someU", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("someU",pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest8Jeu1() throws Exception {
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("user3", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "user3" , "fraude"), "Ajout du membre a échoué");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("user3","fraude", nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest8Jeu2() throws Exception {
        miniSocs.ajouterUtilisateur("newU", "u", "p", "u.p@su.fr");
        miniSocs.creerReseauSocial("newU", "newU", "newR");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "newR", pseudoUtilisateur, pseudoMembre));
	}


    @Test
	void ajouterMembreTest9Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "newU", "newM"));
	}

    @Test
	void ajouterMembreTest9Jeu2() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).desactiverCompte();
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	}

    @Test
	void ajouterMembreTest9Jeu3() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).bloquerCompte();
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre));
	} 
    @Test
	void ajouterMembreTest9Jeu4() throws Exception {
        miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, "someMember");
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, "someOtherMember"));
	}

    @Test
	void ajouterMembreTest10Jeu1() throws Exception {
        miniSocs.ajouterUtilisateur("newU", "u", "p", "u.p@su.fr");
        miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre);
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "newU", pseudoMembre));
	}
    @Test
	void creerReseauTest11Jeu1() throws Exception {
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).getMembres().size());
		miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre);
		Assertions.assertEquals(2, miniSocs.getReseauxSociaux().get(nomReseau).getMembres().size());
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).getMembres().get(pseudoMembre)!=null);
		Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).getMembres().get(pseudoMembre).estModerateur());
	}
}
