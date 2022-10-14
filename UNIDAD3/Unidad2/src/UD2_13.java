import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UD2_13  {
	/*
	 * Realiza una clase UD2_13 que almacene como objetos en un fichero ventas.dat los datos basicos
	 * de los clientes como son el nombre completo (String),telefono(String),direccion(String),
	 * nif (String) y moroso (String si/no).Debera codificarse para ellos 2 métodos:
	 * - Introducir en el fichero anterior los datos de los clientes que se pediran por teclado y se iran añadiendo al fichero
	 * .El atributo moroso no se incluira en el fichero(aun asi debe pedirse por teclado).
	 * - Visualizar los datos del fichero.
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		File f = new File("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\ventas.dat");
		String nombres [];
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			System.out.print("Introduce el nombre completo: ");
			String nombre = s.next();
			
			System.out.print("Introduce el telefono: ");
			String telefono = s.next();
			
			System.out.print("Introduce la direccion: ");
			String direccion = s.next();
			
			System.out.print("Introduce el nif: ");
			String nif = s.next();
			
			System.out.print("Debe dinero? ");
			String moroso =s.next();
			
			oos.writeObject(new Datos(nombre,telefono,direccion,nif,moroso));
			oos.close();
			
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			while(true)
				System.out.println(ois.readObject());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}

}
