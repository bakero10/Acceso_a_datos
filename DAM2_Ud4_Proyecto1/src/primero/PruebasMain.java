package primero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		

		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		// EXAMEN DE ALBERTO
		// Pregunta 1 
		//Método public boolean pregunta1(String nombre, String localidad) que almacene una nueva tienda a partir del nombre
		// y teléfono que se le pasan como argumentos. El nombre de la tienda no puede ser nulo ni vacío. El codigo de la tienda no hace falta suministrarlo 
		// pues se trata de una columna autoincrementable. Incluir un manejador (genérico) de error. El método devolverá true/false si se ha podido o no insertar la nueva tienda.
		
//		String nombre="Bakero";
//		String localidad = "Huesca";
//		try {
//			Transaction tx = session.beginTransaction();   //ABRIMOS CONEXION
//			Socio s = new Socio(1307,nombre,177,34,localidad);
//			session.save(s);
//			tx.commit();									//CERRAMOS CONEXION
//			//return true
//		} catch (Exception e) {
//			//return false
//		}
		
		//Pregunta 2
		// Método public boolean pregunta2(int id, int edad) que reciba el id de una persona como primer argumento y actualice su edad 
		// con el valor del segundo argumento que se pasa al procedimiento. Incluir un manejador (genérico) de error. El método devolverá true si no ha habido 
		// errores en la operación o false en caso contrario.
		
//		try {
//			//IMPLEMENTANDO HIBERNATE
//			int id=1307;
//			int edad = 67;
//			Transaction tx= session.beginTransaction();	//Asi iniciariamos una transaccion,	
//			Socio socio = (Socio) session.load(Socio.class, (int) id); // carga el objeto Socio con id que le pasamos
//
//			socio.setNombre("Bakero");socio.setEstatura(173);socio.setEdad(edad);socio.setLocalidad("Huesca"); // ponemos datos nuevos del socio
//			session.update(socio);	//actualizamos el socio
//			
//			tx.commit(); //Hacemos commit antes del return		
//			//return true;
//			
//		} catch (Exception e) {
//			//return false;
//		}
		
		
		//PARA RECUPERAR TODOS AQUELLOS SOCIOS QUE MIDAN MENOS 160
		
		Transaction tx = session.beginTransaction();
		int edades = 160;
		Query q = session.createQuery("FROM Socio WHERE edad < :e").setParameter("e", edades);
		List<Socio> lista = q.list();
		for (Socio socio : lista) {
				System.out.println(socio.getNombre()+" - "+socio.getLocalidad()+" - "+socio.getEstatura());
		}
		tx.commit();
		
		
//		//PARA RECUPERAR VARIAS PERSONAS POR SU LOCALIDAD
//		String localidad = "Malaga";
//		Query q = session.createQuery("FROM Socio WHERE localidad = :localidad").setParameter("localidad", localidad);
//		List<Socio> lista = q.list();
//		for (Socio socio : lista) {
//			System.out.println(socio.getNombre()+" - "+socio.getLocalidad());
//		}
 		
//		// PARA RECUPERAR UN SOCIO POR NOMBRE
//		String nombre = "Melones Ana Maria";
//		Query query = session.createQuery("FROM Socio s WHERE s.nombre = :nombre");
//		query.setParameter("nombre", nombre);
//		Socio socio = (Socio) query.uniqueResult();
//		System.out.println(socio.getNombre()+" - "+socio.getLocalidad()+" - "+socio.getEdad());

		
//		// PARA RECUPERAR UN SOCIO POR ID
//		Transaction tx = session.beginTransaction();
//		Socio socio2 = (Socio) session.load(Socio.class, 1235);
//		tx.commit();
//		System.out.println(socio2.getNombre());
		
		
//		Query consultaHibernate = session.createQuery("from Socio");
//		List<Socio> lista = consultaHibernate.list();
//		
//		
//		//PARTE MIA 
//		for (Socio socio : lista) {
//			System.out.println(socio.getNombre());
//		}
		//PARTE SANTI
		
//		Iterator <Socio> iter = lista.iterator();
//		while (iter.hasNext())
//		{
//		   //extraer el objeto
//			Socio   s = (Socio) iter.next(); 
//		   System.out.println(s.getLocalidad());		   
//		}
		
		
		session.close();
		
		//ANTIGUO
		/*
		AccesoBdatos abd = new AccesoBdatos();
		abd.conectar();
		ResultSet rs = abd.consultaLocalidad("Malaga");
		
		//System.out.println(rs);
		
		while(rs.next()) {
			int socioID = rs.getInt("socioID");
			String nombre = rs.getString("nombre");
			int estatura = rs.getInt("estatura");
			int edad = rs.getInt("edad");
			String localidad = rs.getString("localidad");
			
			System.out.println("Id socio: " + socioID + " Nombre: " + nombre + " Estatura: " + estatura + " Edad: " + edad + " Localidad: " + localidad);
		}
		abd.desconectar();
		 */
	}

}
