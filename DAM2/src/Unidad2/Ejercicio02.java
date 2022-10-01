package Unidad2;

import java.io.File;

public class Ejercicio02 {
	/*
	 * Realiza una clase UD2_3 que complete la clase EjemploClaseFile02 de los materiales borrando el directorio
	 * y el fichero creados en ella(primero borra el fichero y despues el directorio pues no se permite borrar
	 * directorios no vacios).
	 */
//------------------PARTE 1 DE EJEMPLOCLASEFILE02-----------------------------

//public static void main(String[] args) {
//	boolean resultado;
//	File directorio = new File("C:\\DAM2");
//	resultado = directorio.mkdir();
//	if(resultado)
//		System.out.println("Directorio creado");
//	else {
//		System.out.println("No se pudo crear el directorio");
//		System.exit(-1);
//	}
//	try {
//		File fichero = new File("C:\\DAM2\\Alberto.txt");
//		resultado = fichero.createNewFile();
//		if(resultado)
//			System.out.println("Archivo creado");
//		else {
//			System.out.println("No se pudo crear el archivo");
//		}
//	} catch (IOException e) {
//		System.out.println("Error "+e.getMessage());
//	}
	
	//---------------PARTE 2 DE EJEMPLOCLASEFILE02---------------------------
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
}//main
}//class
