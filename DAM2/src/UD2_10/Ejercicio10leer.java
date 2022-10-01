package UD2_10;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class Ejercicio10leer {

	public static void main(String[] args) {
		XStream xstream = new XStream();
		ArrayList<Pelicula> pelicula= new ArrayList<Pelicula>();
		try {
		pelicula=(ArrayList<Pelicula>) xstream.fromXML(new FileInputStream("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\Peliculas.xml"));
			for(Pelicula p : pelicula) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}

	}

}
