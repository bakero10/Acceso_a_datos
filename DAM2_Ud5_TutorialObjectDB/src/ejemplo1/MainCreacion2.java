package ejemplo1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//
//Alberto Carrera Mart�n - Abril 2020
// Revisado Febrero 2021

public class MainCreacion2 {

	public static void main(String[] args) throws ParseException {
        //cascade=CascadeType.PERSIST o  en DepartamentoEntity
		// o cascade= CascadeType.ALL
		// y sobre todo atributo orphanRemoval=true!!!! Para que al eliminar el padre
		// elimine tambi�n los hijos
	   
       //       
       
		
		DepartamentoEntity d1 = new DepartamentoEntity (10, "Finanzas", "Huesca");
        DepartamentoEntity d2 = new DepartamentoEntity (20, "I+D", "Walqa-Cuarte");
        DepartamentoEntity d3 = new DepartamentoEntity (30, "Comercial", "Almud�var");
        DepartamentoEntity d4 = new DepartamentoEntity (40, "Producci�n", "Barbastro");
        DepartamentoEntity d5 = new DepartamentoEntity (50, "Marketing", "Zaragoza");
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		EmpleadoEntity e1 = new EmpleadoEntity (1039, "Alberto Carrera Mart�n", "Presidente", null, formato.parse("1999-10-27"), 4900, null, d1);
		EmpleadoEntity e2 = new EmpleadoEntity (1082, "Mario Carrera Bail�n","Director", e1, formato.parse("2001-07-06"), 3385, null, d1);
		EmpleadoEntity e3 = new EmpleadoEntity (1034, "Raquel Carrera Bail�n","Empleado",e2, formato.parse("2002-11-12"), 2690, null,d1);
		
		EmpleadoEntity e4 = new EmpleadoEntity (2066, "Blanca Bail�n Perarnau", "Director", e1, formato.parse("2001-07-12"), 2970, null, d2);
		EmpleadoEntity e5 = new EmpleadoEntity (2002, "Araceli Carrera Salcedo", "Investigador", e4, formato.parse("2003-02-24"),3000 ,null, d2);
		EmpleadoEntity e6 = new EmpleadoEntity (2069, "Fernando Carrera Mart�n", "Empleado", e5, formato.parse("2001-11-19"),2840, null, d2);
		EmpleadoEntity e7 = new EmpleadoEntity (2088, "Carmen Bail�n Perarnau", "Investigador", e4, formato.parse("2001-10-19"), 2600, null, d2);
		EmpleadoEntity e8 = new EmpleadoEntity (2076, "Fernando Carrera Salcedo", "Empleado",e7, formato.parse("2005-02-13"), 2730,null, d2);
		//
		EmpleadoEntity e9 = new EmpleadoEntity (3098, "Fernando Mart�nez P�rez", "Director",  e1, formato.parse("2000-02-03"), 3150,  null, d3);
		EmpleadoEntity e10 = new EmpleadoEntity (3099, "Bel�n Carrera Saus�n", "Comercial", e9, formato.parse("2000-02-22"), 2500, 500, d3);
		EmpleadoEntity e11 = new EmpleadoEntity (3051, "Enrique Casado Alvarez", "Comercial", e9, formato.parse("2002-07-23"), 2600, 550, d3);
		EmpleadoEntity e12 = new EmpleadoEntity (3054, "Antonio M�riz Piedrafita", "Comercial", e9, formato.parse("2003-03-22"), 2600, 1000, d3);
		EmpleadoEntity e13 = new EmpleadoEntity (3044,"Lorenzo Blasco Gonz�lez", "Comercial", e9, formato.parse("2001-03-07"), 2350, 400 ,d3);
		EmpleadoEntity e14 = new EmpleadoEntity (3000,"Javier Escart�n Nasarre", "Empleado", e9, formato.parse("2003-07-13"), 2435, null, d3);
		//
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("db/empleados.odb");
	   EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(d1); em.persist(d2);em.persist(d3);em.persist(d4);em.persist(d5);
       em.persist(e1);em.persist(e2);em.persist(e3);em.persist(e4);em.persist(e5);em.persist(e6);em.persist(e7);
       em.persist(e8);em.persist(e9);em.persist(e10);em.persist(e11);em.persist(e12);em.persist(e13);em.persist(e14);
       //       
       
       em.getTransaction().commit();
       em.getTransaction().begin();
       em.remove(em.find(DepartamentoEntity.class, 10));
       em.getTransaction().commit();
     
      
       em.close();
       emf.close();
      
	}
}
