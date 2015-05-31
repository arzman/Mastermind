package mastermind.arz.presentation.panel.cheatpanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import mastermind.arz.application.joueur.JoueurIHM;
import mastermind.arz.application.strategy.MasterMindStrategy;
import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.domaine.Pion;
import mastermind.arz.domaine.ResultatEssai;
import mastermind.arz.presentation.panel.MasterMindHolePanel;

/**
 * Panneau d'aide. Ce panneau présente des combinaisons jouées par différentes
 * IA, ainsi que le nombre de solutions restantes.
 * 
 */
public class CheatPanel extends JPanel {

	/**
	 * Identifiant
	 */
	private static final long serialVersionUID = 2760117787349894301L;

	/**
	 * Map des panneaux permettant d'affichier une proposition de combinaison
	 */
	private final HashMap<String, MasterMindHolePanel[]> _mastermindHolesMap;

	/**
	 * Button d'activation de l'aide
	 */
	private JToggleButton _hintButton;

	/**
	 * Label affichant le nombre de solutions restantes
	 */
	private JLabel _restantLabel;

	/**
	 * Map des stratgies disponible pour l'aide.
	 */
	private final HashMap<String, MasterMindStrategy> _strategies;

	/**
	 * Map des stratgies disponible pour l'aide.
	 */
	private final HashMap<String, CheatPanelWorker> _workers;

	private final JoueurIHM _joueur;

	/**
	 * Constructeur
	 * 
	 * @param strategies
	 *            les stratégies disponibles.
	 */
	public CheatPanel(final HashMap<String, MasterMindStrategy> strategies, final JoueurIHM joueur) {

		_mastermindHolesMap = new HashMap<String, MasterMindHolePanel[]>();

		_workers = new HashMap<String, CheatPanelWorker>();

		_strategies = strategies;

		// pour chaque stratégies, création d'une IHM pour montrer une
		// proposition.
		for (final String key : _strategies.keySet()) {

			final MasterMindHolePanel[] mastermindHoles = new MasterMindHolePanel[Combinaison.NOMBRE_PION];

			for (int col = 0; col < mastermindHoles.length; col++) {
				mastermindHoles[col] = new MasterMindHolePanel(false);
			}

			_mastermindHolesMap.put(key, mastermindHoles);
		}

		// création de l'IHM
		setLayout(new GridBagLayout());
		createUi();

		_joueur = joueur;

	}

	private void createUi() {

		int line = 2;
		// création du label avec le nom de la stratégie et l'affichage de la
		// proposition
		for (final String key : _mastermindHolesMap.keySet()) {

			final GridBagConstraints consLbl = new GridBagConstraints();
			consLbl.gridy = line;
			consLbl.gridwidth = Combinaison.NOMBRE_PION;
			consLbl.anchor = GridBagConstraints.CENTER;
			consLbl.fill = GridBagConstraints.BOTH;
			consLbl.insets = new Insets(2, 2, 2, 2);
			add(new JLabel(key), consLbl);
			line++;

			final MasterMindHolePanel[] mastermindHoles = _mastermindHolesMap.get(key);

			for (int col = 0; col < mastermindHoles.length; col++) {

				final GridBagConstraints cons = new GridBagConstraints();
				cons.gridx = col;
				cons.gridy = line;
				cons.anchor = GridBagConstraints.CENTER;
				cons.fill = GridBagConstraints.NONE;

				add(mastermindHoles[col], cons);
				mastermindHoles[col].setHidden(true);

			}
			line++;
		}

		// ajout du bouton d'activation de l'aide
		_hintButton = new JToggleButton("Aide");
		_hintButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				boolean hidden = false;

				for (final String key : _mastermindHolesMap.keySet()) {

					final MasterMindHolePanel[] mastermindHoles = _mastermindHolesMap.get(key);

					for (int i = 0; i < Combinaison.NOMBRE_PION; i++) {
						mastermindHoles[i].setHidden(!mastermindHoles[i].isHidden());
						hidden = mastermindHoles[i].isHidden();
					}
				}

				if (!hidden) {
					_joueur.launchUpdate();
				}

			}
		});

		final GridBagConstraints cons = new GridBagConstraints();
		cons.gridy = 0;
		cons.gridwidth = Combinaison.NOMBRE_PION;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(2, 2, 2, 2);
		add(_hintButton, cons);

		// ajout du label du nombre de solutions restantes
		_restantLabel = new JLabel("Solutions restantes : " + Pion.NB_COULEUR_MAX * Pion.NB_COULEUR_MAX * Pion.NB_COULEUR_MAX * Pion.NB_COULEUR_MAX);
		final GridBagConstraints consLbl = new GridBagConstraints();
		consLbl.gridy = 1;
		consLbl.gridwidth = Combinaison.NOMBRE_PION;
		consLbl.anchor = GridBagConstraints.CENTER;
		consLbl.fill = GridBagConstraints.BOTH;
		consLbl.insets = new Insets(2, 2, 2, 2);
		add(_restantLabel, consLbl);

	}

	public synchronized void removeWorker(final String key) {
		_workers.remove(key);
	}

	public void setCombi(final String key, final PionCouleur[] bestEssai) {

		if (bestEssai != null) {

			for (int i = 0; i < Combinaison.NOMBRE_PION; i++) {
				_mastermindHolesMap.get(key)[i].setPionColor(bestEssai[i]);

			}
		}

	}

	public void updateLabel(final int restant) {
		_restantLabel.setText("Solutions restantes : " + restant);
	}

	public void updatePropositions(final ArrayList<Combinaison> allCombi, final int nbrCoup) {

		if (_hintButton.isSelected()) {

			if (!allCombi.isEmpty()) {
				// pour toutes les strategies disponible
				for (final String key : _strategies.keySet()) {

					// on lance le calcul de la combinaison à jouer dans un
					// Thread
					// séparé.
					if (_workers.containsKey(key)) {
						_workers.get(key).cancel(true);
					}

					final CheatPanelWorker worker = new CheatPanelWorker(key, _strategies.get(key), new ArrayList<Combinaison>(allCombi), nbrCoup, this);
					_workers.put(key, worker);
					worker.execute();

				}
			}

		}

	}

	public void setPreviousResult(ResultatEssai res, int nbrCoup) {
		
		
		for(String key : _strategies.keySet()){
			_strategies.get(key).setPreviousResult(res,nbrCoup);
		}
		
	}

}
