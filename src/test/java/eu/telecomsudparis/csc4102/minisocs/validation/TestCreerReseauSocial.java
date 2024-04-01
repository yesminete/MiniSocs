// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCreerReseauSocial {
    private MiniSocs miniSocs;
    private String nomReseauSocial;
    private String pseudonyme;
    private String pseudoMembre;

    @BeforeEach
    void setUp() {
        miniSocs = new MiniSocs("MiniSocs");
        nomReseauSocial = "reseau1";
        pseudonyme = "utilisateur1";
        pseudoMembre = "membre1";
    }

    @AfterEach
    void tearDown() {
        miniSocs = null;
        nomReseauSocial = null;
        pseudonyme = null;
        pseudoMembre = null;
    }

    @Test
    void creerReseauSocialTest1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(null, pseudonyme, pseudoMembre));
    }

    @Test
    void creerReseauSocialTest1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau("", pseudonyme, pseudoMembre));
    }

    
    @Test
    void creerReseauSocialTest2Jeu1() throws Exception {
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().isEmpty());
        miniSocs.creerReseau(nomReseauSocial, pseudonyme, pseudoMembre);
        Assertions.assertFalse(miniSocs.listerreseauxSociaux().isEmpty());
        Assertions.assertEquals(1, miniSocs.listerreseauxSociaux().size());
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().get(0).contains(nomReseauSocial));
        Assertions.assertTrue(miniSocs.listerreseauxSociaux().get(0).contains("true"));
		Assertions.assertFalse(miniSocs.listerreseauxSociaux().get(0).contains("false"));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, pseudonyme, pseudoMembre));
    }
    
    @Test
    void creerReseauSocialTest3Jeu1() throws Exception {
        Assertions.assertFalse(miniSocs.listerUtilisateurs().isEmpty());
        Assertions.assertEquals(1, miniSocs.listerUtilisateurs().size());
        Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(pseudonyme));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.BLOQUE.toString()));
        Assertions.assertFalse(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.DESACTIVE.toString()));
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, pseudonyme, pseudoMembre));
    }
    
    @Test
    void creerReseauSocialTest4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, null, pseudoMembre));
    }
    
    @Test
    void creerReseauSocialTest4Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, "", pseudoMembre));
    }
    
    @Test
    void creerReseauSocialTest5Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, pseudonyme, null));
    }
    
    @Test
    void creerReseauSocialTest5Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> miniSocs.creerReseau(nomReseauSocial, pseudonyme, ""));
    }
    
    
}

