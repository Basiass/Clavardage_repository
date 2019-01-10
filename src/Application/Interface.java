package Application;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface extends JPanel {
	private JPanel pan = new JPanel();
	private JFrame fenetre = new JFrame();
	GridLayout gl = new GridLayout();
	private int nbco = 1;
	
	
	public void affichage() {
		// La fenetre
	Boolean connecte = true;
	this.fenetre.setTitle("Chat");
	this.fenetre.setSize(800,700);
	this.fenetre.setLocationRelativeTo(null);
	
	this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.fenetre.setVisible(true);
	
	this.fenetre.add(this.pan);
	//fenetre.setLayout(new GridLayout(this.nbco,4));
	/*gl.setColumns(4);
	gl.setRows(nbco);
	gl.setHgap(500);
	this.fenetre.setLayout(gl);*/
	
	
	// Les boutons
	AjouterBouton("Eva", nbco);
	AjouterBouton("Mel", nbco);
	//AjouterBouton("Diep", nbco);

	
	
	//return connecte;
	
	
	}
	
	public void AjouterBouton(String pseudo, int nbco) {
		JButton newbouton = new JButton(pseudo);
		newbouton.setBounds(20, nbco*50, 100, 40);
		this.nbco++;

		this.pan.add(newbouton);

		this.pan.setVisible(true);

	}
	
	

	

}
