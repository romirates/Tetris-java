import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Classe permetant de représenter la zone de jeu, 
 * les différentes paramètres de celle-ci :
 * état de la partie, vitesse de jeu, score, etc. ainsi que son affichage
 * 
 *
 */
public class Plateau extends JPanel implements ActionListener {

	
	private static final int TailleCellPx = 40;
	//Taille de pixels d'une cellules
	private static final int ZoneLargeur = 10;
	//largeur en cellules de la zone de jeu
	private static final int ZoneHauteur = 18;
	//Hauteur en cellules
	private static final int Temps = 700;
	//Temps initial entre chaque descente d'une piece
	
	
	private ArrayList<Cellule>[] zoneJeu; 
	//Tableau fixe d'arraylist de Cellule
	private Piece pieceActuelle;
	//piece en cours d'execution 
	private Timer timer;
	//instance Swing.Timer permetant l'appel a une fonction toutes les X ms
	private Fabrique fab;
	//objet f
	private int ligneComplete;
	//nombre de ligne complete
	private boolean stop;
	//booleen permetant de connaitre la partie est arrêté ou non
	private Tetris tetris;
	//instance de tetris permet de modifier l'affichage de score a la vole
	
	/**
	 * Constructeur qui crée la zone de jeu, la fabrique et le timer associé
	 * 
	 * rend la fenetre "focusable" pour capter les appuis de touches 
	 * Ajoute un ecouteur d'event par le bias de la classe imbriqué TAdapter
	 * 
	 */
	public Plateau(Tetris instance){
		this.tetris = instance;
		this.zoneJeu = (ArrayList<Cellule>[]) new ArrayList[ZoneHauteur]; 
		for(int i=0; i<this.zoneJeu.length; i++){
			this.zoneJeu[i] = new ArrayList<Cellule>();
		}
		this.fab = new Fabrique();
		this.timer = new Timer(Temps, this);
		setFocusable(true);//permet de "lire" les appuis de touches
		addKeyListener(new TAdapter());
	}
	
	 /**
	  * Methode qui affiche la zone de jeu en tant que d'affichage console
	  */
	public void affichageConsole(){
		char[][] tabAffiche = new char[ZoneHauteur][ZoneLargeur];
		for(int i=0; i<tabAffiche.length;i++){
			for(int j=0; j<tabAffiche[i].length;j++){
				tabAffiche[i][j] = 0;
			}
		}
		for(ArrayList<Cellule> arrayCell : this.zoneJeu){
			for(Cellule cell : arrayCell){
				tabAffiche[cell.getY()][cell.getX()] = (char)((int)cell.getCouleur()-(int)'0');
			}
		}
		for(int i=0; i<tabAffiche.length; i++){
			for(int j=0; j<tabAffiche[i].length; j++){
				System.out.print((int)tabAffiche[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Méthode qui permet d'ajouter une piece au plateau
	 * 
	 * @param piece : ajoute piece au plateau
	 */
	public void ajouter(Piece piece){
		for(Cellule cell : piece.getCellules()){
			this.zoneJeu[cell.getY()].add(cell);
		}
	}
	/**
	 * Méthode qui permet de retirer une piece du plateau
	 * 
	 * @param piece : retire piece du plateau
	 */
	public void retirer(Piece piece){
		for(Cellule cell : piece.getCellules()){
			this.zoneJeu[cell.getY()].remove(cell);
		}
	}
	/**
	 * Méthode qui permet de vérifier si une piece peut être inserée dans le plateau
	 * @param piece représente la piece qui doit être verifier 
	 * @return vrai si la pièce peut être accepter dans le plateau, faux sinon
	 */
	public boolean accepter(Piece piece){
		boolean valide = true;
		for(Cellule cell : piece.getCellules()){
			valide = valide && ((cell.getY() >= 0 && cell.getY() < ZoneHauteur) && (cell.getX()>=0 && cell.getX() < ZoneLargeur));
			if(valide){
				for(Cellule cellZoneJeu : this.zoneJeu[cell.getY()]){
					valide = valide && (cell.getX() != cellZoneJeu.getX());
					
				}
			}
		}
		return valide;
	}
	/**
	 * Methode qui déplace aléatoirement une piece (gauche, droite, bas) 
	 * si c'est possible
	 * 
	 * @param p piece 
	 * @return Piece une piece en fonction de la piece p 
	 */
	public Piece deplacementAlea(Piece p){
		Piece pTemp = p;
		this.retirer(p);
		p = p.choixAlea();
		if(this.accepter(p)){
			this.ajouter(p);
			return p;
		} else{
			p = pTemp;
			this.ajouter(p);
			return p;
		}
	}
	/**
	 * Methode qui démarre la partie :
	 * démarre le timer, ajoute une nouvelle pièce et réactualise l'affichage
	 */
    public void start()
    {
    	this.ligneComplete = 0;
    	this.stop = false;
    	this.tetris.score.setText("le score est de "+this.score()+" ligne");
    	timer.start();
    	this.nouvellePiece();
    }
    /**
     * Methode d'arrêt du jeu 
     * affiche un game over et le score final 
     */
    public void stop(){
    	this.stop = true;
    	timer.stop();
    	this.tetris.score.setText("Game Over ! le score est de "+this.score()+" lignes");
    	
    }
    /**
     * Methode qui vide le plateau sans relancer l'application
     */
    public void viderPlateau(){
    	for(ArrayList<Cellule> cellList : this.zoneJeu){
    		cellList.clear();
    	}
    }
    /**
     * Methode qui reinitialise la partie
     */
    public void reset(){
    	this.viderPlateau();
    	this.start();
    }
    public void vitesse(){
    	this.timer.setDelay((int)(Temps*(1/((Math.sqrt(0.5*this.ligneComplete+1))))));
    }
	/**
	 * Methode qui retourne la valeur de ligneComplete
	 * @return ligneComplete : un entier qui représente le nombre de lignes complete
	 */
	public int score(){
		return this.ligneComplete;
	}
	/**
	 * Methode de creation d'une nouvelle pièce de manière aléatoire
	 * ajoute la piece au plateau si c'est possible arrete le jeu sinon
	 */
	public void nouvellePiece(){
		if(!this.stop){
			this.pieceActuelle = fab.choixPieceAlea();
			if(this.accepter(this.pieceActuelle)){
				this.ajouter(this.pieceActuelle);
				repaint();
				
			}
			else{
				this.stop();
			}
		}
	}
	/**
	 * Méthode qui reactualise la fênetre d'affichage par rapport à la zone de jeu
	 * c'est cette méthode qui est appelé par la methode repaint()
	 */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int j=0; j<TailleCellPx*ZoneHauteur; j+=TailleCellPx){
        		for(int i=0; i<TailleCellPx*ZoneLargeur; i+=TailleCellPx){
        			g.fillRect(i, j, TailleCellPx, TailleCellPx);
        		}
        }
        for (ArrayList<Cellule> arrayCell : this.zoneJeu) {
        	for(Cellule cell : arrayCell){
        		
	        	g.setColor(Color.decode(cell.couleur()));
	        	g.fillRect(TailleCellPx*cell.getX(), TailleCellPx*cell.getY(), TailleCellPx, TailleCellPx);
	        	g.setColor(Color.BLACK);
	        	g.drawRect(TailleCellPx*cell.getX(), TailleCellPx*cell.getY(), TailleCellPx, TailleCellPx);
	        }
        }
        }

    /**
     * Methode qui fait descendre la piece courante toutes les "this.vitesse()" ms  
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Piece pieceT = this.pieceActuelle;
		this.retirer(this.pieceActuelle);
		this.pieceActuelle = this.pieceActuelle.versLeBas();
		if(this.accepter(this.pieceActuelle)){
			this.ajouter(this.pieceActuelle);
			repaint();
		}else{
			this.ajouter(pieceT);
			this.supprimeLignes();
			this.compactageLignes();
			nouvellePiece();
		}
		//this.affichageConsole(); suivi de l'affichage console
		
	}


	/**
	 * Methode qui suprimme toutes les lignes pleine 
	 * (contenant "ZoneLargeur" de cellules) 
	 */
	
	public void supprimeLignes(){
		for(ArrayList<Cellule> listCell : this.zoneJeu){
			if(listCell.size() == ZoneLargeur){
				listCell.clear();
				this.ligneComplete++;
				this.tetris.score.setText("le score est de "+this.score()+" lignes");
			}
		}
		this.vitesse();
	}
	/**
	 * Methode qui permet de ramener toutes les lignes du plateau de jeu tout en bas de celui-ci
	 * tout en vérifiant à ne pas écrasser de lignes
	 * 
	 */
	public void compactageLignes(){
		int ligneCourante = ZoneHauteur - 1; //On commence le compactage par la fin donc on prends la dernière ligne du tableau
		int ligneVide = ZoneHauteur - 1; //On initialise la dernière ligne vide connue, on suppose que c'est la dernière ligne du tableau
		while(ligneCourante >=0){
			if(this.zoneJeu[ligneCourante].isEmpty()){ //si la ligne courante est vide 
				ligneCourante--;//on passe à la prochaine ligne pour la prochaine itération
			}else if((ligneCourante != ZoneHauteur - 1) && this.zoneJeu[ligneCourante+1].isEmpty()){//sinon si on est pas à la dernière ligne du tableau et si la ligne en dessous de la ligne courante est vide
				this.zoneJeu[ligneVide].addAll(this.zoneJeu[ligneCourante]);//on copie toutes les cellules de la ligne courante dans la dernière ligne vide connu
				for(int i=0; i<this.zoneJeu[ligneVide].size(); i++){ //on modifie l'attribut y des cellules en fonction de la ligneVide où elle sont copié
					this.zoneJeu[ligneVide].get(i).setY(ligneVide);
				}
				this.zoneJeu[ligneCourante].clear(); //on vide la ligne courante
				ligneVide--;//la dernière ligne vide n'est thisus vide donc on passe à celle du dessus
				ligneCourante--;//on passe à la prochaine ligne pour la prochaine itération
			}else{//sinon on est dans le cas soit qu'on regarde la première (en partant du bas) ligne du tableau et qu'elle n'est pas vide soit dans le cas d'un ensemble continue de lignes non vide	
				ligneVide--;
				ligneCourante--;
			}
			
		}
	}

	/**
	 * Classe TAdapter:
	 * classe imbriqué TAdapter héritant de la classe abstraite KeyAdapter 
	 * permet de gerer grâce à un switch les différentes actions possible de l'utilisateur avec le clavier
	 * les différents mouvement qui s'éxecute si possible sont :
	 * -flêche droite : déplacement de la pièce à droite 
	 * -flêche gauche : déplacement de la pièce à gauche
	 * -flêche haut : rotation seulement si la pièce n'est pas un carré
	 * -flêche bas : descente d'une ligne vers le bas
	 * 
	 * après chaque action, la fonction repaint est appellé pour la mise à jour de l'affichage
	 * @author Romain Ferrand
	 *
	 */
	class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
       	 Piece pieceT = pieceActuelle;
       	 int keycode = e.getKeyCode();
            switch (keycode) {
            
            case KeyEvent.VK_LEFT:
            	retirer(pieceActuelle);
            	pieceActuelle = pieceActuelle.versLaGauche();
            	if(accepter(pieceActuelle)){
            		ajouter(pieceActuelle);
                }
                else {
                	pieceActuelle = pieceT;
                	ajouter(pieceActuelle);
                }
                break;
                
            case KeyEvent.VK_RIGHT:
            	retirer(pieceActuelle);
            	pieceActuelle = pieceActuelle.versLaDroite();
                
                if(accepter(pieceActuelle)){
                	ajouter(pieceActuelle);
                }
                else {
                	pieceActuelle = pieceT;
                	ajouter(pieceActuelle);
                }
                break;
                
            case KeyEvent.VK_UP:
            	if(pieceActuelle.getCellules().get(0).getCouleur() != '1'){
            		retirer(pieceActuelle);
	                pieceActuelle = pieceActuelle.rotation();
	                if(accepter(pieceActuelle)){
	                	ajouter(pieceActuelle);
	                }
	                else {
	                	pieceActuelle = pieceT;
	                	ajouter(pieceActuelle);
	                	}
	                }
            	break;
                
            case KeyEvent.VK_DOWN:
            	retirer(pieceActuelle);
            	pieceActuelle = pieceActuelle.versLeBas();
            	if(accepter(pieceActuelle)){
            		ajouter(pieceActuelle);
            		}
            	else{
            		pieceActuelle = pieceT;
            		ajouter(pieceActuelle);
            		}
            	break;
           	
            case KeyEvent.VK_R:
            	reset();
            	break;
            	}
            repaint();
            }
        }
}
