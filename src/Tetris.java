
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class Tetris extends JFrame {
	public static final int Largeur = 400;
	public static final int Hauteur = 760;
	public JLabel score;
	public Tetris(){
		score = new JLabel();
		Plateau pl = new Plateau(this);
		add(score, BorderLayout.SOUTH);
		add(pl);
		pl.start();
		setSize(Tetris.Largeur, Tetris.Hauteur);
		setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		Tetris jeu = new Tetris();
		jeu.setLocationRelativeTo(null);
		jeu.setVisible(true);
		
	}
}