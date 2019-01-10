import java.net.*;
import java.util.*;

public class MessageTexte extends Messages{

		private String Message ;
		
		public MessageTexte(InetAddress adrDest, Utilisateur expediteur) {
			super.addr_dest = adrDest ;
			super.user = expediteur;
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
