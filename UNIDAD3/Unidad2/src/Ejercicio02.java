import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio02 {

	public static void main(String[] args) throws IOException {
		File archivo = null;
		
		if (args.length == 0) 
			System.out.println("No se ha pasado ningun argumento");
			else 
				archivo = new File(args[0]);
				if(archivo.exists())
					System.out.println("El archivo existe");
					else
						System.out.println("El archivo no existe");
			}
	}

