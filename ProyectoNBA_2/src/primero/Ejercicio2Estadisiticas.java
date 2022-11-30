package primero;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Ejercicio2Estadisiticas {

	public static void main(String[] args) {
		Scanner s = new Scanner (System.in);
		
		// 1 conectamos.
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		
		System.out.printf("DATOS DEL JUGADOR: ");
		int codigoJug  = s.nextInt();
		
		Jugadores jugador;
		Set lista;
		
		jugador = (Jugadores) sesion.load(Jugadores.class, codigoJug);
		lista = jugador.getEstadisticases();
		Iterator iterator = lista.iterator();
		
		
		System.out.println("Nombre : "+jugador.getNombre());
		System.out.println("Equipo : "+jugador.getEquipos().getNombre());
		System.out.printf("Temporada\tPtos\tAsis\tTap\tReb\n");
		System.out.println("===========================================");
		while(iterator.hasNext()) {		//mientras haya un siguiente
			Estadisticas estadistica = (Estadisticas) iterator.next();	//creamos estadistica y buscamos
			System.out.printf("%s\t\t %.1f\t %.1f\t %.1f\t %.1f \n",estadistica.getId().getTemporada(),estadistica.getPuntosPorPartido()
												,estadistica.getAsistenciasPorPartido(),estadistica.getTaponesPorPartido()
												,estadistica.getRebotesPorPartido());
		}
	}

}
