package Ejercicios;

public class Equipo {

	private String nombre;
	private String entrenador;
	private int anyoCreacion;
	private int titulos;
	
	public Equipo(String nombre, String entrenador, int anyoCreacion, int titulos) {
		super();
		this.nombre = nombre;
		this.entrenador = entrenador;
		this.anyoCreacion = anyoCreacion;
		this.titulos = titulos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public int getAnyoCreacion() {
		return anyoCreacion;
	}

	public void setAnyoCreacion(int anyoCreacion) {
		this.anyoCreacion = anyoCreacion;
	}

	public int getTitulos() {
		return titulos;
	}

	public void setTitulos(int titulos) {
		this.titulos = titulos;
	}
	
	
	
	
	
}
