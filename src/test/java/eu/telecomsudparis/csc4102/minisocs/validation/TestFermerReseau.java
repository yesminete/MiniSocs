// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestFermerReseau {
    
    private MiniSocs miniSocs;
    private String nomReseau;
    private String pseudoMod;
    private String pseudoMembre;
    private String etatStrategie;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        pseudoMembre = "membre";
        pseudoMod = "mod";
        nomReseau = "monReseau";
        etatStrategie = "immédiat";
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMod, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterUtilisateur(pseudoMembre, "n", "p", "nom.pren@som.fr"), "Ajout de l'utilisateur a échoué");
		assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMod,pseudoMod,nomReseau), "Creation reseau a échoué");
        assertDoesNotThrow(() -> miniSocs.ajouterMembre(pseudoMod,pseudoMod,nomReseau,pseudoMembre,pseudoMembre,etatStrategie),"Ajout membre a échoué");  
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        nomReseau = null;
        pseudoMembre = null;
        pseudoMembre = null;
    }
    
    @Test
    void fermerReseauTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( "",pseudoMod, nomReseau));
    }
     
    //pseudonyme non null et non vide  
    
    @Test
    void fermerReseauTest1Jeu2() throws Exception {
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( null,pseudoMod, nomReseau));
    }
        
        
    @Test
    void fermerReseauTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,"", nomReseau));
    }
    
    @Test
    void fermerReseauTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,null, nomReseau));
    }

    @Test
    void fermerReseauTest3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod, ""));
    }

        
    @Test
    void fermerReseauTest3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod, null));
    }

    @Test
    void fermerReseauTest4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod, "nonExisting"));
    }

    @Test
    void fermerReseauTest4Jeu2() throws Exception {
        miniSocs.getReseauxSociaux().get(nomReseau).fermerReseau();
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod, nomReseau));
    }
    // utilisateur existe et son compte est actif
    @Test
    void fermerReseauTest5Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( "nonExisting",pseudoMod,nomReseau ));
    }

    @Test
    void fermerReseauTest5Jeu2() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoMod).desactiverCompte();;
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod,nomReseau ));
    }
    @Test
    void fermerReseauTest5Jeu3() throws Exception {
        miniSocs.getUtilisateurs().get(pseudoMod).bloquerCompte();
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod,nomReseau ));
    }


    // moderateur fait partie de ce reseau, a des droits de modérations 
    @Test
    void fermerReseauTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,"nonExisting",nomReseau ));
    }
    @Test
    void fermerReseauTest6Jeu2() throws Exception {
        assertDoesNotThrow(() -> miniSocs.creerReseauSocial(pseudoMembre,pseudoMembre,"newR"), "Creation reseau a échoué");
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMod,pseudoMod,"newR" ));
    }
    @Test
    void fermerReseauTest6Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMembre,pseudoMembre,nomReseau ));
    }
    // Le compte utilisateur correspond au compte utilisateur
    @Test
    void fermerReseauTest6Jeu4() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudoMembre,pseudoMod,nomReseau ));
    }
    @Test
    void fermerReseauTest7Jeu1() throws Exception { 
		Assertions.assertEquals(1, miniSocs.listerReseaux().size());
        Assertions.assertTrue(miniSocs.getReseauxSociaux().get(nomReseau).estOuvert());
        assertDoesNotThrow(() -> miniSocs.fermerReseau(pseudoMod,pseudoMod,nomReseau), "fermeture réseau a échoué");
        Assertions.assertEquals(1, miniSocs.listerReseaux().size());
        Assertions.assertFalse(miniSocs.getReseauxSociaux().get(nomReseau).estOuvert());
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.fermerReseau( pseudoMod,pseudoMod, nomReseau));
    }    
}
	
