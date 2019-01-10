import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ConversationListener extends Thread{

	private Socket sock;
	private InetAddress ipToSend;
	private Historique historique ;
	private Utilisateur user;
	
	public ConversationListener(Utilisateur user, Socket SocketLocal, Historique Historique) {
		this.sock = SocketLocal ;
		this.ipToSend = SocketLocal.getInetAddress();
		this.historique = Historique ;
		this.user=user;
	}
	
	public void run() {
		String message_distant = "jobijoba";
		
		while (!message_distant.equals("end")) {
		  try {
	
	            BufferedReader input = new BufferedReader (new InputStreamReader(sock.getInputStream()));
	            
	            message_distant = input.readLine();
	            
	            String carac = message_distant.substring(0,1);
	            
	            switch (carac) {
	            case "0":
	            	// affiche le message sans le premier caractère
	            	System.out.println(message_distant.substring(1));
	            	// Gestion de l'affichage à faire dans MessageTexte.Traitement ?
	            	break;
	            	
	            case "1":
	            	// CAS NOTIFICATION PRESENCE
	            	Notifier Notif = new Notifier (this.ipToSend,this.user);
	            	Notif.TraitementNotifPresence(message_distant.substring(1));
	            	
	            	if (this.user.GetLastUserConnected()) {
						PartagerListe Partage = new PartagerListe(this.user,this.ipToSend);
						String liste = Partage.ConstructionListe();
						
						// envoyer liste à l'utilisateur
					}
		           
	            	break;
	            	
	            case "2":
	            	// CAS NOTIFICATION ABSCENCE
	            	Notifier Notif1 = new Notifier (this.ipToSend,this.user);
	            	Notif1.TraitementNotifAbscence(message_distant.substring(1));
	            	
	            	// fermer le socket lié à la conv avec la personne ?
	            	break;
	       
	            case "3":
	            	// CAS PARTAGE DE LISTE
	            	// Màj de la liste
	            	PartagerListe Partage = new PartagerListe (this.user,this.ipToSend);
	            	Partage.TraitementListe(message_distant.substring(1));
	            	break;
	            	
	            default:
	            	break;
	            }
	            
				
	            
	        
	        } catch (UnknownHostException ex) {
	 
	            System.out.println("Server not found: " + ex.getMessage());
	 
	        } catch (IOException ex) {
	 
	        	if (!ex.getMessage().equals("I/O error: Socket is closed")) {
					ex.printStackTrace();
				};
	        }
		  
		}
		
		try {
			System.out.println("La conversation est terminée");
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
