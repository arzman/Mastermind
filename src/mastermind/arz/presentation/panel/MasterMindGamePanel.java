package mastermind.arz.presentation.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;

public class MasterMindGamePanel extends JPanel {

	private static final long serialVersionUID = -3303990138282801139L;

	private final MasterMindHolePanel[][] _mastermindHoles;

	private final MasterMindResPanel[] _mastermindRes;

	public MasterMindGamePanel() {

		_mastermindHoles = new MasterMindHolePanel[10][Combinaison.NOMBRE_PION];
		_mastermindRes = new MasterMindResPanel[10];

		initHoles();

		setLayout(new GridBagLayout());
		createUi();

	}

	private void createUi() {

		for (int line = 0; line < _mastermindHoles.length; line++) {
			for (int col = 0; col < _mastermindHoles[line].length; col++) {

				final GridBagConstraints cons = new GridBagConstraints();
				cons.gridx = col;
				cons.gridy = _mastermindHoles.length - 1 - line;
				cons.anchor = GridBagConstraints.CENTER;
				cons.fill = GridBagConstraints.BOTH;
				cons.insets = new Insets(2, 2, 2, 2);

				add(_mastermindHoles[line][col], cons);
			}

			final GridBagConstraints cons2 = new GridBagConstraints();
			cons2.gridx = _mastermindHoles[line].length;
			cons2.gridy = _mastermindHoles.length - 1 - line;
			cons2.anchor = GridBagConstraints.CENTER;
			cons2.fill = GridBagConstraints.BOTH;
			cons2.insets = new Insets(2, 2, 2, 2);

			add(_mastermindRes[line], cons2);
		}

	}

	private void initHoles() {
		for (int line = 0; line < _mastermindHoles.length; line++) {
			for (int col = 0; col < _mastermindHoles[line].length; col++) {
				_mastermindHoles[line][col] = new MasterMindHolePanel(false);
			}
			_mastermindRes[line] = new MasterMindResPanel();
		}

	}

	public void resetGui() {

		for (int line = 0; line < _mastermindHoles.length; line++) {
			for (int col = 0; col < _mastermindHoles[line].length; col++) {
				_mastermindHoles[line][col].reinit();
			}
			_mastermindRes[line].reinit();
		}

	}

	public void setCombiAtLine(final PionCouleur[] combi, final int line) {

		if (line < _mastermindHoles.length) {

			for (int i = 0; i < _mastermindHoles[line].length; i++) {
				_mastermindHoles[line][i].setPionColor(combi[i]);

			}

		}

	}

	public void setResAtLine(final int nbBienPlace, final int nbMalPlace, final int line) {
		if (line < _mastermindRes.length) {

			for (int i = 0; i < _mastermindRes.length; i++) {
				_mastermindRes[line].setResult(nbBienPlace, nbMalPlace);

			}

		}

	}

}
