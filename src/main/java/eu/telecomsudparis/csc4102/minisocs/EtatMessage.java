package eu.telecomsudparis.csc4102.minisocs;

/**
 * Ce type énuméré modélise le status d'un message.
 */
public enum EtatMessage {
	/**
	 * le message est accepté mais visible
	 */
	VISIBLE("visible"),
	/**
	 * le message est accepté mais caché 
	 */
	CACHÉ ("caché"),
	//Ici nrmlm on doit ajouter un autre type enuméré pour Accepté qui est visibl eet caché 
	/**
	 * le message est refusé.
	 */
	REJETE ("rejeté"),
	/**
	 * le message est en attente.
	 */
	ENATTENTE ("enAttente");
	
	/**
	 * le nom de l'état à afficher.
	 */
	private String nomM;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatMessage (final String nomM) {
		this.nomM = nomM;
	}

	@Override
	public String toString() {
		return nomM;
	}
}