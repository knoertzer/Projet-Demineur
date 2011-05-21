package demineur.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import demineur.modele.Case;
import demineur.modele.ModeleDemineur;
import demineur.vue.VueDemineur;
import demineur.vue.VueScore;

public class ControleurCase implements MouseListener {
	public Case _case;
	public JButton _btnCase;
	private ModeleDemineur _modele;
	private VueDemineur _vue;

	public ControleurCase(Case laCase, JButton btnCase, ModeleDemineur modele,
			VueDemineur vue) {

		this._case = laCase;
		this._btnCase = btnCase;
		this._modele = modele;
		this._vue = vue;

	}

	public void mouseClicked(MouseEvent e) {

		if (!_modele.isPartieTermine()) {
			// Si clic gauche
			if (e.getButton() == MouseEvent.BUTTON1) {
				// Si c'est une bombe
				if (_modele.EstBombe(_case.getPosX(), _case.getPosY())) {
					_btnCase.setIcon(_case.getImage());
					_modele.setPartieTermine(true);
					_vue.StoperTimer();
					_vue.btnRejouer.setIcon(_vue.getDeadPika());
					JOptionPane.showMessageDialog(null, "Vous avez perdu !",
							"Game Over", JOptionPane.INFORMATION_MESSAGE);
					_modele.AfficherBombes();

				}
				// sinon c'est une case Indice
				else 
				{
					if (_case.getIndice() == 0)
						_modele.ZeroDiscover(_case.getPosX(), _case.getPosY(),
								_vue.getNbLignes(), _vue.getNbColonnes());
					_btnCase.setIcon(null);
					_btnCase.setText(Integer.toString(_case.getIndice()));
					MajVue();

				}
				if (!_case.isDecouverte()) {
					_case.setDecouverte(true);
					_modele.setNbCaseDecouverte(1);
				}
				
				// SI win
				if (_modele.verifGagne()) 
				{
					_vue.StoperTimer();
					new VueScore(Integer.parseInt(_vue.lblTemps.getText()));
				}

			} 
			else 
			{
				if (!_case.isDecouverte() )
				{

					if (!_case.isFlag() )
					{
						if (_modele.EstBombe(_case.getPosX(), _case.getPosY()))
							_modele.setDrapBienPose(1);
						_case.setFlag(true);
						_btnCase.setIcon(_case.flag);
						_vue.setNbDrap(_vue.getNbDrap() - 1);
						_vue.setLblCptDrap(Integer.toString((_vue.getNbDrap())));
	
					} 
					else 
					{
						if (_modele.EstBombe(_case.getPosX(), _case.getPosY()))
							_modele.setDrapBienPose(-1);
						_case.setFlag(false);
						_btnCase.setIcon(null);
						_vue.setNbDrap(_vue.getNbDrap() + 1);
					}
					
					// SI win
					if (_modele.verifGagne()) 
					{
						_vue.StoperTimer();
						new VueScore(Integer.parseInt(_vue.lblTemps.getText()));
					}
				}
			}
		}

	}

	private void MajVue() {

		for (int i = 0; i < _vue.getNbColonnes(); i++) {
			for (int j = 0; j < _vue.getNbLignes(); j++) {
				if (_modele.get_tabCases(i, j).isDecouverte())
				{
					JButton btn = (JButton) _vue.pnlGrille.getComponent(i*_vue.getNbColonnes() + j);
					btn.setText(Integer.toString(_modele.get_tabCases(i, j).getIndice()));
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Si clic gauche

		/*
		 * if ( this._modele.EstBombe(_case.getPosX(), _case.getPosY()))
		 * _btnCase.setIcon(_case.getImage()); else { _btnCase.setIcon(null);
		 * _btnCase.setText(Integer.toString(_case.getIndice())); }
		 */

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
