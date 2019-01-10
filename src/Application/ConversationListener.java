package Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ConversationListener extends Thread{

	private Socket sock;
	private InetAddress ipToSend;
	private Utilisateur user;
	private Conversation conv;
	private boolean continuer = true ;
	
	public ConversationListener(Utilisateur user, Socket SocketLocal, Conversation conv) {
		this.sock = SocketLocal ;
		this.ipToSend = SocketLocal.getInetAddress();
		this.user=user;
		this.conv = conv ;
	}
	
	public void run() {
		
		String message_distant = "";
		
		while (continuer) {
		  try {
	
	            BufferedReader input = new BufferedReader (new InputStreamReader(sock.getInputStream()));
	            
	            message_distant = input.readLine();
	            System.out.println(message_distant);
	     
	            conv.receiveMessage(message_distant);
	          
	        
	        } catch (UnknownHostException ex) {
	 
	            System.out.println("Server not found: " + ex.getMessage());
	 
	        }catch (SocketException ex ) {
	        	
	        	continuer = false ;
	        	try {
					this.sock.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        } catch (IOException ex) {
				ex.printStackTrace();
	        } 
		  
		}
		
	}
	
	public void terminerListener () {
		this.continuer = false;
	}
}
