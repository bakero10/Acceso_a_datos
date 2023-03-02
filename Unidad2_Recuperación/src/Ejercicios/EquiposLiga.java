package Ejercicios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class EquiposLiga {

	public static void main(String[] args) {
		
//		Liga liga = new Liga();
//		
//		liga.add(new Equipo("Barcelona","Xavi Hernandez",1923,78));
//		liga.add(new Equipo("Real Madrid","Ancceloti",1917,33));
//		liga.add(new Equipo("Atletico Madrid","Diego Pablo Simeone",1943,50));
//		liga.add(new Equipo("SD Huesca","Cuco Ziganda",1967,157));
//		
//		try {
//			XStream xstream = new XStream(new DomDriver("UTF-8"));
//			xstream.alias("LigaSantander", Liga.class);
//			xstream.alias("Equipos", Equipo.class);
//			xstream.addImplicitCollection(Liga.class, "lista"); // le metemos la lista 
//			xstream.toXML(liga, new FileOutputStream("Ficheros/futbol.xml"));
//			System.out.println("Creando fichero..");
//		} catch (Exception e) {
//			System.out.println("Error de escritura "+e.getMessage());
//		}
		
		//PARA CREAR UN ARCHIVO GSON
		List<Equipo> liga = Arrays.asList(
				new Equipo("Barcelona","Xavi Hernandez",1923,78),
				new Equipo("Real Madrid","Ancceloti",1917,33),
				new Equipo("Atletico Madrid","Diego Pablo Simeone",1943,50),
				new Equipo("SD Huesca","Cuco Ziganda",1967,157)
				);
		
		try {
			FileWriter fw = new FileWriter("Ficheros/liga.json");
			new Gson().toJson(liga,fw);
			fw.close();
			System.out.println("Creado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
