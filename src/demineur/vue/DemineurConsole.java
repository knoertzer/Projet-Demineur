package demineur.vue;

import demineur.modele.ModeleDemineur;
import demineur.options.Console;

public class DemineurConsole {

	private int nbColonnes = 10;
	private int nbLignes = 10;
	private int nbBombes = 10;
	private int nbDrap = 10;
	private boolean termine=false;
	private ModeleDemineur modeleDem;

	public DemineurConsole() {
		System.out.println("Demineur version console");
		System.out.println("Veuillez saisir une nombre de colonnes : 10");
		//setNbColonnes(Console.lireInt());
		System.out.println("Veuillez saisir une nombre de lignes : 10");
		//setNbLignes(Console.lireInt());
		System.out.println("Veuillez saisir une nombre de bombes : 10");
		//setNbBombes(Console.lireInt());
		setModeleDem(new ModeleDemineur(getNbColonnes(), getNbLignes(),
				getNbBombes()));
		System.out.println("La partie commence");
		while(!isTermine())
		{
			int i,j;
			System.out.println("Veuillez saisir une ligne :");
			i=Console.lireInt();
			System.out.println("Veuillez saisir une colonne :");
			j=Console.lireInt();
			
			getModeleDem().ZeroDiscover(j, i, getNbLignes(), getNbColonnes());
			if (getModeleDem().EstBombe(j, i))
			{
				setTermine(true);
				System.out.println("ZETES 1 LOOOOOOOOOOOSER !!");				
			}
			majGrille();
			getModeleDem().verifGagne();
		}
		
	}
	

	private void majGrille() {
		for (int i = 0; i < getNbColonnes(); i++) {
			for (int j = 0; j < getNbLignes(); j++) {
				if(isTermine() && modeleDem.EstBombe(j, i))
				{
					System.out.print("[X]");
				}
				else if (modeleDem.get_tabCases(j, i).isDecouverte())
				{
					System.out.print("["+modeleDem.get_tabCases(j, i).getIndice()+"]");
				}
				else if (modeleDem.get_tabCases(j, i).isFlag())
				{
					System.out.print("[F]");
				}
				else System.out.print("[ ]");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {


		DemineurConsole fenetre = new DemineurConsole();
		
	}

	private void afficherGrille() {
		for (int i = 0; i < getNbLignes(); i++) {
			for (int j = 0; j < getNbColonnes(); j++) {
				if (getModeleDem().EstBombe(i, j))
					System.out.print("[X]");
				else
					System.out.print("["
							+ getModeleDem().get_tabCases(i, j).getIndice()
							+ "]");
			}
			System.out.println("");
		}

	}

	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbBombes(int nbBombes) {
		this.nbBombes = nbBombes;
	}

	public int getNbBombes() {
		return nbBombes;
	}

	public void setNbDrap(int nbDrap) {
		this.nbDrap = nbDrap;
	}

	public int getNbDrap() {
		return nbDrap;
	}

	public void setModeleDem(ModeleDemineur modeleDem) {
		this.modeleDem = modeleDem;
	}

	public ModeleDemineur getModeleDem() {
		return modeleDem;
	}

	public void setTermine(boolean Termine) {
		this.termine = Termine;
	}

	public boolean isTermine() {
		return termine;
	}

}
