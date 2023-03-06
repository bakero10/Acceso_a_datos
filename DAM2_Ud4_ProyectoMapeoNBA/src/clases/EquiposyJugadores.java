package clases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EquiposyJugadores {

	//Resolvemos el ejercicio de dos maneras
	
	// 1 Con dos consultas hql, una consigue el equipo que luego utilizaremos como parametro para la siguiente consulta.
	
	// 2 Recuperando el objeto equipo y preguntando por su atributo lista jugadores, que contiene a todos los jugadores que pertenecen a ese
	// equipo. Esto funciona gracias a que el mapeo de manera automatica a creado este atributo al detectar la relacion de clave ajena.

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		
		System.out.println("\n\n======================FORMA 1======================");
		//FORMA 1 CON HQL
		String consultaEquipos = "from Equipos as e order by e.nombre";
		String consultaDatos = "select j.codigo, j.nombre, round(avg(es.puntosPorPartido),2) "
				+ "from Estadisticas as es, Jugadores as j where es.jugadores.codigo = j.codigo and "
				+ "j.equipos.nombre like :equipoParametro "
				+ "group by j.nombre";
		
		Query consulta1 = sesion.createQuery(consultaEquipos);
		
		List<Equipos> lista1 = consulta1.list();
				
		System.out.println("Número de Equipos: " + lista1.size());
		System.out.println("====================================");
		for(Equipos equipo : lista1) {
			System.out.println("Equipo: " + equipo.getNombre() );
			Query consulta2 = sesion.createQuery(consultaDatos).setString("equipoParametro", "76ers");
			List<Object[]> lista2 = consulta2.list();
			for(Object[] object : lista2) {
				int codigoJugador = (int) object[0];
				String nombreJugador = (String) object[1];
				Double mediaPuntosPartido = (Double) object[2];
				System.out.println(codigoJugador+", "+nombreJugador+": "+mediaPuntosPartido);	
			}
			System.out.println("====================================");
		}
		
		
		
		
		System.out.println("\n\n======================FORMA 2======================");
		//FORMA 2 APROVECHANDO LOS ATRIBUTOS DE LAS CLASES
		List<Equipos> lista3 = sesion.createQuery("from Equipos as e order by e.nombre").list();
		System.out.println("Número de Equipos: " + lista3.size());
		System.out.println("====================================");
		
		for (Equipos equipo : lista3) {
			System.out.println(equipo.getNombre());
			Set lista4 = ((Equipos) sesion.load(Equipos.class, (String)equipo.getNombre())).getJugadoreses();
			Iterator iterator = lista4.iterator();
			
			while(iterator.hasNext()){
				Jugadores jugador = (Jugadores) iterator.next();
				Iterator iteratorEstadisticas = jugador.getEstadisticases().iterator();
				Double media = 0.0;
				int contador = 0;
				while(iteratorEstadisticas.hasNext()) {
					Estadisticas es = (Estadisticas) iteratorEstadisticas.next();
					media += es.getPuntosPorPartido();
					contador++;
				}
				System.out.println(jugador.getCodigo()+", "+jugador.getNombre()+": "+(Math.round(media/contador*100.0)/100.0));
				media = 0.0;
				contador= 0;
			}
			System.out.println("====================================");
		}
		
		
		
		System.out.println("\n\n====================FORMA 2.1 RESUMIENDO CODIGO======================");
		System.out.println("Numero de Equipos: " + sesion.createQuery("from Equipos").list().size());
		System.out.println("====================================");
		
		for (Equipos equipo : (List<Equipos>)sesion.createQuery("from Equipos as e order by e.nombre").list()) {
			System.out.println(equipo.getNombre());
			Iterator iterator = ((Equipos) sesion.load(Equipos.class, (String)equipo.getNombre())).getJugadoreses().iterator();
						
			while(iterator.hasNext()){
				Jugadores jugador = (Jugadores)iterator.next();
				Iterator iteratorEstadisticas = jugador.getEstadisticases().iterator();
				Double media = 0.0;int contador = 0;
				while(iteratorEstadisticas.hasNext()) {
					media += ((Estadisticas) iteratorEstadisticas.next()).getPuntosPorPartido();
					contador++;
				}
				System.out.println(jugador.getCodigo()+", "+jugador.getNombre()+": "+(Math.round(media/contador*100.0)/100.0));
				media = 0.0;contador= 0;
			}
			System.out.println("====================================");
		}
		
		
		

	}

}
