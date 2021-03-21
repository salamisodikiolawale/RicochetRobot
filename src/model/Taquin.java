package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

//import t2s.son.LecteurTexte;
import utilitaire.Direction;
import utilitaire.ModelEcoutable;
import utilitaire.Piece;
import utilitaire.PieceVide;
import utilitaire.Position;
/**
 * 
 * @author SALAMI SODIKI, BA KOMARA, CAMARA MOHAMED, BAMBA ALASSANE
 *
 */
public class Taquin extends ModelEcoutable {

	private int ligne;
	private int colonne;
	private Piece [][] grille;
	private Piece pieceVide;
	private Position posPieceIllumine;
	private BufferedImage bImage;
	private ArrayList<Piece>listeOrdonneeDesPieces;
	

	/**
	 * CONSTRUCTEUR VERSION SANS IMAGE
	 * @param ligne du board
	 * @param colonne du board
	 */
	public Taquin(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.listeOrdonneeDesPieces = new ArrayList<Piece>();
		creerGrille();
		melange();
	}
	
	/**
	 * CONSTRUCTEUR VERSION AVEC IMAGE
	 * @param ligne : type entier
	 * @param colonne : type entier
	 * @param bi : type BufferedImage
	 */
	public Taquin(int ligne, int colonne,BufferedImage bi) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.listeOrdonneeDesPieces = new ArrayList<Piece>();
		creerGrille();
		decouperImg();
		melange();
	
	}
	
	//CREER GRILLLE
	public void creerGrille() {
		if((this.ligne <= 0) || (this.colonne<=0) ) throw new NegativeArraySizeException("la dimension de la grille ne peut etre inférieur ou égale a zero");//throw new LigneColonneGrilleException("la dimension de la grille ne peut etre inférieur ou égale a zero");
		this.grille = new Piece[this.ligne][this.colonne];
		int indice = 0;
		for(int i = 0; i<this.ligne; i++) {
			for(int j = 0; j<this.colonne; j++) { 
				Piece p = new Piece(new Position(i, j), i*this.colonne+j+1);
					this.grille[i][j]= p;
					this.listeOrdonneeDesPieces.add(p);
					indice++;
			}
			
		}
		this.pieceVide=new PieceVide(new Position(this.ligne-1,this.colonne-1), 0);
		this.grille[ligne-1][colonne-1]=this.pieceVide;
		this.listeOrdonneeDesPieces.remove(indice-1);
		this.listeOrdonneeDesPieces.add(indice-1, this.pieceVide);
	}
	
	
	//DECOUPAGE ET AFFECTATION DES PARTIES DE L IMAGE
	public void decouperImg(){
		try {
			this.bImage=ImageIO.read(getClass().getResource("/images/hulk.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width=this.bImage.getWidth()/this.colonne;
		int height=this.bImage.getHeight()/this.ligne;
		for(int i = 0; i<this.ligne; i++) {
			for(int j = 0; j<this.colonne; j++) { 
					this.grille[i][j].setP1Image(new Position(i*height, j*width));
					this.grille[i][j].setP2Image(new Position((i+1)*height, (j+1)*width));
			}

		}
	}

	//MELANGE DES PIECES
	public void melange() {
		int PrePiece = 0;
		Random random = new Random();
		int r;
		int ligne,colonne;
		boolean moi =true;
			
		//RECUPERATION DE L INDICE  DE LA PREMIERE PIECE DU JEU
		int indicePremierePiece = this.getGrille()[PrePiece][PrePiece].getIdPiece();
		do {
			while(this.getGrille()[PrePiece][PrePiece].getIdPiece() == indicePremierePiece) {
				
				r = random.nextInt(this.listdeplacementValide().size());
				ligne = this.listdeplacementValide().get(r).getligne();
				colonne = this.listdeplacementValide().get(r).getcol();
					
				this.deplacement(new Position(ligne, colonne));
			}
			while(this.pieceVide.getPosition().getligne()!=this.ligne-1) {
				this.deplacement(new Position(this.pieceVide.getPosition().getligne()+1, this.pieceVide.getPosition().getcol()));
			}
			while(this.pieceVide.getPosition().getcol()!=this.colonne-1) {
				this.deplacement(new Position(this.pieceVide.getPosition().getligne(), this.pieceVide.getPosition().getcol()+1));
			}
		}
		while(isFinished());
		
	}
		
		
	
	/**
	 * VERIFIE SI LE JEU EST TERMINE
	 * @return  boolean
	 */
	public Boolean isFinished() {
		
		int indice = 0;
		for(int i = 0; i<this.ligne; i++) {
			for(int j = 0; j<this.colonne; j++) {
				if(this.listeOrdonneeDesPieces.get(indice++).getIdPiece() != this.grille[i][j].getIdPiece()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * DEPLACEMENT DES PIECE: CETTE METHODE UTILISE LA METHODE deplacementValide POUR VERIFIER LA VALIDITE DU DEPLACEMENT
	 * LE PARAMETE melange  PERMET DE SAVOIR SI C4EST LA METHODE MELANGE QUI UTILISE LE DEPLACEMENT
	 * @param posDepartPiece : position de depart.
	 */
	
	public void deplacement(Position posDepartPiece) {
		//ON VERIFIE DABORD LA POSSIBILITE DU DEPLACEMENT
		if(isdeplacementValide(posDepartPiece)) {
			int lignePieceVide = this.pieceVide.getPosition().getligne();
			int colonnePieceVide = this.pieceVide.getPosition().getcol();
			this.grille[lignePieceVide][colonnePieceVide]=this.grille[ posDepartPiece.getligne() ][ posDepartPiece.getcol() ];
			this.grille[posDepartPiece.getligne()][posDepartPiece.getcol()] = this.pieceVide;
			this.pieceVide.setPosition(posDepartPiece);
			this.posPieceIllumine=null;
			fireChangement();
		    getGame();
		}else {
			System.out.println("DEPLACEMENT HORS DU BOARD IMPOSSIBLE");
		}
		
	
	}
	
	/**
	 * RETOURNE LA LISTE DE DEPLACEMENT POSSIBLE
	 * @return listPositions : liste de position possible de deplacement 
	 */
	private ArrayList<Position> listdeplacementValide() {
		ArrayList<Position> listPositions=new ArrayList<Position>();
		int lignePieceVide = this.pieceVide.getPosition().getligne();
		int colonnePieceVide = this.pieceVide.getPosition().getcol();
		if(lignePieceVide-1>=0) {
			listPositions.add(new Position(lignePieceVide-1, colonnePieceVide));
		}
		if(lignePieceVide+1<=this.ligne-1) {
			listPositions.add(new Position(lignePieceVide+1, colonnePieceVide));
		}
		if(colonnePieceVide-1>=0) {
			listPositions.add(new Position(lignePieceVide, colonnePieceVide-1));
		}
		if(colonnePieceVide+1<=this.colonne-1) {
			listPositions.add(new Position(lignePieceVide, colonnePieceVide+1));
		}
		
		
		return listPositions;
	}
	/**
	 * TESTE SI UN DEPLACEMENT EST POSSIBLE
	 * @param posDepartPiece position de depart.
	 * @return boolean
	 */
	private boolean isdeplacementValide(Position posDepartPiece) {
		ArrayList<Position> listPositions=listdeplacementValide();
		for (Position position : listPositions) {
			if(position.equals(posDepartPiece)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * PERMET DE MONTRER AU JOUEUR LES PIECES SUCEPTIBLES D ETRE DEPLACER
	 * @param posPiece position d ' une piece
	 */
	public void mettreEnExergue(Position posPiece) {
		if(isdeplacementValide(posPiece)) {
			this.posPieceIllumine=posPiece;
		}
		else {
			this.posPieceIllumine=null;
		}
		fireChangement();
	}
	
	/**
	 * DEPLACEMENT DES PIECE AVEC LES TOUCHES DIRECTIONNELLES DU CLAVIER
	 * @param dr Direction HAUT, BAS, GAUCHE, DROITE
	 */
	public void deplacementKey(Direction dr) {
		Position posCourantePieceVide=this.pieceVide.getPosition();
		boolean pasMelange = false;
			if(dr == Direction.HAUT) {
				deplacement(new Position(posCourantePieceVide.getligne()-1, posCourantePieceVide.getcol()));
			}
			else if(dr == Direction.BAS) {
				deplacement(new Position(posCourantePieceVide.getligne()+1, posCourantePieceVide.getcol()));
			}
			else if(dr == Direction.GAUCHE) {
				deplacement(new Position(posCourantePieceVide.getligne(), posCourantePieceVide.getcol()-1));
			}	
			else if(dr == Direction.DROITE) {
				deplacement(new Position(posCourantePieceVide.getligne(), posCourantePieceVide.getcol()+1));
			}		
	}
	
	/**
	 * DEPLACEMENT DES PIECES EN MODE CONSOLE
	 */
	public void deplacementConsole() {
		while(!isFinished()) {
			 Scanner s = new Scanner(System.in);
			 char dr = s.next().charAt(0); 
			 if(dr!='h' && dr!='H' && dr!='b' && dr!='B' && dr!='d' && dr!='D' && dr!='g' && dr!='G') {
				 System.out.println("Veuillez utilisez les bon caractères pour le deplacament");
			 }else{
				 Position posCourantePieceVide=this.pieceVide.getPosition();
					boolean pasMelange = false;
					if(dr == 'h' || dr == 'H') {
						deplacement(new Position(posCourantePieceVide.getligne()-1, posCourantePieceVide.getcol()));
					}
					else if(dr == 'b' || dr == 'B') {
						deplacement(new Position(posCourantePieceVide.getligne()+1, posCourantePieceVide.getcol()));
					}
					else if(dr == 'g' || dr == 'G') {
						deplacement(new Position(posCourantePieceVide.getligne(), posCourantePieceVide.getcol()-1));
					}	
					else if(dr == 'd' || dr == 'D') {
						deplacement(new Position(posCourantePieceVide.getligne(), posCourantePieceVide.getcol()+1));
					}		
			 }
			
		}
	}
	
	
	/**
	 * METHODE EXPLICITANT LE PROCESSUS DE DEPLACEMENT DES PIECES EN MODE CONSOLE
	 */
	public void directionConsole() {
		System.out.println(" ");
		System.out.println("Bienvenue, Veuillez suivre les instruction de deplacement puis tapez sur la touche entrer du clavier. Bonne chance !!!");
		System.out.println(" ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("-----Choisir une direction (Lettre majuscule ou minuscule)-----");
		System.out.println("-----                 (H ou h)                            -----");
		System.out.println("-----                    ||                               -----");
		System.out.println("-----      (G ou g) <---    ----> (D ou d)                -----");
		System.out.println("-----                    ||                               -----");
		System.out.println("-----                 (B ou b)                            -----");
		System.out.println("---------------------------------------------------------------");
	}
	
	
	public void viderListeOrdonneeDesPieces() {
		for(int i=0;i<this.listeOrdonneeDesPieces.size();i++) {
			this.listeOrdonneeDesPieces.clear();
		}
	}
	
	
	//LES GETTERS
	public Piece[][] getGrille(){
		return this.grille;
	}
		
	public void getGame(){
		for(int i = 0; i<this.ligne; i++) {
			for(int j = 0; j<this.colonne; j++) { 
				if(this.grille[i][j] instanceof PieceVide) {
					System.out.print("    ");
				}
				else
				System.out.print(this.grille[i][j].getIdPiece()+"   ");
			}
			System.out.println("   ");
		}
		
		System.out.println("   ");
	}
	public void getGame1(){
		for(int i = 0; i<this.ligne; i++) {
			for(int j = 0; j<this.colonne; j++) { 
				
				System.out.print(this.grille[i][j].getP1Image()+" "+this.grille[i][j].getP2Image()+"   ");
			}
			System.out.println("   ");
		}
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public Piece getPieceVide() {
		return pieceVide;
	}

	public void setPieceVide(Piece pieceVide) {
		this.pieceVide = pieceVide;
	}

	public Position getPieceIllumine() {
		return posPieceIllumine;
	}

	public void setPieceIllumine(Position posPieceIllumine) {
		this.posPieceIllumine = posPieceIllumine;
	}

	public BufferedImage getbImage() {
		return bImage;
	}

	public void setbImage(BufferedImage bImage) {
		this.bImage = bImage;
	}
	
	
	public Position getPosPieceIllumine() {
		return posPieceIllumine;
	}
	
	public ArrayList<Piece> getListeOrdonneeDesPieces() {
		return listeOrdonneeDesPieces;
	}
	
	
}
