//CHECKSTYLE:OFF 
package eu.telecomsudparis.csc4102.minisocs;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Membre {
    /**
     * le pseudonyme du membre.
     */
    private final String pseudoMembre;
    /**
     * état du membre (membre ou moderateur).
     */
    private boolean estModerateur;
    /**
     * les messages postés par le membre
     */
    private final Map<String, Message> MessagePosted;

    /**
     * construction d'un membre.
     * 
     * @param pseudoMembre le pseudonyme du membre dans le reseau.
     */
    public Membre(final String pseudoMembre, final boolean estModerateur) {
        
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new IllegalArgumentException("pseudoMembre ne doit pas être null ou vide");
        }
        this.pseudoMembre = pseudoMembre;
        this.estModerateur = estModerateur;
        this.MessagePosted= new HashMap<>();
        
        assert invariant();
    }

    /**
     * vérifie l'invariant de la classe.
     * 
     * @return {@code true} si l'invariant est respecté.
     */
    public boolean invariant() {
        return pseudoMembre != null && !pseudoMembre.isBlank()
                && ((estModerateur == true) || (estModerateur == false));
    }

    /**
     * obtient le pseudonyme.
     * 
     * @return le pseudonyme.
     */
    public String getpseudoMembre() {
        return pseudoMembre;
    }

    /**
     * le status du membre.
     * 
     * @return l'énumérateur.
     */
    public boolean getEtatMembre() {
        return estModerateur;
    }

    /**
     * ajouter un message existant au membre 
     */
    public void ajouterMessagePosted(Message message) {
        MessagePosted.put(message.getContenu(), message);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pseudoMembre);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) obj;
        return Objects.equals(pseudoMembre, other.pseudoMembre);
    }
    
    /**
     * liste les messages postés du membre.
     * 
     * @return la liste des messages postés du membre.
     */
    public List<String> listerMessages() {
        return MessagePosted.values().stream().map(Message::toString).toList();
    }
    
    /**
     * getter des messages postés du membre.
     * 
     * @return les messages postés du membre.
     */
    public Map<String, Message> getMessagePosted() {
        return MessagePosted;
    }

    @Override
    public String toString() {
        return "Membre [pseudoMembre=" + pseudoMembre 
                + ", etatMembre=" + estModerateur + "]";
    }
}

