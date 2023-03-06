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
	public void imprimirDepartamento(int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d == null)
			System.out.println("No existe el Departamento " + numDepartamento);
		else {
			Set<EmpleadoEntity> empleados = d.getEmpleados();
			String datos = "Datos del departamento " + numDepartamento + ": ";
			datos += "\nNombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad() + "\n\n";

			if (empleados.isEmpty())
				datos += "No tiene empleados en este momento";
			else {
				datos += "Lista de empleados" + "\n";
				datos += "*******************";
				for (EmpleadoEntity empleado : empleados) {
					datos += "\nNúmero de empleado: " + empleado.getEmpnoId() + "\n";
					datos += "Nombre: " + empleado.getNombre() + "\n";
					datos += "Oficio: " + empleado.getOficio() + "\n";
					if (empleado.getDirId() == null)
						datos += "Jefe: No tiene" + "\n";
					else
						datos += "Jefe: " + empleado.getDirId().getNombre() + "\n";
					datos += "Año de alta: " + (empleado.getAlta().getYear() + 1900) + "\n";
					datos += "Salario: " + empleado.getSalario() + "\n";
					if (empleado.getComision() == null)
						datos += "Comisión: No tiene" + "\n";
					else
						datos += "Comisión: " + empleado.getComision() + "\n";
				}
			}

			System.out.println(datos);
		}
	} // de método imprimirDepartamento

	public boolean insertarDepartamento(DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId()) != null) // Si ya existe el departamento, no te deja insertarlo
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento

	public boolean modificarDepartamento(DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado = buscarDepartamento(d.getDptoId());
		if (departamentoBuscado == null) // Si no existe el departamento con anterioridad no te deja modificarlo
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento

	// Si tiene empleados lo borraría igual, dejando en estos el número de
	// Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo
	// borrar. Esto pasa por no haber definido en la relacion el cascade

	public boolean borrarDepartamento(int numDepartamento) {
		DepartamentoEntity departamentoBuscado = buscarDepartamento(numDepartamento);
		// No deja borrar si no existe el departamento o si el departamento tiene
		// empleados asignados
		if (departamentoBuscado == null || !departamentoBuscado.getEmpleados().isEmpty())
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento

	// Probanco consultas JPQL
	public void demoJPQL() {

		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
		System.out.println("Total Departamentos: " + q1.getSingleResult());
		//
		TypedQuery<Long> tq1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
		System.out.println("Total Departamentos: " + tq1.getSingleResult());
		//
		TypedQuery<DepartamentoEntity> tq2 = em.createQuery("SELECT d FROM DepartamentoEntity d",
				DepartamentoEntity.class);
		List<DepartamentoEntity> l2 = tq2.getResultList();
		for (DepartamentoEntity r2 : l2) {
			System.out.println("Nombre :  " + r2.getNombre() + ", Localidad: " + r2.getLocalidad());
		}
		//
		TypedQuery<Object[]> tq3 = em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d",
				Object[].class);
		List<Object[]> l3 = tq3.getResultList();
		for (Object[] r3 : l3) {
			System.out.println("Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
		}
		// */
		TypedQuery<Object[]> tq4 = em.createQuery(
				"SELECT d.nombre, d.localidad FROM DepartamentoEntity d" + " WHERE d.dptoId != :n", Object[].class);
		tq4.setParameter("n", 10);
		List<Object[]> l4 = tq4.getResultList();
		for (Object[] r4 : l4) {
			System.out.println("Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		}

		/*************************************************************/
		/** ++++++++++++++++RESOLVIENDO EJERCICIO 8++++++++++++++++ **/
		/*************************************************************/
		
		//Nombre y fecha de alta de todos los empleados
		System.out.println();
		TypedQuery<Object[]> tq5 = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e", Object[].class);
		List<Object[]> l5 = tq5.getResultList();
		for (Object[] o : l5) {
			System.out.println(o[0] + " - " + o[1]);
		}

		//Ídem de la anterior pero para aquellos que "Carrera" forma parte del nombre. No distinguir mayúsculas de
		//minúsculas
		System.out.println();
		TypedQuery<Object[]> tq6 = em.createQuery(
				"SELECT e.nombre, e.alta FROM EmpleadoEntity e WHERE UPPER(e.nombre) LIKE '%CARRERA%' ",
				Object[].class);
		List<Object[]> l6 = tq6.getResultList();
		for (Object[] o : l6) {
			System.out.println(o[0] + " - " + o[1]);
		}

		//Empleados del Departamento I+D cuyo oficio es el de Empleado
		System.out.println();
		TypedQuery<Object[]> tq7 = em.createQuery(
				"SELECT e.nombre, e.oficio, e.departamento.nombre FROM EmpleadoEntity e WHERE e.departamento.nombre LIKE 'I+D' and e.oficio LIKE 'Empleado' ",
				Object[].class);
		List<Object[]> l7 = tq7.getResultList();
		for (Object[] o : l7) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}

		//Empleados contratados a partir del 2003
		System.out.println();
		// TypedQuery<Object[]> tq8 = em.createQuery(
		// "SELECT e.nombre, e.alta FROM EmpleadoEntity e WHERE e.alta.getYear()+1900 >=
		// 2003 ", -- Se pueden utilizar metodos de java
		// Object[].class);
		TypedQuery<Object[]> tq8 = em.createQuery(
				"SELECT e.nombre, e.alta FROM EmpleadoEntity e WHERE year(e.alta) >= 2003 ", Object[].class);
		List<Object[]> l8 = tq8.getResultList();
		for (Object[] o : l8) {
			System.out.println(o[0] + " - " + o[1]);
		}

		//Empleados por orden alfabético de departamento
		System.out.println();
		TypedQuery<Object[]> tq9 = em.createQuery(
				"SELECT e.departamento.nombre, e.nombre FROM EmpleadoEntity e order by e.departamento.nombre ",
				Object[].class);
		List<Object[]> l9 = tq9.getResultList();
		for (Object[] o : l9) {
			System.out.println(o[0] + " - " + o[1]);
		}

		//Nombre, no de empleados, total y máximo salario de los departamentos con empleados
		System.out.println();
		TypedQuery<Object[]> tq10 = em.createQuery(
				"SELECT e.departamento.nombre, count(e), SUM(e.salario), MAX(e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre",
				Object[].class);
		List<Object[]> l10 = tq10.getResultList();
		for (Object[] o : l10) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
		}

		//Ídem de la anterior pero para departamentos a partir de 5 empleados
		System.out.println();
		TypedQuery<Object[]> tq11 = em.createQuery(
				"SELECT e.departamento.nombre, count(e), SUM(e.salario), MAX(e.salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING count(e) >= 5 ",
				Object[].class);
		List<Object[]> l11 = tq11.getResultList();
		for (Object[] o : l11) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
		}

		//Cada empleado junto con su jefe
		System.out.println();
		TypedQuery<Object[]> tq12 = em.createQuery(
				"SELECT e.nombre, e.dirId.nombre, e.departamento.dptoId FROM EmpleadoEntity e ",
				Object[].class);
		List<Object[]> l12 = tq12.getResultList();
		for (Object[] o : l12) {
			System.out.println(o[0] + " - su jefe es - " + o[1] + " - departamento - " + o[2]);
		}
		
		//Nombre y total de empleados de los departamentos con algún empleado
		System.out.println();
		TypedQuery<Object[]> tq13 = em.createQuery(
				"SELECT d.nombre, count(e) FROM DepartamentoEntity d join d.empleados e GROUP BY d.nombre",
				Object[].class);
		List<Object[]> l13 = tq13.getResultList();
		for (Object[] o : l13) {
			System.out.println(o[0] + " - " + o[1]);
		}
		
		//Nombre y total de empleados de TODOS los departamentos
		System.out.println();
		//Como la relacion de clave ajena ya esta definida en las clases con lo del OneToMany,OneToOne, ManyToMany... no hace falta indicarles con el
		//join un "on idtal = idtal", por que ya se sobre entiende al tener la relacion @meny.. ya definida. para hacer la relacion, basta con indicarle
		//el atributo de que contiene la relacion, en este caso d.empleados, y con esto ya sobre entiende que campos id necesita y con lo cual, no hace falta definir el "on tal = tal"
		TypedQuery<Object[]> tq14 = em.createQuery(
				"SELECT d.nombre, count(e) FROM DepartamentoEntity d left join d.empleados e group by d.nombre",
				Object[].class);
		List<Object[]> l14 = tq14.getResultList();
		for (Object[] o : l14) {
			System.out.println(o[0] + " - " + o[1]);
		}
		
		//Ordenando descendentemente por departamento y ascendentemente por salario
		System.out.println();
		TypedQuery<Object[]> tq15 = em.createQuery(
				"SELECT e.departamento.dptoId, e.nombre, e.salario FROM EmpleadoEntity e order by e.departamento.dptoId desc, e.salario asc ",
				Object[].class);
		List<Object[]> l15 = tq15.getResultList();
		for (Object[] o : l15) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}

		//Empleados sin jefe.
		System.out.println();
		TypedQuery<Object[]> tq16 = em.createQuery(
				"SELECT e.empnoId, e.nombre FROM EmpleadoEntity e where e.dirId is null ",
				Object[].class);
		List<Object[]> l16 = tq16.getResultList();
		for (Object[] o : l16) {
			System.out.println(o[0] + " - " + o[1]);
		}
		
		//Departamento al que pertenece el empleado no 1039
		System.out.println();
		TypedQuery<Object[]> tq17 = em.createQuery(
				"SELECT e.departamento.dptoId, e.departamento.nombre FROM EmpleadoEntity e where e.empnoId = :id ",
				Object[].class).setParameter("id", 1039);
		List<Object[]> l17 = tq17.getResultList();
		for (Object[] o : l17) {
			System.out.println(o[0] + " - " + o[1]);
		}
				
	}// de demoJPQL
	
	
	/*************************************************************/
	/** ++++++++++++++++RESOLVIENDO EJERCICIO 9++++++++++++++++ **/
	/*************************************************************/
	
	public int incrementarSalario(int cantidad) {
		/** Manera mas larga
		 TypedQuery<Integer> consulta = em.createQuery("update EmpleadoEntity e set e.salario = e.salario + :n", Integer.class).setParameter("n", cantidad);
		 int result = consulta.executeUpdate();
		**/
		int resultado;
		em.getTransaction().begin();
		resultado = em.createQuery("UPDATE EmpleadoEntity e set e.salario = e.salario+:cantidad").setParameter("cantidad", cantidad).executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	public int incrementarSalarioOficio(String oficio, int cantidad) {
		int resultado;
		em.getTransaction().begin();
		resultado = em.createQuery("UPDATE EmpleadoEntity e set e.salario = e.salario+:cantidad "
				+ "where e.oficio LIKE :oficio").setParameter("cantidad", cantidad).setParameter("oficio", oficio).executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	//No se por que, pero en un update, al intentar consultar por los atributos de la tabla(clase) que tiene relacionada, no es capaz de recuperar o ver 
	//el atributo directamente, sin embargo, podemos preguntar por su getter y si nos devuelve el atributo que necesitamos
	public int incrementarSalarioDepartamento(int departamento, int cantidad) {
		int resultado;
		em.getTransaction().begin();
		resultado = em.createQuery("UPDATE EmpleadoEntity e set e.salario = e.salario+:cantidad "
				+ "where e.departamento.getDptoId() = :idDep").setParameter("idDep", departamento).setParameter("cantidad", cantidad).executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	 public int borrarEmpleado (int numEmpleado) {
		 int resultado;
		 em.getTransaction().begin();
		 resultado = em.createQuery("DELETE FROM EmpleadoEntity e WHERE e.empnoId = :n").setParameter("n", numEmpleado).executeUpdate();
		 em.getTransaction().commit();
		 return resultado;
	 }
	
	 public int borrarDepartamentoJPQL(int numDepartamento) {
		 int resultado;
		 em.getTransaction().begin();
		 resultado = em.createQuery("DELETE FROM DepartamentoEntity d WHERE d.dptoId = :n").setParameter("n", numDepartamento).executeUpdate();
		 em.getTransaction().commit();
		 return resultado;
	 }
	
	
//--------------------------------------------------------------------------------------------------------------

} // de la clase
