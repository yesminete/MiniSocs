package eu.telecomsudparis.csc4102.minisocs;

/**
 * Cette classe représente un membre du réseau social, qui est une spécialisation
 * d'un utilisateur avec des fonctionnalités et des attributs supplémentaires,
 * notamment un pseudonyme spécifique au membre et un indicateur de modérateur.
 * 
 * Elle hérite de la classe Utilisateur et ajoute des attributs et méthodes propres
 * aux membres du réseau social.
 * 
 */
public class Membre extends Utilisateur {
    /**
     * Le pseudonyme spécifique du membre dans le réseau social.
     * Ce champ est ajouté pour illustrer la demande, bien que potentiellement redondant.
     */
    private String pseudonymeMembre;

    /**
     * Indique si le membre a des droits de modérateur dans le réseau social.
     */
    private boolean estModerateur;

    /**
     * Indique le réseau social auquel ce membre fait partir.
     */

    private ReseauSocial reseauSocial;
    /**
     * Construit un membre du réseau social avec les informations spécifiées.
     * 
     * @param pseudonyme le pseudonyme de l'utilisateur.
     * @param nom le nom de l'utilisateur.
     * @param prenom le prénom de l'utilisateur.
     * @param courriel l'adresse courriel de l'utilisateur.
     * @param pseudonymeMembre le pseudonyme spécifique du membre dans le réseau social.
     * @param estModerateur indique si le membre est modérateur.
     * @param reseauSocial indique le réseau social auquel ce membre fait partir.
     */

    public Membre(Utilisateur utilisateur, final String pseudonymeMembre, final boolean estModerateur, final ReseauSocial reseauSocial) {

        // Appel du constructeur de la super classe Utilisateur avec les attributs récupérés de l'objet utilisateur existant
        super(utilisateur.getPseudonyme(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getCourriel()); 
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
}
