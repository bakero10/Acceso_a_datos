package sesion6_ej14_1;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


//Ejemplo relacion OneToOne
public class MainCreacion {

	public static void main(String[] args) {
		Date d1 = new Date();
		Tarjeta t1 = new Tarjeta(125664, d1);
		Socio s1 = new Socio("125515f", "Juan", "Perez",t1);

			

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/socioTarjeta.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(s1);em.persist(t1);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
