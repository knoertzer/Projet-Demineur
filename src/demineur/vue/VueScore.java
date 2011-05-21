package demineur.vue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VueScore extends JFrame{

	public VueScore() {
	    //Définit un titre pour votre fenêtre
	    setTitle("Votre choix!");
	    //Définit une taille pour celle-ci ; ici, 500 px de large et 500 px de haut
	    
	    setSize(600,600);
	    //Nous allons maintenant dire à notre objet de se positionner au centre
	    setLocationRelativeTo(null);
	    //Terminer le processus lorsqu'on clique sur "Fermer"
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Permet le redimentionnement de la fenetre
	    setResizable(false);
	    //Permet a la fenetre d'etre toujours au premier plan
	    setAlwaysOnTop(true);
	    
	    
	    
	    setVisible(true);
	}

}
