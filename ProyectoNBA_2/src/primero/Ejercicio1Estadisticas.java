package primero;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Ejercicio1Estadisticas {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int codJugador;
		//1ero hacemos la conexion
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.printf("DATOS DEL JUGADOR: ");
		codJugador = s.nextInt();
		
		//2do hacemos la sentencia
//		String consultaHql = "from Estadisticas e, Jugadores j where e.jugadores.codigo = j.codigo and j.codigo = :id order by e.id.temporada";
//		Query consulta = session.createQuery(consultaHql).setParameter("id", codJugador);
//		
//		//3ero le ponemos un iterator para poder recorrer la lista
//		Iterator i = consulta.iterate(); //En vez de hacer un .list(), hacemos un iterate directamente, por que sabemos que el resultado
//										//va a ser una matriz, (un array, que guarda un array), la posicion 0 hace referencia a Estadisticas y la posicion 1 a Jugadores
//		int contador =0;
//		
//		if(i.hasNext()) {	//si hay un siguiente quiere decir que se ha creado la matriz
//			contador++;
//			Object [] array = (Object[]) i.next();
//			Estadisticas e = (Estadisticas) array[0];
//			Jugadores j = (Jugadores) array[1];
//			System.out.println("Nombre: "+j.getNombre());
//			System.out.println("Equipo: "+j.getEquipos().getNombre());
//			System.out.println("Temporada\tPtos\tAsis\tTap\tReb");
//			System.out.println("=====================================");
//			System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", e.getId().getTemporada(), e.getPuntosPorPartido(), e.getAsistenciasPorPartido()
//					,e.getTaponesPorPartido(), e.getRebotesPorPartido());
//			//ira recorriendo el iterador e ira cogiendo los datos de estadistica para ir imprimiendo linea a linea
//			while(i.hasNext()) {
//				contador++;
//				array = (Object[]) i.next();
//				e = (Estadisticas) array[0];
//				System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", e.getId().getTemporada(), e.getPuntosPorPartido(), e.getAsistenciasPorPartido()
//									,e.getTaponesPorPartido(), e.getRebotesPorPartido());			
//				}			
//			System.out.println("================================================");
//			System.out.println("Num de registros: " +contador);
//			System.out.println("================================================");
//			}
//		else {
//			System.out.println("No existe el jugador con codigo: "+codJugador);
//		}
		
		//Forma 2
		Jugadores jugador;
		Set lista;
		
		jugador = (Jugadores) session.load(Jugadores.class, (int) codJugador);
		lista = jugador.getEstadisticases();
		Iterator iterador = lista.iterator();
		
		System.out.println("Nombre : "+jugador.getNombre());
		System.out.println("Equipo : "+jugador .getEquipos().getNombre());
		System.out.println("Temporada	Ptos	Asis	Tap	Reb");
		System.out.println("================================================");
		while(iterador.hasNext()) {
			Estadisticas estaditicas = (Estadisticas) iterador.next();
			System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", estaditicas.getId().getTemporada(), estaditicas.getPuntosPorPartido(), estaditicas.getAsistenciasPorPartido()
							,estaditicas.getTaponesPorPartido(), estaditicas.getRebotesPorPartido());
		}

	}//main
}//class
