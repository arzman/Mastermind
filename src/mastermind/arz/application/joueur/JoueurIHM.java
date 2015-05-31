package mastermind.arz.application.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mastermind.arz.application.strategy.MasterMindStrategy;
import mastermind.arz.application.strategy.SimpleStrategy;
import mastermind.arz.application.strategy.irving.IrvingStrategy;
import mastermind.arz.application.strategy.knut.KnutStrategy;
import mastermind.arz.application.utils.MastermindUtils;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;
import mastermind.arz.presentation.controller.MasterMindController;
import mastermind.arz.presentation.panel.cheatpanel.CheatPanel;

/**
 * Impl�mentation de {@link Joueur}. Cette classe repr�sente un joueur qui
 * r�pond � la l'aide d'une interface graphique. L'IHM offre la saisie de la
 * combinaison, la pr�sentation du r�sultat et un aide dans la d�cision de la
 * combinaison � jouer.
 * 
 */
public class JoueurIHM implements Joueur {

	/**
	 * Indique si l'IHM est lanc�e.
	 */
	private boolean _isIHMlaunched;

	/**
	 * Stocke les combinaisons solutions restantes.
	 */
	private final ArrayList<Combinaison> _allCombi = new ArrayList<Combinaison>();

	/**
	 * La derni�re combinaison jou�e
	 */
	private Combinaison lastCombi;

	/**
	 * Panneau d'aide au jeu.
	 */
	private final CheatPanel _cheatPanel;

	private int _nbrCoup;

	/**
	 * Constructeur par d�faut
	 */
	public JoueurIHM() {

		_isIHMlaunched = false;
		final HashMap<String, MasterMindStrategy> strategies = new HashMap<String, MasterMindStrategy>();
		// remplissage des strat�gies avec leurs noms
		strategies.put("Knut", new KnutStrategy());
		strategies.put("Irving", new IrvingStrategy());
		strategies.put("Random", new SimpleStrategy());

		// cr�ation du panneau d'aide
		_cheatPanel = new CheatPanel(strategies, this);
		MasterMindController.getInstance().setControlPanel(_cheatPanel);

	}

	/**
	 * RaZ des combinaisons solutions restantes
	 */
	private void generateAllCombinaison() {

		_allCombi.clear();
		for (final Combinaison comb : MastermindUtils.getInstance().getAllCombi()) {
			_allCombi.add(comb.cloneIt());
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see mastermind.arz.application.joueur.Joueur#getEssai(int)
	 */
	@Override
	public synchronized Combinaison getEssai(final int nbrCoup) {

		// mise � z�ro de la derniere combinaison jou�e
		MasterMindController.getInstance().resetCurrentCombi();
		_nbrCoup = nbrCoup;

		if (nbrCoup == 2) {

			MastermindUtils.getInstance().loadCache(_allCombi.toArray(new Combinaison[_allCombi.size()]));
		}

		// mise � jour du panneau d'aide
		launchUpdate();

		// tant que le joueur n'a pas r�pondu, on attend
		while (MasterMindController.getInstance().getCurrentCombi() == null) {

			try {
				Thread.sleep(200);
			} catch (final InterruptedException e) {

				e.printStackTrace();
			}

		}

		// r�cup�ration de la combinaison jou�e
		final PionCouleur[] couleurs = MasterMindController.getInstance().getCurrentCombi();

		lastCombi = new Combinaison();

		for (int i = 0; i < Combinaison.NOMBRE_PION; i++) {

			if (couleurs[i] != null) {
				lastCombi.ajouterPion(new Pion(couleurs[i]));
			} else {
				lastCombi.ajouterPion(new Pion(PionCouleur.BLANC));
			}

		}

		// Mise � jour de l'IHM avec la combinaison jou�e
		MasterMindController.getInstance().setCombiAtLine(couleurs, nbrCoup);

		return lastCombi;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see mastermind.arz.application.joueur.Joueur#jouerEncore()
	 */
	@Override
	public boolean jouerEncore() {

		boolean res;

		// si l'IHM n'est pas lanc�e, on la lance
		if (!_isIHMlaunched) {
			MasterMindController.getInstance().createFrame();
			_isIHMlaunched = true;
			res = true;
		} else {
			// on demande � l'utilisateur s'il veut encore jouer
			res = MasterMindController.getInstance().getJouerEncore();
		}

		if (res) {
			// RaZ de l'�tat
			lastCombi = null;
			generateAllCombinaison();
			MasterMindController.getInstance().resetGui();
		}

		return res;
	}

	/**
	 * Lance la mise � jour du panneau d'aide.
	 * 
	 * @param cheatPanel
	 *            le
	 */
	public void launchUpdate() {

		// MaJ des combinaisons restantes
		_cheatPanel.updateLabel(_allCombi.size());
		_cheatPanel.updatePropositions(_allCombi, _nbrCoup);

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see mastermind.arz.application.joueur.Joueur#setPreviousResult(mastermind.arz.domaine.ResultatEssai,
	 *      int)
	 */
	@Override
	public void setPreviousResult(final ResultatEssai res, final int nbrCoup) {

		// MaJ IHM avec le r�sultat de l'essai
		MasterMindController.getInstance().setResAtLine(res.getNbrBienPlace(), res.getNbrMalPlace(), nbrCoup);

		//Indication du r�sultat aux strat�gies
		_cheatPanel.setPreviousResult(res,nbrCoup);
		
		
		// MaJ des solutions restantes
		_allCombi.remove(lastCombi);

		final Iterator<Combinaison> iter = _allCombi.iterator();

		// Pour toutes les combinaisons solution restantes
		while (iter.hasNext()) {

			final Combinaison combi = iter.next();
			final ResultatEssai resEssai = MastermindUtils.getInstance().getResultat(combi, lastCombi);

			if (!res.equals(resEssai)) {
				// si la solution n'est pas compatible avec le dernier r�sultat
				// obtenu, on l'enleve
				iter.remove();
			}

		}

	}

}
