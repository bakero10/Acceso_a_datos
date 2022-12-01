package primero;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EjercicioEstadisticasConTodo {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//Conexion
		SessionFactory se = SessionFactoryUtil.getSessionFactory();
		Session sesion = se.openSession();
		
		System.out.print("DATOS DEL JUGADOR: ");
		int CodigoJugador = s.nextInt();
		
		//Creamos la consulta
		Jugadores j = (Jugadores) sesion.createQuery("from Jugadores as j where j.codigo = :cod").setInteger("cod", CodigoJugador).uniqueResult();
			System.out.println("Nombre : "+j.getNombre());
			System.out.println("Equipo : "+j.getEquipos().getNombre());
			System.out.println("Temporada\tPtos\tAsis\tTap\tReb");
			System.out.println("============================================");
				@SuppressWarnings("unchecked")
				ArrayList<Object[]> lista = (ArrayList<Object[]>) sesion.createQuery(
						"select e.id.temporada, e.puntosPorPartido, e.asistenciasPorPartido, e.taponesPorPartido, e.rebotesPorPartido"
								+ " FROM Estadisticas e where e.id.jugador= :cod")
						.setInteger("cod", CodigoJugador).list();
				for (Object[] o : lista) {
					System.out.println((String)o[0]+"\t\t"+(float)o[2]+"\t"+(float)o[3]+"\t"+(float)o[4]);
				}
				System.out.println("=============================================\nNum de registros: " + lista.size()
				+ "\n=============================================");
		
	}

}
