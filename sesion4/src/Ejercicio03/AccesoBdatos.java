package Ejercicio03;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AccesoBdatos {

	// IMPLEMENTANDO OBJECTDB
	private EntityManagerFactory emf;
	private EntityManager em;

	public void conectar() {
		// IMPLEMENTANDO OBJECTDB
		emf = Persistence.createEntityManagerFactory("db/jugadoresej3.odb");
		em = emf.createEntityManager();
	}

	public void desconectar() {
		// IMPLEMENTANDO HIBERNATE
		em.close();
		emf.close();
	}

	// Creamos un metodo que primero mire si la localidad que nos da esta vacia,
	// devuelva un ResultSet con todos los socios.
	// Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo
	// los socios de esa Localidad.
	// Ademas despues contaremos el numero de socios(filas) del ResultSet en la
	// interfaz

	// METODO ACTUALIZADO PARA UTILIZAR OBJECTDB Y LENGUAJE JPQL

	public List<Socio> consultaLocalidad(String localidad) {
		List<Socio> lista = null;

		if (localidad.isEmpty() == false) { // Si localidad es distinto de vacio ejecuta lo de dentro
			try {
				List<Socio> lista2 = em.createQuery("SELECT s FROM Socio s WHERE localidad LIKE :localidad")
						.setParameter("localidad", localidad).getResultList();

				if (lista2.size() > 0) { // Si hay siguiente devolver� la lista con todos los socios, podriamos haber
											// hecho lista2.size()>0
					lista = lista2;
				} else {
					lista = null; // Si no encuentra resultados, devolvera null (en este caso nunca se vera esto,
									// por que en el unico caso de que devolviera null, seria que no funcionara la
									// conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
			} catch (Exception sqle) {
				System.out.println("Error en la consulta para mostrar todos los socios");
			}
		} else {// Si esta vacio ejecuta consulta que devuelva todos los usuarios
			try {

				// IMPLEMENTANDO HIBERNATE
				List<Socio> lista2 = em.createQuery("SELECT s FROM Socio s").getResultList();

				if (lista2.size() > 0) { // Si hay siguiente devolvera una lista con todos los socios
					lista = lista2;
				} else {
					lista = null; // Si no encuentra resultados, devolvera null (en este caso nunca se vera esto,
									// por que en el unico caso de que devolviera null, seria que no funcionara la
									// conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
			} catch (Exception sqle) {
				System.out.println("Error en la consulta para mostrar todos los socios");
			}
		}
		return lista;
	}

	// AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL ANIO DAM2 PARA
	// ACTUALIZAR, BORRAR Y ANADIR SOCIOS
	public int actualizarSocio(int id, String nombre, int estatura, int edad, String localidad) {
		Socio socioBuscado = em.find(Socio.class, id);
		if (socioBuscado == null) // Si no existe el departamento con anterioridad no te deja modificarlo
			return 0;
		em.getTransaction().begin();
		socioBuscado.setNombre(nombre);
		socioBuscado.setEstatura(estatura);
		socioBuscado.setEdad(edad);
		socioBuscado.setLocalidad(localidad);
		em.persist(socioBuscado);
		em.getTransaction().commit();
		return 1;
	} // de actualizarSocio

	public int aniadirSocio(String nombre, int estatura, int edad, String localidad) {
		// Como no existe en el lenguaje JPQL el limit 1, tenemos que utilizar el metodo
		// setMaxResults
		TypedQuery<Integer> id = em.createQuery("SELECT s.socioID FROM Socio s order by s.socioID desc", Integer.class)
				.setMaxResults(1);
		Socio socioBuscado = em.find(Socio.class, id.getSingleResult() + 1);
		if (socioBuscado != null) // Si ya existe el socio, no te deja insertarlo
			return 0;
		Socio s = new Socio((id.getSingleResult() + 1), nombre, estatura, edad, localidad);
		em.getTransaction().begin();
		em.persist(s); // pesist para añadir socio
		em.getTransaction().commit();
		return 1;
	} // de actualizarSocio

	public int borrarSocio(int id) {
		Socio socioBuscado = em.find(Socio.class, id);
		if (socioBuscado == null) {
			System.out.println("No existe el socio");
			return 0;
		} else {
			em.getTransaction().begin();
			em.remove(socioBuscado); // remove se elminina el socio deseado
			em.getTransaction().commit();
			return 1;
		}
	}// del metodo borrarSocio

	public void modificarAltura(int SocioID, int altura) {
		Socio s = em.find(Socio.class, SocioID);
		if (s == null) {
			System.out.println("El socio " + s + " no existe.");
		} else {
			em.getTransaction().begin();
			s.setEstatura(altura);
			em.getTransaction().commit();
			System.out.println("Ejecutado");
		}

	}

}

//probar la logica en el main antes
