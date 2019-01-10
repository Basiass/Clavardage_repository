import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PartagerListe extends Messages {

	public PartagerListe(Utilisateur user, InetAddress adrDest) {
		super.addr_dest = adrDest ;
		super.user = user;
	}
	
	public String ConstructionListe() {
		String messageToSend = "3";
		// Construit à partir de la liste des utilisateurs connectés un message.
		
		
		ArrayList<UtilisateurActif> list = this.user.GetListeUtilisateursActifs();
		
		Iterator<UtilisateurActif> iterator = list.iterator();
		while (iterator.hasNext()) {
			messageToSend += iterator.next().GetIdentifiantetPseudo() + "#";
		}
		return messageToSend;
	}
	
	public void TraitementListe(String message) {
		
		// TODO
		user.SetListeUsersActifs(message, this.addr_dest);
		
		
	}
	
	

}
