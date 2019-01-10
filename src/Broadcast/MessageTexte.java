package Broadcast;
import java.net.*;
import java.util.*;

import Application.Utilisateur;

public class MessageTexte{

		private String Message ;
		private Utilisateur user;
		private InetAddress addr_dest ;

		public MessageTexte(InetAddress adrDest, Utilisateur expediteur) {
			this.addr_dest = adrDest ;
			this.user = expediteur;
		}
		
		public String GetMessage() {
			Scanner sc = new Scanner(System.in);
			this.Message = "0"+ sc.nextLine();
			
			return this.Message;
		}
		
		public void TraitementMessage(String Message){
			this.Message = Message ;
			// TODO
		}
		
		public void SetMessage () {
			Scanner sc = new Scanner(System.in);
			this.Message = "0"+ sc.nextLine();
			sc.close();
		}
		
}
