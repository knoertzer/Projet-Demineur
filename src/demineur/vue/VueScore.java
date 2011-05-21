package demineur.vue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VueScore extends JFrame{

<<<<<<< HEAD
	public VueScore() {
=======
	
	private int score;
	private JPanel pnlResultatNorth;
	private JPanel pnlResultatCenter;
	private JPanel pnlResultatSouth;
	private JLabel message;
	private JLabel infoScore;
	private JLabel infoNom;
	private JTextField nom;
	private JButton btnValider;
	
	public VueScore(int score) {
		
		
		this.score = score;
		
>>>>>>> 859840a65dd0da21a97239cdc5cd8ef561363385
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
	    
	    
<<<<<<< HEAD
	    
	    setVisible(true);
	}
=======
	    pnlResultatNorth = new JPanel();
	    pnlResultatNorth.setLayout(new GridLayout(3,1));
		message = new JLabel("Vous avez gagné, Félicitation !!!! \n \n");
	    message.setFont(maFont);
	    message.setHorizontalAlignment(JTextField.CENTER);
		infoScore = new JLabel ("Votre score est : " + this.score);
		infoScore.setFont(maFont);
		infoScore.setHorizontalAlignment(JTextField.CENTER);
		pnlResultatNorth.add(message);
		pnlResultatNorth.add(infoScore);
		
	    pnlResultatCenter = new JPanel();
	    pnlResultatCenter.setLayout(new GridLayout(1,1));
	    
		infoNom = new JLabel ("Entrez votre nom ");
		infoNom.setFont(maFont);
		pnlResultatCenter.add(infoNom);
		pnlResultatCenter.add(nom = new JTextField());
		nom.setHorizontalAlignment(JTextField.CENTER);
		nom.setFont(maFont);
		
		pnlResultatSouth = new JPanel();
		pnlResultatSouth.add(btnValider = new JButton("Valider"));
		
		/*infoNom.setLocation(x, y);
		nom.setLocation(x, y);
		btnValider.setLocation(x, y);*/
		
		add("North",pnlResultatNorth);
		add("Center",pnlResultatCenter);
		add("South",pnlResultatSouth);

		
		
		//JTextField nom;
		//JButton btnValider;
	    
	    setVisible(true);
	}
	
	public static void main (String[] args)
	{
		VueScore vue = new VueScore(30);
	}
>>>>>>> 859840a65dd0da21a97239cdc5cd8ef561363385

}
