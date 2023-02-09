package EjerciciosAlbertoEnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Ejer {

	static MongoClient mongo = new MongoClient("localhost", 27017);
	static MongoDatabase database = mongo.getDatabase("test");
	static MongoCollection<Document> collection = database.getCollection("ciudades");

	public static void ejercicio01() {
		// 1. Número de ciudades.
		System.out.println(collection.countDocuments());

	}

	public static void ejercicio02() {
		// 2. Datos de la ciudad de Elx.
		FindIterable<Document> lista = collection.find(new Document("name", "Elx"));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio02_2() {
		Document d = collection.find(new Document("name", "Elx")).first();
		System.out.println(d);
	}

	public static void ejercicio03() {
		// 3. Población de la ciudad de Vergel.
		ArrayList<Document> lista = new ArrayList<>();
		Document d = new Document("$match", new Document("name", "Vergel"));
		Document d2 = new Document("$project", new Document("_id", 0).append("poblacion", "$population"));
		lista.add(d);
		lista.add(d2);
		AggregateIterable<Document> documento = collection.aggregate(lista);
		Iterator it = documento.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void ejercicio04() {
		// 4. Cantidad de ciudades en España ({"country":"ES"}).
		ArrayList<Document> lista = new ArrayList<>();
		Document d = new Document("$match", new Document("country", "ES"));
		Document d2 = new Document("$group",
				new Document("_id", "$country").append("totalCiudades", new Document("$sum", 1)));
		Document d3 = new Document("$project", new Document("_id", 0).append("totalCiudades", "$totalCiudades"));
		lista.add(d);
		lista.add(d2);
		lista.add(d3);
		AggregateIterable<Document> documento = collection.aggregate(lista);
		Iterator it = documento.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void ejercicio05() {
		// 5. Datos de las ciudades españolas con más de 1.000.000 de habitantes.
		FindIterable<Document> lista = collection
				.find(new Document("country", "ES").append("population", new Document("$gt", 1000000)));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio06() {
		// 6. Cantidad de ciudades de Andorra ({"country":"AD"}) y España.
		ArrayList<Document> lista = new ArrayList<>();
		List<String> strings = Arrays.asList("ES", "AD");
		Document d = new Document("$match", new Document("country", new Document("$in", strings)));
		Document d1 = new Document("$group", new Document("_id", "$country").append("total", new Document("$sum", 1)));
		Document d2 = new Document("$project", new Document("_id", 0).append("total", "$total"));
		lista.add(d);
		lista.add(d1);
		lista.add(d2);
		AggregateIterable<Document> documento = collection.aggregate(lista);
		Iterator it = documento.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void ejercicio06_2() {

		List<String> strings = Arrays.asList("ES", "AD");
		FindIterable<Document> lista = collection.find(new Document("country", new Document("$in", strings)));
		int contador = 0;
		for (Document document : lista) {
			contador++;
		}
		System.out.println(contador);
	}

	public static void ejercicio07() {
		// 7. Listado con el nombre y la población de las 10 ciudades más pobladas.
		ArrayList<Document> lista = new ArrayList<>();
		Document d = new Document("$sort", new Document("population", -1));
		Document d1 = new Document("$limit", 10);
		Document d2 = new Document("$project",
				new Document("_id", 0).append("nombre", "$name").append("poblacion", "$population"));
		lista.add(d);
		lista.add(d1);
		lista.add(d2);
		AggregateIterable<Document> documento = collection.aggregate(lista);
		Iterator it = documento.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void ejercicio07_2() {
		FindIterable<Document> lista = collection.find(new Document()).sort(new Document("population", -1)).limit(10)
				.projection(new Document("_id", 0).append("name", 1).append("population", 1));
		for (Document d : lista) {
			System.out.println(d);
		}
	}

	public static void ejercicio08() {
		// 8. Nombre de las distintas zonas horarias en España.
		ArrayList<Document> lista = new ArrayList<>();
		Document d = new Document("$match", new Document("country", "ES"));
		Document d1 = new Document("$group", new Document("_id", "$timezone"));
		Document d2 = new Document("$project", new Document("_id", "0").append("timezone", "$_id"));
		lista.add(d);
		lista.add(d1);
		lista.add(d2);
		AggregateIterable<Document> documento = collection.aggregate(lista);
		Iterator it = documento.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	public static void ejercicio08_2() {
		for (String s : collection.distinct("timezone", new Document("country", "ES"), String.class)) {
			System.out.println(s);
		}
	}

	public static void ejercicio09() {
		// 9. Ciudades españolas que su zona horaria no sea Europe/Madrid.
		FindIterable<Document> lista = collection
				.find(new Document("country", "ES").append("timezone", new Document("$ne", "Europe/Madrid")));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio10() {
		// 10. Ciudades españolas que comiencen por Ben
		FindIterable<Document> lista = collection
				.find(new Document("country", "ES").append("name", java.util.regex.Pattern.compile("^Ben.*")));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio11() {
		// 11. Ciudades que su zona horaria sea Atlantic/Canary o Africa/Ceuta, y que
		// tengan más de 500.000 habitantes.
		List<String> strings = Arrays.asList("Atlantic/Canary", "Africa/Ceuta");
		FindIterable<Document> lista = collection.find(new Document("timezone", new Document("$in", strings))
				.append("population", new Document("$gt", 500000)));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio12() {
		// 12. Nombre y población de las tres ciudades europeas más pobladas.
		FindIterable<Document> fi = collection
				.find(new Document("timezone", java.util.regex.Pattern.compile("^Europe.*")))
				.sort(new Document("population", -1)).limit(3);
		for (Document d : fi) {
			System.out.println(d);
		}
	}

	public static void ejercicio13() {
		// 13. Cantidad de ciudades españolas cuya coordenadas de longitud estén
		// comprendidas entre -0.1 y 0.1.
		List<Document> and = new ArrayList<>();
		and.add(new Document("location.longitude", new Document("$gt", -0.1)));
		and.add(new Document("location.longitude", new Document("$lt", 0.1)));
		FindIterable<Document> lista = collection.find(new Document("country", "ES").append("$and", and));
		for (Document document : lista) {
			System.out.println(document);
		}
	}

	public static void ejercicio14() {
		// 14. Modifica la población de Huesca a 1.000.000.
		System.out.println(collection.updateOne(new Document("name", "Huesca"),
				new Document("$set", new Document("population", 1000000))));
	}

	public static void ejercicio15() {
		// 15. Incrementa la población de Elx en 666 personas.
		System.out.println(collection.updateOne(new Document("name", "Elx"),
				new Document("$inc", new Document("population", 666))));
	}

	public static void ejercicio16() {
		// 16. Reduce la cantidad de todas las ciudades de Andorra en 5 personas.
		System.out.println(collection.updateMany(new Document("country", "AD"),
				new Document("$inc", new Document("population", -5))));
	}

	public static void ejercicio17() {
		// 17. Modifica la ciudad de Gibraltar para que sea española (tanto el país como
		// la zona horaria). Ten en cuenta que hay
		// una ciudad americana con ese mismo nombre que no debe modificarse.
		System.out
				.println(collection.updateOne(new Document("name", "Gibraltar").append("timezone", "Europe/Gibraltar"),
						new Document("$set", new Document("country", "ES").append("timezone", "Europe/Madrid"))));
	}

	public static void ejercicio18() {
		// 18. Modifica todas las ciudades y añade un atributo tags que contenga un
		// array vacío.
		System.out.println(
				collection.updateMany(new Document(), new Document("$set", new Document("tags", new ArrayList<>()))));
	}

	public static void ejercicio19() {
		// 19. Modifica todas las ciudades españolas y añade al atributo tags el valor
		// sun.
		System.out.println(collection.updateMany(new Document("country", "ES"),
				new Document("$set", new Document("tags", "sun"))));
	}

	public static void ejercicio20() {
		// 20. Modifica el valor de sun de la ciudad A Coruña y sustitúyelo por rain.
		System.out.println(collection.updateOne(new Document("name", "A Coruña"),
				new Document("$set", new Document("tags", "rain"))));
	}

	public static void ejercicio21() {
		// 21. Renombra en las ciudades de Andorra, el atributo population por
		// poblacion.
		System.out.println(collection.updateMany(new Document("Country", "AD"),
				new Document("$rename", new Document("population", "poblacion"))));
	}

	public static void ejercicio22() {
		// 22. Elimina las coordenadas de Gibraltar (el "español", no el americano).
		System.out.println(collection.updateOne(new Document("name", "Gibraltar").append("timezone", "Europe/Madrid"),
				new Document("$unset", new Document("location", 1))));
	}

	public static void ejercicio23() {
		// 22. Elimina las coordenadas de Gibraltar (el "español", no el americano). DE PUTA MADRE QUE CREO QUE YA FUNCION
		System.out.println(collection.updateOne(new Document("name", "Huesca"),
				new Document("$unset", new Document("population", 1))));
	}

	public static void main(String[] args) {
		// ejercicio01();
		// ejercicio02_2();
		// ejercicio03();
		// ejercicio04();
		// ejercicio05();
		// ejercicio06();
		// ejercicio06_2();
		// ejercicio08();
		// ejercicio09();
		// ejercicio10();
		// ejercicio11();
		// ejercicio12();
		// ejercicio13();
		// ejercicio14();
		// ejercicio15();
		// ejercicio16();
		// ejercicio17();
		// ejercicio18();
		// ejercicio19();
		// ejercicio20();
		// ejercicio21();
		// ejercicio22();
		// ejercicio23();
	}

}
