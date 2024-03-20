package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReseauSocial {
    private String nom;
    private boolean estOuvert;
    private final Map<String, Membre> membres;

    public ReseauSocial(String nom, boolean estOuvert) {
        if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom réseau ne peut pas être null ou vide");
		}
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

    public boolean invariant() {
        return nom != null && !nom.isBlank(); 
    }
    
    // Méthode pour ajouter un membre au réseau social
    public void ajouterMembre(Membre membre) {
        membres.put(membre.getPseudonyme(),membre);
    }

       @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReseauSocial)) {
            return false;
        }
        ReseauSocial other = (ReseauSocial) obj;
        return Objects.equals(nom, other.nom) ;
    }

    @Override
    public String toString() {
        return "ReseauSocial[" +
               "nom='" + nom + '\'' +
               ", estOuvert=" + estOuvert +
               ", membres=" + membres +
               ']';
    }
}
