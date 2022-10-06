package Unidad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio09 {
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
	public static void main(String[] args){
	Scanner s = new Scanner(System.in);
			
			System.out.println("Introduce el id del profesor: ");
			int numProf = s.nextInt();
			int posicion = (numProf - 1)*56;
			try {
				File f = new File("Ficheros/ProfesFPSierraGuara.dat");
				RandomAccessFile file = new RandomAccessFile(f,"rw");
				if(posicion >f.length() || numProf == 0) {
					System.out.println("Ese profesor no existe.");
					System.exit(-1); 
				}
				file.seek(posicion);
				if(file.readInt() == 0) {
					System.out.println("Este profesor ya ha sido borrado");
					System.exit(-1);
				}
				file.seek(posicion);
				file.writeInt(0);
				
				int departamento;
				double antiguedad;
				char [] nombre=new char[20];
				file.seek(0);
				System.out.println("Id\tNombre\tDepartamento\tAntiguedad");
				for (int i = 0; i < file.length(); i++) {
					int id = file.readInt();
					for (int j = 0; j < nombre.length; j++) {
						nombre[j]=file.readChar();
					}
					departamento = file.readInt();
					antiguedad = file.readDouble();
					String nombres =  new String(nombre);
					System.out.println(id+"\t"+nombres+"\t"+departamento+"\t"+antiguedad);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	}	

}
