package Unidad2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Ejercicio12 {

	public static void main(String[] args) {
		/**
		 * Realiza una clase UD2_12 con un m�todo principal que reciba dos argumentos,
		 * un directorio y una extensi�n de fichero, e indique los datos (nombre, tama�o
		 * y fecha de creaci�n) de los ficheros del directorio que tienen esa extension.
		 * Pista --> Interfaz FilenameFilter.
		 * Debera comprobarse al principio del codigo que el directorio enviado como primer argumento existe, 
		 * en caso contrario se indicar� y finalizar� su ejecuci�n.
		 * 
		 */
		if (args.length == 0) {
			System.out.println("No se ha recibido ningun argumento!!");
			System.exit(0);
		} else {
			File directorio = new File(args[0]);
			File[] lista = directorio.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File f, String name) {
					if (name.endsWith(args[1])) {
						return true;
					}
					return false;
				}
			});
			for (File fichero : lista) {
				System.out.println("Nombre:\t" + fichero.getName());
				System.out.println("Tama�o:\t" + fichero.length() + " bytes");
				// Para sacar fecha de creacion del archivo
				BasicFileAttributes attrs = null;
				try {
					attrs = Files.readAttributes(fichero.toPath(), BasicFileAttributes.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileTime time = attrs.creationTime();
				//Recibo un timestamp que formateo para sacarlo como fecha y hora
				String pattern = "dd-MM-yyyy HH:mm:ss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String formatted = simpleDateFormat.format(new Date(time.toMillis()));

				System.out.println("Creado:\t" + formatted + "\n");
			
			}

		}//else

	}// main
}// class
