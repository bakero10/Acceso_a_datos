import java.io.File;

public class Ejercicio01 {
	public static void main(String[] args) {
		File f = new File("C:\\windows");
		File[] archivos = f.listFiles();
		if (f.isDirectory()) {
			for (File archivo : archivos) {
				if (archivo.isHidden()) {
					System.out.println("Nombre " + archivo.getName());
					System.out.println("Longitud " + archivo.length());
					System.out.println("Se puede leer: " + archivo.canRead());
					System.out.println("Se puede escribir: " + archivo.canWrite());
					System.out.println(" ");
				}
			}
		}

	}
}
