package Sesion_1_Manejo_de_conectores_DB;

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
	private static String database;
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url;
	private static String username = "root";
	private static String password = "root";

	public Connection conecta;

	public void conectar(String db) {
		try {
			database = db;
			url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	

	//Creamos un metodo que lea los datos y los convierta a un archivo json
	public void crearArchivoJson() {
		
			ArrayList<Socio>lista = new ArrayList<Socio>();
		
			try {
				PreparedStatement consulta = conecta.prepareStatement("Select * from socio");
				ResultSet reg = consulta.executeQuery();
				while (reg.next ()) {
					lista.add(new Socio(reg.getInt (1), reg.getString (2), reg.getInt (3), reg.getInt(4), reg.getString(5)));
				}
			consulta.close ();
			System.out.println("Base de datos leida...");
			} catch (SQLException e) {
				System.out.println("Error al crear la sentencia preparada");
			} 
			

			try {
			FileWriter fw = new FileWriter("Ficheros\\socios.json");
			new Gson().toJson(lista, fw);
			fw.close();
			System.out.println("Archivo Json creado...");
			
			}catch (IOException e) {
				System.out.println("Error a la hora de crear el fichero .json");
			}	
	}
	
	
	

	public void desconecta() {
		try {
			if (conecta != null) {
				conecta.close();
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar desconectar");
		}

	}

}// de la clase
