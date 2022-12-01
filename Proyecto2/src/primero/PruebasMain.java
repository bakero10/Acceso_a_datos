package primero;



import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		AccesoBdatos abd = new AccesoBdatos();
		
		
		abd.conectar();
		
		//System.out.println(rs);
		
		//abd.actualizarDatos(0, null, 0, 0, null);
		
		abd.desconectar();

	}

}
