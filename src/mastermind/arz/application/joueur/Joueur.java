package mastermind.arz.application.joueur;

import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public interface Joueur {

	Combinaison getEssai(int nbrCoup);

	boolean jouerEncore();

	void setPreviousResult(ResultatEssai res, int nbrCoup);

}
