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
	private String etatStrategie;
    @BeforeEach
	void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        pseudoModerateur = "mod1";
        pseudoUtilisateur = "uToAdd";
        pseudoMembre = "newMember";
        nomReseau = "r1";
		etatStrategie = "immédiat";
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


	/**
	 * @throws Exception lorsque pseudoUtilisateur (modérateur) est vide
	 */

	@Test
	void ajouterMembreTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("",pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

	/**
	 * @throws Exception lorsque pseudoUtilisateur (modérateur) est null
	 */

    @Test
	void ajouterMembreTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(null,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

	/**
	 * @throws Exception lorsque pseudoModérateur est vide
	 */

    @Test
	void ajouterMembreTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,null, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

	/**
	 * @throws Exception lorsque pseudoModérateur est null
	 */

    @Test
	void ajouterMembreTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,"", nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

	/**
	 * @throws Exception lorsque pseudoUtilisateur (à ajouter) est vide
	 */
	 @Test
	 void ajouterMembreTest3Jeu1() throws Exception {
		 Assertions.assertThrows(OperationImpossible.class,
				 () -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, null, pseudoMembre,etatStrategie));
	 }
	 /**
	  * @throws Exception lorsque pseudoUtilisateur (à ajouter) est null
	  */
	 @Test
	 void ajouterMembreTest3Jeu2() throws Exception {
		 Assertions.assertThrows(OperationImpossible.class,
				 () -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "", pseudoMembre,etatStrategie));
	 }

	/**
	* @throws Exception lorsque psdeudo nouveu membre est vide
	*/

	@Test
	void ajouterMembreTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, "",etatStrategie));
	}

	/**
	* @throws Exception lorsque psdeudo nouveu membre est bull
	*/

    @Test
	void ajouterMembreTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, null,etatStrategie));
	}



	/**
	 * @throws Exception lorsque nom Réseau est vide
	 */

	 @Test
	 void ajouterMembreTest5Jeu1() throws Exception {
		 Assertions.assertThrows(OperationImpossible.class,
				 () -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, null, pseudoUtilisateur, pseudoMembre,etatStrategie));
	 }
 
	 /**
	  * @throws Exception lorsque nom Réseau est null
	  */
 
	 @Test
	 void ajouterMembreTest5Jeu2() throws Exception {
		 Assertions.assertThrows(OperationImpossible.class,
				 () -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "", pseudoUtilisateur, pseudoMembre,etatStrategie));
	 }
 


    /**
     * @throws Exception lorsque réseau n'existe pas
     */

    @Test
	void ajouterMembreTest6Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "nonExisting", pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

    /**
     * @throws Exception lorsque le réseau est fermé
     */
    @Test
	void ajouterMembreTest6Jeu2() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).fermerReseau();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

    /**
     * @throws Exception lorsque le compte utilisateur(modérateur) n'est pas actif
     */
    @Test
	void ajouterMembreTest7Jeu1() throws Exception {
		miniSocs.getUtilisateurs().get(pseudoModerateur).desactiverCompte();
		Assertions.assertThrows(OperationImpossible.class,
		() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}
	/**
     * @throws Exception lorsque le compte utilisateur(modérateur) n'est pas actif
     */

    @Test
	void ajouterMembreTest7Jeu2() throws Exception {
		miniSocs.getUtilisateurs().get(pseudoModerateur).bloquerCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}
	

	/**
	 * @throws Exception Le compte utilisateur ne correspond pas au compte modérateur
	 */
	@Test
	void ajouterMembreTest7Jeu3() throws Exception {
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("someU", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("someU",pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}


	/**
	 * @throws Exception lorsque le comte utilisateur n'existe pas sur MiniSocs
	 */
	@Test
	void ajouterMembreTest7Jeu4() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("nonExisting",pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

    /**
     * @throws Exception lorsque l'utilisateur n'est pas un modérateur
     */
    @Test
	void ajouterMembreTest8Jeu1() throws Exception {
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("user3", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "user3" , "fraude",etatStrategie), "Ajout du membre a échoué");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("user3","fraude", nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

    /**
     * @throws Exception L'ajout se fait dans un réseau social non modéré par cet utilisateur (modérateur)
     */
    @Test
	void ajouterMembreTest8Jeu2() throws Exception {
        miniSocs.ajouterUtilisateur("newU", "u", "p", "u.p@su.fr");
        miniSocs.creerReseauSocial("newU", "newU", "newR");
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "newR", pseudoUtilisateur, pseudoMembre,etatStrategie));
	}


    /**
     * @throws Exception  lorsque Le compte utilisateur (à ajouter) existe
     */
    @Test
	void ajouterMembreTest9Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "newU", "newM",etatStrategie));
	}

    /**
     * @throws Exception lorsque le compte utilisateur (à ajouter) n'est pas ACTIF
     */
    @Test
	void ajouterMembreTest9Jeu2() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).desactiverCompte();
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}

	/**
     * @throws Exception lorsque le compte utilisateur (à ajouter) n'est pas ACTIF
     */

    @Test
	void ajouterMembreTest9Jeu3() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).bloquerCompte();
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
	}
	 
    /**
     * @throws Exception lorsque l'utilisateur (à ajouter) existe dans le réseau sous un autre pseudo
     */
    @Test
	void ajouterMembreTest9Jeu4() throws Exception {
        miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, "someMember",etatStrategie);
        Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, "someOtherMember",etatStrategie));
	}

    /**
     * @throws Exception lorsque le pseudo du nouveau membre existe déja dans le réseau
     */
    @Test
	void ajouterMembreTest10Jeu1() throws Exception {
        miniSocs.ajouterUtilisateur("newU", "u", "p", "u.p@su.fr");
        miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie);
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, "newU", pseudoMembre,etatStrategie));
	}

	/**
	 * @throws Exception etatStratégie différent de "immédiat" , "quotidien" et "pas de notifications"
	 */
	@Test
	void ajouterMembreTest11Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,"AnyThing"));
	}

    @Test
	void creerReseauTest12Jeu1() throws Exception {
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).getMembres().size());
		assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre,etatStrategie));
		Assertions.assertEquals(2, miniSocs.getReseauxSociaux().get(nomReseau).getMembres().size());
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).getMembres().get(pseudoMembre)!=null);
		Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).getMembres().get(pseudoMembre).estModerateur());
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudoUtilisateur).getMembres().get(pseudoMembre)!=null);
	}
}
