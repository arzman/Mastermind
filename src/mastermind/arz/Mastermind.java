package mastermind.arz;

import mastermind.arz.application.Croupier;
import mastermind.arz.application.joueur.JoueurIHM;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;

public class Mastermind {

	public static void main(final String[] args) {

		Combinaison.NOMBRE_PION = 5;
		Pion.NB_COULEUR_MAX = 10;

		final Croupier croupier = new Croupier();
		final JoueurIHM joueur = new JoueurIHM();

		croupier.jouerAvec(joueur);
		croupier.start();

	}

	public Mastermind() {
	}

}
