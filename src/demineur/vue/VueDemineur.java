package demineur.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import demineur.controleur.ControleurCase;
import demineur.modele.ModeleDemineur;
import demineur.options.Timer;

@SuppressWarnings("serial")
public class VueDemineur extends JFrame{

	public ModeleDemineur modeleDem;

	public static int compteur = 0;
	public static int nbLignes = 10;
	public static int nbColonnes = 10;
	public static int nbBombes = 20;
	
	public JPanel pnlPartie = new JPanel();
	public JPanel pnlJeux = new JPanel();
	public JPanel pnlInfo = new JPanel();
	public JPanel pnlGrille = new JPanel();
	public JPanel pnlJeuxW = new JPanel();
	public JPanel pnlJeuxE = new JPanel();
	public JPanel pnlOption = new JPanel();
	
	public JButton btnPartie = new JButton();
	
	public static demineur.options.Timer timer ;
	public static JLabel lblCptMines  = new JLabel();
	public JButton btnRejouer = new JButton(new ImageIcon("C:\\Users\\Xantho\\Documents\\INFORMATIQUE\\JAVA\\Demineur\\pok.gif"));
	public static JLabel lblTemps  = new JLabel();
	
	public JMenuBar menu = new JMenuBar();
	public JMenu mnuPartie = new JMenu("Partie");
	public JMenu mnuInterro = new JMenu("?");
	
	
	/***
     * Constructeur de la fenetre 
     */
    public VueDemineur() {
   
	    //Définit un titre pour votre fenêtre
	    setTitle("DEMINEUR !");
	    //Définit une taille pour celle-ci ; ici, 500 px de large et 500 px de haut
	    
	    setSize((int)((50-0.7)*nbColonnes), (int)((50-0.5)*nbLignes));
	    //Nous allons maintenant dire à notre objet de se positionner au centre
	    setLocationRelativeTo(null);
	    //Terminer le processus lorsqu'on clique sur "Fermer"
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Permet le redimentionnement de la fenetre
	    setResizable(false);
	    //Permet a la fenetre d'etre toujours au premier plan
	    setAlwaysOnTop(true);

	    //setLayout(null);
	    
	    
	    menu.add(mnuPartie);
	    menu.add(mnuInterro);
	    //pnlOption.add(menu);
	    
	    pnlPartie.setLayout(new BorderLayout());
	    pnlPartie.setBackground(Color.BLACK);
	    
	    pnlJeux.setLayout(new BorderLayout());
	    pnlJeux.setBackground(Color.RED);
	    
	    //pnlInfo.setSize(700, 100);
	    pnlInfo.setBackground(Color.GREEN);
	    pnlInfo.setLayout(new GridLayout(1,3));
	    pnlInfo.add(lblCptMines);
	    pnlInfo.add(btnRejouer);

	    
	    javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
	          public void actionPerformed(ActionEvent e) 
	          {
	      	    lblTemps.setText(String.valueOf(compteur));
	      	    compteur++;
	          }
	       });
	    t.start();
	    
	    
	    
	    pnlInfo.add(lblTemps);

	    pnlJeux.add("North",pnlInfo);
	    
	    //pnlGrille.setSize(20, 20);
	    pnlGrille.setBackground(Color.BLUE);
	    
	    RemplirGrille(nbLignes,nbColonnes,nbBombes);
	    
	    pnlJeux.add("Center",pnlGrille);
	    //pnlJeux.add("East",pnlJeuxE);
	    //pnlJeux.add("West",pnlJeuxW);
	    
	    //pnlPartie.add("North",pnlOption);
	    pnlPartie.add("Center",pnlJeux);
	    add(pnlPartie);
		add(menu, BorderLayout.PAGE_START);
        setVisible(true);


    }


	@SuppressWarnings("static-access")
	private void RemplirGrille(int nbLig, int nbCol, int nbBom) {
		
		pnlGrille.setLayout(new GridLayout(nbLignes,nbColonnes));
		
		modeleDem = new ModeleDemineur();
		modeleDem.GenererGrille(nbCol,nbLig,nbBom);
		
		for (int i = 0 ; i < nbLig ; i++)
		{
			for (int j = 0 ;j < nbCol ; j++)
			{
				JButton btnCase = new JButton();
				btnCase.addMouseListener(new ControleurCase(modeleDem.get_tabCases(i,j),btnCase,modeleDem));
				pnlGrille.add(btnCase);
				
			}
		}		
	}
	
	
	public ModeleDemineur getModeleDem() {
		return modeleDem;
	}


	public void setModeleDem(ModeleDemineur modeleDem) {
		this.modeleDem = modeleDem;
	}
    
    
    
}
