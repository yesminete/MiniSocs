
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
        contenu = "contenu1";
        nomReseau = "reseauX";
        pseudonyme = "userX";
        pseudoMembre = "membreX";
        
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        utilisateur=null;
        contenu = null;
        nomReseau = null;
        pseudonyme = null;
        pseudoMembre = null;
    }
    

    @Test
    void PosterMessageTest1Jeu1() throws Exception {
        
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().stream().noneMatch(reseau -> reseau.contains(nomReseau)));
         Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, nomReseau));
    }
    
    //tester le nom réseau s'il est non null et non vide 
    @Test
    void PosterMessageTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, null));
    }

    @Test
    void PosterMessageTest2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, ""));
    }

    //Ici si on trouve que le réseau danslequel on veut poster le message et ayant un nom nomReseau ayant un état différent de ouvert (nonMatch) alors exception s'eleve
    @Test
    void PosterMessageTest3Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().stream().noneMatch(reseau -> reseau.contains(nomReseau) && reseau.contains("true")));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, nomReseau));
    }
    
    
    // si aucun utilisateur ayant un pseudo "pseudonyme" n'est trouvé alors une exception s'éleve
    @Test
    void PosterMessageTest4Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme)));
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, nomReseau));
    }
    
    //si l'utilisateur avec le pseudonyme "pseudonyme" n'a pas un compte actif alors exception ! 
    
    @Test
    void PosterMessageTest5Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme) && utilisateur.contains(EtatCompte.ACTIF.toString())));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, nomReseau));
    }
    
    //Tester si le pseudonyme non null et non vide 
    
    @Test
    void PosterMessageTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, null , pseudoMembre, nomReseau));
    }
    
    @Test
    void PosterMessageTest6Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, "" , pseudoMembre, nomReseau));
    }
    
    //tester si le pseudoMembre non null et non vide 
    @Test
    void PosterMessageTest7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, pseudonyme , null, nomReseau));
    }
    
    @Test
    void PosterMessageTest7Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.posterMessage(contenu, pseudonyme , "", nomReseau));
    }
    
    // Tester si utilisateur est membre dans d'autre terme si le pseudoMembre existe donc si la methode 
    //posterMessage prend cet argument pseudoMembre qui est inexistant une exception est levée. 
    @Test
    void PosterMessageTest8Jeu1() throws Exception {
        Assertions.assertTrue(utilisateur.listerMembres().stream().noneMatch(membres -> membres.contains(pseudoMembre)));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.posterMessage(contenu, pseudonyme, pseudoMembre, nomReseau));
    }
    //Tester si le contenu non null et non vide 
    
        @Test
        void PosterMessageTest9Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class,
                    () -> miniSocs.posterMessage(null, pseudonyme, pseudoMembre, nomReseau));
        }
        
        @Test
        void PosterMessageTest9Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class,
                    () -> miniSocs.posterMessage("", pseudonyme , pseudoMembre, nomReseau));
        }
    
    
    
    
}
