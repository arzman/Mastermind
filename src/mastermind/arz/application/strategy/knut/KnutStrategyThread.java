package mastermind.arz.application.strategy.knut;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class KnutStrategyThread extends Thread implements Comparable<KnutStrategyThread>{

	private final ArrayList<Combinaison> _possibleCombi;
	private int _max = -1;
	private Combinaison _lastCombi;
	private final SwingWorker<PionCouleur[], JPanel> _worker;
	private final int _begin;
	private final int _end;

	public KnutStrategyThread(final ArrayList<Combinaison> possibleCombi, final int begin, final int end, final SwingWorker<PionCouleur[], JPanel> worker) {

		_possibleCombi = possibleCombi;
		_worker = worker;
		_begin = begin;
		_end = end;
	}

	public Combinaison getCombinaison() {
		return _lastCombi;
	}

	public int getMax() {
		return _max;
	}

	public int[] getScore(final Combinaison combi, final ArrayList<Combinaison> possibleCombi) {

		final int[] scores = new int[MastermindUtils.getInstance().getAllResults().length];

		ResultatEssai resEssaiTest;
		Combinaison combiTest;

		for (int i = 0; i < possibleCombi.size(); i++) {

			combiTest = possibleCombi.get(i);
			resEssaiTest = MastermindUtils.getInstance().getResultat(combiTest, combi);

			scores[resEssaiTest.getIndex()]++;

		}

		for (int i = 0; i < scores.length; i++) {
			scores[i] = possibleCombi.size() - scores[i];
		}

		return scores;
	}

	@Override
	public void run() {

		super.run();
		boolean doRun = true;
		Combinaison combi;

		// pour chaque combinaison
		for (int i = _begin; i < _end && doRun; i++) {

			combi = MastermindUtils.getInstance().getAllCombi()[i];
			final int[] scores = getScore(combi, _possibleCombi);

			// on prend le pire cas
			Arrays.sort(scores);
			final int minScore = scores[0];

			// on prend le meilleur pire cas
			if (minScore == _max) {

				if (combi.getNumericValue() < _lastCombi.getNumericValue()) {
					_lastCombi = combi;
				}
			}

			if (minScore > _max) {
				_max = minScore;
				_lastCombi = combi;
			}

			if (_worker != null && _worker.isCancelled()) {
				doRun = false;
			}
		}

	}
	
	@Override
	public int compareTo(KnutStrategyThread o) {
		int res=0;
		
		if(_max<o._max){
			res =-1;
		}else{
			
			if(_max==o._max){
				
				if(_lastCombi.getNumericValue()<o._lastCombi.getNumericValue()){
					res = -1;
				}else{
					res = 1;
				}
			}else{
				res = 1;
			}
		}
		
		return res;
	}
}
