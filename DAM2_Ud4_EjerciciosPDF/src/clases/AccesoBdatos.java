package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.cj.protocol.Resultset;


public class AccesoBdatos {

			
	//IMPLEMENTANDO HIBERNATE
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	Session session;
	
	
	public void conectar() {
		//IMPLEMENTANDO HIBERNATE
		session = sesion.openSession();
	}
	
	public void desconectar() {
		//IMPLEMENTANDO HIBERNATE
		session.close();
	}
	
	
	
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
	
	//METODO ACTUALIZADO PARA UTILIZAR HIBERNATE Y LENGUAJE HQL 
		
	public List<Socio> consultaLocalidad(String localidad){
			List <Socio> lista = null;	
				
		if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
			try {
				//IMPLEMENTANDO HIBERNATE
				//Query consultaHibernate = session.createQuery("from Socio as soc where soc.localidad = :localidad").setString("localidad", localidad);
				//Query consultaHibernate = session.createQuery("from buscarLocalidad (:localidad)").setString("localidad", localidad);
				Query consultaHibernate = session.createSQLQuery("CALL buscarLocalidad(:localidad)").addEntity(Socio.class).setParameter("localidad",localidad);
				
				List<Socio> lista2 = consultaHibernate.list();
				Iterator <Socio> iter = lista2.iterator();
									
				if (iter.hasNext()) { //Si hay siguiente devolverá la lista con todos los socios
					lista = lista2;;
				}else {
					lista = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
			}catch(Exception sqle) {
				System.out.println("Error en la consulta para mostrar todos los socios");
			}
		}else {//Si esta vacio ejecuta consulta que devuelva todos los usuarios
			try {
								
				//IMPLEMENTANDO HIBERNATE
				Query consultaHibernate = session.createQuery("from Socio");
				List<Socio> lista2 = consultaHibernate.list();
				Iterator <Socio> iter = lista2.iterator();
									
				if (iter.hasNext()) { //Si hay siguiente devolvera una lista con todos los socios
					lista = lista2;;
				}else {
					lista = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
				}catch(Exception sqle) {
					System.out.println("Error en la consulta para mostrar todos los socios");
				}
		}
		return lista;
	}
	

	//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL ANIO DAM2 PARA ACTUALIZAR, BORRAR Y ANADIR SOCIOS
	public int actualizarSocio(int id,String nombre, int estatura, int edad, String localidad) {
		try {
			//IMPLEMENTANDO HIBERNATE
			Transaction tx= session.beginTransaction();	//Asi iniciariamos una transaccion,	
			Socio socio = (Socio) session.load(Socio.class, (int) id);
			
			socio.setNombre(nombre);socio.setEstatura(estatura);socio.setEdad(edad);socio.setLocalidad(localidad);
			session.update(socio);
			
			tx.commit(); //Hacemos commit antes del return		
			return 1;
			
		} catch (Exception e) {
			return 0;
		}
		
	}// del metodo actualizarSocio
	
	
		
	
	public int aniadirSocio(String nombre, int estatura, int edad, String localidad) {
		try {
			//IMPLEMENTANDO HIBERNATE
			Transaction tx= session.beginTransaction();
			
			//Conseguimos el ultimo id y le sumamos 1
			String hql = "select socio.socioId from Socio as socio order by socio.socioId desc";
			//String hql = "SELECT socioID from Socio order by socioID desc limit 1";
			Query q = session.createQuery(hql);
			q.setMaxResults(1); //Esto hay que ponerlo por que en lenguaje hql no se pude poner limit 1, asi que hay que especificarlo con
								//este metodo
			
			int ultimoID = (int) q.uniqueResult();
			Socio socio = new Socio((ultimoID+1),nombre,estatura,edad,localidad);
			session.save(socio);
			
			tx.commit();
			return 1;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}// del metodo aniadirSocio
	
	
	
	public int borrarSocio(int id) {
		
		try {
			Transaction tx = session.beginTransaction();
			Socio socio = (Socio) session.get(Socio.class, (int) id);
			
			if(socio == null) {
				System.out.println("Noi existe el socio");
				return 0;
			}else {
				session.delete(socio);
				tx.commit();	
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}//del metodo borrarSocio
	
	
}

//probar la logica en el main antes
