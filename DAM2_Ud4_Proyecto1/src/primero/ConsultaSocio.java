package primero;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ConsultaSocio extends JFrame {
 
	Container contenedor;
	JButton anterior, siguiente, buscar, actualizar, nuevo, borrar, clear;
	JTextField cajaId, cajaNombre, cajaEstatura, cajaEdad, cajaLocalidad, caja6;
	JLabel etiqSocio, etiqNombre, etiqEstatura, etiqEdad, etiqLocalidad, etiqConteo, etiqCm, etiqAnyos;
	static int ultimo = 0;
	static int posicion = 1;
	
	
	public ConsultaSocio() {
		this.setTitle("Busqueda de Socios por localidad");
		contenedor = this.getContentPane();
		contenedor.setLayout(null);
		
		//Ponemos nombre e inicializamos botones, cajas y etiquetas
		anterior = new JButton("Anterior");
		siguiente = new JButton("Siguiente");
		buscar = new JButton("Buscar");
		actualizar = new JButton("Actualizar");
		nuevo = new JButton("Nuevo");
		borrar = new JButton("Borrar");
		clear = new JButton("Clear");
		
		cajaId = new JTextField();
		cajaNombre = new JTextField();
		cajaEstatura = new JTextField();
		cajaEdad = new JTextField();
		cajaLocalidad = new JTextField();
		caja6 = new JTextField();
		
		etiqSocio = new JLabel("Socio");
		etiqNombre = new JLabel("Nombre");
		etiqEstatura = new JLabel("Estatura");
		etiqEdad = new JLabel("Edad");
		etiqLocalidad = new JLabel("Localidad");
		etiqConteo = new JLabel(); //Le pondremos texto despues, cuando mostremos el conteo
		etiqCm = new JLabel("cm.");
		etiqAnyos = new JLabel("anyos");
		
		//A�adimos los elementos al contenedor y lo posicionamos
		contenedor.add(anterior);
		contenedor.add(siguiente);
		contenedor.add(buscar);
		contenedor.add(actualizar);
		contenedor.add(nuevo);
		contenedor.add(borrar);
		contenedor.add(clear);
		contenedor.add(cajaId);
		contenedor.add(cajaNombre);
		contenedor.add(cajaEstatura);
		contenedor.add(cajaEdad);
		contenedor.add(cajaLocalidad);
		contenedor.add(caja6);
		contenedor.add(etiqSocio);
		contenedor.add(etiqNombre);
		contenedor.add(etiqEstatura);
		contenedor.add(etiqEdad);
		contenedor.add(etiqLocalidad);
		contenedor.add(etiqConteo);
		contenedor.add(etiqCm);
		contenedor.add(etiqAnyos);
		
		anterior.setBounds(120, 315, 85,20);
		siguiente.setBounds(275, 315, 90,20);
		buscar.setBounds(345, 95, 85,20);
		actualizar.setBounds(280, 268, 100,20);
		nuevo.setBounds(70, 268, 85,20);
		borrar.setBounds(175, 268, 85,20);
		clear.setBounds(345, 120, 85,20);
		
		cajaId.setBounds(120, 70, 45,20);
		cajaNombre.setBounds(120, 95, 200,20);
		cajaEstatura.setBounds(120, 125, 45,20);
		cajaEdad.setBounds(120, 155, 45,20);
		cajaLocalidad.setBounds(120, 185, 100,20);
		caja6.setBounds(345, 70, 85,20);
		
		etiqSocio.setBounds(50, 70, 60,20);
		etiqNombre.setBounds(50, 95, 60,20);
		etiqEstatura.setBounds(50, 125, 60,20);
		etiqEdad.setBounds(50, 155, 60,20);
		etiqLocalidad.setBounds(50, 185, 60,20);
		etiqConteo.setBounds(205, 240, 100,20);
		etiqCm.setBounds(170, 125, 60,20);
		etiqAnyos.setBounds(170, 155, 60,20);
		
		//Indicamos el oyente boton
		anterior.addActionListener(new OyenteBoton());
		siguiente.addActionListener(new OyenteBoton());
		buscar.addActionListener(new OyenteBoton());
		actualizar.addActionListener(new OyenteBoton());
		nuevo.addActionListener(new OyenteBoton());
		borrar.addActionListener(new OyenteBoton());
		clear.addActionListener(new OyenteBoton());
		
		//Poner en no visible los alementos que no necesitamos y los botones temporalmente
		cajaId.setEditable(false);
		cajaNombre.setEditable(true);
		cajaEstatura.setEditable(true);
		cajaEdad.setEditable(true);
		cajaLocalidad.setEditable(true);
		
		anterior.setEnabled(false);
		siguiente.setEnabled(false);
		//Ponemos la ventana en visible y asignamos tama�o y condicion de cierre
		this.setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	class OyenteBoton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AccesoBdatos abd = new AccesoBdatos();
			abd.conectar();
			//ResultSet rs = abd.consultaLocalidad(caja6.getText());
			List<Socio> rs = abd.consultaLocalidad(caja6.getText());
						
			try {
			if (rs != null) {
				anterior.setEnabled(true);
				siguiente.setEnabled(true);
				//int ultimo = 0; //lo he declarado como estatico mas arriba para que se pudiera ver desde todos lados, en especial desde los try catch
				//rs.first();primero = rs.getRow(); // no hace falta por que por defecto cuando le por primera vez esta en la primera posicion
				//int posicion = 1; //hay que declararlo como estatico mas arriba por que sino cada ve que entra al oyente boton, lo pondria todo el rato en 1
				//rs.last(); ultimo = rs.getRow();
				//rs.first();
				ultimo = rs.size()-1;
				
				
				if(event.getSource() == buscar) { //Buscar
					posicion = 0;
					etiqConteo.setText("Socio " + (posicion+1) + " de " + (ultimo+1));
										
					Socio s = rs.get(posicion);
					cajaId.setText(String.valueOf(s.getSocioId()));
					cajaNombre.setText(s.getNombre());
					cajaEstatura.setText(String.valueOf(s.getEstatura()));
					cajaEdad.setText(String.valueOf(s.getEdad()));
					cajaLocalidad.setText(s.getLocalidad());
				}
								
				if(event.getSource() == siguiente) { //Siguiente
					posicion++;
					if(posicion > ultimo) {
						posicion = ultimo;
						JOptionPane.showMessageDialog(null,"No existen registros posteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else {
						etiqConteo.setText("Socio " + (posicion+1) + " de " + (ultimo+1));
						Socio s = rs.get(posicion);
						
						cajaId.setText(String.valueOf(s.getSocioId()));
						cajaNombre.setText(s.getNombre());
						cajaEstatura.setText(String.valueOf(s.getEstatura()));
						cajaEdad.setText(String.valueOf(s.getEdad()));
						cajaLocalidad.setText(s.getLocalidad());
					}
				}
				if(event.getSource() == anterior) { //Anterior
					posicion--;
					if (posicion < 0) {
						posicion = 1;
						JOptionPane.showMessageDialog(null,"No existen registros anteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else {
						etiqConteo.setText("Socio " + (posicion+1) + " de " + (ultimo+1));
						Socio s = rs.get(posicion);
						
						cajaId.setText(String.valueOf(s.getSocioId()));
						cajaNombre.setText(s.getNombre());
						cajaEstatura.setText(String.valueOf(s.getEstatura()));
						cajaEdad.setText(String.valueOf(s.getEdad()));
						cajaLocalidad.setText(s.getLocalidad());
					}
					
				}
				
			}else {
				JOptionPane.showMessageDialog(null,"No se han encontrado socios de " + caja6.getText() ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				abd.desconectar();
			}
			
			}catch (Exception e) {
				System.out.println("Error sin especificar, borrar despues");
				e.printStackTrace();
			}
			
			
			//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL ANIO DAM2 PARA ACTUALIZAR, BORRAR Y ANADIR SOCIOS
			if(event.getSource() == actualizar) { //Actualizar socio
				String nombre, estatura, edad, localidad, id;
				id = cajaId.getText();
				nombre = cajaNombre.getText();
				estatura = cajaEstatura.getText();
				edad = cajaEdad.getText();
				localidad = cajaLocalidad.getText();
				
				int resultado = abd.actualizarSocio(Integer.valueOf(id),nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Registro actualizado correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"El registro no se ha podido actualizar" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			//////
			if(event.getSource() == nuevo) { //Anadir socio
				String nombre, estatura, edad, localidad, id;
				id = cajaId.getText();
				nombre = cajaNombre.getText();
				estatura = cajaEstatura.getText();
				edad = cajaEdad.getText();
				localidad = cajaLocalidad.getText();
				
				int resultado = abd.aniadirSocio(nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Se ha creado un socio nuevo correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			/////
			if(event.getSource() == borrar) { //Borrar socio
				String nombre, estatura, edad, localidad, id;
				
				id = cajaId.getText();
				nombre = cajaNombre.getText();
				estatura = cajaEstatura.getText();
				edad = cajaEdad.getText();
				localidad = cajaLocalidad.getText();
					
				int resultado = abd.borrarSocio(Integer.valueOf(id));
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Se ha borrado el socio correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"No se ha podido borrar el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}	
			} 
			
			
		/////
			if(event.getSource() == clear) { //Dejar campos en blanco
				cajaId.setText("");
				cajaNombre.setText("");
				cajaEstatura.setText("");
				cajaEdad.setText("");
				cajaLocalidad.setText("");
				caja6.setText("");
				anterior.setEnabled(false);
				siguiente.setEnabled(false);
				etiqConteo.setVisible(false);
				
			}
			
			
		}//del metodo oyente	
	}//de la clase oyente
	
	
	public static void main(String [] args) {
		ConsultaSocio ventana = new ConsultaSocio();
		
	}//del main
}// de la clase


