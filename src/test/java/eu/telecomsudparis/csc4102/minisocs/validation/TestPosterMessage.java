
// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestPosterMessage {
    private MiniSocs miniSocs;
    private String contenu;
    private String nomReseau;
    private String pseudoMembre;
    private String pseudoModerateur;
    private String pseudoUtilisateur;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        contenu = "contenu1";
        nomReseau = "reseauX";
        pseudoModerateur = "mod1";
        pseudoUtilisateur = "uToAdd";
        pseudoMembre = "newMember";
        nomReseau = "r1";
		assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoModerateur, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoUtilisateur, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoModerateur, pseudoModerateur, nomReseau), "creation reseau social a  échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, nomReseau, pseudoUtilisateur, pseudoMembre), "ajout membre a échoué");    
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        contenu = null;
        nomReseau = null;
        pseudoModerateur = null;
        pseudoMembre = null;
    }
    
    @Test
    void posterMessageTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage("",contenu, "", nomReseau));
    }
    
    @Test
    void posterMessageTest1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(null,contenu, null, nomReseau));
    }


    @Test
    void posterMessageTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, "", nomReseau));
    }
    
    @Test
    void posterMessageTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, null, nomReseau));
    }
    @Test
    void posterMessageTest3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, ""));
    }

    @Test
    void posterMessageTest3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, null));      
    }

    @Test
    void posterMessageTest4Jeu1() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).desactiverCompte();
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, nomReseau));    
    }
    
    @Test
    void posterMessageTest4Jeu2() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoUtilisateur).bloquerCompte();
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, nomReseau));    
    }
    @Test
    void posterMessageTest4Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage("nonExisting",contenu, pseudoMembre, nomReseau));    
    }

    @Test
    void posterMessageTest4Jeu4() throws Exception {
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("otherU", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage("otherU",contenu, pseudoMembre, nomReseau));    
    }

    @Test
    void posterMessageTest5Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,"", pseudoMembre, nomReseau));    
    }
    
    void posterMessageTest5Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,null, pseudoMembre, nomReseau));    
    }  

    @Test
    void posterMessageTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, "noneExisting"));    
    }
    
    @Test
    void posterMessageTest6Jeu2() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).fermerReseau();
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, nomReseau));    
    }

    @Test
    void posterMessageTest7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage(pseudoUtilisateur,contenu, "noneExisting", nomReseau));    
    }
    
    @Test
    void posterMessageTest7Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoModerateur, pseudoModerateur, "newR"), "creation reseau social a  échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur("otherU", "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoModerateur,pseudoModerateur, "newR", "otherU", "otherM"), "ajout membre a échoué");     
        Assertions.assertThrows(OperationImpossible.class,
        () -> miniSocs.posterMessage("otherU",contenu,"otherM", nomReseau));    
    }
    
    @Test
    void PosterMessageTest8Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().isEmpty());         
        assertDoesNotThrow(() -> miniSocs.posterMessage(pseudoUtilisateur,contenu, pseudoMembre, nomReseau), "Poster message a échoué");
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().isEmpty());  
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("visible"));
    } 
    @Test
    void PosterMessageTest9Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().isEmpty());         
        assertDoesNotThrow(() -> miniSocs.posterMessage(pseudoModerateur,contenu, pseudoModerateur, nomReseau), "Poster message a échoué");
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().isEmpty());  
        Assertions.assertEquals(1, miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().size());
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("enAttente"));
        Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).listerMessages().get(0).contains("visible"));
    }
}
