package demineur.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import demineur.modele.ModeleDemineur;
import demineur.vue.VueDemineur;

public class ControleurBouton implements ActionListener {
	
	private String nomBtn ;
	private ModeleDemineur _modele;
	private VueDemineur _vue;
	
	public ControleurBouton (String nomBtn , ModeleDemineur modele, VueDemineur vue) 
	{
		this.nomBtn = nomBtn;
		this._modele = modele;
		this._vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		_vue.pnlGrille.removeAll();
		//_vue.pnlGrille.add(new JButton());
		_modele.setPartieTermine(false);
		_vue.RestartTimer();
		_vue.btnRejouer.setIcon(_vue.getPika());
		_vue.RemplirGrille(_vue.getNbColonnes(), _vue.getNbLignes(), _vue.getNbBombes());
		_vue.setNbDrap(_vue.getNbBombes());
		
	}

		




}
