package AnexosIII_AnexosIV_ProcedimientosMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class Controller implements ActionListener {
	private View view;

	// CONSTRUCTOR
	public Controller(View view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) { //ActionPerformed esta a la espera de que suceda algun evento declarado en la clase view
		ResultSet rs = consultaLocalidad(view.caja6.getText());
		
		
		try {
		if (rs != null) {
			view.anterior.setEnabled(true);
			view.siguiente.setEnabled(true);
			//int ultimo = 0; //lo he declarado como estatico mas arriba para que se pudiera ver desde todos lados, en especial desde los try catch
			//rs.first();primero = rs.getRow(); // no hace falta por que por defecto cuando le por primera vez esta en la primera posicion
			//int posicion = 1; //hay que declararlo como estatico mas arriba por que sino cada ve que entra al oyente boton, lo pondria todo el rato en 1
			rs.last();view.ultimo = rs.getRow();
			rs.first();
			
			if(event.getSource() == view.buscar) { //Buscar
				view.posicion = 1;
				view.etiqConteo.setText("Socio " + view.posicion + " de " + view.ultimo);
				rs.absolute(view.posicion);
				
				view.cajaId.setText(String.valueOf(rs.getInt(1)));
				view.cajaNombre.setText(rs.getString(2));
				view.cajaEstatura.setText(String.valueOf(rs.getInt(3)));
				view.cajaEdad.setText(String.valueOf(rs.getInt(4)));
				view.cajaLocalidad.setText(rs.getString(5));
			}
							
			if(event.getSource() == view.siguiente) { //Siguiente
				view.posicion++;
				if(view.posicion > view.ultimo) {
					view.posicion = view.ultimo;
					JOptionPane.showMessageDialog(null,"No existen registros posteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					view.etiqConteo.setText("Socio " + view.posicion + " de " + view.ultimo);
					rs.absolute(view.posicion);
					
					view.cajaId.setText(String.valueOf(rs.getInt(1)));
					view.cajaNombre.setText(rs.getString(2));
					view.cajaEstatura.setText(String.valueOf(rs.getInt(3)));
					view.cajaEdad.setText(String.valueOf(rs.getInt(4)));
					view.cajaLocalidad.setText(rs.getString(5));
				}
			}
			if(event.getSource() == view.anterior) { //Anterior
				view.posicion--;
				if (view.posicion < 1) {
					view.posicion = 1;
					JOptionPane.showMessageDialog(null,"No existen registros anteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					view.etiqConteo.setText("Socio " + view.posicion + " de " + view.ultimo);
					rs.absolute(view.posicion);
					
					view.cajaId.setText(String.valueOf(rs.getInt(1)));
					view.cajaNombre.setText(rs.getString(2));
					view.cajaEstatura.setText(String.valueOf(rs.getInt(3)));
					view.cajaEdad.setText(String.valueOf(rs.getInt(4)));
					view.cajaLocalidad.setText(rs.getString(5));
				}
				
			}
			
		}else {
			JOptionPane.showMessageDialog(null,"No se han encontrado socios de " + view.caja6.getText() ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
		
		}catch (Exception e) {
			System.out.println("Error sin especificar, borrar despues");
			e.printStackTrace();
		}
		
		
		//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL A�O DAM2 PARA ACTUALIZAR, BORRAR Y A�ADIR SOCIOS
		if(event.getSource() == view.actualizar) { //Actualizar socio
			String nombre, estatura, edad, localidad, id;
			id = view.cajaId.getText();
			nombre = view.cajaNombre.getText();
			estatura = view.cajaEstatura.getText();
			edad = view.cajaEdad.getText();
			localidad = view.cajaLocalidad.getText();
			
			int resultado = actualizarSocio(Integer.valueOf(id),nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
			if(resultado == 1) {
				JOptionPane.showMessageDialog(null,"Registro actualizado correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"El registro no se ha podido actualizar" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		//////
		if(event.getSource() == view.nuevo) { //Anadir socio
			String nombre, estatura, edad, localidad, id;
			id = view.cajaId.getText();
			nombre = view.cajaNombre.getText();
			estatura = view.cajaEstatura.getText();
			edad = view.cajaEdad.getText();
			localidad = view.cajaLocalidad.getText();
			
			int resultado = aniadirSocio(nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
			if(resultado == 1) {
				JOptionPane.showMessageDialog(null,"Se ha creado un socio nuevo correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"No se ha podido crear el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/////
		if(event.getSource() == view.borrar) { //Borrar socio
			String nombre, estatura, edad, localidad, id;
			
			id = view.cajaId.getText();
			nombre = view.cajaNombre.getText();
			estatura = view.cajaEstatura.getText();
			edad = view.cajaEdad.getText();
			localidad = view.cajaLocalidad.getText();
				
			int resultado = borrarSocio(Integer.valueOf(id));
			if(resultado == 1) {
				JOptionPane.showMessageDialog(null,"Se ha borrado el socio correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"No se ha podido borrar el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
			}	
		} 
		
		
	/////
		if(event.getSource() == view.clear) { //Dejar campos en blanco
			view.cajaId.setText("");
			view.cajaNombre.setText("");
			view.cajaEstatura.setText("");
			view.cajaEdad.setText("");
			view.cajaLocalidad.setText("");
			view.caja6.setText("");
			view.anterior.setEnabled(false);
			view.siguiente.setEnabled(false);
			view.etiqConteo.setVisible(false);
			
		}
		
		
	}//del metodo oyente	
	
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//AQUI ESTAN TODOS LOS METODOS DE CONSULTAS A LA BASE DE DATOS QUE ANTES ESTABAN EN LA CLASE ACCESOBDATOS
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	//Creamos un metodo que primero mire si la localidad que nos da esta vacia, devuelva un ResultSet con todos los socios.
	//Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo los socios de esa Localidad.
	//Ademas despues contaremos el numero de socios(filas) del ResultSet en la interfaz
		
	public ResultSet consultaLocalidad(String localidad){
		ResultSet salida = null;
		 //Si localidad es distinto de vacio ejecuta lo de dentro
		if(localidad.isEmpty()==false) {
			try {
			Statement consulta = Bd.getConexion().createStatement();
			ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
			
//			LO HEMOS EDITADO PARA QUE AHOIRA LA CONSULTA SE HAGA A TRAVES DE UNA SENTENCIA PREPARADA EN LA BASE DE DATOS
//			CallableStatement consulta = Bd.getConexion().prepareCall("CALL buscarLocalidad(?)");
//			consulta.setString(1, localidad);
//			ResultSet reg = consulta.executeQuery();
				
			if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
				salida = reg;
			}else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
				salida = null;
			}
			}catch(SQLException sqle) {
				System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
				sqle.printStackTrace();
			}
		}else {
			try {
				Statement consulta = Bd.getConexion().createStatement();
				ResultSet reg = consulta.executeQuery ("SELECT * FROM socio");
					
				if (reg.next()) { //Si hay siguiente devolvera un result set con todos los socios
					salida = reg;
				}else {
					salida = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
				}
				}catch(SQLException sqle) {
					System.out.println("Error en la consulta para mostrar todos los socios");
				}
		}
		return salida;
	}
	

	//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL A�O DAM2 PARA ACTUALIZAR, BORRAR Y A�ADIR SOCIOS
	public int actualizarSocio(int id,String nombre, int estatura, int edad, String localidad) {
		try {
			String sql = "update socio set nombre=?, estatura=?, edad=?, localidad=? where socioID=?";
			PreparedStatement consulta = Bd.getConexion().prepareStatement(sql);
			consulta.setString(1,nombre);
			consulta.setInt(2,estatura);
			consulta.setInt(3,edad);
			consulta.setString(4,localidad);
			consulta.setInt(5,id);
			
			return (consulta.executeUpdate());
			
		} catch (SQLException e) {
			return 0;
		}
		
	}// del metodo actualizarSocio
	
	
	
	
	public int aniadirSocio(String nombre, int estatura, int edad, String localidad) {
		try {
			//Hacemos una consulta para ver cual es el ultimo id y le sumaremos 1
			int ultimoID=0;
			PreparedStatement consultaUltimoID = Bd.getConexion().prepareStatement("select socioID from socio order by socioID desc limit 1");
			ResultSet rs = consultaUltimoID.executeQuery();
			//rs.next(); Acordarse de hacer esto para que pueda leer el registro de la primera posicion para luego poder hacerle un rs.getInt o .getString...
			rs.next();
			ultimoID = rs.getInt(1)+1;
			
			PreparedStatement consulta = Bd.getConexion().prepareStatement("insert into socio values (?,?,?,?,?)");
			consulta.setInt(1, ultimoID);
			consulta.setString(2, nombre);
			consulta.setInt(3, estatura);
			consulta.setInt(4, edad);
			consulta.setString(5, localidad);
			
			return consulta.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}// del metodo aniadirSocio
	
	
	
	public int borrarSocio(int id) {
		try {
			String sql = "delete from socio where socioID=?;";
			PreparedStatement consulta = Bd.getConexion().prepareStatement(sql);
			consulta.setInt(1, id);
			
			return (consulta.executeUpdate());
		} catch (SQLException e) {
			return 0;
		}
	}//del metodo borrarSocio
	
	
		
}

	
	

	

