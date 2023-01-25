package Ejercicio03;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		
		
		Socio s1 = new Socio(1, "Juan", 120, 20, "Huesca");
		Socio s2 = new Socio(2, "Pepe", 140, 20, "Huesca");
		Socio s3 = new Socio(3, "Mario", 130, 20, "Zaragoza");
		Socio s4 = new Socio(4, "Raul", 120, 20, "Murcia");
		Socio s5 = new Socio(5, "Jose", 115, 20, "Barcelona");
		Socio s6 = new Socio(6, "Oscar", 118, 20, "Italia");
		Socio s7 = new Socio(7, "Carlos", 110, 20, "Madrid");
		Socio s8 = new Socio(8, "Santi", 190, 20, "Galicia");
		Socio s9 = new Socio(9, "dani", 172, 20, "Andorra");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/jugadoresej3.odb");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.persist(s4);
		em.persist(s5);
		em.persist(s6);
		em.persist(s7);
		em.persist(s8);
		em.persist(s9);

		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		
		
	}

}
