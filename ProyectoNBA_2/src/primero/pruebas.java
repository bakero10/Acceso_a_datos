package primero;

import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class pruebas {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String nombre = "";
		//1ero hacemos la conexion
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.printf("DATOS DEL JUGADOR: ");
		nombre = s.next();
		
		Equipos equipo;
		Set lista;
		
		equipo = (Equipos) session.load(Equipos.class, nombre);
		
		System.out.println(equipo.getConferencia());
		System.out.println(equipo.getDivision());
		System.out.println(equipo.getCiudad());
		
		

	}

}
