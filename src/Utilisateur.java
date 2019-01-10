import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class Utilisateur {
	private String Identifiant; // Supposé unique par authentification préalable 
								// On le rentre au début de la connexion
	private String Pseudo;
	private Boolean Etat ; // vrai si connecté
	private Boolean LastUserConnected; // vrai si dernier user connecté
	private InetAddress adrIP;
	
	private ArrayList<UtilisateurActif> ListeUsersActifs; // Liste des utilisateurs
	
	public Utilisateur(String pseudo, String Identifiant) {
		
		this.Pseudo = pseudo;
		this.Identifiant = Identifiant;
		this.Etat = true ; // à la création, il se connecte
		this.LastUserConnected = true ;// à la création c'est le dernier utilisateur connecté
		try {
			this.adrIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Création de la liste
		this.ListeUsersActifs = new ArrayList <UtilisateurActif> (); 
		
	}

	public String getIdentifiant() {
		return Identifiant;
	}
	
	public String getPseudo() {
		return Pseudo;
	}
	
	public void SeConnecter() {
		
		try {
			String data = "1"+ this.Identifiant +"#" + this.Pseudo;
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			InetAddress broadcastAddress = getBroadcastAddress(); // elle sort d'où??
			DatagramPacket packet = new DatagramPacket(data.getBytes(),	data.getBytes().length, broadcastAddress, 50000);
			socket.send(packet);
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void SeDeconnecter() {
		try {
			String data = "2"+ this.Identifiant +"#" + this.Pseudo;
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			InetAddress broadcastAddress = socket.getBroadcastAddress(); // elle sort d'où??
			DatagramPacket packet = new DatagramPacket(data.getBytes(),	data.getBytes().length, broadcastAddress, 50000);
			socket.send(packet);
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void SetListeUsersActifs(String message, InetAddress ipExpediteur) {
		// mise à jours liste + personne qui nous l'a envoyé
		// séparée pas des # 
		
		
		// POUR L'INSTANT ON DONNE @IP
		// IL FAUDRAIT RECUPERER LES PSEUDO PAR UNE STRUCTURE : HASH MAP dans conv ?
	}
	
	public ArrayList<UtilisateurActif> GetListeUtilisateursActifs(){
		return this.ListeUsersActifs;
	}
	
	public void AjouterUserActif(UtilisateurActif useractif){
		ListeUsersActifs.add(useractif);
	}
	
	
	public void SupprimerUserActif(UtilisateurActif userToDelete){
		
		Iterator<UtilisateurActif> iterator = this.ListeUsersActifs.iterator();
		while ( iterator.hasNext() ) {
		    UtilisateurActif user = iterator.next();
		    if (user.equals(userToDelete)) {
		        // On supprime l'élément courant de la liste
		        iterator.remove();
		        break;
		    }
		}
	}
	

	// il faudra vérifier l'unicité
	public void SetPseudo(String pseudo) {
		this.Pseudo = pseudo;
	}
	
	
	public Boolean GetEtat() {
		return Etat;
	}
	
	public Boolean GetLastUserConnected() {
		return LastUserConnected;
	}
	
	public Utilisateur GetUtilisateur (String Identifiant) {
		//Chercher dans la liste des utilisateurs actifs pour renvoyer l'utilisateur demandé
		Utilisateur user = null;
		return user;
	}
	
	
	public static void main(String[] args) {
	    
		// Creer user Me
		Utilisateur Me = new Utilisateur("eva", "eva@insa-toulouse.fr");
		Historique historique = new Historique();
	
		
		// ENVOI DE LA NOTIF DE PRESENCE
		/* BROADCAST  : 
		 * Choisir à quelle adresse réseau on se co
		 * Récupérer l'@ IP broadcast du rzo
		 * Envoyer en mode UDP : 
		 * https://openclassrooms.com/fr/courses/2654601-java-et-la-programmation-reseau/2668962-communication-reseau-avec-le-protocole-udp
		 * je pense que l'on peut reprendre le code de ce site
		 */
		
		// SI L'UTILISATEUR VEUT INITIER UNE CONVERSATION
		Conversation conv = new Conversation(Me, historique);
		
		conv.start();
		
		try {
			conv.initiate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conv.fermerConversation();
	
		// ENVOI DE LA NOTIF D'ABSCENCE
		/*
		 * Fermer toutes les sockets lors de la déconnection :
		 * Si dans un thread fermée, alors fermée partout !
		 */
	}
	
}
