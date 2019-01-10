package Broadcast;
	import java.io.IOException;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;
	import java.net.NetworkInterface;
	import java.net.SocketException;
	import java.util.ArrayList;
	import java.util.Enumeration;
	import java.util.Objects;
	
public class SendBroadcast extends Thread {


	//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
	// envoyer la présence
	    private static DatagramSocket socket = null;
	    private String data;
	    private InetAddress bdr;

	    public SendBroadcast(String data) throws SocketException {
	        this.data = data;
	        ArrayList<InetAddress> liste = listAllBroadcastAddresses();
	        this.bdr = liste.get(0);
	    }

//	        broadcast("Hello", InetAddress.getByName("255.255.255.255"));

	 
	    
	    // retourne une liste d'adresse de bdr
	    public ArrayList<InetAddress> listAllBroadcastAddresses() throws SocketException {
	         ArrayList<InetAddress> broadcastList = new ArrayList<>();
	         Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	         while (interfaces.hasMoreElements()) {
	        	 NetworkInterface networkInterface = interfaces.nextElement();
	        
	             if (networkInterface.isLoopback() || !networkInterface.isUp()) {
	                    continue;
	             }
	        
	             networkInterface.getInterfaceAddresses().stream()
	                  .map(a -> a.getBroadcast())
	                  .filter(Objects::nonNull)
	                  .forEach(broadcastList::add);
	        }
	        return broadcastList;
	   }

	    
	    
	    public void run(){
	        try {
				socket = new DatagramSocket();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				socket.setBroadcast(true);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte[] buffer = this.data.getBytes();
	 
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.bdr, 1235);
	        try {
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        socket.close();
	    }
}



