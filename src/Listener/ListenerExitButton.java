package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.ConversationView;

public class ListenerExitButton implements ActionListener {

	ConversationView cv;
	
	public ListenerExitButton(ConversationView c)
	{
		cv = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		cv.fermerConversation() ;
	}
}
