package paquete;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		String url ="jdbc:mysql://localhost:" + 3306 + "/" + "tutorialDb" + "?useSSL=false" + "&serverTimezone=CET";
			BasicConnectionPool connectionPool = BasicConnectionPool.create(url, "root", "root");
			
			Connection con1 = connectionPool.getConnection();
			Connection con2 = connectionPool.getConnection();
			System.out.println("Tamaño Pool conexiones: " + connectionPool.getConnectionPool().size());
			System.out.println("Numero conexiones utilizadas: " +connectionPool.getUsedConnections().size());
     		System.out.println(connectionPool.getUsedConnections()); //Esto te muestra las conexiones que hay actualmente, con ese nombre raro es como se identifican
			Connection con3 = connectionPool.getConnection();
			Connection con4 = connectionPool.getConnection();
			Connection con5 = connectionPool.getConnection();
		//	connectionPool.releaseConnection(con4);
		//	Connection con6 = connectionPool.getConnection();
			
			
			
			
	}	

}
