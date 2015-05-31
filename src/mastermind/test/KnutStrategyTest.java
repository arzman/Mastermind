package mastermind.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import mastermind.arz.application.strategy.knut.KnutStrategy;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

import org.junit.Test;

public class KnutStrategyTest {

	@Test
	public void Globaltest() {

		Pion.NB_COULEUR_MAX = 6;
		final KnutStrategy strat = new KnutStrategy();

		final ArrayList<Combinaison> possibleValue = new ArrayList<Combinaison>();

		for (int c1 = 0; c1 < 6; c1++) {
			for (int c2 = 0; c2 < 6; c2++) {
				for (int c3 = 0; c3 < 6; c3++) {
					for (int c4 = 0; c4 < 6; c4++) {
						possibleValue.add(MastermindUtils.getInstance().generateCombi(
								new PionCouleur[] { PionCouleur.fromInt(c1), PionCouleur.fromInt(c2), PionCouleur.fromInt(c3), PionCouleur.fromInt(c4) }));
					}
				}
			}
		}

		assertTrue(possibleValue.size() == 1296);

		final Combinaison laCombinaison = MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.ROUGE, PionCouleur.ROUGE });

		final Combinaison coup1 = strat.getEssai(possibleValue, 0, null);
		assertTrue(coup1.getCouleurAt(0).equals(PionCouleur.BLANC));
		assertTrue(coup1.getCouleurAt(1).equals(PionCouleur.BLANC));
		assertTrue(coup1.getCouleurAt(2).equals(PionCouleur.JAUNE));
		assertTrue(coup1.getCouleurAt(3).equals(PionCouleur.JAUNE));

		final ResultatEssai res1 = MastermindUtils.getInstance().getResultat(laCombinaison, coup1);
		assertTrue(res1.getNbrBienPlace() == 0);
		assertTrue(res1.getNbrMalPlace() == 1);

		updatePossibleValue(possibleValue, res1, coup1);
		assertTrue(possibleValue.size() == 256);

		final Combinaison coup2 = strat.getEssai(possibleValue, 1, null);
		assertTrue(coup2.getCouleurAt(0).equals(PionCouleur.JAUNE));
		assertTrue(coup2.getCouleurAt(1).equals(PionCouleur.ORANGE));
		assertTrue(coup2.getCouleurAt(2).equals(PionCouleur.ROUGE));
		assertTrue(coup2.getCouleurAt(3).equals(PionCouleur.ROUGE));

		final ResultatEssai res2 = MastermindUtils.getInstance().getResultat(laCombinaison, coup2);
		assertTrue(res2.getNbrBienPlace() == 3);
		assertTrue(res2.getNbrMalPlace() == 0);

		updatePossibleValue(possibleValue, res2, coup2);
		assertTrue(possibleValue.size() == 9);

		final Combinaison coup3 = strat.getEssai(possibleValue, 2, null);
		assertTrue(coup3.getCouleurAt(0).equals(PionCouleur.BLANC));
		assertTrue(coup3.getCouleurAt(1).equals(PionCouleur.ORANGE));
		assertTrue(coup3.getCouleurAt(2).equals(PionCouleur.ORANGE));
		assertTrue(coup3.getCouleurAt(3).equals(PionCouleur.FUSCHIA));

		final ResultatEssai res3 = MastermindUtils.getInstance().getResultat(laCombinaison, coup3);
		assertTrue(res3.getNbrBienPlace() == 0);
		assertTrue(res3.getNbrMalPlace() == 1);

		updatePossibleValue(possibleValue, res3, coup3);
		assertTrue(possibleValue.size() == 1);

	}

	@Test
	public void Globaltest2() {

		Pion.NB_COULEUR_MAX = 6;
		final KnutStrategy strat = new KnutStrategy();

		final ArrayList<Combinaison> possibleValue = new ArrayList<Combinaison>();

		for (final Combinaison comb : MastermindUtils.getInstance().getAllCombi()) {
			possibleValue.add(comb.cloneIt());
		}

		assertTrue(possibleValue.size() == 1296);

		final Combinaison laCombinaison = MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.ORANGE, PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.BLANC });

		final Combinaison coup1 = strat.getEssai(possibleValue, 0, null);
		assertTrue(coup1.getCouleurAt(0).equals(PionCouleur.BLANC));
		assertTrue(coup1.getCouleurAt(1).equals(PionCouleur.BLANC));
		assertTrue(coup1.getCouleurAt(2).equals(PionCouleur.JAUNE));
		assertTrue(coup1.getCouleurAt(3).equals(PionCouleur.JAUNE));

		final ResultatEssai res1 = MastermindUtils.getInstance().getResultat(laCombinaison, coup1);
		assertTrue(res1.getNbrBienPlace() == 1);
		assertTrue(res1.getNbrMalPlace() == 1);

		updatePossibleValue(possibleValue, res1, coup1);
		assertTrue(possibleValue.size() == 208);

		final Combinaison coup2 = strat.getEssai(possibleValue, 1, null);
		assertTrue(coup2.getCouleurAt(0).equals(PionCouleur.BLANC));
		assertTrue(coup2.getCouleurAt(1).equals(PionCouleur.BLANC));
		assertTrue(coup2.getCouleurAt(2).equals(PionCouleur.ORANGE));
		assertTrue(coup2.getCouleurAt(3).equals(PionCouleur.ROUGE));

		final ResultatEssai res2 = MastermindUtils.getInstance().getResultat(laCombinaison, coup2);
		assertTrue(res2.getNbrBienPlace() == 1);
		assertTrue(res2.getNbrMalPlace() == 2);

		updatePossibleValue(possibleValue, res2, coup2);
		assertTrue(possibleValue.size() == 34);

		final Combinaison coup3 = strat.getEssai(possibleValue, 2, null);
		assertTrue(coup3.getCouleurAt(0).equals(PionCouleur.BLANC));
		assertTrue(coup3.getCouleurAt(1).equals(PionCouleur.ORANGE));
		assertTrue(coup3.getCouleurAt(2).equals(PionCouleur.BLANC));
		assertTrue(coup3.getCouleurAt(3).equals(PionCouleur.FUSCHIA));

		final ResultatEssai res3 = MastermindUtils.getInstance().getResultat(laCombinaison, coup3);
		assertTrue(res3.getNbrBienPlace() == 0);
		assertTrue(res3.getNbrMalPlace() == 4);

		updatePossibleValue(possibleValue, res3, coup3);
		assertTrue(possibleValue.size() == 1);

	}

	public void updatePossibleValue(final ArrayList<Combinaison> listToUpdate, final ResultatEssai res, final Combinaison lastCombi) {

		final Iterator<Combinaison> iter = listToUpdate.iterator();

		while (iter.hasNext()) {

			final Combinaison combi = iter.next();
			final ResultatEssai resEssai = MastermindUtils.getInstance().getResultat(lastCombi, combi);

			if (!res.equals(resEssai)) {
				iter.remove();
			}
		}

	}

}
