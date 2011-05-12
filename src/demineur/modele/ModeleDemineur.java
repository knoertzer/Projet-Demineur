package demineur.modele;

import java.util.Random;

import demineur.vue.VueDemineur;

public class ModeleDemineur {

	
	private Case [][] _tabCases;
	
	
	/***
	 * @param args
	 */
	
	public ModeleDemineur ()
	{
		_tabCases = new Case[VueDemineur.nbLignes][VueDemineur.nbColonnes];

	}
	
	public Case get_tabCases(int x ,int y) {
		return _tabCases[x][y];
	}

	public void set_tabCases(int x ,int y, Case laCase) {
		_tabCases[x][y] = laCase;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void AjouterBombes (int nbBombes)
	{
		Random generator = new Random();
		int i=0,x,y;
		while (i < nbBombes) 
		{
			x = generator.nextInt(VueDemineur.nbColonnes);
			y = generator.nextInt(VueDemineur.nbLignes);
			if (_tabCases[x][y] == null)
			{
				_tabCases[y][x] = new CaseBombe(x, y);
				System.out.println(x + " " + y);
				i++;
			}
		}
		
	}
	
	public void GenererGrille (int nbCol, int nbLig , int nbBombes)
	{
		AjouterBombes(nbBombes);
		for (int i=0 ; i < nbLig ; i++)
		{
			for (int j = 0 ; j < nbCol ; j++)
			{
				if (!EstBombe(i,j))
				{
					_tabCases[i][j] = new CaseIndice(i, j,FindIndice(i,j,nbLig,nbCol));
					
				}
			}
		}
		
	}

	private int FindIndice(int lig, int col, int nbLig,int nbCol) {
		
		int nbBombesVisible = 0;
		//Si bord gauche
		if ( col -1 >= 0) // si on est tjrs dans la map
		{
			//et si bord haut
			if ( lig -1 >= 0)
			{
				if (EstBombe(lig-1,col-1))
					nbBombesVisible++;
			}
			//et si bord bas
			if (lig+1 <= nbLig-1)
			{
				if (EstBombe(lig+1,col-1))
					nbBombesVisible++;
			}
			//et milieu

			if (EstBombe(lig,col-1))
				nbBombesVisible++;

		}
		//Si bord droit
		if ( col+1 <= nbCol-1)
		{
			//et si bord haut
			if ( lig-1 >= 0)
			{
				if (EstBombe(lig-1,col+1))
					nbBombesVisible++;
			}
			//et si bord bas
			if (lig+1 <= nbLig-1)
			{
				if (EstBombe(lig+1,col+1))
					nbBombesVisible++;
			}
			//et milieu
			if (EstBombe(lig,col+1))
				nbBombesVisible++;
		}
		
		//si Bord haut milieu
		if ( lig-1 >= 0)
		{
			if (EstBombe(lig-1,col))
				nbBombesVisible++;
		}
		
		//si Bord bas milieu
		if( lig+1 <= nbLig -1)
		{
			if (EstBombe(lig+1,col))
				nbBombesVisible++;
		}
		return nbBombesVisible;
	}

	public boolean EstBombe(int lig, int col) 
	{
		return (_tabCases[col][lig] instanceof CaseBombe);
	}
	


}
