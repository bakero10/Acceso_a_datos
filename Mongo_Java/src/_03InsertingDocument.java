import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
public class _03InsertingDocument {
	public static void main( String args[] ) {
	
	// Crear cliente de mongo
	MongoClient mongo = new MongoClient( "localhost" , 27017 );
	
	// Acceso a la base de datos
	MongoDatabase database = mongo.getDatabase("myDb");
	
	// Crear tablas
	//database.createCollection("sampleCollection");
	//System.out.println("Collection created successfully");
	
	// Recuperando una colección
	MongoCollection<Document> collection = database.getCollection("sampleCollection");
	System.out.println("La colección -- sampleCollection -- seleccionada satisfactoriamente¡");
    Document document = new Document("Titulo", "MongoDB")
	.append("Descripción", "database")
	.append("likes", 100)
	.append("url", "http://www.tutorialspoint.com/mongodb/")
	.append("by", "tutorials point");
	
	//Insertar documento en la colección
	collection.insertOne(document);
	System.out.println("Documento insertado satisfactoriamente¡");
	mongo.close();

}
}