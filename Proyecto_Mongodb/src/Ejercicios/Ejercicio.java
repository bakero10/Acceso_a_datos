package Ejercicios;

import java.util.Iterator;
import java.util.TreeSet;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class Ejercicio {

	private static MongoClient mongo;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;

	// conectar
	public static void conectar() {
		mongo = new MongoClient("localhost", 27017);
		database = mongo.getDatabase("test");
		collection = database.getCollection("ciudades");
	}

	// desconectar
	public static void desconectar() {
		mongo.close();
	}

	private static boolean insertarCiudad(Ciudad ciudad) {
		try {
			Document document = new Document("name", ciudad.getName()).append("country", ciudad.getCountry())
					.append("timezone", ciudad.getTimezone()).append("population", ciudad.getPopulation())
					.append("location",
							new Document("longitude", ciudad.getLongitude()).append("latitude", ciudad.getLatitude()));
			collection.insertOne(document);
			return true;
		} catch (Exception e) {
			System.out.println("Error en la inserción");
			return false;
		}
	}

	private static void listarCiudades() {
		FindIterable<Document> lista = collection.find();
		for (Document d : lista) {
			System.out.println(d.getString("name"));
		}
	}
	
	private static void listarCiudadesPais(String pais) {
		FindIterable<Document> lista = collection.find();
		for (Document d : lista) {
			if(d.getString("country").equals(pais)) {
				System.out.println(d.getString("name"));
			}
		}
	}
	
	private static void listarPaises() {
		TreeSet<String> ts = new TreeSet<>();

        FindIterable<Document> resultDocument = collection.find().projection(Projections.include("name"))
                .projection(Projections.exclude("_id", "name", "timezone", "population", "location"));
        
        Iterator<Document> it = resultDocument.iterator();
        while (it.hasNext()) {
            ts.add(it.next().getString("country"));
        }
        
        for (String s : ts) {
            System.out.println(s);
        }
	}
	
	public static void main(String[] args) {
		conectar();
		// System.out.println(insertarCiudad(new Ciudad("Huesca","ES","Europe/Madrid",60000,0,42)));
		// listarCiudades();
		// listarCiudadesPais("ES");
		listarPaises();
		desconectar();

	}

}
