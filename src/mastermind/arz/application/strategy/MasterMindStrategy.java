package mastermind.arz.application.strategy;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public interface MasterMindStrategy {

	Combinaison getEssai(ArrayList<Combinaison> allCombi, int nbrCoup, SwingWorker<PionCouleur[], JPanel> worker);

	void setPreviousResult(ResultatEssai resultat, int nbrCoup);

}
