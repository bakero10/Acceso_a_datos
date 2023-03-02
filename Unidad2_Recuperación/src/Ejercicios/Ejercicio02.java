package Ejercicios;

import java.io.File;

public class Ejercicio02 {

	public static void main(String[] args) {
		
		if(args.length <= 0) {
			System.out.println("No se ha pasado ningun argumento");
		}
		else {
			File f = new File(args[0]);
			if(f.exists()) {
				System.out.println("El fichero o directorio ¡Existe!");
			}
			else {
				System.out.println("El fichero o directorio ¡No existe!");
			}
		}
	}

}
