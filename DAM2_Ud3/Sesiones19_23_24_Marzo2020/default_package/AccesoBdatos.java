package default_package;
/*
 * Revisado para versi�n 8.0.19 de MySQL 
 * Alberto Carrera Mart�n
 * y a�adido comentarios el 17 de marzo de 2020
 * 
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
public class AccesoBdatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "demodb";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database +
									"?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "rootroot";
    private Connection conecta;
	
	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	public void desconectar() throws SQLException, ClassNotFoundException {
		
		conecta = null; // = conecta.close();
	}
	//
	//m�todo insertar. Recibe la clave (n�mero de departamento), el nombre del departamento
	// y la localidad y los inserta dentro de una fila en la tabla dept de la base de datos demodb
	// 
	public boolean insertar(Integer clave, String nombre, String localidad){
		try {
			// Se utilizan por seguridad y rapidez sentencias preparadas que ya conocemos de la sesi�n anterior
			String sql="insert into dept values (?,?,?)";
			// Creo la sentencia preparada a partir de la conexi�n
			PreparedStatement inserta=conecta.prepareStatement(sql);
			// Ahora viene el acoplamiento de valores
			// El primer ? de la instrucci�n de la l�nea 37 es un entero y su valor se obtiene del argumento "clave" pasado al m�todo insertar
			inserta.setInt(1,clave);
			// El segundo ? de la instrucci�n es una cadena y su valor se obtiene del argumento "nombre" pasado al m�todo insertar
			inserta.setString(2,nombre);
			// El tercer ? de la instrucci�n es una cadena y su valor se obtiene del argumento "localidad" pasado al m�todo insertar
			inserta.setString(3,localidad);
			// Se lleva a cabo la ejecuci�n de la instrucci�n
			inserta.executeUpdate(); 
			// Nota a la l�nea anterior. Si pusi�ramos en su lugar int filas = inserta.executeUpdate(); 
			// adem�s de ejecutarse la instrucci�n anterior nos devolver�a en la variable "filas" el n�mero de filas insertadas
			// (que como mucho ser�a 1 �No?)
			return true; // Todo ha ido bien
			// Cualquier error en la inserci�n, sin distinci�n del tipo del error, lo atrapo a continuaci�n
		} catch(SQLException e){
			// Atrapar� cualquier problema en la inserci�n: El departamento ya existe, el n�mero de departamento no puede ser null ...
			// Podr�a saber el error concreto MySQL preguntando por e.getErrorCode()
			return false;// No ha ido bien la inserci�n
		}
	}
	//
	//
	//m�todo insertarConBean. Recibe un solo argumento, un objeto departamento, y lo inserta como una fila en la tabla dept de la base de datos demodb
	// Recordad que tengo una clase Depto.java "similar" a la tabla dept con la que estamos trabajando
	// Procedimiento Muy similar al anterior, con una modificaci�n para que en caso que la inserci�n sea correcta devuelva un 1 y
	// si no es correcta devuelva el n�mero de error MySQL
	public int insertarConBean(Depto registro){
		try {
			String sql="insert into dept values (?,?,?)";
			PreparedStatement inserta =conecta.prepareStatement(sql);
			inserta.setInt(1,registro.getDeptno());
			inserta.setString(2,registro.getDname());
			inserta.setString(3,registro.getLoc());
			inserta.executeUpdate();
			return 1;
		} catch(SQLException e){
			return e.getErrorCode();
		}
	}
	//
	// M�todo consultarTodos. �NO DEVUELVE UN RESULTSET O CONJUNTO DE FILAS DE UNA TABLA QUE TANTO NOS GUSTA! SINO
	// QUE ESE RESULTSET OBTENIDO, LO PASA A UN ARRAYLIST DE DEPARTAMENTOS  Y DEVUELVE DICHO ARRAYLIST
	//
	public ArrayList<Depto> consultarTodos(){
		// Creamos una lista donde ir a�adiendo cada departamento que vamos recuperando del ResultSet
		ArrayList<Depto> deptos = new ArrayList<Depto> ();
		try {
			// Preparo la sentencia. Observad que no lleva "?" o datos que tenga que pasarle antes de ejecutarla.
			PreparedStatement consulta = conecta.prepareStatement("SELECT * FROM dept");
			// Ejecuto la instrucci�n y obtengo en reg el ResultSet o conjunto de filas resultantes de ejecutar la Select...
			ResultSet reg = consulta.executeQuery ();
			//Paso a recorrer cada una de las filas del ResultSet
			while (reg.next ()) {
				// Creo un departamento y a la vez le paso al constructor los datos con los que inicializo dicho departamento
				// Esos 3 datos con los que construyo el departamento son los datos que extraigo de la fila del ResultSet
				Depto departamento = new Depto(reg.getInt (1), reg.getString (2), reg.getString (3));
				// una vez creado el departamento con los datos extra�dos de la fila, lo a�ado a la lista
				deptos.add(departamento);
			}
			consulta.close ();
		} catch (SQLException e) {
			return null; // En caso de error devuelvo la lista null
		} 
			return deptos; // Si todo va bien devuelvo la lista (�ArrayList no ResultSet!) de departamentos
		}
	//
	// m�todo consultarUno. Le paso como argumento  el n�mero de departamento y me devuelve un objeto departamento 
	// que se corresponde con dicho n�mero. Si no lo encuentra o se produce error, devuelve nulo
	public Depto consultarUno(int numero){
		try {
			PreparedStatement consulta = conecta.prepareStatement("SELECT * FROM dept WHERE deptno=?");
			// El dato que me falta para lanzar la sentencia anterior, el ?,
			// es un entero que se obtiene del argumento "numero" enviado al m�todo consultarUno
			consulta.setInt(1, numero);
			// Ejecuto la instrucci�n
			ResultSet reg = consulta.executeQuery ();
			// Creo un departamento donde dejar el resultado obtenido. El ResultSet reg solo puede tener una fila o ninguna, 
			// pues realizo una b�squeda por clave primaria Select... WHERE deptno=....
			Depto departamento = new Depto();
			// Si he recuperado una fila, monto el departamento con los datos de las columnas de dicha fila
			if (reg.next ()) {
				departamento.setDeptno(reg.getInt (1));
				departamento.setDname(reg.getString(2));
				departamento.setLoc(reg.getString(3));
			}
			// si no he recuperado ninguna, devuelvo null
			else
				departamento=null;
			consulta.close ();
			return departamento;
		}
		catch (SQLException e) {
			//System.out.println("error en la consulta - "+e.getMessage());
			return null;
		}
	}
	
	// m�todo actualizar. Le pasamos un objeto departamento y actualiza en la tabla dept,
	// el nombre y la localidad de la fila cuyo n�mero de departamento coincide con el del departamento que le paso
	// Devuelve el n�mero de filas actualizadas, que en este caso ser� 0 � 1
	public int actualizar(Depto registro){
		try {
			String sql="update dept set dname=?, loc=? where deptno=?";
			// Preparo la sentencia
			PreparedStatement actualiza = conecta.prepareStatement(sql);
			// Seguidamente el acoplamiento de los 3 valores ?
			actualiza.setString(1,registro.getDname());
			actualiza.setString(2,registro.getLoc());
			actualiza.setInt(3,registro.getDeptno());
			// ejecuto la instrucci�n "actualiza.executeUpdate()" y despu�s de ello devuelvo el n�mero de filas afectadas
			return (actualiza.executeUpdate());
			// Nota la instrucci�n anterior equivale a:
			// int filas = actualiza.executeUpdate();
			// return filas
		} catch(SQLException e){
		//System.out.println("error al actualizar en dept - "+e.getMessage());
		return 0;
		}
	}
	//
	// metodo borrar. Le pasamos un n�mero de departamento y borra dicho departamento.
	// devuelve el n�mero de filas borradas, que como mucho ser� 1
	// o el n�mero de error MySQL si se produce una excepci�n
	public int borrar (int numero){
		int filas=0; // 
		try {
			PreparedStatement baja = conecta.prepareStatement("DELETE FROM dept WHERE deptno=?");
			baja.setInt(1, numero);
			filas = baja.executeUpdate ();
			baja.close ();
		return filas;
		} catch (SQLException e) {
			return e.getErrorCode(); // En caso de error en la consulta devuelve el c�digo de error MySQL
		}
	}
}