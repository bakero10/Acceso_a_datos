import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio06 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Realiza una clase que indique cuantas veces aparece una palabra dentro de un fichero de texto(crearlo con block de notas)
		 * Tanto el nombre del fichero como la palabra se deben pasar como argumentos.
		 * No distinguir mayusculas/minusculas.
		 * Incluye tratamiento de excepciones.
		 * Pista --> Metodos para cadenas substring e indexOf.
		 */
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		int contador = 0;
		String linea;
		String palabra = args[1];
		while((linea = br.readLine()) != null)
			if(linea.split(palabra) != null) {
				contador++;
			}
			
		System.out.println(br.readLine());
		br.close();
	}

}
