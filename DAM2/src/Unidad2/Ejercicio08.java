package Unidad2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Ejercicio08 {
	
	/*
	 * UD2.8 Adaptación de los ejemplos vistos en los materiales con las clases ObjectInputStream y
	ObjectOuputStream. Realiza una clase UD2_8 que pida al usuario datos de varios profesores
	(nombre y la antigüedad) y los inserte en el fichero antiguedad.dat_obj.dat. Si el fichero no
	existe se creará con los nuevos datos introducidos, en caso contrario se añadirán por el final.
	Antes de finalizar el código se recorrerá el fichero para visualizar su contenido. Prueba varias
	veces la ejecución de la clase.
	 */
	public static class Profesor implements Serializable{
		private String nombre;
		private double antiguedad;
		public Profesor(String nombre, double antiguedad) {
			super();
			this.nombre = nombre;
			this.antiguedad = antiguedad;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public double getAntiguedad() {
			return antiguedad;
		}
		public void setAntiguedad(double antiguedad) {
			this.antiguedad = antiguedad;
		}
		@Override
		public String toString() {
			return "Profesor Nombre= " + nombre + " || Antiguedad= " + antiguedad;
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner s = new Scanner(System.in);
		//PARTE 1 INTRODUCCION DE DATOS ---------------------------------------------------
		System.out.print("¿Cuantos profesores quieres introducir?");
		String num = s.next();
			while(!esEntero.esNumeroEntero(num)) {
				System.out.println("Porfavor introduce un numero.");
				num = s.next();
			}
			int nume = Integer.parseInt(num);
			String [] nombres = new String [nume];
			double [] antiguedades = new double [nume];
				for(int i=0; i<nume; i++) {
					System.out.print("Nombre: ");
					nombres[i] = s.next();
					System.out.print("Antiguedad: ");
					String antiguedad = s.next();
						while(!esReal.esNumeroReal(antiguedad)) {
							System.out.print("Porfavor introduce un numero.");
							antiguedad=s.next();
							}
						antiguedades[i] = Double.parseDouble(antiguedad);
					}
			//PARTE DOS ESCRIBIMOS LOS DATOS EN EL FICHERO --------------------------------------------------------
			File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\antiguedad.dat_obj.dat");
			try {
				if(f.exists()) {
					miObjectOutputStream moos = new miObjectOutputStream(new FileOutputStream(f,true));
						for(int  i=0; i<nume;i++) {
					moos.writeObject(new Profesor(nombres[i],antiguedades[i]));
						}
						moos.close();
				}
				else {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f,true));
						for(int i=0; i<nume; i++) {
							oos.writeObject(new Profesor(nombres[i],antiguedades[i]));
						}
						oos.close();
				}
			} catch (IOException e) {
				System.out.println("Error en mos,oos "+e.getMessage());
			}
			
			//PARTE TRES LEEMOS EL FICHERO GENERADO -----------------------------------------------------------------
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
					try {
						while(true) {
							System.out.println(ois.readObject());
						}
					} catch (Exception e) {
					}
			} catch (IOException e) {
				System.out.println("Error en ois "+e.getMessage());
			}
	}

}
