package Ejercicio_24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Ejercicio24 {
	static MongoClient mongo = new MongoClient( "localhost" , 27017 );
	static MongoDatabase database = mongo.getDatabase("test");
	static MongoCollection<Document> collection = database.getCollection("ciudades");
	
	
	public static void main(String[] args) {
		
		Ciudad c =  new Ciudad("Huesconsin","ES","Europe/Madrid",43000,234432343,223443435);
		System.out.println(insertaCiudad(c));
		//listarCiudades();
		//listarCiudadesPais("ES");
		//listarPaises();
	}
		private static boolean insertaCiudad(Ciudad ciudad) {
		
		try {
			Document d = new Document("name",ciudad.getName())
					.append("country",ciudad.getCountry())
					.append("timezone",ciudad.getTimezone())
					.append("location",ciudad.getLongitude())
					.append("latitude",ciudad.getLatitude())
					.append("Poblacion",ciudad.getPopulation());
			
			
			collection.insertOne(d);
			return true;

		} catch (Exception e) {
			return false;
		}
		
	}
		private static void listarCiudades() {
			
			ArrayList<Document> lista = new ArrayList<>();
			Document documento1 = new Document("$project",new Document("nombre","$name").append("_id", 0));
			
			lista.add(documento1);
			
			AggregateIterable<Document> imprimir = collection.aggregate(lista);
			Iterator it = imprimir.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
			mongo.close();
		}
		private static void listarCiudadesPais(String pais) {
			
			ArrayList<Document> lista = new ArrayList<>();
			Document d = new Document("$match",new Document("country",pais));
			Document d1 = new Document("$project",new Document("_id",0).append("ciudad","$name"));
			
			lista.add(d);
			lista.add(d1);
			
			AggregateIterable<Document> aggregate = collection.aggregate(lista);
			Iterator it = aggregate.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
			mongo.close();
		}
		private static void listarPaises() {
			
			ArrayList<Document> lista = new ArrayList<>();
			Document d = new Document("$group",new Document("_id","$country"));
			Document d1 = new Document("$project",new Document("_id",1));
			Document d2 = new Document("$sort",new Document("_id",1));
			lista.add(d);
			lista.add(d1);
			lista.add(d2);
			
			AggregateIterable<Document> ag = collection.aggregate(lista);
			Iterator it = ag.iterator();
			
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			
			
		}
			
}
