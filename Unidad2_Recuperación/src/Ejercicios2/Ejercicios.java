package Ejercicios2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Ejercicios.Pelicula;

public class Ejercicios {

//	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		/*
//		 * UD2.1- Realiza una clase que muestre nombre,longitud,si se puede leer,
//		 * si se puede escribir de todos los archicos ocultos de la carpeta windows de tu disco;
//		 * solo de los que se encuentran en la carpeta principal de Windows,no en sus subcarpetas.
//		 */
//		
//		File f = new File("C://WINDOWS");
//		File [] lista = f.listFiles();
//		for (File file : lista) {
//			if(file.isHidden()) {
//				System.out.println(file.getName());
//				System.out.println(file.length());
//				System.out.println(file.canRead());
//				System.out.println(file.canWrite());
//				System.out.println("---------------");
//			}
//		}
	

		/*
		 * UD2.2- Realiza una clase cuyo metodo principla reciba como argumento una cadena con la 
		 * trayectoria de un directorio o fichero e indique si existe realmente o no dicho directorio
		 * o fichero. Si el metodo principal no recibe ningun argumento se indicara por pantalla y finalizara 
		 * su ejecucion.
		 */
		
//		public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		
//		try {
//			if(args.length == 0) {
//				System.out.println("No se ha pasado ningun argumento!");
//			}
//			File f = new File(args[0]);
//			if(f.isFile() || f.isDirectory()) {
//				if(f.isDirectory()) {
//					System.out.println("El directorio --> "+f+" <-- existe!");
//				}
//				if(f.isFile()) {
//					System.out.println("El archivo --> "+f+" <-- existe!");
//				}
//			}
//			else {
//				System.out.println("El archivo o directorio no existe!");
//			}
//		} catch (Exception e) {
//			
//		}
		
		/*
		 * 	UD2.3- Realiza una clase que complete la clase EjemploClaseFile02 de los materiales
		 * borrando el directorio y el fichero creador en ella(primero borra el fichero y despues
		 * el directorio pues no se permite borrar directorios no vacios).
		 */
		
//		public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//	
//		boolean resultado;
//		File directorio = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas");
//		resultado = directorio.mkdir();
//		if(resultado) {
//			System.out.println("Directorio Creado");
//		}
//		else {
//			System.out.println("No se pudo crear el directorio");	//POSIBLEMENTE EXISTA
//			System.exit(-1);
//		}
//		try {
//			File fichero = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas\\Alberto.txt");
//			resultado = fichero.createNewFile();
//			if(resultado) {
//				System.out.println("Archivo creado");
//			}
//			else {
//				System.out.println("No se pudo crear el archivo");	//POSIBLEMENTE EXISTA
//			}
//		} catch (Exception e) {
//			System.out.println("Se produjo un error: "+e.getMessage());
//		}
		
		// SEGUNDA PARTE DEL EJERCICIO3 BORRAR FICHERO Y DIRECTORIO

//		public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//	
//		boolean resultado;
//		File fichero = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas\\Alberto.txt");
//		resultado = fichero.delete();
//		
//		if (resultado) {
//			System.out.println("Fichero borrado con exito");
//		} else {
//			System.out.println("No se pudo borrar el fichero"); // POSIBLEMENTE NO EXISTA
//		}
//		
//		File directorio1 = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\pruebas");
//		resultado = directorio1.delete();
//		if (resultado) {
//			System.out.println("Directorio borrado con exito");
//		} else {
//			System.out.println("No se pudo borrar el directorio"); // POSIBLEMENTE NO EXISTA
//		}
		
		/*
		 * UD2-4 Realiza una clase que guarde en un fichero con nombre pares.txt los numeros pares
		 * que hay en entre 0 y 500, un número en cada linea del fichero.Seguidamente lee el fihcero y
		 * muestralo por pantalla.Incluye tambien el tratamiento de excepciones.
		 */
		
//		public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//	
//		File f = new File("Ficheros/pares.txt");
//		try {
//			FileWriter fw = new FileWriter(f);
//			BufferedWriter bw =  new BufferedWriter(fw);
//			
//			for (int i = 0; i < 500; i++) {
//				if(i%2 == 0) {
//					String num = String.valueOf(i);
//					bw.write(num);
//					bw.newLine();
//				}
//			}
//			System.out.println("Archivo pares creado");
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		FileReader fr;
//		try {
//			fr = new FileReader(f);
//			BufferedReader br = new BufferedReader(fr);
//			String lineaLeida;
//			
//			try {
//				while((lineaLeida = br.readLine()) != null) {
//					System.out.println(lineaLeida);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		/*
		 * UD2-5 Realiza una clase que sea capaz de ordenar alfabeticamente las lineas contenidas en un fichero de texto(puedes crearlo con 
		 * el block de notas).El nombre del fichero que contiene las lineas se debe pasar como argumento.El nombre del fichero resultado
		 * ya ordenado debe ser el mismo que el original añadiendole la coletilla_sort al nombre. Incluye tambien tratamiento de excepciones.
		 */
		
//		public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
//	
//			FileReader fr = new FileReader(new File(args[0]+".txt"));
//			BufferedReader br = new BufferedReader(fr);
//			String lineaLeida;
//			ArrayList<String> lista = new ArrayList<>();
//				while((lineaLeida = br.readLine())!= null) {
//					lista.add(lineaLeida);
//				}
//				Collections.sort(lista);
//				for (String string : lista) {
//					System.out.println(string);
//				}
		
		
	
		/*
		 * UD2-6 Realiza una clase que indique cuantas veces aparece una palabra dentro de un fichero de texto( puedes
		 * crearlo con el block de notas). Tanto el nombre del fichero como la palabra se debe pasar como argumentos. No distinguir
		 * mayusculas de minusculas. Incluye tambien tratamiento de excepciones.
		 */
		
//				public static void main(String[] args) {
//					Scanner s = new Scanner(System.in);
//				
//		int contador = 0;
//		String [] trozos = null;
//		try {
//			FileReader fr = new FileReader(new File(args[0]));
//			BufferedReader br = new BufferedReader(fr);
//			String lineaLeida;
//			try {
//				while((lineaLeida = br.readLine())!=null) {
//					System.out.println(lineaLeida); 
//					trozos = lineaLeida.split("\\s+");
//					for (String string : trozos) {
//						if(string.equalsIgnoreCase(args[1])) {
//							contador++;
//						}
//					}
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	
//		
//		System.out.println("La palabra "+args[1]+" aparece "+contador);
//		
		/*
		 * UD2-7 Realiza una clase que guarde 20 numeros enteros aleatorios comprendidos entre 1 y 5 en el fichero
		 * puntuacion.dat Completa el codigo abriendo el fichero para visualizarlos todos por la consola indicando al
		 * final cuantas veces se repite cada uno de ellos. Incluye tambien el tratamiento de excepciones.
		 */
	
//		public static void main(String[] args) throws IOException {
//		
//		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\puntuacion.dat");
//		try {
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
//			for (int i = 0; i < 20; i++) {
//				int num = 0;
//				num = (int) Math.round((Math.random() * 4) + 1);
//				dos.writeInt(num);
//			}
//			dos.close();
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//		//SEGUNDA PARTE 
//		DataInputStream datosEntrada = new DataInputStream(new FileInputStream(f));
//		ArrayList<Integer> listaEnteros = new ArrayList<>();
//		int contadorUnos = 0;
//		int contadorDoses = 0;
//		int contadorTreses = 0;
//		int contadorCuatros = 0;
//		int contadorCincos = 0;
//		try {
//			while(true) {
//				listaEnteros.add(datosEntrada.readInt());
//			}			
//		} catch (Exception e) {
//		}
//		for (Integer integer : listaEnteros) {
//			System.out.print(integer);
//			if(integer == 1) {contadorUnos++;}
//			if(integer == 2) {contadorDoses++;}
//			if(integer == 3) {contadorTreses++;}
//			if(integer == 4) {contadorCuatros++;}
//			if(integer == 5) {contadorCincos++;}
//		}
//		System.out.println();
//		System.out.println("El numero 1 aparece "+contadorUnos+" veces.");
//		System.out.println("El numero 2 aparece "+contadorDoses+" veces.");
//		System.out.println("El numero 3 aparece "+contadorTreses+" veces.");
//		System.out.println("El numero 4 aparece "+contadorCuatros+" veces.");
//		System.out.println("El numero 5 aparece "+contadorCincos+" veces.");
//	}
	
	/*
	 * UD2-8 Adaptacion de los ejemplos vistos en los materiales con las clases ObjectInputStream y 
	 * ObjectOutputStream Realiza una clase UD2-8 que pida al usuario datos de varios profesores
	 * (nombre y la antiguedad) y los inserte en el fichero antiguedad.dat_obj.dat Si el fichero no existe
	 * se creará con los nuevos datos introducidos en caso contrario se añadiran por el final. Antes de finalizar el 
	 * codigo se recorrera el fichero para visualizar su contenido.Prueba varias veces la ejecucion de la clase.
	 */
//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		Scanner s = new Scanner(System.in);
//		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\antiguedad.dat_obj.dat");
//		ObjectOutputStream objetoSalida = new ObjectOutputStream(new FileOutputStream(f));
//		String nombre="";
//		double antiguedad;
//		int numeroProf;
//		
//		System.out.println("Introduce el numero de profesores a inscribir: ");
//		numeroProf = s.nextInt();
//		
//		String [] nombres = new String [numeroProf];
//		double [] antiguedades = new double [numeroProf];
//		
//		for (int i=0; i<numeroProf; i++) {
//			System.out.println("Introduce el nombre del profesor: ");
//			nombre = s.next();
//			System.out.println("Introduce la antiguedad: ");
//			antiguedad = s.nextDouble();
//			Profesor p = new Profesor(nombre,antiguedad);
//			objetoSalida.writeObject(p);			
//		}
//		System.out.println("Objeto introducido");
//		objetoSalida.close();
//		
//		//SEGUNDA PARTE RECUPERAMOS LOS DATOS DEL FICHERO	
//		
//		ObjectInputStream objetoEntrada = new ObjectInputStream(new FileInputStream(f));
//			try {
//				while(true) {
//				System.out.println(objetoEntrada.readObject());
//				}
//			} catch (ClassNotFoundException | IOException e) {
//				System.out.println("Final de Fichero ");
//			}
//			objetoEntrada.close();
//	}
		/*
		 * UD2-9 Adaptacion de los ejemplos vistos en los materiales con la clase RandomAccesFile.
		 * Realiza una clase que pida al usuario el identificador del profesor y lo borre del fichero ProfesFPSierraGuara.dat
		 * Borrar un dato simplemente consiste en pone su campo id dentro del fichero a 0 para indicar que ese registro no existe 
		 * y su posicion esta libre. Se debera controlar que:
		 * 		- El identificador del profesor este dentro de los limites del fichero
		 * 		- El identificador del profesor debe existir.Si ha sido borrado previamente se advertira de la situacion
		 * 		- Antes de finalizar el codigo visalizar de manera secuencial todos los registros del fichero
		 * 		  para comprovar la operacion.
		 * 
		 * Id 			  (entero – 4 bytes)
		 * Nombre (20 caracteres – 40 bytes)
		 * Departamento    (entero– 4 bytes)
		 * Antigüedad 		(real – 8 bytes)
		 *			===================================
		 *					 Total(56 bytes)
		 */
		
//		public static void main(String[] args) throws FileNotFoundException, IOException {
//			Scanner s = new Scanner(System.in);
//			int posicion;
//			int id;
//			System.out.println("Introduce el identificador del profesor ");
//			id = s.nextInt();
//			File file = new File("Ficheros/ProfesFPSierraGuara.dat");
//			try {
//				RandomAccessFile random = new RandomAccessFile(file, "rw");
//				posicion = (id - 1)*56;			//el primero llega hasta 56 por eso se pone asi ya que en total hay 56 bytes
//				if(posicion > file.length() || posicion == 0) {
//					System.out.println("El profesor con ese identificador no existe");
//					System.exit(-1);
//				}
//				random.seek(posicion);				//con seek no ponemos en la posicion que queremos
//				if(random.readInt() == 0) {
//					System.out.println("Este profesor ya ha sido borrado");
//					System.exit(-1);
//				}
//				random.seek(posicion);
//				random.writeInt(0);					//con write int ponemos el id a 0 osease borrado
//				int departamento;
//				double antiguedad;
//				char [] nombre=new char[20];
//				random.seek(0);
//				System.out.println("Id\tNombre\tDepartamento\tAntiguedad");
//				for (int i = 0; i < file.length(); i++) {
//					int ide = random.readInt();
//					for (int j = 0; j < nombre.length; j++) {
//						nombre[j]=random.readChar();
//					}
//					departamento = random.readInt();
//					antiguedad = random.readDouble();
//					String nombres =  new String(nombre);
//					System.out.println(id+"\t"+nombres+"\t\t"+departamento+"\t"+antiguedad);
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		/*
		 * UD2-10 Crea un paquete de nombre UD2-10 e incluye en el todas las clases necesarias 
		 * para construir el siguiente documento XML. Incluye otra clase que recorra el documento
		 * xml creado y visualce sus datos por la consola.
		 */
		
			// Clase del arrayList
			//	public class ListaPeliculas {
					
			//		private ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
			//		public ListaPeliculas() {}
					
			//		public void add (Pelicula p) {
			//			lista.add(p);
			//		}
					
			//		public List<Pelicula> getListaPeliculas(){
			//			return lista;
			//		}
					
			//	}
	
	
	
//			public static void main(String[] args) throws FileNotFoundException, IOException {
//			Scanner s = new Scanner(System.in);
//			
//			//ESCRIBIR UN ARCHIVO JSON
//			
//			ArrayList<Pelicula> listaPelis = new ArrayList<>();
//			Pelicula p = new Pelicula(1,"Triple X",2001,"Tierra Media");
//			Pelicula p1 = new Pelicula(2,"Braveheart",2004,"Escoces loco");
//			Pelicula p2 = new Pelicula(3,"Gladiator",2006,"Romanos");
//			Pelicula p3 = new Pelicula(4,"Gru mi villano favorito",2003,"Villano de dibujos");
//			listaPelis.add(p);listaPelis.add(p1);listaPelis.add(p2);listaPelis.add(p3);
//			
//			XStream xstream = new XStream();
//			xstream.alias("Peliculas", ListaPeliculas.class);	// Nombre y clase del arrayList
//			xstream.alias("Pelicula", Pelicula.class );			// Nombre y clase de pelicula
//			xstream.addImplicitCollection(ListaPeliculas.class, "lista");	// clase del ArrayList y lista(Metodo del arrayList)
//			xstream.toXML(listaPelis,new FileWriter("Ficheros/peliculas.xml")); // ArrayList de aquí y el escribidor
//			System.out.println("Archivo creado");
//			
//			//LEER UN ARCHIVO JSON
//			
//			XStream xstream2 = new XStream();
//			xstream2.alias("Peliculas", ListaPeliculas.class);
//			xstream2.alias("Pelicula", Pelicula.class );
//			xstream2.addImplicitCollection(ListaPeliculas.class, "lista");
//
//			File archivo = new File("Ficheros/peliculas.xml");
//			FileReader reader = new FileReader(archivo);
//			BufferedReader br = new BufferedReader(reader);
//
//			String linea;
//			StringBuilder sb = new StringBuilder();
//			while ((linea = br.readLine()) != null) {
//			    sb.append(linea);
//			}
//
//			String xml = sb.toString();
//			Object obj = xstream.fromXML(xml);
//
//			ArrayList<Pelicula> listaPelis2 = (ArrayList<Pelicula>) obj;
//
//			// Imprimir la lista de películas
//			for (Pelicula pelicula : listaPelis2) {
//				System.out.println(pelicula.getId()+" - "+pelicula.getTitulo()+" - "+pelicula.getAnyo()+" - "+pelicula.getDescripcion());
//			}
//			
//			
//			}
		
		/*
		 * UD2-11 Crea un paquete de nombre UD2-11 e incluye en el todas las clases necesarias para crear en disco un fichero JSON
		 * con la informacion que aparece en el ejercicio anterior.Incluye tambien las dos clases que recorren el documento JSON 
		 * creado que se han explicado en los materiales.
		 */
		
			//CREACION DE UN ARCHIVO JSON
	
//			public static void main(String[] args) throws FileNotFoundException, IOException {
//			Scanner s = new Scanner(System.in);
//			
//			ArrayList<Pelicula> listaPelis = new ArrayList<>();
//			Pelicula p = new Pelicula(1,"Triple X",2001,"Tierra Media");
//			Pelicula p1 = new Pelicula(2,"Braveheart",2004,"Escoces loco");
//			Pelicula p2 = new Pelicula(3,"Gladiator",2006,"Romanos");
//			Pelicula p3 = new Pelicula(4,"Gru mi villano favorito",2003,"Villano de dibujos");
//			listaPelis.add(p);listaPelis.add(p1);listaPelis.add(p2);listaPelis.add(p3);
//			
//			FileWriter fw = new FileWriter("Ficheros/creadoJson.json");
//			new Gson().toJson(listaPelis,fw);
//			System.out.println("archivo creado");
//			fw.close();
//			
//			//LEER UN ARCHIVO JSON
//			
//			FileReader fr =  new FileReader("Ficheros/creadoJson.json");
//			Gson gson =	new Gson();
//			String fichero;
//			fichero = new String(Files.readAllBytes(Paths.get("Ficheros/creadoJson.json")));
//			List<Pelicula> listaPelis2 =  Arrays.asList(gson.fromJson(fichero, Pelicula[].class));
//			for (Pelicula pelicula : listaPelis2) {
//				System.out.println(pelicula.getId()+" - "+pelicula.getTitulo()+" - "+pelicula.getAnyo()+" - "+pelicula.getDescripcion());
//			}
//			fr.close();
//			}
	
		//ESTO ES POR SIACASO DE SANTI
	/**
	 * Metodo para leer el fichero Json, esta es la forma, con m�s codigo y no recogemos los objetos
	 * en una lista, asi que tenemos que utilizar los metodos de Json para sacar los elementos que componen un archivo json
	 * 
	 * JsonElement, JsonArray, JsonObject...
	 *  
	 * JsonElement representa cualquier elemento JSON que puede ser:
	 *	- JsonObject: Representa un objeto JSON.
	 *	- JsonArray: Representa un array JSON .
	 *	- JsonPrimitive: Representa un tipo de dato primitivo u objetos de datos simples (String p.ej.).
	 *	- JsonNull: Representa un objeto nulo.
	 * **/
	
//	public static void main(String[] args) {
//		try {
//			String Fichero = new String(Files.readAllBytes(Paths.get("Ficheros\\ProfesFPSierraGuara.json")));
//			
//			//Declaramos un objeto tipo JsonArray que contendra el conjunto de objetos 
//			//del archivo, le indicamos que parsee o traduzca el fichero declarando el parse 
//			//de json y le indicamos que lo convierta un array con el .getJsonArray, ya que lo queremos meter en 
//			//un objeto tipo JsonArray
//			JsonArray gsonArr = JsonParser.parseString(Fichero).getAsJsonArray();
//			
//			//Ahora recorremos la lista, tener en cuenta que todos los objetos de la lista en json se les llama JsonElement
//			for (JsonElement obj : gsonArr) {
//				JsonObject gsonObj = obj.getAsJsonObject(); //Por cada vuelta, le indicamos que es un objeto lo que va a leer. Le indica que el siguiente elemento sera un objeto
//				//Ahora de ese objeto recogido en json, podemos decirle que nos de el valor que contiene la etiqueta(Hay que poner exactamente el mismo nombre)
//				System.out.println(gsonObj.get("id").getAsInt() + " - " +
//								   gsonObj.get("titulo").getAsString() + " - " +
//								   gsonObj.get("anyo").getAsInt() + " - " +
//								   gsonObj.get("descripcion").getAsString() + " - ");			
//			}
//			System.out.println();
//			
//		}catch (Exception e) {
//			System.out.println("Ha ocurrido un error");
//		}
	
	
	
	
	
	
}
