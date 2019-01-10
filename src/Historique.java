import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Historique {

	private Utilisateur user1;
	private Utilisateur user2;
	private String Message;
	private Array CouplesUtilisateurs;
	private ArrayList ListeMessages;
	
	public Historique () {}
	
	public int GetNumCoupleUtilisateurs (Utilisateur user1, Utilisateur user2) {
		Integer num = 0;
		return num;
	}
	public ArrayList TrouverHistorique (Integer numconversation) {
		ArrayList<MessageTexte> ListeMessages = new ArrayList <MessageTexte>();
		return ListeMessages;
	}
	
	public boolean StockerDansHistorique (MessageTexte Message) {
		return true;
	}
}
