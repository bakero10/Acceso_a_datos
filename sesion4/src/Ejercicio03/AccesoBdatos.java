package Ejercicio03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AccesoBdatos {

	private EntityManagerFactory emf;
	private EntityManager em;

	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleados.odb");
		em = emf.createEntityManager();
		}
	
	
	public void desconectar() {
		em.close();
		emf.close();
	}
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
		
	public List<Socio> consultaLocalidad(String localidad){
		int resultado;
		ResultSet salida = null;
		if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
			em.getTransaction().begin();
				List<Socio> lista= em.createQuery("SELECT s FROM socio s WHERE s.localidad like : localidad").setParameter("localidad", localidad).getResultList();			
			em.getTransaction().commit();
		}
	}
	return lista;
}
//probar la logica en el main antes
