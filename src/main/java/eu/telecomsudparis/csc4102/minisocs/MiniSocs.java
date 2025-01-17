package eu.telecomsudparis.csc4102.minisocs;

import eu.telecomsudparis.csc4102.util.OperationImpossible;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Cette classe est la façade du logiciel.
 * 
 */
public class MiniSocs {
    /**
     * le nom du système.
     */
    private final String nomDuSysteme;
    /**
     *Map des utilisateurs du système.
     */
    private final Map<String, Utilisateur> utilisateurs;
    /**
     *Map des réseaux sociaux du système.
     */
    private final Map<String, ReseauSocial> reseauxSociaux;

    /**
     * Temps d'attente après l'envoie de notifications.
     */
    private static final int TIMESLOT = 100;
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
        return nomDuSysteme != null && !nomDuSysteme.isBlank() && utilisateurs != null && reseauxSociaux != null;
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
     * liste les réseaux sociaux.
     * 
     * @return la liste des pseudonymes des utilisateurs.
     */

    public List<String> listerReseaux() {
		return reseauxSociaux.values().stream().map(ReseauSocial::toString).toList();
	}


    /**
     * @return les réseau sociaux
     */
    public Map<String, ReseauSocial> getReseauxSociaux() {
		return reseauxSociaux;
	}
	
	/**
	 * @return les utilisateurs
	 */
	public Map<String, Utilisateur> getUtilisateurs() {
		return utilisateurs;
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
     * bloquer un compte utilisateur.
     * 
     * @param pseudo le pseudo de l'utilisateur.
     * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
     */

    public void bloquerCompteUtilisateur(final String pseudo) throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			return;
		}
		u.bloquerCompte();
		assert invariant();
	}
    
    /**
     * créer un réseau social.
     * 
     * @param pseudoUtilisateur le pseudo de l'utilisateur.
     * @param pseudoMembre le pseudo de l'utilisateur dans ce réseau
     * @param nomReseau le nom de ce réseau
     * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
     */
   
	public void creerReseauSocial(final String pseudoUtilisateur,
    final String pseudoMembre, final String nomReseau)
     throws OperationImpossible {
		if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
			throw new OperationImpossible("pseudo utilisateur ne peut pas être null ou vide");
		}
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("le nom du réseau ne peut pas être null ou vide");
		}
		if (pseudoMembre == null || pseudoMembre.isBlank()) {
			throw new OperationImpossible("pseudo membre ne peut pas être null ou vide");
		}
		ReseauSocial r = reseauxSociaux.get(nomReseau);
		if (r != null) {
			throw new OperationImpossible("un réseau avec ce nom existe");	
		}
		Utilisateur u = utilisateurs.get(pseudoUtilisateur);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudoUtilisateur + ")");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			throw new OperationImpossible("le compte est bloqué");
		}
		if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
			throw new OperationImpossible("le compte est DESACTIVE");	
		}
        r = new ReseauSocial(nomReseau, true);
        MonConsommateur conso = new MonConsommateur(EtatStrategie.IMMEDIAT);
		reseauxSociaux.put(nomReseau, r);
		r.ajouterMembre(new Membre(pseudoMembre, true, r, conso));
        u.ajouterMembre(r.getMembres().get(pseudoMembre));
        r.getProducteurMessageEnAttente().subscribe(conso);
        r.getProducteurMessagePoste().subscribe(conso);
		assert invariant();
	}   
    
    /**     * 

     * ajouter un membre à un réseau social.
     * 
     * @param pseudoUtilisateurMod le pseudo Utilisateur du moderateur
     * @param pseudoModerateur le pseudo du modérateur
     * @param pseudoUtilisateur le pseudo de l'utilisateur à ajouter dans le réseau.
     * @param pseudoMembre le pseudo de l'utilisateur dans ce réseau
     * @param nomReseau le nom de ce réseau
     * @param etatStrategie une chaine de caractère spécifiant l'etat du stratégie
     * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
     */
    
	public void ajouterMembre(final String pseudoUtilisateurMod, final String pseudoModerateur,
     final String nomReseau, final String pseudoUtilisateur, final String pseudoMembre, final String etatStrategie) 
	throws OperationImpossible {
        if (pseudoUtilisateurMod == null || pseudoUtilisateurMod.isBlank()) {
			throw new OperationImpossible("pseudo utilisateur ne peut pas être null ou vide");
		}
		if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
			throw new OperationImpossible("pseudo utilisateur ne peut pas être null ou vide");
		}
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("le nom du réseau ne peut pas être null ou vide");
		}
		if (pseudoMembre == null || pseudoMembre.isBlank()) {
			throw new OperationImpossible("pseudo membre ne peut pas être null ou vide");
		}
		if (pseudoModerateur == null || pseudoModerateur.isBlank()) {
			throw new OperationImpossible("pseudo modérateur ne peut pas être null ou vide");
		}
        if (!etatStrategie.equals("immédiat") && !etatStrategie.equals("quotidien") && !etatStrategie.equals("pas de notifications")) {
			throw new OperationImpossible("Le type ed stratégie doit etre soit 'immédiat' ou 'quotidien' ou 'pas de notifications'");
        }
		ReseauSocial r = reseauxSociaux.get(nomReseau);
		if (r == null) {
			throw new OperationImpossible("le réseau social doit exister");
		}
		if (!r.estOuvert()) {
			throw new OperationImpossible("le réseau social doit etre OUVERT");
		}
		Membre mod = r.getMembres().get(pseudoModerateur);
		if (mod == null) {
			throw new OperationImpossible("l'utilisateur doit appartenir à ce réseau social(" + nomReseau + ")");
		}
        Utilisateur modU = utilisateurs.get(pseudoUtilisateurMod);  
        if (modU == null) {
            throw new OperationImpossible("l'utilisateur (modérateur) doit avoir une compte sur le réseau");
        }
		if (!modU.getEtatCompte().equals(EtatCompte.ACTIF)) {
			throw new OperationImpossible("l'utilisateur doit avoir un compte actif");
		}
		if (!mod.estModerateur()) {
			throw new OperationImpossible("l'utilisateur doit etre un modérateur de ce réseau social et non pas seulement un membre");
		}
        if (modU.getMembres().get(pseudoModerateur) != mod) {
			throw new OperationImpossible("l'utilisateur ne correspond pas au compte moderateur mentionné");         
        }
		Utilisateur u = utilisateurs.get(pseudoUtilisateur);
		Membre nm = r.getMembres().get(pseudoMembre);
		if (u == null) {
			throw new OperationImpossible("l'utilisateur à ajouter doit exister sur MiniSoc");
		}
		if (!u.getEtatCompte().equals(EtatCompte.ACTIF)) {
			throw new OperationImpossible("l'utilisateur à ajouter doit avoir un compte actif");
		}
		if (nm != null) {
			throw new OperationImpossible("Un membre avec un pseudonyme similaire existe ");
		}

		// verifier si l'utilisateur existe dans le réseau social sous un autre pseudonyme
		for (Map.Entry<String, Membre> entry : u.getMembres().entrySet()) {
			Membre it = entry.getValue();
			if (r.equals(it.getReseauSocial())) {
				throw new OperationImpossible("L'utilisateur existe dans le réseau social sous un autre pseudo");	
			}
		}
        MonConsommateur conso = new MonConsommateur(EtatStrategie.fromNom(etatStrategie));
		nm = new Membre(pseudoMembre, false, r, conso);
		r.ajouterMembre(nm);
        u.ajouterMembre(nm);
        r.getProducteurMessagePoste().subscribe(conso);         
		assert invariant();
	}

    
    /**
     * @param pseudoUtilisateur le pseudo de l'utilisateur
     * @param contenu le contenu du message
     * @param pseudoMembre le pseudo du membre
     * @param nomReseau le nom du réseau
     * @return l'id du message (ajouté pour la phase test)
     * @throws OperationImpossible lorsque poster message ne fonctionne pas
     * @throws InterruptedException ..
     */
    public Long posterMessage(final String pseudoUtilisateur, final String contenu, final String pseudoMembre, final String nomReseau)
			throws OperationImpossible, InterruptedException {
		
        if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
			throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
		}
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("nomReseau ne peut pas être null ou vide");
		}
		if (pseudoMembre == null || pseudoMembre.isBlank()) {
			throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
		}
		if (contenu == null || contenu.isBlank()) {
			throw new OperationImpossible("le contenu du message ne peut pas être null ou vide");
		}

        ReseauSocial r = reseauxSociaux.get(nomReseau);
        Utilisateur u = utilisateurs.get(pseudoUtilisateur);
		if (r == null) {
			throw new OperationImpossible("Réseau avec nom (" + nomReseau + " ) n'existe pas. ");
		}
        if (u == null) {
			throw new OperationImpossible("Utilisateur inexistant");
		}
        Membre m = r.getMembres().get(pseudoMembre);
		if (m == null) {
			throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas dans le reseau (" + nomReseau + "). ");
		}
		if (!r.estOuvert()) {
			throw new OperationImpossible("le réseau est fermé");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			throw new OperationImpossible("le compte est bloqué");
		}
		if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
			throw new OperationImpossible("le compte est desactivé");
		}
        if (u.getMembres().get(pseudoMembre) != m) {
            throw new OperationImpossible("le pseudo du membre indiqué ne correspond pas à cette utilisateur");
        }
		
		//creer une instance de ce message avec l'etatMessage correspondnat 
		
		Message message = new Message(contenu);
        // retourner l'id message a celui qui a posté

		r.posterMessageDansunReseau(message, pseudoMembre);
        assert invariant();
        return message.getId();
	}
    
    /**
     * Un modérateur accepte un Message (en ce moment posté et en attente), si acceptation est true alors l'etatMessage devient VISIBLE
     * si non l'étaMessage devient REJETÉ .
     * 
     * @param pseudoModUtilisateur   le pseudonyme de l'utilisateur qui va accepter le message.
     * @param pseudonymeModerateur   le pseudo membre de l'utilsateur.
     * @param nomReseau le nom du reseau auquel appartient le moderateur
     * @param idMessage      l'id du message à accepter 
     * @param acceptation prend vrai si le message est accepté et faux si non
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */
    public void modereMessage(final String pseudoModUtilisateur, final String pseudonymeModerateur, final String nomReseau, final Long idMessage, final boolean acceptation)
            throws OperationImpossible, InterruptedException {
        if (pseudoModUtilisateur == null || pseudoModUtilisateur.isBlank()) {
            throw new OperationImpossible("pseudonyme utilisateur ne peut pas être null ou vide");
        }        
        if (pseudonymeModerateur == null || pseudonymeModerateur.isBlank()) {
            throw new OperationImpossible("pseudonyme moderateur ne peut pas être null ou vide");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (idMessage == null) {
            throw new OperationImpossible("l'id du message ne peut pas être null");
        }
        
        ReseauSocial rs = reseauxSociaux.get(nomReseau);
        if (rs == null) {
            throw new OperationImpossible("reseau inexistant");
        }
        if (!rs.estOuvert()) {
            throw new OperationImpossible("Le réseau est fermé");
        }
        Membre m = rs.getMembres().get(pseudonymeModerateur);
        if (m == null) {
			throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudonymeModerateur + " ) n'existe pas dans le reseau (" + nomReseau + "). ");
		}
        Utilisateur u = utilisateurs.get(pseudoModUtilisateur);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }

        if (m != u.getMembres().get(pseudonymeModerateur)) {
            throw new OperationImpossible("le compte moderateur ne correspond pas au compte utilisateur"); 
        }
    
        if (!m.estModerateur()) {
            throw new OperationImpossible("vous n'êtes pas modérateur, vous ne pouvez pas moderer un message");
        }
        
        Message mess = rs.getMessages().get(idMessage);
        if (mess == null) {
            throw new OperationImpossible("Message avec cet id (" + idMessage + " ) n'existe pas dans le reseau. ");
        }
        if (!mess.getEtatMessage().equals(EtatMessage.ENATTENTE)) {
            throw new OperationImpossible("Message deja modéré ");
        }
        mess.modererMessage(acceptation); 
        
        if (acceptation) {
            rs.getProducteurMessagePoste().submit(mess);
        }
        Thread.sleep(TIMESLOT);
        
        assert invariant();
    }
    /**
     * Le modérateur peut fermer le réseau social d'un membre. 
     * 
     * @param pseudoUtilisateur   le pseudonyme de l'utilisateur qui va accepter le message.
     * @param pseudoMembre   le pseudo membre de l'utilsateur.
     * @param nomReseau     l'id du message à accepter 
     * @throws OperationImpossible en cas de problème sur les pré-conditions.
     */


    public void fermerReseau(final String pseudoUtilisateur, final String pseudoMembre, final String nomReseau)
            throws OperationImpossible {

        if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (pseudoMembre == null || pseudoMembre.isBlank()) {
            throw new OperationImpossible("pseudo_membre ne peut pas être null ou vide");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("le nom de réseau ne peut pas être ni null ni vide");
        }
        
        ReseauSocial r = reseauxSociaux.get(nomReseau);
        if (r == null) {
            throw new OperationImpossible("Reseau avec ce nom (" + nomReseau + " ) n'existe pas. ");
        }
        
        if (!r.estOuvert()) {
            throw new OperationImpossible("le réseau est fermé");
        }
        Membre m = r.getMembres().get(pseudoMembre);
        if (m == null) {
            throw new OperationImpossible("Membre avec ce pseudonyme (" + pseudoMembre + " ) n'existe pas dans ce réseau. ");
        }
        if (!m.estModerateur()) {
            throw new OperationImpossible("vous n'êtes pas modérateur, vous ne pouvez pas fermer le réseau");
        }
        Utilisateur u = utilisateurs.get(pseudoUtilisateur);
        if (u == null) {
            throw new OperationImpossible("utilisateur inexistant");
        }
        if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
            throw new OperationImpossible("le compte est bloqué");
        }
        if (u.getEtatCompte().equals(EtatCompte.DESACTIVE)) {
            throw new OperationImpossible("le compte est desactivé");
        }
        if (u.getMembres().get(pseudoMembre) != m) {
            throw new OperationImpossible("le compte modérateur ne correspond pas à cet utilisateur");      
        }
        r.fermerReseau();
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

