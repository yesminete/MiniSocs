// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestPosterMessage {
	private MiniSocs miniSocs;
	private Utilisateur utilisateur;
	private String contenu;
	private String nomReseau;
	private String pseudonyme;
	private String pseudoMembre;

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		utilisateur = new Utilisateur("userX","nomX", "prenomX", "courrierlX");
		nomReseau = "reseauX";
		pseudonyme = "userX";
		pseudo_membre = "membreX";
		
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		utilisateur=null;
		nomReseau = null;
		pseudonyme = null;
		pseudo_membre = null;
	}
	
	
    //si aucun réseau de ce nom n'existe pas dans la liste des réseau existants alors exception !
	@Test
	void PosterMessageTest1Jeu1() throws Exception {
		
	    Assertions.assertTrue(miniSocs.listerReseaux().stream().noneMatch(reseau -> reseau.contains(nomReseau)));
	     Assertions.assertThrows(OperationImpossible.class,
	               () -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, nomReseau));
	}
	
	//tester le nom réseau s'il est non null et non vide 
	@Test
	void PosterMessageTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, null));
	}

	@Test
	void PosterMessageTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, ""));
	}

	//Ici si on trouve que le réseau danslequel on veut poster le message et ayant un nom nomReseau ayant un état différent de ouvert (nonMatch) alors exception s'eleve
	@Test
	void PosterMessageTest3Jeu1() throws Exception {

	    Assertions.assertTrue(miniSocs.listerReseaux().stream().noneMatch(reseau -> reseau.contains(nomReseau) && reseau.contains(EtatRéseau.OUVERT.toString())));
	    Assertions.assertThrows(OperationImpossible.class,
	         () -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, nomReseau));
	}
	
	
	// si aucun utilisateur ayant un pseudo "pseudonyme" n'est trouvé alors une exception s'éleve
	@Test
	void PosterMessageTest4Jeu1() throws Exception {
		Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme)));
	     Assertions.assertThrows(OperationImpossible.class,
	         () -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, nomReseau));
	}
	
	//si l'utilisateur avec le pseudonyme "pseudonyme" n'a pas un compte actif alors exception ! 
	
	@Test
	void PosterMessageTest5Jeu1() throws Exception {
		Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme) && utilisateur.contains(EtatCompte.ACTIF.toString())));
	    Assertions.assertThrows(OperationImpossible.class,
	         () -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, nomReseau));
	}
	
	//Tester si le pseudonyme non null et non vide 
	
	@Test
	void PosterMessageTest6Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, null , pseudo_membre, nomReseau));
	}
	
	@Test
	void PosterMessageTest6Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, "" , pseudo_membre, nomReseau));
	}
	
	//tester si le pseudoMembre non null et non vide 
	@Test
	void PosterMessageTest7Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, pseudonyme , null, nomReseau));
	}
	
	@Test
	void PosterMessageTest7Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.PosterMessage(contenu, pseudonyme , "", nomReseau));
	}
	
	// Tester si utilisateur est membre dans d'autre terme si le pseudoMembre existe donc si la methode 
	//posterMessage prend cet argument pseudoMembre qui est inexistant une exception est levée. 
	@Test
	void PosterMessageTest8Jeu1() throws Exception {
		Assertions.assertTrue(utilisateur.listerMembres().stream().noneMatch(membres -> membres.contains(pseudo_membre)));
	    Assertions.assertThrows(OperationImpossible.class,
	         () -> miniSocs.PosterMessage(contenu, pseudonyme, pseudo_membre, nomReseau));
	}
	//Tester si le contenu non null et non vide 
	
		@Test
		void PosterMessageTest9Jeu1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> miniSocs.PosterMessage(null, pseudonyme, pseudo_membre, nomReseau));
		}
		
		@Test
		void PosterMessageTest9Jeu2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> miniSocs.PosterMessage("", pseudonyme , pseudo_membre, nomReseau));
		}
	
	
	
	
}
