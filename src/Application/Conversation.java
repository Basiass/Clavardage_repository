package Application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.ConversationView;

public class Conversation extends Thread {
	
	private Socket sock;
	private Utilisateur user;
	private Integer port = 51000;
	private UtilisateurActif dest ;
	
	private ArrayList<Socket> listSocket ;
	
	private ConversationView convView ;
	private ConversationListener listener;
	
	public Conversation(Socket sock) {
		this.sock = sock ;
		convView = new ConversationView (this);
		listener = new ConversationListener(this.user,sock,this);
		listener.start();
		
		//this.dest = new UtilisateurActif ();
	}

	
	public Conversation (Utilisateur user) {
		this.user = user ;
		this.sock = new Socket();
		convView = new ConversationView (this);
	}
	
	
	public void initiate (UtilisateurActif dest){
		
		String ipadress = "10.0.0.1";
		this.dest = dest ;
		
		try {
			sock.connect(new InetSocketAddress(InetAddress.getByName(ipadress), port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		// Test ajout des utilisateurs actifs à la main
		//this.user.AjouterUserActif(new UtilisateurActif("eva","eva",InetAddress.getLocalHost()));
		//this.user.AjouterUserActif(new UtilisateurActif("mel","mel",InetAddress.getLocalHost()));
		//this.user.AjouterUserActif(new UtilisateurActif("mael","mael",InetAddress.getLocalHost()));

		
		listener = new ConversationListener(this.user,sock,this);
		listener.start();
		
		// AFFICHE LA LISTE DES IP DES PERSONNES AVEC QUI ON A UNE CONV EN COURS
		//Iterator<String> iterator = listIP.iterator();
		//while (iterator.hasNext()) {
		//	System.out.println(iterator.next());
		//}
		
	}

	public void sendMessage(String contenu) {
		PrintWriter out;
		
		try {
			out = new PrintWriter (this.sock.getOutputStream(),true);
			System.out.println(contenu);
			out.println(contenu);
	    	out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public void receiveMessage(String contenu) {
		convView.receive(contenu);
	}


	
	
	public void terminerConversation () {
		listener.terminerListener();
	}

	
	
	/*
	public void fermerToutesConversation() {
		
		try {
			//fermer tous  sockets liés aux conversations en cours
			for (Socket s : listSocket)
			{
				s.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 */
	
	
	
	
	
	
	// but envoyer useractif dest 
	
	// pour envoyer des objets
	//ois = new ObjectInputStream(socket.getInputStream());
	//oos = new ObjectOutputStream(socket.getOutputStream());
	
	// l'enregister dans conversation
	// modifier l'affichage avec le pseudo heure et date du dest
	// classe message ? -> message texte ?
	
	// gestion en broadcast
	// envoie départ / arrivée / partagerliste

}
