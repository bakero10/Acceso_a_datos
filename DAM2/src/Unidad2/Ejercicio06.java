package Unidad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Ejercicio06 {

	public static void main(String[] args) {
		String cadena = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String linea = "";
			while ((linea = br.readLine()) != null) {
				cadena += linea.toLowerCase();
			}
			br.close();

		} catch (IOException eo) {
			System.err.println("Se ha producido un error de lectura");
		}

		int cont = 0;
		while (cadena.indexOf(args[1]) != -1) {
			cadena = cadena.substring(cadena.indexOf(args[1]) + (args[1].length()));
			cont++;
			System.out.println(cadena);
		}

		System.out.println("La palabra \""+args[1]+"\" se repite " + cont + " veces");
	}

}
