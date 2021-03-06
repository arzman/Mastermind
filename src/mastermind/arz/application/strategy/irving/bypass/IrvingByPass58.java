package mastermind.arz.application.strategy.irving.bypass;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class IrvingByPass58 {

	public static Combinaison byPassFirst() {

		return MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });
	}

	public static Combinaison byPassSecond(ResultatEssai _firstRes) {
		Combinaison res = null;

		if (_firstRes.toString().equals("0/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.FUSCHIA, PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("0/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("0/2")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.ORANGE, PionCouleur.ROUGE, PionCouleur.FUSCHIA });
		}
		if (_firstRes.toString().equals("0/3")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE, PionCouleur.FUSCHIA, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("0/4")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("0/5")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ORANGE, PionCouleur.JAUNE, PionCouleur.ROUGE });
		}
		if (_firstRes.toString().equals("1/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.FUSCHIA, PionCouleur.VIOLET, PionCouleur.JAUNE, PionCouleur.VERT, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("1/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("1/2")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.BLANC, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("1/3")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.ORANGE, PionCouleur.JAUNE, PionCouleur.JAUNE });
		}
		if (_firstRes.toString().equals("1/4")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.BLANC, PionCouleur.ROUGE, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("2/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.VIOLET, PionCouleur.VERT });
		}
		if (_firstRes.toString().equals("2/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.BLANC, PionCouleur.ORANGE, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("2/2")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.ROUGE });
		}
		if (_firstRes.toString().equals("2/3")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ROUGE });
		}
		if (_firstRes.toString().equals("3/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.VIOLET });
		}
		if (_firstRes.toString().equals("3/1")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.FUSCHIA, PionCouleur.JAUNE, PionCouleur.JAUNE, PionCouleur.ORANGE });
		}
		if (_firstRes.toString().equals("3/2")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.ROUGE });
		}
		if (_firstRes.toString().equals("4/0")) {
			res = MastermindUtils.getInstance().generateCombi(
					new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.JAUNE, PionCouleur.ROUGE, PionCouleur.FUSCHIA });
		}

		return res;
	}

}
