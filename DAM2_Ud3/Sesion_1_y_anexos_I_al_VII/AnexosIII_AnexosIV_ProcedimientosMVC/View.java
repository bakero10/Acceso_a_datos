package AnexosIII_AnexosIV_ProcedimientosMVC;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import javax.swing.JTable;

public class View extends JFrame {

	Container contenedor;
	JButton anterior, siguiente, buscar, actualizar, nuevo, borrar, clear;
	JTextField cajaId, cajaNombre, cajaEstatura, cajaEdad, cajaLocalidad, caja6;
	JLabel etiqSocio, etiqNombre, etiqEstatura, etiqEdad, etiqLocalidad, etiqConteo, etiqCm, etiqAnyos;
	static int ultimo = 0;
	static int posicion = 1;

	/**************** M�TODOS ***************************/
	// CONSTRUCTOR
	public View() { //inicializa el constructor sin public, private o protected
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
//		anterior.addActionListener(new OyenteBoton());
//		siguiente.addActionListener(new OyenteBoton());
//		buscar.addActionListener(new OyenteBoton());
//		actualizar.addActionListener(new OyenteBoton());
//		nuevo.addActionListener(new OyenteBoton());
//		borrar.addActionListener(new OyenteBoton());
//		clear.addActionListener(new OyenteBoton());
		
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

	public void conectaControlador(Controller c) {
		anterior.addActionListener(c);
		siguiente.addActionListener(c);
		buscar.addActionListener(c);
		actualizar.addActionListener(c);
		nuevo.addActionListener(c);
		borrar.addActionListener(c);
		clear.addActionListener(c);

	}
}