package demineur.modele;

import javax.swing.ImageIcon;

public abstract class Case {

	private int posX;
	private int posY;
	public final ImageIcon flag = new ImageIcon ("./flag.png");
	private boolean isFlag;
	private boolean decouverte;
	
	public Case(int posX, int posY) 
	{
		super();
		this.posX = posX;
		this.posY = posY;
		this.isFlag = false;
		this.decouverte = false;
		
	}
	
	public boolean isDecouverte() {
		return decouverte;
	}

	public void setDecouverte(boolean decouverte) {
		this.decouverte = decouverte;
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
