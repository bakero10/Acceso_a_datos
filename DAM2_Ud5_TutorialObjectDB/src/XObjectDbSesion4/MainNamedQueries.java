package XObjectDbSesion4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//
//Alberto Carrera Martín - Abril 2020
// Revisado Febrero 2021

public class MainNamedQueries {

	public static void main(String[] args) throws ParseException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/empleados1.odb");
		EntityManager em = emf.createEntityManager();
		
		String nombreDepartamento = "I+D";
		long numero = (Long) em.createNamedQuery("numeroEmpleados").setParameter("nombre", nombreDepartamento)
				.getSingleResult();
		
		System.out.println("Número de empleados del Departamento de: " + nombreDepartamento + " -> " + numero);

		em.close();
		emf.close();

	}
}
