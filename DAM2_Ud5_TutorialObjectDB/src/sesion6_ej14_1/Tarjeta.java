package sesion6_ej14_1;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Tarjeta {
	@Id
	private int numero;
	private Date caducidad;
	
	@OneToOne(mappedBy="tarjeta")
	private Socio socio;
	
	
	public Tarjeta(int numero, Date caducidad) {
		super();
		this.numero = numero;
		this.caducidad = caducidad;
	}
	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	

}
