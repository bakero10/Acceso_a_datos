package Unidad2;

import java.io.File;

public class Ejercicio03 {
	/*
	 * Realiza una clase UD2_3 que complete la clase EjemploClaseFile02 de los materiales borrando el directorio
	 * y el fichero creados en ella(primero borra el fichero y despues el directorio pues no se permite borrar
	 * directorios no vacios).
	 */
		
			public static void main(String[] args) {
				
				File fichero = new File("C:\\DAM2\\Alberto.txt");
				if(fichero.exists()) {
					fichero.delete();
					System.out.println("Archivo borrado.");
				}
				File directorio = new File("C:\\DAM2");
				if(directorio.exists()) {
					directorio.delete();
					System.out.println("Directorio borrado.");
				}
	}

}
