package UD2_10;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Ejercicio10 {
	/*
	 * Crea un paquete de nombre UD2_10 e incluye en el todas las clases necesarias para construir el siguiente documento xml.
	 * Incluye otra clase que recorra el documento XML creado y visualice sus datos por la consola.
	 */
	public static void main(String[] args) {
		
		ArrayList<Pelicula> pelicula = new ArrayList<Pelicula>();
		pelicula.add(new Pelicula(1,"El señor de los Anillos:la Comunidad del Anillo",2001,"Ambientada en la Tierra Media"));
		pelicula.add(new Pelicula(2,"El señor de los Anillos:las dos torres",2002,"comienza con la disolucion de la compañia"));
		pelicula.add(new Pelicula(3,"El señor de los Anillos:el retorno del rey",2003,"ultima parte del viaje"));
		
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			xstream.toXML(pelicula,new FileOutputStream("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\Peliculas.xml"));
			System.out.println("Archivo creado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
