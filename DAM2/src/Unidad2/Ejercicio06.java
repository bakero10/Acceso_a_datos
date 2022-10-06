package Unidad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Ejercicio06 {
	/*
	 * Realiza una clase UD2_6 que indique cuantas veces aparece una palabra dentro
	 * de un fichero de texto (puedes crearlo con el bloc de notas). Tanto el nombre
	 * del fichero com la palabra se deben pasar como argumentos. No distinguir
	 * mayusculas/minusculas.Incluye tratamiento de excepciones. PISTAS --> Metodos
	 * para cadenas substring e IndexOf
	 */
	public static void main(String[] args) {
		String texto = "";
		// PARTE 1 LEEMOS EL DOCUMENTO
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String cadena;
			while ((cadena = br.readLine()) != null) {
				texto += cadena.toLowerCase();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		// PARTE 2 BUSCAMOS EL ARGUMENTO 2
		int contador = 0;
		while (texto.indexOf(args[1]) != -1) {
			texto = texto.substring(texto.indexOf(args[1]) + (args[1].length()));
			contador++;
		}
		System.out.println("La palabra " + args[1] + " aparece " + contador + " veces.");
	}// main
}// class
