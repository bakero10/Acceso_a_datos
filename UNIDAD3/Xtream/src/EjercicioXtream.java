import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class EjercicioXtream {
	/*
	 * Crea un paquete de nombre UD2_10 e incluye en el todas las clases
	 * necesarias para construir el siguiente documento XML. Incluye otra 
	 * clase que recorra el documento XML creado  visualice sus datos por la consola.
	 * 
	 */
	public static void main(String[] args) {
		
		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
		lista.add(new Pelicula(1,"El señor de los Anillos:la Comunidad del Anillo",2001,"Ambientada en la Tierra Media"));
		lista.add(new Pelicula(2,"El señor de los Anillos:las dos torres",2002,"Disolucion compañia del anillo"));
		lista.add(new Pelicula(3,"El señor de los Anillos:El retorno del rey",2003,"Ultima parte del viaje"));
		
		try {
			XStream xstream = new XStream();
			xstream.alias("Peliculas", List.class);
//			xstream.alias("pelicula", Pelicula.class );
//			xstream.addImplicitCollection(lista.getClass(), "lista");
			xstream.toXML(lista,new FileOutputStream("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\Ejer10.xml"));
			System.out.println("Creado fichero XML.....");
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		
	}
}
