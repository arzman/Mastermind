package mastermind.arz.domaine;

import java.util.ArrayList;

import mastermind.arz.common.PionCouleur;

/**
 * Cette classe représente une combinaison de pion. Elle est constituée de
 * NOMBRE_PION pions.
 * 
 */
public final class Combinaison {

	public static int NOMBRE_PION = 4;

	public static Combinaison fromInt(final int hashcode) {

		final Combinaison res = new Combinaison();

		int mult = (int) Math.pow(Pion.NB_COULEUR_MAX, NOMBRE_PION - 1);
		int restTmp = hashcode;
		for (int i = 0; i < NOMBRE_PION; i++) {

			final PionCouleur pc = PionCouleur.fromInt(restTmp / mult);
			restTmp = restTmp % mult;
			mult = mult / Pion.NB_COULEUR_MAX;

			final Pion p = new Pion(pc);
			res.ajouterPion(p, i);
		}

		return res;
	}

	private final Pion[] _lesPions;

	private final ArrayList<PionCouleur> _couleursInCombi;

	private int _hash = -1;

	private byte[] _nbrCouleur;

	public Combinaison() {

		_lesPions = new Pion[NOMBRE_PION];
		_couleursInCombi = new ArrayList<PionCouleur>(NOMBRE_PION);
		_nbrCouleur = new byte[Pion.NB_COULEUR_MAX];

		for (int i = 0; i < Pion.NB_COULEUR_MAX; i++) {
			_nbrCouleur[i] = -1;
		}

	}

	public void ajouterPion(final Pion pion) {
		ajouterPion(pion, -1);

	}

	public final boolean ajouterPion(final Pion aPion, final int place) {

		boolean res = true;

		if (place > NOMBRE_PION - 1 || place < -1) {
			res = false;
		} else {

			if (place == -1) {

				boolean found = false;
				for (byte i = 0; i < _lesPions.length && !found; i++) {
					if (_lesPions[i] == null) {
						_lesPions[i] = aPion;
						aPion.setPlace(i);
						found = true;
					}
				}
				if (!found) {
					res = false;
				}

			} else {
				_lesPions[place] = aPion;
				aPion.setPlace(place);
			}

		}

		if (res) {
			if (!_couleursInCombi.contains(aPion.getCouleur())) {
				_couleursInCombi.add(aPion.getCouleur());
			}
		}

		return res;
	}

	public final Combinaison cloneIt() {

		final Combinaison res = new Combinaison();

		for (int i = 0; i < _lesPions.length; i++) {
			res.ajouterPion(new Pion(getCouleurAt(i)));
		}

		return res;
	}

	@Override
	public boolean equals(final Object obj) {

		boolean res = false;

		if (obj instanceof Combinaison) {

			res = ((Combinaison) obj).getCouleurAt(0).equals(getCouleurAt(0));
			for (int b = 1; b < NOMBRE_PION; b++) {
				res = res && ((Combinaison) obj).getCouleurAt(b).equals(getCouleurAt(b));

			}

		}

		return res;
	}

	public PionCouleur getCouleurAt(final int i) {

		return _lesPions[i].getCouleur();
	}

	public ArrayList<PionCouleur> getCouleurs() {

		return _couleursInCombi;
	}

	public byte getNbrColor(final PionCouleur color) {

		if (_nbrCouleur[color.ordinal()] == -1) {

			byte res = 0;

			for (int i = 0; i < NOMBRE_PION; i++) {

				if (getCouleurAt(i).equals(color)) {
					res++;
				}
			}
			_nbrCouleur[color.ordinal()]=res;
		}

		return _nbrCouleur[color.ordinal()];
	}

	public int getNumericValue() {

		return 1000 * getCouleurAt(0).ordinal() + 100 * getCouleurAt(1).ordinal() + 10 * getCouleurAt(2).ordinal() + 1 * getCouleurAt(0).ordinal();

	}

	@Override
	public int hashCode() {

		if (_hash == -1) {

			int res = getCouleurAt(0).ordinal();
			for (int b = 1; b < NOMBRE_PION; b++) {
				res = res * Pion.NB_COULEUR_MAX + getCouleurAt(b).ordinal();
			}

			_hash = res;
		}

		return _hash;
	}

	public int indexOf(final PionCouleur coul) {

		int res = -1;
		boolean finish = false;

		for (int i = 0; i < NOMBRE_PION && !finish; i++) {

			if (getCouleurAt(i).equals(coul)) {
				res = i;
				finish = true;
			}
		}

		return res;
	}

	public boolean isComplete() {

		boolean res = true;

		for (int i = 0; i < _lesPions.length; i++) {
			res = res && (_lesPions[i] != null);
		}

		return res;

	}

	public byte nbreBienPlaceForColor(final PionCouleur color, final Combinaison combiEssai) {

		byte res = 0;

		for (int i = 0; i < NOMBRE_PION; i++) {

			if (getCouleurAt(i).equals(color) && combiEssai.getCouleurAt(i).equals(color)) {
				res++;
			}
		}

		return res;
	}

	public PionCouleur[] toArray() {

		final PionCouleur[] res = new PionCouleur[NOMBRE_PION];

		for (int i = 0; i < res.length; i++) {
			res[i] = getCouleurAt(i);
		}

		return res;
	}

	@Override
	public String toString() {

		String str = "";
		for (int i = 0; i < NOMBRE_PION; i++) {
			str = str + getCouleurAt(i) + " ";
		}

		return str;
	}

}
