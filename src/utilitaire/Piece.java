package utilitaire;

/**
 * 
 * @author SALAMI SODIKI, BA KOMARA, CAMARA MOHAMED, BAMBA ALASSANE
 *
 */

public class Piece {

	private int idPiece;
	private Position posiPiece;
	private Position p1Image;
	private Position p2Image;
	
	/**
	 * CONSTRUCTEUR DE LA CLASS
	 * @param posiPiece : position de la piece
	 * @param id : Identifiant de la Piece 
	 */
	public Piece(Position posiPiece, int id) {
		this.posiPiece = posiPiece;
		this.idPiece = id;
	}
	
	public int getIdPiece() {
		return this.idPiece;
		
	}
	
	public void setIdPiece(int id) {
		this.idPiece = id;
		
	}
	
	public Position getPosition() {
		return this.posiPiece;
	}
	
	public void setPosition(Position posi) {
		this.posiPiece = posi;
	}


	public Position getP1Image() {
		return p1Image;
	}


	public void setP1Image(Position p1Image) {
		this.p1Image = p1Image;
	}


	public Position getP2Image() {
		return p2Image;
	}


	public void setP2Image(Position p2Image) {
		this.p2Image = p2Image;
	}
	
	
	
}
