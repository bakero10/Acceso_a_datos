package Unidad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio04 {
	/*
	 * Realiza una clase UD_4 que guarde en un fichero con nombre pares.txt los
	 * numeros pares que hay entre 0 y 500 un numero en cada linea del
	 * fichero.Seguidamente lee el fichero y muestralo por consola.Incluye tambien
	 * el tratamiento de excepciones.
	 */
	public static void main(String[] args) throws IOException {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Ficheros/pares.txt"));
			for (int i = 0; i < 500; i++) {
				if(i%2 == 0) {
					bw.write(Integer.toString(i));
					bw.newLine();
				}
			}
			bw.close();
			
			BufferedReader br = new BufferedReader(new FileReader("Ficheros/pares.txt"));
			String cadena;
			while((cadena = br.readLine())!=null) {
				System.out.println(cadena);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
