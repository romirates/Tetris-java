import java.util.Random;
/**
 * Classe Fabrique
 * implémente la création des différents triominos, 
 * et leurs donne une position aléatoire sur l'axe des abscisse de la première ligne du plateau
 * 
 * @author romain
 *
 */

public class Fabrique {
	private static int yInitial = 0;
	private Piece[] pieces;
	public Fabrique(){
		this.pieces = new Piece[7];
		this.pieces[0] = this.creeCarre();
		this.pieces[1] = this.creeBarre();
		this.pieces[2] = this.creeL();
		this.pieces[3] = this.creeLinverse();
		this.pieces[4] = this.creeS();
		this.pieces[5] = this.creeT();
		this.pieces[6] = this.creeZ();
	}
	
	private static int positionAleaX(){
		Random rd = new Random();
		int posX = rd.nextInt(7)+1;
		return posX;
	}
	
	public Piece creeCarre(){
		Piece p = new Piece();
		char couleur = '1';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial+1, couleur));
		return p;
	}
	public Piece creeT(){
		Piece p = new Piece();
		char couleur = '2';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial-1, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial, couleur));
		return p;
	}
	
	public Piece creeZ(){
		Piece p = new Piece();
		char couleur = '3';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial+1, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial+2, yInitial+1, couleur));
		return p;
	}
	
	public Piece creeS(){
		Piece p = new Piece();
		char couleur = '4';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial-1, yInitial+1, couleur));
		return p;
	}
	
	public Piece creeL(){
		Piece p = new Piece();
		char couleur = '5';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial+1, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial+1, yInitial+2, couleur));
		return p;
	}
	
	public Piece creeLinverse(){
		Piece p = new Piece();
		char couleur = '6';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial-1, yInitial+2, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+2, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		return p;		
	}
	
	public Piece creeBarre(){	
		Piece p = new Piece();
		char couleur = '7';
		int xInitial = positionAleaX();
		p.ajouterCell(new Cellule(xInitial, yInitial+2, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+1, couleur));
		p.ajouterCell(new Cellule(xInitial, yInitial+3, couleur));
		return p;
	}
	public Piece choixPieceAlea(){
		Random rd = new Random();
		int choix = rd.nextInt(7);
		return this.pieces[choix];
	}
}