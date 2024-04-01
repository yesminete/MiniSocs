// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterMembreAUnReseau {
    private MiniSocs miniSocs;
    private String nomReseau;
    private String pseudonyme;
    private String monPseudoMembre;
    private String pseudonymeNew;
    private String pseudoMembreNew;
    private boolean etatMembreNew;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        nomReseau = "reseau1";
        pseudonyme = "utilisateur1";
        monPseudoMembre = "membre1";
        pseudonymeNew = "utilisateurNouveau";
        pseudoMembreNew = "membreNouveau";
        etatMembreNew = false; //c'est un membre (n'est pas modérateur)
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        nomReseau = null;
        pseudonyme = null;
        monPseudoMembre = null;
        pseudoMembreNew = null;
        pseudoMembreNew = null;
    }

    @Test
    void AjouterMembreAuReseauTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(null, pseudonyme, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau("", pseudonyme, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest2Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerreseauxSociaux().isEmpty());
        Assertions.assertEquals(1, miniSocs.listerreseauxSociaux().size());
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().get(0).contains(nomReseau));
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().get(0).contains("True"));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest3Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerreseauxSociaux().isEmpty());
        Assertions.assertEquals(1, miniSocs.listerreseauxSociaux().size());
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().get(0).contains(nomReseau));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    
    @Test
    void AjouterMembreAuReseauTest4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, null, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest4Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, "", monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest5Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerUtilisateurs().isEmpty());
        Assertions.assertEquals(1, miniSocs.listerUtilisateurs().size());
        Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(pseudonyme));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.BLOQUE.toString()));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.DESACTIVE.toString()));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre,  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    //les tests 6 et 7 ont déjà été vérifiés dans le test 5
    
    @Test
    void AjouterMembreAuReseauTest8Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , null,  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest8Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , "",  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest9Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerMembres(pseudonyme).isEmpty());
        Assertions.assertEquals(1, miniSocs.listerMembres(pseudonyme).size());
        Assertions.assertTrue(miniSocs.listerMembres(pseudonyme).get(0).contains(pseudonyme));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest10Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerMembres(pseudonyme).isEmpty());
        Assertions.assertEquals(1, miniSocs.listerMembres(pseudonyme).size());
        Assertions.assertTrue(miniSocs.listerMembres(pseudonyme).get(0).contains(pseudonyme));
        Assertions.assertTrue(miniSocs.listerMembres(pseudonyme).get(0).contains("True"));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre,  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest11Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , monPseudoMembre, null, pseudoMembreNew , etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest11Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , monPseudoMembre, "" , pseudoMembreNew , etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest12Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , monPseudoMembre, pseudonymeNew, null , etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest12Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , monPseudoMembre, pseudonymeNew, "" , etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest13Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme , monPseudoMembre, pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest14Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerUtilisateurs().isEmpty());
        Assertions.assertEquals(2, miniSocs.listerUtilisateurs().size());
        Assertions.assertTrue(miniSocs.listerUtilisateurs().get(1).contains(pseudonymeNew));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre,  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
    
    @Test
    void AjouterMembreAuReseauTest15Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerMembres(pseudonyme).isEmpty());
        miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre,  pseudonymeNew, pseudoMembreNew, etatMembreNew);
        Assertions.assertFalse(miniSocs.listerMembres(pseudonyme).isEmpty());
        Assertions.assertEquals(1, miniSocs.listerMembres(pseudonyme).size());
        Assertions.assertTrue(miniSocs.listerMembres(pseudonymeNew).get(0).contains(pseudoMembreNew));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.ajouterMembreAuReseau(nomReseau, pseudonyme, monPseudoMembre,  pseudonymeNew, pseudoMembreNew, etatMembreNew));
    }
}
