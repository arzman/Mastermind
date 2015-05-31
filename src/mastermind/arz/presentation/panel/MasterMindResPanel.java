package mastermind.arz.presentation.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import mastermind.arz.domaine.Combinaison;

public class MasterMindResPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7567985618198364329L;
	private int _nbBienPlace;
	private int _nbMalPlace;

	public MasterMindResPanel() {

		setSize(14 * Combinaison.NOMBRE_PION, 50);
		setPreferredSize(getSize());

	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		boolean finish = _nbBienPlace + _nbMalPlace == 0;

		int bpTmp = _nbBienPlace;
		int mpTmp = _nbMalPlace;

		final Integer[] xc2 = new Integer[Combinaison.NOMBRE_PION];
		final Integer[] yc2 = new Integer[xc2.length];

		for (int i = 0; i < xc2.length; i++) {

			if (i < xc2.length / 2) {

				yc2[i] = getHeight() / 4;

			} else {

				yc2[i] = 3 * getHeight() / 4;
			}

			int div;

			if (Combinaison.NOMBRE_PION % 2 == 0) {
				div = Combinaison.NOMBRE_PION / 2;
			} else {
				div = Combinaison.NOMBRE_PION / 2 + 1;
			}

			xc2[i] = ((i % div) + 1) * getWidth() / Combinaison.NOMBRE_PION;

		}

		int index = 0;
		while (!finish) {

			final int xC = xc2[index];
			final int yC = yc2[index];

			if (bpTmp > 0) {
				g2d.setColor(Color.GREEN);
				g2d.fillOval(xC, yC, 10, 10);
				index++;
				bpTmp--;

			} else {

				if (mpTmp > 0) {
					g2d.setColor(Color.RED);
					g2d.fillOval(xC, yC, 10, 10);
					index++;
					mpTmp--;

				}

			}

			finish = bpTmp + mpTmp == 0;
		}

		for (int i = index; i < Combinaison.NOMBRE_PION; i++) {
			final int xC = xc2[i];
			final int yC = yc2[i];
			g2d.setColor(Color.BLACK);
			final BasicStroke bs = new BasicStroke(1);
			g2d.setStroke(bs);
			g2d.drawOval(xC, yC, 8, 8);
		}

	}

	public void reinit() {

		setResult(0, 0);

	}

	public void setResult(final int nbBienPlace, final int nbMalPlace) {

		_nbBienPlace = nbBienPlace;
		_nbMalPlace = nbMalPlace;

		repaint();
	}

}
