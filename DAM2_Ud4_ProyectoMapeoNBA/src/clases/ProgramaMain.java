package clases;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ProgramaMain {

	public static void main(String[] args) {

		//++++++++++++++++++++++++El ejercicio se puede resolver de varias maneras...
		//1� siguiendo el ejemplo de la clase HQLClasesNoasociadas17.java jugamos con una matriz para ver lo que seria un simil a los join en sql.
		
		//2� Con una consulta hql que contiene un join
		
		//3 Cargando el jugador y preguntando por sus atributos, esto es posible gracias a que al mapear las tablas se han creado las relaciones por sus
		//atributos
				
		
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		
		Scanner s = new Scanner(System.in);
		System.out.print("DATOS DEL JUGADOR: ");
		int idJugador = s.nextInt();
	
		//Forma 1
		//Le decimos que nos recupere Estadisticas y Jugadores en una lista, pero solo los que cumplan la condicion del where
		String ConsultaConHql = "from Estadisticas e, Jugadores j where e.jugadores.codigo = j.codigo and j.codigo = :id order by e.id.temporada";
		Query consulta = sesion.createQuery(ConsultaConHql).setParameter("id", idJugador);
		//List<Object[]> filas = consulta.list(); //de esta manera podriamos hacerlo con una lista directamente, sin iterate
		Iterator i = consulta.iterate(); //En vez de hacer un .list(), hacemos un iterate directamente, por que sabemos que el resultado 
										//va a ser una matriz, (un array, que guarda un array), la posicion 0 hace referencia a Estadisticas y la posicion 1 a Jugadores
		
		int contador = 0;
		if(i.hasNext()) {//Si hay siguiente quiere decir que se ha creado la matriz, con lo que continuamos, sino, salta al else
			contador++;
			Object[] array = (Object[]) i.next();
			Estadisticas es = (Estadisticas) array[0];
			Jugadores ju = (Jugadores) array[1];
			
			System.out.println("\n\n++++++++ FORMA 1 ++++++++++++++++++++");
			System.out.println("Nombre : "+ju.getNombre());
			System.out.println("Equipo : "+ju.getEquipos().getNombre());
			System.out.println("Temporada	Ptos	Asis	Tap	Reb");
			System.out.println("================================================");
			System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", es.getId().getTemporada(), es.getPuntosPorPartido(), es.getAsistenciasPorPartido()
					,es.getTaponesPorPartido(), es.getRebotesPorPartido());
		
			//ira recorriendo el iterador e ira cogiendo los datos de estadistica para ir imprimiendo linea a linea
			while(i.hasNext()) {
				contador++;
				array = (Object[]) i.next();
				es = (Estadisticas) array[0];	
				System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", es.getId().getTemporada(), es.getPuntosPorPartido(), es.getAsistenciasPorPartido()
						,es.getTaponesPorPartido(), es.getRebotesPorPartido());
			}
			System.out.println("================================================");
			System.out.println("Num de registros: " +contador);
			System.out.println("================================================");
			
		}else {
			System.out.println("El id del jugador introducido no existe");
		}
		
		System.out.println("\n\n++++++++ FORMA 2 ++++++++++++++++++++");
		
		
		
		
		/*
		//Forma 2, no consigo que funcione la consulta
		String ConsultaConSql = "select e.temporada, e.Puntos_por_partido, e.Asistencias_por_partido, "
				+ "e.Tapones_por_partido, e.Rebotes_por_partido from Jugadores as j join Estadisticas as e "
				+ "on j.codigo = e.jugador"
				+ "where j.codigo = :id "
				+ "group by e.temporada ";
		
		Query consulta2 = sesion.createQuery(ConsultaConSql).setParameter("id", idJugador);
		List<Object[]> lista = consulta2.list();
		
		for (int j = 0; j < lista.size(); j++) {
			Object[] fila = lista.get(0);
			System.out.println(fila[0]);
		}
		*/
		
		
		
		//Forma 3
		Jugadores jugador = (Jugadores) sesion.load(Jugadores.class, (int) idJugador);;
		Set lista = jugador.getEstadisticases();
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
		System.out.println("================================================");
		System.out.println("Num de registros: " +lista.size());
		System.out.println("================================================");
			
		sesion.close();
		System.exit(0);
		
	}//del main
}//de la clase
