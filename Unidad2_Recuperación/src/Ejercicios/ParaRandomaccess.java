package Ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class ParaRandomaccess {
	public static void main(String[] args) throws IOException {

		// Creamos los archivos
		File F = new File("Ficheros/micasa.dat");
		RandomAccessFile file = new RandomAccessFile(F, "rw");
		// Creamos los arrays
		String[] nombres = { "Marivi", "Toñin", "Alexia", "Jose", "Maria" };
		int[] edad = { 63, 53, 38, 34, 32 };
		String[] profesion = { "Encargada", "Albañil", "Jubilada", "Fisioterapeuta" };

		StringBuffer sb = null;
		StringBuffer sb1 = null;
		int total = nombres.length;

		for (int i = 0; i < total; i++) {
			// id
			file.writeInt(i + 1); // Escribimos el ID del random
			// array nombres
			sb = new StringBuffer(nombres[i]);
			sb.setLength(20); // Le decimos que la longitud sera de 20 caracteres osea 40 bits
			file.writeChars(sb.toString());
			// array edad
			file.writeInt(edad[i]);
			// array profesion
//			sb1 = new StringBuffer(profesion[i]);
//			sb.setLength(20);
//			file.writeChars(sb.toString());
		}
		file.close();
	}
}
