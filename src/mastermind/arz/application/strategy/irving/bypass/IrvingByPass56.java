package mastermind.arz.application.strategy.irving.bypass;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;

public class IrvingByPass56 {

	public static Combinaison byPassFirst() {
		return MastermindUtils.getInstance().generateCombi(
				new PionCouleur[] { PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.JAUNE, PionCouleur.ORANGE });
	}

}
