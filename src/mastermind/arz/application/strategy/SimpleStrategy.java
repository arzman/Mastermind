package mastermind.arz.application.strategy;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.ResultatEssai;

public class SimpleStrategy implements MasterMindStrategy {

	@Override
	public Combinaison getEssai(final ArrayList<Combinaison> allCombi, final int nbrCoup, final SwingWorker<PionCouleur[], JPanel> worker) {

		Combinaison res = null;
		if (!allCombi.isEmpty()) {

			int index = 0;
			if (allCombi.size() != 1) {

				index = (int) Math.round(Math.random() * allCombi.size());
				index = Math.min(allCombi.size() - 1, index);

			}
			res = allCombi.get(index);
		}
		return res;

	}

	@Override
	public void setPreviousResult(ResultatEssai resultat, int nbrCoup) {
		
		
	}

}
