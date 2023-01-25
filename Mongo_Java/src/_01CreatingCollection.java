import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  
public class _01CreatingCollection { 
   
   public static void main( String args[] ) {  
      
      // Creating a Mongo client 
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
     
      // Crear las credenciales
      MongoCredential credential; 
      credential = MongoCredential.createCredential("alberto", "myDb", 
         "carrera".toCharArray()); 
      System.out.println("Connected to the database successfully");  
      
      //Acceso a la base de datos
      MongoDatabase database = mongo.getDatabase("myDb");  
      
      //Creacion de tablas 
      database.createCollection("TablaNueva"); 
      System.out.println("Collection created successfully"); 
      mongo.close();
   } 
} 