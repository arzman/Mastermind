package mastermind.arz.presentation.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.presentation.controller.MasterMindController;

public class MasterMindEssaiPanel extends JPanel {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1672813325978171614L;

	private final MasterMindHolePanel[] _mastermindHoles;

	public MasterMindEssaiPanel() {

		_mastermindHoles = new MasterMindHolePanel[Combinaison.NOMBRE_PION];

		for (int col = 0; col < _mastermindHoles.length; col++) {
			_mastermindHoles[col] = new MasterMindHolePanel(true);
		}

		setLayout(new GridBagLayout());
		createUi();

	}

	private void createUi() {

		for (int col = 0; col < _mastermindHoles.length; col++) {

			final GridBagConstraints cons = new GridBagConstraints();
			cons.gridx = col;
			cons.anchor = GridBagConstraints.CENTER;
			cons.fill = GridBagConstraints.BOTH;
			cons.insets = new Insets(2, 2, 2, 2);

			add(_mastermindHoles[col], cons);
			_mastermindHoles[col].setPionColor(PionCouleur.BLANC);
		}

		final JButton tryButton = new JButton("Essai");
		tryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				final PionCouleur[] combi = new PionCouleur[Combinaison.NOMBRE_PION];
				for (int i = 0; i < combi.length; i++) {
					combi[i] = _mastermindHoles[i].getPionCouleur();
				}

				MasterMindController.getInstance().setEssaiCombinaison(combi);

			}
		});

		final GridBagConstraints cons = new GridBagConstraints();
		cons.gridy = 1;
		cons.gridwidth = Combinaison.NOMBRE_PION;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(2, 2, 2, 2);
		add(tryButton, cons);

	}

	public void setColorOn(final PionCouleur color, final int index) {
		_mastermindHoles[index].setPionColor(color);
	}

}
