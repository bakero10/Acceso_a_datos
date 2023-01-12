package sesion6_ej14_2;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainCreacion {

	public static void main(String[] args) {
		// Authors
		Autor author1 = new Autor();
		author1.setNombre("Juan Perez");

		Autor author2 = new Autor();
		author2.setNombre("Oscar Blancarte");

		Autor author3 = new Autor();
		author3.setNombre("Arturo Martinez");

		// Books
		Libro book1 = new Libro();
		book1.setNombre("El lago y el pato");
		book1.addAuthor(author1);
		book1.addAuthor(author2);
		book1.addAuthor(author3);

		Libro book2 = new Libro();
		book2.setNombre("Una mañana de verano");
		book2.addAuthor(author1);
		book2.addAuthor(author2);
		book2.addAuthor(author3);
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/autoresLibros.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//No seria necesario hacer persits de los autores, ya que los libro ya tienen los autores metidos asi que automaticamente, 
		//crearia tambien la tabla autores, aun que no la indiquemos con el persist, si lo ponemos no pasaria nada. Pero mejor no ponerlo para 
		//ahorrar lineas de codigo
		//em.persist(author1);em.persist(author2);em.persist(author3); 
		em.persist(book1);em.persist(book2);
		em.getTransaction().commit();
		em.close();
		emf.close();

		System.out.println("FIN");

	}

}
