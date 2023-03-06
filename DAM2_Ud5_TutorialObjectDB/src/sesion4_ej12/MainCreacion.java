package sesion4_ej12;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainCreacion {

	public static void main(String[] args) {

		Socio s1 = new Socio(1, "Juan", 120, 20, "Huesca");
		Socio s2 = new Socio(2, "Pepe", 140, 20, "Huesca");
		Socio s3 = new Socio(3, "Mario", 130, 20, "Zaragoza");
		Socio s4 = new Socio(4, "Raul", 120, 20, "Murcia");
		Socio s5 = new Socio(5, "Jose", 115, 20, "Barcelona");
		Socio s6 = new Socio(6, "Oscar", 118, 20, "Italia");
		Socio s7 = new Socio(7, "Carlos", 110, 20, "Madrid");
		Socio s8 = new Socio(8, "Santi", 190, 20, "Galicia");
		Socio s9 = new Socio(9, "dani", 172, 20, "Andorra");

		ArrayList<Socio> lista = new ArrayList<Socio>();
		lista.add(s1);
		lista.add(s2);
		lista.add(s3);
		lista.add(s4);
		lista.add(s5);
		lista.add(s6);
		lista.add(s7);
		lista.add(s8);
		lista.add(s9);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/jugadoresej12.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		for (Socio s : lista) {
			em.persist(s);
		}
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
