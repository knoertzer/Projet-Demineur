package demineur.modele;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import demineur.vue.VueDemineur;

public class ModeleDemineur {

	
	private Case [][] _tabCases;
	public VueDemineur _vueDem;
	private boolean _partieTermine;
	private int DrapBienPose ;

	/***
	 * @param args
	 */
	
	public ModeleDemineur (VueDemineur vueDem)
	{
		_tabCases = new Case[vueDem.getNbLignes()][vueDem.getNbColonnes()];
		this._vueDem = vueDem;
		this._partieTermine = false;

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
	
	public void AjouterBombes (int nbBombes)
	{
		Random generator = new Random();
		int i=0,x,y;
		while (i < nbBombes) 
		{
			x = generator.nextInt(_vueDem.getNbColonnes());
			y = generator.nextInt(_vueDem.getNbLignes());
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
		AjouterBombes(nbBombes);
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
	
	public void ZeroDiscover (int lig, int col,int nbLig,int nbCol,JButton leBtn)
	{
		if (!_tabCases[lig][col].isDecouverte())
		{
			_tabCases[lig][col].setDecouverte(true);
			//Si bord gauche
			if ( col -1 >= 0) // si on est tjrs dans la map
			{
				//et si bord haut
				if ( lig -1 >= 0)
				{
					if (_tabCases[lig-1][col-1].getIndice() == 0)
						DiscoverOne ( lig-1 , col-1 , nbLig , nbCol);
					else if (!EstBombe(lig-1,col-1))
						DiscoverOneIndice( lig-1 , col-1 , nbLig , nbCol);	
				}
				//et si bord bas
				if (lig+1 <= nbLig-1)
				{
					if (_tabCases[lig+1][col-1].getIndice() == 0)
						DiscoverOne ( lig+1 , col-1 , nbLig , nbCol);
					else if (!EstBombe(lig+1,col-1))
						DiscoverOneIndice( lig+1 , col-1 , nbLig , nbCol);
				}
				//et milieu

				if (_tabCases[lig][col-1].getIndice() == 0)
					DiscoverOne ( lig , col-1 , nbLig , nbCol);
				else if (!EstBombe(lig,col-1))
					DiscoverOneIndice( lig , col-1 , nbLig , nbCol);
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
						DiscoverOneIndice( lig-1 , col+1 , nbLig , nbCol);
				}
				//et si bord bas
				if (lig+1 <= nbLig-1)
				{
					if (_tabCases[lig+1][col+1].getIndice() == 0)
						DiscoverOne ( lig+1 , col+1 , nbLig , nbCol);
					else if (!EstBombe(lig+1,col+1))
						DiscoverOneIndice( lig+1 , col+1 , nbLig , nbCol);
				}
				
				//et milieu
				if (_tabCases[lig][col+1].getIndice() == 0)
					DiscoverOne( lig , col+1 , nbLig , nbCol);
				else if (!EstBombe(lig,col+1))
					DiscoverOneIndice ( lig , col+1 , nbLig , nbCol);
			}
			
			//si Bord haut milieu
			if ( lig-1 >= 0)
			{
				if (_tabCases[lig-1][col].getIndice() == 0)
					DiscoverOne ( lig-1 , col , nbLig , nbCol);
				else if (!EstBombe(lig-1,col))
					DiscoverOneIndice( lig-1 , col , nbLig , nbCol);
			}
			
			//si Bord bas milieu
			if( lig+1 <= nbLig -1)
			{
				if (_tabCases[lig+1][col].getIndice() == 0)
					DiscoverOne ( lig+1 , col , nbLig , nbCol);
				else if (!EstBombe(lig+1,col))
					DiscoverOneIndice( lig+1 , col , nbLig , nbCol);
			}
		}
	}
	
	public void DiscoverOne (int lig , int col , int nbLig , int nbCol )
	{
		JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig*nbCol+col);
		btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
		ZeroDiscover(lig,col,nbLig, nbCol,btn);
		_tabCases[lig][col].setDecouverte(true);
	}
	
	public void DiscoverOneIndice (int lig , int col , int nbLig , int nbCol )
	{
		JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig*nbCol+col);
		btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
		_tabCases[lig][col].setDecouverte(true);
	}
	
	public String score()
	{
		int resultat;
		resultat = (_vueDem.getNbBombes() * _vueDem.getNbColonnes()*  _vueDem.getNbColonnes())/ _vueDem.getCompteur();
		return Integer.toString(resultat);
	}
	
	public void AfficherBombes ()
	{
		for (int lig = 0 ; lig < _vueDem.getNbLignes() ; lig ++)
		{
			for (int col = 0 ; col < _vueDem.getNbColonnes() ; col ++)
			{
				if (!_tabCases[lig][col].isDecouverte())
				{
					JButton btn = (JButton) _vueDem.pnlGrille.getComponent(lig* _vueDem.getNbColonnes()+col);
					if (EstBombe(lig,col))
						btn.setIcon(CaseBombe.getBombe());
					else
						btn.setText(Integer.toString(_tabCases[lig][col].getIndice()));
					_tabCases[lig][col].setDecouverte(true);
				}
			}
		}
	}

}
