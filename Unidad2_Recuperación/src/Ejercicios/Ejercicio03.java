package Ejercicios;

import java.io.File;

public class Ejercicio03 {

	public static void main(String[] args) {

		boolean resultado;
		File directorio = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas");
		resultado = directorio.mkdir();
		if(resultado) {
			System.out.println("Directorio Creado");
		}
		else {
			System.out.println("No se pudo crear el directorio");	//POSIBLEMENTE EXISTA
			System.exit(-1);
		}
		try {
			File fichero = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas\\Alberto.txt");
			resultado = fichero.createNewFile();
			if(resultado) {
				System.out.println("Archivo creado");
			}
			else {
				System.out.println("No se pudo crear el archivo");	//POSIBLEMENTE EXISTA
			}
		} catch (Exception e) {
			System.out.println("Se produjo un error: "+e.getMessage());
		}
		
	}

}
