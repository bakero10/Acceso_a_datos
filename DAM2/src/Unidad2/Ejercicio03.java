package Unidad2;

import java.io.File;

public class Ejercicio03 {

	public static void main(String[] args) {
		//adsfasd
		File f;
		if(args.length == 0) {
			System.out.println("No as introducido ninguna cadena.");
			System.exit(-1);
		}
		f = new File(args[0]);
		if(f.exists()) {
			if(f.isDirectory()) {
				f.delete();
				System.out.println("Borrado de directorio.");
			}
			if(f.isFile()) {
				f.delete();
				System.out.println("Borrado de fichero.");
			}
		}
		else
			System.out.println("El directorio o fichero no existe");

	}

}
