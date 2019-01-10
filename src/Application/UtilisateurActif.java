package Application;
import java.net.InetAddress;
public class UtilisateurActif {
	
	private String Identifiant; 
	private String Pseudo;
	private InetAddress adrIP;
	private Boolean enCoursDeConversation ;
	
	public UtilisateurActif(String pseudo, String Identifiant, InetAddress adrip) {

		this.Pseudo = pseudo;
		this.Identifiant = Identifiant;
		this.adrIP = adrip;
		this.enCoursDeConversation = false;
	}
	
	public String GetIdentifiant() {
		return Identifiant;
	}
	
	public String GetPseudo() {
		return Pseudo;
	}
	
	public InetAddress getInetAddress () {
		return this.adrIP;
	}
	
	public String GetIdentifiantetPseudo() {
		return (this.Identifiant+"#"+this.Pseudo);
	}
	
	public void setEnCoursDeConversation (boolean val) {
		this.enCoursDeConversation = val ;
	}
	
	public boolean getEnCoursDeConversation () {
		return this.enCoursDeConversation;
	}
}

