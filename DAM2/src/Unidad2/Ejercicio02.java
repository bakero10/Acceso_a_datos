package Unidad2;

import java.io.File;

public class Ejercicio02 {
	/*
	 * Realiza una clase UD2_2 cuyo metodo principal reciba como argumento una
	 * cadena con la trayectoria de un directorio o fichero e indique si existe
	 * realmente o no dicho directorio o fichero. Si el metodo principal no recibe
	 * ningun argumento se indicara por pantalla y finalizara su ejecucion.
	 */

	public static void main(String[] args)  {
		
		if(args.length == 0) {
			System.out.println("No se ha pasado ningun argumento.");
			System.exit(-1);
		}
		else {
			File f = new File(args[0]);
			if(f.exists()) {
				if(f.isDirectory()) {
					System.out.println("Es un directorio.");
				}
				if(f.isFile()) {
					System.out.println("Es un archivo.");
				}
			}
			else {
				System.out.println("El archivo o directorio no existe.");
			}
		}
	}
}
