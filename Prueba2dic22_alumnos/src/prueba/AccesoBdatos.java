package prueba;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import primero.Familia;
import primero.Producto;
import primero.SessionFactoryUtil;
import primero.Stock;

//

public class AccesoBdatos {
	private SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	private Session session = sesion.openSession();

	public void conectar() {
		sesion = SessionFactoryUtil.getSessionFactory();
		session = sesion.openSession();
	}

	public void desconectar() {
		session.close();
		sesion.close();
		System.exit(0);
	}

	public boolean pregunta1(String nombre, String telefono) {
		
		Transaction tx = null;
		tx = session.beginTransaction();
		try {
			
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
			
		return true;
	}

	

	public boolean pregunta2(int codigo, String telefono) { 
		
		Transaction tx = null;
		tx = session.beginTransaction();
		try {
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		return true;
	}
	
	public long pregunta3() {
		Familia familia;
		List lista;
		int contador = 0;
		
		String hql = ("from Familia");
		Query query = session.createQuery(hql);
		lista = query.list();
		for (Object object : lista) {
			familia = (Familia) object;
			contador++;
		}
		return contador;
	}
	
	
	public void pregunta4(String codigoP,int codigoT) {
		String hql = ("from Stock ");
		Query q = session.createQuery(hql);
		List<Stock> lista1 = q.list();
	}
	
	public void pregunta6() {
		Producto productos;
		
		String total = null;
		String hql = ("from Producto ");
		Query q = session.createQuery(hql);
		List<Producto> lista = q.list();
		
		for (Producto producto : lista) {
			String hql1 = ("from Stock ");
			Query q1 = session.createQuery(hql1);
			List<Stock> lista1 = q1.list();
			for (Stock stock : lista1) {
				if(stock.getProducto().equals(producto.getCod())) {
					System.out.println("");
				}
				else {
					 total = (producto.getNombre()+"- "+producto.getPvp()+"- "+producto.getFamilia());
				}
			}
		}
		System.out.println(total);
		
	}
	
} // de la clase AccesoBdatos
