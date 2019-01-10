package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.ConversationView;

public class ListenerSendButton implements ActionListener{

	ConversationView cv;
	
	public ListenerSendButton(ConversationView c)
	{
		cv = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String contenu = cv.getConversation().getDest()  + cv.getJTextArea().getText();
		cv.sendMessage(contenu);
	}

}
