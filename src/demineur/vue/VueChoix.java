package demineur.vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VueChoix extends JFrame{

	/**
	 * 
	 */
	public JButton btnConsole;
	public JButton btnGraphique;
	
	public VueChoix() 
	{
	    //Définit un titre pour votre fenêtre
	    setTitle("Votre choix!");
	    //Définit une taille pour celle-ci ; ici, 500 px de large et 500 px de haut
	    
	    setSize(600,200);
	    //Nous allons maintenant dire à notre objet de se positionner au centre
	    setLocationRelativeTo(null);
	    //Terminer le processus lorsqu'on clique sur "Fermer"
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Permet le redimentionnement de la fenetre
	    setResizable(false);
	    //Permet a la fenetre d'etre toujours au premier plan
	    setAlwaysOnTop(true);
	    
	    setLayout(new GridLayout(1,2));
	    
	    btnConsole = new JButton("Mode Console");
	    btnConsole.setIcon(new ImageIcon("./pikaGraph.jpg"));
	    btnGraphique = new JButton("Mode Graphique");
	    
	    //setDisabledSelectedIcon (Icon disabledSelectedIcon)
	    btnGraphique.setIcon(new ImageIcon("./pikaRun.gif"));
	    
	    btnConsole.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				DemineurConsole fenetre = new DemineurConsole();
			}
		});
	    btnGraphique.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				VueDemineur fenetre = new VueDemineur();
			}
		});
	    
	    add(btnConsole);
	    add(btnGraphique);
	    
	    setVisible(true);
	}

}
