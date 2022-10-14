package Ud10_sql_JDBC_Sesion1.Ejercicios_PDF.Ejercicio03;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AccesoBdatos {

	/*
	 * rs.next()
	 * rs.first()
	 * rs.last()
	 * rs.absolute(posicion)
	 * rs.getRow()
	 * */
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	private Connection conecta; //Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo en el metodo conecta 
	

	public void conectar() {
		try {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password); //Cuando ejecutemos conectar, iniciaremos todos los pasos para conectar
		}catch (ClassNotFoundException cnf) {
			System.out.println("Clase driver no encontrada");
		}catch (SQLException sqle) {
			System.out.println("Error SQL en la conexion");
		}
	}
	
	public void desconectar() {
		try {
		if (conecta !=null) { //Si en conecta hay algo, entonces desconecta. Con esto tenemos un metodo que cierre la conexion
			conecta.close();
		}
		}catch (SQLException sqle) {
			System.out.println("Error al desconectar");
		}
	}
	
public void actualizarDatos(int socioID, String nombre, int estatura, int edad, String localidad) {
		try {
			PreparedStatement ps = conecta.prepareStatement("update socio set nombre = ? , estatura = ?, edad = ?, localidad = ? where socioID = ?");
			ps.setString(1, nombre);
			ps.setInt(2, estatura);
			ps.setInt(3, edad);
			ps.setString(4, localidad);
			ps.setInt(5, socioID);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Socio con id "+socioID+" actualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
		
	public ResultSet consultaLocalidad(String localidad){
		ResultSet salida = null;
		if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
			try {
			Statement consulta = conecta.createStatement();
			ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
				
			if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
				salida = reg;
			}else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
				salida = null;
			}
			}catch(SQLException sqle) {
				System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
			}
		}else {//Si esta vacio ejecuta consulta que devuelva todos los usuarios
			try {
				Statement consulta = conecta.createStatement();
				ResultSet reg = consulta.executeQuery ("SELECT * FROM socio");
					
				if (reg.next()) { //Si hay siguiente devolvera un result set con todos los socios
					salida = reg;
				}else {
					salida = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
				}catch(SQLException sqle) {
					System.out.println("Error en la consulta para mostrar todos los socios");
				}
		}
		return salida;
	}
	
}

//probar la logica en el main antes
