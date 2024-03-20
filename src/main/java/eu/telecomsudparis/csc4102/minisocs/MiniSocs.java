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
	/**
	 * les utilisateurs.
	 */
	private final Map<String, Utilisateur> utilisateurs;
	private final Map<String, ReseauSocial> reseauSociaux;
	/**
	 * construit une instance du système.
	 * 
	 * @param nomDuSysteme le nom.
	 */
	public MiniSocs(final String nomDuSysteme) {
		this.nomDuSysteme = nomDuSysteme;
		this.utilisateurs = new HashMap<>();
		this.reseauSociaux = new HashMap<>();
	}

	/**
	 * l'invariant de la façade.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomDuSysteme != null && !nomDuSysteme.isBlank() && utilisateurs != null && reseauSociaux!=null;
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

	public List<String> listerReseaux() {
		return reseauSociaux.values().stream().map(ReseauSocial::toString).toList();
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

	public void creerReseauSocial(final String pseudoUtilisateur,final String pseudoMembre, final String nomReseau) throws OperationImpossible {
		if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
			throw new OperationImpossible("pseudo utilisateur ne peut pas être null ou vide");
		}
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("le nom du réseau ne peut pas être null ou vide");
		}
		if (pseudoMembre == null || pseudoMembre.isBlank()) {
			throw new OperationImpossible("pseudo membre ne peut pas être null ou vide");
		}
		ReseauSocial r = reseauSociaux.get(nomReseau);
		if (r!=null) {
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
		reseauSociaux.put(nomReseau,new ReseauSocial(nomReseau,true));
		reseauSociaux.get(nomReseau).ajouterMembre(new Membre(u,pseudoMembre,true,reseauSociaux.get(nomReseau)));
		assert invariant();
	}
	public void ajouterMembre(final String pseudoModerateur, final String nomReseau, final String pseudoUtilisateur, final String pseudoMembre) 
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
		if (pseudoModerateur == null || pseudoModerateur.isBlank()) {
			throw new OperationImpossible("pseudo modérateur ne peut pas être null ou vide");
		}
		ReseauSocial r = reseauSociaux.get(nomReseau);
		if(r==null){
			throw new OperationImpossible("le réseau social doit exister");
		}
		if(!r.estOuvert()){
			throw new OperationImpossible("le réseau social doit etre OUVERT");
		}
		Membre mod = r.getMembres().get(pseudoModerateur);
		if(mod==null){
			throw new OperationImpossible("l'utilisateur doit etre un membre de ce réseau social");
		}
		if(!mod.getEtatCompte().equals(EtatCompte.ACTIF)){
			throw new OperationImpossible("l'utilisateur doit avoir un compte actif");
		}
		if(!mod.estModerateur()){
			throw new OperationImpossible("l'utilisateur doit etre un modérateur de ce réseau social");
		}
		Utilisateur u = utilisateurs.get(pseudoUtilisateur);
		Membre nm = r.getMembres().get(pseudoMembre);
		if(u==null){
			throw new OperationImpossible("l'utilisateur à ajouter doit etre existant");
		}
		if(!u.getEtatCompte().equals(EtatCompte.ACTIF)){
			throw new OperationImpossible("l'utilisateur à ajouter doit avoir un compte actif");
		}
		if(nm!=null){
			throw new OperationImpossible("Un membre avec un pseudonyme similaire existe ");
		}

		// verifier si l'utilisateur existe dans le réseau social sous un autre pseudonyme
		for (Map.Entry<String, Membre> entry : r.getMembres().entrySet()) {
			Membre it = entry.getValue();
			if(it.equals(u)){
				throw new OperationImpossible("L'utilisateur existe dans le réseau social");	
			}
		}
		nm = new Membre(u,pseudoMembre, false,r);
		r.ajouterMembre(nm);
		assert invariant();
	}
}
