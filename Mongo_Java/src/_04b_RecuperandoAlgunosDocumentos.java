// Alberto Carrera Mart�n - Diciembre 21

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class _04b_RecuperandoAlgunosDocumentos {

	public static void main(String args[]) {

		// Crear cliente de mongo
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Crear credenciales
		//	MongoCredential credential;
		//	credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
		System.out.println("Conectado a la base de datos");

		// Acceso a la base de datos
		MongoDatabase database = mongo.getDatabase("myDb");

		// Recuperando una colección
		MongoCollection<Document> collection = database.getCollection("sampleCollection");
		System.out.println("Colecci�n sampleCollection seleccionada");
		//
		ArrayList<String> al = new ArrayList<String>();
		Document criterios = new Document("description", "database")
		.append("likes", new Document("$gte", 50));

		FindIterable<Document> resultDocument = collection.find(criterios);
//			.projection(Projections.include("description",...."))
//			.projection(Projections.exclude("_id", "by",...."))
//			.sort(new Document("likes",1));

		Iterator<Document> it = resultDocument.iterator();
		while (it.hasNext()) {
			al.add(it.next().getString("title"));
		}

		for (String s : al)
			System.out.println(s);
				
		//---------Documento Entero--------
		//---------------------------------
		ArrayList<Document> ald = new ArrayList<Document>();
		resultDocument = collection.find(criterios);
		it = resultDocument.iterator();
		while (it.hasNext()) {
			ald.add(it.next());
		}

		for (Document d : ald)
			System.out.println(d);
		mongo.close();

	}
}