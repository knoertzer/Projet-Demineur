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

	}

	public int getIndice() {
		return this._indice;
	}

	public void setIndice(int indice) {
		this._indice = indice;
	}

	@Override
	public ImageIcon getImage() {
		return null;
	}

}
