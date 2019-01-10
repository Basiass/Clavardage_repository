package Application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConversation extends Thread {
	
	private Integer port = 51000 ;
	private Socket SocketLocal;
	public ServerSocket sockserv;
	
	
	@Override
	public void run() {
		
		try {
			this.sockserv = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("ServerSocket couldn't be created");
			e.printStackTrace();
		}
		while(true){
			try {
				
				//this.user.SeConnecter();
				
				// ACCEPTER DES DEMANDES DE CO QUI ST REDIRIGEES VERS UN AUTRE SOCKET
				this.SocketLocal= this.sockserv.accept();
				System.out.println("demande de co acceptée");
				
				Conversation Conv = new Conversation ( this.SocketLocal);
				
			} catch (IOException e) {
				System.err.println("wait");
				e.printStackTrace();
			}
		}
	}
	
}
