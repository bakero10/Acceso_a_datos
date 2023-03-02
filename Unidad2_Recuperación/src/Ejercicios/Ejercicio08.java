package Ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio08 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner s = new Scanner(System.in);
		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\antiguedad.dat_obj.dat");
		ObjectOutputStream objetoSalida = new ObjectOutputStream(new FileOutputStream(f));
		String nombre="";
		double antiguedad;
		int numeroProf;
		
		System.out.println("Introduce el numero de profesores a inscribir: ");
		numeroProf = s.nextInt();
		
		String [] nombres = new String [numeroProf];
		double [] antiguedades = new double [numeroProf];
		
		for (int i=0; i<numeroProf; i++) {
			System.out.println("Introduce el nombre del profesor: ");
			nombre = s.next();
			System.out.println("Introduce la antiguedad: ");
			antiguedad = s.nextDouble();
			Profesor p = new Profesor(nombre,antiguedad);
			objetoSalida.writeObject(p);			
		}
		System.out.println("Objeto introducido");
		objetoSalida.close();
		
		//SEGUNDA PARTE RECUPERAMOS LOS DATOS DEL FICHERO	
		
		ObjectInputStream objetoEntrada = new ObjectInputStream(new FileInputStream(f));
			try {
				while(true) {
				System.out.println(objetoEntrada.readObject());
				}
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Final de Fichero ");
			}
			objetoEntrada.close();
		
		
	}

}
