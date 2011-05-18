package demineur.options;

import java.io.*; // Pour les classes flux

/**
 * Une classe specialement pour lire des infos au clavier
 * 
 * @author Bastoul
 */
public class Console {
	/**
	 * @return une chaine correspondant a ce qui est lu au clavier.
	 */
	public static String lireString() {
		String ligne = null;
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			ligne = entree.readLine();
		} catch (IOException ex) {
			System.exit(0);
		}
		return ligne;
	}

	/**
	 * @return un flottant lu depuis le clavier.
	 */
	public static float lireFloat() {
		float x = 0;
		try {
			String ligne = lireString();
			x = Float.parseFloat(ligne);
		} catch (NumberFormatException ex) {
			System.out.println("Erreur format !");
			System.exit(0);
		}
		return x;
	}

	/**
	 * @return un entier lu depuis le clavier.
	 */
	public static int lireInt() {
		int x = 0;
		try {
			String ligne = lireString();
			x = Integer.parseInt(ligne);
		} catch (NumberFormatException ex) {
			System.out.println("Erreur format !");
			System.exit(0);
		}
		return x;
	}
}
