package demineur.modele;

import javax.swing.ImageIcon;

public class CaseBombe extends Case {

	public final static ImageIcon bombe = new ImageIcon ("./bombe2.png"); 
	
	public CaseBombe(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

	public static ImageIcon getBombe() {
		return bombe;
	}

	@Override
	public ImageIcon getImage() {
		return bombe;
	}

	@Override
	public int getIndice() {
		return -1;
	}

}
