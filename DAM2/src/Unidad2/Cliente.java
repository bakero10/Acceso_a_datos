package Unidad2;

import java.io.Serializable;

public class Cliente implements Serializable{
	private String nombre;
	private String telefono;
	private String direccion;
	private String nif;
	transient String moroso;

	public Cliente(String nombre, String telefono, String direccion, String nif, String moroso) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.nif = nif;
		this.moroso = moroso;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", nif=" + nif
				+ "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getMoroso() {
		return moroso;
	}

	public void setMoroso(String moroso) {
		this.moroso = moroso;
	}
	
	
	
	
	
	
}
