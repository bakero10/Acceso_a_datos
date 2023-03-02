package Ejercicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11LeeiendoJson {

	public static void main(String[] args) {
		// TODO Auto-generatGson 
		Gson gson2 = new Gson();
		//Leer de json
		String sFichero = null;
		try {
			sFichero = new String(Files.readAllBytes(Paths.get("Ficheros/Peliculas.json")));
			List<Pelicula> peli = Arrays.asList(gson2.fromJson(sFichero, Pelicula[].class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
