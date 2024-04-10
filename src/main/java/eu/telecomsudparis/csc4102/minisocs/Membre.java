//CHECKSTYLE:OFF 
package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;


public class Membre {
    /**
     * Le pseudonyme spécifique du membre dans le réseau social.
     */
    private String pseudonymeMembre;

    /**
     * Indique si le membre a des droits de modérateur dans le réseau social.
     */
    private boolean estModerateur;

    /**
     * Indique le réseau social auquel ce membre fait partir.
     */

    final private ReseauSocial reseauSocial;
     /**
     * les messages postés par le membre
     */
    private final Map<Long, Message> messagePosted;

    /**
     * Consommateur de notifications
     */
    private final MonConsommateur monConsommateur;



    /**
     * Construit un membre du réseau social avec les informations spécifiées.
     * @param pseudonymeMembre le pseudonyme spécifique du membre dans le réseau social.
     * @param estModerateur indique si le membre est modérateur.
     * @param reseauSocial indique le réseau social auquel ce membre fait partir.
     */

    public Membre(final String pseudonymeMembre, final boolean estModerateur, final ReseauSocial reseauSocial , final MonConsommateur consommateur) {

        // Vérification pseudonymeMembre est non null non vide , utilisateur non null
        if (pseudonymeMembre == null || pseudonymeMembre.isBlank()) {
            throw new IllegalArgumentException("pseudonymeMembre ne peut pas être null ou vide");
        }
        if(reseauSocial==null){
            throw new IllegalArgumentException("Le réseau social ne peux pas null");
        }
        // Initialisation des attributs spécifiques à Membre
        this.pseudonymeMembre = pseudonymeMembre;
        this.estModerateur = estModerateur;
        this.reseauSocial = reseauSocial;
        this.messagePosted = new HashMap<>();
        this.monConsommateur = consommateur;
        assert invariant();
    }
 
    public boolean invariant() {
        return  pseudonymeMembre != null && !pseudonymeMembre.isBlank() && 
               reseauSocial != null 
               && messagePosted != null
               && ((estModerateur == true) || (estModerateur == false))
               && monConsommateur!=null;
    }

    /**
     * Obtient le pseudonyme spécifique du membre.
     * 
     * @return le pseudonyme spécifique du membre.
     */
    public String getPseudonymeMembre() {
        return pseudonymeMembre;
    }

    /**
     * Définit le pseudonyme spécifique du membre.
     * 
     * @param pseudonymeMembre le nouveau pseudonyme spécifique du membre.
     */
    public void setPseudonymeMembre(String pseudonymeMembre) {
        this.pseudonymeMembre = pseudonymeMembre;
    }

    /**
     * Vérifie si le membre est modérateur.
     * 
     * @return {@code true} si le membre est modérateur, sinon {@code false}.
     */
    public boolean estModerateur() {
        return estModerateur;
    }

    /**
     * Définit le statut de modérateur du membre.
     * 
     * @param estModerateur le nouveau statut de modérateur du membre.
     */
    public void setEstModerateur(boolean estModerateur) {
        this.estModerateur = estModerateur;
    }

    /**
     * Obtient le réseau social du membre.
     * 
     * @return Le reseauSocial du memre.
     */
    public ReseauSocial getReseauSocial() {
        return reseauSocial;
    }

    /**
     * ajouter un message existant au membre 
     */
    public void ajouterMessagePosted(Message message) {
        messagePosted.put(message.getId(), message);
    }
    
    /**
     * @return le consommateur du membre
     */
    public MonConsommateur getMonConsommateur() {
    	return monConsommateur;
    }
    // On a mi pseudonymeMembre dans le hashcode en supposant que pseudonymeMembre
    // est unique et invariant pour chaque reseau social (pour des raisons de simplicité)

    @Override
    public int hashCode() {
        return Objects.hash(pseudonymeMembre,reseauSocial.getNom() );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Membre)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Membre other = (Membre) obj;
        return Objects.equals(pseudonymeMembre, other.getPseudonymeMembre()) &&
               Objects.equals(this.getReseauSocial().getNom(), other.getReseauSocial().getNom());
    }

	@Override
	public String toString() { 
		return "Membre [pseudonyme=" + pseudonymeMembre + 
        ", est moderateur =" + estModerateur + 
        ",réseau social = "+ reseauSocial.getNom() 
        + "]";
	}
}

