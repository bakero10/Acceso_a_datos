package Anexo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaSociosBaloncesto {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "nacarino";
	
	public static Connection conecta;
	static ArrayList <Socio> lista;

	public static void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
		
	}
	
	public static void desconectar() throws SQLException {
		conecta.close();
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

			conectar();	
		
			ArrayList<Socio> lista = new ArrayList<>();
			int contador = 0;
			try {
				String sql = "SELECT * FROM socio";
				PreparedStatement ps = conecta.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Socio socio = new Socio(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getString(5));
					lista.add(socio);
				}
				ps.close();
			} catch (Exception e) {
				System.out.println("Error "+e.getMessage());
			}
			System.out.println("--------------> LISTA DE SOCIOS <----------");
			for (Socio socio : lista) {
				System.out.println(socio);
				contador++;
			}
			System.out.println("---------------------------------------------");
			System.out.println("El numero de socios introducidos es: "+contador);
			
			desconectar();
			
}
}
