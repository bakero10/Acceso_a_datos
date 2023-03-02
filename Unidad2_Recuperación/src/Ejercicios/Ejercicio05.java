package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio05 {

	public static void main(String[] args) throws FileNotFoundException{

			ArrayList<String> lista = new ArrayList<>();
			FileReader fr = new FileReader(args[0]+".txt");
			BufferedReader entrada = new BufferedReader(fr);
			String linea;
			try {
				while((linea = entrada.readLine())!=null) {
					lista.add(linea);
				}
				Collections.sort(lista);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				BufferedWriter salida = new BufferedWriter(new FileWriter(args[0]+"_sort.txt"));
				for (String string : lista) {
					salida.write(string);
					salida.newLine();
				}
				salida.close();
				System.out.println("Escritura realizada correctamente");
			} catch (IOException e) {
				System.out.println("Error de escritura "+e.getMessage());
			}
		
	}

}
