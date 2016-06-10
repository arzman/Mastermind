package mastermind.arz.application.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

public class MastermindUtils {

	private boolean _cacheLoaded = false;

	private static MastermindUtils _instance;

	public static MastermindUtils getInstance() {

		if (_instance == null) {
			_instance = new MastermindUtils();
		}

		return _instance;
	}

	private final Combinaison[] _allCombi;

	private ResultatEssai[][] _cache;

	private final ResultatEssai[] _allResult;

	private MastermindUtils() {

		int size = 1;

		for (int i = 0; i < Combinaison.NOMBRE_PION; i++) {
			size = size * Pion.NB_COULEUR_MAX;
		}

		_allCombi = new Combinaison[size];

		for (int i = 0; i < size; i++) {

			final Combinaison combTmp = Combinaison.fromInt(i);
			_allCombi[combTmp.hashCode()] = combTmp;
		}

		final ArrayList<ResultatEssai> resEssaiTmp = new ArrayList<ResultatEssai>();

		for (int bp = 0; bp < Combinaison.NOMBRE_PION + 1; bp++) {
			for (int mp = 0; mp < Combinaison.NOMBRE_PION + 1; mp++) {

				if (bp + mp == Combinaison.NOMBRE_PION) {

					if (bp != Combinaison.NOMBRE_PION - 1) {
						resEssaiTmp.add(new ResultatEssai(bp, mp));
					}

				}

				if (bp + mp < Combinaison.NOMBRE_PION) {
					resEssaiTmp.add(new ResultatEssai(bp, mp));
				}
			}
		}

		_allResult = new ResultatEssai[resEssaiTmp.size()];

		for (int i = 0; i < _allResult.length; i++) {
			_allResult[i] = resEssaiTmp.get(i);
			_allResult[i].setIndex((byte) i);

		}

	}

	public Combinaison generateCombi(final PionCouleur[] couleurs) {

		final Combinaison combi1 = new Combinaison();
		for (int i = 0; i < Combinaison.NOMBRE_PION; i++) {
			combi1.ajouterPion(new Pion(couleurs[i]), i);
		}

		return combi1;
	}

	public Combinaison[] getAllCombi() {
		return _allCombi;
	}

	public final ResultatEssai[] getAllResults() {
		return _allResult;
	}

	public ResultatEssai getResultat(final Combinaison combiRef, final Combinaison combiEssai) {

		ResultatEssai res = null;

		if (_cacheLoaded) {
			if (combiRef.hashCode() > combiEssai.hashCode()) {

				if (combiRef.hashCode() < _cache.length && _cache[combiRef.hashCode()] != null && combiEssai.hashCode() < _cache[combiRef.hashCode()].length) {

					res = _cache[combiRef.hashCode()][combiEssai.hashCode()];
				}
			} else {

				if (combiEssai.hashCode() < _cache.length && _cache[combiEssai.hashCode()] != null && combiRef.hashCode() < _cache[combiEssai.hashCode()].length) {

					res = _cache[combiEssai.hashCode()][combiRef.hashCode()];
				}

			}
		}

		if (res == null)

		{

			byte nbreBienPlace = 0;
			byte nbreMalPlace = 0;

			byte bpTmp;
			byte mpTmp;
			byte cInSolTmp;

			for (final PionCouleur colorInEssai : combiEssai.getCouleurs()) {

				bpTmp = combiRef.nbreBienPlaceForColor(colorInEssai, combiEssai);
				cInSolTmp = combiRef.getNbrColor(colorInEssai);

				mpTmp = (byte) Math.min(cInSolTmp - bpTmp, combiEssai.getNbrColor(colorInEssai) - bpTmp);
				mpTmp = (byte) Math.max(mpTmp, 0);

				nbreBienPlace = (byte) (nbreBienPlace + bpTmp);
				nbreMalPlace = (byte) (nbreMalPlace + mpTmp);

			}

			res = new ResultatEssai(nbreBienPlace, nbreMalPlace);

		}

		boolean finish = false;
		for (int i = 0; i < _allResult.length && !finish; i++)

		{
			if (_allResult[i].equals(res)) {
				finish = true;
				res.setIndex((byte) _allResult[i].getIndex());
			}
		}

		return res;

	}

	public final void loadCache(Combinaison[] possibleCombi) {

		if (!_cacheLoaded) {
			_cache = new ResultatEssai[_allCombi.length][];

			final JFrame progressFrame = new JFrame("Chargement en cours...");
			final JProgressBar pb = new JProgressBar();
			pb.setIndeterminate(true);
			final JLabel lbl = new JLabel("Pré-calcul des combinaisons");

			progressFrame.getContentPane().setLayout(new GridBagLayout());
			progressFrame.getContentPane().add(lbl,
					new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
			progressFrame.getContentPane().add(pb,
					new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

			progressFrame.pack();
			progressFrame.setVisible(true);

			if (possibleCombi == null) {
				possibleCombi = _allCombi;
			}

			final CacheLoaderCallable call1 = new CacheLoaderCallable(possibleCombi, possibleCombi, _cache, 0, possibleCombi.length / 3);
			final CacheLoaderCallable call2 = new CacheLoaderCallable(possibleCombi, possibleCombi, _cache, possibleCombi.length / 3,
					2 * possibleCombi.length / 3);
			final CacheLoaderCallable call3 = new CacheLoaderCallable(possibleCombi, possibleCombi, _cache, 2 * possibleCombi.length / 3, possibleCombi.length);

			final ArrayList<CacheLoaderCallable> calls = new ArrayList<CacheLoaderCallable>();
			calls.add(call1);
			calls.add(call2);
			calls.add(call3);

			final BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<Runnable>(6);

			// Create the ThreadPoolExecutor
			final ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 10, TimeUnit.SECONDS, worksQueue, new ThreadPoolExecutor.CallerRunsPolicy());
			executor.allowCoreThreadTimeOut(true);

			for (final CacheLoaderCallable call : calls) {
				executor.execute(call);
			}

			while (executor.getActiveCount() != 0) {
				try {
					Thread.sleep(500);
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			progressFrame.dispose();
			_cacheLoaded = true;

		}
	}

}
