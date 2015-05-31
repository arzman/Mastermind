package mastermind.arz.application.strategy.knut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.application.strategy.MasterMindStrategy;
import mastermind.arz.application.strategy.knut.bypass.KnutByPass510;
import mastermind.arz.application.strategy.knut.bypass.KnutByPass56;
import mastermind.arz.application.strategy.knut.bypass.KnutByPass58;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

public class KnutStrategy implements MasterMindStrategy {

	private ResultatEssai _firstRes;

	private final int NB_THREAD = 3;

	public KnutStrategy() {

	}

	@Override
	public Combinaison getEssai(final ArrayList<Combinaison> possibleCombi, final int nbrCoup, final SwingWorker<PionCouleur[], JPanel> worker) {

		Combinaison lastCombi = null;

		if (possibleCombi.size() == 1) {
			lastCombi = possibleCombi.get(0);
		} else {

			// premier essai on by-pass le calcul
			if (nbrCoup == 0) {

				lastCombi = byPassFirst();
			}

			// second essai, on by-pass également le calcul
			if (nbrCoup == 1) {

				lastCombi = byPassSecond();
			}

			if (lastCombi == null) {

				lastCombi = possibleCombi.get(0);

				final ArrayList<KnutStrategyThread> calls = new ArrayList<KnutStrategyThread>();
				for (int i = 0; i < NB_THREAD; i++) {

					int begin = (i * MastermindUtils.getInstance().getAllCombi().length) / NB_THREAD;
					int end = ((i + 1) * MastermindUtils.getInstance().getAllCombi().length) / NB_THREAD;

					KnutStrategyThread thread = new KnutStrategyThread(possibleCombi, begin, end, worker);
					calls.add(thread);
				}

				final BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<Runnable>(6);

				// Create the ThreadPoolExecutor
				final ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 10, TimeUnit.SECONDS, worksQueue, new ThreadPoolExecutor.CallerRunsPolicy());
				executor.allowCoreThreadTimeOut(true);

				for (final KnutStrategyThread call : calls) {
					executor.execute(call);
				}

				while (executor.getActiveCount() != 0) {
					try {
						Thread.sleep(500);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}

				Collections.sort(calls);
				lastCombi = calls.get(0).getCombinaison();

			}

		}

		return lastCombi;
	}

	private Combinaison byPassSecond() {

		Combinaison res = null;
		if (Combinaison.NOMBRE_PION == 4) {
			if (Pion.NB_COULEUR_MAX == 6) {

			}
			if (Pion.NB_COULEUR_MAX == 8) {

			}
			if (Pion.NB_COULEUR_MAX == 10) {

			}
		}

		if (Combinaison.NOMBRE_PION == 5) {
			if (Pion.NB_COULEUR_MAX == 6) {

			}
			if (Pion.NB_COULEUR_MAX == 8) {

				res = KnutByPass58.byPassSecond(_firstRes);

			}
			if (Pion.NB_COULEUR_MAX == 10) {

				res = KnutByPass510.byPassSecond(_firstRes);

			}

		}

		return res;

	}

	private Combinaison byPassFirst() {

		Combinaison res = null;
		if (Combinaison.NOMBRE_PION == 4) {
			if (Pion.NB_COULEUR_MAX == 6) {
				res = MastermindUtils.getInstance().generateCombi(
						new PionCouleur[] { PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.JAUNE });
			}
			if (Pion.NB_COULEUR_MAX == 8) {
				res = MastermindUtils.getInstance().generateCombi(
						new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });
			}
			if (Pion.NB_COULEUR_MAX == 10) {
				res = MastermindUtils.getInstance().generateCombi(
						new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });
			}
		}

		if (Combinaison.NOMBRE_PION == 5) {
			if (Pion.NB_COULEUR_MAX == 6) {
				res = KnutByPass56.byPassFirst();
			}

			if (Pion.NB_COULEUR_MAX == 8) {

				res = KnutByPass58.byPassFirst();

			}

			if (Pion.NB_COULEUR_MAX == 10) {

				res = KnutByPass510.byPassFirst();

			}

		}

		return res;
	}

	@Override
	public void setPreviousResult(ResultatEssai resultat, int nbrCoup) {

		if (nbrCoup == 0) {
			_firstRes = resultat;
		}

	}

}
