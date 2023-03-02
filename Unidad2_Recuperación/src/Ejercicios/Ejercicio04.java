package Ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio04 {

	public static void main(String[] args) {
		try {
			FileWriter archivoEscrbir = new FileWriter(new File ("C:\\Users\\Bakero\\Desktop\\Pruebas\\pares.txt"));
			BufferedWriter salida = new BufferedWriter(archivoEscrbir);
			for (int i=0; i<500; i++) {
				if(i%2 == 0) {
					
					salida.write(String.valueOf(i));
					salida.newLine();
				}
			}
			salida.close();
			System.out.println("Archivo completado¡");
		} catch (IOException e) {
			System.out.println("Error"+e.getMessage());
		}
	}

}
