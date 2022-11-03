package Anexos_III_IV_Explicacion_SentenciasPreparadasFunciones;
import java.sql.*;
// Alberto Carrera Mart�n - 20 de Marzo de 2020
// Demo de llamada al procedimiento pesetasAeuros almacenado en MySQL 
// Este procedimiento ya se encuentra tambi�n compilado en MySQL despu�s de lanzar 
// el script "script_procedimientosMySQL.sql". De todas formas al final de esta clase
// se encuentra el c�digo fuente del mismo


public class Main3 {
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "procedimientos";
    private static String hostname = "localhost";
    private static String port = "3307";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database +
									"?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";
    
	public static void main(String[] args) 
     throws ClassNotFoundException, SQLException {
		
	     Class.forName(driver);
		 Connection conn = DriverManager.getConnection(url, username, password);
         CallableStatement proc = conn.prepareCall(" CALL pesetasAeuros(?) ");
         double cantidad = 25000; // Quiero convertir 25000 pesetas a euros
         //Acoplamiento. El par�metro o argumento 1� es un real, en concreto cantidad
         proc.setDouble(1,cantidad); 
         // El par�metro 1� tambi�n es a su vez de salida y evidentemente de tipo real
         proc.registerOutParameter(1, Types.DOUBLE);
         // Ejecuto el procedimiento
         proc.execute();            
         System.out.print("Convertir 25.000 pesetas a euros -->");
         // Imprimo el argumento de salida
         System.out.println(proc.getDouble(1));
       
    proc.close();   
	conn.close();
  }
	/*
	 * CREATE PROCEDURE pesetasAeuros (INOUT p_p1 DECIMAL(7,2) )
	BEGIN
  		SET p_p1=p_p1 / 166.386;
	END $$
	 */
}// de Main3
