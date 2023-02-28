package AnexosIII_AnexosIV_ProcedimientosFunciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

import com.mysql.cj.protocol.Resultset;

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
	private static String port = "3307";
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
	
	
	
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
		
	public ResultSet consultaLocalidad(String localidad){
		ResultSet salida = null;
		 //Si localidad es distinto de vacio ejecuta lo de dentro
			try {
			Statement consulta = conecta.createStatement();
			ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
			
//			LO HEMOS EDITADO PARA QUE AHOIRA LA CONSULTA SE HAGA A TRAVES DE UNA SENTENCIA PREPARADA EN LA BASE DE DATOS
//			CallableStatement consulta = conecta.prepareCall("CALL buscarLocalidad(?)");
//			consulta.setString(1, localidad);
//			ResultSet reg = consulta.executeQuery();
				
			if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
				salida = reg;
			}else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
				salida = null;
			}
			}catch(SQLException sqle) {
				System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
			}
		
		return salida;
	}
	

	//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL A�O DAM2 PARA ACTUALIZAR, BORRAR Y A�ADIR SOCIOS
	public int actualizarSocio(int id,String nombre, int estatura, int edad, String localidad) {
		try {
			String sql = "update socio set nombre=?, estatura=?, edad=?, localidad=? where socioID=?";
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setString(1,nombre);
			consulta.setInt(2,estatura);
			consulta.setInt(3,edad);
			consulta.setString(4,localidad);
			consulta.setInt(5,id);
			
			return (consulta.executeUpdate());
			
		} catch (SQLException e) {
			return 0;
		}
		
	}// del metodo actualizarSocio
	
	
	
	
	public int aniadirSocio(String nombre, int estatura, int edad, String localidad) {
		try {
			//Hacemos una consulta para ver cual es el ultimo id y le sumaremos 1
			int ultimoID=0;
			PreparedStatement consultaUltimoID = conecta.prepareStatement("select socioID from socio order by socioID desc limit 1");
			ResultSet rs = consultaUltimoID.executeQuery();
			//rs.next(); Acordarse de hacer esto para que pueda leer el registro de la primera posicion para luego poder hacerle un rs.getInt o .getString...
			rs.next();
			ultimoID = rs.getInt(1)+1;
			
			PreparedStatement consulta = conecta.prepareStatement("insert into socio values (?,?,?,?,?)");
			consulta.setInt(1, ultimoID);
			consulta.setString(2, nombre);
			consulta.setInt(3, estatura);
			consulta.setInt(4, edad);
			consulta.setString(5, localidad);
			
			return consulta.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}// del metodo aniadirSocio
	
	
	
	public int borrarSocio(int id) {
		try {
			String sql = "delete from socio where socioID=?;";
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setInt(1, id);
			
			return (consulta.executeUpdate());
		} catch (SQLException e) {
			return 0;
		}
	}//del metodo borrarSocio
	
	
}

//probar la logica en el main antes
