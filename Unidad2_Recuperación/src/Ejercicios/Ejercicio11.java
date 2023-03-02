package Ejercicios;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11 {

	public static void main(String[] args) {
		
		try {
			
			//1-Introducimos en la lista de peliculas las peliculas con el atributo Arrays.aslist
			List<Pelicula> lista = Arrays.asList(new Pelicula(1,"La comunidad del anillo",2001,"Ambientada en la tierra media"),
					new Pelicula(2,"Las dos torres",2002,"La disolucion de la compañia"),
					new Pelicula(3,"El retorno del rey",2003,"Vuelve el rey")
					);
			
			//2-Escribimos con FileWriter en el archivo...
			FileWriter fw = new FileWriter("Ficheros/Peliculas.json");
			//3-Le decimos que va a ser un new Gson.toJson y le damos el ArrayList y le decimos FileWriter
			new Gson().toJson(lista,fw);
			//4-Cerramos el json
			fw.close();
			System.out.println("Json creado!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
