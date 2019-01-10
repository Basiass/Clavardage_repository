package Broadcast;
import java.net.*;
import java.util.Date;

import Application.Utilisateur;
import Application.UtilisateurActif;

public class Notifier {
	
	private Utilisateur user;
	private InetAddress addr_dest ;
	
	public Notifier (InetAddress adrDest, Utilisateur user) {
		this.addr_dest = adrDest ;
		this.user = user;
	}
	
	
	//////////// A TESTER
	public void TraitementNotifPresence (String mess) {
		
		// recup identifiant et pseudo
		
		String identifiant ;
		String carac = mess.substring(0,1);
		Integer compteur = 0;
		while (!carac.equals("#")) {
			compteur ++;
			carac = mess.substring(compteur, compteur+1);
		}
		identifiant = mess.substring(0,compteur);
		
		// IDEM ENSUITE POUR LE PSEUDO
		String pseudo;
		Integer compteurpseudo = 0 ;
		while (!carac.equals("#")) {
			compteurpseudo ++;
			carac = mess.substring(compteur, compteur+1);
		}
		pseudo = mess.substring(compteur,compteurpseudo);
		
		UtilisateurActif userToAdd = new UtilisateurActif(pseudo,identifiant,this.addr_dest);
		this.user.AjouterUserActif(userToAdd);
		
	}
	
	public void TraitementNotifAbscence (String mess) {
		// meme façon de récupérer les infos
		String pseudo = "r";
		String identifiant = "r";
		UtilisateurActif userToDelete = new UtilisateurActif(pseudo,identifiant,this.addr_dest);
		this.user.SupprimerUserActif(userToDelete);	
	}

	
	public String NotifierDepart () {
		return ("2"+this.user.getIdentifiant() + "#" + this.user.getPseudo());
	}
	
	
	
}
