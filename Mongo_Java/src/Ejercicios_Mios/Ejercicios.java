package Ejercicios_Mios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Ejercicios {

	static MongoClient mongo = new MongoClient("localhost", 27017);
	static MongoDatabase database = mongo.getDatabase("test");
	static MongoCollection<Document> collection = database.getCollection("ciudades");

	public static void ejercicioInventado1() {

		/*
		 * busca en las distintas zonas del pais en numero total de ciudades que se
		 * encuentran en esa zona.
		 */

		// ATENCION A COMO INSERTAR UN ARRAY DE STRING

		ArrayList<Document> lista = new ArrayList<>();
		List<String> countries = Arrays.asList("ES", "AD");

		Document d = new Document("$match", new Document("country", new Document("$in", countries)));
		Document d1 = new Document("$group",
				new Document("_id", "$timezone").append("totalZona", new Document("$sum", 1)));
		Document d2 = new Document("$project",
				new Document("_id", 0).append("zona", "$_id").append("totalZona", "$totalZona"));

		lista.add(d);
		lista.add(d1);
		lista.add(d2);

		AggregateIterable<Document> aggregate = collection.aggregate(lista);
		MongoCursor<Document> it = aggregate.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	public static void ejercicioInventado2() {
		/*
		 * muestra la longitud y la latitud de huesca.
		 */ 
		FindIterable<Document> documento = collection.find(new Document("name", "Huesca"))
													 .projection(new Document("_id", 0)
													 .append("nombre", "$name")
													 .append("longitud", "$location.longitude")
													 .append("latitud", "$location.latitude"));
		Iterator<Document> iterator = documento.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toJson());
			
		}
	}
	
	public static void ejercicioInventado3() {
		
		FindIterable<Document> documento = collection.find(new Document("country","AD"));
		Iterator<Document> iterator = documento.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	public static void ejercicioInventado4() {
		
		//muestra el total de la poblacion que hay en cada zona de europa y ordenalos 
		//por maxima poblacion a menos
		
		//ATENCION A COMO SE PONE EL PATTERN EN UNA CONSULTA EN JAVA CON MONGO
		
		ArrayList<Document> lista = new ArrayList<>();
		
		Document d = new Document("$match",new Document("timezone",java.util.regex.Pattern.compile("^Europe.*")));
		Document d1 = new Document("$group",new Document("_id","$timezone").append("poblacion",new Document("$sum",1)));
		Document d2 = new Document("$project",new Document("_id",0).append("poblacion","$poblacion").append("zona","$_id"));
		Document d3 = new Document("$sort",new Document("poblacion",-1));
		
		lista.add(d);
		lista.add(d1);
		lista.add(d2);
		lista.add(d3);
		
		AggregateIterable<Document> documento = collection.aggregate(lista);
		MongoCursor<Document> it = documento.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) {

		//ejercicioInventado1();
		//ejercicioInventado2();
		//ejercicioInventado3();
		//ejercicioInventado4();
	}
}
