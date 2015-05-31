package mastermind.test;

import java.util.Calendar;

import mastermind.arz.application.Croupier;
import mastermind.arz.application.joueur.JoueurIA;
import mastermind.arz.application.strategy.knut.KnutStrategy;

public class StrategyTest {

	public static void main(final String[] args) {

		final int nbPartie = 1;

		int nbCoupCumule = 0;
		int nbCoupMax = 0;

		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// MastermindUtils.getInstance().loadCache(null);

		final Croupier croupier = new Croupier();

		final JoueurIA joueurIA = new JoueurIA(nbPartie);
		joueurIA.setStrategy(new KnutStrategy());

		croupier.jouerAvec(joueurIA);

		int tmp = 0;
		final long timeDeb = Calendar.getInstance().getTimeInMillis();
		for (int i = 0; i < nbPartie; i++) {

			if (i % 10 == 0) {

				System.out.println((i * 100 / nbPartie) + " %");
			}

			tmp = croupier.jouerEnTest();
			nbCoupMax = Math.max(nbCoupMax, tmp);
			nbCoupCumule = nbCoupCumule + tmp;

		}
		final long timeFin = Calendar.getInstance().getTimeInMillis();

		final long duree = timeFin - timeDeb;
		System.out.println("Executé en : " + duree + " ms");
	}

}
