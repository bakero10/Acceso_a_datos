package primero;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AccesoBdatos {
	
//	private static String driver = "com.mysql.cj.jdbc.Driver";
//	private static String database = "baloncesto";
//	private static String hostname = "localhost";
//	private static String port = "3306";
//	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
//	private static String username = "root";
//	private static String password = "root";
//	
//	private Connection conecta; //Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo en el metodo conecta 
	
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	Session session;
	
	public void conectar() {
		session = sesion.openSession();

	}
	
	public void desconectar() {
		session.close();

	}
		//METODO ACTUALIZAR DATOS ----------------------------------------- HIBERNATE************
		public void actualizarDatos(int socioID, String nombre, int estatura, int edad, String localidad) {
			
			String hqlModif = "update Socio set nombre = :nombre,estatura = :estatura,edad = :edad,localidad = :localidad where socioID = :socioID;"; 
			Query q = session.createQuery(hqlModif);
				q.setParameter("nombre", nombre);
				q.setParameter("estatura",estatura);
				q.setParameter("edad", edad);
				q.setParameter("localidad", localidad);
				q.setParameter("socioID", socioID);
			int filasModificadas = q.executeUpdate();
			System.out.println("Filas modificadas --> "+filasModificadas);
		}
		
		//METODO PARA INSERTAR ----------------------------------------------- HIBERNATE *************
		public void insertarNuevo(String nombre, int estatura, int edad, String localidad,int socioID) {
			
				String hql = "insert into Socio values (?, ?, ?, ?, ?)";
				Query q = session.createQuery(hql);
				q.setParameter(1, ultimoIdDisponible());
				q.setParameter(2, nombre);
				q.setParameter(3, estatura);
				q.setParameter(4, edad);
				q.setParameter(5, localidad);
				q.executeUpdate();
				JOptionPane.showMessageDialog(null, "Nuevo socio introducido");
			
		}
		//METODO PARA SACAR LA ULTIMA ID DISPONIBLE --------------------------------- HIBERNATE ***************
		
		public int ultimoIdDisponible() {
			Query q;
			int num = 0 ;
				String hql = "select socioID from Socio order by socioId desc limit 1";
				q  = session.createQuery(hql);
				q.executeUpdate();
				Socio socio = new Socio();
				if(socio == null) {
					System.out.println("Este socio no existe");
				}
				else {
					num = socio.getSocioId();
				}
				return num +1;
		}
		//METODO PARA BORRAR UN SOCIO MEDIANTE UNA ID ---------------------------------- HIBERNATE ***************
		public void borrarSocio(int socioID) {
				
				String hql = "delete from Socio where socioId = : socioId ";
				Query q = session.createQuery(hql);
				q.setParameter("socioId", socioID);
				q.executeUpdate();
				JOptionPane.showMessageDialog(null, "Socio borrado");
			
		}
 
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
		
	public List<Socio> consultaLocalidad(String localidad){
		Query q;
		List <Socio> lista = null;
		if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
			String hql = "from Socio where localidad like :localidad"; //tabla socio que la localidad sea --> localidad
			q = session.createQuery(hql);		// creamos la consulta query
			q.setParameter("localidad", localidad);		// Le decimos que la localidad sea la localidad que le pasamos por parametro
			lista = q.list();		// Lista de socios = lista de socios que contengan la localidad insertada
			
		}
		return lista;	//retornamos la lista
	}
//	//METODO BUSCAR POR ID
//		public ResultSet buscarPorId(int id) {
//			ResultSet rs = null;	
//			try {
//				PreparedStatement ps = conecta.prepareStatement("select * from socio where socioId = ?");
//				ps.setInt(1, id);
//				rs = ps.executeQuery();
//					
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return rs;
//		}//FIN METODO BUSCAR POR ID
//		
//		//METODO ACTUALIZAR
//		public void actualizarDatos(int socioId , String nombre,int estatura,int edad,String localidad) {
//			
//			try {
//				PreparedStatement ps = conecta.prepareStatement("update socio set nombre = ? , estatura = ? , edad = ? , localidad = ? where socioId = ?;");
//				ps.setInt(5, socioId);
//				ps.setString(1, nombre);
//				ps.setInt(2, estatura);
//				ps.setInt(3, edad);
//				ps.setString(4, localidad);
//				ps.executeUpdate();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}// FIN ACTUALIZAR
//		
//		
//		// METODO ULTIMA ID
//		public int ultimaId() {
//			ResultSet rs;
//			int num = 0;
//			try {
//				PreparedStatement ps = conecta.prepareStatement("select socioID from socio order by socioId desc limit 1");
//				rs = ps.executeQuery();
//				//rs.next(); Acordarse de hacer esto para que pueda leer el registro de la primera posicion para luego poder hacerle un rs.getInt o .getString...
//				rs.next();
//				num = rs.getInt(1) + 1;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(num);
//			return num;
//			
//		}//FIN ULTIMA ID
//		
//		//METODO NUEVO
//		
//		public void nuevoSocio(String nombre,int estatura,int edad,String localidad) {
//			ResultSet rs;
//			try {
//				PreparedStatement ps = conecta.prepareStatement(" insert into socio values(?,?,?,?,?) ");
//				ps.setInt(1, ultimaId());
//				ps.setString(2, nombre);
//				ps.setInt(3, estatura);
//				ps.setInt(4, edad);
//				ps.setString(5, localidad);
//				ps.executeUpdate();
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		//METODO ELIMINAR
//		
//		public void eliminar ( int socioId) {
//			
//			try {
//				PreparedStatement ps = conecta.prepareStatement("delete from socio where socioID = ?");
//				ps.setInt(1, socioId);
//				ps.execute();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
}

//probar la logica en el main antes
