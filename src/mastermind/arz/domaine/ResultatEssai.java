package mastermind.arz.domaine;

import java.security.InvalidParameterException;

/**
 * Classe représentant un résultat donné par le croupier lorsque que le joueur
 * tente une combinaison. Cette classe est composé de 2 entier chacun compris
 * entre 0 et la taille d'un combinaison ( 4 ou 5 ici). Ainsi pour des raisons
 * d'optimisation mémoire, les 2 entiers sont stocké dans un seul octet. L'un
 * sur les 4 premiers bit, l'autre sur les 4 derniers.
 * 
 * Les entiers ne doivent donc pas dépasser 15.
 * 
 */
public class ResultatEssai {

	/**
	 * Octet dans lequel est stocké les 2 entiers.
	 */
	private final byte _place;

	/**
	 * Index unique du résultat
	 */
	private byte _index;

	/**
	 * Constructeur
	 * 
	 * @param bienPlace
	 *            nombre de pions bien placé dans la combinaison
	 * @param malPlace
	 *            nombre de pions mal placés dans la combinaison
	 */
	public ResultatEssai(final int bienPlace, final int malPlace) {

		// controle des entrées
		if (bienPlace < 0 || bienPlace > 15 || malPlace < 0 || malPlace > 15) {
			throw new InvalidParameterException("new ResultatEssai - Les paramètres ne doivent pas dépasser 15");
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

		// on regarde si les entiers sont les mêmes.
		if (obj instanceof ResultatEssai) {
			res = ((ResultatEssai) obj)._place == _place;
		}

		return res;
	}

	public int getIndex() {
		return _index;

	}

	/**
	 * Retourne le nombre de pions bien placés.
	 * 
	 * @return le nombre de pions bien placés
	 */
	public final byte getNbrBienPlace() {
		return (byte) ((_place & 0xF0) >> 4);
	}

	/**
	 * Retourne le nombre de pions mal placés.
	 * 
	 * @return le nombre de pions mal placés.
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
