package Unidad2;

import java.io.Serializable;

public class profesor implements Serializable{

	String nombre;
	double antiguedad;
	
	public profesor(String nombre, double antiguedad) {
		super();
		this.nombre = nombre;
		this.antiguedad = antiguedad;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(double antiguedad) {
		this.antiguedad = antiguedad;
	}
	@Override
	public String toString() {
		return "profesor [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}
}
