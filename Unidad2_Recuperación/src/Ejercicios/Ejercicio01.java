package Ejercicios;

import java.io.File;

public class Ejercicio01 {

	public static void main(String[] args) {

		File f = new File("c:\\WINDOWS");
		File[] arrayArchivos = f.listFiles();

		for (File file : arrayArchivos) {
			if (file.isHidden()) {
				System.out.println("Nombre: " + file.getName());
				System.out.println("Longitud: " + file.length());
				System.out.println("Se puede leer? " + file.canRead());
				System.out.println("Se puede escribir? " + file.canWrite());
				System.out.println("-----------------------------------");
			}
		}
	}
}
