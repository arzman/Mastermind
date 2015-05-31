package mastermind.arz.application;

import mastermind.arz.application.joueur.Joueur;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;

public class Croupier extends Thread {

	private Combinaison laCombinaison;
	private Joueur _leJoueur;

	public Croupier() {
		initCombinaison();
	}

	public Combinaison getLaCombinaison() {
		return laCombinaison;
	}

	public ResultatEssai getResultat(final Combinaison uneCombinaison) {

		return MastermindUtils.getInstance().getResultat(laCombinaison, uneCombinaison);
	}

	public void initCombinaison() {

		final PionCouleur[] pc = new PionCouleur[Combinaison.NOMBRE_PION];

		for (int i = 0; i < pc.length; i++) {
			pc[i] = tirerCouleur();
		}

		laCombinaison = MastermindUtils.getInstance().generateCombi(pc);
	}

	public void jouerAvec(final Joueur leJoueur) {
		_leJoueur = leJoueur;

	}

	public int jouerEnTest() {

		_leJoueur.jouerEncore();
		initCombinaison();
		int coupRestant = 0;
		boolean gagne = false;

		Combinaison combiJoueur = null;
		ResultatEssai res;

		while (coupRestant != 10 && !gagne) {

			combiJoueur = _leJoueur.getEssai(coupRestant);

			res = getResultat(combiJoueur);

			_leJoueur.setPreviousResult(res, coupRestant);

			if (res.getNbrBienPlace() == Combinaison.NOMBRE_PION) {
				gagne = true;
			}
			coupRestant = coupRestant + 1;

		}

		return coupRestant;

	}

	@Override
	public void run() {

		super.run();
		while (_leJoueur.jouerEncore()) {

			initCombinaison();
			int coupRestant = 0;
			boolean gagne = false;

			while (coupRestant != 10 && !gagne) {

				final Combinaison combiJoueur = _leJoueur.getEssai(coupRestant);

				final ResultatEssai res = getResultat(combiJoueur);

				_leJoueur.setPreviousResult(res, coupRestant);

				if (res.getNbrBienPlace() == Combinaison.NOMBRE_PION) {
					gagne = true;
				}
				coupRestant = coupRestant + 1;

			}

			if (gagne) {
				System.out.println("GAGNE !!! en " + coupRestant + " coup(s)");
			} else {
				System.out.println("PERDU !");
			}
		}
	}

	public void setLaCombinaison(final Combinaison laCombinaison) {
		this.laCombinaison = laCombinaison;
	}

	protected PionCouleur tirerCouleur() {

		return PionCouleur.fromInt((int) (Math.random() * Pion.NB_COULEUR_MAX));
	}

}
