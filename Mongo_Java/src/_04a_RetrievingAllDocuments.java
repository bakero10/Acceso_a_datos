import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
public class _04a_RetrievingAllDocuments {
	
	
	
	public static void main( String args[] ) {
	
		// Crear cliente de mongo
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
		// Crear credenciales
//		MongoCredential credential;
//		credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
		System.out.println("Connected to the database successfully");
		
		// Acceso base de datos
		MongoDatabase database = mongo.getDatabase("myDb");
		
		// Recuperando una colección
		MongoCollection<Document> collection = database.getCollection("sampleCollection");
		System.out.println("Collection sampleCollection selected successfully");
		
		// DOCUMENTO 1
		// El documento 1 se ha creado en la clase anterior
			/* Document document1 = new Document("title", "MongoDB")
			.append("description", "database")
			.append("likes", 100)
			.append("url", "http://www.tutorialspoint.com/mongodb/")
			.append("by", "tutorials point");
			*/
		//DOCUMENTO 2
		Document document2 = new Document("title", "RethinkDB")
		.append("description", "database")
		.append("likes", 200)
		.append("url", "http://www.tutorialspoint.com/rethinkdb/")
		.append("by", "tutorials point");
		
		
		List<Document> list = new ArrayList<Document>();
		//list.add(document1);
		list.add(document2);
		collection.insertMany(list);
		
		// Obtener el objeto iterable
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;
		
		// Obtener el iterador
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
		mongo.close();
	}
}