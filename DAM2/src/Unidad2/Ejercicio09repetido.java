package Unidad2;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio09repetido {
	/*
	 * Adaptación de los ejemplos vistos en los materiales con la clase RandomAccessFile.
	 * Realiza una clase UD2_9 que pida al usuario el identificador del profesor y lo borre del fichero ProfesFPSierraGuara.dat
	 * Borrar un dato simplemente consiste en poner su campo id dentro del fichero a 0 para indicar que ese registro no existe y su posicion esta libre.
	 * Se debera controlar que:
	 * 				- El identificador del profesor este dentro de los limites del fichero.
	 * 				- El identificador del profesor debe existir.Si ha sido borrado previamente se advertira de la situacion.
	 * 				- Antes de finalizar el codigo visualizar de manera secuencial todos los registros del fichero para comprobar la operacion.
	 * 			==================================
	 * Id 			  (entero – 4 bytes)*
	 * Nombre (20 caracteres – 40 bytes)*
	 * Departamento    (entero– 4 bytes)*
	 * Antigüedad 		(real – 8 bytes)*
	 *			===================================
	 *					 Total(56 bytes)*
	 * 	
	 */
			public static void main(String[] args) {
				Scanner s = new Scanner(System.in);
				//1ERA PARTE ID PROFESOR
				System.out.print("Introduce el id del profesor: ");
				int id = s.nextInt();
				int posicion = (id - 1)*56;
					//SEGUNDA PARTE IDENTIFICAMOS SI ESTA DENTRO DE LOS LIMITES DEL FICHERO
					try {
						File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\ProfesFPSierraGuara.dat");
						RandomAccessFile file = new RandomAccessFile(f,"rw");
						if(posicion>f.length()) {
							System.out.println("No existe ningun profesor con ese id!");
							System.exit(-1);
						}
					//PARTE TRES SI EL IDENTIFICADOR ES O EL PROFESOR YA HA SIDO BORRADO
						file.seek(posicion);
						if(file.readInt() == 0) {
							System.out.println("Este profesor ya ha sido borrado.");
							System.exit(-1);
						}
						else {
							file.seek(file.getFilePointer()-1);
							file.writeInt(0);
						}
						//PARTE 4 VISUALIZAMOS EL FICHERO
						int departamento;
						double antiguedad;
						char [] nombres = new char[20];
						file.seek(0);//IMPORTANTE IMPRIMIR DESDE 0
						System.out.println("Id\tNombre\tDepartamento\tAntiguedad");
						for(int i=0; i<file.length();i++) {
							int ide = file.readInt();
							for(int j=0; j<nombres.length;j++) {
								nombres[j]=file.readChar();
							}
							departamento=file.readInt();
							antiguedad = file.readDouble();
							String nombre = new String(nombres);
							System.out.println(ide +"\t"+nombre +"\t"+departamento +"\t"+antiguedad);
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					} 
					
							
	}

}
