package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		AccesoBdatos abd = new AccesoBdatos();
		
		/*
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
		
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query consultaHibernate = session.createQuery("from Socio");
		List<Socio> lista = consultaHibernate.list();
		Iterator <Socio> iter = lista.iterator();
		while (iter.hasNext())
		{
		   //extraer el objeto
			Socio   s = (Socio) iter.next(); 
		   System.out.println(s.getLocalidad());		   
		}
		
		session.close();
		
	}

}
