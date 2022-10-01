package Unidad2;

public class esEntero {

	public static boolean esNumeroEntero(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
