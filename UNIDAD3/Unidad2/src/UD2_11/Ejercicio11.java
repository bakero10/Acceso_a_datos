package UD2_11;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11 {
	
	/*
	 * Crea un paquete de nombre UD2_11 e incluye en el todas las clases necesarias para crear en disco
	 * un fichero JSON con la informacion que aparece en el ejercicio anterior.
	 * Incluye tambien las dos clases que recorren el documento JSON creado que se han explicado en los materiales.
	 */
	public static void main(String[] args) {
		try {
			
			List<Pelicula> pelicula = Arrays.asList(
					new Pelicula(1,"El señor de los anillos:la Comunidad del Anillo",2001,
							"Se forma la compañia del anillo"),
					new Pelicula(2,"El señor de los anillos:Las dos torres",2002,
							 "Disolucion compañia del anillo"),
					new Pelicula(3,"El señor de los anillos:El retorno del rey",2003,
							 "Ultima parte del viaje")
					);
			FileWriter fw = new FileWriter("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\Peliculas.json");
			new Gson().toJson(pelicula,fw);
			fw.close();
			System.out.println("Archivo creado.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
