package primero;

import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EjercicioEstadisticasSanti {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int codJugador;
		int contador = 0;
		//1ero hacemos la conexion
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.printf("DATOS DEL JUGADOR: ");
		codJugador = s.nextInt();
		
		Jugadores jugador;
		Set lista;
		
		jugador = (Jugadores) session.load(Jugadores.class, codJugador);
		lista = jugador.getEstadisticases();
		
		System.out.println("Nombre: "+jugador.getNombre());
		System.out.println("Equipo: "+jugador.getEquipos().getNombre());
		System.out.println("Temporada	Ptos	Asis	Tap	Reb");
		System.out.println("================================================");
		for (Object object : lista) {
			contador++;
			Estadisticas es = (Estadisticas) object;
			System.out.println(es.getId().getTemporada()+"\t"+es.getPuntosPorPartido()+"\t"+es.getAsistenciasPorPartido()+"\t"+es.getTaponesPorPartido()+"\t"+es.getRebotesPorPartido());
		}

		System.out.println("================================================");
		System.out.println("Num de registros: "+contador);
		System.out.println("================================================");
	}
}
