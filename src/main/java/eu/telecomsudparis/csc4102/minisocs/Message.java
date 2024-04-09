

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
    final private Long id;
    /**
     * Posting message date.
     */
    final private LocalDateTime date;
    /**
     * le contenu du message.
     */
    final private String contenu;
    /**
     * l'état du message.
     */
    private EtatMessage etatMessage;
    /**
     * l'id du dernier msg posté.
     */
    private static Long lastIDused = 0l;

    /**
     * construit une instance du message.
     * 
     * @param contenu le contenu du messgae.
    */
    public Message(final String contenu) {
        
        if (contenu == null || contenu.isBlank()) {
            throw new IllegalArgumentException("le contenu d'un message ne peut pas etre ni vide ni null");
        }
        this.id = lastIDused++;
        this.date = LocalDateTime.now();
        this.contenu = contenu;
        this.etatMessage = EtatMessage.ENATTENTE;
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

    public long getId(){
        return id;
    }
    
    public long getLastIdUsed(){
        return lastIDused;
    }
    public void setEtatMessage(EtatMessage etat){
        etatMessage = etat;
    }
    
    /**
     * moderer le message posté du membre.
     */
    
     public void modererMessage(boolean acceptation) { 
        if (etatMessage == EtatMessage.ENATTENTE)  { 
            if(acceptation){
                setEtatMessage(EtatMessage.VISIBLE);         
            } 
            else{
                setEtatMessage(EtatMessage.REJETE);                     
            }
        }   
        assert invariant();
    }

    
     /**
     * cacher le message posté du membre.
     */
    
    public void cacherMessage() {
        if (etatMessage == EtatMessage.VISIBLE) {    
            setEtatMessage(EtatMessage.CACHÉ);
        }   
        assert invariant();
    }
    
     /**
     * rendre visible le message posté du membre. 
     */
    
    public void rendreVisibleMessage() {
        if (getEtatMessage() == EtatMessage.CACHÉ) { 
            setEtatMessage(EtatMessage.VISIBLE);;
        }   
    }

    
    @Override
    public String toString() {
        return "Message [id= " + id.toString() + " date= " + date + " contenu=" + contenu + " , etatMessage= " + etatMessage + "]";
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
