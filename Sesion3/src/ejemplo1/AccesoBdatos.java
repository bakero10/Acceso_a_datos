package ejemplo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//
// Alberto Carrera Mart�n - Abril 2020
//

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
	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		return em.find(DepartamentoEntity.class, numDepartamento);
	}// de m�todo buscarDepartamento
	//
	@SuppressWarnings("deprecation")
	public void imprimirDepartamento (int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d==null)
			System.out.println("No existe el Departamento " + numDepartamento);
		else {
			Set <EmpleadoEntity> empleados =d.getEmpleados();
			String datos="Datos del departamento " + numDepartamento + ": ";
			datos+= "Nombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad()+ "\n";
			if (empleados.isEmpty())
				datos+="No tiene empleados en este momento";
			else {			
				datos+="Lista de empleados"+ "\n";
				datos+="*******************";
			}
			for (EmpleadoEntity empleado :empleados) {
				datos+= "\nN�mero de empleado: " + empleado.getEmpnoId()+ "\n";
				datos+= "Nombre: " + empleado.getNombre()+ "\n";
				datos+= "Oficio: " + empleado.getOficio()+ "\n";
				if (empleado.getDirId()==null)
					datos+= "Jefe: No tiene"+ "\n";
				else
					datos+= "Jefe: "+ empleado.getDirId().getNombre()+ "\n";
				datos+= "A�o de alta: " + (empleado.getAlta().getYear()+1900)+ "\n";	
				datos+= "Salario: "+ empleado.getSalario()+ "\n";
				if (empleado.getComision() ==null)
					datos+= "Comisi�n: No tiene"+ "\n";
				else
					datos+= "Comisi�n: "+ empleado.getComision()+ "\n";
			}
			
			System.out.println(datos);
		}
	} // de m�todo imprimirDepartamento
	
	public boolean insertarDepartamento (DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId())!=null)
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento
	
	public boolean modificarDepartamento (DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(d.getDptoId());
		if (departamentoBuscado==null)
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist (departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	// Si tiene empleados lo borrar�a igual, dejando en estos el n�mero de Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo borrar
	
	public boolean borrarDepartamento  (int numDepartamento) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(numDepartamento);
		if (departamentoBuscado==null || !departamentoBuscado.getEmpleados().isEmpty() )
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	public void demoJPQL() {
		
		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
        System.out.println("Total Departamentos: " + q1.getSingleResult());
        //
        TypedQuery<Long> tq1 = em.createQuery(
        	      "SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
        System.out.println("Total Departamentos: " + tq1.getSingleResult());
        //
        TypedQuery<DepartamentoEntity>tq2 =
	            em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
	        List<DepartamentoEntity> l2 = tq2.getResultList();
	        for (DepartamentoEntity r2 : l2) {
	            System.out.println("Nombre :  " + r2.getNombre()+ ", Localidad: "+ r2.getLocalidad());
	        }
	    //
        TypedQuery<Object[]>tq3 =
	            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d", Object[].class);
	        List<Object[]> l3 = tq3.getResultList();
	        for (Object[] r3 : l3) {
	            System.out.println(
	            "Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
	    }    
	    //*/
	      TypedQuery<Object[]>tq4 =
		            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity d"
		            		+ " WHERE d.dptoId != :n", Object[].class);
	        		tq4.setParameter("n", 10);
		        List<Object[]> l4 = tq4.getResultList();
		        for (Object[] r4 : l4) {
		            System.out.println(
		            "Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		    }  
		     
	     
	}// de demoJPQL
//--------------------------------------------------------------------------------------------------------------
	public void ejercicio1() {
		 //Nombre y fecha de alta de todos los empleados
		
		 List<Object[]> lista = em.createQuery("SELECT d.nombre,d.alta FROM EmpleadoEntity d ").getResultList();
		 for (Object[] object : lista) {
			System.out.println(object[0]+" - "+object[1]);
		 }
	}
		 //Igual que el anterior pero para aquellos que carrera forme parte del nombre.No distinguir mayusculas de minusculas
	
	public void ejercicio2() {
		List<Object[]> lista = em.createQuery("SELECT e.nombre,e.alta FROM EmpleadoEntity e WHERE e.nombre like '%Carrera%'").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	public void ejercicio03() {
		//Empleados del Departamento I+D cuyo oficio es el de Empleado
		
		List<Object[]> lista = em.createQuery("SELECT e.nombre,e.oficio,e.departamento.nombre FROM EmpleadoEntity e WHERE e.departamento.nombre like '%I+D%' AND e.oficio like '%Empleado%' ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+ objects[1]+" - "+objects[2]);
		}
	}
	public void ejercicio04() {
		//Empleados contratados a partir del 2003
		List<Object[]> lista = em.createQuery("SELECT e.nombre , e.alta FROM EmpleadoEntity e WHERE  e.alta > 2003 ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+ objects[1]);
		}
	}
	public void ejercicio05() {
		//Empleados por orden alfabetico de departamento
		List<Object[]> lista = em.createQuery("SELECT d.nombre , d.empleados.nombre FROM DepartamentoEntity d order by d.nombre asc").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+ objects[1]);
		}
	}
	public void ejercicio06() {
		//Nombre, nº de empleados, total y maximo salario de los departamentos con empleados
		List<Object[]> lista = em.createQuery("SELECT d.nombre , COUNT (d.empleados) , SUM (d.empleados.salario) ,MAX (d.empleados.salario) FROM DepartamentoEntity d GROUP BY d.nombre").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+ objects[1]+" - "+objects[2]+" - "+objects[3]);
		}
	}
	public void ejercicio07() {
		//Idem de la anterior pero para departamentos a partir de 5 empleados
		List<Object[]> lista = em.createQuery("SELECT d.nombre ,COUNT (d.nombre), SUM (d.empleados.salario), MAX (d.empleados.salario) FROM DepartamentoEntity d GROUP BY d.nombre HAVING COUNT (d.nombre) > 5 ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+ objects[1]+" - "+objects[2]+" - "+objects[3]);
		}
	}
	public void ejercicio08() {
		//Cada empleado junto con su jefe
		List<Object[]> lista = em.createQuery("").getResultList();
	}
	public void ejercicio09() {
		//Nombre y total de empleados de los departamentos con algun empleado
		List<Object[]> lista = em.createQuery("SELECT d.nombre , COUNT (d.empleados.nombre) FROM DepartamentoEntity d GROUP BY d.nombre ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	public void ejercicio10() {
		//Nombre y total de empleados de TODOS los departamentos
		List<Object[]> lista = em.createQuery("SELECT d.nombre, COUNT (d.empleados) FROM DepartamentoEntity d GROUP BY d.nombre").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	public void ejercicio11() {
		//Ordenando descendentemente por departamento y ascendentemente por salario
		List<Object[]> lista = em.createQuery("SELECT d.dptoId, d.empleados.nombre , d.empleados.salario FROM DepartamentoEntity d ORDER BY d.dptoId desc , d.empleados.salario asc ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]);
		}
	}
	public void ejercicio12() {
		//Empleados sin jefe
		List<Object[]> lista = em.createQuery("").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]);
		}
	}
	public void ejercicio13() {
		//Departamento al que pertenece el empleado nº1039
		List<Object[]> lista = em.createQuery("SELECT e.departamento.dptoId, e.oficio FROM EmpleadoEntity e WHERE e.empnoId = 1039").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	
} // de la clase
