package mastermind.arz.common;

public enum PionCouleur {

	BLANC("BLANC"), JAUNE("JAUNE"), ORANGE("ORANGE"), ROUGE("ROUGE"), FUSCHIA("FUSCHIA"), VIOLET("VIOLET"), VERT("VERT"), BLEU("BLEU"), GRIS("GRIS"), NOIR(
			"NOIR");

	public static PionCouleur fromInt(final int index) {

		PionCouleur res;
		switch (index) {
		case 0:
			res = BLANC;
			break;
		case 1:
			res = JAUNE;
			break;
		case 2:
			res = ORANGE;
			break;
		case 3:
			res = ROUGE;
			break;
		case 4:
			res = FUSCHIA;
			break;
		case 5:
			res = VIOLET;
			break;
		case 6:
			res = VERT;
			break;
		case 7:
			res = BLEU;
			break;
		case 8:
			res = GRIS;
			break;
		case 9:
			res = NOIR;
			break;
		default:
			res = BLANC;
			break;

		}

		return res;

	}

	private String nom;

	PionCouleur(final String aNom) {
		nom = aNom;
	}

	@Override
	public String toString() {

		return nom;
	}

}
