package demineur.modele;

import java.util.Random;

import javax.swing.JButton;
import demineur.vue.VueDemineur;

public class ModeleDemineur {

	
	private Case [][] _tabCases;
	private int _nbColonnes, _nbLignes, _nbBombes;
	private boolean _partieTermine;
	private int DrapPose, DrapBienPose;
	private int NbCaseDecouverte ;
	public VueDemineur _vueDem;

	/***
	 * @param args
	 */
	
	public ModeleDemineur (VueDemineur vueDem)
	{
		_tabCases = new Case[vueDem.getNbLignes()][vueDem.getNbColonnes()];
		this._vueDem = vueDem;
		this._partieTermine = false;
		this.NbCaseDecouverte = vueDem.getNbBombes();
		this.DrapPose=0;
	}
	
	public ModeleDemineur(int colonne, int ligne, int bombe){
		
		//constructeur spécial pour la console
		
		_tabCases = new Case[ligne][colonne];
		//this._vueDem = vueDem;
		set_nbColonnes(colonne);
		set_nbLignes(ligne);
		set_nbBombes(bombe);
		NbCaseDecouverte = bombe;
		setDrapPose(0);
		this._partieTermine = false;
		
		//on génère la grille
		GenererGrille(colonne, ligne, bombe);
		
	}
	
	public Case get_tabCases(int x ,int y) {
		return _tabCases[x][y];
	}

	public void set_tabCases(int x ,int y, Case laCase) {
		_tabCases[x][y] = laCase;
	}
	
	public boolean isPartieTermine() {
		return _partieTermine;
	}

	public void setPartieTermine(boolean partieTermine) {
		this._partieTermine = partieTermine;
	}
	
	public int getDrapBienPose() {
		return DrapBienPose;
	}

	public void setDrapBienPose(int inc) {
		DrapBienPose += inc;
	}
	
	public void AjouterBombes (int nbBombes, int nbCol, int nbLig)
	{
		Random generator = new Random();
		int i=0,x,y;
		while (i < nbBombes) 
		{
			x = generator.nextInt(nbCol);
			y = generator.nextInt(nbLig);
			if (_tabCases[x][y] == null)
			{
				_tabCases[x][y] = new CaseBombe(x, y);
				System.out.println(x + " " + y);
				i++;
			}
		}
	}
	
	public void GenererGrille (int nbCol, int nbLig , int nbBombes)
	{
		AjouterBombes(nbBombes,nbCol,nbLig);
		for (int i=0 ; i < nbLig ; i++)
		{
			for (int j = 0 ; j < nbCol ; j++)
			{
				if (!EstBombe(i,j))
					_tabCases[i][j] = new CaseIndice(i, j,FindIndice(i,j,nbLig,nbCol));
			}
		}
		System.out.println("toto");
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
		return (_tabCases[lig][col] instanceof CaseBombe);
	}
	
	public void ZeroDiscover (int lig, int col,int nbLig,int nbCol)
	{
		if (!_tabCases[lig][col].isDecouverte())
		{
			_tabCases[lig][col].setDecouverte(true);
			NbCaseDecouverte++;
			//Si bord gauche
			if ( col -1 >= 0) // si on est tjrs dans la map
			{
				//et si bord haut
				if ( lig -1 >= 0)
				{
					if (_tabCases[lig-1][col-1].getIndice() == 0)
						DiscoverOne ( lig-1 , col-1 , nbLig , nbCol);
					else if (!EstBombe(lig-1,col-1))
						DiscoverOneIndice( lig-1 , col-1);	
				}
				//et si bord bas
				if (lig+1 <= nbLig-1)
				{
					if (_tabCases[lig+1][col-1].getIndice() == 0)
						DiscoverOne ( lig+1 , col-1 , nbLig , nbCol);
					else if (!EstBombe(lig+1,col-1))
						DiscoverOneIndice( lig+1 , col-1);
				}
				//et milieu

				if (_tabCases[lig][col-1].getIndice() == 0)
					DiscoverOne ( lig , col-1 , nbLig , nbCol);
				else if (!EstBombe(lig,col-1))
					DiscoverOneIndice( lig , col-1);
			}
			//Si bord droit
			if ( col+1 <= nbCol-1)
			{
				//et si bord haut
				if ( lig-1 >= 0)
				{
					if (_tabCases[lig-1][col+1].getIndice() == 0)
						DiscoverOne ( lig-1 , col+1 , nbLig , nbCol);
					else if (!EstBombe(lig-1,col+1))
						DiscoverOneIndice( lig-1 , col+1);
				}
				//et si bord bas
				if (lig+1 <= nbLig-1)
				{
					if (_tabCases[lig+1][col+1].getIndice() == 0)
						DiscoverOne ( lig+1 , col+1 , nbLig , nbCol);
					else if (!EstBombe(lig+1,col+1))
						DiscoverOneIndice( lig+1 , col+1);
				}
				
				//et milieu
				if (_tabCases[lig][col+1].getIndice() == 0)
					DiscoverOne( lig , col+1 , nbLig , nbCol);
				else if (!EstBombe(lig,col+1))
					DiscoverOneIndice ( lig , col+1);
			}
			
			//si Bord haut milieu
			if ( lig-1 >= 0)
			{
				if (_tabCases[lig-1][col].getIndice() == 0)
					DiscoverOne ( lig-1 , col , nbLig , nbCol);
				else if (!EstBombe(lig-1,col))
					DiscoverOneIndice( lig-1 , col);
			}
			
			//si Bord bas milieu
			if( lig+1 <= nbLig -1)
			{
				if (_tabCases[lig+1][col].getIndice() == 0)
					DiscoverOne ( lig+1 , col , nbLig , nbCol);
				else if (!EstBombe(lig+1,col))
					DiscoverOneIndice( lig+1 , col);
			}
		}
	}
	
	public void DiscoverOne (int lig , int col , int nbLig , int nbCol )
	{
		//JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig*nbCol+col);
		//btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
		if (!_tabCases[lig][col].isFlag())
		{
			ZeroDiscover(lig,col,nbLig, nbCol);
			if (!_tabCases[lig][col].isDecouverte())
			{
				_tabCases[lig][col].setDecouverte(true);
				NbCaseDecouverte++;
			}
		}
	}
	
	public void DiscoverOneIndice (int lig , int col)
	{
		//JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig*nbCol+col);
		//btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
		if (!_tabCases[lig][col].isFlag())
		{
			if (!_tabCases[lig][col].isDecouverte())
			{
				_tabCases[lig][col].setDecouverte(true);
				NbCaseDecouverte++;
			}
		}
	}
	
	public String score(long temps)
	{
		double resultat;
		resultat = (get_nbBombes() * get_nbColonnes() * get_nbColonnes())/(temps/10000);
		return Double.toString(resultat);
	}

	
	public void AfficherBombes ()
	{
		for (int lig = 0 ; lig < get_nbLignes() ; lig ++)
		{
			for (int col = 0 ; col < get_nbColonnes() ; col ++)
			{
				if (!_tabCases[lig][col].isDecouverte())
				{
					JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig* get_nbColonnes()+col);
					if (EstBombe(lig,col))
						btn.setIcon(CaseBombe.getBombe());
					else
						btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
					_tabCases[lig][col].setDecouverte(true);
				}
			}
		}
	}
	public boolean verifGagne() {

		//boolean rep=true;
		//if (getDrapPose()<0) rep = false;
		//if (getDrapBienPose() get_nbBombes())
		//return true;//rep;

		boolean rep=false;
		if ((getDrapBienPose()+ getDrapPose()) == 2*get_nbBombes()) rep = true;
		if ((getNbCaseDecouverte() + get_nbBombes()) == (get_nbColonnes()*get_nbLignes())) rep = true;
		
		return rep;

	}
	
	
	
	/*public boolean CasesIndicesDecouvertent ()
	{
		boolean flagCaseDecouverte = true;
		int lig =0, col = 0;
		
		while (lig < _vueDem.getNbLignes() && flagCaseDecouverte)
		{
			while (col < _vueDem.getNbColonnes() && flagCaseDecouverte)
			{
				if (_tabCases[lig][col] instanceof CaseIndice && !_tabCases[lig][col].isDecouverte())
					flagCaseDecouverte = false;
				col++;
			}
			col = 0;
			lig++;
		}
		return flagCaseDecouverte;
	}*/

	public int getNbCaseDecouverte() {
		return NbCaseDecouverte;
	}

	public void setNbCaseDecouverte(int inc) {
		NbCaseDecouverte += inc;
	}

	public void set_nbColonnes(int _nbColonnes) {
		this._nbColonnes = _nbColonnes;
	}

	public int get_nbColonnes() {
		return _nbColonnes;
	}

	public void set_nbLignes(int _nbLignes) {
		this._nbLignes = _nbLignes;
	}

	public int get_nbLignes() {
		return _nbLignes;
	}

	public void set_nbBombes(int _nbBombes) {
		this._nbBombes = _nbBombes;
	}

	public int get_nbBombes() {
		return _nbBombes;
	}

	public void setDrapPose(int drapPose) {
		DrapPose = drapPose;
	}

	public int getDrapPose() {
		return DrapPose;
	}



}
