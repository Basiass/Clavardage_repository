import java.io.*;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.awt.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Conversation extends Thread{
	
	private Socket SocketLocal;
	
	private Utilisateur user;
	private ConversationListener Listener;
	private ConversationWriter Writer ;
	private Historique Historique;
	
	// LISTE DES IP
	public ArrayList<String> listIP;
	public ServerSocket sockserv;
	
	public ArrayList<Socket> listSocket ;
	
	
	
	
	public Conversation(Utilisateur me, Historique historique) {
		
		this.user = me ;
		
		// CREE LE SERVEUR D'ECOUTE 
		try {
			this.sockserv = new ServerSocket(60010);
		} catch (IOException e) {
			System.err.println("ServerSocket couldn't be created");
			e.printStackTrace();
		}
		
		// CREE LA LISTE D'IP
		this.listIP = new ArrayList<String>();
		this.listSocket = new ArrayList<Socket>();
	}
	

	
	
	
	
	@Override
	public void run() {
		while(true){
			try {
				
				//this.user.SeConnecter();
				
				// ACCEPTER DES DEMANDES DE CO QUI ST REDIRIGEES VERS UN AUTRE SOCKET
				this.SocketLocal= this.sockserv.accept();
				System.out.println("demande de co acceptée");
				// RECUPERER ADR DU DEMANDEUR DE CONNEXION
				InetAddress AdrDest = SocketLocal.getInetAddress() ;
				
				// Mettre à jour la liste des IP avec qui on communique
				this.listIP.add(SocketLocal.getInetAddress().getHostAddress());                    ///////// VERIFIER QUE C'EST PAS NOTRE ADR
				this.listSocket.add(SocketLocal);
				
				Listener = new ConversationListener(this.user,SocketLocal,Historique);
				Listener.start();
				
				Writer = new ConversationWriter (this.user, SocketLocal,AdrDest, Historique);
				Writer.start();
				
			} catch (IOException e) {
				System.err.println("Client socket couldn't be created");
				e.printStackTrace();
			}
		}
	}

	
	public void initiate () throws IOException {
		
		String ipadress = "127.0.0.1";
		
		Socket sock = new Socket();
		try {
			sock.connect(new InetSocketAddress(InetAddress.getByName(ipadress), 60010));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		Writer = new ConversationWriter (this.user,sock,InetAddress.getByName(ipadress), Historique);
		this.listIP.add(InetAddress.getLocalHost().getHostAddress());
		this.user.AjouterUserActif(new UtilisateurActif("eva","eva",InetAddress.getLocalHost()));
		this.user.AjouterUserActif(new UtilisateurActif("mel","mel",InetAddress.getLocalHost()));
		this.user.AjouterUserActif(new UtilisateurActif("mael","mael",InetAddress.getLocalHost()));
		Writer.start();
		
		Listener = new ConversationListener(this.user,sock,Historique);
		Listener.start();
		
		// AFFICHE LA LISTE DES IP DES PERSONNES AVEC QUI ON A UNE CONV EN COURS
		Iterator<String> iterator = listIP.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
	
	
	public void fermerConversation() {
		
		// FERMER LES THREADS
		
		try {
			// fermer le socket qui fait l'accept
			this.sockserv.close();
			//fermer tous les autres sockets liés aux conversations en cours
			Iterator<Socket> iterator = listSocket.iterator();
			while (iterator.hasNext()) {
				iterator.next().close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Supprimer IP de la liste d'ip
	// fermer les fenetres et les threads
	
}
