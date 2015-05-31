package mastermind.arz.domaine;

import mastermind.arz.common.PionCouleur;

public class Pion {

	public static int NB_COULEUR_MAX = 8;

	private PionCouleur couleur;

	private byte place;

	public Pion(final PionCouleur aCouleur) {

		couleur = aCouleur;
		place = -1;
	}

	@Override
	public boolean equals(final Object obj) {

		boolean res = false;

		if (obj instanceof Pion) {

			res = ((Pion) obj).place == place;
			res = res && ((Pion) obj).couleur.equals(couleur);

		}

		return res;
	}

	public PionCouleur getCouleur() {
		return couleur;
	}

	public int getPlace() {
		return place;
	}

	public void setCouleur(final PionCouleur couleur) {
		this.couleur = couleur;
	}

	public void setPlace(final int place) {
		this.place = (byte) place;
	}

}
