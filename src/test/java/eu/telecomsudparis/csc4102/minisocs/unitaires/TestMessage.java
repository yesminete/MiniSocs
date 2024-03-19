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
				() -> new Message (null, EtatMessage.ENATTENTE)) ;
	}

	@Test
	void constructeurMessageTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
		 () -> new Message("", EtatMessage.ENATTENTE));
	}

	@Test
	void constructeurMessageTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu", null));
	}

	@Test
	void constructeurMessageTest3() {
		Message message = new Message("contenu", EtatMessage.ENATTENTE);
		Assertions.assertNotNull(message);
		Assertions.assertEquals("contenu", message.getContenu());
		Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
	}
	@Test
	void constructeurMessageTest4() {
		Message message = new Message("contenu", EtatMessage.VISIBLE);
		Assertions.assertNotNull(message);
		Assertions.assertEquals("contenu", message.getContenu());
		Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
	}

	@Test
    void accepterMessageTest1() {
        Message message = new Message("contenu", EtatMessage.ENATTENTE);
    	Assertions.assertEquals(EtatMessage.ENATTENTE, message.getEtatMessage());
        message.AccepterMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    @Test
    void accepterMessageTest2() {
        Message message = new Message("contenu", EtatMessage.VISIBLE);
        message.AccepterMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    @Test
    void accepterMessageTest3() {
        Message message = new Message("contenu", EtatMessage.REJETE);
        // (Dans ce cas, on s'attend à une exception)
        Assertions.assertThrows(IllegalStateException.class, () -> {
            message.AccepterMessage();
        });
    }
    
    @Test
    void rendreMessageVisibleTest1() {
    
        Message message = new Message("contenu", EtatMessage.CACHÉ);
        message.RendeVisibleMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    @Test
    void rendreMessageVisibleTest2() {
        Message message = new Message("contenu", EtatMessage.VISIBLE);
        message.RendeVisibleMessage();
        Assertions.assertEquals(EtatMessage.VISIBLE, message.getEtatMessage());
    }
    
    @Test
    void cacherMessageTest1() {
        Message message = new Message("contenu", EtatMessage.VISIBLE);

        message.CacherMessage();
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
    }
    
    @Test
    void cacherMessageTest2() {
        Message message = new Message("contenu", EtatMessage.CACHÉ);
        message.CacherMessage();
        Assertions.assertEquals(EtatMessage.CACHÉ, message.getEtatMessage());
    }

    @Test
    void refuserMessageTest1() {
        Message message = new Message("contenu", EtatMessage.ENATTENTE);
        message.RefuserMessage();
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }
    
    @Test
    void refuserMessageTest2() {
        Message message = new Message("contenu", EtatMessage.REJETE);
        message.RefuserMessage();
        Assertions.assertEquals(EtatMessage.REJETE, message.getEtatMessage());
    }
	
}
