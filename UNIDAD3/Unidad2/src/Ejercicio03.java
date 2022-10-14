import java.io.File;

public class Ejercicio03 {

	public static void main(String[] args) {
		File archivo = null;
		boolean resultado = false;
		if (args.length == 0)
			System.out.println("No se ha pasado ningun argumento");
		else
			archivo = new File(args[0]);
		if (archivo.exists())
			resultado = archivo.delete();
		if (archivo.isDirectory())
			archivo.delete();
		if (resultado == true)
			System.out.println("Se ha borrado el fichero");
		else
			System.out.println("El archivo no existe");

		System.out.println(resultado);

	}
}
