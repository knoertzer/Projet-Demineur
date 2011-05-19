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
		String reponse;
		int lig,col,opt;
		
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

			//Jouer
			System.out.println("Veuillez saisir une ligne :");
			lig=Console.lireInt();
			System.out.println("Veuillez saisir une colonne :");
			col=Console.lireInt();
			System.out.println("Voulez-vous :\n 1 - Planter un drapeau\n 2 - Appuyer sur la case\n 3 - Annuler la saisie");
			opt=Console.lireInt();
			
			//gestion des options
			switch(opt){
			case 1:
				System.out.println("Plantage de drapeau en cours");
				getModeleDem().get_tabCases(col, lig).setFlag(true);
				break;
			case 2:
				System.out.println("Decouverte en cours");
				if (getModeleDem().EstBombe(col, lig))
				{
					setTermine(true);
					System.out.println("ZETES 1 LOOOOOOOOOOOSER !!");				
				}
				else getModeleDem().ZeroDiscover(col, lig, getNbLignes(), getNbColonnes());
				break;
			case 3:
				System.out.println("Annulation");
				break;
			default:
				System.out.println("Option non-reconnue");
				break;
			}
			
			//Vérif

			majGrille();
			getModeleDem().verifGagne();
		}
		System.out.println("Souhaitez-vous rejouer ? (y/n)");
		reponse=Console.lireString();
		
		if (reponse == "y"){
			
		}
	}
	

	private void majGrille() {
		System.out.print("   ");
		for (int i=0;i<getNbColonnes();i++)System.out.print("["+i+"]");
		System.out.println("");
		for (int i = 0; i < getNbLignes(); i++) {
			System.out.print("["+i+"]");
			for (int j = 0; j < getNbColonnes(); j++) {
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
