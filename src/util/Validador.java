package util;

public interface Validador {
	int MAX = 30;
	int MIN = 1;
	public default boolean validaPeso (Double valor) {
		if (valor >= MIN && valor <= MAX) {
			return true;
		} else {
			return false;
		}
	}

}
