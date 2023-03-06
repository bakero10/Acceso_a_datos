package sesion6_ej14_1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void conectar() {
		emf = Persistence.createEntityManagerFactory("db/socioTarjeta.odb");
		em = emf.createEntityManager();
	}

	public static void desconectar() {
		em.close();
		emf.close();
	}
	
	public static void main(String[] args) {
		//Demostracion de que funciona, pd lo he hecho bidireccional, tanto desde socio se puede acceder a la tarjeta y desde la tarjeta se puede acceder al socio
		//por eso cargo las dos clases, para demostrar que desde ambas se pueden acceder a los atributos de la otra
		conectar();
		Tarjeta tarjeta = em.find(Tarjeta.class, 125664); 
		System.out.println(tarjeta.getSocio().getNombre()+" - "+ tarjeta.getNumero());
		Socio socio = em.find(Socio.class, "125515f"); 
		System.out.println(socio.getNombre()+" - "+ socio.getTarjeta().getNumero());
		desconectar();
		
	}

}
