package demineur.modele;

import javax.swing.ImageIcon;

public class CaseIndice extends Case {

	
	public int _indice;
	
	public CaseIndice(int posX, int posY , int indice) {
		super(posX, posY);
		this._indice = indice;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getIndice() {
		return this._indice;
	}

	public void setIndice(int indice) {
		this._indice = indice;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
