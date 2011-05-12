package demineur.modele;

import javax.swing.ImageIcon;

public abstract class Case {

	public int posX;
	public int posY;
	public ImageIcon flag = new ImageIcon ("C:\\Users\\Xantho\\Documents\\INFORMATIQUE\\JAVA\\Demineur\\flag.png");
	public boolean isFlag;
	
	public Case(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.isFlag = false;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
		
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public abstract ImageIcon getImage();

	public abstract int getIndice();
}
