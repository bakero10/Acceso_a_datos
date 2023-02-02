package Ejercicio29;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Ejercicio29 {

	/*
		29.- Codifica una clase con su método main() para realizar la consulta del ejercicio 26 desde Java. Resultado:

		Document{{nombre=Madrid, poblacion=3255944}}
		Document{{nombre=Barcelona, poblacion=1621537}}
		Document{{nombre=Valencia, poblacion=814208}}
	 */
	public static void main(String[] args) {
		
				MongoClient mongo = new MongoClient("localhost", 27017);
				MongoDatabase database = mongo.getDatabase("test");
				MongoCollection<Document> collection = database.getCollection("ciudades");
				
				ArrayList<Document> lista = new ArrayList<>();
				Document documento = new Document("$match",new Document("country","ES"));
				Document documento1 = new Document("$project",new Document("nombre","$name").append("poblacion", "$population"));
				Document documento2 = new Document("$sort",new Document("poblacion",-1));
				Document documento3 = new Document("$limit",3);
				
				lista.add(documento);
				lista.add(documento1);
				lista.add(documento2);
				lista.add(documento3);
				
				AggregateIterable<Document> imprimir = collection.aggregate(lista);
				Iterator it = imprimir.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				mongo.close();
	}

}
