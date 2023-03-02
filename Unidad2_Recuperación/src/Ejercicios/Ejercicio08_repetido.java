package Ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio08_repetido {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner s = new Scanner(System.in);
		ArrayList<Profesor> listaProfe = new ArrayList<>();	
		int numProfe;
		System.out.print("Introduce el numero de profesores que quieres introducir: ");
		numProfe = s.nextInt();
		
		for (int i=0; i<numProfe; i++) {
			System.out.print("Introduce el nombre del profesor: ");
			String nombre = s.next();
			System.out.print("Introduce la antiguedad del profesor: ");
			double antiguedad  = s.nextDouble();
			listaProfe.add(new Profesor(nombre,antiguedad));
		}
		
		FileOutputStream fw = new FileOutputStream(new File("Ficheros/antiguedad.dat_obj.dat"));
		ObjectOutputStream objetoSalida = new ObjectOutputStream(fw);
		
		for (Profesor profesor : listaProfe) {
			objetoSalida.writeObject(profesor);
		}
		objetoSalida.close();
		
		//SEGUNDA PARTE LEEMOS LOS OBJETOS DEL FICHERO
		
		FileInputStream entrada = new FileInputStream(new File("Ficheros/antiguedad.dat_obj.dat"));
		ObjectInputStream entradaDatos = new ObjectInputStream(entrada);
		try {
			while(true) {
				System.out.println(entradaDatos.readObject());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
