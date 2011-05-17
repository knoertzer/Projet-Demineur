package demineur.modele;

import javax.swing.ImageIcon;

public class CaseBombe extends Case {

	public final static ImageIcon bombe = new ImageIcon ("C:\\Users\\Xantho\\Documents\\INFORMATIQUE\\JAVA\\Demineur\\bombe2.png"); 
	
	public CaseBombe(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static ImageIcon getBombe() {
		return bombe;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return bombe;
	}

	@Override
	public int getIndice() {
		// TODO Auto-generated method stub
		return -1;
	}

}
