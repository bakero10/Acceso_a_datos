package Anexo1;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		AccesoBdatos abd = new AccesoBdatos();
		abd.conectar();
		//System.out.println(abd.consultarTodos());
		//System.out.println(abd.exportarJson());
		//System.out.println(abd.consultarLocalidad("Málaga"));
		abd.desconectar();
	}

}
