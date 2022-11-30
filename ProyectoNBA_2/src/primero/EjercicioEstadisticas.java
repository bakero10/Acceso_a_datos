package primero;

import java.util.Iterator;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EjercicioEstadisticas {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//1er paso hacemos la conexion
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		//Preguntamos de que jugador queremos hacer la consulta
		System.out.print("DATOS DEL JUGADOR: ");
		int idJugador = s.nextInt();
		// 2do paso realizamos la consulta.
		//Forma 1
		//Le decimos que nos recupere Estadisticas y Jugadores en una lista, pero solo los que cumplan la condicion del where
		// usamos las clases mapeadas por ejemplo en estadistica hay un atributo que se llama jugadores para referenciar a la tabla jugadores
		String ConsultaConHql = "from Estadisticas e, Jugadores j where e.jugadores.codigo = j.codigo and j.codigo = :id order by e.id.temporada";
		Query consulta = sesion.createQuery(ConsultaConHql).setParameter("id", idJugador);
		
		//List<Object[]> filas = consulta.list(); //de esta manera podriamos hacerlo con una lista directamente, sin iterate
		
		Iterator i = consulta.iterate(); //En vez de hacer un .list(), hacemos un iterate directamente, por que sabemos que el resultado
										//va a ser una matriz, (un array, que guarda un array), la posicion 0 hace referencia a Estadisticas y la posicion 1 a Jugadores
		int contador = 0;
		
		if(i.hasNext()) {		//Si hay siguiente quiere decir que se ha creado la matriz, con lo que continuamos, sino, salta al else
			contador++;
		Object[] array = (Object[]) i.next();
		Estadisticas es = (Estadisticas) array[0];
		Jugadores ju = (Jugadores) array[1];
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
		}
		else 
			{			
			System.out.println("El id del jugador introducido no existe");
			}
		
//		//Forma 2
//				Jugadores jugador;
//				Set lista;
//				
//				jugador = (Jugadores) sesion.load(Jugadores.class, (int) idJugador);
//				lista = jugador.getEstadisticases();
//				Iterator iterador = lista.iterator();
//				
//				System.out.println("Nombre : "+jugador.getNombre());
//				System.out.println("Equipo : "+jugador .getEquipos().getNombre());
//				System.out.println("Temporada	Ptos	Asis	Tap	Reb");
//				System.out.println("================================================");
//				while(iterador.hasNext()) {
//					Estadisticas estaditicas = (Estadisticas) iterador.next();
//					System.out.printf("%s \t\t %.1f \t%.1f \t%.1f \t%.1f \n", estaditicas.getId().getTemporada(), estaditicas.getPuntosPorPartido(), estaditicas.getAsistenciasPorPartido()
//									,estaditicas.getTaponesPorPartido(), estaditicas.getRebotesPorPartido());
//				}
		
		
		}//main
}//class
