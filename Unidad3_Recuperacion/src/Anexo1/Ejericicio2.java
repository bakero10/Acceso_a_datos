package Anexo1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

public class Ejericicio2 {
	public static void main(String[] args) {
		
//		// TRANSACCIONES
//		// IMPORTANTE 
//		// 1- En el conecta(): conecta.setAutoCommit(false);
//		//    En el desconectar(): conecta.setAutoCommit(true);
//		// 2- Despues del executeQuery conecta.commit();
//		// 3- Utilizar el rollback en los catch;
//		
//		
//		private static String driver = "com.mysql.cj.jdbc.Driver";
//		private static String database = "baloncesto";
//		private static String hostname = "localhost";
//		private static String port = "3306";
//		private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
//		private static String username = "root";
//		private static String password = "nacarino";
//		
//		private Connection conecta; //Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo en el metodo conecta 
//		
//
//		public void conectar() {
//			try {
//			Class.forName(driver);
//			conecta = DriverManager.getConnection(url, username, password); //Cuando ejecutemos conectar, iniciaremos todos los pasos para conectar
//			conecta.setAutoCommit(false);
//			}catch (ClassNotFoundException cnf) {
//				System.out.println("Clase driver no encontrada");
//			}catch (SQLException sqle) {
//				System.out.println("Error SQL en la conexion");
//			}
//		}
//		
//		public void desconectar() {
//			try {
//			if (conecta !=null) { //Si en conecta hay algo, entonces desconecta. Con esto tenemos un metodo que cierre la conexion
//				conecta.close();
//			}
//			}catch (SQLException sqle) {
//				System.out.println("Error al desconectar");
//			}
//		}
//		
//		public int ultimoId() {
//			int id = 0;
//			String sql = "SELECT socioId FROM socio ORDER BY socioId DESC LIMIT 1";
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				if (rs.next()) {
//				    id = rs.getInt(1) + 1;
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return id;
//		}
//		
//		//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
//		//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
//		//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
//			
//		public ResultSet consultaLocalidad(String localidad){
//			ResultSet salida = null;
//			if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
//				try {
//				Statement consulta = conecta.createStatement();
//				ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
//				conecta.commit();
//					
//				if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
//					salida = reg;
//				}else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
//					salida = null;
//				}
//				}catch(SQLException sqle) {
//					try {
//						conecta.rollback();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
//				}
//			}else {//Si esta vacio ejecuta consulta que devuelva todos los usuarios
//				try {
//					Statement consulta = conecta.createStatement();
//					ResultSet reg = consulta.executeQuery ("SELECT * FROM socio");
//						
//					if (reg.next()) { //Si hay siguiente devolvera un result set con todos los socios
//						salida = reg;
//					}else {
//						salida = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
//					}
//					}catch(SQLException sqle) {
//						try {
//							conecta.rollback();
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						System.out.println("Error en la consulta para mostrar todos los socios");
//					}
//			}
//			return salida;
//		}
//		
//		// REPASO PARA EL EXAMEN
//		
//		public void actualizarSocio(String nombre,int estatura,int edad,String localidad,int socioID) {
//			
//			String sql = "UPDATE socio SET nombre=?, estatura=?, edad=?, localidad=? WHERE socioID=?";
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				ps.setString(1, nombre);
//		        ps.setInt(2, estatura);
//		        ps.setInt(3, edad);
//		        ps.setString(4, localidad);
//		        ps.setInt(5, socioID);
//		        
//		        int filasActualizadas = ps.executeUpdate();
//		        conecta.commit();
//		        if(filasActualizadas > 0) {
//		        	System.out.println("Se actualizo correctamente el socio");
//		        }
//		        else {
//		        	System.out.println("No se ha actualizado el socio");
//		        }
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} 
//		}
//		public void nuevoSocio(String nombre,int estatura,int edad,String localidad) {
//			String sql = "INSERT INTO socio VALUES (?,?,?,?,?)";
//			try {
//				
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				int id = ultimoId();
//				ps.setInt(1, id);
//				ps.setString(2, nombre);
//				ps.setInt(3, estatura);
//				ps.setInt(4,edad);
//				ps.setString(5, localidad);
//				int filasActualizadas = ps.executeUpdate();
//				conecta.commit();
//				if(filasActualizadas > 0) {
//					System.out.println("Socio insertado");
//				}
//				else {
//					System.out.println("Error en la insercion");
//				}
//			} catch (SQLException e) {
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		public void borrarSocio(int socioId) {
//			String sql = "DELETE FROM socio WHERE socioId ="+socioId;
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				int filasBorradas = ps.executeUpdate();
//				conecta.commit();
//				if(filasBorradas > 0) {
//					System.out.println("Se borro el socio!");
//				}
//				else {
//					System.out.println("No se pudo borrar el socio");
//				}
//			} catch (SQLException e) {
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//		
//	// HASTA AQUI LAS TRANSACCIONES
		
//		/*
//		 * Los metodos de la parte del controller desde donde llamaremos a los metodos de la bd
//		 */
//		//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL A�O DAM2 PARA ACTUALIZAR, BORRAR Y A�ADIR SOCIOS
//				if(event.getSource() == view.actualizar) { //Actualizar socio
//					String nombre, estatura, edad, localidad, id;
//					id = view.cajaId.getText();
//					nombre = view.cajaNombre.getText();
//					estatura = view.cajaEstatura.getText();
//					edad = view.cajaEdad.getText();
//					localidad = view.cajaLocalidad.getText();
//					
//					int resultado = actualizarSocio(Integer.valueOf(id),nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
//					if(resultado == 1) {
//						JOptionPane.showMessageDialog(null,"Registro actualizado correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
//					}else {
//						JOptionPane.showMessageDialog(null,"El registro no se ha podido actualizar" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
//					}
//					
//				}
//				
//				
//				//////
//				if(event.getSource() == view.nuevo) { //Anadir socio
//					String nombre, estatura, edad, localidad, id;
//					id = view.cajaId.getText();
//					nombre = view.cajaNombre.getText();
//					estatura = view.cajaEstatura.getText();
//					edad = view.cajaEdad.getText();
//					localidad = view.cajaLocalidad.getText();
//					
//					int resultado = aniadirSocio(nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
//					if(resultado == 1) {
//						JOptionPane.showMessageDialog(null,"Se ha creado un socio nuevo correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
//					}else {
//						JOptionPane.showMessageDialog(null,"No se ha podido crear el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//				
//				/////
//				if(event.getSource() == view.borrar) { //Borrar socio
//					String nombre, estatura, edad, localidad, id;
//					
//					id = view.cajaId.getText();
//					nombre = view.cajaNombre.getText();
//					estatura = view.cajaEstatura.getText();
//					edad = view.cajaEdad.getText();
//					localidad = view.cajaLocalidad.getText();
//						
//					int resultado = borrarSocio(Integer.valueOf(id));
//					if(resultado == 1) {
//						JOptionPane.showMessageDialog(null,"Se ha borrado el socio correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
//					}else {
//						JOptionPane.showMessageDialog(null,"No se ha podido borrar el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
		
		// HASTA AQUI LOS PROCEDIMIENTOS DEL CONTROLLER
		
		
		/*
		 * FUNCIONES Y PROCEDIMIENTOS CALLABLESTATEMENT
		 */
//		public static void main(String[] args) 
//			     throws ClassNotFoundException, SQLException {
//					
//				  Class.forName(driver);
//				  Connection conn = DriverManager.getConnection(url, username, password);
//			      // Creo la llamada a la funci�n de manera muy parecida 
//				  // a la empleada para sentencias preparadas
//				  CallableStatement proc = conn.prepareCall("Select totalEmpleados(?) ");
//			      // Acoplamiento de variables: El ? de la l�nea anterior
//				  // es un entero de valor 110
//				   proc.setInt(1, 110); // Quiero saber el n�mero empleados del departamento 110            
//			       // Ejecuto la llamada y recojo las filas resultantes 
//				   // en un ResultSet. Realmente, solo una fila y una �nica columna 
//				   // que contendr�  la cuenta de empleados de ese departamento
//				   ResultSet rs=proc.executeQuery();
//			       //Declaro una variable donde recoger el valor de la columna
//				   // (se puede hacer directamente sin esta variable)
//				   int numero=0;
//			       if (rs.next()) {
//			    	   numero = rs.getInt(1);
//			           System.out.println("N�mero empleados Departamento 110 --> " + numero);
//			       }
//			       else
//			    	   System.out.println("No se recuperaron datos");
//			       rs.close();
//			    proc.close();   
//				conn.close();
		
		
//		public static void main(String[] args) 
//			     throws ClassNotFoundException, SQLException {
//				   Class.forName(driver);
//				   Connection conn = DriverManager.getConnection(url, username, password);	
//				   // Creo la llamada al procedimiento de manera muy parecida 
//				   // a la empleada para sentencias preparadas
//				   CallableStatement proc = conn.prepareCall(" CALL jefeyPresupuesto(?,?,?) ");
//			       // Acoplamiento de variables
//				   // El primer dato ? es un entero y le asigno el valor 120
//				   proc.setInt(1, 120); // Quiero saber el director y presupuesto departamento 120
//			       // El segundo dato ? es un entero y adem�s un par�metro de salida, de tipo OUT
//				   proc.registerOutParameter(2, Types.INTEGER);
//			       // El tercer dato ? es tambi�n un entero y de salida como el anterior
//				   proc.registerOutParameter(3, Types.INTEGER);
//			       // Ejecuto el procedimiento
//				   proc.execute();            
//			       System.out.println("Presupuesto y Director del departamento 120:");
//			       // Imprimo el 2� y 3� par�metro de salida
//			       System.out.println(proc.getInt(2) + " - " + proc.getInt(3));
//			    proc.close();   
//				conn.close();
//			  }
		
//		public static void main(String[] args) 
//			     throws ClassNotFoundException, SQLException {
//					
//				     Class.forName(driver);
//					 Connection conn = DriverManager.getConnection(url, username, password);
//			         CallableStatement proc = conn.prepareCall(" CALL pesetasAeuros(?) ");
//			         double cantidad = 25000; // Quiero convertir 25000 pesetas a euros
//			         //Acoplamiento. El par�metro o argumento 1� es un real, en concreto cantidad
//			         proc.setDouble(1,cantidad); 
//			         // El par�metro 1� tambi�n es a su vez de salida y evidentemente de tipo real
//			         proc.registerOutParameter(1, Types.DOUBLE);
//			         // Ejecuto el procedimiento
//			         proc.execute();            
//			         System.out.print("Convertir 25.000 pesetas a euros -->");
//			         // Imprimo el argumento de salida
//			         System.out.println(proc.getDouble(1));
//			       
//			    proc.close();   
//				conn.close();
//			  }
		
		// HASTA AQUI LOS PROCEDIMIENTOS DE CALLABLESTATEMNT
		
		/*
		 *  METODOS NORMALES DEL TEMA
		 */
//		private Connection conecta; //Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo en el metodo conecta 
//		
//
//		public void conectar() {
//			try {
//			Class.forName(driver);
//			conecta = DriverManager.getConnection(url, username, password); //Cuando ejecutemos conectar, iniciaremos todos los pasos para conectar
//			conecta.setAutoCommit(false);
//			}catch (ClassNotFoundException cnf) {
//				System.out.println("Clase driver no encontrada");
//			}catch (SQLException sqle) {
//				System.out.println("Error SQL en la conexion");
//			}
//		}
//		
//		public void desconectar() {
//			try {
//			if (conecta !=null) { //Si en conecta hay algo, entonces desconecta. Con esto tenemos un metodo que cierre la conexion
//				conecta.close();
//			}
//			}catch (SQLException sqle) {
//				System.out.println("Error al desconectar");
//			}
//		}
//		
//		public int ultimoId() {
//			int id = 0;
//			String sql = "SELECT socioId FROM socio ORDER BY socioId DESC LIMIT 1";
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				if (rs.next()) {
//				    id = rs.getInt(1) + 1;
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return id;
//		}
//		
//		//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
//		//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
//		//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
//			
//		public ResultSet consultaLocalidad(String localidad){
//			ResultSet salida = null;
//			if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
//				try {
//				Statement consulta = conecta.createStatement();
//				ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
//				conecta.commit();
//					
//				if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
//					salida = reg;
//				}else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
//					salida = null;
//				}
//				}catch(SQLException sqle) {
//					try {
//						conecta.rollback();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
//				}
//			}else {//Si esta vacio ejecuta consulta que devuelva todos los usuarios
//				try {
//					Statement consulta = conecta.createStatement();
//					ResultSet reg = consulta.executeQuery ("SELECT * FROM socio");
//						
//					if (reg.next()) { //Si hay siguiente devolvera un result set con todos los socios
//						salida = reg;
//					}else {
//						salida = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
//					}
//					}catch(SQLException sqle) {
//						try {
//							conecta.rollback();
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						System.out.println("Error en la consulta para mostrar todos los socios");
//					}
//			}
//			return salida;
//		}
//		
//		// REPASO PARA EL EXAMEN
//		
//		public void actualizarSocio(String nombre,int estatura,int edad,String localidad,int socioID) {
//			
//			String sql = "UPDATE socio SET nombre=?, estatura=?, edad=?, localidad=? WHERE socioID=?";
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				ps.setString(1, nombre);
//		        ps.setInt(2, estatura);
//		        ps.setInt(3, edad);
//		        ps.setString(4, localidad);
//		        ps.setInt(5, socioID);
//		        
//		        int filasActualizadas = ps.executeUpdate();
//		        conecta.commit();
//		        if(filasActualizadas > 0) {
//		        	System.out.println("Se actualizo correctamente el socio");
//		        }
//		        else {
//		        	System.out.println("No se ha actualizado el socio");
//		        }
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} 
//		}
//		public void nuevoSocio(String nombre,int estatura,int edad,String localidad) {
//			String sql = "INSERT INTO socio VALUES (?,?,?,?,?)";
//			try {
//				
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				int id = ultimoId();
//				ps.setInt(1, id);
//				ps.setString(2, nombre);
//				ps.setInt(3, estatura);
//				ps.setInt(4,edad);
//				ps.setString(5, localidad);
//				int filasActualizadas = ps.executeUpdate();
//				conecta.commit();
//				if(filasActualizadas > 0) {
//					System.out.println("Socio insertado");
//				}
//				else {
//					System.out.println("Error en la insercion");
//				}
//			} catch (SQLException e) {
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		public void borrarSocio(int socioId) {
//			String sql = "DELETE FROM socio WHERE socioId ="+socioId;
//			try {
//				PreparedStatement ps = conecta.prepareStatement(sql);
//				int filasBorradas = ps.executeUpdate();
//				conecta.commit();
//				if(filasBorradas > 0) {
//					System.out.println("Se borro el socio!");
//				}
//				else {
//					System.out.println("No se pudo borrar el socio");
//				}
//			} catch (SQLException e) {
//				try {
//					conecta.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	//probar la logica en el main antes
			
			// POOL DE CONEXIONES
		
//		 public Connection getConnection() throws SQLException {
//		        if (connectionPool.isEmpty()) {
//		            if (usedConnections.size() < MAX_POOL_SIZE) {
//		                connectionPool.add(createConnection(url, user, password));
//		            } else {
//		                throw new RuntimeException("Maximum pool size reached, no available connections!");
//		            }
//		        }
//
//		        Connection connection = connectionPool.remove(connectionPool.size() - 1);
//		        usedConnections.add(connection);
//		        return connection;
//		    }
		
		/*
		 * Metodo getSize() devuelve el total de conexiones(suma de las disponibles mas las utlizadas)
		 * Metodo getConnectioPool() devuelve la lista de conexiones disponibles
		 * Metodo getUsedConnections() devuelve la lista de conexiones que estan en uso
		 * Metodo shutdown() libera la lista de conexiones utilizadas que pasan a la lista de conexiones
		 * disponibles, y a continuacion cierra todas estas ultimas conexiones
		 */
	}
}
