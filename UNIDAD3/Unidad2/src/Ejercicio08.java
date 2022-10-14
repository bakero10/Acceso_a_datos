import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio08 {
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		/*
		 * Adaptacion de los ejemplos vistos en los materiales con las clases OBjectInputStream y ObjectOutputStream.
		 * Realiza una clase UD2_8 que pida al usuario datos de varios profesores (nombre y la antiguedad) y los inserte en el fichero antiguedad.dat_obj.dat.
		 * Si el fichero no existe se creara con los nuevos datos introducidos, en caso contrario se añadiran por el final.
		 * Antes de finalizar el codigo se recorrera el fichero para visualizar su contenido.
		 * Prueba varias veces la ejecucion de la clase.
		 */
		int cantidad = 0;
		ArrayList<String> nombres = new ArrayList();
		ArrayList<Integer> edades = new ArrayList<Integer>();
		File f = new File("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\antiguedad.dat_obj.dat");
		FileOutputStream fos = new FileOutputStream(f,true);
		MiObjectOutputStream moos = new MiObjectOutputStream(fos);
		System.out.print("¿Cuantos profesores quieres introducir? ");
		cantidad = s.nextInt();
		String nombre;
		int edad;
			for(int i=0; i<cantidad; i++) {
				System.out.print("Nombre del profesor: ");
				nombre = s.next();
				nombres.add(nombre);
				System.out.print("Edad profesor: ");
				edad = s.nextInt();
				edades.add(edad);
				moos.writeObject(new Profesor(nombre,edad));
				}
			moos.close();
			
	}
}
