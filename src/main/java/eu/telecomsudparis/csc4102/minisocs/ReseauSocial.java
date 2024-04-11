//CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.SubmissionPublisher;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class ReseauSocial {
    // Nom du réseau social
    private final String nom;
    
    // Indique si le réseau social est ouvert ou fermé
    private boolean estOuvert;
    
    // Liste des membres du réseau social, indexés par leur pseudonyme
    private Map<String, Membre> membres;
    
    // Liste des messages du réseau social, indexés par leur identifiant
    private Map<Long, Message> messages;
    
    //Producteur des notifications de messages en attente
    
    private SubmissionPublisher<Notification> producteurMessageEnAttente;
    
    //Producteur des notifcations de message postés
    
    private SubmissionPublisher<Notification> producteurMessagePoste;
    
    // Constructeur du réseau social
    public ReseauSocial(String nom, boolean estOuvert) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du réseau ne peut pas être null ou vide");
        }
        this.nom = nom;
        this.estOuvert = estOuvert;
        this.membres = new HashMap<>();
        this.messages = new HashMap<>();
        this.producteurMessageEnAttente = new SubmissionPublisher<>();
        this.producteurMessagePoste = new SubmissionPublisher<>();
        assert invariant();
    }
    
    public SubmissionPublisher<Notification> getProducteurMessageEnAttente()
    {
    	return producteurMessageEnAttente;
    }
    
    public SubmissionPublisher<Notification> getProducteurMessagePoste()
    {
    	return producteurMessagePoste;
    }

    // Vérifie l'invariant du réseau social
    public boolean invariant() {
        return nom != null && !nom.isBlank() && (estOuvert==true || estOuvert==false)
        && membres!= null && messages!=null;
    }

    // Retourne la liste des messages du réseau social
    public List<String> listerMessages() {
        return messages.values().stream().map(Message::toString).toList();
    }

    // Retourne le nom du réseau social
    public String getNom() {
        return nom;
    }


    // Retourne vrai si le réseau social est ouvert, sinon faux
    public boolean estOuvert() {
        return estOuvert;
    }


    // Retourne la liste des membres du réseau social
    public Map<String, Membre> getMembres() {
        return this.membres;
    }

    // Retourne la liste des messages du réseau social
    public Map<Long, Message> getMessages() {
        return messages;
    }



    // Ajoute un membre au réseau social
    public void ajouterMembre(Membre membre) {
        membres.put(membre.getPseudonymeMembre(), membre);
        assert invariant();
    }
    
    /**
     * crée un message et l'ajoute au réseau 
     * @throws OperationImpossible 
     * @throws InterruptedException 
     */
    public void posterMessageDansunReseau(final Message message, final String pseudoMembre) throws OperationImpossible, InterruptedException {
        Membre m = membres.get(pseudoMembre);
        if (m == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas. ");
        }
        
        // associer un état au message en fonction de l'état du membre
        if (m.estModerateur())
        {
            message.setEtatMessage(EtatMessage.VISIBLE);      
            producteurMessagePoste.submit(new Notification("Nouveau message! posté le " + LocalDateTime.now() + " <<" + message.getContenu() + ">> " ));
        } 
        else 
        {
            producteurMessageEnAttente.submit(new Notification("Message en attente de modération! posté le " + LocalDateTime.now() + " <<" + message.getContenu() + ">> " ));            
        }
                
        //creer une instance de ce message avec l'etatMessage correspondant 
        ajouterMessage(message);
        
        //ajouter le message aux messages postés du membre
        m.ajouterMessagePosted(message);
        
        Thread.sleep(100);
    }
    
    /**
     * ajoute un message existant au réseau 
     */
    public void ajouterMessage(Message message) {
        messages.put(message.getId(), message);
        assert invariant();
    }

    // Ferme le réseau social
    public void fermerReseau() {
        if (estOuvert) {
            estOuvert = false;
        }
        assert invariant();
    }

    // Méthode de hachage
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    // Méthode d'égalité
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReseauSocial)) {
            return false;
        }
        ReseauSocial other = (ReseauSocial) obj;
        return Objects.equals(nom, other.nom);
    }

    // Méthode de représentation sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "ReseauSocial[" +
               "nom='" + nom + '\'' +
               ", estOuvert=" + estOuvert +
               ", membres=" + membres +
               ']';
    }
}
