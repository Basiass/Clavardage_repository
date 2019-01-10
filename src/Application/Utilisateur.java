package Application;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import Broadcast.SendBroadcast;

public class Utilisateur {
	
	
	private String identifiant; // Supposé unique par authentification préalable 
								// On le rentre au début de la connexion
	private String pseudo;
	private Boolean etat ; 		// vrai si connecté
	private Boolean lastUserConnected; // vrai si dernier user connecté
	private InetAddress adrIP;
	
	private ArrayList<UtilisateurActif> listeUsersActifs; // Liste des utilisateurs
	
	public Utilisateur(String pseudo, String Identifiant) {
		
		this.pseudo = pseudo;
		this.identifiant = Identifiant;
		this.etat = true ; 
		this.lastUserConnected = true ;// à la création c'est le dernier utilisateur connecté
		try {
			this.adrIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Création de la liste
		this.listeUsersActifs = new ArrayList <UtilisateurActif> (); 
		
	}

	public String getIdentifiant() {
		return identifiant;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	/*
	public void SeConnecter() {
        String data = this.Identifiant +"#" + this.Pseudo +"#";
        try {
			SendBroadcast SendCo = new SendBroadcast(data);
			SendCo.start();
        } catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	
	public void SeDeconnecter() {
		try {
			String data = "2"+ this.Identifiant +"#" + this.Pseudo;
			Socket socket = new Socket();
			
			Iterator<UtilisateurActif> iterator = this.ListeUsersActifs.iterator();
			while ( iterator.hasNext() ) {
			    UtilisateurActif dest = iterator.next();
			    try {
					socket.connect(new InetSocketAddress(dest.getInetAddress(), 51000));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				PrintWriter out = new PrintWriter (socket.getOutputStream(),true);
				out.println(data);
	        	out.flush();
	        	socket.close();
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	*/
	
	public void SetListeUsersActifs(String message, InetAddress ipExpediteur) {
		// mise à jours liste + personne qui nous l'a envoyé
		// séparée pas des # 
		
		
		// POUR L'INSTANT ON DONNE @IP
		// IL FAUDRAIT RECUPERER LES PSEUDO PAR UNE STRUCTURE : HASH MAP dans conv ?
	}
	
	public ArrayList<UtilisateurActif> GetListeUtilisateursActifs(){
		return this.listeUsersActifs;
	}
	
	public void AjouterUserActif(UtilisateurActif useractif){
		listeUsersActifs.add(useractif);
	}
	
	
	public void SupprimerUserActif(UtilisateurActif userToDelete){
		
		
		for (UtilisateurActif userActif : listeUsersActifs)
		{
			if (userActif.equals(userToDelete)) { listeUsersActifs.remove(userActif);break;}
		}
	
	}
	

	// il faudra vérifier l'unicité
	public void SetPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	public Boolean GetEtat() {
		return etat;
	}
	
	public Boolean GetLastUserConnected() {
		return lastUserConnected;
	}
	
	/*
	public Utilisateur GetUtilisateur (String Identifiant) {
		//Chercher dans la liste des utilisateurs actifs pour renvoyer l'utilisateur demandé
		Utilisateur user = null;
		return user;
	}
	*/
	
	public static void main(String[] args) {
	    
		// Creer user Me
		 
		Utilisateur Me = new Utilisateur("mel", "mel@insa-toulouse.fr");
		Historique historique = new Historique();
		//Interface inter = new Interface();
		//inter.affichage();
		
		// ENVOI DE LA NOTIF DE PRESENCE
		/* BROADCAST  : 
		 * Choisir à quelle adresse réseau on se co
		 * Récupérer l'@ IP broadcast du rzo
		 * Envoyer en mode UDP : 
		 * https://openclassrooms.com/fr/courses/2654601-java-et-la-programmation-reseau/2668962-communication-reseau-avec-le-protocole-udp
		 * je pense que l'on peut reprendre le code de ce site
		 */
		
		// SI L'UTILISATEUR VEUT INITIER UNE CONVERSATION
		AcceptConversation acceptConv = new AcceptConversation();
		acceptConv.start();
		
		Conversation Conv = new Conversation (Me); 
		Conv.initiate(UtilisateurActif userDest);
		
		//conv.fermerConversation();
	
		// ENVOI DE LA NOTIF D'ABSCENCE
		/*
		 * Fermer toutes les sockets lors de la déconnection :
		 * Si dans un thread fermée, alors fermée partout !
		 */
	}
	
}
