package mastermind.arz.presentation.controller;

import java.awt.Component;

import javax.swing.JOptionPane;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Combinaison;
import mastermind.arz.presentation.frame.MasterMindFrame;

public class MasterMindController {

	private static MasterMindController _instance;

	public static MasterMindController getInstance() {

		if (_instance == null) {
			_instance = new MasterMindController();
		}

		return _instance;
	}

	private PionCouleur[] _currentCombi;

	private MasterMindFrame _frame;

	private Component _customComp;

	public void createFrame() {

		_frame = new MasterMindFrame(_customComp);
		_frame.setVisible(true);

	}

	public PionCouleur[] getCurrentCombi() {
		return _currentCombi;
	}

	public MasterMindFrame getFrame() {
		return _frame;
	}

	public boolean getJouerEncore() {

		final int rep = JOptionPane.showConfirmDialog(_frame, "Voulez-vous rejouer ?", "Rejouer", JOptionPane.YES_NO_OPTION);

		return rep == JOptionPane.YES_OPTION;
	}

	public void resetCurrentCombi() {
		_currentCombi = null;

	}

	public void resetGui() {
		_frame.getMastermindGamePanel().resetGui();

	}

	public void setCombiAtLine(final PionCouleur[] combi, final int line) {

		if (_frame.getMastermindGamePanel() != null) {
			_frame.getMastermindGamePanel().setCombiAtLine(combi, line);
		}
	}

	public void setControlPanel(final Component comp) {

		_customComp = comp;

	}

	public void setEssaiCombinaison(final PionCouleur[] combi) {

		for (short i = 0; i < Combinaison.NOMBRE_PION; i++) {
			if (combi[i] == null) {
				combi[i] = PionCouleur.BLANC;
			}
		}

		_currentCombi = combi;

	}

	public void setResAtLine(final int nbBienPlace, final int nbMalPlace, final int line) {

		if (_frame.getMastermindGamePanel() != null) {
			_frame.getMastermindGamePanel().setResAtLine(nbBienPlace, nbMalPlace, line);
		}
	}

}
