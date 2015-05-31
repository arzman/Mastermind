package mastermind.arz.application.joueur;

import java.util.ArrayList;
import java.util.Iterator;

import mastermind.arz.application.strategy.MasterMindStrategy;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

public class JoueurIA implements Joueur {

	private int nbPartie = 1;

	private final ArrayList<Combinaison> _allCombi = new ArrayList<Combinaison>();

	private Combinaison lastCombi;

	private MasterMindStrategy strategy;

	public JoueurIA(final int anbPartie) {
		nbPartie = anbPartie;
	}

	private void generateAllCombinaison() {

		_allCombi.clear();

		for (int c1 = 0; c1 < Pion.NB_COULEUR_MAX; c1++) {
			for (int c2 = 0; c2 < Pion.NB_COULEUR_MAX; c2++) {
				for (int c3 = 0; c3 < Pion.NB_COULEUR_MAX; c3++) {
					for (int c4 = 0; c4 < Pion.NB_COULEUR_MAX; c4++) {
						_allCombi.add(MastermindUtils.getInstance().generateCombi(
								new PionCouleur[] { PionCouleur.fromInt(c1), PionCouleur.fromInt(c2), PionCouleur.fromInt(c3), PionCouleur.fromInt(c4) }));
					}
				}
			}
		}

	}

	@Override
	public Combinaison getEssai(final int nbrCoup) {
		lastCombi = strategy.getEssai(_allCombi, nbrCoup, null);

		return lastCombi;
	}

	@Override
	public boolean jouerEncore() {

		boolean res = false;

		if (nbPartie > 0) {
			res = true;
			nbPartie = nbPartie - 1;
			lastCombi = null;
			generateAllCombinaison();

		}

		return res;
	}

	@Override
	public void setPreviousResult(final ResultatEssai res, final int nbrCoup) {

		_allCombi.remove(lastCombi);

		final Iterator<Combinaison> iter = _allCombi.iterator();

		while (iter.hasNext()) {

			final Combinaison combi = iter.next();
			final ResultatEssai resEssai = MastermindUtils.getInstance().getResultat(combi, lastCombi);

			if (!res.equals(resEssai)) {
				iter.remove();
			}

		}
		// System.out.println("Combinaison restantes : " + allCombi.size());

	}

	public void setStrategy(final MasterMindStrategy uneStrategy) {
		strategy = uneStrategy;

	}
}
