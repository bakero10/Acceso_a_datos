import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 
import org.bson.Document; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  

public class _02selectingCollection { 
   
   public static void main( String args[] ) {  
      
      // Crear cliente de mongo
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
     
      // Crear credenciales 
      MongoCredential credential; 
      credential = MongoCredential.createCredential("alberto", "myDb","carrera".toCharArray()); 
      System.out.println("Connected to the database successfully");  
      
      // Acceso a la base de datos
      MongoDatabase database = mongo.getDatabase("myDb");  
      
      // Crear tablas
      database.createCollection("myCollection"); 
      System.out.println("Collection created successfully"); 
      
      // Recuperando una colección
      MongoCollection<Document> collection = database.getCollection("myCollection"); 
      System.out.println("La colección -- myCollection -- seleccionada satisfactoriamente¡"); 
      mongo.close();
   }
}