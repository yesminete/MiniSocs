// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAccepterMessage {
    
    private MiniSocs miniSocs;
    private Utilisateur utilisateur;
    private ReseauSocial reseau; 
    private String idMessage;
    private String contenu;
    private String pseudonyme;
    private String pseudoMembre;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        reseau = new ReseauSocial ("nomReseau", "pseudoMembre");
        utilisateur = new Utilisateur("userX","nomX", "prenomX", "courrierlX");
        idMessage = "id";
        contenu = "contenu1";
        pseudonyme = "userX";
        pseudoMembre = "membreX";
        
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        reseau = null ;
        utilisateur=null;
        contenu = null;
        pseudonyme = null;
        pseudoMembre = null;
    }
    
    
    //si aucun message ayant un contenu "contenu1" n'est trouvé alors une exception !
    @Test
    void accepterMessageTest1Jeu1() throws Exception {
        
        Assertions.assertTrue(reseau.listerMessages().stream().noneMatch( message-> message.contains(contenu)));
         Assertions.assertThrows(OperationImpossible.class,
                   () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, idMessage));
    }
    
    //tester le idMessage non null 
    void accepterMessageTest2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, null));
    }
    
    
    //tester l'état message en attente 
    @Test
    void accepterMessageTest3Jeu1() throws Exception {
        Assertions.assertFalse(reseau.listerMessages().stream().filter(message -> message.contains(contenu)).anyMatch(message -> message.contains(EtatMessage.ENATTENTE.toString())));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, idMessage));
    }

    //si aucun utilisateur ayant un pseudo "pseudonyme" n'est trouvé alors une exception s'éleve
    @Test
    void accepterMessageTest4Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().noneMatch(utilisateur -> utilisateur.contains(pseudonyme)));
         Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, idMessage));
    }
    
    
    // Tester le compte de l'utilisateur non bloqué et non désactivé 
    @Test
    void accepterMessageest5Jeu1() throws Exception {
        
        Assertions.assertTrue(miniSocs.listerUtilisateurs().stream().anyMatch(utilisateur-> utilisateur.contains(pseudonyme)));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().stream().filter(utilisateur -> utilisateur.contains(pseudonyme)).anyMatch (utilisateur -> utilisateur.contains(EtatCompte.DESACTIVE.toString())));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().stream().filter(utilisateur -> utilisateur.contains(pseudonyme)).anyMatch (utilisateur -> utilisateur.contains(EtatCompte.BLOQUE.toString())));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, idMessage));
    }
    
    //pseudonyme non null et non vide  
    
    @Test
    void accepterMessageTest6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.accepterMessage(null, pseudoMembre, idMessage));
    }
    
    
    @Test
    void accepterMessageTest6Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.accepterMessage("", pseudoMembre, idMessage));
    }
    
    //tester si le pseudoMembre non null et non vide 
    @Test
    void accepterMessageTest7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.accepterMessage(pseudonyme , null, idMessage));
    }
    
    @Test
    void accepterMessageTest7Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.accepterMessage(pseudonyme , "", idMessage));
    }
    
    // Tester si l'utilisateur est modérateur du réseau social 
    @Test
    void accepterMessageTest8Jeu1() throws Exception {
        Assertions.assertFalse(utilisateur.listerMembres().stream().anyMatch(membres -> membres.contains(pseudoMembre) && membres.contains("true")));
        Assertions.assertThrows(OperationImpossible.class,
             () -> miniSocs.accepterMessage(pseudonyme, pseudoMembre, idMessage));
    }
    
    
}

