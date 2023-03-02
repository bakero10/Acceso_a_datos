package Ejercicios;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11_repetido {

	public static void main(String[] args) {
		
		List<Pelicula> lista = Arrays.asList(
						new Pelicula(1,"La comunidad del anillo",2001,"Ambientada en la tierra media"),
						new Pelicula(2,"Las dos torres",2002,"La disolucion de la compañia"),
						new Pelicula(3,"El retorno del rey",2003,"Vuelve el rey")
						);
		
		try {
			FileWriter fw = new FileWriter("Ficheros/peliculetas.json");
			new Gson().toJson(lista,fw);
			System.out.println("Se creo el archivo json.");
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
