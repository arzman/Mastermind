package mastermind.arz.application.strategy.knut.bypass;

import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

public class KnutByPass {

	public Combinaison bypass(int nbrCoup, ResultatEssai _firstRes) {
		
		
		Combinaison comb = null;
		
		if(nbrCoup==0){
			comb = byPassFirst();
		}
		
//		if(nbrCoup==1){
//			comb = byPassSecond(_firstRes);
//		}
		
		
		return comb;
	}

	public void storeCombinaison(int nbrCoup, ResultatEssai _firstRes, Combinaison lastCombi) {
		// TODO Auto-generated method stub
		
	}
	
	
	private Combinaison byPassSecond(ResultatEssai ancienres) {

		Combinaison res = null;
		if (Combinaison.NOMBRE_PION == 4) {
			if (Pion.NB_COULEUR_MAX == 6) {

			}
			if (Pion.NB_COULEUR_MAX == 8) {

			}
			if (Pion.NB_COULEUR_MAX == 10) {

			}
		}

		if (Combinaison.NOMBRE_PION == 5) {
			if (Pion.NB_COULEUR_MAX == 6) {

			}
			if (Pion.NB_COULEUR_MAX == 8) {

				res = KnutByPass58.byPassSecond(ancienres);

			}
			if (Pion.NB_COULEUR_MAX == 10) {

				res = KnutByPass510.byPassSecond(ancienres);

			}

		}

		return res;

	}

	private Combinaison byPassFirst() {

		Combinaison res = null;
		if (Combinaison.NOMBRE_PION == 4) {
			if (Pion.NB_COULEUR_MAX == 6) {
				res = MastermindUtils.getInstance()
						.generateCombi(new PionCouleur[] { PionCouleur.BLANC, PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.JAUNE });
			}
			if (Pion.NB_COULEUR_MAX == 8) {
				res = MastermindUtils.getInstance()
						.generateCombi(new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });
			}
			if (Pion.NB_COULEUR_MAX == 10) {
				res = MastermindUtils.getInstance()
						.generateCombi(new PionCouleur[] { PionCouleur.BLANC, PionCouleur.JAUNE, PionCouleur.ORANGE, PionCouleur.ROUGE });
			}
		}

		if (Combinaison.NOMBRE_PION == 5) {
			if (Pion.NB_COULEUR_MAX == 6) {
				res = KnutByPass56.byPassFirst();
			}

			if (Pion.NB_COULEUR_MAX == 8) {

				res = KnutByPass58.byPassFirst();

			}

			if (Pion.NB_COULEUR_MAX == 10) {

				res = KnutByPass510.byPassFirst();

			}

		}

		return res;
	}
	

}
