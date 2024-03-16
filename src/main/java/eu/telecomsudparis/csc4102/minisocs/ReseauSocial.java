package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;

public class ReseauSocial {
    private String nom;
    private boolean estOuvert;
    private final Map<String, Membre> membres;

    public ReseauSocial(String nom, boolean estOuvert) {
        this.nom = nom;
        this.estOuvert = estOuvert;
        this.membres = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public boolean estOuvert() {
        return estOuvert;
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    public Map<String, Membre> getMembres() {
        return this.membres ;
    }

    // Méthode pour ajouter un membre au réseau social
    public void ajouterMembre(Membre membre) {
        membres.put(membre.getPseudonyme(),membre);
    }
}
