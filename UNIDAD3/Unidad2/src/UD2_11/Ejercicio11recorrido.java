package UD2_11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11recorrido {

	public static void main(String[] args) {
		
		try {
			Gson gson = new Gson();
			String fichero = new String(Files.readAllBytes(Paths.get("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\Peliculas.json")));
			List<Pelicula> pelicula = Arrays.asList(gson.fromJson(fichero,Pelicula[].class));
			for(Pelicula p : pelicula) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}

	}

}
