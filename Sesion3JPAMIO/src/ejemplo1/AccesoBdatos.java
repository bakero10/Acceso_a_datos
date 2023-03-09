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
// Alberto Carrera Martín - Abril 2020
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
	}// de método buscarDepartamento
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
				datos+= "\nNúmero de empleado: " + empleado.getEmpnoId()+ "\n";
				datos+= "Nombre: " + empleado.getNombre()+ "\n";
				datos+= "Oficio: " + empleado.getOficio()+ "\n";
				if (empleado.getDirId()==null)
					datos+= "Jefe: No tiene"+ "\n";
				else
					datos+= "Jefe: "+ empleado.getDirId().getNombre()+ "\n";
				datos+= "Año de alta: " + (empleado.getAlta().getYear()+1900)+ "\n";	
				datos+= "Salario: "+ empleado.getSalario()+ "\n";
				if (empleado.getComision() ==null)
					datos+= "Comisión: No tiene"+ "\n";
				else
					datos+= "Comisión: "+ empleado.getComision()+ "\n";
			}
			
			System.out.println(datos);
		}
	} // de método imprimirDepartamento
	
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
	
	// Si tiene empleados lo borraría igual, dejando en estos el número de Departamento
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
	public void ejercicio01() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	public void ejercicio02() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e WHERE e.nombre LIKE '%Carrera%'",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio03() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.nombre, e.oficio, e.departamento.nombre FROM EmpleadoEntity e WHERE e.departamento.nombre LIKE 'I+D'AND e.oficio LIKE 'Empleado'",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]+" - "+o[2]);
		}
	}
	public void ejercicio04() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e WHERE YEAR (e.alta) >= 2003",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio05() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.departamento.nombre, e.nombre FROM EmpleadoEntity e ORDER BY e.departamento.nombre asc ",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio06() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.departamento.nombre, COUNT (e.departamento.nombre), SUM (e.salario), MAX (e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]+" - "+o[2]+" - "+o[3]);
		}
	}
	public void ejercicio07() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.departamento.nombre, COUNT (e.departamento.nombre), SUM (e.salario), MAX (e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING COUNT (e.departamento.nombre) >= 5",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]+" - "+o[2]+" - "+o[3]);
		}
	}
	public void ejercicio08() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.nombre , e.dirId.nombre , e.departamento.dptoId FROM EmpleadoEntity e",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - su jefe es - "+o[1]+" - departamento - "+o[2]);
		}
	}
	public void ejercicio09() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.departamento.nombre, COUNT(e.departamento.nombre) FROM EmpleadoEntity e GROUP BY e.departamento.nombre",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio09Santi() {
		TypedQuery<Object[]> q = em.createQuery("SELECT d.nombre, count(e) FROM DepartamentoEntity d join d.empleados e GROUP BY d.nombre",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}
	public void ejercicio10() {
		TypedQuery<Object[]> q = em.createQuery("SELECT e.departamento.nombre, COUNT(e.departamento.nombre) FROM EmpleadoEntity e GROUP BY e.departamento.nombre",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio10Santi() {
		TypedQuery<Object[]> q = em.createQuery("SELECT d.nombre, count(e) FROM DepartamentoEntity d left join d.empleados e GROUP BY d.nombre",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}
	public void ejercicio11() {
		TypedQuery<Object[]> q = em.createQuery(" ",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]+" - "+o[2]);
		}
	}
	public void ejercicio12() {
		TypedQuery<Object[]> q = em.createQuery(" ",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public void ejercicio13() {
		TypedQuery<Object[]> q = em.createQuery(" ",Object[].class);
		List<Object[]> lista = q.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0]+" - "+o[1]);
		}
	}
	public int incrementarSalario (int cantidad) {
		int resultado;
		em.getTransaction().begin();
			resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: cantidad ").setParameter("cantidad", cantidad).executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	public int incrementarSalarioOficio(String oficio, int cantidad) {
		int resultado;
			em.getTransaction().begin();
			resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: cantidad WHERE oficio LIKE: oficio ").setParameter("cantidad", cantidad).setParameter("oficio", oficio).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	public int incrementarSalarioDepartamento (int numDepartamento, int cantidad) {
		int resultado;
			em.getTransaction().begin();
			resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: cantidad WHERE e.departamento.getDptoId() =: numDepartamento").setParameter("cantidad", cantidad).setParameter("numDepartamento",numDepartamento).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	public int borrarEmpleado(int numEmpleado) {
		int resultado;
			em.getTransaction().begin();
			resultado = em.createQuery("DELETE FROM EmpleadoEntity e WHERE e.empnoId =: numEmpleado").setParameter("numEmpleado", numEmpleado).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	public int borrarDepartamento2(int numDepartamento) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("DELETE FROM EmpleadoEntity e WHERE e.departamento.dptoId =: numDepartamento").setParameter("numDepartamento", numDepartamento).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	
} // de la clase
