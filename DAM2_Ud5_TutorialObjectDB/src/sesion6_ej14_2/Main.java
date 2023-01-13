package sesion6_ej14_2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


//Hay que tener en cuenta que estas relaciones no son funcionales aplicado a un proyecto real, por que ocasionan muchos problemas de integridad. Siempre que tengamos una 
//relacion de muchos a muchos, tendremos que crear una tabla intermendia que una dicha relacion, para consutar de una mejor manera estos datos.

//En este ejemlo, hemos creado una tabla inermedia, a traves de las antaciones JPA, para consultar este ejemplo, se puede ver en: https://www.oscarblancarteblog.com/2018/12/27/relaciones-manytomany/

public class Main {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void conectar() {
		emf = Persistence.createEntityManagerFactory("db/autoresLibros.odb");
		em = emf.createEntityManager();
	}

	public static void desconectar() {
		em.close();
		emf.close();
	}
	
	
	public static void main(String[] args) {
		conectar();
		List<Autor> listaAutores = em.createQuery("SELECT a FROM Autor a").getResultList();
		List<Libro> listaLibros = em.createQuery("SELECT l FROM Libro l").getResultList();
	//	List<Object> listaRelacionLibroAutores = em.createQuery("SELECT r FROM rel_libros_autores r").getResultList();
		for(Autor a : listaAutores) {
			System.out.println(a);
		}
		System.out.println("+++++++++++++++++++++");
		for(Libro l : listaLibros) {
			System.out.println(l);
		}
		System.out.println("+++++++++++++++++++++");
		//No se por que no se crea la tabla con la relacion, preguntar a alberto. De esta manera podriamos consultar esta tabla y ver todas las relaciones
		//desde una sola consulta
	
		//TAMBIEN PODRIAMOS CREAR NOSOTROS LA TABLA A MANO, CREANDO UNA CLASE NUEVA Y PONIENDO LAS ANOTACIONES, PERO NO SE SI ES LO MÁS CORRECTO. HABRÁ QUE PROBAR
	//	for(Object o : listaRelacionLibroAutores) {
	//		System.out.println(o);
	//	}
		
		//Como lo anterior no funciona, demostramos la relacion manytomany, mostrando como desde libro puedes ver a todos sus autores y desde autores, 
		//puedes ver todos sus libros
		System.out.println("Demostracion relacion ManyToMany: Todos los libros del autor Juan Perez");
		TypedQuery<Autor> consulta =  em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE 'Juan Perez'",Autor.class);
		Autor autor1 = consulta.getSingleResult();
		for(Libro l : autor1.getLibros()) {
			System.out.println(l.toString());
		}
		System.out.println("\nDemostracion relacion ManyToMany: Todos los Autores del libro 'El lago y el pato'");
		TypedQuery<Libro> consulta2 =  em.createQuery("SELECT l FROM Libro l WHERE l.nombre LIKE 'El lago y el pato'",Libro.class);
		Libro libro1 = consulta2.getSingleResult();
		for(Autor a : libro1.getAutores()) {
			System.out.println(a.toString());
		}
		
		desconectar();
		
	}

}
