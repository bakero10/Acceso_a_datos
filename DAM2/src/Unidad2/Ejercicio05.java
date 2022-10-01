package Unidad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio05 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Con este metodo leemos el fichero
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\"+args[0]+".txt"));
		String linea;
		ArrayList<String> lista = new ArrayList<String>();
		while((linea = br.readLine())!= null) {
			lista.add(linea);
		}
			br.close();
			Collections.sort(lista);
		//Con este metodo vamos a crear el fichero y vamos a escribirlo
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\"+args[0]+"_sort.txt"));
		for(String string : lista) {
			bw.write(string);
			bw.newLine();
		}
			bw.close();
	}

}
