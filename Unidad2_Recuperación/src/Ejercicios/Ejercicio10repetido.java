package Ejercicios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Ejercicio10repetido {
	public static void main(String[] args) throws IOException {
		
		ArrayList<Pelicula> listaSeries = new ArrayList<>();
		
		Pelicula p = new Pelicula(1,"La casa de papel",2010,"Robo en un banco");
		Pelicula p1 = new Pelicula(2,"Stranger Thinks",2014,"Serie de monstruos");
		Pelicula p2= new Pelicula(3,"Your Honor",2008,"El honor de un juez");
		Pelicula p3 = new Pelicula(4,"Peaky Blinders",2022,"Londres años 60 y 70");
		Pelicula p4 = new Pelicula(5,"Breaking Bad",2017,"Profesor drogata");
		
		listaSeries.add(p);listaSeries.add(p1);listaSeries.add(p2);listaSeries.add(p3);listaSeries.add(p4);
		
		
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Biblioteca", ListaPeliculas.class);
		xstream.alias("Serie", Pelicula.class);
		xstream.addImplicitCollection(ListaPeliculas.class, "lista");
		xstream.toXML(listaSeries,new FileWriter("Ficheros/series.xml"));
		System.out.println("Se creo el archivo xml");
		
	}
}
