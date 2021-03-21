package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MaquetteImg;
import controller.MaquetteSansImg;
import model.Taquin;
/**
 * 
 * @author SALAMI SODIKI, BA KOMARA, CAMARA MOHAMED, BAMBA ALASSANE
 *
 */
public class Fenetre extends JFrame{
	
	//REFERENCE VERS LE MODEL
	Taquin taquin;
	
	/**
	 * CONSTRUCTEUR SANS IMAGE
	 * @param taquin : le model
	 */
	public Fenetre(Taquin taquin) {
		
		this.setTitle("Taquin");
	   
	    this.taquin = taquin;

	    Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		MaquetteSansImg maquette = new MaquetteSansImg(taquin);
		this.setPreferredSize(new Dimension(800,800));
		cp.add(maquette, BorderLayout.CENTER);
		
		
		addKeyListener(maquette);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * CONSTRUCTEUR AVEC IMAGE
	 * @param taquin est le model
	 * @param maquette est une vue controller
	 */
	public Fenetre(Taquin taquin,MaquetteImg maquette) {
		
			this.setTitle("Taquin");
			
			this.setPreferredSize(new Dimension(1350,830));
			this.taquin = taquin;
		
		    Container cp = this.getContentPane();
			cp.setLayout(new BorderLayout());
			
			
			maquette.setBackground(Color.WHITE);
			cp.add(maquette, BorderLayout.CENTER);
			addKeyListener(maquette);
			pack();
			this.setLocationRelativeTo(null);
			setVisible(true);
	}
	
}
