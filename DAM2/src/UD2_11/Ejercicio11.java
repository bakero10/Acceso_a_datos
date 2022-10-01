package UD2_11;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import UD2_10.Pelicula;

public class Ejercicio11 {
	/*
	 * Crea un paquete de nombre UD2_11 e incluye en el todas las clases necesarias para crear en disco un fichero JSON con la
	 * informacion que aparece en el ejercicio anterior. Incluye tambien las dos clases que recorren el documento JSON
	 * creado que se han explicado en los materiales.
	 */
	public static void main(String[] args) {
		
		try {
			
			List<Pelicula> pelicula = Arrays.asList(
					new Pelicula(1,"El señor de los Anillos:la Comunidad del Anillo",2001,"Ambientada en la Tierra Media"),
					new Pelicula(2,"El señor de los Anillos:las dos torres",2002,"comienza con la disolucion de la compañia"),
					new Pelicula(3,"El señor de los Anillos:el retorno del rey",2003,"ultima parte del viaje"));
			
				FileWriter fw = new FileWriter("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\Peliculas.json");
				new Gson().toJson(pelicula,fw);
				fw.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
