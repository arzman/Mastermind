package mastermind.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;

import mastermind.arz.application.Croupier;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

import org.junit.Test;

public class MastermindTest {

	@Test
	public void Combinaisontest() {

		final Pion pion1 = new Pion(PionCouleur.BLANC);
		final Pion pion2 = new Pion(PionCouleur.VERT);
		final Pion pion3 = new Pion(PionCouleur.ROUGE);
		final Pion pion4 = new Pion(PionCouleur.FUSCHIA);

		assertTrue(pion1.getCouleur().equals(PionCouleur.BLANC));

		final Combinaison combi = new Combinaison();

		assertTrue(!combi.isComplete());

		assertFalse(combi.ajouterPion(pion4, 4));
		assertFalse(combi.ajouterPion(pion4, 5));
		assertFalse(combi.ajouterPion(pion4, -2));

		assertTrue(combi.ajouterPion(pion2, 1));
		assertTrue(combi.ajouterPion(pion1, -1));
		combi.ajouterPion(pion4, 3);
		combi.ajouterPion(pion3, -1);

		assertTrue(pion1.getPlace() == 0);
		assertTrue(pion3.getPlace() == 2);

		assertTrue(combi.isComplete());
		assertFalse(combi.ajouterPion(pion3, -1));

		final Pion pion5 = new Pion(PionCouleur.FUSCHIA);
		final Pion pion6 = new Pion(PionCouleur.FUSCHIA);
		pion6.setPlace(12);
		final Pion pion7 = new Pion(PionCouleur.FUSCHIA);
		final Pion pion8 = new Pion(PionCouleur.BLEU);

		assertFalse(pion5.equals(pion6));
		assertTrue(pion5.equals(pion7));
		assertFalse(pion5.equals(pion8));

		assertTrue(combi.getCouleurs().size() == 4);

		final ResultatEssai res = new ResultatEssai(5, 2);
		assertTrue(res.getNbrBienPlace() == 5);
		assertTrue(res.getNbrMalPlace() == 2);

		try {
			new ResultatEssai(16, 8);
			fail("Exception attendue");
		} catch (final InvalidParameterException e) {

		}
		try {
			new ResultatEssai(-2, 8);
			fail("Exception attendue");
		} catch (final InvalidParameterException e) {

		}
		try {
			new ResultatEssai(8, 16);
			fail("Exception attendue");
		} catch (final InvalidParameterException e) {

		}
		try {
			new ResultatEssai(8, -2);
			fail("Exception attendue");
		} catch (final InvalidParameterException e) {

		}

		final Combinaison combi2 = MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.VERT, PionCouleur.ROUGE });

		final int hascode = combi2.hashCode();

		final Combinaison combi3 = Combinaison.fromInt(hascode);

		assertTrue(combi2.equals(combi3));

	}

	@Test
	public void Croupiertest() {

		final Croupier croupier = new Croupier();

		assertTrue(croupier.getLaCombinaison() != null);

		final Combinaison combi1 = generateCombi(PionCouleur.BLEU, PionCouleur.ROUGE, PionCouleur.ROUGE, PionCouleur.BLANC);
		croupier.setLaCombinaison(combi1);

		final ResultatEssai res10 = croupier.getResultat(generateCombi(PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.ROUGE, PionCouleur.ROUGE));
		assertTrue(res10.getNbrBienPlace() == 1);
		assertTrue(res10.getNbrMalPlace() == 2);

		final ResultatEssai res11 = croupier.getResultat(generateCombi(PionCouleur.BLANC, PionCouleur.ROUGE, PionCouleur.VERT, PionCouleur.VERT));
		assertTrue(res11.getNbrBienPlace() == 1);
		assertTrue(res11.getNbrMalPlace() == 1);

		final ResultatEssai res12 = croupier.getResultat(generateCombi(PionCouleur.ROUGE, PionCouleur.VERT, PionCouleur.BLANC, PionCouleur.BLEU));
		assertTrue(res12.getNbrBienPlace() == 0);
		assertTrue(res12.getNbrMalPlace() == 3);

		final ResultatEssai res13 = croupier.getResultat(generateCombi(PionCouleur.BLANC, PionCouleur.BLEU, PionCouleur.BLEU, PionCouleur.ROUGE));
		assertTrue(res13.getNbrBienPlace() == 0);
		assertTrue(res13.getNbrMalPlace() == 3);

		final ResultatEssai res14 = croupier.getResultat(generateCombi(PionCouleur.BLEU, PionCouleur.VERT, PionCouleur.ROUGE, PionCouleur.BLANC));
		assertTrue(res14.getNbrBienPlace() == 3);
		assertTrue(res14.getNbrMalPlace() == 0);

		final ResultatEssai res15 = croupier.getResultat(generateCombi(PionCouleur.BLEU, PionCouleur.ROUGE, PionCouleur.BLEU, PionCouleur.BLANC));
		assertTrue(res15.getNbrBienPlace() == 3);
		assertTrue(res15.getNbrMalPlace() == 0);

		final ResultatEssai res16 = croupier.getResultat(generateCombi(PionCouleur.BLEU, PionCouleur.ROUGE, PionCouleur.ROUGE, PionCouleur.BLANC));
		assertTrue(res16.getNbrBienPlace() == 4);
		assertTrue(res16.getNbrMalPlace() == 0);

	}

	private Combinaison generateCombi(final PionCouleur c0, final PionCouleur c1, final PionCouleur c2, final PionCouleur c3) {

		final Combinaison combi1 = new Combinaison();
		combi1.ajouterPion(new Pion(c0), 0);
		combi1.ajouterPion(new Pion(c1), 1);
		combi1.ajouterPion(new Pion(c2), 2);
		combi1.ajouterPion(new Pion(c3), 3);

		return combi1;
	}

}
