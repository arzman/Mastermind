package mastermind.arz.presentation.panel.cheatpanel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mastermind.arz.application.strategy.MasterMindStrategy;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;

public class CheatPanelWorker extends SwingWorker<PionCouleur[], JPanel> {

	private final String _key;
	private final MasterMindStrategy _strat;
	private final ArrayList<Combinaison> _allCombi;
	private final CheatPanel _cheatPanel;
	private final int _nbrCoup;

	public CheatPanelWorker(final String key, final MasterMindStrategy strat, final ArrayList<Combinaison> allCombi, final int nbrCoup,
			final CheatPanel cheatPanel) {
		_key = key;
		_strat = strat;
		_allCombi = allCombi;
		_cheatPanel = cheatPanel;
		_nbrCoup = nbrCoup;

	}

	@Override
	protected PionCouleur[] doInBackground() throws Exception {

		PionCouleur[] res = null;

		final Combinaison combi = _strat.getEssai(_allCombi, _nbrCoup, this);

		if (combi != null) {
			res = combi.toArray();
		}

		return res;
	}

	@Override
	protected void done() {

		super.done();
		if (!isCancelled()) {
			try {
				_cheatPanel.setCombi(_key, get());
			} catch (final InterruptedException e) {
				e.printStackTrace();
			} catch (final ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
