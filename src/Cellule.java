/**
 * Classe de Cellule
 * définie le carré de base d'un tétromino
 * @author romain
 *
 */
public class Cellule {
	private int x;
	private int y;
	private char couleur;
	/**
	 * Constructeur des cellules du tableau 
	 * @param x : coordonnée x de la cellule concidéré 
	 * @param y : coordonnée y de la cellule concidéré 
	 * @param couleur : couleur de la cellule concidéré
	 */
	public Cellule(int x, int y, char couleur){
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	/**
	 * Getter de la "couleur" de la cellule
	 * @return couleur 
	 */
	public char getCouleur() {
		return couleur;
	}
	/**
	 * Getter de la position y de la cellule
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter de la position y de la cellule
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	/**
	 * Getter de la position x de la cellule
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Méthode qui permet traduire le code couleur 
	 * @return String : mot clé couleur pour la class awt.Color 
	 */
	public String couleur(){ //Yellow	Purple	green		red			blue	orange		cyan
		String[] couleurs = {"#FFFF00","#800080","#008000","#FF0000","#0000FF","#FF4500","#00FFFF"};
		return couleurs[(int)this.couleur-(int)'0' -1]; //fait un calcul entre la valeur du caractère couleur - la valeur de 0 -1 car on commence le tableau à partir de 0 et les caractères couleurs à partir de 1
		
	}
}
