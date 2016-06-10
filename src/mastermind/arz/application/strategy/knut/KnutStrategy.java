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
import mastermind.arz.application.strategy.knut.bypass.KnutByPass;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class KnutStrategy implements MasterMindStrategy {

	private ResultatEssai _firstRes;

	private KnutByPass _bypass;

	private final int NB_THREAD = 3;

	public KnutStrategy() {

		_bypass = new KnutByPass();

	}

	@Override
	public Combinaison getEssai(final ArrayList<Combinaison> possibleCombi, final int nbrCoup, final SwingWorker<PionCouleur[], JPanel> worker) {

		Combinaison lastCombi = null;

		if (possibleCombi.size() == 1) {
			lastCombi = possibleCombi.get(0);
		} else {

			// on essaye le bypass
			lastCombi = _bypass.bypass(nbrCoup,_firstRes);

			// le bypass ne marche pas, on fait le calcul
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
			
			//on sauve le résultat dans le bypass
			_bypass.storeCombinaison(nbrCoup,_firstRes,lastCombi);
			

		}

		return lastCombi;
	}

	

	@Override
	public void setPreviousResult(ResultatEssai resultat, int nbrCoup) {

		_firstRes = resultat;

	}

}
