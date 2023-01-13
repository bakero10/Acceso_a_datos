package sesion6_ej14_3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GuardarCliente {
	public static void main(String[] args) {

		// Entidad de personal docente
		PersonalDocente ts1 = new PersonalDocente(1, "Gopal", "MSc MEd", "Maths");
		PersonalDocente ts2 = new PersonalDocente(2, "Manisha", "BSc BEd", "English");

		// Entidad de personal no docente
		PersonalNoDocente nts1 = new PersonalNoDocente(3, "Satish", "Accounts");
		PersonalNoDocente nts2 = new PersonalNoDocente(4, "Krishna", "Office Admin");
		
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("db/relacionHerencia.odb");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		// guardamos las entidades
		entitymanager.persist(ts1);
		entitymanager.persist(ts2);
		entitymanager.persist(nts1);
		entitymanager.persist(nts2);

		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
		System.out.println("DB creada");
	}
}
