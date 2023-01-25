
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  
public class _00ConnectToDB { 
   
   public static void main( String args[] ) {  
      
      // Crear cliente de Mongo
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
      
      // DE MOMENTO NO SE USA
      // Crear credencial 
      MongoCredential credential; 
      credential = MongoCredential.createCredential("alberto", "myDb","carrera".toCharArray()); 
      System.out.println("Conexion con la base de datos realizada¡");  
      
      // Acceso a la base de datos
      MongoDatabase database = mongo.getDatabase("myDb"); 
      System.out.println("Credencial ::"+ credential);    
      mongo.close();
   } 
}