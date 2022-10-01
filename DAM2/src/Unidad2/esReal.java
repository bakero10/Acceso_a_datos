package Unidad2;
/*
 * Clase que implementa un metodo estatico, para comprobar si un String pasado
 * por parametro es un numero real.
 * @author Jose Miguel Andres Perez
 */
public class esReal {
	public static boolean esNumeroReal(String numero) {
		try {
			Double.parseDouble(numero);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
