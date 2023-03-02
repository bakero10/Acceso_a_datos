package Ejercicios;

import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Ejercicio10 {

	public static void main(String[] args) {
		ListaPeliculas lista = new ListaPeliculas();
		
		lista.add(new Pelicula(1,"La comunidad del anillo",2001,"Ambientada en la tierra media"));
		lista.add(new Pelicula(2,"Las dos torres",2002,"La disolucion de la compañia"));
		lista.add(new Pelicula(3,"El retorno del rey",2003,"Vuelve el rey"));
		
		
		try {
			
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			xstream.alias("Peliculas", ListaPeliculas.class);
			xstream.alias("PeliculasVistas", Pelicula.class);
			xstream.addImplicitCollection(ListaPeliculas.class, "lista");
			xstream.toXML(lista,new FileOutputStream("Ficheros/peliculas.xml"));
			System.out.println("Creando fichero");
		} catch (Exception e) {
			System.out.println("Error en el creado del archivo "+e.getMessage());
		}
	}
	
	
}
