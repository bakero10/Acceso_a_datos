import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio05 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Realiza una clase que sea capaz de organizar alfabeticamente las lineas contenidas de un fichero de texto (puedes crearlo usando el block de notas)
		 * El nombre del fichero que contiene las lineas se debe pasar como argumento.
		 * El nombre del fichero resultado ya ordenado debe ser el mismo que el original añadiendole la coletilla_sort al nombre.
		 * Incluye tambien el tratamiento de excepciones.
		 */
		File f = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea;
		String contador = null;
		int [] palabras;
		while((linea= br.readLine())!=null) {
			
			System.out.println(linea);
			
		}
		br.close();
	}
}
