package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class MiniSocs {
    /**
     * le nom du système.
     */
    private final String nomDuSysteme;
    private final Map<String, Utilisateur> utilisateurs;
    private final Map<String, ReseauSocial> reseauxSociaux;

    /**
     * construit une instance du système.
     * 
     * @param nomDuSysteme le nom.
     */
    public MiniSocs(final String nomDuSysteme) {
        this.nomDuSysteme = nomDuSysteme;
        this.utilisateurs = new HashMap<>();
        this.reseauxSociaux = new HashMap<>();
    }

    /**
     * l'invariant de la façade.
     * 
     * @return {@code true} si l'invariant est respecté.
     */
    public boolean invariant() {
        return nomDuSysteme != null && !nomDuSysteme.isBlank() && utilisateurs != null;
    }

    /**
     * ajoute un utilisateur.
     * 
     * @param pseudo   le pseudo de l'utilisateur.
     * @param nom      le nom de l'utilisateur.
     * @param prenom   le prénom de l'utilisateur.
     * @param courriel le courriel de l'utilisateur.
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void ajouterUtilisateur(final String pseudo, final String nom, final String prenom, final String courriel)
            throws OperationImpossible {
        if (pseudo == null || pseudo.isBlank()) {
            throw new OperationImpossible("pseudo ne peut pas être null ou vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new OperationImpossible("nom ne peut pas être null ou vide");
        }
        if (prenom == null || prenom.isBlank()) {
            throw new OperationImpossible("prenom ne peut pas être null ou vide");
        }
        if (courriel == null || courriel.isBlank()) {
            throw new OperationImpossible("courriel ne peut pas être null ou vide");
        }
        if (!EmailValidator.getInstance().isValid(courriel)) {
            throw new OperationImpossible("courriel ne respecte pas le standard RFC822");
        }
        Utilisateur u = utilisateurs.get(pseudo);
        if (u != null) {
            throw new OperationImpossible(pseudo + "déjà un utilisateur");
        }
        utilisateurs.put(pseudo, new Utilisateur(pseudo, nom, prenom, courriel));
        assert invariant();
    }

    /**
     * liste les utilisateurs.
     * 
     * @return la liste des pseudonymes des utilisateurs.
     */
    public List<String> listerUtilisateurs() {
        return utilisateurs.values().stream().map(Utilisateur::toString).toList();
    }

    /**
     * désactiver son compte utilisateur.
     * 
     * @param pseudo le pseudo de l'utilisateur.
     * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
     */
    public void desactiverCompteUtilisateur(final String pseudo) throws OperationImpossible {
        if (pseudo == null || pseudo.isBlank()) {
            throw new OperationImpossible("pseudo ne peut pas être null ou vide");
        }
        Utilisateur u = utilisateurs.get(pseudo);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        u.desactiverCompte();
        assert invariant();
    }
    
    /**
     * crée un réseau (ajoute un réseau).
     * 
     * @param nomReseau    le nom du réseau.
     * @param pseudonyme   le pseudonyme de l'utilisateur qui cree le réseau.
     * @param pseudoMembre le pseudonyme du membre dans le reseau (l'utilisateur devient membre).
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void creerReseau(final String nomReseau, final String pseudonyme, final String pseudoMembre)
            throws OperationImpossible {
        
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("nomReseau ne peut pas être null ou vide");
        }
        if (pseudonyme == null || pseudonyme.isBlank()) {
            throw new OperationImpossible("pseudonyme ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        Utilisateur u = utilisateurs.get(pseudonyme);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudonyme + ")");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
        
        ReseauSocial r = reseauxSociaux.get(nomReseau);
        if (r != null) {
            throw new OperationImpossible(nomReseau + "déjà un réseau");
        }
        reseauxSociaux.put(nomReseau, new ReseauSocial(nomReseau, pseudoMembre));
        u.ajouterMembre(pseudoMembre);
        assert invariant();
    }
    
    /**
     * lister les reseauxSociaux.
     * 
     * @return la liste des noms des reseauxSociaux.
     */
    public List<String> listerreseauxSociaux() {
        return reseauxSociaux.values().stream().map(ReseauSocial::toString).toList();
    }
    
    /**
     * lister les membres (dans le sens d'un abonnement) d'un utilisateur.
     * 
     * @param pseudonyme le pseudonyme de l'utilisateur
     * @return la liste des membres de l'utilisateur.
     */
    public List<String> listerMembres(final String pseudonyme) {
        Utilisateur u = utilisateurs.get(pseudonyme);
        return u.listerMembres();
    }
    
    
    /**
     * ajouter un membre au réseausocial.
     * 
     * @param nomReseau    le nom du réseau.
     * @param pseudonyme   le pseudonyme de l'utilisateur qui ajoute un membre.
     * @param monPseudoMembre le pseudo_membre de l'utilisateur sert à vérifier si il est modérateur
     * @param pseudonymenouveau    le pseudonyme de l'utilisateur à ajouter comme membre.
     * @param pseudoMembrenouveau   le pseudo_membre du membre à ajouter.
     * @param etatnouveauMembre l'état du membre à ajouter.
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void ajouterMembreAuReseau(final String nomReseau, final String pseudonyme, final String monPseudoMembre, final String pseudonymenouveau, 
            final String pseudoMembrenouveau, final boolean etatnouveauMembre)
            throws OperationImpossible {
        
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("nomReseau ne peut pas être null ou vide");
        }
        if (pseudonyme == null || pseudonyme.isBlank()) {
            throw new OperationImpossible("pseudonyme ne peut pas être null ou vide");
        }
        if (monPseudoMembre == null || monPseudoMembre.isBlank()) {
            throw new OperationImpossible("mon pseudo_membre ne peut pas être null ou vide");
        }
        if (pseudonymenouveau == null || pseudonymenouveau.isBlank()) {
            throw new OperationImpossible("pseudonyme de l'utilisateur à ajouter ne peut pas être null ou vide");
        }
        if (pseudoMembrenouveau == null || pseudoMembrenouveau.isBlank()) {
            throw new OperationImpossible("pseudo_membre à ajouter ne peut pas être null ou vide");
        }
        if (etatnouveauMembre) {
            throw new OperationImpossible("l'état du membre à ajouter ne peut pas être null");
        }
        Utilisateur u = utilisateurs.get(pseudonyme);
        if (u == null) {
            throw new OperationImpossible("utilisateur n'existe pas avec ce pseudo (" + pseudonyme + ")");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
        
        //vérification de l'existance du réseau
        ReseauSocial r = reseauxSociaux.get(nomReseau);
        if (r == null) {
            throw new OperationImpossible("réseau avec ce nom (" + nomReseau + ") n'existe pas!");
        }
        
        //vérifier que l'utilisateur est bien membre de ce réseau en plus d'etre modérateur
        Map<String, Membre> membres = r.getMembres();
        Membre n = membres.get(monPseudoMembre);
        if (n == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + monPseudoMembre + " ) n'existe pas dans ce réseau. ");
        }
        if (!u.monEtatMembre(monPseudoMembre)) {
            throw new OperationImpossible("vous n'êtes pas modérateur dans ce réseau, vous ne pouvez pas ajouter un membre");
        }
        
        
        Utilisateur v = utilisateurs.get(pseudonymenouveau);
        if (v == null) {
            throw new OperationImpossible("utilisateur à ajouter inexistant avec ce pseudo (" + pseudonymenouveau + ")");
        }
        
        new Membre(pseudoMembrenouveau, etatnouveauMembre);
        v.ajouterMembre(pseudoMembrenouveau);
        r.ajouterMembre(pseudoMembrenouveau);
        assert invariant();
    }
    
    /**
     * Poster un Message dans un reseau social existant.
     * 
     * @param contenu    le contenu du message.
     * @param pseudonyme   le pseudonyme de l'utilisateur qui est membre et qui publie le message.
     * @param pseudoMembre le pseudonyme du membre qui va publier le message dans le reseau .
     * @param nomReseau le réseau dans lequel le message va etre publié 
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void posterMessage(final String contenu, final String pseudonyme, final String pseudoMembre, final String nomReseau)
            throws OperationImpossible {
        
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("nomReseau ne peut pas être null ou vide");
        }
        if (pseudonyme == null || pseudonyme.isBlank()) {
            throw new OperationImpossible("pseudonyme ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (contenu == null || contenu.isBlank()) {
            throw new OperationImpossible("le contenu du message ne peut pas être null ou vide");
        }
        
        Utilisateur u = utilisateurs.get(pseudonyme);
        if (u == null) {
            throw new OperationImpossible("utilisateur avec ce pseudo (" + pseudonyme + ") n'existe pas!");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
    
        ReseauSocial r = reseauxSociaux.get(nomReseau);
        if (r == null) {
            throw new OperationImpossible("Réseau avec ce nom (" + nomReseau + " ) n'existe pas. ");
        }
        if (! r.getEtatReseau()) {
            throw new OperationImpossible("le réseau est fermé");
        }
        
        r.posterMessageReseauSocial(contenu, pseudoMembre);
        
        
        assert invariant();
    }
    
    /**
     * Un modérateur accepte un Message (en ce moment posté et en attente), il devient alors visible.
     * 
     * @param pseudonyme   le pseudonyme de l'utilisateur qui va accepter le message.
     * @param pseudoMembre   le pseudo membre de l'utilsateur.
     * @param idMessage      l'id du message à accepter 
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void accepterMessage(final String pseudonyme, final String pseudoMembre, final String idMessage)
            throws OperationImpossible {
        
        if (pseudonyme == null || pseudonyme.isBlank()) {
            throw new OperationImpossible("pseudonyme ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (idMessage == null) {
            throw new OperationImpossible("l'id du message ne peut pas être null");
        }
        
        Utilisateur u = utilisateurs.get(pseudonyme);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudonyme + ")");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
    
        Map<String, Membre> membres = u.getMembre();
        Membre n = membres.get(pseudoMembre);
        if (n == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas dans ce réseau. ");
        }
        if (!u.monEtatMembre(pseudoMembre)) {
            throw new OperationImpossible("vous n'êtes pas modérateur, vous ne pouvez pas accepter un message");
        }
        
        Map<String, Message> MessagePosted = n.getMessagePosted();
        Message mess = MessagePosted.get(idMessage);
        if (mess == null) {
            throw new OperationImpossible("Message avec cet id (" + idMessage + " ) n'existe pas. ");
        }
        
        mess.accepterMessage();
        
        assert invariant();
    }
    /**
     * Le modérateur peut fermer le réseau social d'un membre. 
     * 
     * @param pseudonyme   le pseudonyme de l'utilisateur qui va accepter le message.
     * @param pseudoMembre   le pseudo membre de l'utilsateur.
     * @param nomReseau     l'id du message à accepter 
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void fermerReseau (final String pseudonyme, final String pseudoMembre, final String nomReseau)
            throws OperationImpossible {
        
        if (pseudonyme == null || pseudonyme.isBlank()) {
            throw new OperationImpossible("pseudonyme ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("le nom de réseau ne peut pas être ni null ni vide");
        }
        
        Utilisateur u = utilisateurs.get(pseudonyme);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudonyme + ")");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
    
        Map<String, Membre> membres = u.getMembre();
        Membre n = membres.get(pseudoMembre);
        if (n == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas dans ce réseau. ");
        }
        if (!u.monEtatMembre(pseudoMembre)) {
            throw new OperationImpossible("vous n'êtes pas modérateur, vous ne pouvez pas fermer le réseau");
        }
        
        ReseauSocial r = reseauxSociaux.get(nomReseau);
        if (r == null) {
            throw new OperationImpossible("Reseau avec ce nom (" + nomReseau + " ) n'existe pas. ");
        }
        
        if (!r.getEtatReseau()){
            throw new OperationImpossible("le réseau est fermé");
        }
        r.fermerReseauSocial();
        assert invariant();
        
    }

    /**
     * obtient le nom du projet.
     * 
     * @return le nom du projet.
     */
    public String getNomDeProjet() {
        return nomDuSysteme;
    }

    @Override
    public String toString() {
        return "MiniSocs [nomDuSysteme=" + nomDuSysteme + ", utilisateurs=" + utilisateurs + "]";
    }
}

