package mastermind.arz.application.strategy.knut.bypass;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class KnutByPass510 {

	public static Combinaison byPassSecond(ResultatEssai _firstRes) {

		Combinaison res = null;

		if (_firstRes.toString().equals("0/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("0/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("0/2")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.BLANC, PionCouleur.JAUNE });
		}
		if (_firstRes.toString().equals("0/3")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.ORANGE, PionCouleur.BLANC, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("0/4")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.VIOLET, PionCouleur.JAUNE, PionCouleur.BLANC, PionCouleur.ROUGE, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("0/5")) {

		}
		if (_firstRes.toString().equals("1/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("1/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.BLANC, PionCouleur.ORANGE, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("1/2")) {

		}
		if (_firstRes.toString().equals("1/3")) {

		}
		if (_firstRes.toString().equals("1/4")) {

		}
		if (_firstRes.toString().equals("2/0")) {

		}
		if (_firstRes.toString().equals("2/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.VIOLET, PionCouleur.BLANC, PionCouleur.ORANGE, PionCouleur.FUSCHIA, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("2/2")) {

		}
		if (_firstRes.toString().equals("2/3")) {

		}
		if (_firstRes.toString().equals("3/0")) {

		}
		if (_firstRes.toString().equals("3/1")) {

		}
		if (_firstRes.toString().equals("3/2")) {

		}
		if (_firstRes.toString().equals("4/0")) {

		}

		return res;
	}

	public static Combinaison byPassFirst() {

		return MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });

	}

}
