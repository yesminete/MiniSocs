// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;

class TestMessage {


    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void constructeurMessageTest1Jeu1() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Message (null)) ;
    }

    @Test
    void constructeurMessageTest1Jeu2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Message(""));
    }

    @Test
    void constructeurMessageTest2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu"));
    }

//Tester les postconditions afin de garantir que la classe Message initalise correctement les objets avec leurs états spécifiques
    @Test
    void constructeurMessageTest3() {
        Message message = new Message("contenu");
        Assertions.assertNotNull(message);
        Assertions.assertEquals("contenu", message.getContenu());
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
    }
    @Test
    void constructeurMessageTest4() {
        Message message = new Message("contenu");
        Assertions.assertNotNull(message);
        Assertions.assertEquals("contenu", message.getContenu());
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    
    /**On s'attend à ce qu'une IllegalStateException soit levée lors de la tentative d'acceptation du message, 
     * car un message refusé ne peut pas être accepté. C'est pourquoi cette assertion vérifie que cette exception 
     * est bien levée lorsque accepterMessage() est appelé après que le message ait été refusé.
     */

    @Test
    void AccepterMessageTest1() {
            Message message = new Message("contenu");
            Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
            message.modererMessage(false);
            Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
            Assertions.assertThrows(IllegalStateException.class, () -> message.modererMessage(true));
    }

    @Test
    void AccepterMessageTest2() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.modererMessage(true);
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
        
    }
    
    //Ici le message ne peut pas étre visible s'il est refusé ! donc levée d'une exception 
    
    @Test
    void RendreMessageVisibleTest1() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
        message.modererMessage(false);
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
        Assertions.assertThrows(IllegalStateException.class, () -> message.rendreVisibleMessage());
    }
    
    @Test
    void RendreMessageVisibleTest2() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
        message.rendreVisibleMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    //Levé de l'exception lorsque le message est par exemple refusé et la méthode caché est appliquer !
    
    @Test
    void CacherMessageTest1() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
        message.modererMessage(false);
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
        Assertions.assertThrows(IllegalStateException.class, () -> message.cacherMessage());
    }
    
    @Test
    void CacherMessageTest2() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
        message.cacherMessage();
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
    }
    
    //Levé de l'exception lorsque le message est par exemple caché et la méthode refuser est appliquer !
    
    @Test
    void RefuserMessageTest1() {
            Message message = new Message("contenu");
            Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
            message.cacherMessage();
            Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
            Assertions.assertThrows(IllegalStateException.class, () -> message.modererMessage(false));
    }
    @Test
    void RefuserMessageTest2() {
            Message message = new Message("contenu");
            Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
            message.modererMessage(false);
            Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }
}
