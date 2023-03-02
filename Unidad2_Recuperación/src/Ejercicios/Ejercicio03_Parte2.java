package Ejercicios;

import java.io.File;

public class Ejercicio03_Parte2 {
	public static void main(String[] args) {
		boolean resultado;
		File fichero = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas\\Alberto.txt");
		resultado = fichero.delete();
		
		if (resultado) {
			System.out.println("Fichero borrado con exito");
		} else {
			System.out.println("No se pudo borrar el fichero"); // POSIBLEMENTE NO EXISTA
		}
		
		File directorio1 = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas");
		resultado = directorio1.delete();
		if (resultado) {
			System.out.println("Directorio borrado con exito");
		} else {
			System.out.println("No se pudo borrar el directorio"); // POSIBLEMENTE NO EXISTA
		}
	}
}
