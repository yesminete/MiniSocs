package eu.telecomsudparis.csc4102.minisocs;
import java.util.ArrayList;
import java.util.List;

public class ReseauSocial {
    private String nom;
    private boolean estOuvert;
    private List<Membre> membres; // Liste de tous les membres du réseau social

    public ReseauSocial(String nom, boolean estOuvert) {
        this.nom = nom;
        this.estOuvert = estOuvert;
        this.membres = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public boolean isEstOuvert() {
        return estOuvert;
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    // Méthode pour ajouter un membre au réseau social
    public void ajouterMembre(Membre membre) {
        membres.add(membre);
    }
}
