package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReseauSocial {
    // Nom du réseau social
    private final String nom;
    
    // Indique si le réseau social est ouvert ou fermé
    private boolean estOuvert;
    
    // Liste des membres du réseau social, indexés par leur pseudonyme
    private Map<String, Membre> membres;
    
    // Liste des messages du réseau social, indexés par leur identifiant
    private Map<Long, Message> messages;
    
    // Constructeur du réseau social
    public ReseauSocial(String nom, boolean estOuvert) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du réseau ne peut pas être null ou vide");
        }
        this.nom = nom;
        this.estOuvert = estOuvert;
        this.membres = new HashMap<>();
        this.messages = new HashMap<>();
        assert invariant();
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

    // Ajoute un message au réseau social
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
