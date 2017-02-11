
public class Cellule {
	private int x;
	private int y;
	private char couleur;
	/**
	 * Constructeur des cellules du tableau 
	 * @param x coordonnée x de la cellule concidéré 
	 * @param y coordonnée y de la cellule concidéré 
	 * @param couleur couleur de la cellule concidéré
	 */
	public Cellule(int x, int y, char couleur){
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	/**
	 * 
	 * @return couleur 
	 */
	public char getCouleur() {
		return couleur;
	}
	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}
	public String couleur(){ //Yellow	Purple	green		red			blue	orange		cyan
		String[] couleurs = {"#FFFF00","#800080","#008000","#FF0000","#0000FF","#FF4500","#00FFFF"};
		return couleurs[(int)this.couleur-(int)'0' -1]; //fait un calcul entre la valeur du caractère couleur - la valeur de 0 -1 car on commence le tableau à partir de 0 et les caractères couleurs à partir de 1
		
	}
}
