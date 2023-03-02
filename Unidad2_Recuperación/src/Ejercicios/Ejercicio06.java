package Ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio06 {

	public static void main(String[] args) throws FileNotFoundException {

			int contador = 0;
			BufferedReader entrada = new BufferedReader(new FileReader(args[0]));
			String linealeida;
			String [] trozos;
			try {
				while((linealeida = entrada.readLine())!=null) {
					trozos = linealeida.split("\\s+");			// Divide la línea en palabras, las separa por el espacio
					for (String string : trozos) {
						if(string.indexOf(args[1]) != -1) {
							contador++;
						}
					}
				}
				entrada.close();
				System.out.println("El numero de veces que aparece la palabra --> "+args[1]+" <-- es de "+ contador);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
