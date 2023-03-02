package Ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio9_repetido {
	/*
	 * Adaptación de los ejemplos vistos en los materiales con la clase RandomAccessFile.
	 * Realiza una clase UD2_9 que pida al usuario el identificador del profesor y lo borre del fichero ProfesFPSierraGuara.dat
	 * Borrar un dato simplemente consiste en poner su campo id dentro del fichero a 0 para indicar que ese registro no existe y su posicion esta libre.
	 * Se debera controlar que:
	 * 				- El identificador del profesor este dentro de los limites del fichero.
	 * 				- El identificador del profesor debe existir.Si ha sido borrado previamente se advertira de la situacion.
	 * 				- Antes de finalizar el codigo visualizar de manera secuencial todos los registros del fichero para comprobar la operacion.
	 * * 			==================================
	 * Id 			  (entero – 4 bytes)*
	 * Nombre (20 caracteres – 40 bytes)*
	 * Departamento    (entero– 4 bytes)*
	 * Antigüedad 		(real – 8 bytes)*
	 *			===================================
	 *					 Total(56 bytes)*
	 * 	
	 */
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner (System.in);
		
		int idProfe;
		System.out.print("Introduce el identificador del profesor que quieras borrar: ");
		idProfe = s.nextInt();
		File f = new File("Ficheros/ProfesFPSierraGuara.dat");
		RandomAccessFile random = new RandomAccessFile(f, "rw");
		if(idProfe < 0 || idProfe > f.length()) {
			System.out.println("El identificador esta fuera de rango");
		}
		else {
			int posicion = (idProfe-1)*56;
			random.seek(posicion);
			if(random.readInt() == 0) {
				System.out.println("Ese profesor ya ha sido borrado");
			}
			else {
				random.seek(posicion);
				random.writeInt(0);
				random.seek(posicion);
				random.writeInt(0);					//con write int ponemos el id a 0 osease borrado
				int departamento;
				double antiguedad;
				char [] nombre=new char[20];
				random.seek(0);
				System.out.println("Id\tNombre\tDepartamento\tAntiguedad");
				for (int i = 0; i < f.length(); i++) {
					int ide = random.readInt();
					for (int j = 0; j < nombre.length; j++) {
						nombre[j]=random.readChar();
					}
					departamento = random.readInt();
					antiguedad = random.readDouble();
					String nombres =  new String(nombre);
					System.out.println(ide+"\t"+nombres+"\t\t"+departamento+"\t"+antiguedad);
				}
			}
		}
		
		
		
		
	}
}
