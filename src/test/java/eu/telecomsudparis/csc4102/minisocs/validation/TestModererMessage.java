// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestModererMessage {
    
    private MiniSocs miniSocs;
    private String contenu;
    private String pseudoMod;
    private String pseudoMembre;
    private String nomReseau;
    private Long  idMessage;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        contenu = "contenu1";
        pseudoMembre = "membre";
        pseudoMod = "mod";
        nomReseau = "monReseau";
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMod, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMembre, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMod,pseudoMod,nomReseau), "Creation reseau a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoMod,pseudoMod,nomReseau,pseudoMembre,pseudoMembre),"Ajout membre a échoué");
        assertDoesNotThrow(() -> idMessage = miniSocs.posterMessage(pseudoMembre,contenu,pseudoMembre,nomReseau),"Poster Message ne fonctionne pas");
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        contenu = null;
        pseudoMembre = null;
        pseudoMod = null;
        nomReseau =null;
        idMessage = null;

    }
    
    
    //si aucun message ayant un contenu "contenu1" n'est trouvé alors une exception !
    @Test
    void modererMessageTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage(pseudoMod,"", nomReseau, idMessage,true));
    }

    @Test
    void modererMessageTest1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage(pseudoMod,null, nomReseau, idMessage,true));
    }
    
    //tester le idMessage non null 
    @Test
    void  modererMessageTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,null, idMessage, true));
    }

    @Test
    void  modererMessageTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,"", idMessage, true));
    }

    @Test
    void  modererMessageTest3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,"", null, true));
    }
   
    
    
    //tester l'état message en attente 
    @Test
    void  modererMessageTest5Jeu1() throws Exception {
        assertDoesNotThrow(() -> miniSocs.desactiverCompteUtilisateur(pseudoMod),"");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, 0l, true));
    }

    @Test
    void  modererMessageTest5Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.bloquerCompteUtilisateur(pseudoMod),"");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, 0l, true));
    }

    
    @Test
    void  modererMessageTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,"mohsen",nomReseau, idMessage, true));
    }
    
    
    @Test
    void  modererMessageTest6Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.bloquerCompteUtilisateur(pseudoMod),"");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMembre,nomReseau, idMessage, true));
    }
    
    //tester si le pseudoMembre non null et non vide 
    @Test
    void  modererMessageTest7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, 5l, true));
    }
    
    // Tester si l'utilisateur est modérateur du réseau social 
    @Test
    void  modererMessageTest8Jeu1() throws Exception {
        assertDoesNotThrow(() -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));
    }

    @Test
	void  modererMessageTest9Jeu1() throws Exception {
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        assertDoesNotThrow(() -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));    
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("visible"));
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
	}

    @Test
	void  modererMessageTest9Jeu2() throws Exception {
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        assertDoesNotThrow(() -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));    
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("rejeté"));
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
	}
}

