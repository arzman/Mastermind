package mastermind.arz.presentation.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import mastermind.arz.common.PionCouleur;
import mastermind.arz.domaine.Pion;

public class MasterMindHolePanel extends JPanel {

	private static final long serialVersionUID = 8626274284919544346L;

	private PionCouleur color = null;

	private Color trueColor = null;

	private boolean editMode = false;

	private boolean _isHidden = false;

	private static final Color C_FUSCHIA = new Color(255, 0, 255);

	private static final Color C_VIOLET = new Color(127, 0, 255);

	private static final Color C_ORANGE = new Color(255, 127, 0);

	private static final Color C_ROUGE = new Color(255, 0, 0);

	private static final Color C_VERT = new Color(0, 139, 0);

	private static final Color C_JAUNE = new Color(238, 238, 0);

	private static final Color C_BLUE = new Color(28, 134, 238);

	public MasterMindHolePanel(final boolean anEditeMode) {

		setSize(50, 50);
		setPreferredSize(getSize());
		editMode = anEditeMode;

		if (editMode) {
			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(final MouseEvent e) {

					super.mouseClicked(e);

					int index = color.ordinal();

					if (e.getButton() == 1) {
						index = (index + 1) % Pion.NB_COULEUR_MAX;
					} else {
						index = index - 1;

						if (index < 0) {
							index = index + Pion.NB_COULEUR_MAX;
						}
						index = index % Pion.NB_COULEUR_MAX;
					}

					setPionColor(PionCouleur.fromInt(index));
				}

			});
		}
	}

	public PionCouleur getPionCouleur() {
		return color;
	}

	public boolean isHidden() {
		return _isHidden;
	}

	@Override
	public void paint(final Graphics g) {

		super.paint(g);

		if (!_isHidden) {
			final Graphics2D g2d = (Graphics2D) g;

			final int x1 = getWidth() / 4;
			final int y1 = getHeight() / 4;

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (trueColor != null) {
				g2d.setColor(trueColor);
				g2d.fillOval(x1 + 1, y1 + 1, getWidth() / 2, getHeight() / 2);
			}

			final BasicStroke bs = new BasicStroke(3);
			g2d.setStroke(bs);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(x1, y1, getWidth() / 2, getHeight() / 2);

		}

	}

	public void reinit() {

		trueColor = null;
		color = null;
		repaint();

	}

	public void setHidden(final boolean b) {

		_isHidden = b;
		repaint();

	}

	public void setPionColor(final PionCouleur aColor) {

		color = aColor;

		switch (aColor) {
		case BLANC:
			trueColor = Color.WHITE;
			break;
		case FUSCHIA:
			trueColor = C_FUSCHIA;
			break;
		case JAUNE:
			trueColor = C_JAUNE;
			break;
		case BLEU:
			trueColor = C_BLUE;
			break;
		case ROUGE:
			trueColor = C_ROUGE;
			break;
		case VERT:
			trueColor = C_VERT;
			break;
		case ORANGE:
			trueColor = C_ORANGE;
			break;
		case VIOLET:
			trueColor = C_VIOLET;
			break;
		case GRIS:
			trueColor = Color.DARK_GRAY;
			break;
		case NOIR:
			trueColor = Color.BLACK;
			break;

		default:
			trueColor = null;
			break;

		}

		repaint();

	}

}
