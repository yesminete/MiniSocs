package eu.telecomsudparis.csc4102.minisocs;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Sabrine Azaiez
 */
public class Message {
	/**
	 * le contenu du message.
	 */
	private String contenu;
	/**
	 * l'état du message.
	 */
	private EtatMessage status;


	/**
	 * construit une instance du message
	 * 
	 * @param contenu le contenu du messgae.
	 */
	public Message (final String contenu, final EtatMessage status) {
		if (contenu== null || contenu.isBlank()) {
			throw new IllegalArgumentException("le contenu d'un message ne peut pas etre ni vide ni null");
		}
		this.contenu = contenu;
		this.status = status;
		
		assert invariant();
	}


	/**
	 * l'invariant de la façade.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return contenu != null && !contenu.isBlank()  && status!= null ;
	}


	/**
	 * obtient le contenu.
	 * 
	 * @return le contenu.
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * obtient l'état du message.
	 * 
	 * @return l'état du message
	 */
	
	public EtatMessage getEtatMessage() {
		return status;
	}
	/**
	 * accepter le message posté du membre 
	 */
	public void AccepterMessage() {
		if (status == EtatMessage.ENATTENTE){
			
			status = EtatMessage.VISIBLE;
		}	
	}
	
	public void RefuserMessage() {
		if (status == EtatMessage.ENATTENTE){	
			status = EtatMessage.REJETE;
		}	
	}
	
	public void CacherMessage() {
		if (status == EtatMessage.VISIBLE){
			
			status = EtatMessage.CACHÉ;
		}	
	}
	
	public void RendeVisibleMessage() {
		if (status == EtatMessage.CACHÉ){
			
			status = EtatMessage.VISIBLE;
		}	
	}
	
	@Override
	public String toString() {
		return "Message [contenu=" + contenu + " , status= " + status+ "]" ;
	}
}
