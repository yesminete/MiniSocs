

package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;
import java.time.LocalDateTime;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Sabrine Azaiez
 */
public class Message {
    /**
     * l'id du message.
     */
    private String id;
    /**
     * Posting message date.
     */
    private LocalDateTime date;
    /**
     * le contenu du message.
     */
    private String contenu;
    /**
     * l'état du message.
     */
    private EtatMessage etatMessage;
    /**
     * l'id du dernier msg posté.
     */
    private static int lastIDused = 0;


    /**
     * construit une instance du message.
     * 
     * @param contenu le contenu du messgae.
     * @param etatMessage l'état du message 
    */
    public Message(final String contenu, final EtatMessage etatMessage) {
        
        if (contenu == null || contenu.isBlank()) {
            throw new IllegalArgumentException("le contenu d'un message ne peut pas etre ni vide ni null");
        }
        this.id = String.valueOf(++lastIDused);
        this.date = LocalDateTime.now();
        this.contenu = contenu;
        this.etatMessage = etatMessage;
        
        assert invariant();
    }


    /**
     * l'invariant de la façade.
     * 
     * @return {@code true} si l'invariant est respecté.
     */
    public boolean invariant() {
        return contenu  != null  && !contenu.isBlank()  && etatMessage != null && id != null && date != null;
    }


    /**
     * obtient le contenu.
     * 
     * @return le contenu.
     */
    public String getContenu() {
        return contenu;
    }
    /**
     * obtient l'état du message.
     * 
     * @return l'état du message
     */
    
    public EtatMessage getEtatMessage() {
        return etatMessage;
    }
    
     /**
     * accepter le message posté du membre.
     */
    
    public void accepterMessage() {
        
        if (etatMessage == EtatMessage.ENATTENTE)  {
            
            etatMessage = EtatMessage.VISIBLE;
        }   
    }
    
     /**
     * refuser le message posté du membre.
     */
    
    public void refuserMessage() {
        
        if (etatMessage == EtatMessage.ENATTENTE) { 
            etatMessage = EtatMessage.REJETE ;
        }   
    }
    
     /**
     * cacher le message posté du membre.
     */
    
    public void cacherMessage() {
        if (etatMessage == EtatMessage.VISIBLE) { 
            
            etatMessage = EtatMessage.CACHÉ;
        }   
    }
    
     /**
     * rendre visible le message posté du membre. 
     */
    
    public void rendreVisibleMessage() {
        if (etatMessage == EtatMessage.CACHÉ) {
            
            etatMessage = EtatMessage.VISIBLE;
        }   
    }
    
    @Override
    public String toString() {
        return "Message [id= " + id + " date= " + date + " contenu=" + contenu + " , etatMessage= " + etatMessage + "]";
    }


    @Override
    public int hashCode() {
        return Objects.hash(contenu, date);
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Message other = (Message) obj;
        return Objects.equals(contenu, other.contenu) && Objects.equals(date, other.date);
    }

    
    
    
}
