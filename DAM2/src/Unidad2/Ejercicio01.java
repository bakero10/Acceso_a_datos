package Unidad2;

import java.io.File;

public class Ejercicio01 {
	/*
	 * Realiza una clase UD2_1 que muestre nombre,longitud,si se puede leer,si se puede escribir en todos
	 * los archivos ocultos de la carpeta Windows de tu disco; solo de los que se encuentran en la carpeta 
	 * principal de Windows, no en sus subcarpetas. 
	 */
	public static void main(String[] args) {
		
		File f = new File("C:\\WINDOWS");
		File [] archivos = f.listFiles();
		
		for(File archivo : archivos) {
			if(archivo.isHidden()) {
				System.out.println("Nombre: "+archivo.getName());
				System.out.println("La longitud es: "+archivo.length());
				System.out.println("¿Se puede leer? "+archivo.canRead());
				System.out.println("¿Se puede escribir? "+archivo.canWrite()+"\n");
			}
		}

	}

}
