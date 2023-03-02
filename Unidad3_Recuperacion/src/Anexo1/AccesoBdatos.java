package Anexo1;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AccesoBdatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "nacarino";
	
	public Connection conecta;
	static ArrayList <Socio> lista;

	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
		
	}
	
	public void desconectar() throws SQLException {
		conecta.close();
	}
	
	public ArrayList <Socio> consultarTodos(){
		lista = new ArrayList<Socio> ();
		try {
			String sql = "SELECT * FROM Socio";
			PreparedStatement consulta = conecta.prepareStatement(sql);
			ResultSet reg = consulta.executeQuery();
			while (reg.next ()) {
				Socio socio= new Socio(reg.getInt (1), reg.getString (2), reg.getInt (3), reg.getInt(4), reg.getString(5));
				lista.add(socio);
		}
		consulta.close ();
		} catch (SQLException e) {
			return null;
		} 
		return lista;
		}
	
	public boolean exportarJson() {
		
		try {
			FileWriter fw = new FileWriter("Ficheros/socios.json");
			new Gson().toJson(lista,fw);
			fw.close();
			return true;
			
		} catch (IOException e) {
			return false;
		}
	}
	
	public ArrayList<Socio> consultarLocalidad(String localidad) throws SQLException {
		ArrayList<Socio> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM Socio WHERE localidad LIKE '"+localidad+"'";
		PreparedStatement ps = conecta.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Socio socio = new Socio(rs.getInt (1), rs.getString (2), rs.getInt (3), rs.getInt(4), rs.getString(5));
			lista.add(socio);
		}
		ps.close();
		return lista;
	}
}
