package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Application.Conversation;
import Listener.ListenerSendButton;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

public class ConversationView {

	private JFrame frame;
	private DefaultListModel<String> dlm;
	private JTextArea textArea;
	private Conversation conv;

	/**
	 * Create the application.
	 */
	public ConversationView(Conversation c) {
		this.conv = c;
		initialize();
	}

	public Conversation getConversation() {
		return this.conv;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(6, 0, 6, 0));
		dlm = new DefaultListModel<String>();
		JList list = new JList(dlm);
		frame.getContentPane().add(list);
		
		this.textArea = new JTextArea();
		frame.getContentPane().add(textArea);
		
		JButton btnSend = new JButton("Envoyer");
		frame.getContentPane().add(btnSend);
		
		ListenerSendButton lsb = new ListenerSendButton(this);
		btnSend.addActionListener(lsb);
		
		JButton btnExit = new JButton("Exit");
		frame.getContentPane().add(btnExit);
		
		dlm.addElement("test");
	}
	
	public JTextArea getJTextArea()
	{
		return this.textArea;
	}

	public void sendMessage(String contenu) {
		dlm.addElement(contenu);	
		this.conv.sendMessage(contenu);
	}
	
	public void receive(String contenu) {
		dlm.addElement(contenu);
	}
	
	public void fermerConversation() {
		this.conv.terminerConversation();
	}

}
