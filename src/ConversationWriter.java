import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.net.*;

public class ConversationWriter extends Thread{

	private Socket sock;
	private InetAddress ipToSend;
	private Historique historique ;
	private Utilisateur user;
	
	public ConversationWriter(Utilisateur user, Socket sock,InetAddress ipToSend,Historique historique) {
		this.sock = sock;
		this.ipToSend = ipToSend;
		this.historique = historique ;
		this.user = user;
	}
	
	
	public void run() {
		try {
			
			System.out.println("Veuillez saisir votre message");

			String Message = "azertfslnqf";
			PrintWriter out = new PrintWriter (sock.getOutputStream(),true);
			
			byte[] buffer = new byte[8192];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			// TESTS
			MessageTexte MessTxt = new MessageTexte (this.ipToSend,this.user);
			PartagerListe Partage = new PartagerListe (this.user,this.ipToSend);
			Notifier Notif = new Notifier(this.ipToSend,this.user);
			
			while (!Message.equals("end")) {
				
				//Message = MessTxt.GetMessage();
				//Message = Partage.ConstructionListe();
				Message = Notif.NotifierDepart();
				
				System.out.println(Message);
				out.println(Message);
	        	out.flush();

			}

			System.out.println("Vous avez demandé la fin de la conversation");
			this.sock.close();
			
		}catch (IOException e) {
			if (!e.getMessage().equals("I/O error: Socket is closed")) {
				e.printStackTrace();
			};
			
		}
		
	}
	
	
}
