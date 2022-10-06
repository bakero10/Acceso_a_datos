package Unidad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio05 {
	/*
	 * Realiza una clase UD2_5 que sea capaz de ordenar alfabeticamente las lineas contenidas en un fichero de
	 * texto(puedes crearlo con el bloc de notas).
	 * El nombre del fichero que contiene las lineas se debe pasar como argumento.
	 * El nombre del fichero resultado ya ordenado debe ser el mismo que el original añadiendole
	 * la coletilla_sort al nombre.Incluye tambien tratamiento de excepciones.
	 */
	public static void main(String[] args) {
			
			//LEEMOS EL ARCHIVO
			ArrayList<String> lista = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(args[0]+".txt"));
				String cadena;
				while((cadena = br.readLine())!= null) {
					lista.add(cadena);
				}
				Collections.sort(lista);
				br.close();
			} catch (IOException e) {
				System.out.println("Error "+e.getMessage());
			}
			//ESCRIBIMOS EL NUEVO ARCHIVO
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(args[0]+"_sort.txt"));
				for(String string : lista) {
					bw.write(string);
					bw.newLine();
					System.out.println(string);
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("Error "+e.getMessage());
			}
	}//MAIN

}
