import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author romain
 *
 */
public class Piece {
	/**
	 * NbPiece Constante static qui défini le nombre normale de cellule dans une piece
	 * Cellules tableau de cellules 
	 */
	private final static int NbCellules = 4;
	private ArrayList<Cellule> cellules;
	public Piece(){
		this.cellules = new ArrayList<Cellule>(NbCellules);
	}
	public void ajouterCell(Cellule cell){
		this.cellules.add(cell);
	}
	/**
	 * Methode qui effectue un déplacement d'une case vers le bas de la piece
	 * @return la piece déplacé
	 */
	public Piece versLeBas(){
		Piece p = new Piece();
		for(Cellule cell : this.cellules){
			Cellule cellT = new Cellule(cell.getX(),cell.getY()+1,cell.getCouleur());
			p.ajouterCell(cellT);
		}
		return p;
	}
	public ArrayList<Cellule> getCellules(){
		return this.cellules;
	}
	/**
	 * Methode qui effectue un déplacement d'une case vers la droite de la piece
	 * @return la piece déplacé 
	 */
	public Piece versLaDroite(){
		Piece p = new Piece();
		for(Cellule cell : this.cellules){
			Cellule cellT = new Cellule(cell.getX()+1,cell.getY(),cell.getCouleur());
			p.ajouterCell(cellT);
		}
		return p;
	}
	/**
	 * Methode qui effectue un déplacement d'une case vers la gauche de la piece
	 * @return retourne la piece déplacé
	 */
	public Piece versLaGauche(){
		Piece p = new Piece();
		for(Cellule cell : this.cellules){
			Cellule cellT = new Cellule(cell.getX()-1,cell.getY(),cell.getCouleur());
			p.ajouterCell(cellT);
		}
		
		return p;
	}
	/**
	 * Methode qui choisi un déplacement aléatoirement entre la droite, gauche et bas
	 * @return la piece après le choix aléatoire
	 */
	public Piece choixAlea(){
		Random rd = new Random();
		int choix = rd.nextInt(3);
		if(choix == 0){
			return this.versLeBas();
		}else if(choix ==1){
			return this.versLaDroite();
		}else{
			return this.versLaGauche();
		}
	}
	
	/**
	 * Methode qui effectue grave à la matrice de rotation [0,-1,1,0],
	 * une rotation d'un angle pi/2 à partir de la cellule indicé à 0 de pièce dans l'arrayList 
	 * qui représente le centre de rotation
	 * @return la piece après la rotation
	 */
	public Piece rotation(){
			int cx, cy;
			int xR, yR; 
			char couleur;
			Piece pieceR; 
			couleur = this.cellules.get(0).getCouleur();
			pieceR = new Piece();
			cx = this.cellules.get(0).getX();
			cy = this.cellules.get(0).getY();
			pieceR.ajouterCell(this.cellules.get(0));
			for(int i=1;i<NbCellules; i++){
				xR = -this.cellules.get(i).getY()+cy+cx;
				yR = this.cellules.get(i).getX()-cx+cy;
				pieceR.ajouterCell(new Cellule(xR, yR, couleur));
			}
			return pieceR;
	}
}
