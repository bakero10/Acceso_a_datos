package primero;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EquiposyJugadores {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int totalEquipos=0;
		//HACEMOS LA CONEXION
		SessionFactory sessionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sessionF.openSession();
		
		String hql = "from Equipos as e order by e.nombre";							//que retorna??equipos por lo cual la lista es de equipos
		Query q = sesion.createQuery(hql);											//creamos la consulta
		List<Equipos> lista = q.list();												//creamos un array lista con la consulta
		totalEquipos = lista.size();
		System.out.println("Numero de Equipos: "+totalEquipos);
		
		float puntosPartido;
		int contador;
		
		for (Equipos equipo : lista) {													//Bajamos equipo											OBJETO EQUIPO
			System.out.println("=============================");
			System.out.println("Equipo: "+equipo.getNombre());
			Set jugadores = equipo.getJugadoreses();									// por cada equipo nos va a dar una lista de los jugadores	OBJETO JUGADOR
				for(Object ob : jugadores) {											// por cada jugador va ser un objeto jugador					
					puntosPartido = 0;
					contador = 0;
					Jugadores j = (Jugadores) ob;										// Creamos el jugador 
					System.out.print(j.getCodigo()+","+j.getNombre()+": ");
					Set estadistica = j.getEstadisticases();
					for(Object est : estadistica) {										// nos da la estadistica de cada jugador					OBJETO ESTADISTICA 
						Estadisticas estadisticasJugador = (Estadisticas) est;				// recogemos el nombre para la estadistica
						puntosPartido += estadisticasJugador.getPuntosPorPartido();						//recogemos el total de puntos
						contador++;
						if(contador == estadistica.size()) {
							DecimalFormat f = new DecimalFormat("###.##");
							System.out.println(f.format(puntosPartido/contador));
						}
					}
				
			}
		}
		
		
		
		
	
		
		
		
		
		

	}
}
