package Ejercicios;

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
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner (System.in);
		int posicion;
		int id;
		System.out.println("Introduce el identificador del profesor ");
		id = s.nextInt();
		File file = new File("Ficheros/ProfesFPSierraGuara.dat");
		try {
			RandomAccessFile random = new RandomAccessFile(file, "rw");
			posicion = (id - 1)*56;			//el primero llega hasta 56 por eso se pone asi ya que en total hay 56 bytes
			if(posicion > file.length() || posicion == 0) {
				System.out.println("El profesor con ese identificador no existe");
				System.exit(-1);
			}
			random.seek(posicion);				//con seek no ponemos en la posicion que queremos
			if(random.readInt() == 0) {
				System.out.println("Este profesor ya ha sido borrado");
				System.exit(-1);
			}
			random.seek(posicion);
			random.writeInt(0);					//con write int ponemos el id a 0 osease borrado
			int departamento;
			double antiguedad;
			char [] nombre=new char[20];
			random.seek(0);
			System.out.println("Id\tNombre\tDepartamento\tAntiguedad");
			for (int i = 0; i < file.length(); i++) {
				int ide = random.readInt();
				for (int j = 0; j < nombre.length; j++) {
					nombre[j]=random.readChar();
				}
				departamento = random.readInt();
				antiguedad = random.readDouble();
				String nombres =  new String(nombre);
				System.out.println(id+"\t"+nombres+"\t\t"+departamento+"\t"+antiguedad);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
}
