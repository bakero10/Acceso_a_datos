package primero;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EjercicioEstadisticasCarlos {
	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session;
		session = sesion.openSession();

		Scanner s = new Scanner(System.in);
		System.out.println("Introduzca el codigo de un jugador: ");
		int i = s.nextInt();
		try {
			Jugadores j = (Jugadores) session.createQuery("FROM Jugadores as j WHERE j.codigo= :cod")
					.setInteger("cod", i).uniqueResult();

			System.out.println("Datos del jugador: " + i + "\n" + "Nombre: " + j.getNombre() + "\n" + "Equipo: "
					+ j.getEquipos() + "\n" + "Temporada\tPtos\tAsis\tTap\tReb\n"
					+ "=============================================");
			
			@SuppressWarnings("unchecked")
			ArrayList<Object[]> lista = (ArrayList<Object[]>) session.createQuery(
					"select e.id.temporada, e.puntosPorPartido, e.asistenciasPorPartido, e.taponesPorPartido, e.rebotesPorPartido"
							+ " FROM Estadisticas e where e.id.jugador= :cod")
					.setInteger("cod", i).list();
			for (Object[] o : lista)
				System.out.println((String) o[0] + "\t\t" + (Float) o[1] + "\t" + (Float) o[2] + "\t" + (Float) o[3]
						+ "\t" + (Float) o[4]);
			System.out.println("=============================================\nNum de registros: " + lista.size()
					+ "\n=============================================");
		} catch (NullPointerException e) {
			System.out.println("El jugador no existe ");
		}

	}
}
