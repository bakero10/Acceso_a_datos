package AnexosIII_AnexosIV_ProcedimientosMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ESTE PAQUETE NOS ENSE�A EL MODELO VISTA CONTROLADOR
//El modelo seria la clase Bd, que es lo que nosotros hemos hecho hasta ahora que lo llamabamos el modelo de negocio
//en este ejemplo el que creo este paquete que copio pablo, creo todos los metodos en vez de en java, los creo como procedimietnos y funciones en 
//la base de datos sql kadummvc.sql

//La vista sera donde crearemos la interfaz

//la clase controlador es el encargado de controlar todo para que funcionen los metodos, etc...

public class Bd {
	private String maquina = "localhost";
	private String usuario = "root";
	private String clave = "root";
	private int puerto = 3307;
	private String servidor = "";
	private static Connection conexion = null;

	// CONSTRUCTOR
	// Recibe el nombre de la base de datos
	public Bd(String baseDatos) {
		this.servidor = "jdbc:mysql://" + this.maquina + ":" + this.puerto + "/" + baseDatos + "?useSSL=false" + "&serverTimezone=CET";;

		// Registrar el driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("ERROR AL REGISTRAR EL DRIVER");
			System.exit(0); // parar la ejecuci�n
		}

		// Establecer la conexi�n con el servidor
		try {
			conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);
		} catch (SQLException e) {
			System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
			System.exit(0); // parar la ejecuci�n
		}
		System.out.println("Conectado a " + baseDatos);
	}

	// Devuelve el objeto Connection que se usar� en la clase Controller
	public static Connection getConexion() {
		return conexion;
	}

}