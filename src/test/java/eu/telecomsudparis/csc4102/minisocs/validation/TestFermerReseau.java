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

class TestfermerReseau {
    
    private MiniSocs miniSocs;
    private Utilisateur utilisateur;
    private String nomReseau;
    private String pseudonyme;
    private String pseudoMembre;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        utilisateur = new Utilisateur("userX","nomX", "prenomX", "courrierlX");
        nomReseau ="reseaux";
        pseudonyme = "userX";
        pseudoMembre = "membreX";
        
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        utilisateur=null;
        nomReseau = null;
        pseudonyme = null;
        pseudoMembre = null;
    }
    
    
    @Test
    void fermerReseauTest1Jeu1() throws Exception {
        
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme)));
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
     
    //pseudonyme non null et non vide  
    
    @Test
    void fermerReseauTest2Jeu1() throws Exception {
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
        
        
    @Test
    void fermerReseauTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
    
    // Tester le compte de l'utilisateur non bloqué et non désactivé 
    @Test
    void fermerReseauTest3Jeu1() throws Exception {
        
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().anyMatch(utilisateur-> utilisateur.contains(pseudonyme)));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().stream().filter(utilisateur -> utilisateur.contains(pseudonyme)).anyMatch (utilisateur -> utilisateur.contains(EtatCompte.DESACTIVE.toString())));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().stream().filter(utilisateur -> utilisateur.contains(pseudonyme)).anyMatch (utilisateur -> utilisateur.contains(EtatCompte.BLOQUE.toString())));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
    
    // Tester si l'utilisateur est modérateur du réseau social 
    @Test
    void fermerReseauTest4Jeu1() throws Exception {
        Assertions.assertFalse(utilisateur.listerMembres().stream().anyMatch(membres -> membres.contains(pseudoMembre) && membres.contains("true")));
        Assertions.assertThrows(OperationImpossible.class,
               () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
        
    //pseudoMembre non null et non vide  
    
    @Test
    void fermerReseauTest5Jeu1() throws Exception {
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( pseudonyme,"", nomReseau));
    }
        
        
    @Test
    void fermerReseauTest5Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
            () -> miniSocs.fermerReseau(pseudonyme,null, nomReseau));
    }   
    

    //si aucun réseau ayant un pseudo "nomReseau" n'est trouvé alors une exception s'éleve
    @Test
    void fermerReseauTest6Jeu1() throws Exception { 
         Assertions.assertTrue(miniSocs.listerreseauxSociaux().stream().noneMatch(reseau -> reseau.contains(nomReseau)));
         Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.fermerReseau(pseudonyme,pseudoMembre, nomReseau));
    }
    
    @Test
    void fermerReseauTest7Jeu1() throws Exception { 
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().stream().noneMatch(reseau -> reseau.contains(nomReseau) && reseau.contains("true")));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.fermerReseau( pseudonyme,pseudoMembre, nomReseau));
    }
    
}
	
