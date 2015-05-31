package mastermind.arz.presentation.frame;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mastermind.arz.presentation.panel.MasterMindEssaiPanel;
import mastermind.arz.presentation.panel.MasterMindGamePanel;

public class MasterMindFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744724604965015732L;

	private final MasterMindGamePanel _mmgp;

	public MasterMindFrame(final Component joueurComp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MasterMind");

		final JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		getContentPane().add(content);

		final JPanel _jeuPanel = new JPanel();
		_jeuPanel.setBorder(new TitledBorder(null, "Jeu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		final GridBagConstraints gbc_jeuPanel = new GridBagConstraints();
		gbc_jeuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jeuPanel.anchor = GridBagConstraints.LINE_START;
		gbc_jeuPanel.gridx = 0;
		gbc_jeuPanel.gridy = 0;
		content.add(_jeuPanel, gbc_jeuPanel);

		final GridBagLayout gbl__jeuPanel = new GridBagLayout();
		gbl__jeuPanel.rowWeights = new double[] { 0.0, 1.0 };
		gbl__jeuPanel.columnWeights = new double[] { 1.0 };
		_jeuPanel.setLayout(gbl__jeuPanel);
		final GridBagConstraints gamePanelCons = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1,
				1, 5, 1), 0, 0);
		_mmgp = new MasterMindGamePanel();
		_jeuPanel.add(_mmgp, gamePanelCons);

		final JPanel essaiPanel = new JPanel();
		essaiPanel.setBorder(new TitledBorder(null, "Essai", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		final GridBagConstraints gbc_essaiPanel = new GridBagConstraints();
		gbc_essaiPanel.fill = GridBagConstraints.BOTH;
		gbc_essaiPanel.gridx = 0;
		gbc_essaiPanel.gridy = 1;
		_jeuPanel.add(essaiPanel, gbc_essaiPanel);
		essaiPanel.setLayout(new GridLayout(1, 0, 0, 0));

		final MasterMindEssaiPanel mmep = new MasterMindEssaiPanel();
		essaiPanel.add(mmep);

		final JPanel _controlPanel = new JPanel();
		_controlPanel.setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		final GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.fill = GridBagConstraints.BOTH;
		gbc_controlPanel.gridx = 1;
		gbc_controlPanel.gridy = 0;
		content.add(_controlPanel, gbc_controlPanel);
		final GridBagLayout gbl__controlPanel = new GridBagLayout();
		gbl__controlPanel.rowWeights = new double[] { 1.0 };
		gbl__controlPanel.columnWeights = new double[] { 1.0 };
		_controlPanel.setLayout(gbl__controlPanel);

		final JPanel controlContentPanel = new JPanel();
		final GridBagConstraints gbc_controlContentPanel = new GridBagConstraints();
		gbc_controlContentPanel.fill = GridBagConstraints.BOTH;
		gbc_controlContentPanel.gridx = 0;
		gbc_controlContentPanel.gridy = 0;
		_controlPanel.add(controlContentPanel, gbc_controlContentPanel);
		final GridBagLayout gbl_controlContentPanel = new GridBagLayout();
		gbl_controlContentPanel.columnWidths = new int[] { 57, 10, 0 };
		gbl_controlContentPanel.rowHeights = new int[] { 23, 0, 0 };
		gbl_controlContentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_controlContentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		controlContentPanel.setLayout(gbl_controlContentPanel);

		if (joueurComp != null) {

			controlContentPanel.add(joueurComp);
		}

		pack();
	}

	public MasterMindGamePanel getMastermindGamePanel() {

		return _mmgp;
	}

}
