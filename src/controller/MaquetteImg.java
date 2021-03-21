package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Taquin;
import utilitaire.Direction;
import utilitaire.IEcouteurModel;
import utilitaire.PieceVide;
import utilitaire.Position;
/**
 * 
 * @author SALAMI SODIKI, BA KOMARA, CAMARA MOHAMED, BAMBA ALASSANE
 *
 */
public class MaquetteImg extends JPanel implements IEcouteurModel, MouseListener, KeyListener,MouseMotionListener{
	
	//INSTANCE MODEL
	private Taquin taquin;
	private final int DIMCASE = 50;
	private final int DECALAGE = 30;
	
	/**
	 * CONSTRUCTEUR VU CONTROLLER
	 * @param taquin : le model
	 */
	public MaquetteImg(Taquin taquin) {
		this.taquin = taquin;
		//ABONNEMENT AU MODEL
		this.taquin.ajoutEcouteur(this);
		//ABONNEMENT AU MOUSELISTENER
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(this.taquin.getbImage().getWidth(), this.taquin.getbImage().getHeight()));
		this.addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
	
		Graphics2D g2D = (Graphics2D) g;
		String id;
		int ligne=this.taquin.getLigne();
		int colonne=this.taquin.getColonne();
		int width=this.taquin.getbImage().getWidth()/this.taquin.getColonne();
		int height=this.taquin.getbImage().getHeight()/this.taquin.getLigne();
		for(int i = 0; i<ligne; i++) {
			for(int j = 0; j<colonne; j++) { 
				id = String.valueOf(this.taquin.getGrille()[i][j].getIdPiece());
				Position p1=this.taquin.getGrille()[i][j].getP1Image();
				Position p2=this.taquin.getGrille()[i][j].getP2Image();
				if(this.taquin.getGrille()[i][j] instanceof PieceVide) {
					BufferedImage bImgNoir;
					try {
						if(!taquin.isFinished()) {
								
							 bImgNoir=ImageIO.read(getClass().getResource("/images/imgNoir.jpeg"));
							 g2D.drawImage(bImgNoir,
			                        (j)*width+DECALAGE,(i)*height+DECALAGE,(j+1)*width+DECALAGE,(i+1)*height+DECALAGE,
			                        0,0, width, height,
			                        null);
						}else{
							g2D.drawImage(this.taquin.getbImage(),
			                        (j)*width+DECALAGE,(i)*height+DECALAGE,(j+1)*width+DECALAGE,(i+1)*height+DECALAGE,
			                        p1.getcol(), p1.getligne(), p2.getcol(), p2.getligne(),
			                        
			                        null);
							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					
					g2D.drawImage(this.taquin.getbImage(),
							(j)*width+DECALAGE,(i)*height+DECALAGE,(j+1)*width+DECALAGE,(i+1)*height+DECALAGE,
	                        p1.getcol(), p1.getligne(), p2.getcol(), p2.getligne(),
	                        
	                        null);
					g2D.setColor(Color.WHITE);
					g2D.drawString(id,(j+1)*width+(width/2), (i+1)*height+(height/2));
				}
				
			}
			
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
			int width=this.taquin.getbImage().getWidth()/this.taquin.getColonne();
			int height=this.taquin.getbImage().getHeight()/this.taquin.getLigne();
			int ligne = e.getY()/height;
			int colonne = e.getX()/width;
			this.taquin.deplacement(new Position((ligne), (colonne)));
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
			// TOUCHE GAUCHE
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				this.taquin.deplacementKey(Direction.GAUCHE);
				//messageFinal();
			}
			//TOUCHE DROITE 
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.taquin.deplacementKey(Direction.DROITE);
				//messageFinal();
			}
			//TOUCHE HAUT  
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				this.taquin.deplacementKey(Direction.HAUT);
				//messageFinal();
			}
			//TOUCHE BAS 
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				this.taquin.deplacementKey(Direction.BAS);
				//messageFinal();
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
		
	}
}
