package Unidad2;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;

public class Ejercicio12repetido {

	/**
	 * Realiza una clase UD2_12 con un m�todo principal que reciba dos argumentos,
	 * un directorio y una extensi�n de fichero, e indique los datos (nombre, tama�o
	 * y fecha de creaci�n) en caso contrario se indicar� y finalizar� su ejecuci�n.
	 * 
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("No se ha introducido ningun argumento");
			System.exit(0);
		} else {
			File directorio = new File(args[0]);
			File[] lista = directorio.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File f, String name) {
					if (name.endsWith(args[1])) {
						return true;
					}
					return false;
				}
			});
			for (File fichero : lista){
				System.out.println("Nombre: "+fichero.getName()+ ".");
				System.out.println("Tama�o: "+fichero.length() + " Bytes.");
				String pattern = "dd-mm-yyyy HH-mm-ss";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				System.out.println("Creaci�n: ");
				
			}
		}
	}// main
}// class
