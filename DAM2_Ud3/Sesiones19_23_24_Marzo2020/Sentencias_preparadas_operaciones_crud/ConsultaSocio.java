package Sentencias_preparadas_operaciones_crud;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConsultaSocio extends JFrame {
 
	Container contenedor;
	JButton boton1, boton2, boton3, boton4, boton5, boton6, boton7;
	JTextField caja1, caja2, caja3, caja4, caja5, caja6;
	JLabel etiq1, etiq2, etiq3, etiq4, etiq5, etiq6, etiq7, etiq8;
	static int ultimo = 0;
	static int posicion = 1;
	
	
	public ConsultaSocio() {
		this.setTitle("Busqueda de Socios por localidad");
		contenedor = this.getContentPane();
		contenedor.setLayout(null);
		
		//Ponemos nombre e inicializamos botones, cajas y etiquetas
		boton1 = new JButton("Anterior");
		boton2 = new JButton("Siguiente");
		boton3 = new JButton("Buscar");
		boton4 = new JButton("Actualizar");
		boton5 = new JButton("Nuevo");
		boton6 = new JButton("Borrar");
		boton7 = new JButton("Clear");
		
		caja1 = new JTextField();
		caja2 = new JTextField();
		caja3 = new JTextField();
		caja4 = new JTextField();
		caja5 = new JTextField();
		caja6 = new JTextField();
		
		etiq1 = new JLabel("Socio");
		etiq2 = new JLabel("Nombre");
		etiq3 = new JLabel("Estatura");
		etiq4 = new JLabel("Edad");
		etiq5 = new JLabel("Localidad");
		etiq6 = new JLabel(); //Le pondremos texto despues, cuando mostremos el conteo
		etiq7 = new JLabel("cm.");
		etiq8 = new JLabel("anyos");
		
		//A�adimos los elementos al contenedor y lo posicionamos
		contenedor.add(boton1);
		contenedor.add(boton2);
		contenedor.add(boton3);
		contenedor.add(boton4);
		contenedor.add(boton5);
		contenedor.add(boton6);
		contenedor.add(boton7);
		contenedor.add(caja1);
		contenedor.add(caja2);
		contenedor.add(caja3);
		contenedor.add(caja4);
		contenedor.add(caja5);
		contenedor.add(caja6);
		contenedor.add(etiq1);
		contenedor.add(etiq2);
		contenedor.add(etiq3);
		contenedor.add(etiq4);
		contenedor.add(etiq5);
		contenedor.add(etiq6);
		contenedor.add(etiq7);
		contenedor.add(etiq8);
		
		boton1.setBounds(120, 315, 85,20);
		boton2.setBounds(275, 315, 90,20);
		boton3.setBounds(345, 95, 85,20);
		boton4.setBounds(280, 268, 100,20);
		boton5.setBounds(70, 268, 85,20);
		boton6.setBounds(175, 268, 85,20);
		boton7.setBounds(345, 120, 85,20);
		
		caja1.setBounds(120, 70, 45,20);
		caja2.setBounds(120, 95, 200,20);
		caja3.setBounds(120, 125, 45,20);
		caja4.setBounds(120, 155, 45,20);
		caja5.setBounds(120, 185, 100,20);
		caja6.setBounds(345, 70, 85,20);
		
		etiq1.setBounds(50, 70, 60,20);
		etiq2.setBounds(50, 95, 60,20);
		etiq3.setBounds(50, 125, 60,20);
		etiq4.setBounds(50, 155, 60,20);
		etiq5.setBounds(50, 185, 60,20);
		etiq6.setBounds(205, 240, 100,20);
		etiq7.setBounds(170, 125, 60,20);
		etiq8.setBounds(170, 155, 60,20);
		
		//Indicamos el oyente boton
		boton1.addActionListener(new OyenteBoton());
		boton2.addActionListener(new OyenteBoton());
		boton3.addActionListener(new OyenteBoton());
		boton4.addActionListener(new OyenteBoton());
		boton5.addActionListener(new OyenteBoton());
		boton6.addActionListener(new OyenteBoton());
		boton7.addActionListener(new OyenteBoton());
		
		//Poner en no visible los alementos que no necesitamos y los botones temporalmente
		caja1.setEditable(false);
		caja2.setEditable(true);
		caja3.setEditable(true);
		caja4.setEditable(true);
		caja5.setEditable(true);
		
		boton1.setEnabled(false);
		boton2.setEnabled(false);
		//Ponemos la ventana en visible y asignamos tama�o y condicion de cierre
		this.setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	class OyenteBoton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AccesoBdatos abd = new AccesoBdatos();
			abd.conectar();
			ResultSet rs = abd.consultaLocalidad(caja6.getText());
			
			try {
			if (rs != null) {
				boton1.setEnabled(true);
				boton2.setEnabled(true);
				//int ultimo = 0; //lo he declarado como estatico mas arriba para que se pudiera ver desde todos lados, en especial desde los try catch
				//rs.first();primero = rs.getRow(); // no hace falta por que por defecto cuando le por primera vez esta en la primera posicion
				//int posicion = 1; //hay que declararlo como estatico mas arriba por que sino cada ve que entra al oyente boton, lo pondria todo el rato en 1
				rs.last();ultimo = rs.getRow();
				rs.first();
				
				if(event.getSource() == boton3) { //Buscar
					posicion = 1;
					etiq6.setText("Socio " + posicion + " de " + ultimo);
					rs.absolute(posicion);
					
					caja1.setText(String.valueOf(rs.getInt(1)));
					caja2.setText(rs.getString(2));
					caja3.setText(String.valueOf(rs.getInt(3)));
					caja4.setText(String.valueOf(rs.getInt(4)));
					caja5.setText(rs.getString(5));
				}
								
				if(event.getSource() == boton2) { //Siguiente
					posicion++;
					if(posicion > ultimo) {
						posicion = ultimo;
						JOptionPane.showMessageDialog(null,"No existen registros posteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else {
						etiq6.setText("Socio " + posicion + " de " + ultimo);
						rs.absolute(posicion);
						
						caja1.setText(String.valueOf(rs.getInt(1)));
						caja2.setText(rs.getString(2));
						caja3.setText(String.valueOf(rs.getInt(3)));
						caja4.setText(String.valueOf(rs.getInt(4)));
						caja5.setText(rs.getString(5));
					}
				}
				if(event.getSource() == boton1) { //Anterior
					posicion--;
					if (posicion < 1) {
						posicion = 1;
						JOptionPane.showMessageDialog(null,"No existen registros anteriores" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else {
						etiq6.setText("Socio " + posicion + " de " + ultimo);
						rs.absolute(posicion);
						
						caja1.setText(String.valueOf(rs.getInt(1)));
						caja2.setText(rs.getString(2));
						caja3.setText(String.valueOf(rs.getInt(3)));
						caja4.setText(String.valueOf(rs.getInt(4)));
						caja5.setText(rs.getString(5));
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
			
			
			//AMPLIACION DEL CODIGO PARA INCORPORAR LOS NUEVO DEL A�O DAM2 PARA ACTUALIZAR, BORRAR Y A�ADIR SOCIOS
			if(event.getSource() == boton4) { //Actualizar socio
				String nombre, estatura, edad, localidad, id;
				id = caja1.getText();
				nombre = caja2.getText();
				estatura = caja3.getText();
				edad = caja4.getText();
				localidad = caja5.getText();
				
				int resultado = abd.actualizarSocio(Integer.valueOf(id),nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Registro actualizado correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"El registro no se ha podido actualizar" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			//////
			if(event.getSource() == boton5) { //A�adir socio
				String nombre, estatura, edad, localidad, id;
				id = caja1.getText();
				nombre = caja2.getText();
				estatura = caja3.getText();
				edad = caja4.getText();
				localidad = caja5.getText();
				
				int resultado = abd.aniadirSocio(nombre,Integer.valueOf(estatura),Integer.valueOf(edad),localidad);
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Se ha creado un socio nuevo correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"No se ha podido crear el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			/////
			if(event.getSource() == boton6) { //Borrar socio
				String nombre, estatura, edad, localidad, id;
				
				id = caja1.getText();
				nombre = caja2.getText();
				estatura = caja3.getText();
				edad = caja4.getText();
				localidad = caja5.getText();
					
				int resultado = abd.borrarSocio(Integer.valueOf(id));
				if(resultado == 1) {
					JOptionPane.showMessageDialog(null,"Se ha borrado el socio correctamente" ,"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"No se ha podido borrar el socio" ,"Mensaje", JOptionPane.ERROR_MESSAGE);
				}	
			}
			
			
		/////
			if(event.getSource() == boton7) { //Dejar campos en blanco
				caja1.setText("");
				caja2.setText("");
				caja3.setText("");
				caja4.setText("");
				caja5.setText("");
				boton1.setEnabled(false);
				boton2.setEnabled(false);
				etiq6.setVisible(false);
				
			}
			
			
		}//del metodo oyente	
	}//de la clase oyente
	
	
	public static void main(String [] args) {
		ConsultaSocio ventana = new ConsultaSocio();
		
	}//del main
}// de la clase


