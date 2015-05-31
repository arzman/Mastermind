package mastermind.arz.domaine;

import java.security.InvalidParameterException;

/**
 * Classe repr�sentant un r�sultat donn� par le croupier lorsque que le joueur
 * tente une combinaison. Cette classe est compos� de 2 entier chacun compris
 * entre 0 et la taille d'un combinaison ( 4 ou 5 ici). Ainsi pour des raisons
 * d'optimisation m�moire, les 2 entiers sont stock� dans un seul octet. L'un
 * sur les 4 premiers bit, l'autre sur les 4 derniers.
 * 
 * Les entiers ne doivent donc pas d�passer 15.
 * 
 */
public class ResultatEssai {

	/**
	 * Octet dans lequel est stock� les 2 entiers.
	 */
	private final byte _place;

	/**
	 * Index unique du r�sultat
	 */
	private byte _index;

	/**
	 * Constructeur
	 * 
	 * @param bienPlace
	 *            nombre de pions bien plac� dans la combinaison
	 * @param malPlace
	 *            nombre de pions mal plac�s dans la combinaison
	 */
	public ResultatEssai(final int bienPlace, final int malPlace) {

		// controle des entr�es
		if (bienPlace < 0 || bienPlace > 15 || malPlace < 0 || malPlace > 15) {
			throw new InvalidParameterException("new ResultatEssai - Les param�tres ne doivent pas d�passer 15");
		}

		// encodage des entiers.
		_place = (byte) ((bienPlace << 4) | malPlace);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {

		boolean res = false;

		// on regarde si les entiers sont les m�mes.
		if (obj instanceof ResultatEssai) {
			res = ((ResultatEssai) obj)._place == _place;
		}

		return res;
	}

	public int getIndex() {
		return _index;

	}

	/**
	 * Retourne le nombre de pions bien plac�s.
	 * 
	 * @return le nombre de pions bien plac�s
	 */
	public final byte getNbrBienPlace() {
		return (byte) ((_place & 0xF0) >> 4);
	}

	/**
	 * Retourne le nombre de pions mal plac�s.
	 * 
	 * @return le nombre de pions mal plac�s.
	 */
	public final byte getNbrMalPlace() {
		return (byte) (_place & 0xF);
	}

	public void setIndex(final byte index) {
		_index = index;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return getNbrBienPlace() + "/" + getNbrMalPlace();
	}

}
