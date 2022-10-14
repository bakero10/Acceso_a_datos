
public class Profesor {

	String nombre;
	int antiguedad;
	
	public Profesor(String nombre, int antiguedad) {
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

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "Profesor [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}
	
}
