package primero;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EquiposYJugadores4 {
	public static void main(String[] args) {
	
		//hacemos la conexion
		SessionFactory see = SessionFactoryUtil.getSessionFactory();
		Session sesion = see.openSession();
		
		String hql = ("from Equipos as e order by e.nombre");
		Query q = sesion.createQuery(hql);
		
		List<Equipos> lista = q.list();
		System.out.println("Numero de Equipos "+lista.size());
		System.out.println("==================================");
		
		for (Equipos equipos : lista) {
			System.out.println("Equipo: "+equipos.getNombre());
			for (Object o : equipos.getJugadoreses()) {
				Jugadores j = (Jugadores) o;
				float puntosPartido=0;
				int contador = 0;
				System.out.print(j.getCodigo()+", "+j.getNombre()+", ");
				for (Object ob : j.getEstadisticases()) {
					Estadisticas es = (Estadisticas) ob;
					puntosPartido+= es.getPuntosPorPartido();
					contador++;
					if(contador == j.getEstadisticases().size()) {
						DecimalFormat df = new DecimalFormat("###.###");
						System.out.println(df.format(puntosPartido/contador));
					}
				}
			}
			System.out.println("............................");
			System.out.println("============================");
		}
		
		
	}
}
