package Unidad2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio13 {
		/*
		 * Realiza una clase UD2_13 que almacene como objetos en un fichero ventas.dat los datos basicos de los clientes como son
		 * el nombre completo(String),telefono(String),direccion(String),nif(String) y moroso(String si/no).Debera codificarse
		 * para ellos 2 metodos:
		 *  -   Intorducir el fichero anterior con los datos de los clientes que se pediran por teclado y se iran añadiendo
		 *  	al fichero.El atributo moroso no se incluira en el fichero(aun asi debe pedirse por teclado).
		 *  -	Visualizar los datos del fichero
		 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Introduce tu nombre: ");
		String nombre =s.next();
		System.out.print("Introduce tu Telefono: ");
		String telefono=s.next();
		System.out.print("Introduce tu Dirección: ");
		String direccion=s.next();
		System.out.print("Introduce tu nif: ");
		String nif=s.next();
		System.out.print("¿Eres moroso? ");
		String moroso=s.next();

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/ventas.dat"));
		try {
			oos.writeObject(new Cliente(nombre,telefono,direccion,nif, null));
			oos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/ventas.dat"));
		try {
			while(true) {
				System.out.println(ois.readObject());
			}
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		ois.close();
	
	}

}
