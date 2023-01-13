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
	//METODO PARA BUSCAR DEPARTAMENTO
	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		return em.find(DepartamentoEntity.class, numDepartamento);
	}
	//METODO PARA BUSCAR EMPLEADO PO ID
	public EmpleadoEntity buscarNombre (int empnoId) {
		return em.find(EmpleadoEntity.class, empnoId);
		
	}
	
<<<<<<< HEAD
=======
	public void imprimirEmpleado(int empnoId) {
		EmpleadoEntity emp = buscarNombre(empnoId);
		if(emp == null) {
			System.out.println("El empleado con id "+empnoId+". ¡No existe!");
		}
		String texto = "El nombre del empleado "+emp.getEmpnoId()+" es "+emp.getNombre();
		System.out.println(texto);
	}
>>>>>>> branch 'master' of git@github.com:bakero10/Acceso_a_datos.git
	//
	
	public void imprimirDepartamento (int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d==null)
			System.out.println("No existe el Departamento " + numDepartamento+"\n-------------------------------");
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
	  } //de metodo imprimirDepartamento
	
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
	
	// Si tiene empleados lo borrar�a igual, dejando en estos el numero de Departamento
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
	
	public int borrarDepartamento1(int numDepartamento) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("DELETE FROM DepartamentoEntity d WHERE d.dptoId =: c").setParameter("c", numDepartamento).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	public void demoJPQL() {	//TODOS SON LO MISMO DE DISTINTAS FORMAS
		
		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
        System.out.println("Total Departamentos: " + q1.getSingleResult());
        //
        TypedQuery<Long> tq1 = em.createQuery(
        	      "SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
        System.out.println("Total Departamentos: " + tq1.getSingleResult());
        //
        TypedQuery<DepartamentoEntity>tq2 =
	            em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
	        List<DepartamentoEntity> lista2 = tq2.getResultList();
	        for (DepartamentoEntity r2 : lista2) {
	            System.out.println("Nombre :  " + r2.getNombre()+ ", Localidad: "+ r2.getLocalidad());
	        }
            System.out.println("------------------------------------------------------------------");
	    //
        TypedQuery<Object[]>tq3 =
	            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d", Object[].class);
	        List<Object[]> lista3 = tq3.getResultList();
	        for (Object[] object : lista3) {
	            System.out.println(
	            "Nombre :  " + object[0] + ", Localidad: " + object[1]);
	    }   
            System.out.println("------------------------------------------------------------------");
	    //*/
		        List<Object[]> lista4 = em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity d WHERE d.dptoId != :n").setParameter("n", 10).getResultList();
		        for (Object[] object2 : lista4) {
		            System.out.println("Nombre :  " + object2[0] + ", Localidad: " + object2[1]);
		    }  
	            System.out.println("------------------------------------------------------------------");
	     
	}// de demoJPQL
	
	public void ejercicio01() {
		//Nombre y fecha de alta de todos los empleados
		List<Object[]> lista = em.createQuery("SELECT e.nombre , e.alta FROM EmpleadoEntity e").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio02() {
		//Igual que el anterior pero para aquellos que carrera forme parte del nombre.No distinguir mayusculas de minusculas
		List<Object[]> lista = em.createQuery("SELECT e.nombre , e.alta FROM EmpleadoEntity e WHERE e.nombre LIKE '%Carrera%'").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio03() {
		//Empleados del Departamento I+D cuyo oficio es el de Empleado
		List<Object[]> lista = em.createQuery("SELECT e.nombre ,e.oficio ,e.departamento.nombre FROM EmpleadoEntity e WHERE e.departamento.nombre LIKE 'I+D' and e.oficio like 'Empleado'").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]);
		}
	}
	
	public void ejercicio04() {
		//Empleados contratados a partir del 2003
		List<Object[]> lista = em.createQuery("SELECT e.nombre ,e.alta FROM EmpleadoEntity e WHERE year (e.alta) >= 2003 ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio05() {
		//Empleados por orden alfabetico de departamento
		List<Object[]> lista = em.createQuery("SELECT e.departamento.getNombre() ,e.nombre FROM EmpleadoEntity e ORDER BY e.departamento.getNombre() asc ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio06() {
		//Nombre, nº de empleados, total y maximo salario de los departamentos con empleados
		List<Object[]> lista = em.createQuery("SELECT e.departamento.nombre ,COUNT (e.nombre) ,SUM (e.salario) , MAX (e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]+" - "+objects[3]);
		}
	}
	
	public void ejercicio07() {
		//Idem de la anterior pero para departamentos a partir de 5 empleados
		List<Object[]> lista = em.createQuery("SELECT e.departamento.nombre ,COUNT (e.nombre) ,SUM (e.salario) , MAX (e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING COUNT (e.nombre) >= 5").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]+" - "+objects[3]);
		}
	}
	
	public void ejercicio08() {
		//Cada empleado junto con su jefe
		List<Object[]> lista = em.createQuery("SELECT e.nombre , e.dirId.nombre , e.departamento.getDptoId() FROM EmpleadoEntity e ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - su jefe es - "+objects[1]+" - departamento - "+objects[2]);
		}
	}
	
	public void ejercicio09() {
		//Nombre y total de empleados de los departamentos con algun empleado
		List<Object[]> lista = em.createQuery("SELECT e.departamento.nombre ,COUNT (e.nombre) FROM EmpleadoEntity e GROUP BY e.departamento.nombre ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio10() {
		//Nombre y total de empleados de TODOS los departamentos
		List<Object[]> lista = em.createQuery("SELECT d.nombre, COUNT(e) FROM DepartamentoEntity d left join d.empleados e group by d.nombre ").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	
	public void ejercicio11() {
		//Ordenando descendentemente por departamento y ascendentemente por salario
		List<Object[]> lista = em.createQuery("SELECT e.departamento.getDptoId() , e.nombre, e.salario FROM EmpleadoEntity e ORDER BY e.departamento desc , e.salario asc").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]+" - "+objects[2]);
		}
	}
	
	public void ejercicio12() {
		//Empleados sin jefe
<<<<<<< HEAD
		List<Object[]> lista = em.createQuery("SELECT e.empnoId, e.nombre FROM EmpleadoEntity e WHERE e.dirId is null ").getResultList();
=======
		List<Object[]> lista = em.createQuery("SELECT e.empnoId , e.nombre FROM EmpleadoEntity e WHERE e.dirId is null"
				).getResultList();
>>>>>>> branch 'master' of git@github.com:bakero10/Acceso_a_datos.git
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	public void ejercicio13() {
		List<Object[]> lista = em.createQuery("SELECT e.departamento.dptoId , e.departamento.getNombre() FROM EmpleadoEntity e WHERE empnoId = 1039").getResultList();
		for (Object[] objects : lista) {
			System.out.println(objects[0]+" - "+objects[1]);
		}
	}
	//EJEERCICIO09 Realiza los siguientes metodos 
	// Metodo public int incrementarSalario (int cantidad) para incrementar el salario de todos los empleados en la cantidad pasada
	// como argumentos. Utiliza la sentencia UPDATE con parametros. El método devuelve el numero de filas modificadas.
	
	public int incrementarSalario(int cantidad) {
		int resultado;
		em.getTransaction().begin();
			resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: PEPINO").setParameter("PEPINO", 										cantidad).executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	// Metodo public int incrementarSalarioOficio(String oficio,int cantidad). Idem del anterior pero solo para los empleados
	// de un departamento concreto.
	
	public int incrementarSalarioOficio(String oficio,int cantidad) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: cantidad WHERE e.oficio =: oficio").setParameter("cantidad", cantidad).setParameter("oficio", oficio).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	// IMPORTANTE IMPORTANTE IMPORTANTE IMPORTANTE IMPORTANTE IMPORTANTE IMPORTANTE
	
	// Metodo public int incrementarSalarioDepartamento(int numDepartamento, int cantidad). Idem del anterior pero solo para
	// los empleados de un departamento concreto.
	public int incrementarSalarioDepartamento(int numDepartamento, int cantidad) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario +: cantidad WHERE e.departamento.getDptoId() =: numDepartamento").setParameter("cantidad", cantidad).setParameter("numDepartamento", 											numDepartamento).executeUpdate(); 
			em.getTransaction().commit();
		return resultado;
	}
//	 Metodo public int borrarEmpleado (int numEmpleado) para borrar el empleado que se pasa como argumento 
//	 (Nota: Despues de hacerlo comprueba el metodo anterior de tal forma que si el empleado borrado es jefe 
//	 de algun otro empleado, este ultimo pasara a conetener null en su atribuo dirId). Utiliza la sentencia 
//	 DELETE con paramentros. El metodo devuelve el numero de filas borradas.
	
	public int borrarEmpleado (int numEmpleado) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("DELETE FROM EmpleadoEntity e WHERE e.empnoId =: n").setParameter("n", numEmpleado).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}
	// Metodo public int borrarDepartamento(int numDepartamento) para borrar el departamento que se pasa como argumento.
	// (Nota: Si el departamento borrado tiene empleados a su cargo, debido a la relacion establecida en el momento 
	// de creacion de la entidad, no se borra ninguno de estos, dejando ademas el atributo departamento de sus empleados 
	// con todos sus valores puestos a NULL excepto el dptoId que conserva su valor aunque corresponda a un departamento 
	// qye ya no existe). Utiliza la sentencia DELETE con parametros. El metodo devuelve el numero de filas borradas.
	
	public int borrarDepartament (int numDepartamento) {
		int resultado;
			em.getTransaction().begin();
				resultado = em.createQuery("DELETE FROM DepartamentoEntity d WHERE dptoId =: n ").setParameter("n", numDepartamento).executeUpdate();
			em.getTransaction().commit();
		return resultado;
	}

} // de la clase
