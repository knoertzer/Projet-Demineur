package demineur.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import demineur.controleur.ControleurBouton;
import demineur.controleur.ControleurCase;
import demineur.modele.ModeleDemineur;

@SuppressWarnings("serial")
public class VueDemineur extends JFrame{

	public ModeleDemineur modeleDem;

	private int compteur = 0;
	private int nbLignes = 10;
	private int nbColonnes = 10;
	private int nbBombes = 3;
	private int nbDrap = 3;

	public JPanel pnlPartie = new JPanel();
	public JPanel pnlJeux = new JPanel();
	public JPanel pnlInfo = new JPanel();
	public JPanel pnlGrille = new JPanel();
	public JPanel pnlJeuxW = new JPanel();
	public JPanel pnlJeuxE = new JPanel();
	public JPanel pnlOption = new JPanel();
	
	
	public JButton btnPartie = new JButton();
	
	private javax.swing.Timer timer ;
	//private demineur.options.Timer timer ;
	
	private static JLabel lblCptDrap  = new JLabel();
	public JButton btnRejouer = new JButton();
	private ImageIcon pika = new ImageIcon("./pok.gif");
	private ImageIcon deadPika = new ImageIcon("./deadPika.png");
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
	    pnlInfo.add(lblCptDrap);
	    pnlInfo.add(btnRejouer);
	    
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
		
		btnRejouer.setIcon(pika);
		btnRejouer.addActionListener( new ControleurBouton("btnRejouer",modeleDem,this));
		LancementTimer();
		
        setVisible(true);

    }

	public void RemplirGrille(int nbLig, int nbCol, int nbBom) {
		
		pnlGrille.setLayout(new GridLayout(nbLignes,nbColonnes));
		
		modeleDem = new ModeleDemineur(this);
		modeleDem.GenererGrille(nbCol,nbLig,nbBom);
		
		for (int i = 0 ; i < nbLig ; i++)
		{
			for (int j = 0 ;j < nbCol ; j++)
			{

					
				JButton btnCase = new JButton();
				btnCase.addMouseListener(new ControleurCase(modeleDem.get_tabCases(i,j),btnCase,modeleDem,this));
				/*if (j == 0 && i == 0)
					btnCase.setName("toto");*/
				pnlGrille.add(btnCase);
				
			}
		}
        //System.out.println("le control " + pnlGrille.getComponent(0).getName());
	}
	
	public void LancementTimer()
	{
	    timer = new javax.swing.Timer(1000, new ActionListener() {
	          public void actionPerformed(ActionEvent e) 
	          {
	      	    lblTemps.setText(String.valueOf(compteur));
	      	    compteur++;
	      	    lblCptDrap.setText(Integer.toString(nbDrap));
	          }
	       });
	    timer.start();
	}
	
	public void StoperTimer()
	{
		timer.stop();
	}
	
	public void RestartTimer()
	{
		timer.restart();
	}
	
	
	public ModeleDemineur getModeleDem() {
		return modeleDem;
	}


	public void setModeleDem(ModeleDemineur modeleDem) {
		this.modeleDem = modeleDem;
	}
    
	public int getNbLignes() {
		return nbLignes;
	}
	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}

	public int getNbBombes() {
		return nbBombes;
	}

	public void setNbBombes(int nbBombes) {
		this.nbBombes = nbBombes;
	}
	public int getNbDrap() {
		return nbDrap;
	}

	public void setNbDrap(int nbDrap) {
		this.nbDrap = nbDrap;
	}
	public JLabel getLblCptDrap() {
		return lblCptDrap;
	}

	public void setLblCptDrap(String nbDrap) {
		VueDemineur.lblCptDrap.setText(nbDrap);
	}
	
	public int getCompteur() {
		return compteur;
	}

	public javax.swing.Timer getTimer() {
		return timer;
	}
	
	public ImageIcon getDeadPika() {
		return deadPika;
	}
	
	public ImageIcon getPika() {
		return pika;
	}




    
}
