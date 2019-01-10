package Broadcast;
	import java.io.IOException;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;
	import java.net.InetSocketAddress;
	import java.net.Socket;

import Application.Utilisateur;
	
public class ReceiveBroadcast extends Thread {

	// recevoir la présence
	    private Utilisateur user;
	    private static DatagramSocket socket = null;
	    
	    public ReceiveBroadcast(Utilisateur user, DatagramSocket SocketLocal) {
	        this.user = user;
	        this.socket = SocketLocal;
	    }
	    
	    
	    
	    public void run() {
	        byte[] buffer = new byte[256];
	        DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
	        try {
	            socket.receive(inPacket);
	            
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        String Actif = new String(inPacket.getData(), 0, inPacket.getLength());
	        
	        Notifier Notif = new Notifier (null,this.user);
	        Notif.TraitementNotifPresence(Actif);
	        
	        
	        
	        
	        if (this.user.GetLastUserConnected()) {
	            PartagerListe Partage = new PartagerListe(this.user,inPacket.getAddress());
	            String liste = Partage.ConstructionListe();
	            // ENVOI VIA UN NV CONV WRITER
	            ConversationWriter partage = new ConversationWriter(this.user,inPacket.getAddress(), liste,1);
	            partage.run();
	            // envoyer liste à l'utilisateur
	        }
	    }

	}

}
