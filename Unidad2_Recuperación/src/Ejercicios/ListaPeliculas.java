package Ejercicios;

import java.util.ArrayList;
import java.util.List;

public class ListaPeliculas {
	
	private ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
	public ListaPeliculas() {}
	
	public void add (Pelicula p) {
		lista.add(p);
	}
	
	public List<Pelicula> getListaPeliculas(){
		return lista;
	}
	
}
