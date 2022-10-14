package Ud10_sql_JDBC_Sesion1.Ejercicios_PDF.Ejercicio03;



import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		AccesoBdatos abd = new AccesoBdatos();
		
		
		abd.conectar();
		ResultSet rs = abd.consultaLocalidad("Malaga");
		
		//System.out.println(rs);
		
		while(rs.next()) {
			int socioID = rs.getInt("socioID");
			String nombre = rs.getString("nombre");
			int estatura = rs.getInt("estatura");
			int edad = rs.getInt("edad");
			String localidad = rs.getString("localidad");
			
			System.out.println("Id socio: " + socioID + " Nombre: " + nombre + " Estatura: " + estatura + " Edad: " + edad + " Localidad: " + localidad);
		}
		
		abd.desconectar();

	}

}
