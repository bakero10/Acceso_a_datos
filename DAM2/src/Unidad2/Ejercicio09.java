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
	 */
	public static void main(String[] args){
		Scanner s = new Scanner (System.in);
			try {
				File f = new File ("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\ProfesFPSierraGuara.dat");
				RandomAccessFile file = new RandomAccessFile(f, "rw");
					int id = s.nextInt();
					int posicion = (id - 1)*56;
					
					// PARTE 1 EL IDENTIFICADOR ESTA DENTRO DE LOS LIMITES DEL FICHERO
					if((posicion > f.length() -56) || (id<1)) {
						System.err.println("No existe ningun profesor con ese ID");
						System.exit(-1);
					}
					
					//PARTE 2 SI EL IDENTIFIADOR ES 0 ADVERTIMOS DE LA SITUACION
					file.seek(posicion); // le decimos que ponga el puntero en la posicion
					if(file.readInt() == 0) { // si el puntero esta en la posicion 0....
						System.err.println("El profesor con ese ID ya a sido borrado.");
					}
					else {
						file.seek(file.getFilePointer()-1);//retrocedemos una posicion para situarnos en el id de nuevo ya que al leerlo hemos avanzado el puntero
						file.writeInt(0); //Ponemos el identificador a 0 ,con esto le decimos que esta borrado
					}
					// PARTE 3 VISUALIZAMOS TODOS LOS FICHEROS
					int dept;
					double antiguedad;
					char profes[] = new char[20];
					file.seek(0);
					System.out.println("ID\tNombre\t\tDepartamento\tAntiguedad");
					for(int i=0; i<file.length()/56; i++) {
						id=file.readInt();
						for(int j=0; j<profes.length; j++) {//recojo los 20 caracteres en un array
							profes[j]=file.readChar();
						}
						dept=file.readInt();
						antiguedad=file.readDouble();
						String Profe = new String (profes);//Paso el array a String para imprimirlo
						System.out.println(id+"\t"+Profe+"\t"+dept+"\t"+antiguedad);
					}
			} catch (FileNotFoundException e) {
				System.err.println("no se encuentra el archivo");
			} catch (IOException e) {
				System.err.println("ERROR IOException");
			}
	}	

}
