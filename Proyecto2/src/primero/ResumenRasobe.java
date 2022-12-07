//package clases;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.TransactionException;
//
//public class Programa {
//
//	static SessionFactory sf = SessionFactoryUtil.getSessionFactory();
//	static Session s = sf.openSession();
//
//	public static void main(String[] args) {
//
//		short id = 1;
//
////		obtenerEmpleadoPorId(id);
//
////		insertarNuevoEmpleado(new Empleados((short) 2, (Departamentos) s.get(Departamentos.class, (byte) 30),
////				"ANGARONA", "SSL", (short) 7839, new Date(), (float) 5000, null));
////
////		eliminarEmpleadoNoDirector((short) 1);
//
////		actualizarEmpleado(new Empleados((short)2, (Departamentos) s.get(Departamentos.class, (byte)30), "ANGARONA", "SSL", (short)7839, new Date(), (float) 9000.12, null));
//		
////		guardarOactualizar(new Empleados((short)2, (Departamentos) s.get(Departamentos.class, (byte)30), "ANGARONA", "BRONCE", (short)7839, new Date(), (float) 1000.12, null));
//		
////		precedimientoPorId(id);
//		
//		System.out.println(recogerEmpleadoId(id).getApellido());
//		
////		for (Object[] o : obtenerListaConXColumnas()) {
////			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
////		}
//		
////		for (Empleados e: obtenerEmpleadosPorIdDepartamento((byte)10)) {
////			System.out.println(e.getEmpNo() + " - " + e.getApellido() + " - " + e.getOficio() + " - " + e.getComision() + " - " + e.getDir() + " - " + e.getSalario() + " - " + e.getDepartamentos().getDnombre() + " - " + e.getFechaAlt());
////		}
//		
////		todosDepartamentosConSusEmpleados();
//
//		s.close();
//		sf.close();
//	}
//
//	public static Empleados obtenerEmpleadoPorId(short id) {
//		Empleados e = (Empleados) s.get(Empleados.class, id);
//
//		if (e == null) {
//			System.out.println("No se ha encontrado ningÃºn empleado con esta id");
//			return null;
//		} else {
//			if (e.getComision() == null) {
//				e.setComision((float) 0);
//			}
//			return e;
//		}
//
//	}
//
//	public static void insertarNuevoEmpleado(Empleados e) {
//
//		Transaction tx = s.beginTransaction();
//		try {
//			if (obtenerEmpleadoPorId(e.getEmpNo()) == null) {
//				s.save(e);
//				System.out.println("Empleado insertado correctamente");
//				tx.commit();
//			} else {
//				System.out.println("No se puede insertar un empleado con el mismo id");
//				tx.rollback();
//			}
//		} catch (Exception e2) {
//			System.out.println("Error al insertar");
//			tx.rollback();
//		}
//
//	}
//
//	public static void eliminarEmpleadoNoDirector(short id) {
//		
//		Empleados e = obtenerEmpleadoPorId(id);
//		Transaction tx = s.beginTransaction();
//		try {
//			if (e != null) {
//				System.out.println(e.getOficio());
//				if (e.getOficio().equalsIgnoreCase("DIRECTOR")) {
//					System.out.println("No se puede eliminar un director");
//				} else {
//					s.delete(e);
//					tx.commit();
//					System.out.println("Empleado eliminado correctamente!");
//				}
//			}
//		} catch (TransactionException e2) {
//			System.out.println("No puedes borrar un director");
//			tx.rollback();
//		}
//
//	}
//
//	public static void actualizarEmpleado(Empleados e) {
//
//		Transaction tx = s.beginTransaction();
//
//		try {
//			Empleados empActualizar = obtenerEmpleadoPorId(e.getEmpNo());
//			empActualizar.setApellido(e.getApellido());
//			empActualizar.setComision(e.getComision());
//			empActualizar.setDepartamentos(e.getDepartamentos());
//			empActualizar.setDir(e.getDir());
//			empActualizar.setFechaAlt(e.getFechaAlt());
//			empActualizar.setOficio(e.getOficio());
//			empActualizar.setSalario(e.getSalario());
//			s.update(empActualizar);
//			tx.commit();
//		} catch (Exception e2) {
//			tx.rollback();
//		}
//		
//	}
//	
//	public static void guardarOactualizar(Empleados e) {
//		Transaction tx = s.beginTransaction();
//		try {
//			s.saveOrUpdate(e);
//			tx.commit();
//			System.out.println("Usuario guardado o actualizado correctamente!");
//		} catch (Exception e2) {
//			tx.rollback();
//			System.out.println("Error");
//		}
//		
//	}
//	
//	public static void precedimientoPorId(short id) {
//		Object[] objeto = (Object[]) s.createSQLQuery("call llamarEmpId(:id)").setShort("id", id).uniqueResult();
//		System.out.println(objeto[0] + " - " +  objeto[1] + " - " +objeto[2] + " - " + objeto[3]);
//	}
//	
//	public static List<Object[]> obtenerListaConXColumnas() {
//		return s.createQuery("select empNo, apellido, departamentos.dnombre, salario "
//							+ "from Empleados").list();
//	}
//	
//	public static List<Empleados> obtenerEmpleadosPorIdDepartamento(byte id) {
//		return (List<Empleados>) s.createQuery("select d.empleadoses from Departamentos as d where d.deptNo = :id").setByte("id", id).list();
//	}
//	
//	public static void todosDepartamentosConSusEmpleados() {
//		for (Departamentos d : (List<Departamentos>) s.createQuery("from Departamentos").list()) {
//			System.out.println("Departamento: " + d.getDnombre() + "\n===================================================================================");
//			for (Object o : d.getEmpleadoses()) {
//				Empleados e = (Empleados) o;
//				System.out.println(" - Empleado id: " + e.getEmpNo() + ", apellido: " + e.getApellido() + ", oficio: " + e.getOficio() + ", salario: " + e.getSalario());
//			}
//			System.out.println();
//		}
//	}
//	
//	public static Empleados recogerEmpleadoId(short id) {
//		return (Empleados) s.createSQLQuery("call recogerEmpleadoId(:id)").addEntity(Empleados.class).setShort("id", id).uniqueResult();
//	}
//
//}