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
	/**
	 * Constructeur de Fabrique
	 * crée un tableau de pièce contenant toutes les pièces possibles
	 */
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
	/**
	 * Méthode qui randomise la position de la pièce sur l'axe des abscisses
	 * @return un entier 
	 */
	private static int positionAleaX(){
		Random rd = new Random();
		int posX = rd.nextInt(7)+1;
		return posX;
	}
	/**
	 * Méthode qui crée un carré
	 * 
	 * @return pièce carré
	 */
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
	/**
	 * Méthode qui crée un T
	 * 
	 * @return pièce T
	 */
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
	/**
	 * Méthode qui crée un Z
	 * 
	 * @return pièce Z
	 */
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
	/**
	 * Méthode qui crée un S
	 * 
	 * @return pièce S
	 */
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
	/**
	 * Méthode qui crée un L
	 * 
	 * @return pièce L
	 */
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
	/**
	 * Méthode qui crée un L inversé
	 * 
	 * @return pièce L inversé
	 */
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
	/**
	 * Méthode qui crée une barre
	 * 
	 * @return pièce barre
	 */
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
	/**
	 * Méthode qui retourne une pièce choisie "aléatoirement" 
	 * 
	 * @return pièce
	 */
	public Piece choixPieceAlea(){
		Random rd = new Random();
		int choix = rd.nextInt(7);
		return this.pieces[choix];
	}
}