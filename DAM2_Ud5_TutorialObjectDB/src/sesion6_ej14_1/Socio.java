package sesion6_ej14_1;

//Cogido ejemplo de internet https://www.arquitecturajava.com/jpa-onetoone-y-relaciones-1-a-1/

//Explicacion de unidireccional y bidireccional:
//Unidireccional quiere decir que la clase socio por ejemplo, puede acceder a su tarjeta por la clave foranea y la relacion. Pero desde tarjeta no seremos capaz de ver al Socio
//Bidireccional, al contrario que la anterior, podemos acceder de una tabla(clase) a otra, independientemente de cual estemos manejando
//Explicacion más detallada: http://www.jtech.ua.es/j2ee/restringido/jpa/sesion03-apuntes.html#:~:text=Nos%20indica%20si%20desde%20una,puede%20obtener%20la%20otra%20parte.


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Socio {
	@Id
	private String dni;
	private String nombre;
	private String apellidos;

	@OneToOne
	private Tarjeta tarjeta;
	
	
	public Socio(String dni, String nombre, String apellidos, Tarjeta tarjeta) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tarjeta = tarjeta;
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}



}
