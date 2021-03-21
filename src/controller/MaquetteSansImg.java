package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Taquin;
import utilitaire.Direction;
import utilitaire.IEcouteurModel;
import utilitaire.PieceVide;
import utilitaire.Position;

public class MaquetteSansImg extends JPanel implements IEcouteurModel, MouseListener, KeyListener,MouseMotionListener{
	
	//Instance de modele
	private Taquin taquin;
	public static final  int DIMCASE = 80;
	public static final int DECALAGE = 1;
	
	/**
	 * Constructeur de la class vueController
	 * @param taquin reference du model
	 */
	public MaquetteSansImg(Taquin taquin) {
		
		this.taquin = taquin;
		//Abonnement au model
		this.taquin.ajoutEcouteur(this);
		//Abonnement au Mouslistener
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	

	public void paintComponent(Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.GREEN);
		int arrondi = 10;
		String id;
		int ligne=this.taquin.getLigne();
		int colonne=this.taquin.getColonne();
		for(int i = 0; i<ligne; i++) {
			for(int j = 0; j<colonne; j++) { 
				g2D.setColor(Color.WHITE);
				id = String.valueOf(this.taquin.getGrille()[i][j].getIdPiece());
				if(this.taquin.getGrille()[i][j] instanceof PieceVide) {
					
					g2D.setPaint(Color.BLACK);
					g2D.fillRoundRect((j+DECALAGE)*DIMCASE, (i+DECALAGE)*DIMCASE, DIMCASE, DIMCASE, arrondi, arrondi);
					g2D.setColor(Color.WHITE);
					
					
				}else {
					g2D.fillRoundRect((j+DECALAGE)*DIMCASE, (i+DECALAGE)*DIMCASE, DIMCASE, DIMCASE, arrondi, arrondi);
					g2D.setColor(Color.BLUE);
					g2D.drawString(id, (j+DECALAGE)*DIMCASE+(DIMCASE/2), (i+DECALAGE)*DIMCASE+(DIMCASE/2));
				}
				
				
			}
			
		}
		//illuminer la piece deplacable
		if(this.taquin.getPieceIllumine()!=null) {
			ligne=this.taquin.getPieceIllumine().getligne();
			colonne=this.taquin.getPieceIllumine().getcol();
			id = String.valueOf(this.taquin.getGrille()[ligne][colonne].getIdPiece());
			g2D.setPaint(Color.GREEN);
			g2D.fillRoundRect((colonne+DECALAGE)*DIMCASE,(ligne+DECALAGE)*DIMCASE , DIMCASE, DIMCASE,arrondi,arrondi);
			g2D.setColor(Color.BLUE);
			g2D.drawString(id, (colonne+DECALAGE)*DIMCASE+(DIMCASE/2), (ligne+DECALAGE)*DIMCASE+(DIMCASE/2));
		}
		
		g2D.dispose();
	
	}

	@Override
	public void modeleMisAJour(Object source) {
		this.repaint();
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!taquin.isFinished()) {
			int ligne = e.getY()/DIMCASE;
			int colonne = e.getX()/DIMCASE;
			System.out.println("ligne"+ligne);
			System.out.println("colonne"+colonne);
			this.taquin.deplacement(new Position((ligne-1), (colonne-1)));
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Les methodes de keyListener

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!taquin.isFinished()) {
			// Pour deplacer la piece avec la touche gauche du clavier 
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				this.taquin.deplacementKey(Direction.GAUCHE);
			}
			// Pour deplacer la piece avec la touche droite du clavier 
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.taquin.deplacementKey(Direction.DROITE);
			}
			// Pour deplacer la piece avec la touche du haut du clavier 
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				this.taquin.deplacementKey(Direction.HAUT);
			}
			// Pour deplacer la piece avec la touche du bas du clavier 
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				this.taquin.deplacementKey(Direction.BAS);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!taquin.isFinished()) {
			int ligne = e.getY()/DIMCASE;
			int col = e.getX()/DIMCASE;
			this.taquin.mettreEnExergue(new Position(ligne-1, col-1));
		}
		
	}
}
