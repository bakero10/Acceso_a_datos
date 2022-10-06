package UD2_11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Ejercicio11Leer {

	public static void main(String[] args) {
		try {
			Gson gson = new Gson();
			String fichero = new String(Files.readAllBytes(Paths.get("Ficheros/pelisborrar.json")));
				System.out.println(fichero);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}//main
}//class
