package demineur.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class ModeleScore {
	
	private final static File _fichier = new File("./Scores.txt");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(ModeleScore.getTousLesScores());
		ModeleScore.insererScore("Flop", 100);
		System.out.println(ModeleScore.getTousLesScores());

	}

	public static String getTousLesScores(){
		String reponse="",ligne;
		BufferedReader ficTexte = null;
		try {
			ficTexte = new BufferedReader(new FileReader(_fichier));

		while ((ligne = ficTexte.readLine()) != null)
			      reponse+=ligne+"\n";
		
		ficTexte.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return reponse;
	}

	public static void insererScore(String nom, int score){
		String ligne,nomJoueur;
		BufferedReader ficTexte = null;
		boolean trouve=false;
		int scoreJoueur,positionJoueur;
		try {
			ficTexte = new BufferedReader(new FileReader(_fichier));

		while (((ligne = ficTexte.readLine()) != null) &&!trouve)
		{
			StringTokenizer ligneJoueur=new StringTokenizer(ligne, ";");
			positionJoueur=Integer.parseInt(ligneJoueur.nextToken());
			nomJoueur=ligneJoueur.nextToken();
			scoreJoueur=Integer.parseInt(ligneJoueur.nextToken());
			if (score>scoreJoueur){
				trouve=true;
				ecrireScore(positionJoueur-1,nom,score);
			}
		}
		ficTexte.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void ecrireScore(int position, String nom, int score) throws IOException {
		String avant=ModeleScore.getTousLesScores(),nvLigne;
		StringTokenizer ligneJoueur=new StringTokenizer(avant, "\n");
		ArrayList<String> listeScore= new ArrayList<String>();
		File tmp;
		FileWriter sortie = null;
		
		//on place les lignes dans la liste pour faciliter la manipulation
		for (int i =0;i<=ligneJoueur.countTokens();i++)
		{
			listeScore.add( ligneJoueur.nextToken());
			System.out.println(listeScore.get(i));
		}
		for (int i=position;i<10;i++){
			ligneJoueur= new StringTokenizer(listeScore.get(i),";");
			ligneJoueur.nextToken();
			nvLigne=(position+1)+";"+ligneJoueur.nextToken()+";"+ligneJoueur.nextToken();
			listeScore.set(position, nvLigne);
		}

		listeScore.set(position, position+";"+nom+";"+score);
		
		//on ne retient que les 10 derniers score
		listeScore.remove(10);
		
		//on met à jour le fichier des scores
		tmp= _fichier.createTempFile("", "modif");
		sortie = new FileWriter(tmp);

		for (int i=0;i<10;i++){
			sortie.write(listeScore.get(i));
			System.out.println(listeScore.get(i));
		}
		
		if(_fichier.delete()){
			tmp.renameTo(_fichier);
		}
	}
}
