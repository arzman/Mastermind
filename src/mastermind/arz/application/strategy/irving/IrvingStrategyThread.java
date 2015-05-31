package mastermind.arz.application.strategy.irving;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class IrvingStrategyThread extends Thread implements Comparable<IrvingStrategyThread>{

	private final ArrayList<Combinaison> _possibleCombi;
	private double _min = -1;
	private Combinaison _lastCombi;
	private final SwingWorker<PionCouleur[], JPanel> _worker;
	private final int _begin;
	private final int _end;

	public IrvingStrategyThread(final ArrayList<Combinaison> possibleCombi, final int begin, final int end, final SwingWorker<PionCouleur[], JPanel> worker) {

		_possibleCombi = possibleCombi;
		_worker = worker;
		_begin = begin;
		_end = end;

	}

	public Combinaison getCombinaison() {

		return _lastCombi;
	}

	public double getMin() {
		return _min;
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

		final double possibleSize = _possibleCombi.size();
		int[] scores;
		double score;
		Combinaison combi;

		// pour chaque combinaison
		for (int i = _begin; i < _end && doRun; i++) {

			combi = MastermindUtils.getInstance().getAllCombi()[i];

			scores = getScore(combi, _possibleCombi);

			score = 0;
			for (final double ltScore : scores) {

				score = score + ltScore * ltScore / possibleSize;

			}

			if (_min == -1) {
				_min = score + 1;
			}

			if (score < _min) {
				_min = score;
				_lastCombi = combi;

			}
			if (_worker != null && _worker.isCancelled()) {
				doRun = false;
			}
		}
	}

	@Override
	public int compareTo(IrvingStrategyThread o) {
		int res=0;
		
		if(_min<o._min){
			res =-1;
		}else{
			
			if(_min==o._min){
				
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
