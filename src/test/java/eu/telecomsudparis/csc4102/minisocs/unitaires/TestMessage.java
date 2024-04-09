// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Message;

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

//Tester les postconditions afin de garantir que la classe Message initalise correctement les objets avec leurs états spécifiques
    @Test
    void constructeurMessageTest2() {
        Message message = new Message("contenu");
        Assertions.assertNotNull(message);
        Assertions.assertEquals("contenu", message.getContenu());
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        Assertions.assertEquals(message.getLastIdUsed()-1, message.getId());
    }
    
    /**On s'attend à ce qu'une IllegalStateException soit levée lors de la tentative d'acceptation du message, 
     * car un message refusé ne peut pas être accepté. C'est pourquoi cette assertion vérifie que cette exception 
     * est bien levée lorsque accepterMessage() est appelé après que le message ait été refusé.
     */

    @Test
    void modererMessageTest1() {
            Message message = new Message("contenu");
            Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
            message.setEtatMessage(EtatMessage.REJETE);
            message.modererMessage(true);
            Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }

    @Test
    void mdererMessageTest2() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.setEtatMessage(EtatMessage.VISIBLE);
        message.modererMessage(true);
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());  
    }

    @Test
    void mdererMessageTest3() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.setEtatMessage(EtatMessage.CACHÉ);
        message.modererMessage(true);
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());  
    }

    @Test
    void modererMessageTest4() {
            Message message = new Message("contenu");
            Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
            message.setEtatMessage(EtatMessage.REJETE);
            message.modererMessage(false);
            Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }

    @Test
    void mdererMessageTest5() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.setEtatMessage(EtatMessage.VISIBLE);
        message.modererMessage(false);
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());  
    }

    @Test
    void modererMessageTest6() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.setEtatMessage(EtatMessage.CACHÉ);
        message.modererMessage(false);
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());  
    }

    @Test
    void mdererMessageTest7() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.modererMessage(false);
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());  
    }


    @Test
    void mdererMessageTest8() {
        Message message = new Message("contenu");
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.modererMessage(true);
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());  
    }

   
    
    //Ici le message ne peut pas étre visible s'il est refusé ! donc levée d'une exception 
    
    @Test
    void rendreMessageVisibleTest1() {
        Message message = new Message("contenu");
        message.rendreVisibleMessage();
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
    }
    
    @Test
    void rendreMessageVisibleTest2() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.REJETE);
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }
    

    @Test
    void rendreMessageVisibleTest3() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.VISIBLE);
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }

    @Test
    void rendreMessageVisibleTest4() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.CACHÉ);
        message.rendreVisibleMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }

    @Test
    void cacherMessageTest1() {
        Message message = new Message("contenu");
        message.cacherMessage();
        Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
    }
    
    @Test
    void cacherMessageTest2() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.CACHÉ);
        message.cacherMessage();
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
    }
    
    @Test
    void cacherMessageTest3() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.REJETE);
        message.cacherMessage();
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }

        
    @Test
    void cacherMessageTest4() {
        Message message = new Message("contenu");
        message.setEtatMessage(EtatMessage.VISIBLE);
        message.cacherMessage();
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
    }

}
