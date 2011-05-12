package demineur.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import demineur.modele.Case;
import demineur.modele.CaseBombe;
import demineur.modele.CaseIndice;
import demineur.modele.ModeleDemineur;
import demineur.vue.VueDemineur;


	
	

public class ControleurCase implements MouseListener
{
	public Case _case ;
	public JButton _btnCase;
	private ModeleDemineur _modele;
	
	public ControleurCase (Case laCase, JButton btnCase, ModeleDemineur modele)
	{
		
		this._case = laCase;
		this._btnCase = btnCase;
		this._modele = modele;
		
	}

	public void mouseClicked(MouseEvent e) {
		//Si clic gauche
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			if ( this._modele.EstBombe(_case.posX, _case.posY))
				_btnCase.setIcon(_case.getImage());
			else
			{
				_btnCase.setIcon(null);
				_btnCase.setText(Integer.toString(_case.getIndice()));
			}
				
		}
		else
		{
			if (!_case.isFlag)
			{
				_case.isFlag = true;
				_btnCase.setIcon(_case.flag);
			}
			else
			{
				_case.isFlag = false;
				_btnCase.setIcon(null);
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	



}
