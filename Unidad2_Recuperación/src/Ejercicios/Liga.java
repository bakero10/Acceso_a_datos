package Ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Liga {

	ArrayList<Equipo> lista = new ArrayList<Equipo>();
	public Liga() {}
	
	public void add (Equipo e) {
		lista.add(e);
	}
	
	public List<Equipo> getListaEquipo(){
		return lista;
	}
	
	
}
