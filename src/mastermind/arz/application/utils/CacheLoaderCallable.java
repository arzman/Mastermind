package mastermind.arz.application.utils;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class CacheLoaderCallable extends Thread {

	private final Combinaison[] _allCombi;
	private final ResultatEssai[][] _cache;
	private final int _begin;
	private final int _end;
	private final ResultatEssai[] _allResult;
	private final Combinaison[] _possibleCombi;

	public CacheLoaderCallable(final Combinaison[] allCombi, final Combinaison[] possCombi, final ResultatEssai[][] cache, final int begin, final int end) {

		_allCombi = MastermindUtils.getInstance().getAllCombi();
		_cache = cache;
		_begin = begin;
		_end = end;
		_allResult = MastermindUtils.getInstance().getAllResults();
		_possibleCombi = possCombi;
	}

	public ResultatEssai getResultat(final Combinaison combiRef, final Combinaison combiEssai) {

		ResultatEssai res = null;

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

		boolean finish = false;
		for (int i = 0; i < _allResult.length && !finish; i++) {
			if (_allResult[i].equals(res)) {
				finish = true;
				res.setIndex((byte) _allResult[i].getIndex());

			}
		}

		return res;
	}

	@Override
	public void run() {

		super.run();

		Combinaison combi1;
		Combinaison combi2;

		for (int i1 = _begin; i1 < _end; i1++) {

			combi1 = _allCombi[i1];
			_cache[combi1.hashCode()] = new ResultatEssai[combi1.hashCode() + 1];
			for (int i2 = 0; i2 < _possibleCombi.length; i2++) {
				combi2 = _allCombi[i2];
				if (combi2.hashCode() < _cache[combi1.hashCode()].length) {
					_cache[combi1.hashCode()][combi2.hashCode()] = getResultat(combi1, combi2);
				}

			}

		}
	}

}
