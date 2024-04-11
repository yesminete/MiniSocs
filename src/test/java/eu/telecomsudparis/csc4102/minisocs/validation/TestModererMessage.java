// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestModererMessage {
    
    private MiniSocs miniSocs;
    private String contenu;
    private String pseudoMod;
    private String pseudoMembre;
    private String nomReseau;
    private String etatStrategie;
    private Long  idMessage;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        contenu = "contenu1";
        pseudoMembre = "membre";
        pseudoMod = "mod";
        nomReseau = "monReseau";
        etatStrategie = "immédiat";
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMod, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMembre, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMod,pseudoMod,nomReseau), "Creation reseau a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoMod,pseudoMod,nomReseau,pseudoMembre,pseudoMembre,etatStrategie),"Ajout membre a échoué");
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
        etatStrategie = null;
    }
    
    
    @Test
    void modererMessageTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage("",pseudoMod, nomReseau, idMessage,true));
    }

    @Test
    void modererMessageTest1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage(null,pseudoMod, nomReseau, idMessage,true));
    }

    @Test
    void modererMessageTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage(pseudoMod,"", nomReseau, idMessage,true));
    }

    @Test
    void modererMessageTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.modereMessage(pseudoMod,null, nomReseau, idMessage,true));
    }
    
    @Test
    void  modererMessageTest3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,null, idMessage, true));
    }

    @Test
    void  modererMessageTest3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,"", idMessage, true));
    }

    @Test
    void  modererMessageTest4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, null, true));
    }
   

    @Test
    void  modererMessageTest5Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage("nonExisting",pseudoMod,nomReseau, idMessage, true));
    }
    @Test
    void  modererMessageTest5Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.desactiverCompteUtilisateur(pseudoMod),"");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));
    }

    @Test
    void  modererMessageTest5Jeu3() throws Exception {
        assertDoesNotThrow(() -> miniSocs.bloquerCompteUtilisateur(pseudoMod),"");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));
    }

    @Test
    void  modererMessageTest5Jeu4() throws Exception {
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("newU","n","p","n.p@mail.fr"),"Ajout utilisateur a échoué");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage("newU",pseudoMod,nomReseau, idMessage, true));
    }
    
    @Test
    void  modererMessageTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMembre,pseudoMembre,nomReseau, idMessage, true));
    }

    @Test
    void  modererMessageTest6Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMembre, pseudoMembre, "newR"),"Création réseau a échoué");    
        assertDoesNotThrow(() -> idMessage = miniSocs.posterMessage(pseudoMembre,contenu,pseudoMembre,"newR"),"Poster Message ne fonctionne pas");
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,"newR", idMessage, true));
    }
    
    

    
    @Test
    void  modererMessageTest7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,"nonExisting", idMessage, true));
    }

    @Test
    void  modererMessageTest7Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.fermerReseau(pseudoMod, pseudoMod, nomReseau),"fermer réseau a échoué");    
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));
    }
    
    @Test
    void  modererMessageTest8Jeu1() throws Exception {
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMod, pseudoMod, "newR"),"Création réseau a échoué");   
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoMod,pseudoMod,"newR",pseudoMembre,pseudoMembre,etatStrategie),"Ajout membre a échoué"); 
        assertDoesNotThrow(() -> idMessage = miniSocs.posterMessage(pseudoMembre,contenu,pseudoMembre,"newR"),"Poster Message ne fonctionne pas");
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));
    }

    @Test
    void  modererMessageTest8Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, 526369l, false));
    }


    
    @Test
    void  modererMessageTest9Jeu1() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).getMessages().get(idMessage).setEtatMessage(EtatMessage.VISIBLE);
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));
    }

    @Test
    void  modererMessageTest9Jeu2() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).getMessages().get(idMessage).setEtatMessage(EtatMessage.CACHÉ);
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));
    }

    @Test
    void  modererMessageTest9Jeu3() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).getMessages().get(idMessage).setEtatMessage(EtatMessage.REJETE);
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));
    }

    @Test
	void  modererMessageTest10Jeu1() throws Exception {
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        assertDoesNotThrow(() -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, true));    
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("visible"));
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
	}

    @Test
	void  modererMessageTest11Jeu2() throws Exception {
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        assertDoesNotThrow(() -> miniSocs.modereMessage(pseudoMod,pseudoMod,nomReseau, idMessage, false));    
		Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("rejeté"));
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
		Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
	}
}

