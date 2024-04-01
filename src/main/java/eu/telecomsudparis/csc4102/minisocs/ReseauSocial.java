//CHECKSTYLE:OFF 
package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;



/**
 * Cette classe réalise le concept d'utilisateur du système, à ne pas confondre
 * avec le concept de participant, sous-entendu à un réséeau social.
 * @author Sabrine Azaiez
 */
public class ReseauSocial {
    
    /**
     * le nom du réseau.
     */
    private  String nom;
    
    /**
     * l'état du réseau.
     */
    private boolean estOuvert;
    
    /**
     * les membres
     */
    private final Map<String, Membre> Membres;

    /**
     * les messages
     */
    private final Map<String, Message> Messages;
    
    /**
     * construit un réseau.
     * 
     * @param nom le nom du réseau 
     * @param pseudo du membre qui a creé le ReseauSocial  
     */
    public ReseauSocial(final String nom, final String pseudoMembre){
        
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("le nom de Reseau ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new IllegalArgumentException("le pseudo membre ne peut pas être null ou vide");
        }
        this.nom= nom;
        this.estOuvert= true;
        this.Membres= new HashMap<>();
        //un nouveau membre (moderateur) se crée et il est dans le Reseau
        Membres.put(pseudoMembre, new Membre(pseudoMembre, true));
        this.Messages= new HashMap<>();
        assert invariant();
    }
    
    
    /**
     * ajoute un membre
     * @throws OperationImpossible 
     */
    public void ajouterMembre(final String pseudoMembre) throws OperationImpossible {
        Membre m = Membres.get(pseudoMembre);
        if (m == null) {
            throw new OperationImpossible("membre avec ce pseudo n'existe pas : " + pseudoMembre);
        }
        this.Membres.put(pseudoMembre, m);
        assert invariant();
    }



    /**
     * verification de l'invariant de la classe.
     * 
     * @return {@code true} si l'invariant est respecté.
     */
    
    public boolean invariant() {
        return nom != null && !nom.isBlank() && ((estOuvert == true) || (estOuvert == false)) ;
    }

    /**
     * obtient le nom du réseau.
     * 
     * @return le nom de ce réseau .
     */
    public String getNomReseau() {
        return nom ;
    }

    /**
     * obtenir l'état du réseau.
     * 
     * @return l'énumérateur.
     */
    public boolean getEtatReseau() {
        return estOuvert;
    }
    
    /**
     * liste les membres du ReseauSocial.
     * 
     * @return la liste des pseudoMembres des membres.
     */
    public List<String> listerMembres() {
        return Membres.values().stream().map(Membre::toString).toList();
    }
    
    /**
     * getter pour les membres du ReseauSocial.
     * 
     * @return les membres du ReseauSocial.
     */
    public Map<String, Membre> getMembres() {
        return Membres;
    }
    
    /**
     * liste les messages dans le ReseauSocial.
     * 
     * @return la liste des messages dans ce ReseauSocial.
     */
    public List<String> listerMessages() {
        return Messages.values().stream().map(Message::toString).toList();
    }
    
    /**
     * creer un message et l'ajouter au reseau 
     * @trows OperationImpossible 
     */
    public void posterMessageReseauSocial(final String contenu, final String pseudoMembre) throws OperationImpossible {
        Membre m = Membres.get(pseudoMembre);
        if (m == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas.");
        }
        
        // associer un état au message en fonction de l'état du membre
        EtatMessage etatMessage;
        if (m.getEtatMembre()) 
        {
            etatMessage = EtatMessage.VISIBLE;
        } 
        else 
        {
            etatMessage = EtatMessage.ENATTENTE;
        }
                
        //creer une instance de ce message avec l'etatMessage correspondant 
        Message message=new Message(contenu, etatMessage);
                
        ajouterMessage(message);
        
        //ajouter le message comme posté du membre
        m.ajouterMessagePosted(message);
    }
    
    /**
     * ajoute un message existant au réseau 
     */
    public void ajouterMessage(Message message) {
        Messages.put(message.getContenu(), message);
    }
    
    public void fermerReseauSocial () {
        
        if(estOuvert == true) {
            
            estOuvert = false;
        }
        

    }


    @Override
    public String toString() {
        return "ReseauSocial [nom=" + nom +  ", estOuvert=" + estOuvert + "]" ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReseauSocial other = (ReseauSocial) obj;
        return Objects.equals(nom, other.nom);
    }
}
