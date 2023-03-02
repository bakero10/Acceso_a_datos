package Anexo1;

public class Socio {

	private int socioId;
	private String nombre;
	private int estatura;
	private int edad;
	private String localidad;
	
	public Socio(int socioId, String nombre, int estatura, int edad, String localidad) {
		super();
		this.socioId = socioId;
		this.nombre = nombre;
		this.estatura = estatura;
		this.edad = edad;
		this.localidad = localidad;
	}

	public int getSocioId() {
		return socioId;
	}

	public void setSocioId(int socioId) {
		this.socioId = socioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "Socio [socioId=" + socioId + ", nombre=" + nombre + ", estatura=" + estatura + ", edad=" + edad
				+ ", localidad=" + localidad + "]";
	}
	
	
	
	
	
	
}
